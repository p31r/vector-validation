package com.p31r.vectorvalidation.service

import com.p31r.vectorvalidation.BaseIntegrationTests
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ClassPathResource

class VectorValidationServiceIntegrationTest : BaseIntegrationTests() {

    @Autowired
    private lateinit var vectorValidationService: VectorValidationService

    private val stringSetValid by lazy {
        readResourceToSet("json/string_set-valid.json")
    }
    private val stringSetInvalidNumbers by lazy {
        readResourceToSet("json/string_set-invalid_numbers.json")
    }
    private val stringSetInvalidOccurrence by lazy {
        readResourceToSet("json/string_set-invalid_occurrence.json")
    }
    private val stringSetInvalidSpecial by lazy {
        readResourceToSet("json/string_set-invalid_special.json")
    }
    private val stringSetInvalidUppercase by lazy {
        readResourceToSet("json/string_set-invalid_uppercase.json")
    }

    private fun readResourceToSet(resourceName: String): Set<String> = objectMapper.readerForListOf(String::class.java)
        .readValue<List<String>>(ClassPathResource(resourceName).inputStream)
        .toSet()

    /** Tests service method [VectorValidationService.isStringValid] - valid strings */
    @Test
    fun `isStringValid valid`() {
        val checkedMap = getCheckedMap(stringSetValid)
        assertTrue(checkedMap.values.all { it })
    }

    /** Tests service method [VectorValidationService.isStringValid] - invalid - numbers */
    @Test
    fun `isStringValid invalid - empty and blank string`() {
        assertFalse(vectorValidationService.isStringValid(""))
        assertFalse(vectorValidationService.isStringValid(" "))
    }

    /** Tests service method [VectorValidationService.isStringValid] - invalid - numbers */
    @Test
    fun `isStringValid invalid - numbers`() {
        val checkedMap = getCheckedMap(stringSetInvalidNumbers)
        assertTrue(checkedMap.values.all { !it })
    }

    /** Tests service method [VectorValidationService.isStringValid] - invalid - occurrence */
    @Test
    fun `isStringValid invalid - occurrence`() {
        val checkedMap = getCheckedMap(stringSetInvalidOccurrence)
        assertTrue(checkedMap.values.all { !it })
    }

    /** Tests service method [VectorValidationService.isStringValid] - invalid - special */
    @Test
    fun `isStringValid invalid - special`() {
        val checkedMap = getCheckedMap(stringSetInvalidSpecial)
        assertTrue(checkedMap.values.all { !it })
    }

    /** Tests service method [VectorValidationService.isStringValid] - invalid - uppercase */
    @Test
    fun `isStringValid invalid - uppercase`() {
        val checkedMap = getCheckedMap(stringSetInvalidUppercase)
        assertTrue(checkedMap.values.all { !it })
    }

    private fun getCheckedMap(setToCheck: Set<String>): Map<String, Boolean> =
        setToCheck.associateWith { vectorValidationService.isStringValid(it) }
}

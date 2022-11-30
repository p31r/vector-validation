package com.p31r.vectorvalidation.service

import com.p31r.vectorvalidation.logic.VectorValidator
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class VectorValidationService {

    private val log = LoggerFactory.getLogger(javaClass)
    private val validator = VectorValidator

    fun isStringValid(stringToValidate: String): Boolean {
        log.info("Called isStringValid for: $stringToValidate")

        val validToCheck = validator.isValidToCheck(stringToValidate)
            .also { log.debug("String: $stringToValidate | isValidToCheck: $it") }

        val validLowercaseLetters = validator.isOnlyLowercaseLetters(stringToValidate)
            .also { log.debug("String: $stringToValidate | isOnlyLowercaseLetters: $it") }

        val validOccurrences = validator.isValidForOccurrence(stringToValidate)
            .also { log.debug("String: $stringToValidate | isValidForOccurrence: $it") }

        return validToCheck && validLowercaseLetters && validOccurrences
    }

}

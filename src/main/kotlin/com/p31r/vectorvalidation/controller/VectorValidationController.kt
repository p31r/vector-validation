package com.p31r.vectorvalidation.controller

import com.p31r.vectorvalidation.service.VectorValidationService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class VectorValidationController(
    private val vectorValidationService: VectorValidationService
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping("/validate")
    fun validateString(
        @RequestParam stringToValidate: String
    ): Boolean {
        log.info("GET /validate?stringToValidate=$stringToValidate")

        return vectorValidationService.isStringValid(stringToValidate).also {
            log.info("GET /validate?stringToValidate=$stringToValidate response: $it")
        }
    }

}

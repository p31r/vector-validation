package com.p31r.vectorvalidation.logic

import kotlin.math.abs

object VectorValidator {

    private val lowercaseLetterRegex = "^[a-z]+$".toRegex()

    fun isValidToCheck(s: String): Boolean = s.isNotBlank()

    fun isOnlyLowercaseLetters(s: String): Boolean = lowercaseLetterRegex.matches(s)

    fun isValidForOccurrence(s: String): Boolean = s.fold(emptyMap<Char, Int>()) { charMap, char ->
        charMap + mapOf(char to (charMap.getOrDefault(char, 0) + 1))
    }
        .values
        .groupBy { it }
        .let { mapOccurrences ->
            when (mapOccurrences.size) {
                1 -> true
                2 -> mapOccurrences.values.any { it.size == 1 }
                        && mapOccurrences.keys.let { abs(it.first() - it.last()) == 1 }

                else -> false
            }
        }

}

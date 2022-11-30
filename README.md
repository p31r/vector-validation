# Vector Validation

Simple small Spring application to validate string against exact rules.

## Base information
### Description
In the big system we need to create a microservice for vector validation. The vector of the
data is represented by text (string).
Text can contain only characters a-z.
Text is valid if all characters in the text appear the same number of times. It is also valid if we
can remove just one character at 1 index in the text, and the remaining characters will occur
the same number of times.

### Task
Your task is to create a service with an API which can be used by other applications in the
system to validate a text.
You can use whatever technologies (language, frameworks, etc.) you want. However you
should be able to explain your choice.

## Rules
String is valid, when:
1) is not null or blank
2) contains only characters from the set "a-z"
3) all characters have the same number of occurrences (i.e., the same frequency)
4) when the one character is removed from the string, previous point is still valid

For the rules, previous conditions are implemented as:
1) only non-null string is allowed into AP & string is checked for "notBlank"
2) Regex patter is used - "^[a-z]+$"
3) Kotlin collection function groupBy with special check on the number of occurrences


## Project info

### Setup
- full Kotlin
- SpringBoot Web
- Jackson

### How to run
1) plain - from class com/p31r/vectorvalidation/VectorValidationApplication.kt
   1) run from the class (basic IDEs should be able to run the application)
2) gradle - from the project root
   1) from terminal in project root
   2) $ gradle bootRun
3) gradle wrapper - from the project roo
   1) from terminal in project root
   2) $ ./gradlew bootRun
4) gradle plugin - if yours IDE support it
5) run the jar
   1) after the build, there should be JAR file
   2) projectRoot/build/libs
   3) start the JAR file from terminal
   4) $ java -jar pathToFile

## Usage
After running the application, SpringBoot web should kick in and start a server (default port is 8080)

### Endpoint
- method: GET
- url: default:defaultPort/validate (so for default: "localhost:8080/validate")
- query: "stringToValidate", string value
- content type: application/json

Example of the call:
localhost:8080/validate?stringToValidate=abcabc

returns: "true"


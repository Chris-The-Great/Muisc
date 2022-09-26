package com.example.ccmusichomework.rest

/**
 * Creating custom exectionps should the retiving of data return null or fail
 */
class NullResponseFromServer(message: String) : Exception(message)
class FailureResponseFromServer(message: String?) : Exception(message)
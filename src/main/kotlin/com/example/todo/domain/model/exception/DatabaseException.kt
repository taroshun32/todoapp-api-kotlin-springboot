package com.example.todo.domain.model.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
class DatabaseException : BaseException {

  constructor(
    debugMessage: String,
    frontMessage: String = ExceptionFrontMessages.INTERNAL_SERVER_ERROR,
    errorObject: Any? = null
  ) : super(HttpStatus.INTERNAL_SERVER_ERROR, debugMessage, frontMessage, errorObject)

  constructor(
    cause: Throwable,
    debugMessage: String = cause.message ?: "${cause::class.simpleName} message is null",
    frontMessage: String = ExceptionFrontMessages.INTERNAL_SERVER_ERROR,
    errorObject: Any? = null
  ) : super(HttpStatus.INTERNAL_SERVER_ERROR, cause, debugMessage, frontMessage, errorObject)
}

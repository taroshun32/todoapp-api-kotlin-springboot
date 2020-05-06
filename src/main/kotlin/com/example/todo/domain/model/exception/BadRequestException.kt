package com.example.todo.domain.model.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
class BadRequestException : BaseException {

  constructor(
    debugMessage: String,
    frontMessage: String = ExceptionFrontMessages.BAD_REQUEST,
    errorObject: Any? = null
  ) : super(HttpStatus.BAD_REQUEST, debugMessage, frontMessage, errorObject)

  constructor(
    cause: Throwable,
    debugMessage: String = cause.message ?: "${cause::class.simpleName} message is null",
    frontMessage: String = ExceptionFrontMessages.BAD_REQUEST,
    errorObject: Any? = null
  ) : super(HttpStatus.BAD_REQUEST, cause, debugMessage, frontMessage, errorObject)
}

package com.example.todo.domain.model.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.NOT_FOUND)
class NotFoundException : BaseException {

  constructor(
    debugMessage: String,
    frontMessage: String = ExceptionFrontMessages.NOT_FOUND,
    errorObject: Any? = null
  ) : super(HttpStatus.NOT_FOUND, debugMessage, frontMessage, errorObject)

  constructor(
    cause: Throwable,
    debugMessage: String = cause.message ?: "${cause::class.simpleName} message is null",
    frontMessage: String = ExceptionFrontMessages.NOT_FOUND,
    errorObject: Any? = null
  ) : super(HttpStatus.NOT_FOUND, cause, debugMessage, frontMessage, errorObject)
}

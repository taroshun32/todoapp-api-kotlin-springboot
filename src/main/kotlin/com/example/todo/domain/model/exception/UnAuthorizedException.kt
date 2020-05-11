package com.example.todo.domain.model.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
class UnAuthorizedException : BaseException {

  constructor(
    debugMessage: String,
    frontMessage: String = ExceptionFrontMessages.UNAUTHORIZED,
    errorObject: Any? = null
  ) : super(HttpStatus.UNAUTHORIZED, debugMessage, frontMessage, errorObject)

  constructor(
    cause: Throwable,
    debugMessage: String = cause.message ?: "${cause::class.simpleName} message is null",
    frontMessage: String = ExceptionFrontMessages.UNAUTHORIZED,
    errorObject: Any? = null
  ) : super(HttpStatus.UNAUTHORIZED, cause, debugMessage, frontMessage, errorObject)
}

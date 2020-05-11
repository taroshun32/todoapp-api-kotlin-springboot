package com.example.todo.domain.model.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * リソースにアクセスができなかった場合にスローする例外クラス
 */
@ResponseStatus(code = HttpStatus.FORBIDDEN)
class ForbiddenException : BaseException {

  constructor(
    debugMessage: String,
    frontMessage: String = ExceptionFrontMessages.FORBIDDEN,
    errorObject: Any? = null
  ) : super(HttpStatus.FORBIDDEN, debugMessage, frontMessage, errorObject)

  constructor(
    cause: Throwable,
    debugMessage: String = cause.message ?: "${cause::class.simpleName} message is null",
    frontMessage: String = ExceptionFrontMessages.FORBIDDEN,
    errorObject: Any? = null
  ) : super(HttpStatus.FORBIDDEN, cause, debugMessage, frontMessage, errorObject)
}

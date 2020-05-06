package com.example.todo.domain.model

import com.example.todo.domain.model.exception.BaseException
import com.example.todo.util.PathParamRemover
import org.springframework.http.HttpStatus
import javax.servlet.http.HttpServletRequest

/**
 * デバッグ情報（※クライアントには返さない）
 */
data class DebugInfo(
  val method: String,
  val path: String,
  val status: HttpStatus,
  val exceptionName: String? = null,
  val parameters: String? = null,
  val debugMessage: String? = null,
  val errorObject: Any? = null
) {

  companion object {

    /**
     * GlobalExceptionHandler or 握りつぶしたエラーをログに吐く用
     */
    fun of(
      request: HttpServletRequest,
      exception: BaseException
    ): DebugInfo =
      DebugInfo(
        method = request.method,
        path = PathParamRemover.remove(request),
        status = exception.status,
        exceptionName = exception::class.simpleName,
        parameters = request.queryString,
        debugMessage = exception.message,
        errorObject = exception.errorObject
      )
  }

  val requestLine: String = "$method $path"

  override fun toString(): String {
    val debugParameters = if (parameters.isNullOrEmpty()) "" else "\n  parameters     = $parameters"
    val debugErrorObject = if (errorObject == null) "" else "\n  errorObject    = $errorObject"

    return """DebugInfo
  Request-Line   = $requestLine$debugParameters
  Status-Line    = $status $exceptionName
  debugMessage   = $debugMessage$debugErrorObject"""
  }
}

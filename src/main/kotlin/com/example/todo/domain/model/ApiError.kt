package com.example.todo.domain.model

import com.example.todo.domain.model.exception.BaseException
import org.openapitools.spring.models.Error
import org.openapitools.spring.models.ValidationInfo
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.time.OffsetDateTime
import javax.servlet.http.HttpServletRequest

/**
 * エラーハンドリング後にレスポンス用のオブジェクト作成とログ情報を作成するクラス
 */
data class ApiError(
  val status: HttpStatus,
  val frontMessage: String,
  val validationInfos: List<ValidationInfo>? = null,
  val debugInfo: DebugInfo
) {

  companion object {
    fun of(
      request: HttpServletRequest,
      exception: BaseException,
      validationInfos: List<ValidationInfo>? = null
    ): ApiError =
      ApiError(
        status = exception.status,
        frontMessage = exception.frontMessage,
        validationInfos = validationInfos,
        debugInfo = DebugInfo.of(request, exception)
      )
  }

  // クライアントに返却するオブジェクトの生成
  fun toDto(): ResponseEntity<Any> {
    return ResponseEntity(
      Error(
        code = status.value(),
        message = frontMessage,
        validationInfo = validationInfos
      ), status
    )
  }

  override fun toString(): String =
    """$status cause=${debugInfo.exceptionName} Request-Line=${debugInfo.requestLine} debugMessage=${debugInfo.debugMessage}
${if (validationInfos.isNullOrEmpty()) "" else "validationInfos=$validationInfos"}
$debugInfo
"""
}

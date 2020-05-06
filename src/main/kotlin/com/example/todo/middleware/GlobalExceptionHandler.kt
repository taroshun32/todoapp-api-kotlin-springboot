package com.example.todo.middleware

import com.example.todo.domain.model.ApiError
import com.example.todo.domain.model.exception.BadRequestException
import com.example.todo.util.RequestTrackingLogger
import org.openapitools.spring.models.ValidationInfo
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindingResult
import org.springframework.web.HttpMediaTypeNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import javax.servlet.http.HttpServletRequest

/**
 * Controllerにディスパッチされたあとで起きたエラーをハンドリングするクラス
 *
 */
@RestControllerAdvice
class GlobalExceptionHandler(
  val messageSource: MessageSource
) {

  companion object {
    private val log = RequestTrackingLogger(this)
  }

  /**========================40X系エラーハンドリング[LogLevel.INFO]========================*/

  // 400
  /**
   * バリデーションエラー
   */
  @ExceptionHandler(MethodArgumentNotValidException::class)
  fun handleMethodArgumentNotValidException(
    exception: MethodArgumentNotValidException,
    request: HttpServletRequest
  ): ResponseEntity<Any> = badRequestException(exception, request, exception.bindingResult)

  /**
   * リクエストボディのJSONが型に変換されなかったエラー
   */
  @ExceptionHandler(HttpMessageNotReadableException::class)
  fun handleHttpMessageNotReadableException(
    exception: HttpMessageNotReadableException,
    request: HttpServletRequest
  ): ResponseEntity<Any> = badRequestException(exception, request)

  /**
   * リクエストパラメータでのバリデーションエラー
   */
  @ExceptionHandler(MethodArgumentTypeMismatchException::class)
  fun handleMethodArgumentTypeMismatchException(
    exception: MethodArgumentTypeMismatchException,
    request: HttpServletRequest
  ): ResponseEntity<Any> = badRequestException(exception, request)

  /**
   * サポートしていないMediaTypeでリクエストがきた時のエラー
   */
  @ExceptionHandler(HttpMediaTypeNotSupportedException::class)
  fun handleHttpMediaTypeNotSupportedException(
    exception: HttpMediaTypeNotSupportedException,
    request: HttpServletRequest
  ): ResponseEntity<Any> = badRequestException(exception, request)

  /**
   * クエリパラメータでのバリデーションエラー
   */
  @ExceptionHandler(MissingServletRequestParameterException::class)
  fun handleMissingServletRequestParameterException(
    exception: MissingServletRequestParameterException,
    request: HttpServletRequest
  ): ResponseEntity<Any> = badRequestException(exception, request)

  private fun badRequestException(
    exception: Exception,
    request: HttpServletRequest,
    bindingResult: BindingResult? = null
  ): ResponseEntity<Any> {
    val validationInfos = bindingResult?.let {
      it.fieldErrors.map { fieldError ->
        ValidationInfo(
          field = fieldError.field,
          rejectedValue = fieldError.rejectedValue?.toString() ?: "rejectedValue is null",
          message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale())
        )
      }
    }
    return ApiError.of(request, BadRequestException(exception), validationInfos).also {
      log.info(it.toString(), exception)
    }.toDto()
  }

  /**
   * その他の入力エラー
   */
  @ExceptionHandler(BadRequestException::class)
  fun handleBadRequestException(
    exception: BadRequestException,
    request: HttpServletRequest
  ): ResponseEntity<Any> {
    return ApiError.of(request, exception).also {
      log.info(it.toString(), exception)
    }.toDto()
  }
}

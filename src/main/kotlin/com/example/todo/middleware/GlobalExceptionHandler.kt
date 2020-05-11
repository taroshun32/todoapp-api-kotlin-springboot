package com.example.todo.middleware

import com.example.todo.domain.model.api.ApiError
import com.example.todo.domain.model.exception.BadRequestException
import com.example.todo.domain.model.exception.DatabaseException
import com.example.todo.domain.model.exception.ForbiddenException
import com.example.todo.domain.model.exception.NotFoundException
import com.example.todo.domain.model.exception.UnAuthorizedException
import com.example.todo.domain.model.exception.UnHandledException
import com.example.todo.util.RequestTrackingLogger
import org.openapitools.spring.models.ValidationInfo
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindingResult
import org.springframework.web.HttpMediaTypeNotSupportedException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.servlet.NoHandlerFoundException
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

  // 401
  /**
   * 実行するロールが無いエラー
   */
  @ExceptionHandler(UnAuthorizedException::class)
  fun handleUnAuthorizedException(
    exception: UnAuthorizedException,
    request: HttpServletRequest
  ): ResponseEntity<Any> {
    return ApiError.of(request, exception).also {
      log.info(it.toString(), exception)
    }.toDto()
  }

  // 403
  /**
   *
   */
  @ExceptionHandler(ForbiddenException::class)
  fun handleForbiddenException(
    exception: ForbiddenException,
    request: HttpServletRequest
  ): ResponseEntity<Any> {
    return ApiError.of(request, exception).also {
      log.info(it.toString(), exception)
    }.toDto()
  }

  // 404
  /**
   * 存在しないAPIにアクセスされたエラー
   */
  @ExceptionHandler(NoHandlerFoundException::class)
  fun handleNoHandlerFoundException(
    exception: NoHandlerFoundException,
    request: HttpServletRequest
  ): ResponseEntity<Any> {
    return ApiError.of(request, NotFoundException(exception)).also {
      log.info(it.toString(), exception)
    }.toDto()
  }

  /**
   * サポートしていないメソッドでリクエストがきた時のエラー
   */
  @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
  fun handleHttpRequestMethodNotSupportedException(
    exception: HttpRequestMethodNotSupportedException,
    request: HttpServletRequest
  ): ResponseEntity<Any> {
    return ApiError.of(request, NotFoundException(exception)).also {
      log.info(it.toString(), exception)
    }.toDto()
  }

  /**
   * リソースが見つからなかった場合は、HTTPステータスコードに404を設定します。
   */
  @ExceptionHandler(NotFoundException::class)
  fun handleNotFoundException(
    exception: NotFoundException,
    request: HttpServletRequest
  ): ResponseEntity<Any> {
    return ApiError.of(request, exception).also {
      log.info(it.toString(), exception)
    }.toDto()
  }

  /**========================50X系エラーハンドリング[LogLevel.ERROR]========================*/

  /**
   * DB処理のエラー
   */
  @ExceptionHandler(DatabaseException::class)
  fun handleDatabaseException(
    exception: DatabaseException,
    request: HttpServletRequest
  ): ResponseEntity<Any> {
    return ApiError.of(request, exception).also {
      log.error(it.toString(), exception)
    }.toDto()
  }

  /**
   * 他のExceptionHandlerでとり逃したException
   */
  @ExceptionHandler(Exception::class)
  fun handleException(
    exception: Exception,
    request: HttpServletRequest
  ): ResponseEntity<Any> {
    return ApiError.of(request, UnHandledException(exception)).also {
      log.error(it.toString(), exception)
    }.toDto()
  }
}

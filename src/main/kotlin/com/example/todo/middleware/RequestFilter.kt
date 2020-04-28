package com.example.todo.middleware

import com.example.todo.util.RequestTrackingId
import com.example.todo.util.RequestTrackingLogger
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * リクエスト / レスポンス時に処理を付与するフィルタークラス
 */
@Component
@Order(value = 1)
class RequestFilter : Filter {
  companion object {
    private val log = RequestTrackingLogger(this)
  }

  /**
   * フィルターの処理
   *
   * @param request
   * @param response
   * @param chain
   * @return 共通処理後のレスポンス
   */
  override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
    // リクエストIDを発行
    RequestTrackingId.put()
    // リクエスト開始時間を記録
    val start = System.currentTimeMillis()
    if (request is HttpServletRequest) {
      log.info("[REQUEST START]${request.method} ${request.requestURI}")
      // リクエスト共通処理を記述
    } else {
      log.info("[REQUEST START] Can't get request information.")
    }

    chain.doFilter(request, response)

    val executionTime = System.currentTimeMillis() - start

    if (response is HttpServletResponse) {
      val status = response.status
      val method = if (request is HttpServletRequest) request.method else "NO_DATA"
      val path = if (request is HttpServletRequest) request.requestURI else "NO_DATA"
      log.info("[REQUEST END] $status : $method $path ${executionTime}ms")
    }
    // リクエストIDを破棄
    RequestTrackingId.remove()
  }
}

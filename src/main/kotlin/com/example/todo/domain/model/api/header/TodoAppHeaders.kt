package com.example.todo.domain.model.api.header

import org.springframework.http.HttpHeaders

data class TodoAppHeaders(
  val os: MobileOs,
  val appType: MobileApp,
  val authorization: Authorization
) {
  companion object {
    fun of(httpHeaders: HttpHeaders): TodoAppHeaders {
      return TodoAppHeaders(MobileOs.of(httpHeaders), MobileApp.of(httpHeaders), Authorization.of(httpHeaders))
    }

    fun of(
      xOsType: String,
      xAppVersion: String,
      authorization: String
    ): TodoAppHeaders {
      return TodoAppHeaders(MobileOs.of(xOsType), MobileApp.of(xAppVersion), Authorization.of(authorization))
    }
  }
}

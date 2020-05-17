package com.example.todo.domain.model.api.header

import org.springframework.http.HttpHeaders

class TodoAppNoAuthHeaders private constructor(
  val os: MobileOs,
  val appType: MobileApp
) {

  companion object {
    fun of(httpHeaders: HttpHeaders): TodoAppNoAuthHeaders {
      return TodoAppNoAuthHeaders(MobileOs.of(httpHeaders), MobileApp.of(httpHeaders))
    }

    fun of(
      xOsType: String,
      xAppVersion: String
    ): TodoAppNoAuthHeaders {
      return TodoAppNoAuthHeaders(MobileOs.of(xOsType), MobileApp.of(xAppVersion))
    }
  }
}

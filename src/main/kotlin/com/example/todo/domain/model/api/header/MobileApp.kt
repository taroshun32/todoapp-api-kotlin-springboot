package com.example.todo.domain.model.api.header

import com.example.todo.domain.model.exception.BadRequestException
import net.swiftzer.semver.SemVer
import org.springframework.http.HttpHeaders

data class MobileApp(
  val appVersion: SemVer
) {

  companion object {
    const val HEADER_KEY = "X-APP-VERSION"

    fun of(httpHeaders: HttpHeaders): MobileApp {
      return httpHeaders.getFirst(HEADER_KEY)?.let { of(it) }
        ?: throw BadRequestException("${HEADER_KEY}は必須項目です")
    }

    fun of(xAppVersion: String): MobileApp {
      val appVersion = SemVer.parse(xAppVersion)
      return MobileApp(appVersion)
    }
  }
}

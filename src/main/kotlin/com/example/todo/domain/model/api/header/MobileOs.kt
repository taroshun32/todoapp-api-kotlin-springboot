package com.example.todo.domain.model.api.header

import com.example.todo.domain.model.api.user.OsType
import com.example.todo.domain.model.exception.BadRequestException
import net.swiftzer.semver.SemVer
import org.springframework.http.HttpHeaders

data class MobileOs(
  val osType: OsType,
  val osVersion: SemVer
) {

  companion object {
    const val HEADER_KEY = "X-OS-TYPE"

    fun of(httpHeaders: HttpHeaders): MobileOs {
      return httpHeaders.getFirst(HEADER_KEY)?.let { of(it) }
        ?: throw BadRequestException("${HEADER_KEY}は必須項目です")
    }

    fun of(xOsType: String): MobileOs {
      val split = xOsType.split(" ")
      if (split.size != 2) {
        throw BadRequestException("不正な${HEADER_KEY}です", errorObject = xOsType)
      }

      val osType = split[0].let {
        try {
          OsType.valueOf(it)
        } catch (e: IllegalArgumentException) {
          throw BadRequestException("不正なOSです", errorObject = it)
        }
      }

      val osVersion = SemVer.parse(split[1])

      return MobileOs(osType, osVersion)
    }
  }
}

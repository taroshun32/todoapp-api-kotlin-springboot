package com.example.todo.domain.model.api.header

import com.example.todo.domain.model.api.token.AccessToken
import com.example.todo.domain.model.exception.BadRequestException
import org.springframework.http.HttpHeaders

data class Authorization(
  val accessToken: AccessToken
) {
  companion object {
    private const val BEARER = "Bearer "

    fun of(httpHeaders: HttpHeaders): Authorization {
      return Authorization(validateToken(httpHeaders.getFirst(HttpHeaders.AUTHORIZATION)))
    }

    fun of(authorization: String): Authorization {
      return Authorization(validateToken(authorization))
    }

    private fun validateToken(token: String?): AccessToken {
      if (token.isNullOrBlank()) {
        throw BadRequestException("${HttpHeaders.AUTHORIZATION}は必須項目です")
      }

      if (!token.contains(BEARER)) {
        throw BadRequestException("不正なトークン形式です", errorObject = token)
      }

      return try {
        AccessToken(token.replace(BEARER, ""))
      } catch (e: IllegalArgumentException) {
        throw BadRequestException(e)
      }
    }
  }
}

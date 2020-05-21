package com.example.todo.domain.model.api.token

import com.example.todo.domain.model.api.user.UserId
import com.example.todo.infrastructure.record.TokenRecord
import com.example.todo.util.dddsupport.Entity
import java.time.LocalDateTime

class Token(
  id: TokenId,
  val userId: UserId,
  val accessToken: AccessToken,
  val accessExpiresIn: AccessExpiresIn,
  val refreshToken: RefreshToken,
  val refreshExpiresIn: RefreshExpiresIn
) : Entity<TokenId>(id) {
  companion object {
    private const val ACCESS_EXPIRES_IN_SECONDS = 604800L
    private const val TOKEN_TYPE = "Bearer"

    fun of(
      userId: UserId
    ): Token {
      return Token(
        id = TokenId.create(),
        userId = userId,
        accessToken = AccessToken.create(),
        accessExpiresIn = AccessExpiresIn.create(),
        refreshToken = RefreshToken.create(),
        refreshExpiresIn = RefreshExpiresIn.create()
      )
    }
  }

  val toRecord: TokenRecord
    get() {
      val now = LocalDateTime.now()
      return TokenRecord(
        id = id,
        userId = userId,
        accessToken = accessToken,
        accessExpiresIn = accessExpiresIn,
        refreshToken = refreshToken,
        refreshExpiresIn = refreshExpiresIn,
        createdAt = now,
        updatedAt = now,
        version = 1
      )
    }

  val dto: org.openapitools.spring.models.Token
    get() {
      return org.openapitools.spring.models.Token(
        tokenType = TOKEN_TYPE,
        accessToken = accessToken.value,
        refreshToken = refreshToken.value,
        expiresIn = ACCESS_EXPIRES_IN_SECONDS
      )
    }
}

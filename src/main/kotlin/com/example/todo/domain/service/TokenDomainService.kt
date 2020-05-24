package com.example.todo.domain.service

import com.example.todo.application.repository.TokenRepository
import com.example.todo.domain.model.api.header.TodoAppHeaders
import com.example.todo.domain.model.api.token.AccessToken
import com.example.todo.domain.model.api.token.RefreshToken
import com.example.todo.domain.model.api.token.Token
import com.example.todo.domain.model.api.user.UserId
import com.example.todo.domain.model.exception.DatabaseException
import com.example.todo.domain.model.exception.UnAuthorizedException
import com.example.todo.infrastructure.record.TokenRecord
import org.openapitools.spring.models.TokenRefreshPostParameter
import org.springframework.stereotype.Service

@Service
class TokenDomainService(
  private val tokenRepository: TokenRepository
) {

  fun updateToken(userId: UserId): Token {
    return tokenRepository.findByUserId(userId)?.let {
      tokenRepository.update(it.update()).toDomain
    } ?: throw DatabaseException("ユーザに紐づくトークンが存在しません")
  }

  fun validateToken(accessToken: AccessToken): Token {
    val token = findByAccessToken(accessToken).toDomain
    if (token.accessExpiresIn.isExpire()) {
      throw UnAuthorizedException("トークンの有効期限が切れています")
    }
    return token
  }

  fun validateRefreshToken(tokenRefreshParam: TokenRefreshPostParameter): TokenRecord {
    val tokenTable = tokenRepository.findByRefreshToken(RefreshToken(tokenRefreshParam.refreshToken))
      ?: throw UnAuthorizedException("不正なリフレッシュトークンです")
    if (tokenTable.toDomain.refreshExpiresIn.isExpire()) {
      throw UnAuthorizedException("リフレッシュトークンの有効期限が切れています")
    }
    return tokenTable
  }

  fun logout(accessToken: AccessToken) {
    val tokenTable = findByAccessToken(accessToken)
    tokenRepository.update(tokenTable.logout())
  }

  fun findByAccessToken(accessToken: AccessToken): TokenRecord {
    return tokenRepository.findByAccessToken(accessToken) ?: throw UnAuthorizedException("不正なトークンです")
  }
}

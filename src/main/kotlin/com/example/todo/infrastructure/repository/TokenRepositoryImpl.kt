package com.example.todo.infrastructure.repository

import com.example.todo.application.repository.TokenRepository
import com.example.todo.domain.model.api.token.AccessToken
import com.example.todo.domain.model.api.token.RefreshToken
import com.example.todo.domain.model.api.user.UserId
import com.example.todo.infrastructure.dao.TokenDao
import com.example.todo.infrastructure.record.TokenRecord
import org.springframework.stereotype.Repository

@Repository
class TokenRepositoryImpl(
  private val dao: TokenDao
) : TokenRepository {

  override fun findByUserId(userId: UserId): TokenRecord? = dao.selectByUserId(userId)

  override fun findByAccessToken(accessToken: AccessToken): TokenRecord? = dao.selectByAccessToken(accessToken)

  override fun findByRefreshToken(refreshToken: RefreshToken): TokenRecord? = dao.selectByRefreshToken(refreshToken)

  override fun store(tokenRecord: TokenRecord): TokenRecord = dao.store(tokenRecord).entity

  override fun update(tokenRecord: TokenRecord): TokenRecord = dao.update(tokenRecord).entity
}

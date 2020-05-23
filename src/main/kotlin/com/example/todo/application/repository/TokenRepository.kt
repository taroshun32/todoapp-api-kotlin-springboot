package com.example.todo.application.repository

import com.example.todo.domain.model.api.user.UserId
import com.example.todo.infrastructure.record.TokenRecord

interface TokenRepository {
  fun findByUserId(userId: UserId): TokenRecord?
  fun store(tokenRecord: TokenRecord): TokenRecord
  fun update(tokenRecord: TokenRecord): TokenRecord
}

package com.example.todo.infrastructure.repository

import com.example.todo.application.repository.TokenRepository
import com.example.todo.infrastructure.dao.TokenDao
import com.example.todo.infrastructure.record.TokenRecord
import org.springframework.stereotype.Repository

@Repository
class TokenRepositoryImpl(
  private val dao: TokenDao
) : TokenRepository {

  override fun store(
    tokenRecord: TokenRecord
  ): TokenRecord = dao.store(tokenRecord).entity
}

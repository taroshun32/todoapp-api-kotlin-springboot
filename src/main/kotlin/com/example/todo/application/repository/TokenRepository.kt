package com.example.todo.application.repository

import com.example.todo.infrastructure.record.TokenRecord

interface TokenRepository {
  fun store(tokenRecord: TokenRecord): TokenRecord
}

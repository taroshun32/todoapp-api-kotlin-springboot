package com.example.todo.application.repository

import com.example.todo.infrastructure.record.UserRecord

interface UserRepository {
  fun findByName(name: String): UserRecord?
  fun store(userRecord: UserRecord): UserRecord
}

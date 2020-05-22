package com.example.todo.application.repository

import com.example.todo.infrastructure.record.UserRecord

interface UserRepository {
  fun store(userRecord: UserRecord): UserRecord
}

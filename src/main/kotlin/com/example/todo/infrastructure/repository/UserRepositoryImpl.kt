package com.example.todo.infrastructure.repository

import com.example.todo.application.repository.UserRepository
import com.example.todo.infrastructure.dao.UserDao
import com.example.todo.infrastructure.record.UserRecord
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(
  private val dao: UserDao
) : UserRepository {

  override fun findByName(name: String): UserRecord? = dao.selectByName(name)

  override fun store(userRecord: UserRecord): UserRecord = dao.insert(userRecord).entity
}

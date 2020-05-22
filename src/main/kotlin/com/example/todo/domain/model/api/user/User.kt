package com.example.todo.domain.model.api.user

import com.example.todo.domain.model.api.header.TodoAppNoAuthHeaders
import com.example.todo.infrastructure.record.UserRecord
import com.example.todo.util.dddsupport.Entity
import org.openapitools.spring.models.AuthPostParameter
import java.time.LocalDateTime

class User(
  id: UserId,
  val name: String,
  val password: String,
  val osType: OsType
) : Entity<UserId>(id) {
  companion object {
    fun of(
      header: TodoAppNoAuthHeaders,
      authParam: AuthPostParameter
    ): User {
      return User(
        id = UserId.create(),
        name = authParam.userName,
        password = authParam.password,
        osType = header.os.osType
      )
    }
  }

  val toRecord: UserRecord
    get() {
      val now = LocalDateTime.now()
      return UserRecord(
        id = id,
        name = name,
        password = password,
        osType = osType,
        createdAt = now,
        updatedAt = now,
        version = 1
      )
    }
}

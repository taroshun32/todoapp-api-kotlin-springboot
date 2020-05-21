package com.example.todo.domain.model.api.user

import com.example.todo.util.dddsupport.IdGenerator
import com.example.todo.util.dddsupport.Identifier
import org.seasar.doma.Domain

@Domain(valueType = String::class)
class UserId(
  value: String
) : Identifier<String>(value) {
  companion object {
    fun create() = UserId(IdGenerator.generate())
  }
}

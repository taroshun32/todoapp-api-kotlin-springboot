package com.example.todo.domain.model.api.token

import com.example.todo.util.dddsupport.IdGenerator
import com.example.todo.util.dddsupport.Identifier
import org.seasar.doma.Domain

@Domain(valueType = String::class)
class TokenId(
  value: String
) : Identifier<String>(value) {
  companion object {
    fun create() = TokenId(IdGenerator.generate())
  }
}

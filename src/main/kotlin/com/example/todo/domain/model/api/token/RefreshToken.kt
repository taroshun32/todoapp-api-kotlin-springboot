package com.example.todo.domain.model.api.token

import com.example.todo.util.dddsupport.Identifier
import org.seasar.doma.Domain
import java.util.UUID

@Domain(valueType = String::class)
class RefreshToken(
  value: String
) : Identifier<String>(value) {

  companion object {
    private const val TOKEN_LENGTH = 36

    fun create() = RefreshToken(UUID.randomUUID().toString())
  }

  init {
    if (value.length != TOKEN_LENGTH) {
      throw IllegalArgumentException("アクセストークンの形式が不正です")
    }
  }
}

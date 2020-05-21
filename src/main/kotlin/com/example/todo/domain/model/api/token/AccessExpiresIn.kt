package com.example.todo.domain.model.api.token

import org.seasar.doma.Domain
import java.time.LocalDateTime

@Domain(valueType = LocalDateTime::class)
data class AccessExpiresIn(
  val value: LocalDateTime
) {
  companion object {
    private const val AVAILABLE_DAYS = 7L

    fun create() = AccessExpiresIn(LocalDateTime.now().plusDays(AVAILABLE_DAYS))
    fun revoke() = AccessExpiresIn(LocalDateTime.now())
  }

  fun isExpire(): Boolean = LocalDateTime.now().isAfter(value)
}

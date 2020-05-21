package com.example.todo.domain.model.api.token

import org.seasar.doma.Domain
import java.time.LocalDateTime

@Domain(valueType = LocalDateTime::class)
data class RefreshExpiresIn(
  val value: LocalDateTime
) {
  companion object {
    private const val AVAILABLE_DAYS = 90L

    fun create() = RefreshExpiresIn(LocalDateTime.now().plusDays(AVAILABLE_DAYS))
    fun revoke() = RefreshExpiresIn(LocalDateTime.now())
  }

  fun isExpire(): Boolean = LocalDateTime.now().isAfter(value)
}

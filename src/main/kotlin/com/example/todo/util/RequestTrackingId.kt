package com.example.todo.util

import org.slf4j.MDC
import java.util.UUID

object RequestTrackingId {
  private const val KEY = "requestId"

  fun get(): String = MDC.get(KEY) ?: "can't get request id"

  fun getNullable(): String? = MDC.get(KEY)

  fun put() {
    MDC.put(KEY, UUID.randomUUID().toString())
  }

  fun remove() {
    MDC.remove(KEY)
  }
}

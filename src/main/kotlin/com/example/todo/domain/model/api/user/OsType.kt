package com.example.todo.domain.model.api.user

import org.seasar.doma.Domain

/**
 * スマホOS種別
 */
@Domain(valueType = String::class)
enum class OsType {
  IOS,
  ANDROID
}

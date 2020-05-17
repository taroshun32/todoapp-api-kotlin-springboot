package com.example.todo.domain.model.api.user

import org.seasar.doma.Domain

/**
 * スマホOS種別
 */
@Domain(valueType = String::class, accessorMethod = "name", factoryMethod = "valueOf")
enum class OsType {
  IOS,
  ANDROID
}

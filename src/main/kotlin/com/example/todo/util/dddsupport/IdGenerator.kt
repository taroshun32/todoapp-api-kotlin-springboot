package com.example.todo.util.dddsupport

import de.huxhorn.sulky.ulid.ULID

/**
 * IDの生成を行うクラス
 */
object IdGenerator {
  private val ulid: ULID = ULID()
  fun generate(): String = ulid.nextULID()
}

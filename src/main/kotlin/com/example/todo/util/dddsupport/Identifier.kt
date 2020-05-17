package com.example.todo.util.dddsupport

/**
 * 釘宮さん本14p参考
 * https://booth.pm/ja/items/837226
 */
abstract class Identifier<T>(val value: T) {

  override fun equals(other: Any?): Boolean {
    /**
     * === はメモリの参照先が同じかどうか
     * ==  はequalsメソッドの糖衣構文
     */
    if (this === other) return true
    if (other !is Identifier<*>) return false

    return value == other.value
  }

  override fun hashCode(): Int = value.hashCode()
}

package com.example.todo.util.dddsupport

/**
 * 釘宮さん本15p参考
 * https://booth.pm/ja/items/837226
 *
 * 同一性の担保
 * [Identifier]型を継承したidをメンバにもっている
 * equalsメソッドでは[Identifier]のidが等しいかどうかをみる
 */
abstract class Entity<T : Identifier<*>>(val id: T) {

  override fun equals(other: Any?): Boolean = (other is Entity<*>) && other.id == id

  override fun hashCode(): Int = id.hashCode()
}

package com.example.todo.infrastructure.record

import com.example.todo.util.dddsupport.Identifier
import org.seasar.doma.Entity
import java.time.LocalDateTime

/**
 * テーブルの共通カラム
 *
 * @property id
 * @property createdAt
 * @property updatedAt
 * @property version
 */
@Entity(immutable = true)
abstract class Common {

  abstract val id: Identifier<String> // DBのPrimaryKeyにULIDを使用しているため、Stringに決め打ち

  abstract val createdAt: LocalDateTime

  abstract val updatedAt: LocalDateTime

  abstract val version: Long
}

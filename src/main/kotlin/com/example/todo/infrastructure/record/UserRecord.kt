package com.example.todo.infrastructure.record

import com.example.todo.domain.model.api.user.OsType
import com.example.todo.domain.model.api.user.UserId
import org.seasar.doma.Column
import org.seasar.doma.Entity
import org.seasar.doma.Id
import org.seasar.doma.Table
import org.seasar.doma.Version
import java.time.LocalDateTime

/**
 * usersテーブルへのマッピングオブジェクト
 */
@Entity(immutable = true)
@Table(name = "users")
data class UserRecord(

  @Id
  @Column(name = "id")
  override val id: UserId,

  @Column(name = "name")
  val name: String,

  @Column(name = "password")
  val password: String,

  @Column(name = "os_type")
  val osType: OsType,

  @Column(name = "created_at")
  override val createdAt: LocalDateTime,

  @Column(name = "updated_at")
  override val updatedAt: LocalDateTime,

  @Version
  @Column(name = "version")
  override val version: Long
) : Common() {

}

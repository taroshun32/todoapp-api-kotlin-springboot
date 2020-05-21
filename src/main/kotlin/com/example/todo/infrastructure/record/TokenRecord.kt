package com.example.todo.infrastructure.record

import com.example.todo.domain.model.api.token.AccessExpiresIn
import com.example.todo.domain.model.api.token.AccessToken
import com.example.todo.domain.model.api.token.RefreshExpiresIn
import com.example.todo.domain.model.api.token.RefreshToken
import com.example.todo.domain.model.api.token.Token
import com.example.todo.domain.model.api.token.TokenId
import com.example.todo.domain.model.api.user.UserId
import org.seasar.doma.Column
import org.seasar.doma.Entity
import org.seasar.doma.Id
import org.seasar.doma.Table
import org.seasar.doma.Version
import java.time.LocalDateTime

/**
 * tokensテーブルへのマッピングオブジェクト
 */
@Entity(immutable = true)
@Table(name = "tokens")
data class TokenRecord(

  @Id
  @Column(name = "id")
  override val id: TokenId,

  @Column(name = "user_id")
  val userId: UserId,

  @Column(name = "access_token")
  val accessToken: AccessToken,

  @Column(name = "access_expires_in")
  val accessExpiresIn: AccessExpiresIn,

  @Column(name = "refresh_token")
  val refreshToken: RefreshToken,

  @Column(name = "refresh_expires_in")
  val refreshExpiresIn: RefreshExpiresIn,

  @Column(name = "created_at")
  override val createdAt: LocalDateTime,

  @Column(name = "updated_at")
  override val updatedAt: LocalDateTime,

  @Column(name = "version")
  @Version
  override val version: Long
) : Common() {

  fun update(): TokenRecord {
    return copy(
      accessToken = AccessToken.create(),
      accessExpiresIn = AccessExpiresIn.create(),
      refreshToken = RefreshToken.create(),
      refreshExpiresIn = RefreshExpiresIn.create(),
      updatedAt = LocalDateTime.now()
    )
  }

  fun logout(): TokenRecord {
    return copy(
      accessExpiresIn = AccessExpiresIn.revoke(),
      refreshExpiresIn = RefreshExpiresIn.revoke(),
      updatedAt = LocalDateTime.now()
    )
  }

  val toDomain: Token
    get() = Token(
      id = id,
      userId = userId,
      accessToken = accessToken,
      accessExpiresIn = accessExpiresIn,
      refreshToken = refreshToken,
      refreshExpiresIn = refreshExpiresIn
    )
}

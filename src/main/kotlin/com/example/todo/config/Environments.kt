package com.example.todo.config

import com.example.todo.domain.model.exception.IllegalConfigException
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

/**
 * application.ymlから設定値を取得するクラス
 * environments以下の項目を取得する
 */
@ConstructorBinding
@ConfigurationProperties(prefix = "environments")
data class Environments(
  /** 環境プロファイル */
  val profile: String
) {

  /** 各環境ごとのプロファイル名 */
  companion object {
    private const val PROFILE_PRD = "prd"
    private const val PROFILE_STG = "stg"
    private const val PROFILE_DEV = "dev"
    private const val PROFILE_LOCAL = "local"
  }

  enum class Profile {
    PRD, STG, DEV, LOCAL;
  }

  fun profile(): Profile {
    return when (profile) {
      PROFILE_PRD -> Profile.PRD
      PROFILE_STG -> Profile.STG
      PROFILE_DEV -> Profile.DEV
      PROFILE_LOCAL -> Profile.LOCAL
      else -> throw IllegalConfigException("想定外のprofile変数が設定されています $profile")
    }
  }

  /**
   * 本番環境かどうかを判断するメソッド
   */
  val isPrd
    get() = profile == PROFILE_PRD

  val isStg
    get() = profile == PROFILE_STG

  val isDev
    get() = profile == PROFILE_DEV

  val isLocal
    get() = profile == PROFILE_LOCAL
}

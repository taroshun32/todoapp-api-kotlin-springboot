package com.example.todo.domain.model.exception

/**
 * アプリに返すエラーメッセージのデフォルト文言
 *
 */
object ExceptionFrontMessages {
  const val BAD_REQUEST = "入力内容に誤りがあります"
  const val UNAUTHORIZED = "期限切れか、正しくないトークンです"
  const val NOT_FOUND = "指定された情報は見つかりませんでした"
  const val REQUEST_TIMEOUT = "タイムアウトが発生しました"
  const val INTERNAL_SERVER_ERROR = "サーバーエラーが発生しました"
}

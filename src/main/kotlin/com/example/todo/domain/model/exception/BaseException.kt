package com.example.todo.domain.model.exception

import org.springframework.http.HttpStatus

/**
 * 例外発生時にフロントに返す文言と調査用にログに残したい情報を持たせる基底例外クラス
 *
 * frontMessage フロントに返すエラー文言、例外発生箇所で[ExceptionFrontMessages]にない文言も設定可能
 * errorObject 外部APIのエラー用レスポンスのようなログにだす有用な情報を持つオブジェクトがあれば例外スロー時に持たせる
 */
abstract class BaseException : RuntimeException {

  val status: HttpStatus
  val frontMessage: String
  val errorObject: Any?

  constructor(
    status: HttpStatus,
    debugMessage: String,
    frontMessage: String,
    errorObject: Any? = null
  ) : super(debugMessage) {
    this.status = status
    this.frontMessage = frontMessage
    this.errorObject = errorObject
  }

  constructor(
    status: HttpStatus,
    cause: Throwable,
    debugMessage: String,
    frontMessage: String,
    errorObject: Any? = null
  ) : super(debugMessage, cause) {
    this.status = status
    this.frontMessage = frontMessage
    this.errorObject = errorObject
  }
}

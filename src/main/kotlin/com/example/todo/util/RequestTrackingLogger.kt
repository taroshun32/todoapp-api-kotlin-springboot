package com.example.todo.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.slf4j.Marker

/**
 * Kotlinだと@Slf4jが使えないので、Logger生成のボイラープレートを楽にするクラス
 *
 * 参考URL
 * https://qiita.com/mumei_himazin/items/df94b3bc9c6112a4125e
 * https://discuss.kotlinlang.org/t/best-practices-for-loggers/226
 * https://saiya-moebius.hatenablog.com/entry/2017/11/08/033932
 * https://www.reddit.com/r/Kotlin/comments/8gbiul/slf4j_loggers_in_3_ways/
 */
class RequestTrackingLogger(any: Any) : Logger {

  private val log: Logger = LoggerFactory.getLogger(
    // Companion Objectを継承していた場合は「$Companion」を除外する
    any.javaClass.name.replace("\$Companion", "")
  )

  override fun warn(msg: String?) {
    log.warn("[${RequestTrackingId.get()}] $msg")
  }

  override fun warn(format: String?, arg: Any?) {
    log.warn("[${RequestTrackingId.get()}] $format", arg)
  }

  override fun warn(format: String?, vararg arguments: Any?) {
    log.warn("[${RequestTrackingId.get()}] $format", arguments)
  }

  override fun warn(format: String?, arg1: Any?, arg2: Any?) {
    log.warn("[${RequestTrackingId.get()}] $format", arg1, arg2)
  }

  override fun warn(msg: String?, t: Throwable?) {
    log.warn("[${RequestTrackingId.get()}] $msg", t)
  }

  override fun warn(marker: Marker?, msg: String?) {
    log.warn(marker, "[${RequestTrackingId.get()}] $msg")
  }

  override fun warn(marker: Marker?, format: String?, arg: Any?) {
    log.warn(marker, "[${RequestTrackingId.get()}] $format", arg)
  }

  override fun warn(marker: Marker?, format: String?, arg1: Any?, arg2: Any?) {
    log.warn(marker, "[${RequestTrackingId.get()}] $format", arg1, arg2)
  }

  override fun warn(marker: Marker?, format: String?, vararg arguments: Any?) {
    log.warn(marker, "[${RequestTrackingId.get()}] $format", arguments)
  }

  override fun warn(marker: Marker?, msg: String?, t: Throwable?) {
    log.warn(marker, "[${RequestTrackingId.get()}] $msg", t)
  }

  override fun getName(): String = log.name

  override fun info(msg: String?) {
    log.info("[${RequestTrackingId.get()}] $msg")
  }

  override fun info(format: String?, arg: Any?) {
    log.info("[${RequestTrackingId.get()}] $format", arg)
  }

  override fun info(format: String?, vararg arguments: Any?) {
    log.info("[${RequestTrackingId.get()}] $format", arguments)
  }

  override fun info(format: String?, arg1: Any?, arg2: Any?) {
    log.info("[${RequestTrackingId.get()}] $format", arg1, arg2)
  }

  override fun info(msg: String?, t: Throwable?) {
    log.info("[${RequestTrackingId.get()}] $msg", t)
  }

  override fun info(marker: Marker?, msg: String?) {
    log.info(marker, "[${RequestTrackingId.get()}] $msg")
  }

  override fun info(marker: Marker?, format: String?, arg: Any?) {
    log.info(marker, "[${RequestTrackingId.get()}] $format", arg)
  }

  override fun info(marker: Marker?, format: String?, arg1: Any?, arg2: Any?) {
    log.info(marker, "[${RequestTrackingId.get()}] $format", arg1, arg2)
  }

  override fun info(marker: Marker?, format: String?, vararg arguments: Any?) {
    log.info(marker, "[${RequestTrackingId.get()}] $format", arguments)
  }

  override fun info(marker: Marker?, msg: String?, t: Throwable?) {
    log.info(marker, "[${RequestTrackingId.get()}] $msg", t)
  }

  override fun isErrorEnabled(): Boolean = log.isErrorEnabled

  override fun isErrorEnabled(marker: Marker?): Boolean = isErrorEnabled(marker)

  override fun error(msg: String?) {
    log.error("$msg　[${RequestTrackingId.get()}] ")
  }

  override fun error(format: String?, arg: Any?) {
    log.error("[${RequestTrackingId.get()}] $format", arg)
  }

  override fun error(format: String?, vararg arguments: Any?) {
    log.error("[${RequestTrackingId.get()}] $format", arguments)
  }

  override fun error(format: String?, arg1: Any?, arg2: Any?) {
    log.error("[${RequestTrackingId.get()}] $format", arg1, arg2)
  }

  override fun error(msg: String?, t: Throwable?) {
    log.error("[${RequestTrackingId.get()}] $msg", t)
  }

  override fun error(marker: Marker?, msg: String?) {
    log.error(marker, "[${RequestTrackingId.get()}] $msg")
  }

  override fun error(marker: Marker?, format: String?, arg: Any?) {
    log.error(marker, "[${RequestTrackingId.get()}] $format", arg)
  }

  override fun error(marker: Marker?, format: String?, arg1: Any?, arg2: Any?) {
    log.error(marker, "[${RequestTrackingId.get()}] $format", arg1, arg2)
  }

  override fun error(marker: Marker?, format: String?, vararg arguments: Any?) {
    log.error(marker, "[${RequestTrackingId.get()}] $format", arguments)
  }

  override fun error(marker: Marker?, msg: String?, t: Throwable?) {
    log.error(marker, "[${RequestTrackingId.get()}] $msg", t)
  }

  override fun isDebugEnabled(): Boolean = isDebugEnabled

  override fun isDebugEnabled(marker: Marker?): Boolean = isDebugEnabled(marker)

  override fun debug(msg: String?) {
    log.debug("[${RequestTrackingId.get()}] $msg")
  }

  override fun debug(format: String?, arg: Any?) {
    log.debug("[${RequestTrackingId.get()}] $format", arg)
  }

  override fun debug(format: String?, vararg arguments: Any?) {
    log.debug("[${RequestTrackingId.get()}] $format", arguments)
  }

  override fun debug(format: String?, arg1: Any?, arg2: Any?) {
    log.debug("[${RequestTrackingId.get()}] $format", arg1, arg2)
  }

  override fun debug(msg: String?, t: Throwable?) {
    log.debug("[${RequestTrackingId.get()}] $msg", t)
  }

  override fun debug(marker: Marker?, msg: String?) {
    log.debug(marker, "[${RequestTrackingId.get()}] $msg")
  }

  override fun debug(marker: Marker?, format: String?, arg: Any?) {
    log.debug(marker, "[${RequestTrackingId.get()}] $format", arg)
  }

  override fun debug(marker: Marker?, format: String?, arg1: Any?, arg2: Any?) {
    log.debug(marker, "[${RequestTrackingId.get()}] $format", arg1, arg2)
  }

  override fun debug(marker: Marker?, format: String?, vararg arguments: Any?) {
    log.debug(marker, "[${RequestTrackingId.get()}] $format", arguments)
  }

  override fun debug(marker: Marker?, msg: String?, t: Throwable?) {
    log.debug(marker, "[${RequestTrackingId.get()}] $msg", t)
  }

  override fun isInfoEnabled(): Boolean = log.isInfoEnabled

  override fun isInfoEnabled(marker: Marker?): Boolean = log.isInfoEnabled(marker)

  override fun trace(msg: String?) {
    log.trace("[${RequestTrackingId.get()}] $msg")
  }

  override fun trace(format: String?, arg: Any?) {
    log.trace("[${RequestTrackingId.get()}] $format", arg)
  }

  override fun trace(format: String?, vararg arguments: Any?) {
    log.trace("[${RequestTrackingId.get()}] $format", arguments)
  }

  override fun trace(format: String?, arg1: Any?, arg2: Any?) {
    log.trace("[${RequestTrackingId.get()}] $format", arg1, arg2)
  }

  override fun trace(msg: String?, t: Throwable?) {
    log.trace("[${RequestTrackingId.get()}] $msg", t)
  }

  override fun trace(marker: Marker?, msg: String?) {
    log.trace(marker, "[${RequestTrackingId.get()}] $msg")
  }

  override fun trace(marker: Marker?, format: String?, arg: Any?) {
    log.trace(marker, "[${RequestTrackingId.get()}] $format", arg)
  }

  override fun trace(marker: Marker?, format: String?, arg1: Any?, arg2: Any?) {
    log.trace(marker, "[${RequestTrackingId.get()}] $format", arg1, arg2)
  }

  override fun trace(marker: Marker?, format: String?, vararg arguments: Any?) {
    log.trace(marker, "[${RequestTrackingId.get()}] $format", arguments)
  }

  override fun trace(marker: Marker?, msg: String?, t: Throwable?) {
    log.trace(marker, "[${RequestTrackingId.get()}] $msg", t)
  }

  override fun isWarnEnabled(): Boolean = log.isWarnEnabled

  override fun isWarnEnabled(marker: Marker?): Boolean = log.isWarnEnabled(marker)

  override fun isTraceEnabled(): Boolean = log.isTraceEnabled

  override fun isTraceEnabled(marker: Marker?): Boolean = log.isTraceEnabled(marker)
}

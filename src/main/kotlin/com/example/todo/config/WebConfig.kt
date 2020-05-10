package com.example.todo.config

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.OffsetDateTimeSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.http.codec.json.Jackson2JsonDecoder
import org.springframework.http.codec.json.Jackson2JsonEncoder
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

/**
 * サーバに関する設定を行うクラス
 */
@Configuration
class WebConfig {

  companion object {
    /** ISO8601形式のフォーマット文字列 */
    private const val ISO8601_DATETIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ssXXX"
  }

  /**
   * Jsonのエンコーダとデコーダを登録
   *
   * @return カスタムしたコーデックコンフィグ
   */
  @Bean
  fun serverCodecConfigurer(): ServerCodecConfigurer =
    ServerCodecConfigurer.create().apply {
      defaultCodecs().jackson2JsonEncoder(Jackson2JsonEncoder(provideConverter().objectMapper))
      defaultCodecs().jackson2JsonDecoder(Jackson2JsonDecoder(provideConverter().objectMapper))
    }

  /**
   * Jsonのエンコーダ/デコーダをカスタム
   *
   * @return カスタムしたコンバータ
   */
  private fun provideConverter(): MappingJackson2HttpMessageConverter =
    MappingJackson2HttpMessageConverter(provideObjectMapper())

  /**
   * ObjectMapperをBean登録する
   *
   * @return カスタム済みのObjectMapper
   */
  @Bean
  fun provideObjectMapper(): ObjectMapper =
    ObjectMapper()

      // ISO8601形式を実現するためのOffsetDateTimeをシリアライズ・デシリアライズできるように設定
      // https://stackoverflow.com/questions/49070155/use-jackson-deserialization-for-datetime-in-spring-rest-controller
      .registerModule(JavaTimeModule().apply {
        addSerializer(
          OffsetDateTime::class.java,
          OffsetDateTimeSerializerFormatted()
        )
        addDeserializer(OffsetDateTime::class.java, InstantDeserializer.OFFSET_DATE_TIME)
      })
      .apply {
        enable(SerializationFeature.INDENT_OUTPUT)

        // シリアライズ時に、OffsetDateTimeをUNIX時間に変換させないよう設定
        // https://fasterxml.github.io/jackson-databind/javadoc/2.0.0/com/fasterxml/jackson/databind/SerializationFeature.html#WRITE_DATES_AS_TIMESTAMPS
        disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

        // デシリアライズ時に、OffsetDateTimeのタイムゾーンをUTCに変換させないよう設定
        // https://fasterxml.github.io/jackson-databind/javadoc/2.6/com/fasterxml/jackson/databind/DeserializationFeature.html#ADJUST_DATES_TO_CONTEXT_TIME_ZONE
        disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)

        // Booleanのisプリフィックス問題
        // https://mikan.github.io/2018/08/24/mysterious-behavior-caused-by-the-combination-of-jackson-and-kotlin/
        // プロパティの命名だけをみてJsonにするように設定
        // https://stackoverflow.com/questions/7105745/how-to-specify-jackson-to-only-use-fields-preferably-globally
        setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE)
        setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)

        // NonNullのプリミティブ型がnullの時、勝手に値が入るのを防いでエラーを吐く
        // https://fasterxml.github.io/jackson-databind/javadoc/2.8/com/fasterxml/jackson/databind/DeserializationFeature.html#FAIL_ON_NULL_FOR_PRIMITIVES
        enable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)

        // レスポンスでnullの要素は表示しないようにする
        // http://s-edword.hatenablog.com/entry/2016/07/26/231130
        setSerializationInclusion(JsonInclude.Include.NON_NULL)
      }

  /**
   * ISO8601形式でOffsetDateTimeをシリアライズ・デシリアライズするためのフォーマッター
   *
   */
  private class OffsetDateTimeSerializerFormatted : OffsetDateTimeSerializer(
    INSTANCE,
    false,
    DateTimeFormatter.ofPattern(ISO8601_DATETIME_PATTERN)
  )
}

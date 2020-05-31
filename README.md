# todoapp-api-kotlin-springboot
kotlin×springboot タスク管理アプリ用API

## プロジェクトについて
このプロジェクトでは `GithubActions` を `CI` のメインツールとして使用しています。[ [各Actionについて ](https://github.com/taroshun32/todoapp-api-kotlin-springboot/wiki/GithubActionsについて)]  
API定義には `OpenAPI(Swagger)` を用いています。  
`Controller` と `Model` を自動生成したものをサブモジュールとして取り込み、Gradleでサブプロジェクト化しています。

## 開発環境
| 項目 | バージョン | 用途 |
|:-----------:|:-----------:|:-----------:|
| Kotlin | 1.3 | 言語 |
| AdoptOpenJDK | 11 | JDK |
| Gradle | 6.3 | ビルド |
| Sprint Boot | 2.2.6 | フレームワーク |
| MySQL | 5.7 | データベース |
| Doma | 2.19.3 | ORマッパー |
| Flyway | 5.2.3 | マイグレーション |

## プロジェクト構成
<pre>
root
  ├ .docker : Dockerのローカル開発用の設定
  ├ .github : GithubActions定義
  ├ gradle : Gradle実行バイナリ
  ├ schema : DB定義(SchemaSpyにより生成)
  ├ scripts: スクリプトファイル
  ├ src: アプリケーション
  ├ todoapp-openapi: API定義(submodule)
  ├ .editorconfig : コーディングスタイル定義
  ├ .gitmodules : サブモジュールの設定
  ├ detekt-config.yml : detektのルール定義
  ├ Dangerfile : Dangerのタスク定義
  ├ Dockerfile : Dockerイメージの定義
  ├ docker-compose.yml : ローカル開発用の複数Dockerコンテナ管理
  ├ build.gradle :Gradleプロジェクト定義
  ├ datasource.gradle: データソース設定
  ├ settings.gradle :Gradleプロジェクト設定
  └ README.md
</pre>

## パッケージ構成
- 参考リポジトリ https://github.com/system-sekkei/isolating-the-domain
```
main
  kotlin
    com.example.todo
      application                     … アプリケーション層
        repository                      … リポジトリインターフェイス
        service                         … @Service
      config                          … コンフィギュレーション(@Configuration)
      domain                          … ドメイン層
        model                           … ドメインモデル
        service                         … ドメインサービス
      infrastructure                  … インフラストラクチャ層
        dao                             … @Dao
        record                          … @Entity
        repository                      … リポジトリインターフェイスの実装(@Repository)
      middleware                      … ミドルウェア
      presentation                    … プレゼンテーション層
        controller                      … @Controller
          api                             … API(/api)
      util                            … ユーティリティ
  resources
    db                                … flyway管理(マイグレーション)
    - application.yml                 … アプリケーション設定ファイル
```

## セットアップ

[[ローカル環境構築]](https://github.com/taroshun32/todoapp-api-kotlin-springboot/wiki/ローカル環境構築)を参照

## 起動方法

```shell
# 起動
$ ./docker-compose up

# 停止
$ ./docker-compose down
```

http://localhost:8080/ にアクセス

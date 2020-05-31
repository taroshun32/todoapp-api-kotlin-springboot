# todoapp-api-kotlin-springboot
kotlin×springboot タスク管理アプリ用API

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

## 開発環境
| 項目 | バージョン | 用途 |
|:-----------:|:-----------:|:-----------:|
| Kotlin | 1.3 | 言語 |
| AdoptOpenJDK | 11 | JDK |
| Gradle | 6.3 | ビルド |
| Sprint Boot | 2.2.6 | フレームワーク |

#!/bin/sh

set -x &&
  # パッケージ全更新
  apk update &&
  # タイムゾーン変更 https://wiki.alpinelinux.org/wiki/Setting_the_timezone
  apk --update add tzdata &&
  cp /usr/share/zoneinfo/Asia/Tokyo /etc/localtime &&
  echo "Asia/Tokyo" >/etc/timezone &&
  # タイムゾーンファイルをコピーし終えて不要になったタイムゾーンパッケージはアンインストール
  apk del tzdata

# アプリケーションの起動
./gradlew bootRun

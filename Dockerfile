FROM adoptopenjdk/openjdk11:alpine-slim

# javaの起動オプション
ENV JAVA_OPTS=""

# ロケール
ENV LANG ja_JP.UTF-8
ENV LC_ALL ja_JP.UTF-8
ENV LC_CTYPE ja_JP.UTF-8

RUN set -x && \
    # パッケージ全更新
    apk update && \
    # タイムゾーン変更 https://wiki.alpinelinux.org/wiki/Setting_the_timezone
    apk --update add tzdata && \
    cp /usr/share/zoneinfo/Asia/Tokyo /etc/localtime && \
    echo "Asia/Tokyo" > /etc/timezone && \
    # タイムゾーンファイルをコピーし終えて不要になったタイムゾーンパッケージはアンインストール
    apk del tzdata

## Add a volume pointing to /tmp
VOLUME /tmp

## Make port 8080 available to the world outside this container
EXPOSE 8080

## The application's jar file
ARG JAR_FILE=todo-0.0.1-SNAPSHOT.jar

## Add the application's jar to the container
ADD ./build/libs/${JAR_FILE} /todo.jar

## Run the jar file
ENTRYPOINT java $JAVA_OPTS -Dfile.encoding=UTF-8 -Djava.security.egd=file:/dev/./urandom -jar /todo.jar
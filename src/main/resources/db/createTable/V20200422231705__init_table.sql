/* Create Tables */

-- ユーザ
CREATE TABLE users
(
    id           CHAR(26)                NOT NULL COMMENT 'ユーザ識別ID',
    name         VARCHAR(24)             NOT NULL COMMENT 'ユーザ名',
    password     VARCHAR(24)             NOT NULL COMMENT 'パスワード',
    os_type      ENUM ('IOS', 'ANDROID') NOT NULL COMMENT 'スマホOS種別',
    created_at   DATETIME                NOT NULL COMMENT '登録日時',
    updated_at   DATETIME                NOT NULL COMMENT '更新日時',
    version      BIGINT UNSIGNED         NOT NULL COMMENT 'バージョン情報',
    PRIMARY KEY (id)
)
    COMMENT = 'ユーザ'
    /*! engine = InnoDb */;

-- ユーザ認証情報
CREATE TABLE tokens
(
    id                 CHAR(26)        NOT NULL COMMENT 'ユーザ認証情報ID',
    user_id            CHAR(26)        NOT NULL COMMENT 'ユーザ識別ID',
    access_token       CHAR(36)        NOT NULL COMMENT 'アクセストークン',
    access_expires_in  DATETIME        NOT NULL COMMENT 'アクセストークン有効期限',
    refresh_token      CHAR(36)        NOT NULL COMMENT 'リフレッシュトークン',
    refresh_expires_in DATETIME        NOT NULL COMMENT 'リフレッシュトークン有効期限',
    created_at         DATETIME        NOT NULL COMMENT '登録日時',
    updated_at         DATETIME        NOT NULL COMMENT '更新日時',
    version            BIGINT UNSIGNED NOT NULL COMMENT 'バージョン情報',
    PRIMARY KEY (id),
    UNIQUE KEY (user_id),
    UNIQUE KEY (access_token),
    UNIQUE KEY (refresh_token),
    CONSTRAINT fk_tokens_user_id
        FOREIGN KEY (user_id)
            REFERENCES users (id)
            ON DELETE CASCADE
            ON UPDATE RESTRICT
)
    COMMENT = 'ユーザ認証情報'
    /*! engine = InnoDb */;

-- タスク情報
CREATE TABLE tasks
(
    id                 CHAR(26)        NOT NULL COMMENT 'タスクID',
    user_id            CHAR(26)        NOT NULL COMMENT 'ユーザ識別ID',
    name               CHAR(36)        NOT NULL COMMENT 'タスク名',
    expires_in         DATE            NOT NULL COMMENT 'タスク期限',
    is_done            BOOL            NOT NULL COMMENT '完了フラグ',
    created_at         DATETIME        NOT NULL COMMENT '登録日時',
    updated_at         DATETIME        NOT NULL COMMENT '更新日時',
    version            BIGINT UNSIGNED NOT NULL COMMENT 'バージョン情報',
    PRIMARY KEY (id),
    CONSTRAINT fk_tasks_user_id
        FOREIGN KEY (user_id)
            REFERENCES users (id)
            ON DELETE CASCADE
            ON UPDATE RESTRICT
)
    COMMENT = 'ユーザ認証情報'
    /*! engine = InnoDb */;
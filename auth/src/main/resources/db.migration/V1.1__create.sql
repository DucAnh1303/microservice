CREATE TABLE auth
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_user  BIGINT NULL,
    created_at    datetime NOT NULL,
    updated_user  BIGINT NULL,
    updated_at    datetime NULL,
    name          VARCHAR(255) NULL,
    email         VARCHAR(255) NULL,
    password      VARCHAR(255) NULL,
    show_password VARCHAR(255) NULL,
    access_role   VARCHAR(255) NULL,
    CONSTRAINT pk_auth PRIMARY KEY (id)
);

CREATE TABLE auth_control
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_user  BIGINT NULL,
    created_at    datetime NOT NULL,
    updated_user  BIGINT NULL,
    updated_at    datetime NULL,
    auth_id       BIGINT NULL,
    name          VARCHAR(255) NULL,
    email         VARCHAR(255) NULL,
    token         VARCHAR(4000) NULL,
    refresh_token VARCHAR(4000) NULL,
    CONSTRAINT pk_auth_control PRIMARY KEY (id)
);

CREATE TABLE `role`
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);
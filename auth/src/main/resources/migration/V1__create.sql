CREATE TABLE auth (
         id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
         name VARCHAR(255),
         email VARCHAR(255),
         password VARCHAR(255),
         show_password VARCHAR(255),
         created_user VARCHAR(1),
         created_at TIMESTAMP ,
         updated_user VARCHAR(1),
         updated_at TIMESTAMP
) AUTO_INCREMENT = 1 ;

CREATE TABLE auth_control (
                      id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                      auth_id BIGINT NOT NULL ,
                      name VARCHAR(255),
                      email VARCHAR(255),
                      token VARCHAR(4000),
                      refresh_token VARCHAR(4000),
                      created_user VARCHAR(1),
                      created_at TIMESTAMP ,
                      updated_user VARCHAR(1),
                      updated_at TIMESTAMP
) AUTO_INCREMENT = 1 ;
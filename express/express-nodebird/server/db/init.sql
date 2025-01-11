DO
$$
    BEGIN
        IF NOT EXISTS
            (SELECT 1
             FROM pg_type
             WHERE typname = 'domain_type_enum')
        THEN
            CREATE TYPE DOMAIN_TYPE_ENUM AS ENUM ('free', 'premium');
        END IF;
    END
$$;

DO
$$
    BEGIN
        IF NOT EXISTS
            (SELECT 1
             FROM pg_type
             WHERE typname = 'provider_type_enum')
        THEN
            CREATE TYPE PROVIDER_TYPE_ENUM AS ENUM ('local', 'kakao');
        END IF;
    END
$$;


CREATE TABLE IF NOT EXISTS users
(
    id         SERIAL PRIMARY KEY,
    email      VARCHAR(100) UNIQUE NOT NULL,
    nick       VARCHAR(100),
    password   VARCHAR(100),
    provider   PROVIDER_TYPE_ENUM DEFAULT 'local',
    sns_id     BIGINT,
    avatar_url VARCHAR(200),
    created_at TIMESTAMP          DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS posts
(
    id         SERIAL PRIMARY KEY,
    title      TEXT       NOT NULL,
    content    TEXT       NOT NULL,
    image      TEXT,
    created_at TIMESTAMP DEFAULT NOW(),
    user_id    INT UNIQUE NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS hashtags
(
    id         SERIAL PRIMARY KEY,
    title      TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS post_hashtags
(
    post_id    INT NOT NULL,
    hashtag_id INT NOT NULL,
    PRIMARY KEY (post_id, hashtag_id),
    FOREIGN KEY (post_id) REFERENCES posts (id) ON DELETE CASCADE,
    FOREIGN KEY (hashtag_id) REFERENCES hashtags (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS domain
(
    id            SERIAL PRIMARY KEY,
    host          VARCHAR(80) NOT NULL,
    type          DOMAIN_TYPE_ENUM DEFAULT ('free'),
    client_secret UUID             DEFAULT gen_random_uuid(),
    created_at    TIMESTAMP        DEFAULT NOW(),
    user_id       INT UNIQUE  NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

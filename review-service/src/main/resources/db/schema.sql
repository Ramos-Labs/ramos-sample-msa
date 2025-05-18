DROP TABLE IF EXISTS reviews;

CREATE TABLE reviews
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    book_id    BIGINT       NOT NULL,
    reviewer   VARCHAR(255) NOT NULL,
    rating     INT          NOT NULL,
    comment    VARCHAR(2000),
    created_at TIMESTAMP    NOT NULL
);
DROP TABLE IF EXISTS books;

CREATE TABLE books
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    title          VARCHAR(255) NOT NULL,
    author_id      BIGINT       NOT NULL,
    published_date TIMESTAMP    NOT NULL
);
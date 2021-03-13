CREATE TABLE IF NOT EXISTS COUNTRY (
    id INT NOT NULL,
    code VARCHAR(4),
    name VARCHAR(60),
    continent VARCHAR(4),
    wikipedia_link VARCHAR(500),
    keywords VARCHAR(500),
    PRIMARY KEY(id)
);
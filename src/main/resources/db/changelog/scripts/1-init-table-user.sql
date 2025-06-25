CREATE TABLE IF NOT EXISTS user_ (
                                      id bigserial PRIMARY KEY,
                                      name VARCHAR(255) NOT NULL,
                                      email VARCHAR(255) NOT NULL,
                                      password VARCHAR(255) NOT NULL,
                                      post VARCHAR(255) NOT NULL,
                                      telegram VARCHAR(255),
                                      number VARCHAR(30) NOT NULL
);

CREATE SEQUENCE if not exists rest_audit_SEQ INCREMENT BY 50;

CREATE TABLE if not exists rest_audit
(
    id           BIGSERIAL PRIMARY KEY not null,
    name         varchar(255)          not null,
    request_time timestamp             not null default now(),
    role         VARCHAR(10)           NOT NULL,
    request_type VARCHAR(10)           NOT NULL,
    body         text
    );

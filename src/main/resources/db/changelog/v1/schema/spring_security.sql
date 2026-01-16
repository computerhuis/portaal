-- liquibase formatted sql
-- changeset ronlievens:schema-0
create table persistent_logins
(
    username  VARCHAR(36)            NOT NULL,
    series    VARCHAR(64)            NOT NULL,
    token     VARCHAR(64)            NOT NULL,
    last_used DATETIME DEFAULT NOW() NOT NULL
);
alter table persistent_logins
    add constraint persistent_logins_pk primary key (SERIES);

-- liquibase formatted sql
-- changeset ronlievens:schema-1
CREATE TABLE ticket_details
(
    ticket_id BIGINT NOT NULL,
    name      VARCHAR(255),
    value     VARCHAR(255),

    CONSTRAINT ticket_details_pk PRIMARY KEY (ticket_id, name),
    CONSTRAINT ticket_details_fk_1 FOREIGN KEY (ticket_id) REFERENCES tickets (id) ON UPDATE RESTRICT ON DELETE CASCADE
) ENGINE = InnoDB
  CHARACTER SET 'utf8'
  COLLATE 'utf8_unicode_ci';

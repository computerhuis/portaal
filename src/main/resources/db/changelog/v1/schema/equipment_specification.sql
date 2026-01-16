-- liquibase formatted sql
-- changeset ronlievens:schema-1
CREATE TABLE equipment_specification
(
    equipment_id BIGINT NOT NULL,
    name         VARCHAR(255),
    value        VARCHAR(255),

    CONSTRAINT equipment_specification_pk PRIMARY KEY (equipment_id, name),
    CONSTRAINT equipment_specification_fk_1 FOREIGN KEY (equipment_id) REFERENCES equipment (id) ON UPDATE RESTRICT ON DELETE CASCADE
) ENGINE = InnoDB
  CHARACTER SET 'utf8'
  COLLATE 'utf8_unicode_ci';

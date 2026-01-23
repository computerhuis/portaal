-- liquibase formatted sql
-- changeset ronlievens:schema-1
CREATE TABLE proof_of_issue_lines
(
    proof_of_issue_id BIGINT         NOT NULL AUTO_INCREMENT,
    line_id           BIGINT         NOT NULL,
    customer_id       BIGINT         NOT NULL,
    equipment_id      BIGINT         NOT NULL,
    ticket_id         BIGINT,
    description       TEXT           NOT NULL,
    quantity          DECIMAL(10, 2) NOT NULL DEFAULT 1,
    unit              VARCHAR(50)             DEFAULT 'pcs',

    CONSTRAINT proof_of_issue_lines_pk PRIMARY KEY (proof_of_issue_id, line_id),
    CONSTRAINT proof_of_issue_lines_fk_1 FOREIGN KEY (proof_of_issue_id) REFERENCES proof_of_issues (id) ON UPDATE RESTRICT ON DELETE CASCADE,
    CONSTRAINT proof_of_issue_lines_fk_2 FOREIGN KEY (equipment_id) REFERENCES equipment (id) ON UPDATE RESTRICT ON DELETE CASCADE,
    CONSTRAINT proof_of_issue_lines_fk_3 FOREIGN KEY (ticket_id) REFERENCES tickets (id) ON UPDATE RESTRICT ON DELETE CASCADE
) ENGINE = InnoDB
  CHARACTER SET 'utf8'
  COLLATE 'utf8_unicode_ci';

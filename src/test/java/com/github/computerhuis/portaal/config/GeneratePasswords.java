package com.github.computerhuis.portaal.config;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
@Disabled("Only for generating passwords")
class GeneratePasswords {

    private BCryptPasswordEncoder encoder;

    @BeforeEach
    void beforeEach() {
        encoder = new BCryptPasswordEncoder();
    }

    @Test
    void generate_passhash_for_sjef() {
        val passhash = encoder.encode("sjef");
        val id = 897;
        log_sql(passhash, id, true);
    }

    @Test
    void generate_passhash_for_ron() {
        val passhash = encoder.encode("ron");
        val id = 2;
        log_sql(passhash, id, true);
    }

    @Test
    void generate_passhash_for_helmien() {
        val passhash = encoder.encode("helmien");
        val id = 2111;
        log_sql(passhash, id, false);
    }

    private void log_sql(@NonNull final String passhash, final int id, final boolean admin) {
        log.info("UPDATE dco.persons SET passhash='{}' WHERE id={};", passhash, id);
        log.info("select * from dco.persons where id={};", id);
        log.info("insert into dco.person_roles (person_id, authority) values ({}, 'ROLE_VOLUNTEER');", id);
        if (admin) {
            log.info("insert into dco.person_roles (person_id, authority) values ({}, 'ROLE_ADMIN');", id);
        }
    }
}

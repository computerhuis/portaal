package com.github.computerhuis.portaal.web.setting.postal;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.github.computerhuis.portaal.repository.PostalRepository;
import com.github.computerhuis.portaal.repository.model.Postal;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Data
@Component
public class PostalCrudBean {

    private final PostalRepository postalRepository;

    private Postal postal;
    private String code;

    @PostConstruct
    public void init() {
        postal = new Postal();
    }

    public void fetch() {
        postal = postalRepository.findById(code).orElse(new Postal());
    }
}


package com.github.computerhuis.portaal.web.dataset.person;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import com.github.computerhuis.portaal.repository.PersonRepository;
import com.github.computerhuis.portaal.repository.model.Person;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Data
@Component
public class PersonCrudBean {

    private final PersonRepository personRepository;

    private Person person;
    private Long id;

    @PostConstruct
    public void init() {
        person = new Person();
    }

    public void fetch() {
        if (id != null) {
            person = personRepository.findById(id).orElse(new Person());
        }
    }

    public String save() {
        personRepository.saveAndFlush(person);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Save", "Wijzigingen opgeslagen"));
        log.info("Saved person {}", person.getId());

        val viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        if (viewId.contains("/ticket/crud")) {
            val params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            if (!params.containsKey("id")) {
                return String.format("/ticket/crud?faces-redirect=true&id=%d", person.getId());
            }
        }
        return null;
    }
}

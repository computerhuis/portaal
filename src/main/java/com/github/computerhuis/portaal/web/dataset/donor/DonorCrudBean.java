package com.github.computerhuis.portaal.web.dataset.donor;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import com.github.computerhuis.portaal.repository.DonorRepository;
import com.github.computerhuis.portaal.repository.model.Donor;
import org.springframework.stereotype.Component;


@Slf4j
@RequiredArgsConstructor
@Data
@Component
public class DonorCrudBean {

    private final DonorRepository donorRepository;

    private Donor donor;
    private Long id;

    @PostConstruct
    public void init() {
        donor = new Donor();
    }

    public void fetch() {
        if (id != null) {
            donor = donorRepository.findById(id).orElse(new Donor());
        }
    }

    public String save() {
        donorRepository.saveAndFlush(donor);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Save", "Wijzigingen opgeslagen"));
        log.info("Saved client {}", donor.getId());

        val viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        if (viewId.contains("/donor/crud")) {
            val params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            if (!params.containsKey("id")) {
                return String.format("/donor/crud?faces-redirect=true&id=%d", donor.getId());
            }
        }
        return null;
    }
}

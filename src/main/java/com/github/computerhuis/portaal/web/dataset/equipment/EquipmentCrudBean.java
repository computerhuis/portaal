package com.github.computerhuis.portaal.web.dataset.equipment;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import com.github.computerhuis.portaal.enumeration.EquipmentCategoryType;
import com.github.computerhuis.portaal.enumeration.EquipmentStatus;
import com.github.computerhuis.portaal.repository.DonorRepository;
import com.github.computerhuis.portaal.repository.EquipmentRepository;
import com.github.computerhuis.portaal.repository.PersonRepository;
import com.github.computerhuis.portaal.repository.model.Donor;
import com.github.computerhuis.portaal.repository.model.Equipment;
import com.github.computerhuis.portaal.repository.model.Person;
import org.primefaces.event.RowEditEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Data
@Component
public class EquipmentCrudBean {

    private static final List<EquipmentCategoryType> EQUIPMENT_CATEGORIES = Arrays.asList(EquipmentCategoryType.values());
    private static final List<EquipmentStatus> EQUIPMENT_STATUSES = Arrays.asList(EquipmentStatus.values());

    private final EquipmentRepository equipmentRepository;
    private final PersonRepository personRepository;
    private final DonorRepository donorRepository;

    private Person owner;
    private Equipment equipment;
    private Long id;
    private List<Donor> donors;

    // TODO: waarschijnlijk niet geupdate nadat een nieuwe donor toegevoegd wordt
    @PostConstruct
    public void init() {
        equipment = new Equipment();
        equipment.setSpecification(new HashMap<>());
        donors = donorRepository.findAll();
    }

    public void fetch() {
        if (id != null) {
            equipment = equipmentRepository.findById(id).orElse(new Equipment());
            if (equipment.getOwnerId() != null) {
                owner = personRepository.findById(equipment.getOwnerId()).orElse(null);
            }
            if (equipment.getSpecification() == null) {
                equipment.setSpecification(new HashMap<>());
            }
        }
    }

    public List<EquipmentCategoryType> getEquipmentCategories() {
        return EQUIPMENT_CATEGORIES;
    }

    public List<EquipmentStatus> getEquipmentStatuses() {
        return EQUIPMENT_STATUSES;
    }

    public String save() {
        equipmentRepository.saveAndFlush(equipment);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Save", "Wijzigingen opgeslagen"));
        log.info("Saved equipment {}", equipment.getId());

        val viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        if (viewId.contains("/equipment/crud")) {
            val params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            if (!params.containsKey("id")) {
                return String.format("/equipment/crud?faces-redirect=true&id=%d", equipment.getId());
            }
        }
        return null;
    }

    public void setDeleteSpecification(final String key) {
        equipment.getSpecification().remove(key);
        equipmentRepository.saveAndFlush(equipment);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "Verwijdering uitgevoerd"));
        log.info("Deleted equipment specification {}", key);
    }

    public void onRowEdit(RowEditEvent<Map.Entry<String, String>> event) {
        Map.Entry<String, String> entry = event.getObject();
        equipment.getSpecification().put(entry.getKey(), entry.getValue());
        equipmentRepository.saveAndFlush(equipment);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Save", "Veld %s aangepast".formatted(entry.getKey())));
        log.info("Chanced equipment specification {}", entry.getKey());
    }
}

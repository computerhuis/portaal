package com.github.computerhuis.portaal.web.dataset.donor;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.github.computerhuis.portaal.repository.DonorRepository;
import com.github.computerhuis.portaal.repository.EquipmentRepository;
import com.github.computerhuis.portaal.repository.model.Donor;
import com.github.computerhuis.portaal.repository.model.Equipment;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Data
@Component
public class DonorEquipmentListBean {

    private final EquipmentRepository equipmentRepository;
    private final DonorRepository donorRepository;

    private Long id;
    private Donor donor;
    private List<Equipment> list;

    public void fetch() {
        donor = donorRepository.findById(id).orElse(new Donor());
        list = equipmentRepository.findByDonorId(id);
    }
}

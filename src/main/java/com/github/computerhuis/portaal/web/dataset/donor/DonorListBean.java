package com.github.computerhuis.portaal.web.dataset.donor;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.github.computerhuis.portaal.repository.DonorRepository;
import com.github.computerhuis.portaal.repository.model.Donor;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Data
@Component
public class DonorListBean {

    private final DonorRepository donorRepository;

    private List<Donor> list;

    public void fetch() {
        list = donorRepository.findAll();
    }
}

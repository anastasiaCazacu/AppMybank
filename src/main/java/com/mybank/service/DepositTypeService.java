package com.mybank.service;

import com.mybank.dto.depositType.DepositTypeCreateDTO;
import com.mybank.dto.depositType.DepositTypeDTO;
import com.mybank.entity.DepositType;
import com.mybank.repository.DepositTypeRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepositTypeService {

    private final DepositTypeRepository depositTypeRepository;

    public DepositTypeService(DepositTypeRepository depositTypeRepository) {
        this.depositTypeRepository = depositTypeRepository;
    }

    public DepositTypeDTO createDepositType(DepositTypeCreateDTO dto) {
        DepositType depositType = new DepositType();
        depositType.setCode(dto.getCode());
        depositType.setLabelRo(dto.getLabelRo());
        depositType.setDescriptionRo(dto.getDescriptionRo());
        depositType.setDefaultRate(dto.getDefaultRate());

        DepositType saved = depositTypeRepository.save(depositType);

        return mapToDTO(saved);
    }

    public List<DepositTypeDTO> getAllDepositTypes() {
        return depositTypeRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Optional<DepositTypeDTO> getDepositTypeById(Long id) {
        return depositTypeRepository.findById(id).map(this::mapToDTO);
    }

    private DepositTypeDTO mapToDTO(DepositType depositType) {
        DepositTypeDTO dto = new DepositTypeDTO();
        dto.setId(depositType.getId());
        dto.setCode(depositType.getCode());
        dto.setLabelRo(depositType.getLabelRo());
        dto.setDescriptionRo(depositType.getDescriptionRo());
        dto.setDefaultRate(depositType.getDefaultRate());
        return dto;
    }

    public DepositTypeDTO updateDepositType(Long id, @Valid DepositTypeCreateDTO dto) {
//        DepositType depositType = depositTypeRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Deposit type not found"));
//
//        depositType.setCode(dto.getCode());
//        depositType.setLabelRo(dto.getLabelRo());
//        depositType.setDescriptionRo(dto.getDescriptionRo());
//        depositType.setDefaultRate(dto.getDefaultRate());
//
//        depositType.setUpdatedAt(new Date());
//        depositType.setUpdatedBy(getAuthenticatedUser());
//
//        DepositType saved = depositTypeRepository.save(depositType);
//        return mapToDTO(saved);
        return null; ///masura temporara
    }
}

package com.mybank.controller;

import com.mybank.dto.depositType.DepositTypeCreateDTO;
import com.mybank.dto.depositType.DepositTypeDTO;
import com.mybank.service.DepositTypeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deposit-types")
public class DepositTypeController {
    private final DepositTypeService depositTypeService;

    public DepositTypeController(DepositTypeService depositTypeService) {
        this.depositTypeService = depositTypeService;
    }

    @PostMapping
    public ResponseEntity<DepositTypeDTO> createDepositType(@Valid @RequestBody DepositTypeCreateDTO dto) {
        DepositTypeDTO created = depositTypeService.createDepositType(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public List<DepositTypeDTO> getAllDepositTypes() {
        return depositTypeService.getAllDepositTypes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepositTypeDTO> getDepositTypeById(@PathVariable Long id) {
        return depositTypeService.getDepositTypeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'BANK')")
    public ResponseEntity<DepositTypeDTO> updateDepositType(
            @PathVariable Long id,
            @Valid @RequestBody DepositTypeCreateDTO dto) {

        DepositTypeDTO updated = depositTypeService.updateDepositType(id, dto);
        return ResponseEntity.ok(updated);
    }

}

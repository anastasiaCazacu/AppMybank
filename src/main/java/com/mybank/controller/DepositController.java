package com.mybank.controller;
import com.mybank.entity.Deposit;
import com.mybank.service.DepositService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mybank.model.DepositRequest;

import java.util.List;

@RestController
@RequestMapping("/deposits")
public class DepositController {
    private final DepositService depositService;

    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    // creare depozit nou
    @PostMapping("/create")
    public ResponseEntity<Deposit> createDeposit(@Valid @RequestBody DepositRequest request) {
        Deposit deposit = depositService.createDeposit(
                request.getUserId(),
                request.getAmount(),
                request.getRate(),
                request.getTermMonths(),
                null // aici poți rezolva cine e createdBy
        );
        return ResponseEntity.ok(deposit);
    }


//    @PostMapping("/create")
//    public ResponseEntity<Deposit> createDeposit(
//            @RequestParam Long userId,
//            @RequestParam Double amount,
//            @RequestParam Double rate,
//            @RequestParam Integer termMonths,
//            @RequestParam Long createdById) {
//
//        // aici poți rezolva cine e createdBy (adminul curent)
//        Deposit deposit = depositService.createDeposit(userId, amount, rate, termMonths, null);
//        return ResponseEntity.ok(deposit);
//    }

    // inchiderea unui depozit
    @PutMapping("/{id}/close")
    public ResponseEntity<Deposit> closeDeposit(@PathVariable Long id) {
        Deposit deposit = depositService.closeDeposit(id);
        return ResponseEntity.ok(deposit);
    }

    // Actualizare status (ex. verificare expirare)
    @PutMapping("/{id}/update-status")
    public ResponseEntity<Void> updateStatus(@PathVariable Long id) {
        depositService.updateDepositStatus(id);
        return ResponseEntity.ok().build();
    }

    // Afisarea depozite active pentru un utilizator
    @GetMapping("/user/{userId}/active")
    public ResponseEntity<List<Deposit>> getActiveDepositsByUser(@PathVariable Long userId) {
        List<Deposit> deposits = depositService.getActiveDepositsByUser(userId);
        return ResponseEntity.ok(deposits);
    }

}

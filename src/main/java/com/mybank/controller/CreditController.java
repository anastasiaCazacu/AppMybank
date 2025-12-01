package com.mybank.controller;

import com.mybank.entity.Credit;
import com.mybank.exception.ResourceNotFoundException;
import com.mybank.model.CreditRequest;
import com.mybank.model.CreditResponse;
import com.mybank.service.CreditService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping("/credits")
public class CreditController {

    private final CreditService creditService;

    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    //Creează un credit nou
    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('BANK', 'ADMIN')")
    public ResponseEntity<CreditResponse> createCredit(@RequestBody CreditRequest request) {
        try {
            CreditResponse credit = creditService.createCredit(request);
            return ResponseEntity.ok(credit);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //Găsește credite într-un interval de date
    @GetMapping("/between")
    @PreAuthorize("hasAnyRole('BANK', 'ADMIN')")
    public ResponseEntity<List<Credit>> getCreditsBetween(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        Date startDate = Date.from(start.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(end.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return ResponseEntity.ok(creditService.getCreditsBetween(startDate, endDate));
    }

    //Găsește credite exact la o dată
    @GetMapping("/by-date")
    @PreAuthorize("hasAnyRole('BANK', 'ADMIN')")
    public ResponseEntity<List<Credit>> getCreditsByDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(creditService.getCreditsByDate(date));
    }

    //Găsește toate creditele asociate unui utilizator
    @GetMapping("/by-user/{userId}")
    @PreAuthorize("hasAnyRole('BANK', 'ADMIN')")
    public ResponseEntity<List<Credit>> getCreditsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(creditService.getCreditsByUser(userId));
    }

    // Găsește toate creditele aprobate de un utilizator
    @GetMapping("/approved-by/{username}")
    @PreAuthorize("hasAnyRole('BANK', 'ADMIN')")
    public ResponseEntity<List<Credit>> getCreditsApprovedBy(@PathVariable String username) {
        return ResponseEntity.ok(creditService.getCreditsApprovedBy(username));
    }

    //Total credite aprobate de un utilizator
    @GetMapping("/count-approved/{username}")
    @PreAuthorize("hasAnyRole('BANK', 'ADMIN')")
    public ResponseEntity<Integer> countApprovedCredits(@PathVariable String username) {
        return ResponseEntity.ok(creditService.countApprovedCredits(username));
    }

    //Total credite într-o zi
    @GetMapping("/count-by-date")
    @PreAuthorize("hasAnyRole('BANK', 'ADMIN')")
    public ResponseEntity<Integer> countCreditsByDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(creditService.countCreditsByDate(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant())));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCredit(@PathVariable Long id) throws ResourceNotFoundException {
        creditService.deleteCredit(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

}
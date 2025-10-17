package com.mybank.controller;

import com.mybank.entity.Credit;
import com.mybank.exception.ResourceNotFoundException;
import com.mybank.model.CreditRequest;
import com.mybank.service.CreditService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Credit> createCredit(@RequestBody CreditRequest request) {
        try {
            Credit credit = creditService.createCredit(request);
            return ResponseEntity.ok(credit);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //Găsește credite într-un interval de date
    @GetMapping("/between")
    public ResponseEntity<List<Credit>> getCreditsBetween(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        Date startDate = Date.from(start.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(end.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return ResponseEntity.ok(creditService.getCreditsBetween(startDate, endDate));
    }

    //Găsește credite exact la o dată
    @GetMapping("/by-date")
    public ResponseEntity<List<Credit>> getCreditsByDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(creditService.getCreditsByDate(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant())));
    }

    //Găsește toate creditele asociate unui utilizator
    @GetMapping("/by-user/{userId}")
    public ResponseEntity<List<Credit>> getCreditsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(creditService.getCreditsByUser(userId));
    }

    // Găsește toate creditele aprobate de un utilizator
    @GetMapping("/approved-by/{username}")
    public ResponseEntity<List<Credit>> getCreditsApprovedBy(@PathVariable String username) {
        return ResponseEntity.ok(creditService.getCreditsApprovedBy(username));
    }

    //Total credite aprobate de un utilizator
    @GetMapping("/count-approved/{username}")
    public ResponseEntity<Integer> countApprovedCredits(@PathVariable String username) {
        return ResponseEntity.ok(creditService.countApprovedCredits(username));
    }

    //Total credite într-o zi
    @GetMapping("/count-by-date")
    public ResponseEntity<Integer> countCreditsByDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(creditService.countCreditsByDate(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant())));
    }
}
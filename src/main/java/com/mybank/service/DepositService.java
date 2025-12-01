package com.mybank.service;

import com.mybank.model.DepositResponse;
import org.springframework.stereotype.Service;
import com.mybank.entity.Deposit;
import com.mybank.entity.DepositStatus;
import com.mybank.entity.User;
import com.mybank.repository.DepositRepository;
import com.mybank.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class DepositService {
    private final DepositRepository depositRepository;
    private final UserRepository userRepository;

    public DepositService(DepositRepository depositRepository,
                          UserRepository userRepository) {
        this.depositRepository = depositRepository;
        this.userRepository = userRepository;
    }

    // Creare depozit nou
    public Deposit createDeposit(Long userId, Double amount, Double rate,
                                 Integer termMonths, User createdBy) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Deposit deposit = new Deposit();
        deposit.setUser(user);
        deposit.setCreatedBy(createdBy);
        deposit.setAmount(amount);
        deposit.setRate(rate);
        deposit.setTermMonths(termMonths);
        deposit.setStartDate(new Date());

        // calculează endDate pe baza termMonths
        long millisInMonth = 30L * 24L * 60L * 60L * 1000L;
        deposit.setEndDate(new Date(deposit.getStartDate().getTime() + termMonths * millisInMonth));

        deposit.setStatus(DepositStatus.ACTIVE);

        return depositRepository.save(deposit);
    }

    //  Închidere depozit
    public Deposit closeDeposit(Long depositId) {
        Deposit deposit = depositRepository.findById(depositId)
                .orElseThrow(() -> new RuntimeException("Deposit not found"));
        deposit.setStatus(DepositStatus.CLOSED);
        return depositRepository.save(deposit);
    }

    //  Actualizare status (ex. verificare expirare sau alte optiuni)
    public void updateDepositStatus(Long depositId) {
        Deposit deposit = depositRepository.findById(depositId)
                .orElseThrow(() -> new RuntimeException("Deposit not found"));

        Date today = new Date();
        if (deposit.getEndDate() != null && deposit.getEndDate().before(today)) {
            deposit.setStatus(DepositStatus.EXPIRED);
            depositRepository.save(deposit);
        }
    }

    //  Listare depozite active pentru un utilizator
    public List<Deposit> getActiveDepositsByUser(Long userId) {
        return depositRepository.findByUserIdAndStatus(userId, DepositStatus.ACTIVE);
    }

    //metoda de conversie
    public DepositResponse toResponse(Deposit deposit) {
        return new DepositResponse(
                deposit.getId(),
                deposit.getAmount(),
                deposit.getRate(),
                deposit.getTermMonths(),
                deposit.getStartDate(),
                deposit.getEndDate(),
                deposit.getStatus(),
                deposit.getUser().getId(),
                deposit.getCreatedBy() != null ? deposit.getCreatedBy().getId() : null,
                deposit.getCreatedAt(),
                deposit.getUpdatedAt()
        );
    }


}

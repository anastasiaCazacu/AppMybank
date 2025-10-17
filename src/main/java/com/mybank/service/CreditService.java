package com.mybank.service;

import com.mybank.entity.Credit;
import com.mybank.entity.User;
import com.mybank.exception.ResourceNotFoundException;
import com.mybank.model.CreditRequest;
import com.mybank.repository.CreditRepository;
import com.mybank.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class CreditService {
    private  final CreditRepository creditRepository;
    private  final UserRepository userRepository;

    public CreditService(CreditRepository creditRepository, UserRepository userRepository) {
        this.creditRepository = creditRepository;
        this.userRepository = userRepository;
    }

    public Credit createCredit(CreditRequest request) throws ResourceNotFoundException {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Credit credit = new Credit();
        credit.setAmount(request.getAmount());
        credit.setDate(new Date(System.currentTimeMillis()));
        return creditRepository.save(credit);
    }
}

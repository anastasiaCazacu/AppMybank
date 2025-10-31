package com.mybank.service;

import com.mybank.entity.Credit;
import com.mybank.entity.User;
import com.mybank.exception.ResourceNotFoundException;
import com.mybank.model.CreditRequest;
import com.mybank.repository.CreditRepository;
import com.mybank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class CreditService {

    private final CreditRepository creditRepository;
    private final UserRepository userRepository;

    @Autowired
    public CreditService(CreditRepository creditRepository, UserRepository userRepository) {
        this.creditRepository = creditRepository;
        this.userRepository = userRepository;
    }

    // ✅ Creează un credit nou
    public Credit createCredit(CreditRequest request) throws ResourceNotFoundException {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Credit credit = new Credit();
        credit.setAmount(request.getAmount());
        credit.setDate(new Date(System.currentTimeMillis()));
        // Poți seta usersCredits aici dacă ai entitatea intermediară
        return creditRepository.save(credit);
    }

    //Găsește credite într-un interval de date
    public List<Credit> getCreditsBetween(Date start, Date end) {
        return creditRepository.findByDateBetween(start, end);
    }

    //Găsește credite exact la o dată
    public List<Credit> getCreditsByDate(Date date) {
        return creditRepository.findByDate(date);
    }

    //Găsește toate creditele asociate unui utilizator
    public List<Credit> getCreditsByUser(Long userId) {
        return creditRepository.findAllByUserId(userId);
    }

    //Găsește toate creditele aprobate de un utilizator
    public List<Credit> getCreditsApprovedBy(String username) {
        return creditRepository.findAllApprovedBy(username);
    }

    //Total credite aprobate de un utilizator
    public int countApprovedCredits(String username) {
        return getCreditsApprovedBy(username).size();
    }

    //Total credite într-o zi
    public int countCreditsByDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date startOfDay = cal.getTime();

        cal.add(Calendar.DAY_OF_MONTH, 1);
        Date endOfDay = cal.getTime();

        return creditRepository.findByDateBetween(startOfDay, endOfDay).size();
    }

//    public int countCreditsByDate(Date date) {
//        return getCreditsByDate(date).size();
//    }
}

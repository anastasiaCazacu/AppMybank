package com.mybank.service;

import com.mybank.entity.Credit;
import com.mybank.entity.User;
import com.mybank.exception.CreditValidationException;
import com.mybank.exception.ResourceNotFoundException;
import com.mybank.model.CreditRequest;
import com.mybank.model.CreditResponse;
import com.mybank.repository.CreditRepository;
import com.mybank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class CreditService {

    private final CreditRepository creditRepository;
    private final UserRepository userRepository;

    //@Autowired
    public CreditService(CreditRepository creditRepository, UserRepository userRepository) {
        this.creditRepository = creditRepository;
        this.userRepository = userRepository;
    }

    //  Creeaza un credit nou
    public CreditResponse createCredit(CreditRequest request) throws ResourceNotFoundException {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // verific varsta
        Date dateOfBirth=user.getDateOfBirth();
        if (dateOfBirth != null){
            int age = Period.between(dateOfBirth.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate(), LocalDate.now()).getYears();
            if (age > 65) {
                throw new CreditValidationException("Vârsta maximă pentru credit este 65 de ani.");
            }
        }

        // verific venit minim
        if (request.getMonthlyIncome() == null || request.getMonthlyIncome().compareTo(BigDecimal.valueOf(3000))<0) {
            throw new CreditValidationException("Venitul lunar trebuie să fie de cel puțin 3000 lei.");
        }

        // verif. credite active
        List<Credit> activeCredits = creditRepository.findActiveByUserId(user.getId());
        if (!activeCredits.isEmpty()) {
            throw new CreditValidationException("Utilizatorul are deja un credit activ.");
        }

        // verific suma maxima
        if (request.getAmount().compareTo(BigDecimal.valueOf(50000))>0) {
            throw new CreditValidationException("Suma maximă acordată este 50.000 lei.");
        }
        // Validare venit minim
        if (request.getMonthlyIncome().compareTo(new BigDecimal("3000")) < 0) {
            throw new CreditValidationException("Venitul lunar trebuie să fie de cel puțin 3000 lei.");
        }

// Validare grad de îndatorare
        if (request.getDebtRatio().compareTo(new BigDecimal("0.4")) > 0) {
            throw new CreditValidationException("Gradul de îndatorare nu poate depăși 40%.");
        }


        Credit credit = new Credit();
        credit.setAmount(request.getAmount());
        credit.setDate(new Date(System.currentTimeMillis()));
        credit.setApprovedBy(request.getApprovedBy());
        credit.setInterestRate(request.getInterestRate());
        credit.setTermMonths(request.getTermMonths());
        credit.setMonthlyIncome(request.getMonthlyIncome());
        credit.setDebtRatio(request.getDebtRatio());

        //  verificăm dacă dueDate a fost trimis în request
        if (request.getDueDate() != null) {
            credit.setDueDate(java.sql.Date.valueOf(request.getDueDate()));
            // dacă în Credit ai java.util.Date, convertim din LocalDate
        } else {
            // fallback: dacă nu e setat, punem data curentă sau o regulă implicită
            credit.setDueDate(new Date(System.currentTimeMillis()));
        }

        //TODO: De adaugat userul
        credit.setUser(user);
        Credit db_Credit = creditRepository.save(credit);

        CreditResponse creditResponse = new CreditResponse();
        creditResponse.setId(db_Credit.getId());
        creditResponse.setApprovedBy(request.getApprovedBy());
        creditResponse.setAmount(request.getAmount());
        creditResponse.setTermMonths(request.getTermMonths());
        creditResponse.setInterestRate(request.getInterestRate());
        creditResponse.setMonthlyIncome(request.getMonthlyIncome());
        creditResponse.setDebtRatio(request.getDebtRatio());
        creditResponse.setDueDate(request.getDueDate());
        creditResponse.setUserName(user.getFullname());

        return creditResponse;
    }

    //Găsește credite într-un interval de date
    public List<Credit> getCreditsBetween(Date start, Date end) {
        return creditRepository.findByDateBetween(start, end);
    }

    //Găsește credite exact la o dată
    public List<Credit> getCreditsByDate(LocalDate date) {
        ZoneId zone = ZoneId.systemDefault();

        Date start = Date.from(date.atStartOfDay(zone).toInstant());
        Date end = Date.from(date.plusDays(1).atStartOfDay(zone).toInstant());

        return creditRepository.findByDateBetween(start, end);
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

    public void deleteCredit(Long id) throws ResourceNotFoundException {
        Credit credit = creditRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Creditul nu a fost găsit cu ID: " + id));
        creditRepository.delete(credit);
    }


//    public int countCreditsByDate(Date date) {
//        return getCreditsByDate(date).size();
//    }
}

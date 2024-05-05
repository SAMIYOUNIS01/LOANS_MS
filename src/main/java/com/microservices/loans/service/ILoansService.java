package com.microservices.loans.service;

import com.microservices.loans.model.dto.LoansDto;
import com.microservices.loans.model.dto.ResponseDto;
import org.springframework.http.ResponseEntity;

public interface ILoansService {

    void createLoan (String mobileNumber);

    LoansDto fetchLoansByMobileNumber(String mobileNumber);

    boolean updateLoans (LoansDto loansDto);

    boolean deleteLonasByMobileNumber(String mobileNumber);
}

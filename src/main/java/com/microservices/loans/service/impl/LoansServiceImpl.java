package com.microservices.loans.service.impl;

import com.microservices.loans.constants.LoansConstants;
import com.microservices.loans.exception.LoansAlreadyExistsException;
import com.microservices.loans.exception.ResourcesNotFoundException;
import com.microservices.loans.mapper.LoansMapper;
import com.microservices.loans.model.dto.LoansDto;
import com.microservices.loans.model.entity.Loans;
import com.microservices.loans.repository.LoansRepository;
import com.microservices.loans.service.ILoansService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService {
    LoansRepository loansRepository;
    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loans> optionalLoans = loansRepository.findByMobileNumber(mobileNumber);
        if(optionalLoans.isPresent()){
            throw new LoansAlreadyExistsException("Loans is already exists for given mobile number = "+ mobileNumber);
        }
        Loans loans = new Loans();
        loans.setMobileNumber(mobileNumber);
        loansRepository.save(createNewLoan(loans));
    }

    @Override
    public LoansDto fetchLoansByMobileNumber(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourcesNotFoundException("Loans" , "mobile" , mobileNumber)
        );

        LoansDto loansDto = LoansMapper.mapToLoansDto(loans , new LoansDto());
        return loansDto;
    }

    @Override
    public boolean updateLoans(LoansDto loansDto) {
        boolean isUpdated = false;
        Loans loans = loansRepository.findByMobileNumber(loansDto.getMobileNumber()).orElseThrow(
                ()-> new ResourcesNotFoundException("Loans" , "mobileNumber" , loansDto.getMobileNumber())
        );
        loansRepository.save(LoansMapper.mapToLoans(loansDto , loans));
        isUpdated = true;
        return isUpdated;
    }

    @Override
    public boolean deleteLonasByMobileNumber(String mobileNumber) {
        boolean isDeleted = false;
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourcesNotFoundException("Loans" , "mobileNumber" , mobileNumber)
        );
        loansRepository.delete(loans);
        isDeleted = true;
        return isDeleted;
    }


    public Loans createNewLoan (Loans newLoan){
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;

    }
}

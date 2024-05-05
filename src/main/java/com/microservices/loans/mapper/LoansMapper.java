package com.microservices.loans.mapper;

import com.microservices.loans.model.dto.LoansDto;
import com.microservices.loans.model.entity.Loans;

public class LoansMapper {


    public static LoansDto mapToLoansDto (Loans loans , LoansDto loansDto){
        loansDto.setLoanType(loans.getLoanType());
        loansDto.setLoanNumber(loans.getLoanNumber());
        loansDto.setTotalLoan(loans.getTotalLoan());
        loansDto.setAmountPaid(loans.getAmountPaid());
        loansDto.setMobileNumber(loans.getMobileNumber());
        loansDto.setOutstandingAmount(loans.getOutstandingAmount());
        return loansDto;

    }



    public static Loans mapToLoans(LoansDto loansDto , Loans loans){
        loans.setMobileNumber(loansDto.getMobileNumber());
        loans.setTotalLoan(loansDto.getTotalLoan());
        loans.setLoanType(loansDto.getLoanType());
        loans.setAmountPaid(loansDto.getAmountPaid());
        loans.setOutstandingAmount(loansDto.getOutstandingAmount());
        loans.setLoanNumber(loansDto.getLoanNumber());
        return loans;
    }
}

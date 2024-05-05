package com.microservices.loans.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Loans extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;
    @Column
    private String mobileNumber;
    @Column
    private String loanNumber;
    @Column
    private String loanType;
    @Column
    private int totalLoan;
    @Column
    private int amountPaid;
    @Column
    private int outstandingAmount;
}

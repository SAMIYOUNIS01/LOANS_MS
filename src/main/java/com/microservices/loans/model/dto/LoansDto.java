package com.microservices.loans.model.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "Loans",
        description = "Schema to hold loans information"
)
public class LoansDto {

    @Schema(
            description = "Client Mobile Number",
            example = "1234567890"
    )
    @NotEmpty(message = "Mobile number can not be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})" , message = "Mobile Number must be 10 digits")
    private String mobileNumber;

    @Schema(
            description = "Client Loan Number",
            example = "100306940190"
    )
    @NotEmpty(message = "Loan Number can not be null or empty")
    @Pattern(regexp = "(^$|[0-9]{12})" , message = "Loan Number must be 12 digits")
    private String loanNumber;

    @Schema(
            description = "Client Loan Type",
            example = "Home Loan"
    )
    @NotEmpty(message = "Loan Type can not be null or empty")
    @Size(min = 5 , max = 30 , message = "The length of loan type must be between 5 and 30 characters")
    private String loanType;

    @Schema(
            description = "Client Total Loan",
            example = "100000"
    )
    @Positive(message = "Total loan must be positive greater than zero")
    private int totalLoan;

    @Schema(
            description = "Client Paid Amount",
            example = "0"
    )
    @PositiveOrZero(message = "Amount paid must be equal or greater than zero")
    private int amountPaid;

    @Schema(
            description = "Client Outstanding Amount",
            example = "10000"
    )
    @PositiveOrZero(message = "Outstanding Amount must be equal or greater than zero")
    private int outstandingAmount;
}

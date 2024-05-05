package com.microservices.loans.controller;

import com.microservices.loans.constants.LoansConstants;
import com.microservices.loans.model.dto.ErrorResponseDto;
import com.microservices.loans.model.dto.LoansDto;
import com.microservices.loans.model.dto.ResponseDto;
import com.microservices.loans.service.ILoansService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "api" , produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
@Tag(
        name = "CRUD REST APIs for loans in bank",
        description = "CRUD REST APIs in bank to create, update ,delete and read loans details"
)
public class LoansController {


    ILoansService loansService;

    @Operation(
            summary = "Create Loan REST API",
            description = "This API to create new Loan"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Http Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status Internal Server Error"
            )
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createNewLoan (
            @Pattern (regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
            @RequestParam
            String mobileNumber){
        loansService.createLoan(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(
                LoansConstants.STATUS_201,
                LoansConstants.MESSAGE_201
        ));

    }

    @Operation(
            summary = "Fetch Loan REST API",
            description = "This API To fetch loan depending on mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping("/fetch")
    public ResponseEntity<LoansDto> fetchLoansByMobileNumber
            (@Pattern(regexp = "(^$|[0-9]{10})" , message = "Mobile number must be 10 digits")
             @RequestParam
             String mobileNumber
            ){
        LoansDto loansDto = loansService.fetchLoansByMobileNumber(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(loansDto);
    }

    @Operation(
            summary = "Update loan REST API",
            description = "This API responsible to update loan details depending on mobile number "
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Http Status Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateLoans (@Valid @RequestBody LoansDto loansDto){
        boolean isUpdated = loansService.updateLoans(loansDto);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(
                    LoansConstants.STATUS_200,
                    LoansConstants.MESSAGE_200
            ));

        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(
                LoansConstants.STATUS_417,
                LoansConstants.MESSAGE_417_UPDATE
        ));
    }


    @Operation(
            summary = "Delete Loan REST API",
            description = "This api responsible on delete loan depending on mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Http Status Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )

    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteLoansByMobileNumber(
            @Pattern(regexp = "(^$|[0-9]{10})" , message = "Mobile number must be 10 digits")
            @RequestParam String mobileNumber){
        boolean isDeleted = loansService.deleteLonasByMobileNumber(mobileNumber);
        if (isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(
                    LoansConstants.STATUS_200,
                    LoansConstants.MESSAGE_200
            ));
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(
                LoansConstants.STATUS_417,
                LoansConstants.MESSAGE_417_DELETE
        ));
    }

}

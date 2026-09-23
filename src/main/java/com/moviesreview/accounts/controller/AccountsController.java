package com.moviesreview.accounts.controller;

import com.moviesreview.accounts.dto.AccountsRequestDto;
import com.moviesreview.accounts.dto.AccountsResponseDto;
import com.moviesreview.accounts.dto.ErrorResponseDto;
import com.moviesreview.accounts.dto.ResponseDto;
import com.moviesreview.accounts.service.impl.AccountsServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/accounts")
@Tag(name = "CRUD Rest API for Accounts Microservices")
public class AccountsController {

    private final AccountsServiceImpl accountsService;

    public AccountsController(AccountsServiceImpl accountsService) {
        this.accountsService = accountsService;
    }


    @Operation(
            summary = "Fetch Accounts by Pagination",
            description = "API REST that returns a pageable list of accounts"
    )
    @ApiResponses(
            value= {
                    @ApiResponse(responseCode = "200", description = "Http Status OK"),
                    @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            ))
            }
    )
    @GetMapping
    public ResponseEntity<Page<AccountsResponseDto>> fetchAccounts(@ParameterObject
                                                               @PageableDefault(page = 0,size = 2,sort = "createdAt",direction = Sort.Direction.ASC)
                                                               Pageable pageable){
        Page<AccountsResponseDto> result = accountsService.fetchAllAccounts(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


    @Operation(
            summary = "Fetch Account Details by UUID",
            description = "API REST that returns the details of an Account based on an UUID"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Http Status OK"),
                    @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                        content = @Content(
                                schema = @Schema(implementation = ErrorResponseDto.class)
                        ))
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<AccountsResponseDto> fetchAccountDetails(@PathVariable UUID id){

        AccountsResponseDto result = accountsService.fetchAccountById(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(
            summary = "Update Account Details",
            description = "API REST that updates an Account by providing UUID and the new details"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Http Status OK"),
                    @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            ))
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<AccountsResponseDto> updateAccount( @PathVariable UUID id, @Valid @RequestBody AccountsRequestDto accountsRequestDto){
        AccountsResponseDto result = accountsService.updateAccount(id,accountsRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(
            summary = "Delete Account Details",
            description = "API REST that delete an Account by UUID"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Http Status OK"),
                    @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            ))
            }
    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(
            @RequestParam @Pattern(
                    regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
                    message = "El formato del UUID no es válido")
            UUID id){

        boolean isDeleted = accountsService.deleteAccount(id);

        if(isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("200","Account deleted"));
        }else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto("417","Something went wrong..."));
        }
    }

}

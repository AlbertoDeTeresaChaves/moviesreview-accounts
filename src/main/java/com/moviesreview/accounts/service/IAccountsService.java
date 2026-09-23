package com.moviesreview.accounts.service;

import com.moviesreview.accounts.dto.AccountsRequestDto;
import com.moviesreview.accounts.dto.AccountsResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IAccountsService {
    Page<AccountsResponseDto> fetchAllAccounts(Pageable pageable);
    AccountsResponseDto fetchAccountById(UUID id);
    AccountsResponseDto updateAccount(UUID id, AccountsRequestDto accountsRequestDto);
    boolean deleteAccount(UUID id);
}

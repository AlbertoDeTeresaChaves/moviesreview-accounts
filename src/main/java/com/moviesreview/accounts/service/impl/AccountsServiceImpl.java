package com.moviesreview.accounts.service.impl;

import com.moviesreview.accounts.dto.AccountsRequestDto;
import com.moviesreview.accounts.dto.AccountsResponseDto;
import com.moviesreview.accounts.entity.Accounts;
import com.moviesreview.accounts.exceptions.AccountAlreadyExistException;
import com.moviesreview.accounts.exceptions.ResourceNotFoundException;
import com.moviesreview.accounts.mapper.AccountsMapper;
import com.moviesreview.accounts.repository.AccountsRepository;
import com.moviesreview.accounts.service.IAccountsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private final AccountsRepository accountsRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<AccountsResponseDto> fetchAllAccounts(Pageable pageable) {
        log.info("Trying to fetch accounts in the page: {}", pageable.getPageNumber());
        Page<Accounts> accountsPage = accountsRepository.findAll(pageable);

        return accountsPage.map(AccountsMapper::toDto);

    }

    @Override
    @Transactional(readOnly = true)
    public AccountsResponseDto fetchAccountById(UUID id) {
        log.info("Trying to fetch Account by the UUID: {} ",id);

        Accounts account = accountsRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Accounts","UUID",id.toString())
        );
        return AccountsMapper.toDto(account);
    }

    @Override
    public AccountsResponseDto updateAccount(UUID id,AccountsRequestDto account) {
        log.info("Trying to update Account with the UUID: {}",id);
        Accounts oldAccount = accountsRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Accounts","UUID",id.toString())
        );

        Accounts updatedAccount = AccountsMapper.toEntity(account,oldAccount);
        accountsRepository.save(updatedAccount);
        return AccountsMapper.toDto(updatedAccount);
    }

    @Override
    public boolean deleteAccount(UUID id) {
        log.info("Trying to delete an Account with the UUID: {}",id);
        Accounts account = accountsRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Accounts","UUID",id.toString())
        );
        accountsRepository.delete(account);
        return true;
    }
}

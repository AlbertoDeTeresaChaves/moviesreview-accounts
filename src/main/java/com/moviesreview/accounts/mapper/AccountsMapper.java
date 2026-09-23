package com.moviesreview.accounts.mapper;

import com.moviesreview.accounts.dto.AccountsRequestDto;
import com.moviesreview.accounts.dto.AccountsResponseDto;
import com.moviesreview.accounts.entity.Accounts;
import org.springframework.stereotype.Component;

@Component
public class AccountsMapper {
    public static AccountsResponseDto toDto (Accounts accounts){
        return new AccountsResponseDto(
                accounts.getId(),
                accounts.getUsername(),
                accounts.getEmail(),
                accounts.getRole(),
                accounts.getDisplayName(),
                accounts.getAvatarUrl(),
                accounts.getBio(),
                accounts.getLocation(),
                accounts.isEnabled()
        );
    }

    public static Accounts toEntity (AccountsRequestDto accountsRequestDto,Accounts account){
        account.setUsername(accountsRequestDto.username());
        account.setEmail(accountsRequestDto.email());
        account.setDisplayName(accountsRequestDto.displayName());
        account.setAvatarUrl(accountsRequestDto.avatarUrl());
        account.setBio(accountsRequestDto.bio());
        account.setLocation(accountsRequestDto.location());
        return account;
    }
}

package com.moviesreview.accounts.dto;

import com.moviesreview.accounts.entity.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Schema(name = "AccountResponse", description = "Schema holding account details sent back to the client")
public record AccountsResponseDto (
        @Schema(description = "Unique identifier of the account", example = "123e4567-e89b-12d3-a456-426614174000")
        UUID id,
        @Schema(description = "Unique username", example = "john_doe")
        String username,
        @Schema(description = "User email address", example = "john.doe@example.com")
        String email,
        @Schema(description = "Assigned user role", example = "ROLE_USER")
        UserRole role,
        @Schema(description = "Public display name", example = "John Doe")
        String displayName,
        @Schema(description = "URL pointing to the user's avatar image", example = "https://example.com/avatars/johndoe.jpg")
        String avatarUrl,
        @Schema(description = "Short biography of the user", example = "Software developer passionate about open-source.")
        String bio,
        @Schema(description = "User location or region", example = "Madrid, Spain")
        String location,
        @Schema(description = "Indicates whether the account is active or locked", example = "true")
        boolean enabled
){ }

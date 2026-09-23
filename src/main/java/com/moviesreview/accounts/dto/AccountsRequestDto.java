package com.moviesreview.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

@Schema(name = "AccountsRequestDto",description = "Schema to hold the Request Information of Accounts")
public record AccountsRequestDto (

    @Schema(description = "Unique username for the account", example = "john_doe")
    @NotBlank(message = "Username cannot be null or empty")
    @Size(min = 3, max = 30, message = "Username must contain between 3 and 30 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_.-]+$", message = "Username contains invalid characters")
    String username,

    @Schema(description = "Email address of the user", example = "john.doe@example.com")
    @NotBlank(message = "Email cannot be null or empty")
    @Email(message = "Email address must be valid")
    @Size(max = 255, message = "Email cannot exceed 255 characters")
    String email,

    @Schema(description = "Password of the account", example = "secure1234")
    @NotBlank(message = "Password cannot be null or empty")
    @Size(min = 8, max = 100, message = "Password must be at least 8 characters long")
    String password,

    @Schema(description = "Public display name of the account holder", example = "John Doe")
    @Size(max = 30, message = "Display name cannot exceed 30 characters")
    String displayName,

    @Schema(description = "URL pointing to the user's avatar image", example = "https://example.com/avatars/johndoe.jpg")
    @Size(max = 2048, message = "Avatar URL cannot exceed 2048 characters")
    @URL(message = "Avatar URL must be a valid web URL")
    String avatarUrl,

    @Schema(description = "Short biography of the user", example = "Software developer passionate about open-source projects.")
    @Size(max = 250, message = "Bio cannot exceed 250 characters")
    String bio,

    @Schema(description = "User location or region", example = "Madrid, Spain")
    @Size(max = 100, message = "Location cannot exceed 100 characters")
    String location
){}

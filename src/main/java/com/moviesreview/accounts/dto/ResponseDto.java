package com.moviesreview.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ResponseDto",description = "Schema to hold success response information")
public record ResponseDto(
        @Schema(description = "Status code in the description")
        String statusCode,
        @Schema(description = "Status message in the description")
        String statusMessage
) {}

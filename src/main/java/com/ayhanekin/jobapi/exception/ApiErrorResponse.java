package com.ayhanekin.jobapi.exception;

import lombok.*;


// Blueprint of api error response format.

@Getter
@Builder
public class ApiErrorResponse {

    private String timestamp;
    private int status;
    private ErrorCode error;
    private String message;
    private String path;

}

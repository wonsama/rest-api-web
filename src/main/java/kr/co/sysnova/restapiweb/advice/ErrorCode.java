package kr.co.sysnova.restapiweb.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    UserNotFound(5000, "user not found");

    private int code;
    private String msg;
}

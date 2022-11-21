package kr.co.sysnova.restapiweb.advice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import kr.co.sysnova.restapiweb.advice.exception.UserNotFoundException;
import kr.co.sysnova.restapiweb.response.CommonResult;
import kr.co.sysnova.restapiweb.service.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    private final ResponseService responseService;

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // INTERNAL_SERVER_ERROR(500) 상태로 설정한다
    protected CommonResult userNotFoundException(HttpServletRequest request, Exception e) {
        // 오류 응답정보를 보내준다
        log.info("user not found exception occured");
        return responseService.getFailResult();
    }

    @ExceptionHandler(Exception.class) // Exception 이 발생하면
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // INTERNAL_SERVER_ERROR(500) 상태로 설정한다
    protected CommonResult defaultException(HttpServletRequest request, Exception e) {
        // 오류 응답정보를 보내준다
        log.info("exception occured");
        return responseService.getFailResult();
    }

}

package kr.co.sysnova.restapiweb.advice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import kr.co.sysnova.restapiweb.advice.exception.UserNotFoundException;
import kr.co.sysnova.restapiweb.model.response.CommonResult;
import kr.co.sysnova.restapiweb.service.ResponseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    private final ResponseService responseService;
    private final MessageSource messageSource;

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // INTERNAL_SERVER_ERROR(500) 상태로 설정한다
    protected CommonResult userNotFoundException(HttpServletRequest request, Exception e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("userNotFound.code")),
                getMessage("userNotFound.msg"));
    }

    @ExceptionHandler(Exception.class) // Exception 이 발생하면
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // INTERNAL_SERVER_ERROR(500) 상태로 설정한다
    protected CommonResult defaultException(HttpServletRequest request, Exception e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("unKnown.code")),
                getMessage("unKnown.msg"));
    }

    private String getMessage(String code) {
        return getMessage(code, null);
    }

    private String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}

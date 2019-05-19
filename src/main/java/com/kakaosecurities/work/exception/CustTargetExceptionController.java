package com.kakaosecurities.work.exception;

import com.kakaosecurities.work.vo.ErrorVO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *  모든 Exception 및 Custom Exception 의 중앙 Handling
 */
@ControllerAdvice
public class CustTargetExceptionController {

    @ExceptionHandler(CustTargetException.class)
    @ResponseBody
    public ErrorVO handleCustTargetException(CustTargetException e){

        ErrorVO error = new ErrorVO();

        // Custom Exception
        if(e instanceof CustTargetException) {
            error = new ErrorVO();
            error.setCode(((CustTargetException) e).getCode());
            error.setMsg(((CustTargetException) e).getMsg());
        }else {
            error = new ErrorVO();
            error.setCode("500");
            error.setMsg("Error 발생");
        }

        return error;
    }

}

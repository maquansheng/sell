package com.imooc.handler;

import com.imooc.VO.ResultVO;
import com.imooc.exception.ResponseBankException;
import com.imooc.exception.SellException;
import com.imooc.exception.SellerAuthorizeException;
import com.imooc.utils.ResultVOUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SellExceptionHandler {
    /*
    拦截登入异常
     */
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException(){
        return new ModelAndView("common/error");
    }

    //
    @ExceptionHandler(value=SellException.class)
    public ResultVO handerSellException(SellException e){
        return ResultVOUtil.error(e.getCode(),e.getMessage());
    }

    //
//    @ExceptionHandler(value = ResponseBankException.class)
//    @ResponseStatus()
//    public void handBankException(){
//
//    }
}

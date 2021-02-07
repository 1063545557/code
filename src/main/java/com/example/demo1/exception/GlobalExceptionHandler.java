package com.example.demo1.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.awt.SunHints;

import java.io.UnsupportedEncodingException;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    //@ExceptionHandler(value = Exception.class)
    @ExceptionHandler(value = UnsupportedEncodingException.class)
    @ResponseBody
    public String UnsupportedEncodingExceptionHandler(Exception e){
        System.out.println("程序异常:"+e.toString());
        return "UnsupportedEncodingException";
    }
}

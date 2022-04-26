package org.lufengxue.exception.global;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.lufengxue.enums.StatusCode;
import org.lufengxue.response.ResponseEnum;
import org.lufengxue.response.Result;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;



@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler( CustomException.class)
    public Result businessExceptionHandler(HttpServletRequest request, CustomException e){
        exceptionLog(request,e);
        if (Result.getCode().equals(e.getCode()) || Result.getCode().equals(e.getCode())) {
            Result baseResponse = new Result();
            baseResponse.setCode(e.getCode());
            baseResponse.setMessage(e.getMessage());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", e.getCode());
            jsonObject.put("message", e.getMessage());
            baseResponse.setData(jsonObject);
            return baseResponse;
        }
        Result result = new Result();
        result.setCode(e.getCode());
        result.setMessage(e.getMessage());
        return result;
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Result methodArgumentNotValidExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException e){
        exceptionLog(request,e);
        StringBuilder sb = new StringBuilder();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            sb.append("{").append(fieldError.getDefaultMessage()).append("}，");
        }
        Result result = new Result();
        result.setCode(400);
        result.setMessage(sb.toString().substring(0,sb.length() - 1));
        return result;
    }

    @ExceptionHandler(BusinessCodeException.class)
    public Result businessCodeExceptionHandler(HttpServletRequest request, BusinessCodeException e){
        log.error("请求失败：uri[" + request.getRequestURI() + "],code:" + JSONObject.toJSONString(e.getCode()),e);
        return new  Result( false,e.getCode() , e.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public Result businessExceptionHandler(HttpServletRequest request, BusinessException e){
        exceptionLog(request,e);
        return new  Result( false,e.getCode() , e.getMessage());
    }

    @ExceptionHandler({Exception.class})
    public Result exceptionHandler(HttpServletRequest request, Exception e){
        exceptionLog(request,e);
        Result baseResponse = new Result();
        baseResponse.setMessage(e.getMessage());
        return baseResponse;
    }

    public static void exceptionLog(HttpServletRequest request, Exception e){
        log.error("请求异常：uri[" + request.getRequestURI() + "]" ,e);
    }

}

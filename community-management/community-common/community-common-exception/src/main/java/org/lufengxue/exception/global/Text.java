package org.lufengxue.exception.global;


import org.lufengxue.response.ThirdException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author : Allen
 * @date : 2022/04/27 0:49
 * @desc : 异常类
 */

public class Text extends RuntimeException {

    public Text(String msg) {
        super(msg);
    }

    public Text(String msg, Exception e) {
        super(msg,e);
    }

    public Text(Exception e) {
        super(e);
    }
}

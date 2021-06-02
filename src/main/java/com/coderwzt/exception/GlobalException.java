package com.coderwzt.exception;

import com.coderwzt.util.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @Author: coderwzt
 * @Date: 2020/12/5 20:41
 * @Version 1.0
 */
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R handlerSQLException(SQLIntegrityConstraintViolationException exception) {

        return R.fail("业主姓名不能重复，请重新输入");
    }

}

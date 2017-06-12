package com.huaao.sunejwapi.api.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huaao.sunejwapi.api.dto.pub.DtoPublicResponse;
import com.huaao.sunejwapi.api.dto.pub.DtoValidationError;
import com.huaao.sunejwapi.common.Constants;
import com.huaao.sunejwapi.common.util.FuncTool;
import com.huaao.sunejwapi.common.web.SystemException;
 
@ControllerAdvice
public class SystemExceptionHandler {
	@Autowired
	private HttpServletRequest request;
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 输入参数不合法异常
	 */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public DtoValidationError processValidationError(BindException ex) {
         BindingResult result = ex.getBindingResult();
         List<FieldError> fieldErrors = result.getFieldErrors();
         DtoValidationError dto = new DtoValidationError();
         for (FieldError fieldError: fieldErrors) {
         	dto.addFieldError(fieldError.getField(),fieldError.getDefaultMessage());
         }
         return dto;
    }
    
    /**
	 * 系统自定义异常
	 */
    @ExceptionHandler(SystemException.class)
    @ResponseBody
    public DtoPublicResponse processSystemError(SystemException ex){
    	return DtoPublicResponse.fail(ex.getErrorCode(), ex.getMessage());
    }
    
    /**
	 * 数据库SQL异常
	 */
    @ExceptionHandler(BadSqlGrammarException.class)
    @ResponseBody
    public DtoPublicResponse processSqlError(BadSqlGrammarException ex){
    	log.error(String.valueOf(request.getAttribute(Constants.REQUEST_ID)), ex);
    	request.setAttribute(Constants.ERROR_TRACE, FuncTool.getErrorInfoFromException(ex));
    	return DtoPublicResponse.fail(Constants.API_CODE_SQL_GRAMMAR, "数据库访问异常");
    }
    
    /**
   	 * 未知异常
   	 */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public DtoPublicResponse processError(Exception ex){
    	log.error(String.valueOf(request.getAttribute(Constants.REQUEST_ID)), ex);
    	request.setAttribute(Constants.ERROR_TRACE, FuncTool.getErrorInfoFromException(ex));
    	String msg = ex.getMessage();
    	if(msg == null){
    		msg = "未知异常";
    	}
    	return DtoPublicResponse.fail(Constants.API_CODE_FAIL, msg);
    }
    
}

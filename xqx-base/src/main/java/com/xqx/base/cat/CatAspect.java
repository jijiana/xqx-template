package com.xqx.base.cat;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import com.xqx.base.exception.BaseException;
import com.xqx.base.exception.ErrorCode;
import com.xqx.base.vo.ResponseMessage;

//@Aspect
//@Component
public class CatAspect {
	private Logger logger = LoggerFactory.getLogger(CatAspect.class);

	@Around(value = "execution( com.xqx..* *(..))")
	public Object catFallbackOption(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("============class name = "+pjp.getTarget().getClass().getName());
		for(Object obj:pjp.getArgs()) {
			System.out.println(obj);
		}
		Object result = pjp.proceed();
		return result;
	}
	
//	@Around(value = "@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public Object catTransactionProcesss(ProceedingJoinPoint pjp) {
		try {
			Object result = pjp.proceed();
			return result;
		} catch (BaseException e) {
			Cat.logEvent("BaseException", e.getClass().getName(), e.getErrorCode().getCode() + "", e.getErrMsg());
			Transaction t = Cat.newTransaction("BaseException", e.getClass().getName());
			t.setStatus(e);
			t.addData("msg", e.getErrMsg());
			t.complete();
			logger.error("自定义异常", e);
			return ResponseMessage.fail(e);
		} catch (Throwable e) {
			Cat.logEvent("Throwable", e.getClass().getName(), "500", e.getMessage());
			Transaction t = Cat.newTransaction("Throwable", e.getClass().getName());
			t.setStatus(e);
			t.addData("msg", e.getMessage());
			t.complete();
			logger.error("未知异常", e);
			return ResponseMessage.fail(ErrorCode.UNKNOWN_ERROR.getCode(), ErrorCode.UNKNOWN_ERROR.getDescription());
		}
	}
}
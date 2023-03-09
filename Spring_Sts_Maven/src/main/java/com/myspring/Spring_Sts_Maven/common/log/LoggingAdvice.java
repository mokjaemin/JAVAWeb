package com.myspring.Spring_Sts_Maven.common.log;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

// Component Annotation 으로 빈 생성
@Component
// AOP로 사용할 것을 정의
@Aspect
public class LoggingAdvice {
	private static final Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);

	// target 메서드의 파라미터 정보를 알아낸다
	// Before -> 메서드 호출전 출력하라
	// 메서드 호출 전에 startLog 실행 됨
	// 여기서는 pointcut으로 execution 사용됨 (종류 : bean, within)
	/*@Before("execution(* com.myspring.Spring_Sts_Maven.member.service.*.*(..)) or "
			+ "execution(* com.myspring.Spring_Sts_Maven.member.dao.*.*(..))")*/
	// service 클래스 안, dao 클래스 안의 메서드에 대해서
	// @Before("execution(* com.myspring.Spring_Sts_Maven.*.service.*.*(..)) or "
	//		+ "execution(* com.myspring.Spring_Sts_Maven.*.dao.*.*(..))")
	public void startLog(JoinPoint jp) {
		// JointPoint의 메서드를 이용

		logger.info("-------------------------------------");
		logger.info("-------------------------------------");

		// 전달되는 모든 파라미터들을 Object의 배열로 가져온다.
		logger.info("1:" + Arrays.toString(jp.getArgs()));

		// 해당 Advice의 타입을 알아낸다.
		logger.info("2:" + jp.getKind());

		// 실행하는 대상 객체의 메서드에 대한 정보를 알아낼 때 사용한다.
		logger.info("3:" + jp.getSignature().getName());

		// target 객체를 알아낼 때 사용한다.
		logger.info("4:" + jp.getTarget().toString());

		// Advice를 행하는 객체를 알아낼 때 사용한다.
		logger.info("5:" + jp.getThis().toString());

	}
	
	
	// After -> 메서드 호출 후 출력한다.
	/*@After("execution(* com.myspring.pro27.member.service.*.*(..)) or "
			+ "execution(* com.myspring.pro27.member.dao.*.*(..))")*/ 
	// @After("execution(* com.myspring.pro27.*.service.*.*(..)) or "
	//		+ "execution(* com.myspring.pro27.*.dao.*.*(..))")
	public void after(JoinPoint jp) { 
		logger.info("-------------------------------------");
		logger.info("-------------------------------------");

		// 전달되는 모든 파라미터들을 Object의 배열로 가져온다.
		logger.info("1:" + Arrays.toString(jp.getArgs()));

		// 해당 Advice의 타입을 알아낸다.
		logger.info("2:" + jp.getKind());

		// 실행하는 대상 객체의 메서드에 대한 정보를 알아낼 때 사용한다.
		// 객체가 클래스이면 해당하는 메서드의 이름 출력
		logger.info("3:" + jp.getSignature().getName());

		// target 객체를 알아낼 때 사용한다.
		// target이 클래스이면 클래스명 출력
		logger.info("4:" + jp.getTarget().toString());

		// Advice를 행하는 객체를 알아낼 때 사용한다.
		logger.info("5:" + jp.getThis().toString());
	
	}


	// Around -> 메서드 호출 전 후 모두 출력 = Before + After
	// 아래의 proceed 를 기준으로 상단 - 메서드 호출 전, 하단 - 메서드 호출 후
	// target 메서드의 동작 시간을 측정한다.
	/*@Around("execution(* com.myspring.Spring_Sts_Maven.member.service.*.*(..)) or "
			+ "execution(* com.myspring.Spring_Sts_Maven.member.dao.*.*(..))")*/
	@Around("execution(* com.myspring.Spring_Sts_Maven.*.service.*.*(..)) or "
			+ "execution(* com.myspring.Spring_Sts_Maven.*.dao.*.*(..))")
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
		long startTime = System.currentTimeMillis();
		logger.info(Arrays.toString(pjp.getArgs()));

		// 실제 타겟(클래스)이 실행되는 부분, 이 부분이 없으면 advice가 적용된 메서드가 동작하지 않는다.
		Object result = pjp.proceed(); // proceed는 Exception 보다 상위 Throwable을 처리한다.

		long endTime = System.currentTimeMillis();
		// Target 메서드의 동작 시간을 출력한다.
		logger.info(pjp.getSignature().getName() + " : " + (endTime - startTime)); 
		logger.info("==============================");

		// Around를 사용할 경우 Object를 리턴해야 한다.
		return result;
	}

}
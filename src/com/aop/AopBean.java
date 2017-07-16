package com.aop;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//ָ����������ȼ������ж������ʱ����ֵԽС���ȼ�Խ��
@Order(1)
// �����������Ϊһ�����棺��Ҫ�Ѹ�����뵽IOC�����С�������Ϊһ������.
@Aspect
@Component
public class AopBean {
	

	/*
	 * ���⹫��������ִ�У�
	 * 
	 * execution(public * *(..))
	 * 
	 * �κ�һ���ԡ�set����ʼ�ķ�����ִ�У�
	 * 
	 * execution(* set*(..))
	 * 
	 * AccountService �ӿڵ����ⷽ����ִ�У�
	 * 
	 * execution(* com.xyz.service.AccountService.*(..))
	 * 
	 * ������service��������ⷽ����ִ�У�
	 * 
	 * execution(* com.xyz.service.*.*(..))
	 * 
	 * ������service���������Ӱ��������������ⷽ����ִ�У�
	 * 
	 * execution(* com.xyz.service..*.*(..))
	 * 
	 * ������pointcutexp���������Ӱ����JoinPointObjP2������ⷽ����ִ�У�
	 * 
	 * execution(* com.test.spring.aop.pointcutexp..JoinPointObjP2.*(..))")
	 *** 
	 * > ���(..)��Ϊ������,����.*(..))��Ϊ�������߽ӿ���,��������JoinPointObjP2.*(..))
	 */
	/**
	 * �����������ʽ��һ���ڸ÷����в�������������롣 ʹ��@Pointcut�������������ʽ�� �����ֱ֪ͨ��ʹ�÷����������õ�ǰ���������ʽ��
	 */
	//@Pointcut("execution(public int com.spring.aop.impl.ArithmeticCalculator.*(..))")
	@Pointcut("execution(* com.service.*.*(..))")
	public void declareJoinPointExpression() {}

	@Before("declareJoinPointExpression()")
	// ����ʹ���������ʽ���ɡ�����Ŀ��Զ��ĳ��������ʽ���������������ʽ�ڱ�İ��У���ǰ����ϰ������������ɡ�
	public void beforeMethod(JoinPoint joinpoint) {
		String methodName = joinpoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinpoint.getArgs());
		System.out.println("ǰ��֪ͨ��The method " + methodName + " begins with " + args);
	}

	/**
	 * ����֪ͨ����Ŀ�귽��ִ��֮��ʼִ�У�����Ŀ�귽���Ƿ��׳��쳣�� �ں���֪ͨ�в��ܷ���Ŀ�귽��ִ�еĽ����
	 * 
	 * @param joinpoint
	 */
	//@After("execution(public int com.spring.aop.impl.ArithmeticCalculator.*(int, int))")
	@After("execution(* com.service.*.*(..))")
	public void afterMethod(JoinPoint joinpoint) {
		String methodName = joinpoint.getSignature().getName();
		// List<Object>args = Arrays.asList(joinpoint.getArgs()); ����֪ͨ�����п��Ի�ȡ������
		System.out.println("����֪ͨ��The method " + methodName + " ends ");
	}

	/**
	 * ����֪ͨ���ڷ�����������֮��ִ�С� ���Է��ʵ������ķ���ֵ��
	 * 
	 * @param joinpoint
	 * @param result
	 *            Ŀ�귽���ķ���ֵ
	 */
	@AfterReturning(value = "execution(* com.service.*.*(..))", returning = "result")
	public void afterReturnning(JoinPoint joinpoint, Object result) {
		String methodName = joinpoint.getSignature().getName();
		System.out.println("����֪ͨ��The method " + methodName + " ends with " + result);
	}

	/**
	 * �쳣֪ͨ��Ŀ�귽�������쳣��ʱ��ִ�У����Է��ʵ��쳣���󣬿���ָ���ڳ����ض��쳣ʱ��ִ�С�
	 * ����Ѳ���д��NullPointerException��ֻ�ڳ��ֿ�ָ���쳣��ʱ��ִ�С�
	 * 
	 * @param joinpoint
	 * @param e
	 */
	//@AfterThrowing(value = "execution(public int com.spring.aop.impl.ArithmeticCalculator.*(..))", throwing = "e")
	@AfterThrowing(value = "execution(* com.service.*.*(..))", throwing = "e")
	public void afterThrowing(JoinPoint joinpoint, Exception e) {
		String methodName = joinpoint.getSignature().getName();
		System.out.println("�쳣֪ͨ��The method " + methodName + " occurs exception " + e);
	}

	/**
	 * ����֪ͨ�����ڶ�̬�����ȫ���̣�ProceedingJoinPoint���͵Ĳ������Ծ����Ƿ�ִ��Ŀ�귽����
	 * 
	 * @param point
	 *            ����֪ͨ��ҪЯ��ProceedingJoinPoint���͵Ĳ�����
	 * @return Ŀ�귽���ķ���ֵ�������з���ֵ��
	 */
	/*
	 * ������
	 * 
	 * @Around(
	 * "execution(public int com.spring.aop.impl.ArithmeticCalculator.*(..))")
	 * public Object aroundMethod(ProceedingJoinPoint point) { Object result =
	 * null; String methodName = point.getSignature().getName(); try { //ǰ��֪ͨ
	 * System.out.println("The method "+ methodName +" begins with " +
	 * Arrays.asList(point.getArgs())); //ִ��Ŀ�귽�� result = point.proceed();
	 * //����֪ͨ System.out.println("The method "+ methodName +" ends with " +
	 * result); } catch (Throwable e) { //�쳣֪ͨ System.out.println("The method "+
	 * methodName +" occurs exception " + e); throw new RuntimeException(e); }
	 * //����֪ͨ System.out.println("The method "+ methodName +" ends"); return
	 * result; }
	 */
}

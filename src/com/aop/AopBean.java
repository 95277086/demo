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

//指定切面的优先级，当有多个切面时，数值越小优先级越高
@Order(1)
// 把这个类声明为一个切面：需要把该类放入到IOC容器中。再声明为一个切面.
@Aspect
@Component
public class AopBean {
	

	/*
	 * 任意公共方法的执行：
	 * 
	 * execution(public * *(..))
	 * 
	 * 任何一个以“set”开始的方法的执行：
	 * 
	 * execution(* set*(..))
	 * 
	 * AccountService 接口的任意方法的执行：
	 * 
	 * execution(* com.xyz.service.AccountService.*(..))
	 * 
	 * 定义在service包里的任意方法的执行：
	 * 
	 * execution(* com.xyz.service.*.*(..))
	 * 
	 * 定义在service包和所有子包里的任意类的任意方法的执行：
	 * 
	 * execution(* com.xyz.service..*.*(..))
	 * 
	 * 定义在pointcutexp包和所有子包里的JoinPointObjP2类的任意方法的执行：
	 * 
	 * execution(* com.test.spring.aop.pointcutexp..JoinPointObjP2.*(..))")
	 *** 
	 * > 最靠近(..)的为方法名,靠近.*(..))的为类名或者接口名,如上例的JoinPointObjP2.*(..))
	 */
	/**
	 * 声明切入点表达式，一般在该方法中不再添加其他代码。 使用@Pointcut来声明切入点表达式。 后面的通知直接使用方法名来引用当前的切入点表达式。
	 */
	//@Pointcut("execution(public int com.spring.aop.impl.ArithmeticCalculator.*(..))")
	@Pointcut("execution(* com.service.*.*(..))")
	public void declareJoinPointExpression() {}

	@Before("declareJoinPointExpression()")
	// 这里使用切入点表达式即可。后面的可以都改成切入点表达式。如果这个切入点表达式在别的包中，在前面加上包名和类名即可。
	public void beforeMethod(JoinPoint joinpoint) {
		String methodName = joinpoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinpoint.getArgs());
		System.out.println("前置通知：The method " + methodName + " begins with " + args);
	}

	/**
	 * 后置通知，在目标方法执行之后开始执行，无论目标方法是否抛出异常。 在后置通知中不能访问目标方法执行的结果。
	 * 
	 * @param joinpoint
	 */
	//@After("execution(public int com.spring.aop.impl.ArithmeticCalculator.*(int, int))")
	@After("execution(* com.service.*.*(..))")
	public void afterMethod(JoinPoint joinpoint) {
		String methodName = joinpoint.getSignature().getName();
		// List<Object>args = Arrays.asList(joinpoint.getArgs()); 后置通知方法中可以获取到参数
		System.out.println("后置通知：The method " + methodName + " ends ");
	}

	/**
	 * 返回通知，在方法正常结束之后执行。 可以访问到方法的返回值。
	 * 
	 * @param joinpoint
	 * @param result
	 *            目标方法的返回值
	 */
	@AfterReturning(value = "execution(* com.service.*.*(..))", returning = "result")
	public void afterReturnning(JoinPoint joinpoint, Object result) {
		String methodName = joinpoint.getSignature().getName();
		System.out.println("返回通知：The method " + methodName + " ends with " + result);
	}

	/**
	 * 异常通知。目标方法出现异常的时候执行，可以访问到异常对象，可以指定在出现特定异常时才执行。
	 * 假如把参数写成NullPointerException则只在出现空指针异常的时候执行。
	 * 
	 * @param joinpoint
	 * @param e
	 */
	//@AfterThrowing(value = "execution(public int com.spring.aop.impl.ArithmeticCalculator.*(..))", throwing = "e")
	@AfterThrowing(value = "execution(* com.service.*.*(..))", throwing = "e")
	public void afterThrowing(JoinPoint joinpoint, Exception e) {
		String methodName = joinpoint.getSignature().getName();
		System.out.println("异常通知：The method " + methodName + " occurs exception " + e);
	}

	/**
	 * 环绕通知类似于动态代理的全过程，ProceedingJoinPoint类型的参数可以决定是否执行目标方法。
	 * 
	 * @param point
	 *            环绕通知需要携带ProceedingJoinPoint类型的参数。
	 * @return 目标方法的返回值。必须有返回值。
	 */
	/*
	 * 不常用
	 * 
	 * @Around(
	 * "execution(public int com.spring.aop.impl.ArithmeticCalculator.*(..))")
	 * public Object aroundMethod(ProceedingJoinPoint point) { Object result =
	 * null; String methodName = point.getSignature().getName(); try { //前置通知
	 * System.out.println("The method "+ methodName +" begins with " +
	 * Arrays.asList(point.getArgs())); //执行目标方法 result = point.proceed();
	 * //翻译通知 System.out.println("The method "+ methodName +" ends with " +
	 * result); } catch (Throwable e) { //异常通知 System.out.println("The method "+
	 * methodName +" occurs exception " + e); throw new RuntimeException(e); }
	 * //后置通知 System.out.println("The method "+ methodName +" ends"); return
	 * result; }
	 */
}

/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-09 05:52:25
 * @modify date 2021-01-09 05:52:25
 * @desc [description]
 */
package com.cg.gatewayserver.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomLogger {

  @Pointcut("execution(* com.cg.*.*.*.*(..)))")
  public void everyWhere() {
  }

  @Before("everyWhere()")
  public void logBefore(JoinPoint point) {
    log.debug("@Before class: " + point.getTarget().getClass().getSimpleName());
    log.debug("@Before method: " + point.getSignature().toShortString());
    log.debug("Arguments: ");
    Object[] objects = point.getArgs();
    for (Object object : objects) {
      log.debug("->" + object);
    }
  }

  @AfterReturning(pointcut = "everyWhere()", returning = "result")
  public void logAfter(JoinPoint point, Object result) {
    log.debug("@AfterReturning class: " + point.getTarget().getClass().getSimpleName());
    log.debug("@AfterReturning method: " + point.getSignature().toShortString());
    log.debug("Returning Value: " + result);
  }

}
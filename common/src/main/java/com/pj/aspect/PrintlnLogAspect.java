package com.pj.aspect;



import com.pj.annotation.PrintlnLog;
import com.pj.utils.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @version 1.0
 * @description 打印入参Aspect
 * @date 2024/1/17 16:31:41
 */
@Slf4j
@Aspect
@Component
//@Profile({"dev"})
public class PrintlnLogAspect {

    private static final ThreadLocal<Long> timeThreadLocal = new ThreadLocal<>();

    @Pointcut(value = "@annotation(logAnnotation)", argNames = "logAnnotation")
    public void pointcut(PrintlnLog logAnnotation) {
    }

    @Before(value = "pointcut(logAnnotation)", argNames = "joinPoint,logAnnotation")
    public void doBefore(JoinPoint joinPoint, PrintlnLog logAnnotation) throws Throwable {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String methodDetailDescription = logAnnotation.description();
        //如果description为空，使用类名+方法名
        if (methodDetailDescription == null || methodDetailDescription.isEmpty()) {
            methodDetailDescription = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        }
        log.info("------------------------------- start --------------------------");
        log.info("Method detail Description: {}", methodDetailDescription);
        log.info("Request Args: {}", JacksonUtil.writeValueAsString(joinPoint.getArgs()));
        if (logAnnotation.executionTime()) {
            timeThreadLocal.set(System.currentTimeMillis());
        }
    }

    @AfterReturning(pointcut = "pointcut(logAnnotation)", returning = "retVal", argNames = "joinPoint,logAnnotation,retVal")
    public void doAfterReturning(JoinPoint joinPoint, PrintlnLog logAnnotation, Object retVal) throws Throwable {
        String description = logAnnotation.description();
        //如果description为空，使用类名+方法名
        if (description == null || description.isEmpty()) {
            description = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        }
        if (logAnnotation.returnValue()) {
            if (retVal != null) {
                log.info("Return Value of Method [{}]: {}", description, JacksonUtil.writeValueAsString(retVal));
            } else {
                log.info("Method [{}] is void type.", description);
            }
        }

        if (logAnnotation.executionTime()) {
            long startTime = timeThreadLocal.get();
            log.info("Execution Time of Method [{}]: {} ms", description, System.currentTimeMillis() - startTime);
            timeThreadLocal.remove();
        }
    }
}

package com.service.employee.aop;

import com.service.employee.common.SnackRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Aspect
@Component
public class SnakRequestAspect {

    @Inject
    private HttpServletRequest request;

    @Before("execution(* *(.., @com.service.employee.common.SnackRequest (*), ..))")
    public void handleSnackRequest(JoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        Object[] args = joinPoint.getArgs();
        Parameter[] parameters = method.getParameters();

        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].isAnnotationPresent(SnackRequest.class)) {
                Class<?> paramType = parameters[i].getType();
                Object instance = paramType.getDeclaredConstructor().newInstance();
                Field[] fields = paramType.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    String paramName = field.getName();
                    String paramValue = request.getParameter(paramName);
                    if (paramValue != null) {
                        field.set(instance, convertValue(field.getType(), paramValue));
                    }
                }
                args[i] = instance;
            }
        }

        System.arraycopy(args, 0, joinPoint.getArgs(), 0, args.length);
    }

    private Object convertValue(Class<?> targetType, String value) {
        if (targetType == String.class) {
            return value;
        } else if (targetType == int.class || targetType == Integer.class) {
            return Integer.parseInt(value);
        } else if (targetType == long.class || targetType == Long.class) {
            return Long.parseLong(value);
        } else if (targetType == double.class || targetType == Double.class) {
            return Double.parseDouble(value);
        } else if (targetType == boolean.class || targetType == Boolean.class) {
            return Boolean.parseBoolean(value);
        }
        // Add more type conversions as needed
        return null;
    }
}

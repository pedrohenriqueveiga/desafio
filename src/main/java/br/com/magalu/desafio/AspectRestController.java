package br.com.magalu.desafio;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Pedro Henrique Veiga
 * @created 05/06/24
 * @lastModified 05/06/24
 */

@Aspect
@Component
public class AspectRestController {

    @Before("execution(public * br.com.magalu.desafio.api.resource.*Controller.*(..))")
    public void logBeforeRestCall(JoinPoint pjp) throws Throwable {
        System.out.println(new Date() + " - :::::  AOP Before REST public ::::: -> " + pjp);
    }

}

package com.example.AccomodationReservationManagementMvc.service;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Aspect
@Component
public final class ReservationManagerServiceAspect {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ReservationManagerServiceAspect.class);
    private static final String msgAspectName = "***AspectJ Earl Wilkerson AddressBook***";
    private static final String msgAspect = "          " + msgAspectName + " %s is running!! intercepted : %s";

    public ReservationManagerServiceAspect(){}

    @Before("execution(* com.example.AccomodationReservationManagementMvc.service.ReservationManagerService.addAccount(..))")
    public void doBefore(JoinPoint joinPoint) {

        String msgAspectDoBefore = String.format(msgAspect, "DoBefore()",  joinPoint.getSignature().getName());
        logger.info(msgAspectDoBefore);
    }

    @After("execution(* com.example.AccomodationReservationManagementMvc.service.ReservationManagerService.addAccount(..))")
    public void doAfter(JoinPoint joinPoint) {

        String msgAspectDoAfter = String.format(msgAspect, "DoAfter()",  joinPoint.getSignature().getName());
        logger.info(msgAspectDoAfter);
    }

    @AfterReturning(
    pointcut = "execution(* com.example.AccomodationReservationManagementMvc.service.ReservationManagerService.*(..))", returning= "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {

        String msgAspectDoAfterReturning = String.format(msgAspect, "DoAfterReturning()",  joinPoint.getSignature().getName());
        logger.info(msgAspectDoAfterReturning);

        String msgAspectResult = String.format("Method void value is : %s", result);
        logger.info(msgAspectResult);
    }


    @AfterThrowing(
    pointcut = "execution(* com.example.AccomodationReservationManagementMvc.service.ReservationManagerService.throwingReservationManagerService())",
    throwing= "error")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable error) {

        String msgAspectDoAfterThrowing = String.format(msgAspect, "DoAfterThrowing()",  joinPoint.getSignature().getName());
        logger.error(msgAspectDoAfterThrowing);

        String msgAspectError = String.format("          Exception : %s", error);
        String msgAspectError2 = "          ******";
        logger.error(msgAspectError);
        logger.error(msgAspectError2);
    }

    @Pointcut("execution(* com.example.AccomodationReservationManagementMvc.service.ReservationManagerService.updateReservation(..))")
    public void doPointCut() {}

    @Around("doPointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {

        String msgAspectDoAround = String.format(msgAspect, "DoAround()",  joinPoint.getSignature().getName());
        String msgAspectArray = String.format("%n       arguments : %s", Arrays.toString(joinPoint.getArgs()));
        String msgAspectDoAroundArray = msgAspectDoAround + msgAspectArray;
        logger.info(msgAspectDoAroundArray);

        String msgAspectDoAroundBeforeAfter = "                " + msgAspectName + " DoAround() %s is running!";
        String msgAspectDoAroundBefore = String.format(msgAspectDoAroundBeforeAfter, "before");
        logger.info(msgAspectDoAroundBefore);

        Object object =  joinPoint.proceed(); // continue on the intercepted method

        String msgAspectDoAroundAfter = String.format(msgAspectDoAroundBeforeAfter, "after");
        logger.info(msgAspectDoAroundAfter);

        return object;
    }

}

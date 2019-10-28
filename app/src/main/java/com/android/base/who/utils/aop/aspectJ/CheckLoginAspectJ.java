package com.android.base.who.utils.aop.aspectJ;

import android.text.TextUtils;
import android.util.Log;

import com.android.base.who.utils.aop.annotation.CheckLogin;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

@Aspect
public class CheckLoginAspectJ {

    @Around("execution(@com.android.base.who.utils.aop.annotation.CheckLogin * *(..))")
    public void isLogin(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        //通过method对象得到切点上的注解
        CheckLogin annotation = method.getAnnotation(CheckLogin.class);
        String value = annotation.value();
        //判断是否登入
        if(TextUtils.isEmpty(value)){
            Log.e("Before=","没有登入，请先登入");
        }else {
            Log.e("Before=","已经登入");
            joinPoint.proceed();
        }
    }

}

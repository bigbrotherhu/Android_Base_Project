package com.android.base.who.utils.aop.aspectJ;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;


import com.android.base.who.utils.aop.annotation.CheckPermissions;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

@Aspect
public class CheckPermissionsAspectJ {

    @Around("execution(@com.android.base.who.utils.aop.annotation.CheckPermissions * *(..))")
    public void requestPermissions(ProceedingJoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        CheckPermissions checkPermissions = method.getAnnotation(CheckPermissions.class);
        String[] permissions = checkPermissions.value();

        Object target = joinPoint.getTarget();
        FragmentActivity activity = null;
        if (target instanceof Activity){
            activity = (FragmentActivity) target;
        }else if (target instanceof Fragment){
            activity = ((Fragment)target).getActivity();
        }
        if(activity != null){
            new RxPermissions(activity).request(permissions)
                    .subscribe(new Observer<Boolean>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }
                        @Override
                        public void onNext(Boolean aBoolean) {
                            if(aBoolean){
                                try {
                                    joinPoint.proceed();
                                } catch (Throwable throwable) {
                                    throwable.printStackTrace();
                                    Log.e("requestPermissions","申请权限失败");
                                }
                            }else {
                                Log.e("requestPermissions","申请权限失败");
                            }
                        }
                        @Override
                        public void onError(Throwable e) {
                            Log.e("requestPermissions","申请权限失败");
                        }
                        @Override
                        public void onComplete() {
                        }
                    });
        }else {
            Log.e("requestPermissions","申请权限的时机有错");
        }
    }

}

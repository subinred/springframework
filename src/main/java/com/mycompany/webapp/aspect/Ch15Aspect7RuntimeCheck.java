package com.mycompany.webapp.aspect;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD)//annotation 생성 시 Target 지정 Source-컴파일 할 때(ex-override), Class-로딩할 때(Controller), Runtime-힙 영역에 하고 싶을때 
public @interface Ch15Aspect7RuntimeCheck {

}

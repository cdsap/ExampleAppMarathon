package com.kaptwithannotationlevel.myapplication

import java.lang.annotation.ElementType
import java.lang.annotation.Retention


import java.lang.annotation.Retention

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE, ElementType.METHOD)
annotation class AnnotationB
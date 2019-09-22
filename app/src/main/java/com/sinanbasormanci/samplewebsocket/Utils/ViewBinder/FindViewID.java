package com.sinanbasormanci.samplewebsocket.Utils.ViewBinder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by sinanbasormanci.com on 2019-09-21.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FindViewID {
    int value() default 0;
}

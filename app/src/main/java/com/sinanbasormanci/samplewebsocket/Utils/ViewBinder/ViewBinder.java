package com.sinanbasormanci.samplewebsocket.Utils.ViewBinder;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;

/**
 * Created by sinanbasormanci.com on 2019-09-21.
 */
public class ViewBinder {

    public static void bind(Activity activity) {
        for (Field field : activity.getClass().getDeclaredFields()) {
            FindViewID annotation = field.getAnnotation(FindViewID.class);

            if (annotation != null) {
                field.setAccessible(true);
                try {
                    field.set(activity, activity.findViewById(annotation.value()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void bindView(Object target, View view) {
        Class<?> targetClass = target.getClass();
        for (Field field : targetClass.getDeclaredFields()) {
            FindViewID annotation = field.getAnnotation(FindViewID.class);

            if (annotation != null) {
                field.setAccessible(true);
                try {
                    field.set(target, view.findViewById(annotation.value()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
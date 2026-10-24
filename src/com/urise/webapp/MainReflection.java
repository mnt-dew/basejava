package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    static void callToStringMethodFromObject(Object object) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method toStringMethod = object.getClass().getMethod("toString");
        System.out.println(toStringMethod.invoke(object));
    }

    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume resume = new Resume();
        callToStringMethodFromObject(resume);
    }
}

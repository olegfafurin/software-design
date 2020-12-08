package org.imd.sd.aspect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    @SuppressWarnings("JavaReflectionInvocation")
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.setProperty("profiledPackage", args[1]);
        Class<?> cls = Class.forName(args[0]);
        Method m = cls.getMethod("main", String[].class);
        String[] param = null;
        m.invoke(null, (Object) null);

        ProfilingManager.getInstance().printStat();
    }
}

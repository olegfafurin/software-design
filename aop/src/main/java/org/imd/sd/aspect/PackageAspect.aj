package org.imd.sd.aspect;

import org.aspectj.lang.Signature;

public aspect PackageAspect {

    pointcut everyMethod(): execution(* *.*(..)) &&
            if(thisJoinPointStaticPart.getSignature().getDeclaringType().getPackage().toString().startsWith("package " + System.getProperty("profiledPackage")));


    Object around(): everyMethod() {
        Signature s = thisEnclosingJoinPointStaticPart.getSignature();
        String methodName = s.getDeclaringTypeName() + "." + s.getName();
        long methodStartedTime = System.nanoTime();
        Object result = proceed();
        long elapsed = System.nanoTime() - methodStartedTime;
        ProfilingManager.getInstance().incMethod(methodName, elapsed);
        return result;
    }

}

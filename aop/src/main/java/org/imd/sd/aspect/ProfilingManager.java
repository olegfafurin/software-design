package org.imd.sd.aspect;

import java.util.HashMap;
import java.util.stream.Collectors;

public class ProfilingManager {

    public static final ProfilingManager INSTANCE = new ProfilingManager();

    private final HashMap<String, Integer> methodCalls = new HashMap<>();
    private final HashMap<String, Long> methodTime = new HashMap<>();

    private ProfilingManager() {
    }

    public static ProfilingManager getInstance() {
        if (INSTANCE == null) {
            return new ProfilingManager();
        }
        return INSTANCE;
    }

    public void incMethod(String methodName, Long elapsed) {
        Long alreadyElapsed = methodTime.getOrDefault(methodName, 0L);
        methodTime.put(methodName, alreadyElapsed + elapsed);
        Integer alreadyCalled = methodCalls.getOrDefault(methodName, 0);
        methodCalls.put(methodName, alreadyCalled + 1);
    }

    public void printStat() {
        Integer methodColWidth = methodTime.keySet().stream().map(String::length).max(Integer::compareTo).orElse(40);
        String methodColFormat = "%-" + methodColWidth.toString() + "." + methodColWidth.toString() + "s";
        String totalFormat = methodColFormat + "  %6d %11d   %12.1f\n";
        String headerMethodColFormat = "%" + methodColWidth.toString() + "." + methodColWidth.toString() + "s";
        System.out.format(headerMethodColFormat + "  %6.6s %11.11s        %7.7s\n", "method name", "called", "total, ns", "avg, ns");
        for (String key: methodTime.keySet().stream().sorted().collect(Collectors.toList())) {
            int totalMethodTime = Math.toIntExact(methodTime.get(key));
            int totalMethodCalls = methodCalls.get(key);
            double avgMethodTime = (double) totalMethodTime / totalMethodCalls;
            System.out.format(totalFormat, key, totalMethodCalls, totalMethodTime, avgMethodTime);
        }
    }

}

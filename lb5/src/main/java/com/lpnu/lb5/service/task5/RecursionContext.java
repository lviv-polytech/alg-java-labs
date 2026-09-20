package com.lpnu.lb5.service.task5;

import java.util.function.Consumer;

/**
 * Tracks the progression and depth of a recursive function to prevent StackOverflow
 * and satisfy task logging constraints. Replaces C++ pass-by-reference logic.
 */
public class RecursionContext {
    private int maxDepth;
    private final Consumer<String> logger;

    /**
     * @param logger Action to perform on each level registration (e.g., printing to console)
     */
    public RecursionContext(Consumer<String> logger) {
        this.maxDepth = 0;
        this.logger = logger;
    }

    /**
     * Registers the current level of a recursive dive.
     * 
     * @param functionName name of the function being executed
     * @param level current stack level
     */
    public void registerLevel(String functionName, int level) {
        if (level > maxDepth) {
            maxDepth = level;
        }
        if (logger != null) {
            logger.accept(String.format("Function [%s] -> level = %d", functionName, level));
        }
    }

    public int getMaxDepth() {
        return maxDepth;
    }
}
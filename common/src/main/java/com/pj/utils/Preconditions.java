package com.pj.utils;

import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @version 1.0
 * @description Preconditions
 * @date 2024/4/3 17:23:00
 */
public final class Preconditions {

    private Preconditions() {
        throw new AssertionError("not supported");
    }

    /**
     * @param validate      argument to be validated
     * @param predicate     to check against
     * @param errorSupplier the error message generator
     * @param <T>
     * @return T if Predicate does not match
     * @throws IllegalArgumentException if predicate returns true
     */
    public static <T> T precondition(
            T validate, Predicate<? super T> predicate, Supplier<String> errorSupplier) {
        if (predicate.test(validate)) {
            throw new IllegalArgumentException(errorSupplier.get());
        }
        return validate;
    }
}

package com.demo.zookeeper.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ListUtil {
    public static <T> List<T> arrayListOf(T... entities) {
        return new ArrayList<>(Arrays.asList(entities));
    }
}

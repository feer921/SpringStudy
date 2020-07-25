package org.flyfish.springstudy.kotlinstudy;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

class TestJava8 {
    public static void main(String[] args) {
        Function<String,Integer> fun = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return null;
            }
        };
        List<String> strs = new ArrayList<>();



//        strs.stream().map(item -> item.toLowerCase());
        strs.stream().map(String::toLowerCase).filter(s -> s.length() > 3);
    }

}

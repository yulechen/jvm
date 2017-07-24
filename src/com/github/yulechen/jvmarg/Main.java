package com.github.yulechen.jvmarg;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import sun.misc.Unsafe;


public class Main {
    /**
     * JVM 参数测试 -verbose:gc -Xms32m -Xmx32m -Xmn16m -Xss128k -XX:PermSize=32m
     * -XX:MaxPermSize=32m -XX:-PrintGCDetails
     * -XX:MaxDirectMemorySize=10M(直接内存设置)
     * 
     * @param args
     * @throws IOException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */

    public static void main(String[] args) throws Exception {
        // testStackMemoryErrorOnJavaheapspace();
        testMaxDirectMemorySize();
        System.out.println("Hello World");
        System.in.read();
    }


    public static void testOutOfMemoryErrorOnJavaheapspace() {
        final int oneM = 1 * 1024 * 16;
        List<Person> ps = new ArrayList();
        for (int i = 0; i < oneM * 32; i++) {
            final Person p = new Person();
            ps.add(p);
        }
    }

    private static int maxcount = 1000;
    private static int count = 0;


    public static void testStackMemoryErrorOnJavaheapspace() {
        long var = 1;
        count++;
        System.out.println(count);
        if (count < maxcount) {
            testStackMemoryErrorOnJavaheapspace();
        }
        else {
            return;
        }
    }


    public static void testMaxDirectMemorySize() throws Exception {
        int _1M = 1024 * 1024;
        for (int i = 0; i < 10; i++)
            getUnsafe().allocateMemory(_1M);
    }


    private static Unsafe getUnsafe() throws Exception {
        // Get the Unsafe object instance
        Field field = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        return (sun.misc.Unsafe) field.get(null);
    }
}

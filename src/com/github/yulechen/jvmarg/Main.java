package com.github.yulechen.jvmarg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {
    /**
     * JVM 参数测试 -verbose:gc -Xms32m -Xmx32m -Xmn16m -Xss128k -XX:PermSize=32m
     * -XX:MaxPermSize=32m -XX:-PrintGCDetails
     * 
     * @param args
     * @throws IOException
     */

    public static void main(String[] args) throws IOException {
        testStackMemoryErrorOnJavaheapspace();
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

    private static int maxcount = 500;
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
}

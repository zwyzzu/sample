package com.zhangwy.sample.utils;

import com.zhangwy.util.Util;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by zhangwy on 2017/3/27.
 */
public class UtilsTest {
    private String string = "123,456,789";
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void string2Array() throws Exception {
        String[] array = Util.string2Array(string, ",");
        for (String str:array) {
            System.out.println(str);
        }
    }

    @Test
    public void string2ArrayList() throws Exception {
        ArrayList<String> array = Util.string2ArrayList(string, ",");
        for (String str:array) {
            System.out.println(str);
        }
    }

    @Test
    public void reverser() throws Exception {
        String string = "中国人民解放军";
        System.out.println(Util.reverser(string));
    }

}
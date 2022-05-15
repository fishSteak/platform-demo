package com.example.utils;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;

public class SessionContain {
    public static Boolean haveAttribute(HttpSession session, String str) {
        Enumeration<String> enumeration = session.getAttributeNames();
        while (enumeration.hasMoreElements()) {
            // 获取session的属性名称
            String name = enumeration.nextElement().toString();
            if (name.equals(str)) return true;
        }
        return false;
    }
}

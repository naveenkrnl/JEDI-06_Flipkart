package com.flipkart.utils;

import com.flipkart.constant.Color;

import java.util.ArrayList;

public class StringUtils {
    public static String padding(String input, int maxLength)
    {
        while(input.length()< maxLength){
            input+=' ';
        }
        return input;
    }
    public static String center(String s, int size) {
        return center(s, size, ' ');
    }

    public static String center(String s, int size, char pad) {
        if (s == null || size <= s.length())
            return s;

        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < (size - s.length()) / 2; i++) {
            sb.append(pad);
        }
        sb.append(s);
        while (sb.length() < size) {
            sb.append(pad);
        }
        return sb.toString();
    }

    public static void printMenu(String heading, String[] options, int menuWidth)
    {
        int spacing = (menuWidth - heading.length())/2;
        String pad = padding("", spacing);
        System.out.println(Color.ANSI_CYAN_BACKGROUND + StringUtils.center(" ",menuWidth)+ Color.ANSI_RESET);
        System.out.println(Color.ANSI_BLACK_BACKGROUND + StringUtils.center(" ",menuWidth)+ Color.ANSI_RESET);
        System.out.println(Color.ANSI_BLACK_BACKGROUND + Color.ANSI_CYAN +Color.ANSI_BOLD + StringUtils.center(heading,menuWidth,'-') + Color.ANSI_RESET);
        System.out.println(Color.ANSI_BLACK_BACKGROUND + StringUtils.center(" ",menuWidth)+ Color.ANSI_RESET);
        for(int i=1; i<=options.length; i++)
        {
            System.out.println(Color.ANSI_BLACK_BACKGROUND + StringUtils.padding(pad+ i +". "+options[i-1],menuWidth) + Color.ANSI_RESET);
        }
        System.out.println(Color.ANSI_BLACK_BACKGROUND +  StringUtils.center(" ",menuWidth)+ Color.ANSI_RESET);
        System.out.println(Color.ANSI_BLACK_BACKGROUND +Color.ANSI_CYAN +Color.ANSI_BOLD + StringUtils.center("",menuWidth,'-')+ Color.ANSI_RESET);
        System.out.println(Color.ANSI_BLACK_BACKGROUND +  StringUtils.center(" ",menuWidth)+ Color.ANSI_RESET);
        System.out.println(Color.ANSI_CYAN_BACKGROUND + StringUtils.center(" ",menuWidth)+ Color.ANSI_RESET);
    }
}

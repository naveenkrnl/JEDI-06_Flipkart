package com.flipkart.utils;

import com.flipkart.constant.Color;

public class StringUtils {

    private StringUtils() {

    }

    public static final int DEFAULT_WIDTH = 100;

    public static String padding(String input, int maxLength) {
        StringBuilder inputBuilder = new StringBuilder(input);
        while (inputBuilder.length() < maxLength) {
            inputBuilder.append(' ');
        }
        input = inputBuilder.toString();
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

    public static void printMenu(String heading, String[] options) {
        printMenu(heading, options, DEFAULT_WIDTH);
    }

    public static void printMenu(String heading, String[] options, int menuWidth) {
        int spacing = (menuWidth - heading.length()) / 2;
        String pad = padding("", spacing);
        System.out.println(Color.ANSI_CYAN_BACKGROUND + StringUtils.center(" ", menuWidth) + Color.ANSI_RESET);
        System.out.println(Color.ANSI_BLACK_BACKGROUND + StringUtils.center(" ", menuWidth) + Color.ANSI_RESET);
        System.out.println(Color.ANSI_BLACK_BACKGROUND + Color.ANSI_CYAN + Color.ANSI_BOLD
                + StringUtils.center(heading, menuWidth, '-') + Color.ANSI_RESET);
        System.out.println(Color.ANSI_BLACK_BACKGROUND + StringUtils.center(" ", menuWidth) + Color.ANSI_RESET);
        for (int i = 1; i <= options.length; i++) {
            System.out.println(Color.ANSI_BLACK_BACKGROUND
                    + StringUtils.padding(pad + i + ". " + options[i - 1], menuWidth) + Color.ANSI_RESET);
        }
        System.out.println(Color.ANSI_BLACK_BACKGROUND + StringUtils.center(" ", menuWidth) + Color.ANSI_RESET);
        System.out.println(Color.ANSI_BLACK_BACKGROUND + Color.ANSI_CYAN + Color.ANSI_BOLD
                + StringUtils.center("", menuWidth, '-') + Color.ANSI_RESET);
        System.out.println(Color.ANSI_BLACK_BACKGROUND + StringUtils.center(" ", menuWidth) + Color.ANSI_RESET);
        System.out.println(Color.ANSI_CYAN_BACKGROUND + StringUtils.center(" ", menuWidth) + Color.ANSI_RESET);
    }

    public static void printPrompt() {
        System.out.println();
        System.out.println(Color.ANSI_CYAN_BACKGROUND + "  " + Color.ANSI_RESET + Color.ANSI_WHITE
                + Color.ANSI_BLACK_BACKGROUND + " Enter user input    " + Color.ANSI_RESET + Color.ANSI_CYAN_BACKGROUND
                + Color.ANSI_BLACK + " > " + Color.ANSI_RESET);
    }

    public static void printHeading(String heading) {
        printHeading(heading, DEFAULT_WIDTH);
    }

    public static void printHeading(String heading, int menuWidth) {
        System.out.println();
        System.out.println(Color.ANSI_CYAN_BACKGROUND + Color.ANSI_BLACK + Color.ANSI_BOLD
                + StringUtils.center(heading, menuWidth, ' ') + Color.ANSI_RESET);
    }

    public static void printSuccessMessage(String msg) {
        printSuccessMessage(msg, DEFAULT_WIDTH);
    }

    public static void printSuccessMessage(String msg, int menuWidth) {
        System.out.println(Color.ANSI_BLACK_BACKGROUND + Color.ANSI_CYAN + Color.ANSI_BOLD
                + StringUtils.center("-", menuWidth, '-') + Color.ANSI_RESET);
        System.out.println(Color.ANSI_BLACK_BACKGROUND + Color.ANSI_GREEN + StringUtils.center(msg, menuWidth, ' ')
                + Color.ANSI_RESET);
        System.out.println(Color.ANSI_BLACK_BACKGROUND + Color.ANSI_CYAN + Color.ANSI_BOLD
                + StringUtils.center("-", menuWidth, '-') + Color.ANSI_RESET);
        System.out.println();
    }

    public static void printErrorMessage(String msg) {
        printErrorMessage(msg, DEFAULT_WIDTH);
    }

    public static void printErrorMessage(String msg, int menuWidth) {
        System.out.println(Color.ANSI_BLACK_BACKGROUND + Color.ANSI_CYAN + Color.ANSI_BOLD
                + StringUtils.center("-", menuWidth, '-') + Color.ANSI_RESET);
        System.out.println(Color.ANSI_BLACK_BACKGROUND + Color.ANSI_RED + StringUtils.center(msg, menuWidth, ' ')
                + Color.ANSI_RESET);
        System.out.println(Color.ANSI_BLACK_BACKGROUND + Color.ANSI_CYAN + Color.ANSI_BOLD
                + StringUtils.center("-", menuWidth, '-') + Color.ANSI_RESET);
        System.out.println();
    }

    public static void printEndLine() {
        System.out.println(Color.ANSI_BLACK_BACKGROUND + Color.ANSI_CYAN + Color.ANSI_BOLD
                + StringUtils.center("-", DEFAULT_WIDTH, '-') + Color.ANSI_RESET);
        System.out.println();
    }

    public static void printEndLine(boolean extraEndLine) {
        System.out.println(Color.ANSI_BLACK_BACKGROUND + Color.ANSI_CYAN + Color.ANSI_BOLD
                + StringUtils.center("-", DEFAULT_WIDTH, '-') + Color.ANSI_RESET);
        if (extraEndLine)
            System.out.println();
    }

    public static void printTable(String row) {
        System.out.println(Color.ANSI_BLACK_BACKGROUND + Color.ANSI_WHITE + Color.ANSI_BOLD
                + padding(row, DEFAULT_WIDTH) + Color.ANSI_RESET);
    }
}

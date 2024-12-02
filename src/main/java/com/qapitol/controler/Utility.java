package com.qapitol.controler;

import java.util.Scanner;

public class Utility {
    Scanner scanner = new Scanner(System.in);
    public String inputString() {
        String str = scanner.next();
        return str;
    }
    public boolean inputBoolean() {
        boolean flag = scanner.nextBoolean();
        return flag;
    }
    public int inputint() {
        int num = scanner.nextInt();
        return num;
    }
    public long inputlong() {
        long lon = scanner.nextLong();
        return lon;
    }
}

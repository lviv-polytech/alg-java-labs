package com.lpnu.lb5.ui;

import com.lpnu.common.SafeScanner;
import com.lpnu.lb5.ui.task1.Task1Runner;

public class Main {
    public static void main(String[] args) {
        SafeScanner scanner = new SafeScanner();
        
        System.out.println("====== Lab 5 ======");
        System.out.println("1. Task 1 (Custom Math Functions composite expression)");
        System.out.println("0. Exit");
        
        while (true) {
            System.out.print("> Select task: ");
            String choice = scanner.nextLineOrNull();
            if (choice == null || choice.equals("0")) {
                System.out.println("Exiting...");
                break;
            }
            
            if (choice.equals("1")) {
                new Task1Runner().run();
                break;
            } else {
                System.out.println("Invalid selection.");
            }
        }
        
        scanner.close();
    }
}
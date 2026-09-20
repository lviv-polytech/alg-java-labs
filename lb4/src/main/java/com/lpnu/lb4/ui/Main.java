package com.lpnu.lb4.ui;

import com.lpnu.common.SafeScanner;
import com.lpnu.lb4.ui.task1.Task1Runner;
import com.lpnu.lb4.ui.task2.Task2Runner;

public class Main {
    public static void main(String[] args) {
        SafeScanner scanner = new SafeScanner();
        
        System.out.println("====== Lab 4 ======");
        System.out.println("1. Task 1 (Trig Series Sum loop evaluation)");
        System.out.println("2. Task 2 (Piecewise Function Tabulation)");
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
            } else if (choice.equals("2")) {
                new Task2Runner().run();
                break;
            } else {
                System.out.println("Invalid selection.");
            }
        }
        
        scanner.close();
    }
}
package com.lpnu.lb6.ui;

import com.lpnu.common.SafeScanner;
import com.lpnu.lb6.ui.task1.Task1Runner;
import com.lpnu.lb6.ui.task2.Task2Runner;
import com.lpnu.lb6.ui.task3.Task3Runner;

public class Main {
    public static void main(String[] args) {
        SafeScanner scanner = new SafeScanner();
        
        System.out.println("====== Lab 6 ======");
        System.out.println("1. Task 1 (1D Array Processing: Iterative vs Recursive)");
        System.out.println("2. Task 2 (Max Even Element Index: Iterative vs Recursive)");
        System.out.println("3. Task 3 (Generics/Templates Console Inputs)");
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
            } else if (choice.equals("3")) {
                new Task3Runner().run();
                break;
            } else {
                System.out.println("Invalid selection.");
            }
        }
        
        scanner.close();
    }
}
package com.lpnu.lb4.ui;

import com.lpnu.common.SafeScanner;
import com.lpnu.lb4.ui.task1.Task1Runner;
import com.lpnu.lb4.ui.task2.Task2Runner;
import com.lpnu.lb4.ui.task3.Task3Runner;
import com.lpnu.lb4.ui.task4.Task4Runner;
import com.lpnu.lb4.ui.task5.Task5Runner;
import com.lpnu.lb4.ui.task6.Task6Runner;
import com.lpnu.lb4.ui.task7.Task7Runner;

public class Main {
    public static void main(String[] args) {
        SafeScanner scanner = new SafeScanner();
        
        System.out.println("====== Lab 4 ======");
        System.out.println("1. Task 1 (Trig Series Sum loop evaluation)");
        System.out.println("2. Task 2 (Piecewise Function Tabulation)");
        System.out.println("3. Task 3 (Parametric Function Tabulation)");
        System.out.println("4. Task 4 (Graphic Function Tabulation)");
        System.out.println("5. Task 5 (Shape Hit Testing)");
        System.out.println("6. Task 6 (Nested Loops Math Series)");
        System.out.println("7. Task 7 (Taylor Series Recurrent)");
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
            } else if (choice.equals("4")) {
                new Task4Runner().run();
                break;
            } else if (choice.equals("5")) {
                new Task5Runner().run();
                break;
            } else if (choice.equals("6")) {
                new Task6Runner().run();
                break;
            } else if (choice.equals("7")) {
                new Task7Runner().run();
                break;
            } else {
                System.out.println("Invalid selection.");
            }
        }
        
        scanner.close();
    }
}
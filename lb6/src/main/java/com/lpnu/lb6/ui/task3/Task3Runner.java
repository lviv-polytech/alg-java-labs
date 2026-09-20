package com.lpnu.lb6.ui.task3;

import com.lpnu.common.SafeScanner;
import com.lpnu.lb6.service.task3.IterativeInputService;
import com.lpnu.lb6.service.task3.RecursiveInputService;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Task3Runner {

    public void run() {
        System.out.println("--- Task 3: Generics / Templates array population ---");

        SafeScanner scanner = new SafeScanner();
        Supplier<String> reader = () -> {
            String s = scanner.nextLineOrNull();
            if (s == null) System.exit(0);
            return s;
        };
        Consumer<String> writer = System.out::print;

        int size;
        while (true) {
            System.out.print("Enter fixed size for arrays (try 3 for speed): ");
            try {
                size = Integer.parseInt(reader.get().trim());
                if (size > 0) break;
                System.out.println("Size must be > 0.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid format.");
            }
        }

        System.out.println("\n[ ITERATIVE EXAMPLES ]");
        IterativeInputService itService = new IterativeInputService();
        
        System.out.println("=> Populating strictly-typed Integer array:");
        Integer[] itIntArr = new Integer[size];
        itService.fillIntArray(itIntArr, reader, writer, "intIter");
        System.out.println("Result: " + itService.formatArray(itIntArr));

        System.out.println("=> Populating generic Double array:");
        Double[] itDoubleArr = new Double[size];
        itService.fillGenericArray(itDoubleArr, Double::parseDouble, reader, writer, "dblIter");
        System.out.println("Result: " + itService.formatArray(itDoubleArr));

        System.out.println("=> Populating generic String array:");
        String[] itStringArr = new String[size];
        itService.fillGenericArray(itStringArr, s -> s, reader, writer, "strIter");
        System.out.println("Result: " + itService.formatArray(itStringArr));

        System.out.println("\n-----------------------------------------------------------");

        System.out.println("\n[ RECURSIVE EXAMPLES ]");
        RecursiveInputService recService = new RecursiveInputService();

        System.out.println("=> Populating strictly-typed Integer array:");
        Integer[] recIntArr = new Integer[size];
        recService.fillIntArray(recIntArr, reader, writer, "intRec", 0);
        System.out.println("Result: " + recService.formatArray(recIntArr, 0));

        System.out.println("=> Populating generic Double array:");
        Double[] recDoubleArr = new Double[size];
        recService.fillGenericArray(recDoubleArr, Double::parseDouble, reader, writer, "dblRec", 0);
        System.out.println("Result: " + recService.formatArray(recDoubleArr, 0));

        System.out.println("=> Populating generic String array:");
        String[] recStringArr = new String[size];
        recService.fillGenericArray(recStringArr, s -> s, reader, writer, "strRec", 0);
        System.out.println("Result: " + recService.formatArray(recStringArr, 0));

        System.out.println("\nTask 3 completed.\n");
    }
}
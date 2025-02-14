import java.io.*;
import java.util.Random;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Clase para medir tiempos de ejecución con selección dinámica de intervalos y corrección del registro de tiempos.
 */
public class SortingProfiler {

    private static final int TOTAL_NUMBERS = 3000;
    private static int[] intervals;
    private static int rangeMax;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        generateNumbersFile("numeros.txt");

        InsertionSort insertionSort = new InsertionSort();
        MergeSort mergeSort = new MergeSort();
        QuickSort quickSort = new QuickSort();
        RadixSort radixSort = new RadixSort();
        ShellSort shellSort = new ShellSort();

        int[] numbers = readNumbersFromFile("numeros.txt");

        boolean continueTesting = true;
        while (continueTesting) {
            System.out.println("\nOpciones:");
            System.out.println("1. Usar los mismos intervalos.");
            System.out.println("2. Generar nuevos intervalos aleatorios.");
            System.out.println("3. Cambiar el rango de intervalos.");
            System.out.print("Selecciona una opción: ");
            int option = scanner.nextInt();

            if (option == 3 || rangeMax == 0) { 
                System.out.print("\n¿Cuál es el tamaño máximo del rango de intervalos? (1 - " + TOTAL_NUMBERS + "): ");
                rangeMax = scanner.nextInt();
                while (rangeMax < 1 || rangeMax > TOTAL_NUMBERS) {
                    System.out.print("Rango inválido. Introduce un número entre 1 y " + TOTAL_NUMBERS + ": ");
                    rangeMax = scanner.nextInt();
                }
            }

            if (option == 2 || intervals == null || option == 3) {
                System.out.print("\n¿Cuántos intervalos quieres generar? (1 - " + rangeMax + "): ");
                int numIntervals = scanner.nextInt();
                while (numIntervals < 1 || numIntervals > rangeMax) {
                    System.out.print("Cantidad inválida. Introduce un número entre 1 y " + rangeMax + ": ");
                    numIntervals = scanner.nextInt();
                }
                intervals = generateRandomIntervals(numIntervals, rangeMax);
                System.out.println("Nuevos intervalos generados: " + Arrays.toString(intervals));
            } else {
                System.out.println("Usando los intervalos anteriores: " + Arrays.toString(intervals));
            }

            System.out.print("\n¿Cuántas veces deseas repetir la prueba con estos intervalos? ");
            int testCount = scanner.nextInt();

            try (FileWriter writer = new FileWriter("Tiempos.csv")) {
                writer.write("Iteración,Insertion Sort,Merge Sort,Quick Sort,Radix Sort,Shell Sort\n");

                for (int test = 1; test <= testCount; test++) {
                    System.out.println("\nEjecución #" + test);

                    // Medir tiempos de cada algoritmo una sola vez por prueba
                    long insertionTime = measureTime(insertionSort, numbers.clone());
                    long mergeTime = measureTime(mergeSort, numbers.clone());
                    long quickTime = measureTime(quickSort, numbers.clone());
                    long radixTime = measureTime(radixSort, numbers.clone());
                    long shellTime = measureTime(shellSort, numbers.clone());

                    writer.write(test + "," + insertionTime + "," + mergeTime + "," + quickTime + "," + radixTime + "," + shellTime + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("\nResultados guardados en Tiempos.csv");

            System.out.print("\n¿Quieres hacer otra prueba? (1 = Sí, 2 = No): ");
            int choice = scanner.nextInt();
            continueTesting = (choice == 1);
        }

        scanner.close();
        System.out.println("\nPruebas finalizadas.");
    }

    private static void generateNumbersFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            Random rand = new Random();
            for (int i = 0; i < TOTAL_NUMBERS; i++) {
                writer.write(rand.nextInt(10000) + "\n");
            }
            System.out.println("Archivo generado: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[] readNumbersFromFile(String filename) {
        int[] numbers = new int[TOTAL_NUMBERS];
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null && index < TOTAL_NUMBERS) {
                numbers[index++] = Integer.parseInt(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numbers;
    }

    private static int[] generateRandomIntervals(int numIntervals, int maxSize) {
        Random rand = new Random();
        HashSet<Integer> intervalSet = new HashSet<>();

        while (intervalSet.size() < numIntervals) {
            int num = rand.nextInt(maxSize) + 1;
            intervalSet.add(num);
        }

        int[] intervals = intervalSet.stream().mapToInt(Integer::intValue).toArray();
        Arrays.sort(intervals);
        return intervals;
    }

    private static long measureTime(Object sorter, int[] array) {
        long startTime = System.nanoTime();

        if (sorter instanceof InsertionSort) {
            ((InsertionSort) sorter).sort(array);
        } else if (sorter instanceof MergeSort) {
            ((MergeSort) sorter).sort(array);
        } else if (sorter instanceof QuickSort) {
            ((QuickSort) sorter).sort(array);
        } else if (sorter instanceof RadixSort) {
            ((RadixSort) sorter).sort(array);
        } else if (sorter instanceof ShellSort) {
            ((ShellSort) sorter).sort(array);
        }

        return System.nanoTime() - startTime;
    }
}

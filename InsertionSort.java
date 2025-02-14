/**
 * Javier Castillo #24014
 * Camila Sandoval #24358
 */
public class InsertionSort {

    public void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        InsertionSort sorter = new InsertionSort();
        int[] array = {5, 3, 8, 1, 2};

        System.out.println("Arreglo original:");
        for (int num : array) {
            System.out.print(num + " ");
        }

        sorter.sort(array);

        System.out.println("\nArreglo ordenado:");
        for (int num : array) {
            System.out.print(num + " ");
        }
    }
}


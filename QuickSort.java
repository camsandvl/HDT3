/**
 * Implementación del algoritmo de Quick Sort.
 * Javier Castillo #24014
 * Camila Sandobal #24358
 */
public class QuickSort {

    /**
     * Ordena un arreglo usando Quick Sort.
     */
    public void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    /**
     * Método recursivo para Quick Sort.
     */
    private void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    /**
     * Coloca el pivote en su posición correcta y divide el arreglo.
     */
    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    /**
     * Intercambia dos elementos en el arreglo.
     */
    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Prueba manual de Quick Sort.
     */
    public static void main(String[] args) {
        QuickSort sorter = new QuickSort();
        int[] array = {10, 7, 8, 9, 1, 5};

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

import java.util.Arrays;

/**
 * Implementación del algoritmo de Radix Sort.
 * Javier Castillo #24014
 * Camila Sandobal #24358
 */
public class RadixSort {

    /**
     * Ordena un arreglo usando Radix Sort.
     */
    public void sort(int[] array) {
        int max = getMax(array);
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(array, exp);
        }
    }

    /**
     * Obtiene el valor máximo en un arreglo.
     */
    private int getMax(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        int max = array[0];
        for (int num : array) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    /**
     * Ordena el arreglo según un dígito específico usando Counting Sort.
     */
    private void countingSort(int[] array, int exp) {
        int n = array.length;
        int[] output = new int[n];
        int[] count = new int[10];

        for (int i = 0; i < n; i++) {
            count[(array[i] / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            output[count[(array[i] / exp) % 10] - 1] = array[i];
            count[(array[i] / exp) % 10]--;
        }

        System.arraycopy(output, 0, array, 0, n);
    }

    /**
     * Prueba manual de Radix Sort.
     */
    public static void main(String[] args) {
        RadixSort sorter = new RadixSort();
        int[] array = {170, 45, 75, 90, 802, 24, 2, 66};

        System.out.println("Arreglo original:");
        System.out.println(Arrays.toString(array));

        sorter.sort(array);

        System.out.println("Arreglo ordenado:");
        System.out.println(Arrays.toString(array));
    }
}

/**
 * Javier Castillo #24014
 * Camila Sandoval #24358
 */
public class MergeSort {

    public void sort(int[] array) {
        if (array.length < 2) {
            return;
        }

        int mid = array.length / 2;
        int[] left = new int[mid];
        int[] right = new int[array.length - mid];

        System.arraycopy(array, 0, left, 0, mid);
        System.arraycopy(array, mid, right, 0, array.length - mid);

        sort(left);
        sort(right);

        merge(array, left, right);
    }

    private void merge(int[] array, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }

        while (i < left.length) {
            array[k++] = left[i++];
        }

        while (j < right.length) {
            array[k++] = right[j++];
        }
    }

    public static void main(String[] args) {
        MergeSort sorter = new MergeSort();
        int[] array = {9, 4, 6, 2, 8, 1, 3};

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

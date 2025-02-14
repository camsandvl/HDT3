import java.util.Arrays;

public class ShellSort {

    public void sort(int[] array) {
        int n = array.length;

        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = array[i];
                int j;

                for (j = i; j >= gap && array[j - gap] > temp; j -= gap) {
                    array[j] = array[j - gap];
                }

                array[j] = temp;
            }
        }
    }

    public static void main(String[] args) {
        ShellSort sorter = new ShellSort();
        int[] array = {12, 34, 54, 2, 3};

        System.out.println("Arreglo original:");
        System.out.println(Arrays.toString(array));

        sorter.sort(array);

        System.out.println("Arreglo ordenado:");
        System.out.println(Arrays.toString(array));
    }
}

package practice.algo.misc;

/**
 * Created by adarsh.sharma on 06/07/18.
 */
public class LocalMinima {
    public static void main(String[] args) {
        int[] arr = {3, 4, 6, 7, 9, 10, 8, 7, 6};
        System.out.println(localMinima(arr, 0, arr.length - 1, arr.length));
    }

    private static int localMinima(int[] arr, int low, int high, int n) {
        int mid = (low + high) / 2;

        if (low > high || mid == 0 || mid == n - 1) {
            return -1;
        }

        if (arr[mid] < arr[mid - 1] && arr[mid] < arr[mid + 1]) {
            return mid;
        } else if(low == high) {
            return -1;
        }

        if (arr[mid] > arr[mid - 1]) {
            int index = localMinima(arr, low, mid, n);
            if (index != -1) {
                return index;
            }

            if (arr[mid] > arr[mid + 1]) {
                index = localMinima(arr, mid, high, n);
            } else {
                index = localMinima(arr, mid + 2, high, n);
            }
            if (index != -1) {
                return index;
            }
        } else {
            int index = localMinima(arr, low, mid - 1, n);
            if (index != -1) {
                return index;
            }

            index = localMinima(arr, mid + 1, high, n);
            if (index != -1) {
                return index;
            }
        }

        return -1;
    }
}

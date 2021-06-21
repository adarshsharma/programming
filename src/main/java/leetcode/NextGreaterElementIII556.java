package leetcode;

public class NextGreaterElementIII556 {

    public int nextGreaterElement(int n) {
        if (n < 10) {
            return -1;
        }

        char[] arr = (Integer.valueOf(n)).toString().toCharArray();
        int i = arr.length - 2;
        for (; i >= 0; i--) {
            if (arr[i] < arr[i + 1]) {
                break;
            }
        }

        if (i == -1) {
            return -1;
        }

        int j = arr.length - 1;
        while (arr[j] <= arr[i]) {
            j--;
        }

        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

        i++;
        j = arr.length - 1;
        while (i < j) {
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }

        String res = new String(arr);

        if (res.length() == 10) {
            String max = Integer.toString(Integer.MAX_VALUE);
            for (i = 0; i < 10; i++) {
                if(max.charAt(i) > res.charAt(i)) {
                    break;
                } else if (max.charAt(i) < res.charAt(i)) {
                    return -1;
                }
            }
        }

        return Integer.parseInt(res);
    }

    public static void main(String[] args) {
        System.out.println(new NextGreaterElementIII556().nextGreaterElement(2138476986));
    }

}

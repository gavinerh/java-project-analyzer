package codingInterviews.dynamicProgramming.tabulation;

public class FibonacciTabulation {
    public static void main(String[] args) {
        System.out.println(generateFibResult(50));
    }

    private static long generateFibResult(int index) {
        long[] arr = new long[index + 1];
        // initialize the first 2 values
        arr[0] = 0; arr[1] = 1;
        int pointer1 = 0;
        int pointer2 = 1;
        int pointer3 = 2;
        while(pointer2 <= index) {
            long toAdd = arr[pointer1];
            if(pointer3 <= index) {
                // as usual
                arr[pointer3] = arr[pointer3] + arr[pointer1];
            }
            arr[pointer2] = arr[pointer2] + arr[pointer1];
            pointer2++;
            pointer1++;
            pointer3++;
        }
        return arr[index];
    }
}

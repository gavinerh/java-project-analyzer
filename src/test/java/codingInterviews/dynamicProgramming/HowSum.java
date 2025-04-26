package codingInterviews.dynamicProgramming;

import java.util.*;

public class HowSum {
    // get the first set of numbers to add to target sum, any one will do
    public static void main(String[] args) {
        Map<Integer,List<Integer>> map = new HashMap<>();
        Integer[] integerList = howSum(300, new int[]{7,3},map);
        if(integerList != null) {
            System.out.println(Arrays.toString(integerList));
        }else {
            System.out.println("null");
        }

    }

    private static Integer[] howSum(int target, int[] arr, Map<Integer,List<Integer>> map) {
        List<Integer> myList = bestSumListWithMemoization(target,arr,map);
        return myList == null ? null : myList.toArray(new Integer[myList.size()]);
    }

    private static List<Integer> bestSumListWithMemoization(int target, int[] arr, Map<Integer,List<Integer>> map) {
        // termination condition
        if(map.containsKey(target)) {
            return map.get(target);
        }
        if(target == 0) {
            return new ArrayList<>();
        }
        if(target < 0) {
            return null;
        }
        for(int num : arr) {
            int remainder = target - num;
            List<Integer> returnedList = bestSumListWithMemoization(remainder,arr,map);
            if(returnedList != null) {
                returnedList.add(num);
                map.put(target,returnedList);
                return returnedList;
            }
        }
        map.put(target,null);
        return null;
    }

    private static List<Integer> bestSumList(int target, int[] arr) {
        // termination condition
        if(target == 0) {
            return new ArrayList<>();
        }
        if(target < 0) {
            return null;
        }
        for(int num : arr) {
            int remainder = target - num;
            List<Integer> returnedList = bestSumList(remainder,arr);
            if(returnedList == null) {
                return returnedList;
            } else {
                returnedList.add(num);
                return returnedList;
            }
        }
        return null;
    }
}

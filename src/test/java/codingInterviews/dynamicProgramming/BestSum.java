package codingInterviews.dynamicProgramming;

import java.util.*;

public class BestSum {
    // get the shortest set of numbers to add to target sum,
    public static void main(String[] args) {
        Integer[] integerList = bestSum(100, new int[]{1,2,5,25});
        if(integerList != null) {
            System.out.println(Arrays.toString(integerList));
        }else {
            System.out.println("null");
        }
    }

    private static Integer[] bestSum(int target, int[] arr) {
        Map<Integer,List<Integer>> map = new HashMap<>();
        List<Integer> myList = bestSumList(target,arr, map);
//        List<Integer> myList = bestSumListNon(target,arr);
        return myList == null ? null : myList.toArray(new Integer[myList.size()]);
    }

    private static List<Integer> bestSumListNon(int target, int[] arr) {
//        if(map.containsKey(target)) {
//            return map.get(target);
//        }
        // termination condition
        if(target == 0) {
            return new ArrayList<>();
        }
        if(target < 0) {
            return null;
        }
        List<Integer> optimizedIntegerList = null;
        for(int num : arr) {
            int remainder = target - num;
            List<Integer> returnedList = bestSumListNon(remainder,arr);
            if(returnedList != null) {
                if(target == 1 && returnedList.size() == 1) {
                    System.out.println("Reached condition");
                }
                returnedList.add(num);
                if(optimizedIntegerList == null || returnedList.size() < optimizedIntegerList.size()) {
                    optimizedIntegerList = returnedList;
                }
            }
        }
        return optimizedIntegerList;
    }

    private static List<Integer> bestSumList(int target, int[] arr, Map<Integer,List<Integer>> map) {
        if(map.containsKey(target)) {
            return map.get(target);
        }
        // termination condition
        if(target == 0) {
            return new ArrayList<>();
        }
        if(target < 0) {
            return null;
        }
        List<Integer> optimizedIntegerList = null;
        for(int num : arr) {
            int remainder = target - num;
            List<Integer> returnedList = bestSumList(remainder,arr,map);
            if(returnedList != null) {
                List<Integer> dup = new ArrayList<>(returnedList); // important to make a deep copy here, or else the returned list from prev remainder will have new val appended
                dup.add(num);
                if(optimizedIntegerList == null || dup.size() < optimizedIntegerList.size()) {
                    optimizedIntegerList = dup;
                }
            }
        }
        map.put(target,optimizedIntegerList);
        return optimizedIntegerList;
    }
}

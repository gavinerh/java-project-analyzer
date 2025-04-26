package codingInterviews.dynamicProgramming.wordConstruct;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AllConstruct {
    // return all the different ways the words can be added to the target
    public static void main(String[] args) {
        Map<String, String[][]> map = new HashMap<>();
        String[][] arr = allConstructMem("abcdef", new String[]{"ab", "abc", "cd", "def", "abcd", "ef", "c"}, map); // true
        printArr(arr, "printing abcdef");
        arr = allConstructMem("purple", new String[]{"purp", "p", "ur", "le", "purpl"}, new HashMap<>());
        printArr(arr, "printing purple");
        arr = allConstructMem("skateboard", new String[]{"bo", "rd", "ate", "t", "ska", "sk", "boar"}, new HashMap<>()); // false
        printArr(arr, "printing skateboard");
        arr = allConstructMem("enterapotentpot", new String[]{"a", "p", "ent", "enter", "ot", "o", "t"}, new HashMap<>()); // true
        printArr(arr, "printing enterapotentpot");
        arr = allConstructMem("eeeeeeeeeeeeeeeeeeeeeeeeeeef", new String[]{"e", "ee", "eee", "eeee", "eeeee", "eeeeee"}, new HashMap<>());
        printArr(arr, "printing eeeeeeeeeeeeeef");
    }

    private static void printArr(String[][] arr, String desc) {
        System.out.println(desc);
        if(arr.length == 0) {
            System.out.println("[]");
        }
        for (String[] innerArr : arr) {
            if (innerArr != null) {
                System.out.println(Arrays.toString(innerArr));
            } else {
                System.out.println("[]");
            }
        }
    }

    private static String[][] allConstructMem(String target, String[] bank, Map<String, String[][]> map) {
        if (map.containsKey(target)) {
            return map.get(target);
        }
        // termination
        if (target.isBlank()) {
            return new String[1][1];
        }
        String[][] parentArr = new String[0][];
        for (String subWord : bank) {
            if (target.indexOf(subWord) == 0) {
                String remainderString = minusPrefix(target, subWord);
                String[][] result = allConstructMem(remainderString, bank, map);
                if (result != null) {
                    appendSubWord(result, subWord);
                    parentArr = addResultToParent(parentArr, result);
                }
            }
        }
        map.put(target, parentArr);
        return parentArr;
    }

    private static String[][] allConstruct(String target, String[] bank) {
        if (target.isBlank()) {
            return new String[1][1];
        }
        String[][] parentArr = null;
        for (String subWord : bank) {
            if (target.indexOf(subWord) == 0) {
                String remainderString = minusPrefix(target, subWord);
                String[][] result = allConstruct(remainderString, bank);
                if (result != null) {
                    appendSubWord(result, subWord);
                    if (parentArr == null) {
                        parentArr = result;
                    } else {
                        parentArr = addResultToParent(parentArr, result);
                    }
                }
            }
        }
        return parentArr;
    }

    private static String[][] addResultToParent(String[][] parent, String[][] result) {
        int numOfInnerArr = result.length;
        int finalSizeOfParent = parent.length + numOfInnerArr;
        String[][] newParent = new String[finalSizeOfParent][];
        int count = 0;
        for (String[] innerParent : parent) {
            newParent[count] = innerParent;
            count++;
        }
        for (String[] innerResult : result) {
            newParent[count] = innerResult;
            count++;
        }
        return newParent;
    }

    private static void appendSubWord(String[][] arr, String subWord) {
        try {
            for (int j = 0; j < arr.length; j++) {
                String[] innerArr = arr[j];
                if (innerArr != null) {
                    if (innerArr[0] == null) {
                        innerArr[0] = subWord;
                    } else {
                        String[] innerArrPlusOne = new String[innerArr.length + 1];
                        innerArrPlusOne[0] = subWord;
                        appendReminderOfArr(innerArr, innerArrPlusOne);
                        arr[j] = innerArrPlusOne;
                    }
                } else {
                    System.out.println("inner array is null");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void appendReminderOfArr(String[] old, String[] oldPlusOne) {
        for (int i = 0; i < old.length; i++) {
            oldPlusOne[i + 1] = old[i];
        }
    }

    private static String minusPrefix(String target, String prefix) {
        return target.substring(prefix.length());
    }
}

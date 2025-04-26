import org.mapstruct.factory.Mappers;

import java.util.*;
import java.util.stream.Collectors;

public class TestingPurposes {

    private static String template = "Moving from %s --> %s";

    private static MapperClone mapperClone = Mappers.getMapper(MapperClone.class);


    public static void main(String[] args) {
        String val = "'NM_profileupd','NM_prfupKFID','NM_prfupKFCN','NM_prflupPPS','NM_prfupPSCN','NM_prfupPSID','NM_LNKCHILD','NM_LNKPARENT','NM_LNKGUD','NM_LNKMIR','NM_HMALNK','NM_NTDLN','NM_NTLNK','NM_XODLN','NM_XOLNK','NM_XOLNKPPS','NM_TCLNK','NM_HMADLN','NM_TCDLN','NM_pinackKF','NM_pinackKFCN','NM_pinackKFID','NM_pinackPPS','NM_pwdackKF','NM_pwdackKFCN','NM_pwdackKFID','NM_pwdackPps','NM_HAACLNK','NM_HAACDLN'";
        String[] originalArr = printLength(val);
        System.out.println("==================================");
        String valChanged = "'NMprfupd','NMprfKFID','NMprfKFCN','NMprfPPS','NMprfPSCN','NMprfPSID','NMLKCHILD','NMLKPARNT','NMLNKGUD','NMLNKMIR','NMHMALNK','NMNTDLN','NMNTLNK','NMXODLN','NMXOLNK','NMXOLNKPS','NMTCLNK','NMHMADLN','NMTCDLN','NMpinKF','NMpinKFCN','NMpinKFID','NMpinPPS','NMpwdKF','NMpwdKFCN','NMpwdKFID','NMpwdPps','NMHAACLNK','NMHAACDLN'";
        String[] modifiedArr = printLength(valChanged);
        Map<String,String> map = printOldMatchToNew(originalArr,modifiedArr);
        System.out.println(map.size());
    }

    private static Map<String,String> printOldMatchToNew(String[] val, String[] modified) {
        Map<String,String> map = new HashMap<>();
        if(val.length != modified.length) {
            throw new RuntimeException("Length is not the same");
        }
        for(int i=0; i<val.length; i++) {
            System.out.println(String.format("%s -- %s",val[i],modified[i]));
            map.put(val[i],modified[i]);
        }
        return map;
    }

    private static String[] printLength(String val) {
        String[] valArr = val.split(",");
        for(int i=0; i<valArr.length; i++) {
            String idModified = removeSingleQuotes(valArr[i]);
            valArr[i] = idModified;
            System.out.println(String.format("%s -- %d",idModified,idModified.length()));
        }
        return valArr;
    }

    private static String removeSingleQuotes(String val) {
        return val.substring(1,val.length() - 1);
    }

    private static void printReplacement(String val) {
        String replacement = "MARMS";
        String toReplace = "RFC_MTPGUI_1496";
        String[] arr = val.split("\n");
        for(String row : arr) {
            if(row.isBlank()) {
                System.out.println("");
            } else {
                if(row.contains(toReplace)) {
                    System.out.println(row.replace(toReplace,replacement));
                }
            }
        }
    }

    public static boolean testTrue() {
        System.out.println("testTrue() called");
        return true;
    }

    public static boolean testFalse() {
        System.out.println("testFalse() called");
        return false;
    }

    private static void mergesort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergesort(arr, left, mid);
            mergesort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        } else {
            // should be the same for left and right
            return;
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        // inplace merge
        int[] dupArr = new int[right - left + 1];
        int rightIndex = mid + 1;
        int leftIndex = left;
        for (int i = 0; i < dupArr.length; i++) {
            if (leftIndex > mid) {
                dupArr[i] = arr[rightIndex];
                rightIndex++;
            } else if (rightIndex > right) {
                dupArr[i] = arr[leftIndex];
                leftIndex++;
            } else if (arr[leftIndex] < arr[rightIndex]) {
                dupArr[i] = arr[leftIndex];
                leftIndex++;
            } else {
                dupArr[i] = arr[rightIndex];
                rightIndex++;
            }
        }

        int index = 0;
        for (int i = left; i <= right; i++) {
            arr[i] = dupArr[index];
            index++;
        }
    }


    private static void hanoi(int count, String source, String inter, String dest) {
        if (count == 1) {
            System.out.println(String.format(template, source, dest));
            return;
        }
        hanoi(count - 1, source, dest, inter);
        hanoi(1, source, inter, dest);
        hanoi(count - 1, inter, source, dest);
    }

    public static Map<String, List<Integer>> createMapping(int[] A) {
        // Implement your solution here
        Map<String, List<Integer>> map = new HashMap<>();
        for (int i : A) {
            int[] firstAndLast = getFirstLast(i);
            String key = generateKey(firstAndLast);
            List<Integer> list = map.get(key);
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(i);
            map.put(key, list);
            for (int j = 1; j < A.length; j++) {
                int[] next = getFirstLast(A[j]);
                if (firstAndLast[0] == next[0] && firstAndLast[1] == next[1]) {
                    // first and last match, add to map
                    if (map.containsKey(key)) {
                        list.add(A[j]);
                    }
                }
            }
        }
        return map;
    }


    private static String generateKey(int[] arr) {
        return String.format("%d%d", arr[0], arr[1]);
    }

    private static int[] getFirstLast(int val) {
        if (val < 10) {
            return new int[]{0, val};
        }
        String valString = String.valueOf(val);
        int first = Integer.parseInt(valString.substring(0, 1));
        int last = Integer.parseInt(valString.substring(valString.length() - 1));
        return new int[]{first, last};
    }

    private static int getLongestBinaryGap(List<Integer> binList) {
        int largest = 0;
        int current = 0;
        for (int i = binList.size() - 1; i >= 0; i--) {
            if (binList.get(i) == 0) {
                current++;
            } else if (binList.get(i) != 0) {
                largest = current >= largest ? current : largest;
                current = 0;
            }
            if (i == 0 && binList.get(i) == 0) {
                current = 0;
            }
        }
        largest = current >= largest ? current : largest;
        return largest;
    }

    private static List<Integer> convertIntegerToBinary(int val) {
        List<Integer> bin = new ArrayList<>();
        if (val == 0) {
            return bin;
        }
        while (val > 0) {
            bin.add(val % 2);
            val = val / 2;
        }
        return bin;
    }


    private static int solutionDup(int[] A) {
        int[] sortedArr = Arrays.stream(A).sorted().toArray();
        int smallestInt = 1;
        Integer prevInt = null;
        for (int i = 0; i < sortedArr.length; i++) {
            // loop through the arr and
            if (prevInt != null && prevInt == sortedArr[i]) {
                continue;
            }
            if (sortedArr[i] != smallestInt) {
                break;
            }
            // increment the smallest int when its the first number
            if (prevInt == null || prevInt != sortedArr[i]) {
                smallestInt++;
            }
            prevInt = sortedArr[i];
        }
        return smallestInt;
    }

    private static void printPath(List<String> paths) {
        String template = "{%s}";
        String innerTemplate = "\"%s\",";
        String finalString = "";
        for (String path : paths) {
            finalString += String.format(innerTemplate, path);
        }
        System.out.println(String.format(template, finalString));
    }

    private static String removeExtension(String path, String end) {
        int endInd = path.indexOf(end);
        return path.substring(0, endInd);
    }


}
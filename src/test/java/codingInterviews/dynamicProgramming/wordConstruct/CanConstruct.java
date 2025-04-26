package codingInterviews.dynamicProgramming.wordConstruct;

import java.util.HashMap;
import java.util.Map;

public class CanConstruct {
    // given a target and a list of string, check if the list of string can concatenate to target
    public static void main(String[] args) {

        System.out.println(canConstructMem("abcdef",new String[]{"ab","abc","cd","def","abcd"},new HashMap<>())); // true
        System.out.println(canConstructMem("skateboard",new String[]{"bo","rd","ate","t","ska","sk","boar"},new HashMap<>())); // false
        System.out.println(canConstructMem("enterapotentpot",new String[]{"a","p","ent","enter","ot","o","t"}, new HashMap<>())); // true
        System.out.println(canConstructMem("eeeeeeeeeeeeeeeeeeeeeeeeeeef",new String[]{"e","ee","eee","eeee","eeeee","eeeeee"},new HashMap<>())); // false
    }

    private static boolean canConstructMem(String target, String[] arr, Map<String,Boolean> map) {
        if(map.containsKey(target)) {
            return map.get(target);
        }
        // termination condition
        if(target.isBlank()) {
            return true;
        }
        for(String s : arr) {
            if(target.startsWith(s)) {
                String truncated = truncate(target,s);
                if(canConstructMem(truncated,arr,map)) {
                    map.put(target,true);
                    return true;
                }
            }
        }
        map.put(target,false);
        return false;
    }

    private static boolean canConstruct(String target, String[] arr) {
        // termination condition
        if(target.isBlank()) {
            return true;
        }
        for(String s : arr) {
            if(target.startsWith(s)) {
                String truncated = truncate(target,s);
                if(canConstruct(truncated,arr)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static String truncate(String target,String toRemove) {
        if(target.length() == toRemove.length()) {
            return "";
        }
        return target.substring(toRemove.length());
    }
}

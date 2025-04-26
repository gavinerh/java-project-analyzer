package codingInterviews.dynamicProgramming.wordConstruct;

import java.util.HashMap;
import java.util.Map;

public class CountConstruct {
    // count the number of ways that the words in the array can be added to the target
    public static void main(String[] args) {
        System.out.println(countConstruct("purple",new String[]{"purp","p","ur","le","purpl"},new HashMap<>()));
        System.out.println(countConstruct("abcdef",new String[]{"ab","abc","cd","def","abcd"},new HashMap<>())); // true
        System.out.println(countConstruct("skateboard",new String[]{"bo","rd","ate","t","ska","sk","boar"},new HashMap<>())); // false
        System.out.println(countConstruct("enterapotentpot",new String[]{"a","p","ent","enter","ot","o","t"}, new HashMap<>())); // true
        System.out.println(countConstruct("eeeeeeeeeeeeeeeeeeeeeeeeeeef",new String[]{"e","ee","eee","eeee","eeeee","eeeeee"},new HashMap<>())); // false
    }

    private static int countConstruct(String target, String[] bank, Map<String,Integer> map) {
        if(map.containsKey(target)) {
            return map.get(target);
        }
        // termination condition
        if(target.isBlank()) {
            return 1;
        }
        int count = 0;
        for(String s : bank) {
            if(target.indexOf(s) == 0) {
                String remainderString = minusPrefix(target,s);
                count += countConstruct(remainderString,bank,map);
            }
        }
        map.put(target,count);
        return count;
    }

    private static String minusPrefix(String target, String prefix) {
        return target.substring(prefix.length());
    }
}

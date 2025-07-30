package MARMSUI.methodcopier.util;

import MARMSUI.methodcopier.model.MethodDetails;

import java.util.Map;

public class RecursiveSearch {
    public static void execute(Map<String, MethodDetails> map, MethodDetails current) {
        if(map.containsKey(MethodListExtractor.getKey(current))) {
            return;
        }
        map.put(MethodListExtractor.getKey(current),current);
        if(current.getChildMethods() != null) {
            for(MethodDetails child : current.getChildMethods()) {
                execute(map,child);
            }
        }
    }
}

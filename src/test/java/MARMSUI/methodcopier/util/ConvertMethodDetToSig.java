package MARMSUI.methodcopier.util;

import MARMSUI.methodcopier.model.MethodDetails;
import MARMSUI.methodcopier.model.MethodSignature;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConvertMethodDetToSig {
    public static List<MethodSignature> convert(Map<String,MethodDetails> detailsList) {
        if(detailsList == null) {
            return null;
        }
        List<MethodSignature> methodSignatureList = new ArrayList<>();
        for(String key : detailsList.keySet()) {
            MethodDetails methodDetails = detailsList.get(key);
            methodSignatureList.add(new MethodSignature(methodDetails.getName(),methodDetails.getListOfParameterTypes()));
        }
        return methodSignatureList;
    }
}

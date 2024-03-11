package MARMSUI.SpecialisedSqlMappingToVo.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TestModel {
    public String mapperName;
    public String packageName;
    public String returnTypeOfDataMethod;
    public String methodName;
    public String combinedName;
    public String responseString;
    public List<String> paramsList;
    public List<String> paramsTypeList;
    public String request;
    public String objectClassName;

    public TestModel() {
    }

    public TestModel(String packageName, String returnTypeOfDataMethod, String methodName, String responseString) {
        this.packageName = packageName;
        this.returnTypeOfDataMethod = returnTypeOfDataMethod;
        this.methodName = methodName;
        this.responseString = responseString;
    }

    public void setParamsList(String args) throws JsonProcessingException {
        paramsList = new ArrayList<>();
        if(!args.isEmpty()){
            // need to check the string to separate into individual obj
//            paramsList.add(args);
            populateList(args,paramsList);
        }
    }
    private static List<String> extractString(String val) {
        List<String> list = new ArrayList<>();
        String obj = "";
        int count = 0;
        if(val.startsWith("{") || val.startsWith("[")) {
            for(int i=0; i<val.length(); i++) {
                if(val.substring(i,i+1).equals("[") || val.substring(i,i+1).equals("]")){
                    continue;
                }
                if(val.substring(i,i+1).equals("{")){
                    count++;
                }
                if(val.substring(i,i+1).equals("}")) {
                    count--;
                }
                obj += val.substring(i,i+1);
                if(count == 0) {
                    if(obj.equals(",")){
                        obj = "";
                        continue;
                    }
                    list.add(obj);
                    obj = "";
                }
            }
        } else {

        }
        return list;
    }



    private List<String> unravelString(String args, List<String> response) {
        int startInd = args.indexOf("{");
        int endInd = args.indexOf("}");
        return null;
    }

    public void setParamsTypeList(String types) {
        paramsTypeList = new ArrayList<>();
        if(!types.isEmpty()) {
            populateList(types, paramsTypeList);
        }
    }

    private void populateList(String content, List<String> list){
        String[] arr = content.split(",");
        for(String cell : arr) {
            list.add(cell);
        }
    }
}

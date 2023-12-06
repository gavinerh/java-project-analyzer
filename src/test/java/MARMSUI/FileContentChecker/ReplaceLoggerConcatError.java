package MARMSUI.FileContentChecker;

import java.util.ArrayList;
import java.util.List;

public class ReplaceLoggerConcatError {
    public static void main(String[] args) {
        String content = " logger.debug(i + \" >> \" + mpt.getPpsMiles() + \" \" + mpt.getPpsSectors() + \" \" + mpt.getTotalValue() + \" \" + mpt.getMonth());";
        String[] contentArr = content.split("\n");
        StringBuilder stringBuilder = new StringBuilder();
        for(String row : contentArr) {
            String temp = null;
            if((row.trim().contains("logger.error") || row.contains("logger.info") || row.contains("logger.debug"))&& row.trim().contains("+")) {
                temp = generateFinalisedLoggerMessage(generateLoggerContent(row.trim()));
                if(temp == null) {
                    temp = row;
                }
            } else {
                temp = row;
            }
            stringBuilder.append(temp + "\n");
        }
        System.out.println(stringBuilder.toString());
    }

    private static String generateLoggerContent(String row) {
        int start = row.indexOf("(") + 1;
        int end = row.indexOf(";") - 1;
        if(end == -2) {
            return null;
        }
        return row.substring(start,end).trim();
    }

    private static String generateFinalisedLoggerMessage(String row) {
        if(row == null) return null;
        List<String> listOfWordsToAppend = new ArrayList<>(); // words in quotes
        List<String> listOfObjectToAppend = new ArrayList<>();
        String tempWord = "";
        String tempObject = "";
        boolean isInsideQuotes = false;
        boolean startsWithWords = false;
        if(row.startsWith("\"")){
            startsWithWords = true;
        }
        for(char c : row.toCharArray()) {
            if(c == '\"') {
                isInsideQuotes = !isInsideQuotes;
                continue;
            }
            if(c == '+') continue;
            if(!isInsideQuotes) {
                if(!tempWord.equals("")) {
                    listOfWordsToAppend.add(tempWord.trim());
                    tempWord = "";
                }
                tempObject += c;
            }
            if(isInsideQuotes) {
                if(!tempObject.equals("")) {
                    listOfObjectToAppend.add(tempObject.trim());
                    tempObject = "";
                }
                tempWord += c;
            }
        }
        if(!tempWord.equals("")) {
            listOfWordsToAppend.add(tempWord.trim());
        }
        if(!tempObject.equals("")) listOfObjectToAppend.add(tempObject.trim());


        int wordCount = 0;
        int objCount = 0;
        String finalString = "logger.error(String.format(\"";
        while(true) {
            tempWord = "";
            if(startsWithWords) {
                try {
                    tempWord = listOfWordsToAppend.get(wordCount);
                } catch (IndexOutOfBoundsException e) {
                    try {
                        if(listOfObjectToAppend.get(objCount) != null) {
                            finalString += "%s";
                        }
                    }catch (IndexOutOfBoundsException e1) {
                        break;
                    }
                }

                wordCount++;
                startsWithWords = false;
            } else {
                try {
                    tempWord = listOfObjectToAppend.get(objCount);
                } catch (IndexOutOfBoundsException e) {
                    try {
                        if(listOfWordsToAppend.get(wordCount) != null) {
                            finalString += listOfWordsToAppend.get(wordCount);
                        }
                    } catch (IndexOutOfBoundsException e1) {
                        break;
                    }
                }
                objCount++;
                tempWord = "%s";
                startsWithWords = true;
            }
            finalString += tempWord + " ";
        }
        finalString += "\",";

        for(String obj : listOfObjectToAppend) {
            finalString += obj + ",";
        }
        if(finalString.trim().equals("")){
            return null;
        }
        return finalString.substring(0,finalString.length()-1) + "));";
    }


}

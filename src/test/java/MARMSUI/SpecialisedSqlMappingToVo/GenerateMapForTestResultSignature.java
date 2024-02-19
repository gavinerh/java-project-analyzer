package MARMSUI.SpecialisedSqlMappingToVo;

public class GenerateMapForTestResultSignature {
    public static void main(String[] args) {
        String result = " ~631\n" +
                "\"~14852\n" +
                ",~4044\n" +
                "-~692\n" +
                ".~172\n" +
                "/~19\n" +
                "0~4170\n" +
                "1~1872\n" +
                "2~1642\n" +
                "3~821\n" +
                "4~697\n" +
                "5~852\n" +
                "6~946\n" +
                "7~738\n" +
                "8~952\n" +
                "9~1025\n" +
                ":~4771\n" +
                "A~821\n" +
                "B~517\n" +
                "C~787\n" +
                "D~456\n" +
                "E~174\n" +
                "F~960\n" +
                "G~80\n" +
                "H~189\n" +
                "I~1286\n" +
                "J~39\n" +
                "K~135\n" +
                "L~444\n" +
                "M~538\n" +
                "N~1575\n" +
                "O~637\n" +
                "P~476\n" +
                "Q~366\n" +
                "R~567\n" +
                "S~887\n" +
                "T~742\n" +
                "U~230\n" +
                "V~225\n" +
                "W~42\n" +
                "X~154\n" +
                "Y~304\n" +
                "Z~52\n" +
                "[~35\n" +
                "]~35\n" +
                "_~107\n" +
                "a~2901\n" +
                "b~508\n" +
                "c~1665\n" +
                "d~1909\n" +
                "e~3401\n" +
                "f~959\n" +
                "g~1060\n" +
                "h~638\n" +
                "i~2122\n" +
                "j~5\n" +
                "k~719\n" +
                "l~2068\n" +
                "m~775\n" +
                "n~2209\n" +
                "o~1628\n" +
                "p~1709\n" +
                "q~33\n" +
                "r~2214\n" +
                "s~1603\n" +
                "t~3772\n" +
                "u~577\n" +
                "v~277\n" +
                "w~50\n" +
                "x~263\n" +
                "y~335\n" +
                "z~19\n" +
                "{~172\n" +
                "}~172";
        String[] resultArr = result.split("\n");
        printMethodToGenerateMap(resultArr);
    }

    private static void printMethodToGenerateMap(String[] arr) {
        printMethodDeclaration();
        for(String row : arr) {
            String[] rowArr = row.split("~");
            System.out.println(String.format("map.put(\"%s\",%s);",rowArr[0],rowArr[1]));
        }
        System.out.println("return map;");
        System.out.println("}");
    }

    private static void printMethodDeclaration() {
        System.out.println(String.format("private Map<String,Integer> generateSignatureForUser() {"));
        System.out.println("Map<String,Integer> map = new HashMap<>();");
    }
}

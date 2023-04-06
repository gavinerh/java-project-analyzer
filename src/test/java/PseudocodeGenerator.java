//import model.Variable;
//
//public class PseudocodeGenerator {
//    public static void main(String[] args) {
//        String filename = "QualificationServiceImpl";
//        String methodString = "";
//        generatePseudoCode(methodString);
//    }
//
//    private static void generatePseudoCode(String fullMethodDeclaration){
//        Variable[] parameters = getRequestParameters(fullMethodDeclaration);
//    }
//
//    private static Variable[] getRequestParameters(String fullMethodDeclaration){
//        String[] sentencesArr = fullMethodDeclaration.split("\n");
//        boolean methodDeclarationComplete = false;
//        for(String sentence : sentencesArr){
//            if(sentence.trim().equals("")) continue;
//            if(sentence.contains("public ") || sentence.contains("protected ") || sentence.contains("private ")){
//                if(sentence.contains("(")){
//                    String paramString = sentence.substring(sentence.indexOf("("));
//                }
//            }
//        }
//    }
//}

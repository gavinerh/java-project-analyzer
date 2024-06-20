import java.io.IOException;

public class TestingPurposes {


    public static void main(String[] args) throws IOException {
        Object t = "Y";
        System.out.println(t.equals("Y"));
        String val = "Re-instate Reserve Value\tchkReinstateRsrvVal\n" +
                "type of qualification\tcboQualType\n" +
                "Disable Early Requalification\tchkRequalDisable\n" +
                "Change Qual start Date\tchkChangeQualStart\n" +
                "For (months)\tcboFor\n" +
                "Qualify As\tcboQualType\n" +
                "current date\trdoCurrentDate\n" +
                "expiry date\trdoExpiryDate\n" +
                "other\trdoOtherDate\n" +
                "Award tier bonus\tchkAwardTier\n" +
                "Reinstate cumulative value\tchkReinstate\n" +
                "Increment years\tchkYears\n" +
                "Recalculate Reserve Value\tchkPostRecalReserveVal\n" +
                "(Post Requalification)\tlblPost";
        String[] lines = val.split("\n");
        printStringVariables(lines);
    }

    private static void printStringVariables(String[] lines) {
        for(String line : lines) {
            String[] parts = line.split("\t");
            System.out.println(String.format("public static final String %s = \"%s\";", parts[1], parts[0]));
        }
    }

    private static void printInvestmentReturns() {
        double annualAmountAdded = 45000;
        double annualIncrement = 2000;
        double totalInvested = 0;
        double totalPrincipalInvested = 0;
        int totalYears = 30;
        double interestRate = 0.1;
        for(int i = 0; i < totalYears; i++) {
            if(i == 0) {
                totalInvested = annualAmountAdded;
                totalPrincipalInvested = annualAmountAdded;
            } else {
                totalInvested += annualAmountAdded + annualIncrement;
                totalPrincipalInvested += annualAmountAdded + annualIncrement;
            }
            double interestEarned = totalInvested * interestRate;
            totalInvested += interestEarned;
            System.out.println(String.format("Year %d: %f", i, interestEarned));
        }
        System.out.println("Total invested: " + totalInvested);
        System.out.println("Total Principal: " + totalPrincipalInvested);
        System.out.println("Total returns: " + String.valueOf(totalInvested - totalPrincipalInvested));
    }

}
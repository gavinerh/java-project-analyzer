import org.mapstruct.factory.Mappers;

import java.io.IOException;

public class TestingPurposes {


    private static MapperClone mapperClone = Mappers.getMapper(MapperClone.class);
    public static void main(String[] args) throws IOException, CloneNotSupportedException {
        Mod mod = new Mod();
        mod.setName("test");
        Mod mod2 = mapperClone.clone(mod);
        mod.setName("changed name");
        System.out.println(mod2);
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
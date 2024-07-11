import org.mapstruct.factory.Mappers;

import java.io.IOException;

public class TestingPurposes {


    private static MapperClone mapperClone = Mappers.getMapper(MapperClone.class);
    public static void main(String[] args) throws IOException, CloneNotSupportedException {
        String val1  = "chrgRte.setPrtCd(rs.getString(\"PRT_CD\"));\n" +
                "                chrgRte.setStartDt(rs.getDate(\"START_DT\"));\n" +
                "                chrgRte.setEndDt(rs.getDate(\"END_DT\"));\n" +
                "                chrgRte.setNormalChrgRte(rs.getDouble(\"NORMAL_CHRG_RT\"));\n" +
                "                chrgRte.setPromoChrgRte(rs.getDouble(\"PROMO_CHRG_RT\"));\n" +
                "                chrgRte.setRdpnChrgRte(rs.getDouble(\"RDPN_CHRG_RT\"));\n" +
                "                chrgRte.setCurrencyCd(rs.getString(\"CURRENCY_CD\"));\n" +
                "                chrgRte.setUSDConvRte(rs.getDouble(\"USD_CONVERT_RT\"));";
        String[] split = val1.split("\n");
        String toPrint = "";
        int count = 0;
        for(String row : split) {
            String col = extractColumnString(row);
            toPrint += col + ",";
            count++;
        }
        toPrint = toPrint.substring(0,toPrint.length()-1);
        System.out.println(toPrint);
        System.out.println(count);
    }

    private static String extractColumnString(String val) {
        String identifier = ".get";
        int start = val.indexOf(identifier);
        start = val.indexOf("(\"", start) + 2;
        int end = val.indexOf("\"", start);
        return val.substring(start,end);
    }

    private static String extractVariableName(String param) {
        // extract intID from "pStmt.setLong(1, intID);"
        String trimmed = param.trim();
        int start = trimmed.indexOf(",") + 1;
        int end = trimmed.length()-1;
        return trimmed.substring(start, end);
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
import org.mapstruct.factory.Mappers;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class TestingPurposes {


    private static MapperClone mapperClone = Mappers.getMapper(MapperClone.class);

    public static void main(String[] args) throws IOException, CloneNotSupportedException {
        boolean isValid = validateCustomerIDFormat("8000000000", "KF");
        System.out.println(isValid);
    }


    private static boolean validateCustomerIDFormat(String customerID,String programCode) {
        boolean isValid = true;

        if ((programCode!=null)&&
                (programCode.trim().equalsIgnoreCase("KF"))) {
            customerID = customerID.trim();
            int length = customerID.length();
            if (length != 10) {
                isValid = false;
                return isValid;
            }

            char [] charArray = customerID.toCharArray();
            for (int i=0;i<charArray.length;i++) {
                if (!Character.isDigit(charArray[i])) {
                    isValid = false;
                    return isValid;
                }
            }

            if ((charArray[0]!='8')&&
                    (charArray[0]!='1')&&
                    (charArray[0]!='2')) {
                isValid = false;
                return isValid;
            }

            String first9 = customerID.substring( 0, 9 );
            int checkDigit = (int) ( Long.parseLong( first9 ) % 7 + 3 );
            int lastDigit = Character.getNumericValue( charArray[9] );
            if (checkDigit!=lastDigit) {
                isValid = false;
                return isValid;
            }
            if ((lastDigit<3)||(lastDigit>9)) {
                isValid = false;
                return isValid;
            }

            return isValid;
        }

        return false;
    }

}
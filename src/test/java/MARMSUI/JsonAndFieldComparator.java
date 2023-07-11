package MARMSUI;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class JsonAndFieldComparator {
    public static void main(String[] args) {
        String jsonStr = "\"givenName\": \"Testing\",\n" +
                "        \"nationality\": \"SG\",\n" +
                "        \"birthDate\": \"1985-10-22 08:10:00\",\n" +
                "        \"gender\": \"M\",\n" +
                "        \"countryOfResidence\": \"AU\",\n" +
                "        \"city\": null,\n" +
                "        \"states\": null,\n" +
                "        \"zipCode\": null,\n" +
                "        \"email\": \"saa_devmail@sqdev.com.sg\",\n" +
                "        \"companyName\": \"\",\n" +
                "        \"corporateCode\": null,\n" +
                "        \"title\": \"Mr\",\n" +
                "        \"countryOfBirth\": \"SG\",\n" +
                "        \"maritalStatus\": \"M\",\n" +
                "        \"contactNoHome\": null,\n" +
                "        \"contactNoOffice\": null,\n" +
                "        \"contactNoMobile\": null,\n" +
                "        \"customerPin\": \"******\",\n" +
                "        \"siaPromotion\": null,\n" +
                "        \"partnerPromoCode\": null,\n" +
                "        \"kfPromotion\": null,\n" +
                "        \"eNews\": null,\n" +
                "        \"passportName\": \"Testing\",\n" +
                "        \"passportNo\": \"F12345678\",\n" +
                "        \"passportExpDate\": null,\n" +
                "        \"passportCtryOfIss\": null,\n" +
                "        \"mailingAddressInd\": \"H\",\n" +
                "        \"address\": [],\n" +
                "        \"phoneNumber\": [],\n" +
                "        \"mailerPref\": [],\n" +
                "        \"altEmailAddress\": null,\n" +
                "        \"programCode\": \"KF\",\n" +
                "        \"spokenLanguage\": [],\n" +
                "        \"writtenLanguage\": [],\n" +
                "        \"srcOfPrgInfoCode\": \"MG\",\n" +
                "        \"numberOfChildern\": 0,\n" +
                "        \"positionHeld\": null,\n" +
                "        \"professionalStatus\": null,\n" +
                "        \"business\": null,\n" +
                "        \"seatPrefPClassCd\": null,\n" +
                "        \"seatPrefYClassCd\": null,\n" +
                "        \"seatPrefJClassCd\": null,\n" +
                "        \"deckMegatopJClass\": null,\n" +
                "        \"medicalMealPrefferedCd\": null,\n" +
                "        \"medicalMealPrefferedText\": null,\n" +
                "        \"religiousMealPrefferedCd\": null,\n" +
                "        \"religiousMealPrefferedText\": null,\n" +
                "        \"splInfltRequirement\": null,\n" +
                "        \"expiredMiles\": 0,\n" +
                "        \"currentTier\": \"KrisFlyer\",\n" +
                "        \"tierCode\": \"K\",\n" +
                "        \"ffpMiles\": 153072,\n" +
                "        \"memberSince\": \"1999-04-14 00:00:00\",\n" +
                "        \"yearsQualified\": \"2\",\n" +
                "        \"kfStatusChangeDate\": \"1999-05-15 00:00:00\",\n" +
                "        \"otherFFPDetails\": null,\n" +
                "        \"guardianInfo\": null,\n" +
                "        \"aircraftSeatPref\": [],\n" +
                "        \"customerID\": \"8000350716\",\n" +
                "        \"internalID\": 8000350716,\n" +
                "        \"stopMailInfos\": [],\n" +
                "        \"hvInd\": \"N\",\n" +
                "        \"socialMediaDetails\": null,\n" +
                "        \"seatPrefSClassCd\": null,\n" +
                "        \"introducerProgramCode\": null,\n" +
                "        \"referralKFNumber\": null,\n" +
                "        \"principalTierCode\": null,\n" +
                "        \"principalTierExpiry\": null,\n" +
                "        \"allianceTier\": \" \",\n" +
                "        \"isEUresident\": null,\n" +
                "        \"password\": null,\n" +
                "        \"nok\": null,\n" +
                "        \"ktnNo\": null,\n" +
                "        \"stopIndicator\": null,\n" +
                "        \"specialCusType\": null,\n" +
                "        \"reasonForChange\": null,\n" +
                "        \"redressNo\": null,\n" +
                "        \"rdpnWLNotification\": \"Y\",\n" +
                "        \"expiringMiles\": 0,\n" +
                "        \"isCorpTraveller\": false,\n" +
                "        \"corporateId\": null,\n" +
                "        \"corporateType\": null,\n" +
                "        \"studentValidTill\": null,\n" +
                "        \"emailVerified\": true,\n" +
                "        \"mobileVerified\": false,\n" +
                "        \"stopMobileIndicator\": false,\n" +
                "        \"stopEmailIndicator\": false,\n" +
                "        \"familyInfo\": null,\n" +
                "        \"redemptionBlockInd\": \"N\",\n" +
                "        \"srcOfEnrollment\": \"KMS\",\n" +
                "        \"lastReadDt\": \"2023-06-22 12:22:28\",\n" +
                "        \"subProgram\": null,\n" +
                "        \"nomineeBlockOutDays\": 3,\n" +
                "        \"familyName\": \"Chandran\",\n" +
                "        \"profileStatus\": \"Complete\",\n" +
                "        \"lifestyleDetails\": null,\n" +
                "        \"eCardOption\": null,\n" +
                "        \"nomineeDetails\": [],\n" +
                "        \"quickLinkSelected\": null,\n" +
                "        \"agentID\": \"\",\n" +
                "        \"lchgDateCusPers\": null,\n" +
                "        \"lchgUserIdCusPers\": null,\n" +
                "        \"lastChangeUserIdCusAddnInfo\": null,\n" +
                "        \"lastChangeDateCusAddnInfo\": null,\n" +
                "        \"lastChangeDateCusInfltPref\": null,\n" +
                "        \"lastChangeDateCusOinfltPref\": null,\n" +
                "        \"lastChangeDateCusPin\": null,\n" +
                "        \"lastChangeDateEnrol\": null,\n" +
                "        \"updatedWithPreference\": true,\n" +
                "        \"leisureYearlyTrvlRgn\": null,\n" +
                "        \"businessYEarlyTrvlRgn\": null,\n" +
                "        \"correspondenceName\": null,\n" +
                "        \"isYec\": false";

        String fieldString = "familyName\n" +
                "givenName\n" +
                "nationality\n" +
                "birthDate\n" +
                "gender\n" +
                "countryOfResidence\n" +
                "city\n" +
                "states\n" +
                "zipCode\n" +
                "email1\n" +
                "companyName\n" +
                "corporateCode\n" +
                "title\n" +
                "countryOfBirth\n" +
                "maritalStatus\n" +
                "contactNoHome\n" +
                "contactNoOffice\n" +
                "contactNoMobile\n" +
                "customerPin\n" +
                "siaPromotion\n" +
                "partnerPromoCode\n" +
                "kfPromotion\n" +
                "eNews\n" +
                "passportName\n" +
                "passportNo\n" +
                "passportExpDate\n" +
                "passportCtryOfIss\n" +
                "mailingAddressInd\n" +
                "address\n" +
                "phoneNumber\n" +
                "mailerPref\n" +
                "altEmailAddress\n" +
                "programCode\n" +
                "spokenLanguage\n" +
                "writtenLanguage\n" +
                "srcOfPrgInfoCode\n" +
                "numberOfChildern\n" +
                "positionHeld\n" +
                "professionalStatus\n" +
                "business\n" +
                "seatPrefPClassCd\n" +
                "seatPrefYClassCd\n" +
                "seatPrefJClassCd\n" +
                "deckMegatopJClass\n" +
                "medicalMealPrefferedCd\n" +
                "medicalMealPrefferedText\n" +
                "religiousMealPrefferedCd\n" +
                "religiousMealPrefferedText\n" +
                "splInfltRequirement\n" +
                "expiredMiles\n" +
                "currentTier\n" +
                "tierCode\n" +
                "ffpMiles\n" +
                "memberSince\n" +
                "kfStatusChangeDate\n" +
                "otherFFPDetails\n" +
                "guardianInfo\n" +
                "aircraftSeatPref\n" +
                "customerID\n" +
                "internalID\n" +
                "stopMailInfos\n" +
                "profileStatus\n" +
                "hvInd\n" +
                "lifestyleDetails\n" +
                "socialMediaDetails\n" +
                "seatPrefSClassCd\n" +
                "introducerProgramCode\n" +
                "referralKFNumber\n" +
                "principalTierCode\n" +
                "principalTierExpiry\n" +
                "allianceTier\n" +
                "eCardOption\n" +
                "isEUresident\n" +
                "password\n" +
                "nomineeDetails\n" +
                "nok\n" +
                "ktn\n" +
                "stopIndicator\n" +
                "specialCusType\n" +
                "reasonForChange\n" +
                "redressNo\n" +
                "rdpnWLNotification\n" +
                "expiringMiles\n" +
                "isCorpTraveller\n" +
                "corporateId\n" +
                "corporateType\n" +
                "studentValidTill\n" +
                "emailVerified\n" +
                "mobileVerified\n" +
                "stopMobileIndicator\n" +
                "stopEmailIndicator\n" +
                "familyInfo\n" +
                "redemptionBlockInd\n" +
                "srcOfEnrollment\n" +
                "lastReadDt\n" +
                "subProgram";

        Set<String> jsonFieldSet = extractJsonToSettOfFields(jsonStr);
        String[] responseFieldArr = extractResponseFieldsToArr(fieldString);
        compareJsonSetAndResponseField(jsonFieldSet, responseFieldArr);
    }

    private static void compareJsonSetAndResponseField(Set<String> jsonSet, String[] responseFieldArr) {
        Set<String> populatedResponse = Arrays.stream(responseFieldArr).collect(Collectors.toSet());
//        if(jsonSet.size() != responseFieldArr.length) {
//            throw new RuntimeException("Json set and response field are different in length");
//        }
        for(String field : jsonSet) {
            if(!populatedResponse.contains(field)) {
                System.out.println(field + " --- in json is not contained in the populated response");
            }
        }
    }

    private static String[] extractResponseFieldsToArr(String fieldString) {
        return fieldString.split("\n");
    }

    private static Set<String> extractJsonToSettOfFields(String jsonStr) {
        String[] jsonStrArr = jsonStr.split("\n");
        Set<String> fieldsSet = new HashSet<>();
        for(String field : jsonStrArr) {
            int startIndex = field.indexOf("\"") + 1;
            int endIndex = field.indexOf("\"", startIndex);
            fieldsSet.add(field.substring(startIndex,endIndex));
        }
        if(jsonStrArr.length != fieldsSet.size()) {
            throw new RuntimeException("Json Arr size is different from set");
        }
        return fieldsSet;
    }
}

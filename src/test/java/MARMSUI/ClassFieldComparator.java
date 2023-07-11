package MARMSUI;

import java.util.*;

public class ClassFieldComparator {
    public static void main(String[] args) {
        String longerClassField = "private String customerID;\n" +
                "    private String familyName;\n" +
                "    private String givenName;\n" +
                "    private String nationality;\n" +
                "    private String birthDate;\n" +
                "    private String gender;\n" +
                "    private String countryOfResidence;\n" +
                "    private String city;\n" +
                "    private String states;\n" +
                "    private String zipCode;\n" +
                "    private String email1;\n" +
                "    private String companyName;\n" +
                "    private String corporateCode;\n" +
                "    private String title;\n" +
                "    private String countryOfBirth;\n" +
                "    private String maritalStatus;\n" +
                "    private String contactNoHome;\n" +
                "    private String contactNoOffice;\n" +
                "    private String contactNoMobile;\n" +
                "    private String customerPin;\n" +
                "    private String siaPromotion;\n" +
                "    private String partnerPromoCode;\n" +
                "    private String kfPromotion;\n" +
                "    private String eNews;\n" +
                "    private String passportName;\n" +
                "    private String passportNo;\n" +
                "    private String passportExpDate;\n" +
                "    private String passportCtryOfIss;\n" +
                "    private String programCode;\n" +
                "    private String altEmailAddress;\n" +
                "    private String srcOfPrgInfoCode;\n" +
                "    private String positionHeld;\n" +
                "    private String principalTierCode;\n" +
                "    private String professionalStatus;\n" +
                "    private String business;\n" +
                "    private String seatPrefPClassCd;\n" +
                "    private String seatPrefYClassCd;\n" +
                "    private String seatPrefJClassCd;\n" +
                "    private String deckMegatopJClass;\n" +
                "    private String religiousMealPrefferedCd;\n" +
                "    private String religiousMealPrefferedText;\n" +
                "    private String splInfltRequirement;\n" +
                "    private String currentTier;\n" +
                "    private String correspondenceName;\n" +
                "    private long internalID;\n" +
                "    private String mailingAddressInd;\n" +
                "    private int numberOfChildern;\n" +
                "    private long expiredMiles;\n" +
                "    private String tierCode;\n" +
                "    private long ffpMiles;\n" +
                "    private String memberSince;\n" +
                "    private CustomerAddressVo[] address;\n" +
                "    private CustomerPhoneDetailsVo[] phoneNumber;\n" +
                "    private CustomerPhoneVo[] phoneNumberVo;\n" +
                "    private CustomerDirmailPrefVo[] mailerPref;\n" +
                "    private CustomerOffpInfoVo[] otherFFPDetails;\n" +
                "    private CustomerGuardianVo[] guardianInfo;\n" +
                "    private CustomerSeatPrefVo[] aircraftSeatPref;\n" +
                "    private String[] spokenLanguage;\n" +
                "    private String[] writtenLanguage;\n" +
                "    private String[] medicalMealPrefferedCd;\n" +
                "    private String[] medicalMealPrefferedText;\n" +
                "    private String[] quickLinkSelected;\n" +
                "    private String agentID;\n" +
                "    private boolean isYec;\n" +
                "    private String lchgDateCusPers;\n" +
                "    private String lchgUserIdCusPers;\n" +
                "    private String lastChangeUserIdCusAddnInfo;\n" +
                "    private String lastChangeDateCusAddnInfo;\n" +
                "    private String lastChangeDateCusInfltPref;\n" +
                "    private String lastChangeDateCusOinfltPref;\n" +
                "    private String lastChangeDateCusPin;\n" +
                "    private String lastChangeDateEnrol;\n" +
                "    private boolean updatedWithPreference;\n" +
                "    private Long[] leisureYearlyTrvlRgn;\n" +
                "    private Long[] businessYEarlyTrvlRgn;\n" +
                "    private CustomerStopmailInfoVo[] stopMailInfos;\n" +
                "    private String profileStatus;\n" +
                "    private String hvInd;\n" +
                "    private CustomerLifestyleVo[] lifestyleDetails;\n" +
                "    private CustomerSocialMediaVo[] socialMediaDetails;\n" +
                "    private String seatPrefSClassCd;\n" +
                "    private String isEUresident;\n" +
                "    private String password;\n" +
                "    private String redressNo;\n" +
                "    private String ktnNo;\n" +
                "    private String introducerProgramCode;\n" +
                "    private String referralKFNumber;\n" +
                "    private String allianceTier;\n" +
                "    private String eCardOption;\n" +
                "    private CustomerCreditCardVo[] creditCardVos;\n" +
                "    private CustomerOinfltPrefVo[] customerOinfltPrefVos;\n" +
                "    private String acvValue;\n" +
                "    private String acvOverrideFlg;\n" +
                "    private String businessPreferenceClass;\n" +
                "    private String leisurePreferenceClass;\n" +
                "    private String[] regionsVisited;\n" +
                "    private String[] hotelsPreffered;\n" +
                "    private String[] carRentalPreffered;\n" +
                "    private Long businessYearlyTravel;\n" +
                "    private Long leisureYearlyTravel;\n" +
                "    private String kfStatusChangeDate;\n" +
                "    private String channelCode;\n" +
                "    private CustomerHobbiesVo[] hobbies;\n" +
                "    private CustomerFamilyInfoVo[] familyInfo;\n" +
                "    private CustomerSupplInfoVo[] supplInfo;\n" +
                "    private CustomerAlternativeNameVo[] alternativeName;\n" +
                "    private RedemptionNomineesVo[] nomineeDetails;\n" +
                "    private CustomerNextKinVo[] cusNextKins;\n" +
                "    private boolean isEnrol;\n" +
                "    private long introducerInternalId;\n" +
                "    private String specialCusType;\n" +
                "    private String reasonForChange;\n" +
                "    private String rdpnWLNotification;\n" +
                "    private OTPValidationRequest otpValidationRequest;\n" +
                "    private String principalTierExpiry;\n" +
                "    private Long principleInternalId;\n" +
                "    private Long expiringMiles;\n" +
                "    private boolean isCorpTraveller;\n" +
                "    private String corporateId;\n" +
                "    private String corporateType;\n" +
                "    private String studentValidTill;\n" +
                "    private boolean stopMobileIndicator;\n" +
                "    private boolean stopEmailIndicator;\n" +
                "    private boolean emailVerified;\n" +
                "    private boolean mobileVerified;\n" +
                "    private String srcOfEnrollment;\n" +
                "    private boolean isUpdateCustomer;\n" +
                "    private String redemptionBlockInd;\n" +
                "    private String lastReadDate;\n" +
                "    private String rcreDate;\n" +
                "    private SubProgramVo[] subProgram;\n" +
                "    private String pnrRef;\n" +
                "    private String etLastDigits;";
        
        String shorterClassField = "private String familyName;\n" +
                "    private String givenName;\n" +
                "    private String nationality;\n" +
                "    private String birthDate;\n" +
                "    private String gender;\n" +
                "    private String countryOfResidence;\n" +
                "    private String isEUresident;\n" +
                "    private String city;\n" +
                "    private String states;\n" +
                "    private String zipCode;\n" +
                "    private String email1;\n" +
                "    private String companyName;\n" +
                "    private String corporateCode;\n" +
                "    private String title;\n" +
                "    private String countryOfBirth;\n" +
                "    private String maritalStatus;\n" +
                "    private String contactNoHome;\n" +
                "    private String contactNoOffice;\n" +
                "    private String contactNoMobile;\n" +
                "    private String customerPin;\n" +
                "    private String siaPromotion;\n" +
                "    private String partnerPromoCode;\n" +
                "    private String kfPromotion;\n" +
                "    private String eNews;\n" +
                "    private String passportName;\n" +
                "    private String passportNo;\n" +
                "    private String passportExpDate;\n" +
                "    private String passportCtryOfIss;\n" +
                "    private String mailingAddressInd;\n" +
                "    private CustomerAddressVo[] address;\n" +
                "    private CustomerPhoneRespVo[] phoneNumber;\n" +
                "    private CustomerDirmailPrefVo[] mailerPref;\n" +
                "    private String altEmailAddress;\n" +
                "    private String programCode;\n" +
                "    private String[] spokenLanguage;\n" +
                "    private String[] writtenLanguage;\n" +
                "    private String srcOfPrgInfoCode;\n" +
                "    private int numberOfChildern;\n" +
                "    private String positionHeld;\n" +
                "    private String principalTierCode;\n" +
                "    private String principalTierExpiry;\n" +
                "    private String professionalStatus;\n" +
                "    private String business;\n" +
                "    private String seatPrefPClassCd;\n" +
                "    private String seatPrefYClassCd;\n" +
                "    private String seatPrefJClassCd;\n" +
                "    private String deckMegatopJClass;\n" +
                "    private String[] medicalMealPrefferedCd;\n" +
                "    private String[] medicalMealPrefferedText;\n" +
                "    private String religiousMealPrefferedCd;\n" +
                "    private String religiousMealPrefferedText;\n" +
                "    private String splInfltRequirement;\n" +
                "    private long expiredMiles;\n" +
                "    private String currentTier;\n" +
                "    private String tierCode;\n" +
                "    private long ffpMiles;\n" +
                "    private String memberSince;\n" +
                "    private String kfStatusChangeDate;\n" +
                "    private CustomerOffpInfoVo[] otherFFPDetails;\n" +
                "    private CustomerGuardianResponseVo[] guardianInfo;\n" +
                "    private CustomerSeatPrefVo[] aircraftSeatPref;\n" +
                "    private String customerID;\n" +
                "    private long internalID;\n" +
                "    private CustomerStopmailInfoRespVo[] stopMailInfos;\n" +
                "    private String profileStatus;\n" +
                "    private String hvInd;\n" +
                "    private CustomerLifestyleResponseVo[] lifestyleDetails;\n" +
                "    private CustomerSocialMediaResponseVo[] socialMediaDetails;\n" +
                "    private String seatPrefSClassCd;\n" +
                "    private String introducerProgramCode;\n" +
                "    private String referralKFNumber;\n" +
                "    private String allianceTier;\n" +
                "    private String eCardOption;\n" +
                "    private String password;\n" +
                "    private RedemptionNomineesVo[] nomineeDetails;\n" +
                "    private CustomerNextKinVo[] nok;\n" +
                "    private String ktnNo;\n" +
                "    private StopIndcatorVo[] stopIndicator;\n" +
                "    private String specialCusType;\n" +
                "    private String reasonForChange;\n" +
                "    private String redressNo;\n" +
                "    private String[] quickLinkSelected;\n" +
                "    private String agentID;\n" +
                "    private boolean isYec;\n" +
                "    private String lchgDateCusPers;\n" +
                "    private String lchgUserIdCusPers;\n" +
                "    private String lastChangeUserIdCusAddnInfo;\n" +
                "    private String lastChangeDateCusAddnInfo;\n" +
                "    private String lastChangeDateCusInfltPref;\n" +
                "    private String lastChangeDateCusOinfltPref;\n" +
                "    private String lastChangeDateCusPin;\n" +
                "    private String lastChangeDateEnrol;\n" +
                "    private boolean updatedWithPreference;\n" +
                "    private int[] leisureYearlyTrvlRgn;\n" +
                "    private int[] businessYEarlyTrvlRgn;\n" +
                "    private String rdpnWLNotification;\n" +
                "    private String correspondenceName;\n" +
                "    private Long expiringMiles;\n" +
                "    private boolean isCorpTraveller;\n" +
                "    private String corporateId;\n" +
                "    private String corporateType;\n" +
                "    private String studentValidTill;\n" +
                "    private boolean stopMobileIndicator;\n" +
                "    private boolean stopEmailIndicator;\n" +
                "    private boolean emailVerified;\n" +
                "    private boolean mobileVerified;\n" +
                "    private String srcOfEnrollment;\n" +
                "    private CustomerFamilyResponse[] familyInfo;\n" +
                "    private String redemptionBlockInd;\n" +
                "    private SubProgramVo[] subProgram;\n" +
                "    private String lastReadDt;";
        Set<String> fieldsNotPresentList = getListOfMissingFields(longerClassField, shorterClassField);
        System.out.println(fieldsNotPresentList.size());
    }

    private static Set<String> getListOfMissingFields(String longerField, String shorterField) {
        String[] longerFieldArr = longerField.split("\n");
        String[] shorterFieldArr = shorterField.split("\n");
        System.out.println("Length of longer field: " + longerFieldArr.length);
        System.out.println("Length of shorter field: " + shorterFieldArr.length);
        Map<String, String> longerSet = new HashMap<>();
        System.out.println("populating longer set----------------------");
        for(String field : longerFieldArr) {
            extractFieldAndAddToSet(longerSet,field);
        }
        Map<String, String> shorterSet = new HashMap<>();
        System.out.println("populating shorter set---------------------");
        for(String field : shorterFieldArr) {
            extractFieldAndAddToSet(shorterSet,field);
        }
        Set<String> fieldsNotPresent = new HashSet<>();
        for(String fieldName : longerSet.keySet()) {
            if(!shorterSet.containsKey(fieldName)) {
                fieldsNotPresent.add(fieldName);
                System.out.println(fieldName);
            }
        }
        for(String fieldname : shorterSet.keySet()) {
            if(longerSet.containsKey(fieldname)) {
                String longerSetType = longerSet.get(fieldname);
                String shorterSetType = shorterSet.get(fieldname);
                if(longerSetType.equals(shorterSetType)){
                    System.out.println(fieldname + " -------------- " + fieldname);
                }

                longerSet.remove(fieldname);
            } else {
                System.out.println("Longer set does not contain field: " + fieldname);
                fieldsNotPresent.add(fieldname);
            }
        }
        System.out.println("---------------------------------");
        return fieldsNotPresent;
    }

    private static void extractFieldAndAddToSet(Map<String,String> set, String field) {
        String[] fieldSplit = field.trim().split(" ");
        String fieldName = fieldSplit[2].substring(0,fieldSplit[2].length()-1);
        String type = fieldSplit[1];
        set.put(fieldName, type);
    }
}

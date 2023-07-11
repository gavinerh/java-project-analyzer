package MARMSUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MethodParamToFieldMapper {
    public static void main(String[] args) {
        String argumentsForMethod = "customerInfoVo.getFamilyName(), customerInfoVo.getGivenName(),\n" +
                "\t\t\t\tcustomerInfoVo.getNationality(), customerInfoVo.getBirthDate(), customerInfoVo.getGender(),\n" +
                "\t\t\t\tcustomerInfoVo.getCountryOfResidence(), customerInfoVo.getCity(), customerInfoVo.getStates(),\n" +
                "\t\t\t\tcustomerInfoVo.getZipCode(), customerInfoVo.getEmail(), customerInfoVo.getCompanyName(),\n" +
                "\t\t\t\tcustomerInfoVo.getCorporateCode(), customerInfoVo.getTitle(), customerInfoVo.getCountryOfBirth(),\n" +
                "\t\t\t\tcustomerInfoVo.getMaritalStatus(), customerInfoVo.getContactNoHome(),\n" +
                "\t\t\t\tcustomerInfoVo.getContactNoOffice(), customerInfoVo.getContactNoOffice(),\n" +
                "\t\t\t\t customerInfoVo.getCustomerPin(),\n" +
                "\t\t\t\tcustomerInfoVo.getSiaPromotion(), customerInfoVo.getPartnerPromoCode(), customerInfoVo.getKfPromotion(),\n" +
                "\t\t\t\tcustomerInfoVo.geteNews(), customerInfoVo.getPassportName(), customerInfoVo.getPassportNo(),\n" +
                "\t\t\t\tcustomerInfoVo.getPassportExpDate(), customerInfoVo.getPassportCtryOfIss(), mailAddrInd,\n" +
                "\t\t\t\tcustomerInfoVo.getAddress(), phoneVo, newMailerPref, customerInfoVo.getAltEmailAddress(),\n" +
                "\t\t\t\tcustomerInfoVo.getProgramCode(), spokenLang, writtenLang, customerInfoVo.getSrcOfPrgInfoCode(),\n" +
                "\t\t\t\tcustomerInfoVo.getNumberOfChildern(), customerInfoVo.getPositionHeld(),\n" +
                "\t\t\t\tcustomerInfoVo.getProfessionalStatus(), customerInfoVo.getBusiness(),\n" +
                "\t\t\t\tcustomerInfoVo.getSeatPrefPClassCd(), customerInfoVo.getSeatPrefYClassCd(),\n" +
                "\t\t\t\tcustomerInfoVo.getSeatPrefJClassCd(), customerInfoVo.getDeckMegatopJClass(),\n" +
                "\t\t\t\tcustomerInfoVo.getMedicalMealPrefferedCd(), customerInfoVo.getMedicalMealPrefferedText(),\n" +
                "\t\t\t\tcustomerInfoVo.getReligiousMealPrefferedCd(), customerInfoVo.getReligiousMealPrefferedText(),\n" +
                "\t\t\t\tcustomerInfoVo.getSplInfltRequirement(), customerInfoVo.getExpiredMiles(),\n" +
                "\t\t\t\tcustomerInfoVo.getCurrentTier(), customerInfoVo.getTierCode(), customerInfoVo.getFfpMiles(),\n" +
                "\t\t\t\tcustomerInfoVo.getMemberSince(), customerInfoVo.getKfStatusChangeDate(),\n" +
                "\t\t\t\tcustomerInfoVo.getOtherFFPDetails(), guardian, customerInfoVo.getCustomerSeatPrefVos(),\n" +
                "\t\t\t\tcustomerInfoVo.getCustomerID(), customerInfoVo.getInternalID(), stopmailInfoRespVos, status,\n" +
                "\t\t\t\tcustomerInfoVo.getHvInd(), lifeStyleDetails, socialMediaDetails, customerInfoVo.getSeatPrefSClassCd(),\n" +
                "\t\t\t\tcustomerInfoVo.getIntroducerProgramCode(), customerInfoVo.getReferralKFNumber(),\n" +
                "\t\t\t\tcustomerInfoVo.getPrincipalTierCode(), customerInfoVo.getPrincipalTierExpiry(),\n" +
                "\t\t\t\tcustomerInfoVo.getAllianceTier(), customerInfoVo.geteCardOption(), customerInfoVo.getIsEUresident(),\n" +
                "\t\t\t\tcustomerInfoVo.getPassword(), customerInfoVo.getNomineeDetails(), customerInfoVo.getCusNextKins(),\n" +
                "\t\t\t\tcustomerInfoVo.getKtnNo(), stopIndcatorVos, customerInfoVo.getSpecialCusType(),\n" +
                "                customerInfoVo.getReasonForChange(),\n" +
                "\t\t\t\tcustomerInfoVo.getRedressNo(), customerInfoVo.getRdpnWLNotification(),customerInfoVo.getExpiringMiles(),customerInfoVo.isCorpTraveller(),\n" +
                "\t\t\t\tcustomerInfoVo.getCorporateId(),customerInfoVo.getCorporateType(),customerInfoVo.getStudentValidTill(),\n" +
                "\t\t\t\tcustomerInfoVo.isEmailVerified(), customerInfoVo.isMobileVerified(),\n" +
                "\t\t\t\tcustomerInfoVo.isStopMobileIndicator(),customerInfoVo.isStopEmailIndicator(),familyInfo,\n" +
                "\t\t\t\tcustomerInfoVo.getRedemptionBlockInd(),\n" +
                "\t\t\t\tcustomerInfoVo.getSrcOfEnrollment(),lastReadDt,subProgram";

        String params = "@JsonProperty(\"familyname\") String familyname,\n" +
                "                                @JsonProperty(\"givenName\") String givenName, @JsonProperty(\"nationality\") String nationality,\n" +
                "                                @JsonProperty(\"birthDate\") String birthDate, @JsonProperty(\"gender\") String gender,\n" +
                "                                @JsonProperty(\"countryOfResidence\") String countryOfResidence, @JsonProperty(\"city\") String city,\n" +
                "                                @JsonProperty(\"states\") String states, @JsonProperty(\"zipCode\") String zipCode,\n" +
                "                                @JsonProperty(\"email\") String email, @JsonProperty(\"companyName\") String companyName,\n" +
                "                                @JsonProperty(\"corporateCode\") String corporateCode, @JsonProperty(\"title\") String title,\n" +
                "                                @JsonProperty(\"countryOfBirth\") String countryOfBirth, @JsonProperty(\"maritalStatus\") String maritalStatus,\n" +
                "                                @JsonProperty(\"contactNoHome\") String contactNoHome,\n" +
                "                                @JsonProperty(\"contactNoOffice\") String contactNoOffice,\n" +
                "                                @JsonProperty(\"contactNoMobile\") String contactNoMobile,\n" +
                "                                @JsonProperty(\"customerPin\") String customerPin, @JsonProperty(\"siaPromotion\") String promotion,\n" +
                "                                @JsonProperty(\"partnerPromoCode\") String partnerPromoCode, @JsonProperty(\"kfPromotion\") String kfPromotion,\n" +
                "                                @JsonProperty(\"eNews\") String geteNews, @JsonProperty(\"passportName\") String passportName,\n" +
                "                                @JsonProperty(\"passportNo\") String passportNo, @JsonProperty(\"passportExpDate\") String passportExpDate,\n" +
                "                                @JsonProperty(\"passportCtryOfIss\") String passportCtryOfIss,\n" +
                "                                @JsonProperty(\"mailingAddressInd\") String mailAddrInd,\n" +
                "                                @JsonProperty(\"address\") CustomerAddressVo[] customerAddressVos,\n" +
                "                                @JsonProperty(\"phoneNumber\") CustomerPhoneRespVo[] phoneVo,\n" +
                "                                @JsonProperty(\"mailerPref\") CustomerDirmailPrefVo[] newMailerPref,\n" +
                "                                @JsonProperty(\"altEmailAddress\") String altEmailAddress,\n" +
                "                                @JsonProperty(\"programCode\") String programCode, @JsonProperty(\"spokenLanguage\") String[] spokenLang,\n" +
                "                                @JsonProperty(\"writtenLanguage\") String[] writtenLang,\n" +
                "                                @JsonProperty(\"srcOfPrgInfoCode\") String srcOfPrgInfoCode,\n" +
                "                                @JsonProperty(\"numberOfChildern\") int numberOfChildern, @JsonProperty(\"positionHeld\") String positionHeld,\n" +
                "                                @JsonProperty(\"professionalStatus\") String professionalStatus, @JsonProperty(\"business\") String business,\n" +
                "                                @JsonProperty(\"seatPrefPClassCd\") String seatPrefPClassCd,\n" +
                "                                @JsonProperty(\"seatPrefYClassCd\") String seatPrefYClassCd,\n" +
                "                                @JsonProperty(\"seatPrefJClassCd\") String seatPrefJClassCd,\n" +
                "                                @JsonProperty(\"deckMegatopJClass\") String deckMegatopJClass,\n" +
                "                                @JsonProperty(\"medicalMealPrefferedCd\") String[] medicalMealPrefferedCd,\n" +
                "                                @JsonProperty(\"medicalMealPrefferedText\") String[] medicalMealPrefferedText,\n" +
                "                                @JsonProperty(\"religiousMealPrefferedCd\") String religiousMealPrefferedCd,\n" +
                "                                @JsonProperty(\"religiousMealPrefferedText\") String religiousMealPrefferedText,\n" +
                "                                @JsonProperty(\"splInfltRequirement\") String splInfltRequirement,\n" +
                "                                @JsonProperty(\"expiredMiles\") long expiredMiles, @JsonProperty(\"currentTier\") String currentTier,\n" +
                "                                @JsonProperty(\"tierCode\") String tierCode, @JsonProperty(\"ffpMiles\") long ffpMiles,\n" +
                "                                @JsonProperty(\"memberSince\") String memberSince,\n" +
                "                                @JsonProperty(\"kfStatusChangeDate\") String kfStatusChangeDate,\n" +
                "                                @JsonProperty(\"otherFFPDetails\") CustomerOffpInfoVo[] otherFFPDetails,\n" +
                "                                @JsonProperty(\"guardianInfo\") CustomerGuardianResponseVo[] guardian,\n" +
                "                                @JsonProperty(\"aircraftSeatPref\") CustomerSeatPrefVo[] customerSeatPrefVos,\n" +
                "                                @JsonProperty(\"customerID\") String customerID, @JsonProperty(\"internalID\") long internalID,\n" +
                "                                @JsonProperty(\"stopMailInfos\") CustomerStopmailInfoRespVo[] stopMailInfos,\n" +
                "                                @JsonProperty(\"status\") String status, @JsonProperty(\"hvInd\") String hvInd,\n" +
                "                                @JsonProperty(\"lifeStyleDetails\") CustomerLifestyleResponseVo[] lifeStyleDetails,\n" +
                "                                @JsonProperty(\"socialMediaDetails\") CustomerSocialMediaResponseVo[] socialMediaDetails,\n" +
                "                                @JsonProperty(\"seatPrefSClassCd\") String seatPrefSClassCd,\n" +
                "                                @JsonProperty(\"introducerProgramCode\") String introducerProgramCode,\n" +
                "                                @JsonProperty(\"referralKFNumber\") String referralKFNumber,\n" +
                "                                @JsonProperty(\"principalTierCode\") String principalTierCode,\n" +
                "                                @JsonProperty(\"principalTierExpiry\") String principalTierExpiry,\n" +
                "                                @JsonProperty(\"allianceTier\") String allianceTier, @JsonProperty(\"geteCardOption\") String geteCardOption,\n" +
                "                                @JsonProperty(\"isEUresident\") String isEUresident, @JsonProperty(\"password\") String password,\n" +
                "                                @JsonProperty(\"nominees\") RedemptionNomineesVo[] nominees, @JsonProperty(\"nok\") CustomerNextKinVo[] nok,\n" +
                "                                @JsonProperty(\"ktnNo\") String ktn, @JsonProperty(\"stopIndicator\") StopIndcatorVo[] stopIndicator,\n" +
                "                                @JsonProperty(\"specialCusType\") String specialCusType,\n" +
                "                                @JsonProperty(\"reasonForChange\") String reasonForChange,\n" +
                "                                @JsonProperty(\"redressNo\") String redressNo,\n" +
                "                                @JsonProperty(\"rdpnWLNotification\") String rdpnWLNotification, @JsonProperty(\"expiringMiles\") Long expiringMiles,\n" +
                "                                @JsonProperty(\"isCorpTraveller\") boolean isCorpTraveller,\n" +
                "                                @JsonProperty(\"corporateId\") String corporateId,\n" +
                "                                @JsonProperty(\"corporateType\") String corporateType, @JsonProperty(\"studentValidTill\") String studentValidTill,\n" +
                "                                @JsonProperty(\"emailVerified\") boolean emailVerified,\n" +
                "                                @JsonProperty(\"mobileVerified\") boolean mobileVerified,\n" +
                "                                @JsonProperty(\"stopMobileIndicator\") boolean stopMobileIndicator, @JsonProperty(\"stopEmailIndicator\") boolean stopEmailIndicator,\n" +
                "                                @JsonProperty(\"familyInfo\") CustomerFamilyResponse[] familyInfo,\n" +
                "                                @JsonProperty(\"redemptionBlockInd\") String redemptionBlockInd,\n" +
                "                                @JsonProperty(\"srcOfEnrollment\") String srcOfEnrollment,\n" +
                "                                @JsonProperty(\"lastReadDt\") String lastReadDt,\n" +
                "                                @JsonProperty(\"subProgram\") SubProgramVo[] subProgram";

        String mappingInConstructor = "this.familyName = familyname;\n" +
                "        this.givenName = givenName;\n" +
                "        this.nationality = nationality;\n" +
                "        this.birthDate = birthDate;\n" +
                "        this.gender = gender;\n" +
                "        this.countryOfResidence = countryOfResidence;\n" +
                "        this.isEUresident = isEUresident;\n" +
                "        this.city = city;\n" +
                "        this.states = states;\n" +
                "        this.zipCode = zipCode;\n" +
                "        this.email1 = email;\n" +
                "        this.companyName = companyName;\n" +
                "        this.corporateCode = corporateCode;\n" +
                "        this.title = title;\n" +
                "        this.countryOfBirth = countryOfBirth;\n" +
                "        this.maritalStatus = maritalStatus;\n" +
                "        this.contactNoHome = contactNoHome;\n" +
                "        this.contactNoOffice = contactNoOffice;\n" +
                "        this.contactNoMobile = contactNoMobile;\n" +
                "        this.customerPin = customerPin;\n" +
                "        this.siaPromotion = promotion;\n" +
                "        this.partnerPromoCode = partnerPromoCode;\n" +
                "        this.kfPromotion = kfPromotion;\n" +
                "        this.eNews = geteNews;\n" +
                "        this.passportName = passportName;\n" +
                "        this.passportNo = passportNo;\n" +
                "        this.passportExpDate = passportExpDate;\n" +
                "        this.passportCtryOfIss = passportCtryOfIss;\n" +
                "        this.mailingAddressInd = mailAddrInd;\n" +
                "        this.address = customerAddressVos;\n" +
                "        this.phoneNumber = phoneVo;\n" +
                "        this.mailerPref = newMailerPref;\n" +
                "        this.altEmailAddress = altEmailAddress;\n" +
                "        this.programCode = programCode;\n" +
                "        this.spokenLanguage = spokenLang;\n" +
                "        this.writtenLanguage = writtenLang;\n" +
                "        this.srcOfPrgInfoCode = srcOfPrgInfoCode;\n" +
                "        this.numberOfChildern = numberOfChildern;\n" +
                "        this.positionHeld = positionHeld;\n" +
                "        this.principalTierCode = principalTierCode;\n" +
                "        this.principalTierExpiry = principalTierExpiry;\n" +
                "        this.professionalStatus = professionalStatus;\n" +
                "        this.business = business;\n" +
                "        this.seatPrefPClassCd = seatPrefPClassCd;\n" +
                "        this.seatPrefYClassCd = seatPrefYClassCd;\n" +
                "        this.seatPrefJClassCd = seatPrefJClassCd;\n" +
                "        this.deckMegatopJClass = deckMegatopJClass;\n" +
                "        this.medicalMealPrefferedCd = medicalMealPrefferedCd;\n" +
                "        this.medicalMealPrefferedText = medicalMealPrefferedText;\n" +
                "        this.religiousMealPrefferedCd = religiousMealPrefferedCd;\n" +
                "        this.religiousMealPrefferedText = religiousMealPrefferedText;\n" +
                "        this.splInfltRequirement = splInfltRequirement;\n" +
                "        this.expiredMiles = expiredMiles;\n" +
                "        this.currentTier = currentTier;\n" +
                "        this.tierCode = tierCode;\n" +
                "        this.ffpMiles = ffpMiles;\n" +
                "        this.memberSince = memberSince;\n" +
                "        this.kfStatusChangeDate = kfStatusChangeDate;\n" +
                "        this.otherFFPDetails = otherFFPDetails;\n" +
                "        this.guardianInfo = guardian;\n" +
                "        this.aircraftSeatPref = customerSeatPrefVos;\n" +
                "        this.customerID = customerID;\n" +
                "        this.internalID = internalID;\n" +
                "        this.stopMailInfos = stopMailInfos;\n" +
                "        this.profileStatus = status;\n" +
                "        this.hvInd = hvInd;\n" +
                "        this.lifestyleDetails = lifeStyleDetails;\n" +
                "        this.socialMediaDetails = socialMediaDetails;\n" +
                "        this.seatPrefSClassCd = seatPrefSClassCd;\n" +
                "        this.introducerProgramCode = introducerProgramCode;\n" +
                "        this.referralKFNumber = referralKFNumber;\n" +
                "        this.allianceTier = allianceTier;\n" +
                "        this.eCardOption = geteCardOption;\n" +
                "        this.password = password;\n" +
                "        this.nomineeDetails = nominees;\n" +
                "        this.nok = nok;\n" +
                "        this.ktn = ktn;\n" +
                "        this.stopIndicator = stopIndicator;\n" +
                "        this.specialCusType = specialCusType;\n" +
                "        this.reasonForChange = reasonForChange;\n" +
                "        this.redressNo = redressNo;\n" +
                "        this.rdpnWLNotification = rdpnWLNotification;\n" +
                "        this.expiringMiles = expiringMiles;\n" +
                "        this.isCorpTraveller = isCorpTraveller;\n" +
                "        this.corporateId = corporateId;\n" +
                "        this.corporateType = corporateType;\n" +
                "        this.studentValidTill = studentValidTill;\n" +
                "        this.stopEmailIndicator = stopEmailIndicator;\n" +
                "        this.stopMobileIndicator = stopMobileIndicator;\n" +
                "        this.emailVerified = emailVerified;\n" +
                "        this.mobileVerified = mobileVerified;\n" +
                "        this.familyInfo = familyInfo;\n" +
                "        this.srcOfEnrollment = srcOfEnrollment;\n" +
                "        this.redemptionBlockInd = redemptionBlockInd;\n" +
                "        this.lastReadDt = lastReadDt;\n" +
                "        this.subProgram = subProgram;";
        argumentsForMethod = combineStringToOneLine(argumentsForMethod);
        List<String> arguments = extractStringSeparatedByComma(argumentsForMethod);

        params = combineStringToOneLine(params);
        List<String> parameters = extractStringSeparatedByComma(params);
        List<String> cleansedParameters = cleanParamsSpecific(parameters);

        Map<String,String> mapping = mapFieldsToParams(mappingInConstructor);

        // create a list that list the field in order of params
        List<String> orderedField = orderFieldsAccordingToParam(cleansedParameters, mapping);
    }

    private static List<String> orderFieldsAccordingToParam(List<String> cleansedParams, Map<String,String> mapping) {
        List<String> orderedField = new ArrayList<>();
        for(String params : cleansedParams) {
            String field = mapping.get(params);
            orderedField.add(field);
            System.out.println(field);
        }
        return orderedField;
    }

    private static Map<String,String> mapFieldsToParams(String s) {
        String[] arr = s.split("\n");
        Map<String,String> mapping = new HashMap<>();
        for(String split : arr) {
            try {
                String[] splitAgain = split.trim().split(" ");
                String field = splitAgain[0].substring(5);
                String param = splitAgain[2].substring(0,splitAgain[2].length()-1);
                mapping.put(param, field);
            } catch (Exception e) {
                System.out.println("error at " + split);
            }
        }
        return mapping;
    }


    private static List<String> cleanParamsSpecific(List<String> params) {
        List<String> newList = new ArrayList<>();
        for(String s : params) {
            try {
                newList.add(s.split(" ")[2]);
            } catch (Exception e) {
                System.out.println("error at " + s);
            }
        }
        return newList;
    }

    private static String combineStringToOneLine(String s) {
        String[] splitStrArr = s.split("\n");
        StringBuffer stringBuffer = new StringBuffer();
        for(String split : splitStrArr) {
            stringBuffer.append(split.trim());
        }
        return stringBuffer.toString();
    }

    private static List<String> extractStringSeparatedByComma(String s) {
        String[] splitArr = s.split(",");
        List<String> list = new ArrayList<>();
        for(String split : splitArr) {
            list.add(split.trim());
        }
        return list;
    }
}

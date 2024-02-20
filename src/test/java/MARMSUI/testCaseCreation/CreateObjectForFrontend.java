package MARMSUI.testCaseCreation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CreateObjectForFrontend {
    public static void main(String[] args) {
        String request = "-agentId(java.lang.String)\n" +
                "-customerID(java.lang.String)\n" +
                "-programCode(java.lang.String)\n" +
                "-agentID(java.lang.String)\n" +
                "-lastReadDt(java.lang.String)\n" +
                "-familyName(java.lang.String)\n" +
                "-givenName(java.lang.String)\n" +
                "-nationality(java.lang.String)\n" +
                "-birthDate(java.lang.String)\n" +
                "-gender(java.lang.String)\n" +
                "-countryOfResidence(java.lang.String)\n" +
                "-city(java.lang.String)\n" +
                "-states(java.lang.String)\n" +
                "-zipCode(java.lang.String)\n" +
                "-email(java.lang.String)\n" +
                "-companyName(java.lang.String)\n" +
                "-corporateCode(java.lang.String)\n" +
                "-title(java.lang.String)\n" +
                "-countryOfBirth(java.lang.String)\n" +
                "-maritalStatus(java.lang.String)\n" +
                "-contactNoHome(java.lang.String)\n" +
                "-contactNoOffice(java.lang.String)\n" +
                "-contactNoMobile(java.lang.String)\n" +
                "-secretQuestion(java.lang.String)\n" +
                "-secretAnswer(java.lang.String)\n" +
                "-customerPin(java.lang.String)\n" +
                "-siaPromotion(java.lang.String)\n" +
                "-partnerPromoCode(java.lang.String)\n" +
                "-kfPromotion(java.lang.String)\n" +
                "-eNews(java.lang.String)\n" +
                "-passportName(java.lang.String)\n" +
                "-passportNo(java.lang.String)\n" +
                "-passportExpDate(java.lang.String)\n" +
                "-passportCtryOfIss(java.lang.String)\n" +
                "-altEmailAddress(java.lang.String)\n" +
                "-srcOfPrgInfoCode(java.lang.String)\n" +
                "-positionHeld(java.lang.String)\n" +
                "-principalTierCode(java.lang.String)\n" +
                "-professionalStatus(java.lang.String)\n" +
                "-business(java.lang.String)\n" +
                "-seatPrefPClassCd(java.lang.String)\n" +
                "-seatPrefYClassCd(java.lang.String)\n" +
                "-seatPrefJClassCd(java.lang.String)\n" +
                "-deckMegatopJClass(java.lang.String)\n" +
                "-religiousMealPrefferedCd(java.lang.String)\n" +
                "-religiousMealPrefferedText(java.lang.String)\n" +
                "-splInfltRequirement(java.lang.String)\n" +
                "-currentTier(java.lang.String)\n" +
                "-correspondenceName(java.lang.String)\n" +
                "-internalID(long)\n" +
                "-mailingAddressInd(java.lang.String)\n" +
                "-numberOfChildern(int)\n" +
                "-expiredMiles(long)\n" +
                "-tierCode(java.lang.String)\n" +
                "-ffpMiles(long)\n" +
                "-memberSince(java.lang.String)\n" +
                "-address(com.sg.sq.marmsui.vo.customerProfile.CustomerAddressVo[])\n" +
                "-address-createdUserID(java.lang.String)\n" +
                "-address-mailCtrlCityCode(java.lang.String)\n" +
                "-address-addrLastChgDate(java.lang.String)\n" +
                "-address-addrType(java.lang.String)\n" +
                "-address-languageCode(java.lang.String)\n" +
                "-address-line1(java.lang.String)\n" +
                "-address-createdDate(java.lang.String)\n" +
                "-address-line2(java.lang.String)\n" +
                "-address-cityCd(java.lang.String)\n" +
                "-address-addressEffDate(java.lang.String)\n" +
                "-address-otherCityDesc(java.lang.String)\n" +
                "-address-line3(java.lang.String)\n" +
                "-address-state(java.lang.String)\n" +
                "-address-countryCd(java.lang.String)\n" +
                "-address-line4(java.lang.String)\n" +
                "-address-postalCode(java.lang.String)\n" +
                "-address-addrLastChgUserID(java.lang.String)\n" +
                "-address-mailRegionCode(java.lang.String)\n" +
                "-address-svcCityCode(java.lang.String)\n" +
                "-address-addressEndDate(java.lang.String)\n" +
                "-address-isAddressUpdated(boolean)\n" +
                "-address-mailCtrlCityCodeUpdated(boolean)\n" +
                "-address-cityDesc(java.lang.String)\n" +
                "-address-countryDesc(java.lang.String)\n" +
                "-address-addressTypeStr(java.lang.String)\n" +
                "-address-isAddressStopped(boolean)\n" +
                "-address-lastChangeDate(java.lang.String)\n" +
                "-address-addrLabel(java.lang.String)\n" +
                "-address-remarks(java.lang.String)\n" +
                "-phoneNumber(com.sg.sq.marmsui.vo.customerProfile.CustomerPhoneDetailsVo[])\n" +
                "-phoneNumber-phoneType(java.lang.String)\n" +
                "-phoneNumber-countryCode(java.lang.String)\n" +
                "-phoneNumber-areaCode(java.lang.String)\n" +
                "-phoneNumber-phoneNumber(java.lang.String)\n" +
                "-phoneNumber-mobileMsgSvc(java.lang.String)\n" +
                "-phoneNumber-mobileSvcProvider(java.lang.String)\n" +
                "-phoneNumber-mobileSubscNetwork(java.lang.String)\n" +
                "-phoneNumber-pagerSvcType(java.lang.String)\n" +
                "-phoneNumber-rcvFltPagingInd(boolean)\n" +
                "-phoneNumber-rcvMobileFltPagingInd(boolean)\n" +
                "-phoneNumber-lastChangeDate(java.lang.String)\n" +
                "-phoneNumber-prefPhoneFlg(java.lang.String)\n" +
                "-phoneNumber-altPhoneFlg(java.lang.String)\n" +
                "-phoneNumber-stopMobInd(boolean)\n" +
                "-phoneNumber-phoneTypeStr(java.lang.String)\n" +
                "-phoneNumber-mobileMsgSvcFlg(java.lang.String)\n" +
                "-phoneNumber-pagerSvcTypeInd(java.lang.String)\n" +
                "-phoneNumberVo(com.sg.sq.marmsui.vo.customerProfile.CustomerPhoneVo[])\n" +
                "-phoneNumberVo-intId(java.lang.Long)\n" +
                "-phoneNumberVo-phoneType(java.lang.String)\n" +
                "-phoneNumberVo-rcreUserId(java.lang.String)\n" +
                "-phoneNumberVo-rcreDt(java.lang.String)\n" +
                "-phoneNumberVo-lchgUserId(java.lang.String)\n" +
                "-phoneNumberVo-lchgDt(java.lang.String)\n" +
                "-phoneNumberVo-phoneCtryCd(java.lang.String)\n" +
                "-phoneNumberVo-phoneAreaCd(java.lang.String)\n" +
                "-phoneNumberVo-phoneNo(java.lang.String)\n" +
                "-phoneNumberVo-mobileMsgSvcFlg(java.lang.String)\n" +
                "-phoneNumberVo-mobileSvcProvider(java.lang.String)\n" +
                "-phoneNumberVo-mobileSubscNetw(java.lang.String)\n" +
                "-phoneNumberVo-pagerSvcTypeInd(java.lang.String)\n" +
                "-phoneNumberVo-rcvFltPagingInd(boolean)\n" +
                "-phoneNumberVo-mobileRcvFltPagingInd(boolean)\n" +
                "-phoneNumberVo-prefPhoneInd(java.lang.String)\n" +
                "-phoneNumberVo-altPhoneInd(java.lang.String)\n" +
                "-mailerPref(com.sg.sq.marmsui.vo.customerProfile.CustomerDirmailPrefVo[])\n" +
                "-mailerPref-lchgDt(java.lang.String)\n" +
                "-mailerPref-mailerType(java.lang.String)\n" +
                "-mailerPref-mailPref(java.lang.String)\n" +
                "-mailerPref-emailContentType(java.lang.String)\n" +
                "-mailerPref-notificationSent(java.lang.Long)\n" +
                "-mailerPref-displayFlag(java.lang.String)\n" +
                "-mailerPref-defaultBoolean(boolean)\n" +
                "-mailerPref-mailerSubscribeInd(java.lang.String)\n" +
                "-mailerPref-prgOrSubPrgCd(java.lang.String)\n" +
                "-otherFFPDetails(com.sg.sq.marmsui.vo.customerProfile.CustomerOffpInfoVo[])\n" +
                "-otherFFPDetails-partnerTier(java.lang.String)\n" +
                "-otherFFPDetails-offpCode(java.lang.String)\n" +
                "-otherFFPDetails-intId(java.lang.Long)\n" +
                "-otherFFPDetails-lchgUserId(java.lang.String)\n" +
                "-otherFFPDetails-lchgDt(java.lang.String)\n" +
                "-otherFFPDetails-offpNumber(java.lang.String)\n" +
                "-otherFFPDetails-linkStatus(java.lang.String)\n" +
                "-otherFFPDetails-statusMatch(java.lang.String)\n" +
                "-otherFFPDetails-offpName(java.lang.String)\n" +
                "-otherFFPDetails-offpNumberOld(java.lang.String)\n" +
                "-guardianInfo(com.sg.sq.marmsui.vo.customerProfile.CustomerGuardianVo[])\n" +
                "-guardianInfo-guardianSeqNumber(java.lang.Long)\n" +
                "-guardianInfo-guardianTitle(java.lang.String)\n" +
                "-guardianInfo-guardianFamilyName(java.lang.String)\n" +
                "-guardianInfo-guardianGivenName(java.lang.String)\n" +
                "-guardianInfo-guardianKFNumber(java.lang.String)\n" +
                "-guardianInfo-guardianGender(java.lang.String)\n" +
                "-guardianInfo-guardianCustomerID(java.lang.String)\n" +
                "-guardianInfo-guardianInternalID(java.lang.Long)\n" +
                "-guardianInfo-guardianRelationshipCode(java.lang.String)\n" +
                "-guardianInfo-guardianRelationship(java.lang.String)\n" +
                "-guardianInfo-guardianAddress(com.sg.sq.marmsui.vo.customerProfile.CustomerAddressVo[])\n" +
                "-guardianInfo-guardianAddress-createdUserID(java.lang.String)\n" +
                "-guardianInfo-guardianAddress-mailCtrlCityCode(java.lang.String)\n" +
                "-guardianInfo-guardianAddress-addrLastChgDate(java.lang.String)\n" +
                "-guardianInfo-guardianAddress-addrType(java.lang.String)\n" +
                "-guardianInfo-guardianAddress-languageCode(java.lang.String)\n" +
                "-guardianInfo-guardianAddress-line1(java.lang.String)\n" +
                "-guardianInfo-guardianAddress-createdDate(java.lang.String)\n" +
                "-guardianInfo-guardianAddress-line2(java.lang.String)\n" +
                "-guardianInfo-guardianAddress-cityCd(java.lang.String)\n" +
                "-guardianInfo-guardianAddress-addressEffDate(java.lang.String)\n" +
                "-guardianInfo-guardianAddress-otherCityDesc(java.lang.String)\n" +
                "-guardianInfo-guardianAddress-line3(java.lang.String)\n" +
                "-guardianInfo-guardianAddress-state(java.lang.String)\n" +
                "-guardianInfo-guardianAddress-countryCd(java.lang.String)\n" +
                "-guardianInfo-guardianAddress-line4(java.lang.String)\n" +
                "-guardianInfo-guardianAddress-postalCode(java.lang.String)\n" +
                "-guardianInfo-guardianAddress-addrLastChgUserID(java.lang.String)\n" +
                "-guardianInfo-guardianAddress-mailRegionCode(java.lang.String)\n" +
                "-guardianInfo-guardianAddress-svcCityCode(java.lang.String)\n" +
                "-guardianInfo-guardianAddress-addressEndDate(java.lang.String)\n" +
                "-guardianInfo-guardianAddress-isAddressUpdated(boolean)\n" +
                "-guardianInfo-guardianAddress-mailCtrlCityCodeUpdated(boolean)\n" +
                "-guardianInfo-guardianAddress-cityDesc(java.lang.String)\n" +
                "-guardianInfo-guardianAddress-countryDesc(java.lang.String)\n" +
                "-guardianInfo-guardianAddress-addressTypeStr(java.lang.String)\n" +
                "-guardianInfo-guardianAddress-isAddressStopped(boolean)\n" +
                "-guardianInfo-guardianAddress-lastChangeDate(java.lang.String)\n" +
                "-guardianInfo-guardianAddress-addrLabel(java.lang.String)\n" +
                "-guardianInfo-guardianAddress-remarks(java.lang.String)\n" +
                "-guardianInfo-guardianPhone(java.util.List<com.sg.sq.marmsui.vo.customerProfile.CustomerPhoneVo>)\n" +
                "-guardianInfo-lastChangeDate(java.lang.String)\n" +
                "-guardianInfo-guardianProgramCode(java.lang.String)\n" +
                "-guardianInfo-othGuardianRelationship(java.lang.String)\n" +
                "-guardianInfo-rcreUserId(java.lang.String)\n" +
                "-guardianInfo-customerguardianPhone(com.sg.sq.marmsui.vo.customerProfile.CustomerPhoneVo[])\n" +
                "-guardianInfo-customerguardianPhone-intId(java.lang.Long)\n" +
                "-guardianInfo-customerguardianPhone-phoneType(java.lang.String)\n" +
                "-guardianInfo-customerguardianPhone-rcreUserId(java.lang.String)\n" +
                "-guardianInfo-customerguardianPhone-rcreDt(java.lang.String)\n" +
                "-guardianInfo-customerguardianPhone-lchgUserId(java.lang.String)\n" +
                "-guardianInfo-customerguardianPhone-lchgDt(java.lang.String)\n" +
                "-guardianInfo-customerguardianPhone-phoneCtryCd(java.lang.String)\n" +
                "-guardianInfo-customerguardianPhone-phoneAreaCd(java.lang.String)\n" +
                "-guardianInfo-customerguardianPhone-phoneNo(java.lang.String)\n" +
                "-guardianInfo-customerguardianPhone-mobileMsgSvcFlg(java.lang.String)\n" +
                "-guardianInfo-customerguardianPhone-mobileSvcProvider(java.lang.String)\n" +
                "-guardianInfo-customerguardianPhone-mobileSubscNetw(java.lang.String)\n" +
                "-guardianInfo-customerguardianPhone-pagerSvcTypeInd(java.lang.String)\n" +
                "-guardianInfo-customerguardianPhone-rcvFltPagingInd(boolean)\n" +
                "-guardianInfo-customerguardianPhone-mobileRcvFltPagingInd(boolean)\n" +
                "-guardianInfo-customerguardianPhone-prefPhoneInd(java.lang.String)\n" +
                "-guardianInfo-customerguardianPhone-altPhoneInd(java.lang.String)\n" +
                "-guardianInfo-homeAddrLine2(java.lang.String)\n" +
                "-guardianInfo-lchgUserId(java.lang.String)\n" +
                "-guardianInfo-homeAddrLine3(java.lang.String)\n" +
                "-guardianInfo-coyZipCd(java.lang.String)\n" +
                "-guardianInfo-lchgDt(java.lang.String)\n" +
                "-guardianInfo-coyAddrLine4(java.lang.String)\n" +
                "-guardianInfo-familyName(java.lang.String)\n" +
                "-guardianInfo-coyPhone(java.lang.String)\n" +
                "-guardianInfo-givenName(java.lang.String)\n" +
                "-guardianInfo-title(java.lang.String)\n" +
                "-guardianInfo-homePhoneCtryCd(java.lang.String)\n" +
                "-guardianInfo-gender(java.lang.String)\n" +
                "-guardianInfo-coyCtyCd(java.lang.String)\n" +
                "-guardianInfo-homeCtryCd(java.lang.String)\n" +
                "-guardianInfo-homeAddrLine1(java.lang.String)\n" +
                "-guardianInfo-rcreDt(java.lang.String)\n" +
                "-guardianInfo-homeAddrLine4(java.lang.String)\n" +
                "-guardianInfo-coyAddrLine1(java.lang.String)\n" +
                "-guardianInfo-homeZipCd(java.lang.String)\n" +
                "-guardianInfo-guardianPrgCd(java.lang.String)\n" +
                "-guardianInfo-coyCtryCd(java.lang.String)\n" +
                "-guardianInfo-homeRgnCd(java.lang.String)\n" +
                "-guardianInfo-coyRgnCd(java.lang.String)\n" +
                "-guardianInfo-guardianConsent(java.lang.String)\n" +
                "-guardianInfo-guardianLink(java.lang.String)\n" +
                "-guardianInfo-linkDt(java.lang.String)\n" +
                "-customerSeatPrefVos(com.sg.sq.marmsui.vo.customerProfile.CustomerSeatPrefVo[])\n" +
                "-customerSeatPrefVos-aircraftTypeCode(java.lang.String)\n" +
                "-customerSeatPrefVos-lchgDt(java.lang.String)\n" +
                "-customerSeatPrefVos-seatNbrPClass(java.lang.String)\n" +
                "-customerSeatPrefVos-seatNbrJClass(java.lang.String)\n" +
                "-customerSeatPrefVos-seatNbrYClass(java.lang.String)\n" +
                "-customerSeatPrefVos-sClsSeatNo(java.lang.String)\n" +
                "-customerSeatPrefVos-alteaAircraftType(java.lang.String)\n" +
                "-spokenLanguage(java.lang.String[])\n" +
                "-writtenLanguage(java.lang.String[])\n" +
                "-medicalMealPrefferedCd(java.lang.String[])\n" +
                "-medicalMealPrefferedText(java.lang.String[])\n" +
                "-quickLinkSelected(java.lang.String[])\n" +
                "-isYec(boolean)\n" +
                "-lchgDateCusPers(java.lang.String)\n" +
                "-lchgUserIdCusPers(java.lang.String)\n" +
                "-lastChangeUserIdCusAddnInfo(java.lang.String)\n" +
                "-lastChangeDateCusAddnInfo(java.lang.String)\n" +
                "-lastChangeDateCusInfltPref(java.lang.String)\n" +
                "-lastChangeDateCusOinfltPref(java.lang.String)\n" +
                "-lastChangeDateCusPin(java.lang.String)\n" +
                "-lastChangeDateEnrol(java.lang.String)\n" +
                "-updatedWithPreference(boolean)\n" +
                "-leisureYearlyTrvlRgn(java.lang.Long[])\n" +
                "-businessYEarlyTrvlRgn(java.lang.Long[])\n" +
                "-stopMailInfos(com.sg.sq.marmsui.vo.customerProfile.CustomerStopmailInfoVo[])\n" +
                "-stopMailInfos-rcreUserId(java.lang.String)\n" +
                "-stopMailInfos-rcreDt(java.lang.String)\n" +
                "-stopMailInfos-lchgUserId(java.lang.String)\n" +
                "-stopMailInfos-lchgDt(java.lang.String)\n" +
                "-stopMailInfos-intId(java.lang.Long)\n" +
                "-stopMailInfos-commChnl(java.lang.String)\n" +
                "-stopMailInfos-rsnCd(java.lang.String)\n" +
                "-stopMailInfos-actionInd(java.lang.String)\n" +
                "-stopMailInfos-emailAddr(java.lang.String)\n" +
                "-stopMailInfos-mailerType(java.lang.String)\n" +
                "-stopMailInfos-addrType(java.lang.String)\n" +
                "-stopMailInfos-phoneNumber(java.lang.String)\n" +
                "-stopMailInfos-origStopMailRsnCode(java.lang.String)\n" +
                "-stopMailInfos-origEmailAddress(java.lang.String)\n" +
                "-stopMailInfos-origAddressType(java.lang.String)\n" +
                "-profileStatus(java.lang.String)\n" +
                "-hvInd(java.lang.String)\n" +
                "-lifestyleDetails(com.sg.sq.marmsui.vo.customerProfile.CustomerLifestyleVo[])\n" +
                "-lifestyleDetails-rcreUserId(java.lang.String)\n" +
                "-lifestyleDetails-lifestyleCd(java.lang.String)\n" +
                "-lifestyleDetails-lchgUserId(java.lang.String)\n" +
                "-lifestyleDetails-lifestyleCategory(java.lang.String)\n" +
                "-lifestyleDetails-lchgDt(java.lang.String)\n" +
                "-lifestyleDetails-intId(java.lang.Long)\n" +
                "-lifestyleDetails-lifestyleDesc(java.lang.String)\n" +
                "-lifestyleDetails-rcreDt(java.lang.String)\n" +
                "-lifestyleDetails-lifestyleType(java.lang.String)\n" +
                "-lifestyleDetails-trvlRgnTotTripYr(java.lang.Long)\n" +
                "-lifestyleDetails-lifestyleMbrNo(java.lang.String)\n" +
                "-lifestyleDetails-trvlClsTotTripYr(java.lang.Long)\n" +
                "-lifestyleDetails-lifestyleCategoryDesc(java.lang.String)\n" +
                "-socialMediaDetails(com.sg.sq.marmsui.vo.customerProfile.CustomerSocialMediaVo[])\n" +
                "-socialMediaDetails-intId(java.lang.Long)\n" +
                "-socialMediaDetails-socialMediaId(java.lang.String)\n" +
                "-socialMediaDetails-socialMedia(java.lang.String)\n" +
                "-socialMediaDetails-rcreUserId(java.lang.String)\n" +
                "-socialMediaDetails-rcreDt(java.lang.String)\n" +
                "-socialMediaDetails-lchgUserId(java.lang.String)\n" +
                "-socialMediaDetails-lchgDt(java.lang.String)\n" +
                "-socialMediaDetails-providerUid(java.lang.String)\n" +
                "-seatPrefSClassCd(java.lang.String)\n" +
                "-isEUresident(java.lang.String)\n" +
                "-redressNo(java.lang.String)\n" +
                "-ktnNo(java.lang.String)\n" +
                "-introducerProgramCode(java.lang.String)\n" +
                "-referralKFNumber(java.lang.String)\n" +
                "-allianceTier(java.lang.String)\n" +
                "-eCardOption(java.lang.String)\n" +
                "-creditCardVos(com.sg.sq.marmsui.vo.customerProfile.CustomerCreditCardVo[])\n" +
                "-creditCardVos-rcreUserId(java.lang.String)\n" +
                "-creditCardVos-crCardBrandCd(java.lang.String)\n" +
                "-creditCardVos-lchgUserId(java.lang.String)\n" +
                "-creditCardVos-crCardExpDt(java.lang.String)\n" +
                "-creditCardVos-lchgDt(java.lang.String)\n" +
                "-creditCardVos-crCardBankTxt(java.lang.String)\n" +
                "-creditCardVos-intId(java.lang.Long)\n" +
                "-creditCardVos-crCardTypeCd(java.lang.String)\n" +
                "-creditCardVos-rcreDt(java.lang.String)\n" +
                "-creditCardVos-crCardBankCd(java.lang.String)\n" +
                "-creditCardVos-token(java.lang.String)\n" +
                "-creditCardVos-crCardNo(java.lang.String)\n" +
                "-creditCardVos-cardholdername(java.lang.String)\n" +
                "-creditCardVos-crCardCobrandFlg(java.lang.String)\n" +
                "-creditCardVos-paymentcurrency(java.lang.String)\n" +
                "-creditCardVos-crCardBankCtryCd(java.lang.String)\n" +
                "-creditCardVos-defaultcardflag(java.lang.String)\n" +
                "-creditCardVos-crCardBankCtryTxt(java.lang.String)\n" +
                "-creditCardVos-cardCompany(java.lang.String)\n" +
                "-customerOinfltPrefVos(com.sg.sq.marmsui.vo.customerProfile.CustomerOinfltPrefVo[])\n" +
                "-customerOinfltPrefVos-infltPrefType(java.lang.String)\n" +
                "-customerOinfltPrefVos-infltPrefCode(java.lang.String)\n" +
                "-customerOinfltPrefVos-infltPrefDesc(java.lang.String)\n" +
                "-customerOinfltPrefVos-infltPrefOthDesc(java.lang.String)\n" +
                "-customerOinfltPrefVos-lchgDt(java.lang.String)\n" +
                "-customerOinfltPrefVos-rcreUserId(java.lang.String)\n" +
                "-customerOinfltPrefVos-rcreDt(java.lang.String)\n" +
                "-customerOinfltPrefVos-lchgUserId(java.lang.String)\n" +
                "-customerOinfltPrefVos-intId(java.lang.Long)\n" +
                "-acvValue(java.lang.String)\n" +
                "-acvOverrideFlg(java.lang.String)\n" +
                "-businessPreferenceClass(java.lang.String)\n" +
                "-leisurePreferenceClass(java.lang.String)\n" +
                "-regionsVisited(java.lang.String[])\n" +
                "-hotelsPreffered(java.lang.String[])\n" +
                "-carRentalPreffered(java.lang.String[])\n" +
                "-businessYearlyTravel(java.lang.Long)\n" +
                "-leisureYearlyTravel(java.lang.Long)\n" +
                "-kfStatusChangeDate(java.lang.String)\n" +
                "-channelCode(java.lang.String)\n" +
                "-hobbies(com.sg.sq.marmsui.vo.customerProfile.CustomerHobbiesVo[])\n" +
                "-hobbies-hobbyCategory(java.lang.String)\n" +
                "-hobbies-hobbies(java.lang.String[])\n" +
                "-familyInfo(com.sg.sq.marmsui.vo.customerProfile.CustomerFamilyInfoVo[])\n" +
                "-familyInfo-rcreUserId(java.lang.String)\n" +
                "-familyInfo-rcreDt(java.lang.String)\n" +
                "-familyInfo-lchgUserId(java.lang.String)\n" +
                "-familyInfo-lchgDt(java.lang.String)\n" +
                "-familyInfo-intId(java.lang.Long)\n" +
                "-familyInfo-relnshipInd(java.lang.String)\n" +
                "-familyInfo-seqNo(short)\n" +
                "-familyInfo-title(java.lang.String)\n" +
                "-familyInfo-familyName(java.lang.String)\n" +
                "-familyInfo-givenName(java.lang.String)\n" +
                "-familyInfo-gender(java.lang.String)\n" +
                "-familyInfo-dob(java.lang.String)\n" +
                "-familyInfo-familyMbrIntId(java.lang.String)\n" +
                "-familyInfo-familyMbrPrgCd(java.lang.String)\n" +
                "-familyInfo-familyMbrCustomerID(java.lang.String)\n" +
                "-familyInfo-xLchgDt(java.util.Date)\n" +
                "-familyInfo-xSeqNo(java.lang.Short)\n" +
                "-familyInfo-xDob(java.util.Date)\n" +
                "-familyInfo-guardianConsent(java.lang.String)\n" +
                "-familyInfo-guardianLink(java.lang.String)\n" +
                "-familyInfo-linkDt(java.lang.String)\n" +
                "-supplInfo(com.sg.sq.marmsui.vo.customerProfile.CustomerSupplInfoVo[])\n" +
                "-supplInfo-intId(java.lang.Long)\n" +
                "-supplInfo-prgCd(java.lang.String)\n" +
                "-supplInfo-supplIntId(java.lang.Long)\n" +
                "-supplInfo-rcreUserId(java.lang.String)\n" +
                "-supplInfo-rcreDt(java.lang.String)\n" +
                "-supplInfo-lchgUserId(java.lang.String)\n" +
                "-supplInfo-lchgDt(java.lang.String)\n" +
                "-supplInfo-relnshipToPrincipal(java.lang.String)\n" +
                "-supplInfo-relnshipToPrincipalRemarks(java.lang.String)\n" +
                "-supplInfo-supplName(java.lang.String)\n" +
                "-supplInfo-supplCustomerId(java.lang.String)\n" +
                "-supplInfo-dbOperation(java.lang.String)\n" +
                "-alternativeName(com.sg.sq.marmsui.vo.customerProfile.CustomerAlternativeNameVo[])\n" +
                "-alternativeName-seqNo(java.lang.Long)\n" +
                "-alternativeName-lchgUserId(java.lang.String)\n" +
                "-alternativeName-intId(java.lang.Long)\n" +
                "-alternativeName-rcreDt(java.lang.String)\n" +
                "-alternativeName-givenName(java.lang.String)\n" +
                "-alternativeName-lchgDt(java.lang.String)\n" +
                "-alternativeName-familyName(java.lang.String)\n" +
                "-alternativeName-rcreUserId(java.lang.String)\n" +
                "-alternativeName-newFlg(java.lang.String)\n" +
                "-nomineeDetails(com.sg.sq.marmsui.vo.customerProfile.RedemptionNomineesVo[])\n" +
                "-nomineeDetails-programCode(java.lang.String)\n" +
                "-nomineeDetails-internalID(long)\n" +
                "-nomineeDetails-sequenceNumber(int)\n" +
                "-nomineeDetails-familyName(java.lang.String)\n" +
                "-nomineeDetails-givenName(java.lang.String)\n" +
                "-nomineeDetails-passportNumber(java.lang.String)\n" +
                "-nomineeDetails-nomineesInternalID(long)\n" +
                "-nomineeDetails-nomineesProgramCode(java.lang.String)\n" +
                "-nomineeDetails-tktAuthorised(java.lang.String)\n" +
                "-nomineeDetails-feesTransactionXrefID(java.lang.String)\n" +
                "-nomineeDetails-supervisorID(java.lang.String)\n" +
                "-nomineeDetails-supervisorRemarks(java.lang.String)\n" +
                "-nomineeDetails-lastChangeDate(java.lang.String)\n" +
                "-nomineeDetails-lastChangeUserID(java.lang.String)\n" +
                "-nomineeDetails-nomineesCustomerID(java.lang.String)\n" +
                "-nomineeDetails-createDate(java.lang.String)\n" +
                "-nomineeDetails-createdUserId(java.lang.String)\n" +
                "-nomineeDetails-isWithinXMonths(boolean)\n" +
                "-nomineeDetails-title(java.lang.String)\n" +
                "-nomineeDetails-birthDate(java.lang.String)\n" +
                "-nomineeDetails-passportExpiryDate(java.lang.String)\n" +
                "-nomineeDetails-passportCtryOfIssuance(java.lang.String)\n" +
                "-nomineeDetails-relationship(java.lang.String)\n" +
                "-nomineeDetails-isEUresident(java.lang.String)\n" +
                "-nomineeDetails-tktFlag(java.lang.String)\n" +
                "-nomineeDetails-birthDateinDateFormat(java.sql.Timestamp)\n" +
                "-nomineeDetails-passportExpiryDateinDateFormat(java.sql.Timestamp)\n" +
                "-nomineeDetails-isNameMismatch(boolean)\n" +
                "-nomineeDetails-isInvalidKf(boolean)\n" +
                "-cusNextKins(com.sg.sq.marmsui.vo.customerProfile.CustomerNextKinVo[])\n" +
                "-cusNextKins-lchgDt(java.lang.String)\n" +
                "-cusNextKins-prgCd(java.lang.String)\n" +
                "-cusNextKins-relation(java.lang.String)\n" +
                "-cusNextKins-phAreaCode(java.lang.String)\n" +
                "-cusNextKins-phCountryCode(java.lang.String)\n" +
                "-cusNextKins-phoneNumber(java.lang.String)\n" +
                "-cusNextKins-familyName(java.lang.String)\n" +
                "-cusNextKins-givenName(java.lang.String)\n" +
                "-cusNextKins-seqNo(long)\n" +
                "-cusNextKins-intId(long)\n" +
                "-isEnrol(boolean)\n" +
                "-introducerInternalId(long)\n" +
                "-specialCusType(java.lang.String)\n" +
                "-rdpnWLNotification(java.lang.String)\n" +
                "-principalTierExpiry(java.lang.String)\n" +
                "-principleInternalId(java.lang.Long)\n" +
                "-expiringMiles(java.lang.Long)\n" +
                "-isCorpTraveller(boolean)\n" +
                "-corporateId(java.lang.String)\n" +
                "-corporateType(java.lang.String)\n" +
                "-studentValidTill(java.lang.String)\n" +
                "-stopMobileIndicator(boolean)\n" +
                "-stopEmailIndicator(boolean)\n" +
                "-deleteCustomerDetails(com.sg.sq.marmsui.vo.customerProfile.DeleteCustomerDetailsVO)\n" +
                "-deleteCustomerDetails-address(com.sg.sq.marmsui.vo.customerProfile.CustomerAddressVo[])\n" +
                "-deleteCustomerDetails-address-createdUserID(java.lang.String)\n" +
                "-deleteCustomerDetails-address-mailCtrlCityCode(java.lang.String)\n" +
                "-deleteCustomerDetails-address-addrLastChgDate(java.lang.String)\n" +
                "-deleteCustomerDetails-address-addrType(java.lang.String)\n" +
                "-deleteCustomerDetails-address-languageCode(java.lang.String)\n" +
                "-deleteCustomerDetails-address-line1(java.lang.String)\n" +
                "-deleteCustomerDetails-address-createdDate(java.lang.String)\n" +
                "-deleteCustomerDetails-address-line2(java.lang.String)\n" +
                "-deleteCustomerDetails-address-cityCd(java.lang.String)\n" +
                "-deleteCustomerDetails-address-addressEffDate(java.lang.String)\n" +
                "-deleteCustomerDetails-address-otherCityDesc(java.lang.String)\n" +
                "-deleteCustomerDetails-address-line3(java.lang.String)\n" +
                "-deleteCustomerDetails-address-state(java.lang.String)\n" +
                "-deleteCustomerDetails-address-countryCd(java.lang.String)\n" +
                "-deleteCustomerDetails-address-line4(java.lang.String)\n" +
                "-deleteCustomerDetails-address-postalCode(java.lang.String)\n" +
                "-deleteCustomerDetails-address-addrLastChgUserID(java.lang.String)\n" +
                "-deleteCustomerDetails-address-mailRegionCode(java.lang.String)\n" +
                "-deleteCustomerDetails-address-svcCityCode(java.lang.String)\n" +
                "-deleteCustomerDetails-address-addressEndDate(java.lang.String)\n" +
                "-deleteCustomerDetails-address-isAddressUpdated(boolean)\n" +
                "-deleteCustomerDetails-address-mailCtrlCityCodeUpdated(boolean)\n" +
                "-deleteCustomerDetails-address-cityDesc(java.lang.String)\n" +
                "-deleteCustomerDetails-address-countryDesc(java.lang.String)\n" +
                "-deleteCustomerDetails-address-addressTypeStr(java.lang.String)\n" +
                "-deleteCustomerDetails-address-isAddressStopped(boolean)\n" +
                "-deleteCustomerDetails-address-lastChangeDate(java.lang.String)\n" +
                "-deleteCustomerDetails-address-addrLabel(java.lang.String)\n" +
                "-deleteCustomerDetails-address-remarks(java.lang.String)\n" +
                "-deleteCustomerDetails-phoneNumber(com.sg.sq.marmsui.vo.customerProfile.CustomerPhoneDetailsVo[])\n" +
                "-deleteCustomerDetails-phoneNumber-phoneType(java.lang.String)\n" +
                "-deleteCustomerDetails-phoneNumber-countryCode(java.lang.String)\n" +
                "-deleteCustomerDetails-phoneNumber-areaCode(java.lang.String)\n" +
                "-deleteCustomerDetails-phoneNumber-phoneNumber(java.lang.String)\n" +
                "-deleteCustomerDetails-phoneNumber-mobileMsgSvc(java.lang.String)\n" +
                "-deleteCustomerDetails-phoneNumber-mobileSvcProvider(java.lang.String)\n" +
                "-deleteCustomerDetails-phoneNumber-mobileSubscNetwork(java.lang.String)\n" +
                "-deleteCustomerDetails-phoneNumber-pagerSvcType(java.lang.String)\n" +
                "-deleteCustomerDetails-phoneNumber-rcvFltPagingInd(boolean)\n" +
                "-deleteCustomerDetails-phoneNumber-rcvMobileFltPagingInd(boolean)\n" +
                "-deleteCustomerDetails-phoneNumber-lastChangeDate(java.lang.String)\n" +
                "-deleteCustomerDetails-phoneNumber-prefPhoneFlg(java.lang.String)\n" +
                "-deleteCustomerDetails-phoneNumber-altPhoneFlg(java.lang.String)\n" +
                "-deleteCustomerDetails-phoneNumber-stopMobInd(boolean)\n" +
                "-deleteCustomerDetails-phoneNumber-phoneTypeStr(java.lang.String)\n" +
                "-deleteCustomerDetails-phoneNumber-mobileMsgSvcFlg(java.lang.String)\n" +
                "-deleteCustomerDetails-phoneNumber-pagerSvcTypeInd(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo(com.sg.sq.marmsui.vo.customerProfile.CustomerGuardianVo[])\n" +
                "-deleteCustomerDetails-guardianInfo-guardianSeqNumber(java.lang.Long)\n" +
                "-deleteCustomerDetails-guardianInfo-guardianTitle(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-guardianFamilyName(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-guardianGivenName(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-guardianKFNumber(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-guardianGender(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-guardianCustomerID(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-guardianInternalID(java.lang.Long)\n" +
                "-deleteCustomerDetails-guardianInfo-guardianRelationshipCode(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-guardianRelationship(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-guardianAddress(com.sg.sq.marmsui.vo.customerProfile.CustomerAddressVo[])\n" +
                "-deleteCustomerDetails-guardianInfo-guardianAddress-createdUserID(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-guardianAddress-mailCtrlCityCode(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-guardianAddress-addrLastChgDate(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-guardianAddress-addrType(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-guardianAddress-languageCode(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-guardianAddress-line1(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-guardianAddress-createdDate(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-guardianAddress-line2(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-guardianAddress-cityCd(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-guardianAddress-addressEffDate(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-guardianAddress-otherCityDesc(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-guardianAddress-line3(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-guardianAddress-state(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-guardianAddress-countryCd(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-guardianAddress-line4(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-guardianAddress-postalCode(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-guardianAddress-addrLastChgUserID(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-guardianAddress-mailRegionCode(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-guardianAddress-svcCityCode(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-guardianAddress-addressEndDate(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-guardianAddress-isAddressUpdated(boolean)\n" +
                "-deleteCustomerDetails-guardianInfo-guardianAddress-mailCtrlCityCodeUpdated(boolean)\n" +
                "-deleteCustomerDetails-guardianInfo-guardianAddress-cityDesc(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-guardianAddress-countryDesc(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-guardianAddress-addressTypeStr(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-guardianAddress-isAddressStopped(boolean)\n" +
                "-deleteCustomerDetails-guardianInfo-guardianAddress-lastChangeDate(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-guardianAddress-addrLabel(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-guardianAddress-remarks(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-guardianPhone(java.util.List<com.sg.sq.marmsui.vo.customerProfile.CustomerPhoneVo>)\n" +
                "-deleteCustomerDetails-guardianInfo-lastChangeDate(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-guardianProgramCode(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-othGuardianRelationship(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-rcreUserId(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-customerguardianPhone(com.sg.sq.marmsui.vo.customerProfile.CustomerPhoneVo[])\n" +
                "-deleteCustomerDetails-guardianInfo-customerguardianPhone-intId(java.lang.Long)\n" +
                "-deleteCustomerDetails-guardianInfo-customerguardianPhone-phoneType(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-customerguardianPhone-rcreUserId(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-customerguardianPhone-rcreDt(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-customerguardianPhone-lchgUserId(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-customerguardianPhone-lchgDt(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-customerguardianPhone-phoneCtryCd(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-customerguardianPhone-phoneAreaCd(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-customerguardianPhone-phoneNo(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-customerguardianPhone-mobileMsgSvcFlg(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-customerguardianPhone-mobileSvcProvider(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-customerguardianPhone-mobileSubscNetw(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-customerguardianPhone-pagerSvcTypeInd(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-customerguardianPhone-rcvFltPagingInd(boolean)\n" +
                "-deleteCustomerDetails-guardianInfo-customerguardianPhone-mobileRcvFltPagingInd(boolean)\n" +
                "-deleteCustomerDetails-guardianInfo-customerguardianPhone-prefPhoneInd(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-customerguardianPhone-altPhoneInd(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-homeAddrLine2(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-lchgUserId(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-homeAddrLine3(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-coyZipCd(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-lchgDt(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-coyAddrLine4(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-familyName(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-coyPhone(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-givenName(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-title(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-homePhoneCtryCd(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-gender(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-coyCtyCd(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-homeCtryCd(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-homeAddrLine1(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-rcreDt(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-homeAddrLine4(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-coyAddrLine1(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-homeZipCd(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-guardianPrgCd(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-coyCtryCd(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-homeRgnCd(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-coyRgnCd(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-guardianConsent(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-guardianLink(java.lang.String)\n" +
                "-deleteCustomerDetails-guardianInfo-linkDt(java.lang.String)\n" +
                "-deleteCustomerDetails-otherFFPDetails(com.sg.sq.marmsui.vo.customerProfile.CustomerOffpInfoVo[])\n" +
                "-deleteCustomerDetails-otherFFPDetails-partnerTier(java.lang.String)\n" +
                "-deleteCustomerDetails-otherFFPDetails-offpCode(java.lang.String)\n" +
                "-deleteCustomerDetails-otherFFPDetails-intId(java.lang.Long)\n" +
                "-deleteCustomerDetails-otherFFPDetails-lchgUserId(java.lang.String)\n" +
                "-deleteCustomerDetails-otherFFPDetails-lchgDt(java.lang.String)\n" +
                "-deleteCustomerDetails-otherFFPDetails-offpNumber(java.lang.String)\n" +
                "-deleteCustomerDetails-otherFFPDetails-linkStatus(java.lang.String)\n" +
                "-deleteCustomerDetails-otherFFPDetails-statusMatch(java.lang.String)\n" +
                "-deleteCustomerDetails-otherFFPDetails-offpName(java.lang.String)\n" +
                "-deleteCustomerDetails-otherFFPDetails-offpNumberOld(java.lang.String)\n" +
                "-deleteCustomerDetails-socialMediaDetails(com.sg.sq.marmsui.vo.customerProfile.CustomerSocialMediaVo[])\n" +
                "-deleteCustomerDetails-socialMediaDetails-intId(java.lang.Long)\n" +
                "-deleteCustomerDetails-socialMediaDetails-socialMediaId(java.lang.String)\n" +
                "-deleteCustomerDetails-socialMediaDetails-socialMedia(java.lang.String)\n" +
                "-deleteCustomerDetails-socialMediaDetails-rcreUserId(java.lang.String)\n" +
                "-deleteCustomerDetails-socialMediaDetails-rcreDt(java.lang.String)\n" +
                "-deleteCustomerDetails-socialMediaDetails-lchgUserId(java.lang.String)\n" +
                "-deleteCustomerDetails-socialMediaDetails-lchgDt(java.lang.String)\n" +
                "-deleteCustomerDetails-socialMediaDetails-providerUid(java.lang.String)\n" +
                "-deleteCustomerDetails-mailerPref(com.sg.sq.marmsui.vo.customerProfile.CustomerDirmailPrefVo[])\n" +
                "-deleteCustomerDetails-mailerPref-lchgDt(java.lang.String)\n" +
                "-deleteCustomerDetails-mailerPref-mailerType(java.lang.String)\n" +
                "-deleteCustomerDetails-mailerPref-mailPref(java.lang.String)\n" +
                "-deleteCustomerDetails-mailerPref-emailContentType(java.lang.String)\n" +
                "-deleteCustomerDetails-mailerPref-notificationSent(java.lang.Long)\n" +
                "-deleteCustomerDetails-mailerPref-displayFlag(java.lang.String)\n" +
                "-deleteCustomerDetails-mailerPref-defaultBoolean(boolean)\n" +
                "-deleteCustomerDetails-mailerPref-mailerSubscribeInd(java.lang.String)\n" +
                "-deleteCustomerDetails-mailerPref-prgOrSubPrgCd(java.lang.String)\n" +
                "-deleteCustomerDetails-creditCard(com.sg.sq.marmsui.vo.customerProfile.CustomerCreditCardVo[])\n" +
                "-deleteCustomerDetails-creditCard-rcreUserId(java.lang.String)\n" +
                "-deleteCustomerDetails-creditCard-crCardBrandCd(java.lang.String)\n" +
                "-deleteCustomerDetails-creditCard-lchgUserId(java.lang.String)\n" +
                "-deleteCustomerDetails-creditCard-crCardExpDt(java.lang.String)\n" +
                "-deleteCustomerDetails-creditCard-lchgDt(java.lang.String)\n" +
                "-deleteCustomerDetails-creditCard-crCardBankTxt(java.lang.String)\n" +
                "-deleteCustomerDetails-creditCard-intId(java.lang.Long)\n" +
                "-deleteCustomerDetails-creditCard-crCardTypeCd(java.lang.String)\n" +
                "-deleteCustomerDetails-creditCard-rcreDt(java.lang.String)\n" +
                "-deleteCustomerDetails-creditCard-crCardBankCd(java.lang.String)\n" +
                "-deleteCustomerDetails-creditCard-token(java.lang.String)\n" +
                "-deleteCustomerDetails-creditCard-crCardNo(java.lang.String)\n" +
                "-deleteCustomerDetails-creditCard-cardholdername(java.lang.String)\n" +
                "-deleteCustomerDetails-creditCard-crCardCobrandFlg(java.lang.String)\n" +
                "-deleteCustomerDetails-creditCard-paymentcurrency(java.lang.String)\n" +
                "-deleteCustomerDetails-creditCard-crCardBankCtryCd(java.lang.String)\n" +
                "-deleteCustomerDetails-creditCard-defaultcardflag(java.lang.String)\n" +
                "-deleteCustomerDetails-creditCard-crCardBankCtryTxt(java.lang.String)\n" +
                "-deleteCustomerDetails-creditCard-cardCompany(java.lang.String)\n" +
                "-deleteCustomerDetails-customerSeatPref(com.sg.sq.marmsui.vo.customerProfile.CustomerSeatPrefVo[])\n" +
                "-deleteCustomerDetails-customerSeatPref-aircraftTypeCode(java.lang.String)\n" +
                "-deleteCustomerDetails-customerSeatPref-lchgDt(java.lang.String)\n" +
                "-deleteCustomerDetails-customerSeatPref-seatNbrPClass(java.lang.String)\n" +
                "-deleteCustomerDetails-customerSeatPref-seatNbrJClass(java.lang.String)\n" +
                "-deleteCustomerDetails-customerSeatPref-seatNbrYClass(java.lang.String)\n" +
                "-deleteCustomerDetails-customerSeatPref-sClsSeatNo(java.lang.String)\n" +
                "-deleteCustomerDetails-customerSeatPref-alteaAircraftType(java.lang.String)\n" +
                "-deleteCustomerDetails-lifestyleDetails(com.sg.sq.marmsui.vo.customerProfile.CustomerLifestyleVo[])\n" +
                "-deleteCustomerDetails-lifestyleDetails-rcreUserId(java.lang.String)\n" +
                "-deleteCustomerDetails-lifestyleDetails-lifestyleCd(java.lang.String)\n" +
                "-deleteCustomerDetails-lifestyleDetails-lchgUserId(java.lang.String)\n" +
                "-deleteCustomerDetails-lifestyleDetails-lifestyleCategory(java.lang.String)\n" +
                "-deleteCustomerDetails-lifestyleDetails-lchgDt(java.lang.String)\n" +
                "-deleteCustomerDetails-lifestyleDetails-intId(java.lang.Long)\n" +
                "-deleteCustomerDetails-lifestyleDetails-lifestyleDesc(java.lang.String)\n" +
                "-deleteCustomerDetails-lifestyleDetails-rcreDt(java.lang.String)\n" +
                "-deleteCustomerDetails-lifestyleDetails-lifestyleType(java.lang.String)\n" +
                "-deleteCustomerDetails-lifestyleDetails-trvlRgnTotTripYr(java.lang.Long)\n" +
                "-deleteCustomerDetails-lifestyleDetails-lifestyleMbrNo(java.lang.String)\n" +
                "-deleteCustomerDetails-lifestyleDetails-trvlClsTotTripYr(java.lang.Long)\n" +
                "-deleteCustomerDetails-lifestyleDetails-lifestyleCategoryDesc(java.lang.String)\n" +
                "-deleteCustomerDetails-familyInfo(com.sg.sq.marmsui.vo.customerProfile.CustomerFamilyInfoVo[])\n" +
                "-deleteCustomerDetails-familyInfo-rcreUserId(java.lang.String)\n" +
                "-deleteCustomerDetails-familyInfo-rcreDt(java.lang.String)\n" +
                "-deleteCustomerDetails-familyInfo-lchgUserId(java.lang.String)\n" +
                "-deleteCustomerDetails-familyInfo-lchgDt(java.lang.String)\n" +
                "-deleteCustomerDetails-familyInfo-intId(java.lang.Long)\n" +
                "-deleteCustomerDetails-familyInfo-relnshipInd(java.lang.String)\n" +
                "-deleteCustomerDetails-familyInfo-seqNo(short)\n" +
                "-deleteCustomerDetails-familyInfo-title(java.lang.String)\n" +
                "-deleteCustomerDetails-familyInfo-familyName(java.lang.String)\n" +
                "-deleteCustomerDetails-familyInfo-givenName(java.lang.String)\n" +
                "-deleteCustomerDetails-familyInfo-gender(java.lang.String)\n" +
                "-deleteCustomerDetails-familyInfo-dob(java.lang.String)\n" +
                "-deleteCustomerDetails-familyInfo-familyMbrIntId(java.lang.String)\n" +
                "-deleteCustomerDetails-familyInfo-familyMbrPrgCd(java.lang.String)\n" +
                "-deleteCustomerDetails-familyInfo-familyMbrCustomerID(java.lang.String)\n" +
                "-deleteCustomerDetails-familyInfo-xLchgDt(java.util.Date)\n" +
                "-deleteCustomerDetails-familyInfo-xSeqNo(java.lang.Short)\n" +
                "-deleteCustomerDetails-familyInfo-xDob(java.util.Date)\n" +
                "-deleteCustomerDetails-familyInfo-guardianConsent(java.lang.String)\n" +
                "-deleteCustomerDetails-familyInfo-guardianLink(java.lang.String)\n" +
                "-deleteCustomerDetails-familyInfo-linkDt(java.lang.String)\n" +
                "-deleteCustomerDetails-alternativeName(com.sg.sq.marmsui.vo.customerProfile.CustomerAlternativeNameVo[])\n" +
                "-deleteCustomerDetails-alternativeName-seqNo(java.lang.Long)\n" +
                "-deleteCustomerDetails-alternativeName-lchgUserId(java.lang.String)\n" +
                "-deleteCustomerDetails-alternativeName-intId(java.lang.Long)\n" +
                "-deleteCustomerDetails-alternativeName-rcreDt(java.lang.String)\n" +
                "-deleteCustomerDetails-alternativeName-givenName(java.lang.String)\n" +
                "-deleteCustomerDetails-alternativeName-lchgDt(java.lang.String)\n" +
                "-deleteCustomerDetails-alternativeName-familyName(java.lang.String)\n" +
                "-deleteCustomerDetails-alternativeName-rcreUserId(java.lang.String)\n" +
                "-deleteCustomerDetails-alternativeName-newFlg(java.lang.String)\n" +
                "-deleteCustomerDetails-tempAddress(com.sg.sq.marmsui.vo.customerProfile.CustomerAddressVo[])\n" +
                "-deleteCustomerDetails-tempAddress-createdUserID(java.lang.String)\n" +
                "-deleteCustomerDetails-tempAddress-mailCtrlCityCode(java.lang.String)\n" +
                "-deleteCustomerDetails-tempAddress-addrLastChgDate(java.lang.String)\n" +
                "-deleteCustomerDetails-tempAddress-addrType(java.lang.String)\n" +
                "-deleteCustomerDetails-tempAddress-languageCode(java.lang.String)\n" +
                "-deleteCustomerDetails-tempAddress-line1(java.lang.String)\n" +
                "-deleteCustomerDetails-tempAddress-createdDate(java.lang.String)\n" +
                "-deleteCustomerDetails-tempAddress-line2(java.lang.String)\n" +
                "-deleteCustomerDetails-tempAddress-cityCd(java.lang.String)\n" +
                "-deleteCustomerDetails-tempAddress-addressEffDate(java.lang.String)\n" +
                "-deleteCustomerDetails-tempAddress-otherCityDesc(java.lang.String)\n" +
                "-deleteCustomerDetails-tempAddress-line3(java.lang.String)\n" +
                "-deleteCustomerDetails-tempAddress-state(java.lang.String)\n" +
                "-deleteCustomerDetails-tempAddress-countryCd(java.lang.String)\n" +
                "-deleteCustomerDetails-tempAddress-line4(java.lang.String)\n" +
                "-deleteCustomerDetails-tempAddress-postalCode(java.lang.String)\n" +
                "-deleteCustomerDetails-tempAddress-addrLastChgUserID(java.lang.String)\n" +
                "-deleteCustomerDetails-tempAddress-mailRegionCode(java.lang.String)\n" +
                "-deleteCustomerDetails-tempAddress-svcCityCode(java.lang.String)\n" +
                "-deleteCustomerDetails-tempAddress-addressEndDate(java.lang.String)\n" +
                "-deleteCustomerDetails-tempAddress-isAddressUpdated(boolean)\n" +
                "-deleteCustomerDetails-tempAddress-mailCtrlCityCodeUpdated(boolean)\n" +
                "-deleteCustomerDetails-tempAddress-cityDesc(java.lang.String)\n" +
                "-deleteCustomerDetails-tempAddress-countryDesc(java.lang.String)\n" +
                "-deleteCustomerDetails-tempAddress-addressTypeStr(java.lang.String)\n" +
                "-deleteCustomerDetails-tempAddress-isAddressStopped(boolean)\n" +
                "-deleteCustomerDetails-tempAddress-lastChangeDate(java.lang.String)\n" +
                "-deleteCustomerDetails-tempAddress-addrLabel(java.lang.String)\n" +
                "-deleteCustomerDetails-tempAddress-remarks(java.lang.String)\n" +
                "-deleteCustomerDetails-customerOinfltPref(com.sg.sq.marmsui.vo.customerProfile.CustomerOinfltPrefVo[])\n" +
                "-deleteCustomerDetails-customerOinfltPref-infltPrefType(java.lang.String)\n" +
                "-deleteCustomerDetails-customerOinfltPref-infltPrefCode(java.lang.String)\n" +
                "-deleteCustomerDetails-customerOinfltPref-infltPrefDesc(java.lang.String)\n" +
                "-deleteCustomerDetails-customerOinfltPref-infltPrefOthDesc(java.lang.String)\n" +
                "-deleteCustomerDetails-customerOinfltPref-lchgDt(java.lang.String)\n" +
                "-deleteCustomerDetails-customerOinfltPref-rcreUserId(java.lang.String)\n" +
                "-deleteCustomerDetails-customerOinfltPref-rcreDt(java.lang.String)\n" +
                "-deleteCustomerDetails-customerOinfltPref-lchgUserId(java.lang.String)\n" +
                "-deleteCustomerDetails-customerOinfltPref-intId(java.lang.Long)\n" +
                "-deleteCustomerDetails-cusNextKins(com.sg.sq.marmsui.vo.customerProfile.CustomerNextKinVo[])\n" +
                "-deleteCustomerDetails-cusNextKins-lchgDt(java.lang.String)\n" +
                "-deleteCustomerDetails-cusNextKins-prgCd(java.lang.String)\n" +
                "-deleteCustomerDetails-cusNextKins-relation(java.lang.String)\n" +
                "-deleteCustomerDetails-cusNextKins-phAreaCode(java.lang.String)\n" +
                "-deleteCustomerDetails-cusNextKins-phCountryCode(java.lang.String)\n" +
                "-deleteCustomerDetails-cusNextKins-phoneNumber(java.lang.String)\n" +
                "-deleteCustomerDetails-cusNextKins-familyName(java.lang.String)\n" +
                "-deleteCustomerDetails-cusNextKins-givenName(java.lang.String)\n" +
                "-deleteCustomerDetails-cusNextKins-seqNo(long)\n" +
                "-deleteCustomerDetails-cusNextKins-intId(long)\n" +
                "-deleteCustomerDetails-stopMailInfos(com.sg.sq.marmsui.vo.customerProfile.CustomerStopmailInfoVo[])\n" +
                "-deleteCustomerDetails-stopMailInfos-rcreUserId(java.lang.String)\n" +
                "-deleteCustomerDetails-stopMailInfos-rcreDt(java.lang.String)\n" +
                "-deleteCustomerDetails-stopMailInfos-lchgUserId(java.lang.String)\n" +
                "-deleteCustomerDetails-stopMailInfos-lchgDt(java.lang.String)\n" +
                "-deleteCustomerDetails-stopMailInfos-intId(java.lang.Long)\n" +
                "-deleteCustomerDetails-stopMailInfos-commChnl(java.lang.String)\n" +
                "-deleteCustomerDetails-stopMailInfos-rsnCd(java.lang.String)\n" +
                "-deleteCustomerDetails-stopMailInfos-actionInd(java.lang.String)\n" +
                "-deleteCustomerDetails-stopMailInfos-emailAddr(java.lang.String)\n" +
                "-deleteCustomerDetails-stopMailInfos-mailerType(java.lang.String)\n" +
                "-deleteCustomerDetails-stopMailInfos-addrType(java.lang.String)\n" +
                "-deleteCustomerDetails-stopMailInfos-phoneNumber(java.lang.String)\n" +
                "-deleteCustomerDetails-stopMailInfos-origStopMailRsnCode(java.lang.String)\n" +
                "-deleteCustomerDetails-stopMailInfos-origEmailAddress(java.lang.String)\n" +
                "-deleteCustomerDetails-stopMailInfos-origAddressType(java.lang.String)\n" +
                "-deleteCustomerDetails-infltPref(com.sg.sq.marmsui.database.sql.persistence.model.CusInfltPref)\n" +
                "-deleteCustomerDetails-infltPref-intId(java.lang.Long)\n" +
                "-deleteCustomerDetails-infltPref-rcreUserId(java.lang.String)\n" +
                "-deleteCustomerDetails-infltPref-rcreDt(java.util.Date)\n" +
                "-deleteCustomerDetails-infltPref-lchgUserId(java.lang.String)\n" +
                "-deleteCustomerDetails-infltPref-lchgDt(java.util.Date)\n" +
                "-deleteCustomerDetails-infltPref-mostFreqAirlineCd(java.lang.String)\n" +
                "-deleteCustomerDetails-infltPref-relMealPrefCd(java.lang.String)\n" +
                "-deleteCustomerDetails-infltPref-splInfltReq(java.lang.String)\n" +
                "-deleteCustomerDetails-infltPref-bizTotTripsYr(java.lang.Short)\n" +
                "-deleteCustomerDetails-infltPref-leisTotTripsYr(java.lang.Short)\n" +
                "-deleteCustomerDetails-infltPref-pClsSeatPrefCd(java.lang.String)\n" +
                "-deleteCustomerDetails-infltPref-jClsSeatPrefCd(java.lang.String)\n" +
                "-deleteCustomerDetails-infltPref-jClsDeckInd(java.lang.String)\n" +
                "-deleteCustomerDetails-infltPref-yClsSeatPrefCd(java.lang.String)\n" +
                "-deleteCustomerDetails-infltPref-seatPrefDesc(java.lang.String)\n" +
                "-deleteCustomerDetails-infltPref-seatPrefDesc2(java.lang.String)\n" +
                "-deleteCustomerDetails-infltPref-splMealPrefDesc(java.lang.String)\n" +
                "-deleteCustomerDetails-infltPref-splMealPrefDesc2(java.lang.String)\n" +
                "-deleteCustomerDetails-infltPref-splInfltReq2(java.lang.String)\n" +
                "-deleteCustomerDetails-infltPref-splDietaryReq(java.lang.String)\n" +
                "-deleteCustomerDetails-infltPref-splTrvlReq1(java.lang.String)\n" +
                "-deleteCustomerDetails-infltPref-splTrvlReq2(java.lang.String)\n" +
                "-deleteCustomerDetails-infltPref-sClsSeatPrefCd(java.lang.String)\n" +
                "-deleteCustomerDetails-infltPref-aciPreference(java.lang.String)\n" +
                "-deleteCustomerDetails-passportCtryOfIss(java.lang.String)\n" +
                "-deleteCustomerDetails-subProgram(com.sg.sq.marmsui.vo.customerProfile.SubProgramVo[])\n" +
                "-deleteCustomerDetails-subProgram-subProgramCode(java.lang.String)\n" +
                "-deleteCustomerDetails-subProgram-subProgramTierCode(java.lang.String)\n" +
                "-deleteCustomerDetails-subProgram-subProgramTier(java.lang.String)\n" +
                "-deleteCustomerDetails-subProgram-shortfall(double)\n" +
                "-deleteCustomerDetails-subProgram-cumulativeSpent(double)\n" +
                "-deleteCustomerDetails-subProgram-subProgramTierExpiryDate(java.lang.String)\n" +
                "-deleteCustomerDetails-subProgram-shippingAddressIndicator(java.lang.String)\n" +
                "-deleteCustomerDetails-subProgram-billingAddressIndicator(java.lang.String)\n" +
                "-deleteCustomerDetails-subProgram-lifestyleDetailsVo(com.sg.sq.marmsui.vo.customerProfile.CustomerLifestyleVo[])\n" +
                "-deleteCustomerDetails-subProgram-lifestyleDetailsVo-rcreUserId(java.lang.String)\n" +
                "-deleteCustomerDetails-subProgram-lifestyleDetailsVo-lifestyleCd(java.lang.String)\n" +
                "-deleteCustomerDetails-subProgram-lifestyleDetailsVo-lchgUserId(java.lang.String)\n" +
                "-deleteCustomerDetails-subProgram-lifestyleDetailsVo-lifestyleCategory(java.lang.String)\n" +
                "-deleteCustomerDetails-subProgram-lifestyleDetailsVo-lchgDt(java.lang.String)\n" +
                "-deleteCustomerDetails-subProgram-lifestyleDetailsVo-intId(java.lang.Long)\n" +
                "-deleteCustomerDetails-subProgram-lifestyleDetailsVo-lifestyleDesc(java.lang.String)\n" +
                "-deleteCustomerDetails-subProgram-lifestyleDetailsVo-rcreDt(java.lang.String)\n" +
                "-deleteCustomerDetails-subProgram-lifestyleDetailsVo-lifestyleType(java.lang.String)\n" +
                "-deleteCustomerDetails-subProgram-lifestyleDetailsVo-trvlRgnTotTripYr(java.lang.Long)\n" +
                "-deleteCustomerDetails-subProgram-lifestyleDetailsVo-lifestyleMbrNo(java.lang.String)\n" +
                "-deleteCustomerDetails-subProgram-lifestyleDetailsVo-trvlClsTotTripYr(java.lang.Long)\n" +
                "-deleteCustomerDetails-subProgram-lifestyleDetailsVo-lifestyleCategoryDesc(java.lang.String)\n" +
                "-deleteCustomerDetails-subProgram-lifestyleDetails(com.sg.sq.marmsui.vo.customerProfile.CustomerLifestyleResponseVo[])\n" +
                "-deleteCustomerDetails-subProgram-lifestyleDetails-lifestyleDesc(java.lang.String)\n" +
                "-deleteCustomerDetails-subProgram-lifestyleDetails-lifestyleType(java.lang.String)\n" +
                "-deleteCustomerDetails-krishopLifestyle(com.sg.sq.marmsui.vo.customerProfile.KrishopLifestyleVo[])\n" +
                "-deleteCustomerDetails-krishopLifestyle-lifestyleType(java.lang.String)\n" +
                "-deleteCustomerDetails-krishopLifestyle-lifestyleCategory(java.lang.String)\n" +
                "-deleteCustomerDetails-krishopLifestyle-lifestyleDesc(java.lang.String)\n" +
                "-deleteCustomerDetails-givenName(java.lang.String)\n" +
                "-deleteCustomerDetails-nationality(java.lang.String)\n" +
                "-deleteCustomerDetails-birthDate(java.lang.String)\n" +
                "-deleteCustomerDetails-countryOfResidence(java.lang.String)\n" +
                "-deleteCustomerDetails-email(java.lang.String)\n" +
                "-deleteCustomerDetails-title(java.lang.String)\n" +
                "-deleteCustomerDetails-countryOfBirth(java.lang.String)\n" +
                "-deleteCustomerDetails-partnerPromoCode(java.lang.String)\n" +
                "-deleteCustomerDetails-passportName(java.lang.String)\n" +
                "-deleteCustomerDetails-passportNo(java.lang.String)\n" +
                "-deleteCustomerDetails-passportExpDate(java.lang.String)\n" +
                "-deleteCustomerDetails-passportExp(java.util.Date)\n" +
                "-deleteCustomerDetails-altEmailAddress(java.lang.String)\n" +
                "-deleteCustomerDetails-srcOfPrgInfoCode(java.lang.String)\n" +
                "-deleteCustomerDetails-mailingAddressInd(java.lang.String)\n" +
                "-deleteCustomerDetails-isEUresident(java.lang.String)\n" +
                "-deleteCustomerDetails-introducerProgramCode(java.lang.String)\n" +
                "-subProgram(com.sg.sq.marmsui.vo.customerProfile.SubProgramVo[])\n" +
                "-subProgram-subProgramCode(java.lang.String)\n" +
                "-subProgram-subProgramTierCode(java.lang.String)\n" +
                "-subProgram-subProgramTier(java.lang.String)\n" +
                "-subProgram-shortfall(double)\n" +
                "-subProgram-cumulativeSpent(double)\n" +
                "-subProgram-subProgramTierExpiryDate(java.lang.String)\n" +
                "-subProgram-shippingAddressIndicator(java.lang.String)\n" +
                "-subProgram-billingAddressIndicator(java.lang.String)\n" +
                "-subProgram-lifestyleDetailsVo(com.sg.sq.marmsui.vo.customerProfile.CustomerLifestyleVo[])\n" +
                "-subProgram-lifestyleDetailsVo-rcreUserId(java.lang.String)\n" +
                "-subProgram-lifestyleDetailsVo-lifestyleCd(java.lang.String)\n" +
                "-subProgram-lifestyleDetailsVo-lchgUserId(java.lang.String)\n" +
                "-subProgram-lifestyleDetailsVo-lifestyleCategory(java.lang.String)\n" +
                "-subProgram-lifestyleDetailsVo-lchgDt(java.lang.String)\n" +
                "-subProgram-lifestyleDetailsVo-intId(java.lang.Long)\n" +
                "-subProgram-lifestyleDetailsVo-lifestyleDesc(java.lang.String)\n" +
                "-subProgram-lifestyleDetailsVo-rcreDt(java.lang.String)\n" +
                "-subProgram-lifestyleDetailsVo-lifestyleType(java.lang.String)\n" +
                "-subProgram-lifestyleDetailsVo-trvlRgnTotTripYr(java.lang.Long)\n" +
                "-subProgram-lifestyleDetailsVo-lifestyleMbrNo(java.lang.String)\n" +
                "-subProgram-lifestyleDetailsVo-trvlClsTotTripYr(java.lang.Long)\n" +
                "-subProgram-lifestyleDetailsVo-lifestyleCategoryDesc(java.lang.String)\n" +
                "-subProgram-lifestyleDetails(com.sg.sq.marmsui.vo.customerProfile.CustomerLifestyleResponseVo[])\n" +
                "-subProgram-lifestyleDetails-lifestyleDesc(java.lang.String)\n" +
                "-subProgram-lifestyleDetails-lifestyleType(java.lang.String)";
        String[] arr = request.split("\n");
        List<String> fields = new ArrayList<>();
        List<String> types = new ArrayList<>();
        printInitializationCode(arr,fields, types);
    }

    private static void printInitializationCode(String[] requestArr, List<String> fields, List<String> types) {
        int count = 0;
        String toPrint = "%s %s = new %s();";
        Set<String> varNames = new HashSet<>();
        for(String row : requestArr) {
            String type = extractType(row);
            String currentFieldName = extractCurrentFieldName(row);
//            if(row.contains("guardianInfo")) {
//                System.out.println("");
//            }
            if(type.startsWith("com.sg.sq.marmsui")) {
                if(type.contains("[]")) {
                    int varCountForObj = count;
                    int varCountForArr = count+1;
                    type = extractTypeWithoutArrayDeclaration(type);
//                    String varName = extractVariableNameFromClassName(type);
                    if(!varNames.contains(currentFieldName)){
                        System.out.println(String.format(toPrint, type, currentFieldName,type));
                        varNames.add(currentFieldName);
                    }
                    System.out.println(String.format("%s[] %s%s = new %s[1];",type,"var", varCountForArr,type));

                    System.out.println(String.format("%s%d[0] = %s;", "var", varCountForArr,currentFieldName));
                    System.out.println(String.format("%s.%s(%s%d);",extractPreviousFieldName(row),generateSetterMethod(currentFieldName),"var",varCountForArr));
                } else if (type.startsWith("java.util.List<")) {
                    throw new RuntimeException("Not implemented yet for list");
                } else if (type.startsWith("java.util.Map<")){
                    throw new RuntimeException("Not implemented yet for maps");
                } else if (type.startsWith("com.sg.sq.marmsui")) {
                    if(!varNames.contains(currentFieldName)) {
                        System.out.println(String.format("%s %s = new %s();", type,currentFieldName,type));
                        varNames.add(currentFieldName);
                    }
                    System.out.println(String.format("%s.%s(%s);",extractPreviousFieldName(row),generateSetterMethod(currentFieldName),currentFieldName));
                }
            }
            count++;
        }
    }

    private static String extractVariableNameFromClassName(String type) {
        int start = 0;
        int end = type.length();
        int count = 0;
        for(char c : type.toCharArray()) {
            if(c == '.') {
                start = count;
            }
            count++;
        }
        start++;
        String var = type.substring(start,end);
        return var.substring(0,1).toLowerCase() + var.substring(1);
    }

    private static String generateSetterMethod(String fieldName) {
        return "set" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
    }

    private static String extractPreviousFieldName(String row) {
        int prevStart = 0;
        int endStart = 0;
        int count = row.indexOf("(");
        String fieldsOnly = row.substring(0,count);
        count = 0;
        for(char c : fieldsOnly.toCharArray()) {
            if(c == '-') {
                if(prevStart != endStart) {
                    prevStart = endStart;
                }
                endStart = count;
            }
            count++;
        }
        if(endStart == 0 || prevStart == endStart) {
            return "oriVar";
        }
        prevStart++;
        return fieldsOnly.substring(prevStart,endStart);
    }

    private static String extractCurrentFieldName(String row) {
        int start = 0;
        int end = row.indexOf("(");
        int count = 0;
        for(char c : row.toCharArray()) {
            if(c == '-') {
                start = count;
            }
            count++;
        }
        start++;
        return row.substring(start,end);
    }

    private static String extractType(String row) {
        int start = row.indexOf("(") + 1;
        int end = row.indexOf(")", start);
        return row.substring(start,end);
    }

    private static String extractTypeWithoutArrayDeclaration(String type) {
        int end = type.indexOf("[");
        return type.substring(0,end);
    }

    // numeric types, int, long, java.lang.Long
}

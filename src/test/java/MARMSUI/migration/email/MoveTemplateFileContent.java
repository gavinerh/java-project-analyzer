package MARMSUI.migration.email;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class MoveTemplateFileContent {
    public static void main(String[] args) {
        String pathToGetTemplateFrom = "/Users/macuser/Documents/updated-lsl-app/lsl-email-for-deployment/lsl-email/src/main/resources/emailtemplate";
        String pathToCopyTo = "/Users/macuser/Documents/updated-lsl-app/lsl-email/src/main/resources/emailtemplate";
        String templateNames = "rdpnupdateack_pps.html\n" +
                "AccrualFailure_PPS_May25.html\n" +
                "notifyClassifiedAccount.html\n" +
                "rdpnupdateack_KF2022_v2.html\n" +
                "FamilyBenefits_LinkingChild.html\n" +
                "linkMember.html\n" +
                "notifyClassifiedAccount_pps.html\n" +
                "delinkMember.html\n" +
                "KF_TRFlatBM_MARMS.html\n" +
                "KFMR_1KReward_RedemptionNotice_5Nov.html\n" +
                "LastMinUpgrades_KF.html\n" +
                "profileupdate_pps_24.html\n" +
                "profileupdate.html\n" +
                "PPS_UpdateMobile2023.html\n" +
                "2022_MARMS_FamilyBenefits_04LinkingParent_v2.html\n" +
                "linkMember.html\n" +
                "delinkMember.html\n" +
                "2022_MARMS_FamilyBenefits_05LinkingChild_Successful_v3.html\n" +
                "2022Template_MARMS_FamilyBenefits_06LinkingParent_Successful.html\n" +
                "EssodelinkMember.html\n" +
                "PPS_TRFlatBM_MARMS.html\n" +
                "Accrnamemismatch.html\n" +
                "NTlinkMember.html\n" +
                "EssolinkMember.html\n" +
                "Retro_OAL_ACK_PPS.html\n" +
                "AccrInvalidCls.html\n" +
                "NTdelinkMember.html\n" +
                "mlTrfMinor.html\n" +
                "Retro_putonhold_Ack.html\n" +
                "EssolinkMember_pps.html\n" +
                "AccrualFailure_KF_May25.html\n" +
                "KF_UpdateMobile2023.html\n" +
                "TPP_oldscheme_Softland_to_QPP_Mar20.html\n" +
                "KfEnrolment_Password23_Wifi.html\n" +
                "TPP_newscheme_QR_to_QPP2020.html\n" +
                "notifyAuditReleasedAc_pps.html\n" +
                "2024ES_Softland_to_Basic.html\n" +
                "TPP_Force_downgraded_to_QPP_Mar20.html\n" +
                "welcome-to-solitaire-pps-club2021.html\n" +
                "milesConversion_pps.html\n" +
                "mlTrfGuard_24.html\n" +
                "2024RequalifyToES_EN.html\n" +
                "milesConversion_pps2021.html\n" +
                "Solitaire_PPS_Club_Membership_Renewal_Gift_interim.html\n" +
                "milesConversion.html\n" +
                "mailerprefack9.html\n" +
                "ONESIAMmilesConversion_pps24.html\n" +
                "AccountUnderReview_KF.html\n" +
                "EssomilesConversion.html\n" +
                "Solitaire_Welcome_Upgrade_Gift.html\n" +
                "LastMinUpgrades_PPS.html\n" +
                "milesConversion.html\n" +
                "mlTrfMinor.html\n" +
                "Accor_MilesConversion_PPS.html\n" +
                "welcome-back-to-pps-club2021-voucher.html\n" +
                "Yuu_MilesConversion_KF.html\n" +
                "NTmilesConversion_pps.html\n" +
                "passwordAckKF.html\n" +
                "welcome-to-solitaire-pps-club2021.html\n" +
                "Yuu_MilesConversion_PPS.html\n" +
                "ONESIAMmilesConversion_kf24.html\n" +
                "AccountUnderReview_PPS.html\n" +
                "milesConversion_pps.html\n" +
                "milesConversion_kf2022_v2.html\n" +
                "EssomilesConversion_pps.html\n" +
                "Accor_MilesConversion_KF.html\n" +
                "Auditcompleted_KF.html\n" +
                "QPP_Softland_to_ES_Mar20.html\n" +
                "reQualifyToEg2021.html\n" +
                "AccountUnderAudit_PPS.html\n" +
                "OutstandingPayment_PPS.html\n" +
                "milesConversion_kf2022_v2.html\n" +
                "2022RequalifyToES_EN.html\n" +
                "TPP_newscheme_Softland_to_QPP_Mar20.html\n" +
                "2022Softland_NewScheme_TPPtoEG_EN.html\n" +
                "Life_Membership_Gift.html\n" +
                "2024UpgradetoES_EN.html\n" +
                "upgradeToEg2021.html\n" +
                "upgradeToEg_Mar20.html\n" +
                "QPP_Softland_to_EG_Mar20.html\n" +
                "PPS_Club_Membership_Renewal_Gift_interim.html\n" +
                "Consent_eDM_template_placeholder.html\n" +
                "AccountUnderAudit_KF.html\n" +
                "welcome-back-to-solitaire-pps-club2021-voucher.html\n" +
                "welcome-to-pps-club2021.html\n" +
                "SecurityAlert_PPS.html\n" +
                "GYmilesConversion.html\n" +
                "SecurityAlert_KF.html\n" +
                "OutstandingPayment_KF.html\n" +
                "EG_Softland_to_ES_2024.html";
        String textFile = "TPP_newscheme_QR_to_QPP2020.txt\n" +
                "Auditcompleted_PPS.txt\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "mlTrfGuard.txt\n" +
                "reQualifyToEs2021.txt\n" +
                "\n" +
                "Solitaire_PPS_Club_Membership_Renewal_Gift.txt\n" +
                "milesConversion.txt\n" +
                "mailerprefack9.txt\n" +
                "\n" +
                "AccountUnderReview_KF.txt\n" +
                "EssomilesConversion.txt\n" +
                "Solitaire_Welcome_Upgrade_Gift.txt\n" +
                "LastMinUpgrades_PPS.txt\n" +
                "milesConversion.txt\n" +
                "mlTrfMinor.txt\n" +
                "\n" +
                "welcome-back-to-pps-club2021-voucher.txt\n" +
                "\n" +
                "NTmilesConversion_pps.txt\n" +
                "passwordAckKF.txt\n" +
                "\n" +
                "\n" +
                "\n" +
                "AccountUnderReview_PPS.txt\n" +
                "milesConversion.txt\n" +
                "milesConversion.txt\n" +
                "EssomilesConversion_pps.txt\n" +
                "\n" +
                "Auditcompleted_KF.txt\n" +
                "\n" +
                "reQualifyToEg2021.txt\n" +
                "AccountUnderAudit_KF.txt\n" +
                "OutstandingPayment_PPS.txt\n" +
                "NTmilesConversion.txt\n" +
                "\n" +
                "\n" +
                "\n" +
                "Life_Membership_Gift.txt\n" +
                "upgradeToEs2021.txt\n" +
                "upgradeToEg2021.txt\n" +
                "upgradeToEg_Mar20.txt\n" +
                "\n" +
                "PPS_Club_Membership_Renewal_Gift.txt\n" +
                "consent_eDM_template_placeholder.txt\n" +
                "AccountUnderAudit_KF.txt\n" +
                "welcome-back-to-solitaire-pps-club2021-voucher.txt\n" +
                "\n" +
                "SecurityAlert_PPS.txt\n" +
                "GYmilesConversion.txt\n" +
                "SecurityAlert_KF.txt\n" +
                "OutstandingPayment_KF.txt\n";
        List<String> combinedList = new ArrayList<>();
        for (String txt : textFile.split("\n")) {
            if (!StringUtils.isBlank(txt)) {
                combinedList.add(txt);
            }
        }
        for (String html : templateNames.split("\n")) {
            if (!StringUtils.isBlank(html)) {
                combinedList.add(html);
            }
        }
        for (String filename : combinedList) {
            try {
                String content = FileOperations.loadContent(pathToGetTemplateFrom, filename);
                FileOperations.overwriteFile(content, pathToCopyTo, filename);
            } catch (Exception e) {
                System.out.println("Error processing file: " + filename);
            }
        }
    }





}

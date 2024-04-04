import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class TestingPurposes {


    public static void main(String[] args) throws IOException {
        System.out.println(true && true);
        String[] codes = {"program_codes", "source_of_kf_info", "source_of_enrolment", "form_of_enrolment",
                "tier_status", "account_status", "titles", "city_codes", "country_codes",
                "airport_codes", "gender", "title_gender_map", "marital_status", "mailer_pref", "carrier_codes",
                "passenger_tag", "redemption_pax_tag", "eligibility_indicators", "meal_request_codes",
                "check_in_mode", "written_language_codes", "spoken_language_codes", "compartment_class_codes",
                "mailing_addr_codes", "deck_indicator", "relationship_supplementary", "professional_status_codes",
                "mailer_pref_channel_type", "email_format_type", "aircraft_types",
                "aircraft_seat_types", "medical_meal_codes", "religious_meal_codes", "trip_type_codes", "flight_type_codes",
                "non_air_participant_type_codes", "city_country_map", "airlines_codes", "other_ffp_codes",
                "sports_games_codes", "culture_interests_codes", "leisure_codes", "hotel_codes",
                "car_rental_company_codes", "credit_card_type_codes", "industry_codes", "bank_codes",
                "region_codes", "phone_types", "insurance_participant_codes", "magazine_participant_codes",
                "income_brackets", "redemption_type", "promotion_award_type", "promotion_type", "current_promotion_status",
                "historical_promotion_status", "redemption_award_type", "open_jaw_indicator", "promotion_award_method",
                "promotion_award_percentage_type", "promotion_time_to_award", "promotion_activity_validity", "view_promotion",
                "credit_card_brand", "stop_mail_reason_mail", "stop_mail_reason_email", "non_miles_award_per",
                "relationship_guardian", "cobrand_partners", "cobrand_approval_status", "cobrand_card_status",
                "cobrand_reject_reason", "card_creation_code", "all_participant_codes", "cobrand_log_record_types",
                "promotion_communication_channel", "backup_communication_channel", "promotion_email_types",
                "credit_indicators", "amendment_codes", "transaction_codes", "admin_fee_payment_types",
                "retro_on_hold_codes", "kf_retro_reject_codes", "offp_retro_reject_codes", "account_close_reason_codes",
                "overdraft_reason_codes", "retro_offp_rej_codes_tg", "retro_offp_rej_codes_lh", "retro_offp_rej_codes_nh",
                "retro_offp_rej_codes_oz", "retro_offp_rej_codes_ek", "currency_codes", "hotel_participant_list",
                "credit_card_participant_list", "car_rental_participant_list", "insurance_participant_list",
                "magazine_participant_list", "billing_on_hold_codes", "pps_gift_category_codes", "magazine_catalog",
                "magazine_freq_of_publication", "city_control_city_map", "dept_station_codes", "acct_group_types",
                "acct_group_relationship_codes", "ctrl_city_codes", "retro_doc_indicators","retro_trans_codes",
                "prtcpnt_type_list", "card_type_list", "func_type_list", "offp_ovrrdg_reason_list","star_carrier_codes", "redemption_type_upgrade",
                "redemption_type_all","control_parameter_desc", "control_parameter_label", "customer_type", "reason_cust_type", "program_relationship_indicator", "promotion_status", "exitcode_validpin","sq_carrier_codes",
                "socialmedia_id", "compartment_class_codes_pey","event_type","point_conversion_status_type","link_status_type","mailer_pref_new",
                "Corporate_participant_codes","corp_retro_reject_codes","corp_retro_on_hold_codes","corporate_country_codes","corporate_account_status","corporate_sr_participant_codes","corp_carrier_code","ru_mailer_pref","corp_event_type","stop_mail_reason_phone","status_match_verify","gy_status_match_verify","spl_point_conv_partners","OTHER_FFP_DOUBLE_STEP_PRT_CODES"};
        System.out.println(codes.length);
    }

    private static void printParams(NodeList<Parameter> parameters) {
        for (Parameter parameter : parameters) {
            System.out.println(String.format("type: %s", parameter.getType()));
        }
    }

    private static void test() throws Exception {
        System.out.println("Hello");
        throw new Exception("No particular error");
    }


}
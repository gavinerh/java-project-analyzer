import model.Variable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SampleT {
    private static String setStatements = "pstmt.setString(1, splCustType);\n" +
            "            pstmt.setString(2, auditID);\n" +
            "pstmt.setLong(7, internalID);";

    public static void main(String[] args) {
//        generateMethodsFromSetAndGetQueryClass();

        String[] typesArr = new String[]{"List<String>",
                "List<GeneralSqlObject>", "int", "List<Integer>"};
        String sql = "SELECT MAX(SEQ_NO) FROM CUSTYPE_CHG_DTLS WHERE INT_ID = ?";
        String sqlOperation = "update";
        String nameOfXMLMethod = "updateCusPersCustomerTypeData";
        String mapperName = "cus_pers";
        String returnType = typesArr[2];
        boolean toIncludeM_ = false;
        returnType = toConvertReturnType(returnType, mapperName,false);
        sql = generateSqlStatementsFromString(); // java string --> sql statement
        String xmlSql = convertQuestionMrkToParam(sql, sqlOperation, nameOfXMLMethod, returnType, toIncludeM_); // ? --> #{}
        convertMybatisCodeToMethodParams(xmlSql, returnType); // mybatis xml --> java mapper method
        printMethodUsage(nameOfXMLMethod, mapperName, returnType, toIncludeM_);
        printSetStatementsForTest(nameOfXMLMethod); // convert get to set statements for testing

//        returnGetMethodNames(); // rs.getString --> getCustomColumn
//
//        populateSqlSelectColumnsToXml(); // select a, b, c --> <result column="a" property=""/>
//        generateXmlResultMapFromSelect(); // eg card.set... --> <result column=...

//        matchGetColumnsWithSqlColumnNames(); // specialised use case where rs.getString(1)
//        matchSqlColumnsWithResultSetGet(); // specialised use case where rs.getString(++j)

//        generateCustom(); // use the setStatements to generate #{variable.fieldName,jdbcType=dataType}
//        onlyAddUpInfoWithinAppendMethod();
//


//        generateMethodsFromQueryClass();

//         generateMapperClassFromSqlColumnsAndSetter();

//        compareTwoOutputs();

//        countNumberOfColumns();
//        printSetNumberOfColumns();

//        matchRsGetStringWithColumnByColNum(); // rs.getString(i) <-- col
    }

    private static String toConvertReturnType(String returnType, String tableName, boolean toConvert){
        if(toConvert){
            String name = convertSqlColumnNameToFieldName(tableName);
            name = name.substring(0,1).toUpperCase() + name.substring(1);
            return String.format("List<%s>", name);
        }
        return returnType;
    }

    private static void matchRsGetStringWithColumnByColNum() {

    }

    private static void printSetNumberOfColumns() {
        for (int i = 1; i < 22; i++) {
            System.out.print("column" + i + ", ");
        }
    }

    private static void countNumberOfColumns() {
        String columns = "PRG_CD,INT_ID,CUS_ID,PRT_CD,PROMO_AWD_DESC, BONUS_AWDED,PROMO_CD,PTS_VALIDITY_PRD,TRANS_XREF_ID,PROMO_XREF_ID,TIER_BONUS_IND,CD_SHARE_PRT,BILL_TO_PRT,PRT_ANA_IND,null,null,PROMO_AWD_DT,PRT_REF_CD,BATCH_ID,ELITE_BONUS_MILES_AWDED,PPS_BONUS_VALUE_AWDED";
        String column2 = "PRG_CD,INT_ID,CUS_ID,null,PROMO_AWARD_DESC,0, PROMO_CD,0,TRANS_XREF_ID,null,'P',null,null,null,NON_MILE_REWARD_TYPE_IND,AWARD_QTY,AWARD_DT,null,null,0,0";
        String[] columnsArr = columns.split(",");
        String[] column2Arr = column2.split(",");
        System.out.println("column1 length: " + columnsArr.length);
        System.out.println("column2 length: " + column2Arr.length);
        List<String> completeColumnNames = new ArrayList<>();
        for (int i = 0; i < columnsArr.length; i++) {
            System.out.println(String.format("%s -- %s", columnsArr[i], column2Arr[i]));
            if (!columnsArr[i].trim().equals("null")) {
                completeColumnNames.add(columnsArr[i]);
            } else {
                completeColumnNames.add(column2Arr[i]);
            }
        }
        for (String col : completeColumnNames) {
            System.out.print(col + ", ");
        }
    }

    private static void printMethodUsage(String xmlMethodName, String mapperName, String returnType, boolean toIncludeM_) {
        mapperName = convertSqlColumnNameToFieldName(mapperName);
        mapperName += "Mapper";
        String variableName = "";
        if (returnType.equals("int")) {
            variableName = "success";
        } else if (returnType.contains("List")) {
            variableName = "list";
        } else {
            variableName = "val";
        }
        String toPrint = String.format("%s %s = %s.%s(", returnType, variableName, mapperName, xmlMethodName);
        List<Variable> variableList = getParamsFromSetStatements(toIncludeM_);
        variableList.size();
        for (int i = 0; i < variableList.size(); i++) {
            toPrint += variableList.get(i).name;
            if (i < variableList.size() - 1) {
                toPrint += ",";
            }
        }
        toPrint += ");";
        System.out.println(toPrint);
    }

    private static void compareTwoOutputs() {
        String output1 = "getCpyCd --- null\n" +
                "getCpnNo --- 1\n" +
                "getTitle --- null\n" +
                "getTktNo --- 6185657689451\n" +
                "getCpyNo --- 1\n" +
                "getCusId --- null\n" +
                "getTktDt --- null\n" +
                "getDob --- null\n" +
                "getDelayInd --- null\n" +
                "getCorpId --- null\n" +
                "getFscVal --- 5\n" +
                "getCrSrcInd --- P\n" +
                "getDisrptdFltMatch --- 5\n" +
                "getFfpReverseDt --- null\n" +
                "getPpsPin --- null\n" +
                "getAccrInd --- null\n" +
                "getAccrualStatus --- C\n" +
                "getInternetBkgInd --- null\n" +
                "getGender --- null\n" +
                "getTktPos --- F\n" +
                "getProcessStatus --- null\n" +
                "getBatchDt --- Wed Jan 22 00:00:00 SGT 2020\n" +
                "getFamilyName --- TEST\n" +
                "getGivenName --- Gavin\n" +
                "getLchgDt --- Wed Jan 18 10:59:01 SGT 2023\n" +
                "getRcreUserId --- GGUser\n" +
                "getRcreDt --- Fri Jan 22 00:00:00 SGT 2021\n" +
                "getLchgUserId --- Z\n" +
                "getSeatNo --- null\n" +
                "getPnrRef --- MI53s8\n" +
                "getEligibilityInd --- null\n" +
                "getPpsCrDt --- Wed Jan 18 00:00:00 SGT 2023\n" +
                "getFfpCrDt --- Wed Jan 18 00:00:00 SGT 2023\n" +
                "getPaxTag --- UL\n" +
                "getFfpNoRes --- null\n" +
                "getFfpAutoRetroInd --- M\n" +
                "getFfpBillDt --- null\n" +
                "getPpsAutoRetroInd --- M\n" +
                "getPpsReverseDt --- null\n" +
                "getPpsBillDt --- null\n" +
                "getFfpNoRdpn --- null\n" +
                "getFfpCdRdpn --- null\n" +
                "getNationality --- null\n" +
                "getPassportName --- TEst\n" +
                "getPassportNo --- F1234566\n" +
                "getDcsOverwriteTag --- null\n" +
                "getPromoProcessFlg --- Y\n" +
                "getBookedCompCls --- J\n" +
                "getBookedSellingCls --- J\n" +
                "getInterlineInd --- A\n" +
                "getUploadErrCd --- null\n" +
                "getPpsPinCr --- A\n" +
                "getPpsTrvlClsCr --- U\n" +
                "getFfpCdRes --- null\n" +
                "getFfpCdDcs --- null\n" +
                "getFfpNoDcs --- null\n" +
                "getFfpCdCr --- SK\n" +
                "getFfpNoCr --- 12345678901\n" +
                "getFfpTrvlClsCr --- U\n" +
                "getFlownCompCls --- J\n" +
                "getPtOfSale --- @DEA1A\n" +
                "getVolInvolTag --- null\n" +
                "getRdpnPaxTag --- null\n" +
                "getMixedClsItinTag --- null\n" +
                "getUpgrDowngrTag --- null\n" +
                "getPdokStatus --- null\n" +
                "geteTktInd --- null\n" +
                "geteUpgrdInd --- null\n" +
                "getChkInMode --- null\n" +
                "getSalesPromoCd --- null\n" +
                "getRdpnPromoCd --- null\n" +
                "getMvsMigSeq --- 002\n" +
                "getPpsTrvlCls --- null\n" +
                "getCertificateNumber --- null\n" +
                "getBookedCommCls --- null\n" +
                "getRejErrCd --- null\n" +
                "getFfpCdSpon --- null\n" +
                "getFfpNoSpon --- null\n" +
                "getLocalCurrencyCd --- A\n" +
                "getLocalGrossAmt --- 3\n" +
                "getGrossProSgd --- 2\n" +
                "getNetProSgd --- 3\n" +
                "getSegmentProVal --- 4\n" +
                "getExchgRt --- 4\n" +
                "getCorpIdCr --- null\n" +
                "getCpsReverseDt --- null\n" +
                "getCpsCrDt --- null\n" +
                "getCpsBillDt --- null\n" +
                "getCorpIdInPas --- null\n" +
                "getFamilyname --- TEST\n" +
                "getGivenname --- Gavin\n" +
                "getSqmobileInd --- null\n" +
                "getPlsgrdInd --- null\n" +
                "getCorpAccruable --- null\n" +
                "getCorpAccruableStatus --- null\n" +
                "getCorpClsCredited --- null\n" +
                "getCorpCrdIndicator --- null\n" +
                "getCorpLevel --- null\n" +
                "getDbacVchrNum --- null\n" +
                "getSaugVchrNum --- R12345676754\n" +
                "getCorpType --- null\n" +
                "getUciInd --- null\n" +
                "getRefId --- 201807209210\n" +
                "getPaxId --- 400000";
        String output2 = "getCusId --- null\n" +
                "getTktDt --- null\n" +
                "getTktNo --- 6185657689451\n" +
                "getCpyNo --- 1\n" +
                "getTitle --- null\n" +
                "getCpnNo --- 1\n" +
                "getCpyCd --- null\n" +
                "getDob --- null\n" +
                "getFfpNoCr --- 12345678901\n" +
                "getFfpTrvlClsCr --- u\n" +
                "getFlownCompCls --- J\n" +
                "getPtOfSale --- @DEA1A\n" +
                "getVolInvolTag --- null\n" +
                "getRdpnPaxTag --- null\n" +
                "getMixedClsItinTag --- null\n" +
                "getUpgrDowngrTag --- null\n" +
                "getPdokStatus --- null\n" +
                "geteTktInd --- null\n" +
                "geteUpgrdInd --- null\n" +
                "getChkInMode --- null\n" +
                "getSalesPromoCd --- null\n" +
                "getRdpnPromoCd --- null\n" +
                "getMvsMigSeq --- 002\n" +
                "getPpsTrvlCls --- null\n" +
                "getCertificateNumber --- null\n" +
                "getBookedCommCls --- null\n" +
                "getRejErrCd --- null\n" +
                "getFfpCdSpon --- null\n" +
                "getFfpNoSpon --- null\n" +
                "getLocalCurrencyCd --- A\n" +
                "getLocalGrossAmt --- 3\n" +
                "getGrossProSgd --- 2\n" +
                "getNetProSgd --- 3\n" +
                "getSegmentProVal --- 4\n" +
                "getExchgRt --- 4\n" +
                "getCorpIdCr --- null\n" +
                "getCpsReverseDt --- null\n" +
                "getCpsCrDt --- null\n" +
                "getCpsBillDt --- null\n" +
                "getCorpIdInPas --- null\n" +
                "getFamilyname --- TEST\n" +
                "getGivenname --- Gavin\n" +
                "getSqmobileInd --- null\n" +
                "getPlsgrdInd --- null\n" +
                "getCorpAccruable --- null\n" +
                "getCorpAccruableStatus --- null\n" +
                "getCorpClsCredited --- null\n" +
                "getCorpCrdIndicator --- null\n" +
                "getCorpLevel --- null\n" +
                "getDbacVchrNum --- null\n" +
                "getSaugVchrNum --- R12345676754\n" +
                "getCorpType --- null\n" +
                "getUciInd --- null\n" +
                "getFfpNoRdpn --- null\n" +
                "getFfpCdRdpn --- null\n" +
                "getPromoProcessFlg --- Y\n" +
                "getBookedCompCls --- J\n" +
                "getBookedSellingCls --- J\n" +
                "getInterlineInd --- A\n" +
                "getFfpNoDcs --- null\n" +
                "getFfpCdCr --- SK\n" +
                "getFfpNoRes --- null\n" +
                "getUploadErrCd --- null\n" +
                "getFfpAutoRetroInd --- M\n" +
                "getPpsAutoRetroInd --- M\n" +
                "getFfpBillDt --- null\n" +
                "getPpsPinCr --- a\n" +
                "getPpsTrvlClsCr --- u\n" +
                "getNationality --- null\n" +
                "getPassportName --- TEst\n" +
                "getPassportNo --- F1234566\n" +
                "getDcsOverwriteTag --- null\n" +
                "getPpsBillDt --- null\n" +
                "getFfpReverseDt --- null\n" +
                "getPpsPin --- null\n" +
                "getAccrInd --- null\n" +
                "getAccrualStatus --- C\n" +
                "getInternetBkgInd --- null\n" +
                "getGender --- null\n" +
                "getTktPos --- F\n" +
                "getProcessStatus --- null\n" +
                "getPaxTag --- UL\n" +
                "getLchgDt --- Wed Jan 18 11:25:44 SGT 2023\n" +
                "getRcreUserId --- GGUser\n" +
                "getRcreDt --- Fri Jan 22 00:00:00 SGT 2021\n" +
                "getLchgUserId --- z\n" +
                "getPpsReverseDt --- null\n" +
                "getBatchDt --- Wed Jan 22 00:00:00 SGT 2020\n" +
                "getFamilyName --- TEST\n" +
                "getGivenName --- Gavin\n" +
                "getSeatNo --- null\n" +
                "getPnrRef --- MI53s8\n" +
                "getEligibilityInd --- null\n" +
                "getPpsCrDt --- Wed Jan 18 00:00:00 SGT 2023\n" +
                "getFfpCrDt --- Wed Jan 18 00:00:00 SGT 2023\n" +
                "getDelayInd --- null\n" +
                "getCorpId --- null\n" +
                "getFscVal --- 5\n" +
                "getCrSrcInd --- P\n" +
                "getDisrptdFltMatch --- 5\n" +
                "getFfpCdRes --- null\n" +
                "getFfpCdDcs --- null\n" +
                "getRefId --- 201807209210\n" +
                "getPaxId --- 400000";
        Map<String, String> mapForOutput1 = new HashMap<>();
        Map<String, String> mapForOutput2 = new HashMap<>();
        String[] output1Split = output1.split("\n");
        String[] output2Split = output2.split("\n");
        for (String s : output1Split) {
            String methodName = s.split("---")[0].trim();
            String value = s.split("---")[1].trim();
            mapForOutput1.put(methodName, value);
        }
        for (String s : output2Split) {
            String methodName = s.split("---")[0].trim();
            String value = s.split("---")[1].trim();
            mapForOutput2.put(methodName, value);
        }
        if (mapForOutput1.size() != mapForOutput2.size()) {
            throw new RuntimeException("Size of the map is different");
        }
        for (String key : mapForOutput1.keySet()) {
            String valueOfOutput1 = mapForOutput1.get(key);
            String valueOfOutput2 = mapForOutput2.get(key);
            if (!valueOfOutput2.equals(valueOfOutput1)) {
                System.out.println(valueOfOutput2 + " is different from " + valueOfOutput1);
            }
        }
    }

    private static void onlyAddUpInfoWithinAppendMethod() {
        String statements = "buffer.append(\"UPDATE PXM_PAX SET \");\n" +
                "            if (ppsBktFlg.equals(\"Y\")) {\n" +
                "                buffer.append(\"PPS_PIN_CR = ?, \");\n" +
                "                buffer.append(\"PPS_TRVL_CLS_CR = ?, PPS_CR_DT = trunc(sysdate), \");\n" +
                "                buffer.append(\"PPS_AUTO_RETRO_IND = ?, PPS_REVERSE_DT = null, \");\n" +
                "                if (\"D\".equals(acrPts.getPpsValueSrcInd()) ||\n" +
                "                        \"O\".equals(acrPts.getPpsValueSrcInd())) {\n" +
                "                    buffer.append(\"SEGMENT_PRO_VAL = ?, \");\n" +
                "                    buffer.append(\"FSC_VAL = ? , TKT_POS = ? ,\");\n" +
                "                    /* Added by Saranya on 15May2013 for MKP91765_CR: Include FSC Field at PaxManifest Level Ends */\n" +
                "                }\n" +
                "            }\n" +
                "\n" +
                "            if (ffpBktFlg.equals(\"Y\")) {\n" +
                "                buffer.append(\"FFP_CD_CR = ?, FFP_NO_CR = ?, FFP_TRVL_CLS_CR = ?, \");\n" +
                "                buffer.append(\"FFP_CR_DT = trunc(sysdate),  \");\n" +
                "                buffer.append(\"FFP_AUTO_RETRO_IND = ?, FFP_REVERSE_DT = null, \");\n" +
                "            }\n" +
                "            if (eliteBktFlg.equals(\"Y\") && ffpBktFlg.equals(\"N\")) {\n" +
                "                buffer.append(\"FFP_CD_CR = ?, FFP_NO_CR = ?, FFP_TRVL_CLS_CR = ?, \");\n" +
                "                buffer.append(\"FFP_CR_DT = trunc(sysdate),  \");\n" +
                "                buffer.append(\"FFP_AUTO_RETRO_IND = ?, FFP_REVERSE_DT = null, \");\n" +
                "\n" +
                "            }\n" +
                "            if (acrPts.getCPSBktFlg() != null) {\n" +
                "                if (passCPSCred != null) {\n" +
                "                    if (acrPts.getCPSBktFlg().equals(\"Y\") && passCPSCred.getOnHold().equals(\"N\")) {\n" +
                "                        buffer.append(\"CORP_ID_CR = ?,\");\n" +
                "                        buffer.append(\"CPS_CR_DT = trunc(sysdate),  \");\n" +
                "                        buffer.append(\" CPS_REVERSE_DT = null, \");\n" +
                "                        buffer.append(\"CORP_ACCRUABLE=?,CORP_ACCRUABLE_STATUS =? ,CORP_CLS_CREDITED=?, CORP_LEVEL=?, CORP_CRD_INDICATOR=?, \"); //Added by vinod for SME-FFP\n" +
                "                    } else {\n" +
                "                        buffer.append(\" CORP_ACCRUABLE_STATUS =?, CORP_CRD_INDICATOR=?,\");\n" +
                "                    }\n" +
                "                }\n" +
                "            }\n" +
                "\n" +
                "            //Accrual_status='p' pending for cps\n" +
                "            m_Logger.info(\"ppsOnHoldFlg:::\" + ppsOnHoldFlg);\n" +
                "            if (passCPSCred != null && passCPSCred.getOnHold() != null && passCPSCred.getOnHold().equals(\"Y\")) {\n" +
                "                buffer.append(\"ACCRUAL_STATUS = 'P', \");\n" +
                "            } else if (!inputModeInd.equals(\"R\") && !inputModeInd.equals(\"F\")\n" +
                "                    && ppsOnHoldFlg != null && ppsOnHoldFlg.equals(\"Y\")) {\n" +
                "                buffer.append(\"ACCRUAL_STATUS = 'P', \");\n" +
                "            } else {\n" +
                "                buffer.append(\"ACCRUAL_STATUS = 'C', \");\n" +
                "            }\n" +
                "            buffer.append(\"LCHG_USER_ID = ?, \");\n" +
                "            buffer.append(\"LCHG_DT = sysdate \");\n" +
                "            buffer.append(\"where REF_ID = ? AND PAX_ID = ? \");";
        String finalString = "";
        String[] splitStatements = statements.split("\n");
        for (int i = 0; i < splitStatements.length; i++) {
            if (splitStatements[i].contains(".append") && !splitStatements[i].startsWith("//")) {
                int start = splitStatements[i].indexOf(".append");
                start = splitStatements[i].indexOf("\"", start) + 1;
                int end = splitStatements[i].indexOf("\"", start);
                String toAppend = splitStatements[i].substring(start, end).trim();
                toAppend += " ";
                finalString += toAppend;
            }
        }
        System.out.println(finalString);
    }

    private static void generateCustom() {
        String[] setStatementSplit = setStatements.split("\n");
        String xmlStatement = "<update id=\"updatePaxRecord\">\n" +
                "        UPDATE PXM_PAX SET\n" +
                "        <if test='ppsBktFlg.equals(\"Y\")'>\n" +
                "            PPS_PIN_CR = ?, PPS_TRVL_CLS_CR = ?, PPS_CR_DT = trunc(sysdate),\n" +
                "            PPS_AUTO_RETRO_IND = ?, PPS_REVERSE_DT = null,\n" +
                "            <if test='\"D\".equals(acrPts.getPpsValueSrcInd()) || \"O\".equals(acrPts.getPpsValueSrcInd())'>\n" +
                "                SEGMENT_PRO_VAL = ?, FSC_VAL = ? , TKT_POS = ? ,\n" +
                "            </if>\n" +
                "        </if>\n" +
                "        <if test='ffpBktFlg.equals(\"Y\")'>\n" +
                "            FFP_CD_CR = ?, FFP_NO_CR = ?, FFP_TRVL_CLS_CR = ?,\n" +
                "            FFP_CR_DT = trunc(sysdate), FFP_AUTO_RETRO_IND = ?, FFP_REVERSE_DT = null,\n" +
                "        </if>\n" +
                "        <if test='eliteBktFlg.equals(\"Y\") &amp;&amp; ffpBktFlg.equals(\"N\")'>\n" +
                "            FFP_CD_CR = ?, FFP_NO_CR = ?, FFP_TRVL_CLS_CR = ?, FFP_CR_DT = trunc(sysdate),\n" +
                "            FFP_AUTO_RETRO_IND = ?, FFP_REVERSE_DT = null,\n" +
                "        </if>\n" +
                "        <if test='acrPts.getCPSBktFlg() != null &amp;&amp; passCPSCred != null'>\n" +
                "            <choose>\n" +
                "                <when test='acrPts.getCPSBktFlg().equals(\"Y\") &amp;&amp; passCPSCred.getOnHold().equals(\"N\")'>\n" +
                "                    CORP_ID_CR = ?, CPS_CR_DT = trunc(sysdate),CPS_REVERSE_DT = null,\n" +
                "                    CORP_ACCRUABLE=?,CORP_ACCRUABLE_STATUS =? ,CORP_CLS_CREDITED=?, CORP_LEVEL=?, CORP_CRD_INDICATOR=?,\n" +
                "                </when>\n" +
                "                <otherwise>\n" +
                "                    CORP_ACCRUABLE_STATUS =?, CORP_CRD_INDICATOR=?,\n" +
                "                </otherwise>\n" +
                "            </choose>\n" +
                "        </if>\n" +
                "        <choose>\n" +
                "            <when test='passCPSCred != null &amp;&amp; passCPSCred.getOnHold()!= null &amp;&amp; passCPSCred.getOnHold().equals(\"Y\")'>\n" +
                "                ACCRUAL_STATUS = 'P',\n" +
                "            </when>\n" +
                "            <when test='!inputModeInd.equals(\"R\") &amp;&amp; !inputModeInd.equals(\"F\") &amp;&amp; ppsOnHoldFlg!= null &amp;&amp;  ppsOnHoldFlg.equals(\"Y\")'>\n" +
                "                ACCRUAL_STATUS = 'P',\n" +
                "            </when>\n" +
                "            <otherwise>\n" +
                "                ACCRUAL_STATUS = 'C',\n" +
                "            </otherwise>\n" +
                "        </choose>\n" +
                "        LCHG_USER_ID = ?, LCHG_DT = sysdate where REF_ID = ? AND PAX_ID = ?\n" +
                "    </update>";
        int numOfQuestion = 0;
        for (int i = 0; i < xmlStatement.length(); i++) {
            if (xmlStatement.substring(i, i + 1).equals("?")) {
                numOfQuestion++;
            }
        }
        System.out.println("Number of question: " + numOfQuestion);
        List<Variable> variableList = new ArrayList<>();
        boolean containsM_ = true;
        boolean variableStartsWithUpperCase = true;
        for (int i = 0; i < setStatementSplit.length; i++) {
            if (setStatementSplit[i].contains("ps.set")) {
                String dataType = "";
                String fieldName = "";
                String instanceName = "";
                String finalString = "";
                Variable variable = new Variable();
                int start = setStatementSplit[i].indexOf("set") + 3;
                int end = setStatementSplit[i].indexOf("(");
                dataType = getDataType(setStatementSplit[i]);
                String[] instanceNamePlusFieldName = setStatementSplit[i].split(",")[1].trim().split("\\.");
                instanceName = instanceNamePlusFieldName[0].trim();
                end = instanceNamePlusFieldName[1].indexOf("(");
                fieldName = instanceNamePlusFieldName[1].substring(0, end);
                if (containsM_) {
                    finalString = instanceName + ".m_";
                    if (!variableStartsWithUpperCase) {
                        // by right should come with upper case
                        finalString += fieldName.substring(0, 1).toLowerCase();
                        finalString += fieldName.substring(1);
                    } else {
                        finalString += fieldName;
                    }
                } else {
                    finalString = instanceName + "." + fieldName;
                }
                variable.name = finalString;
                variable.type = dataType;
                variableList.add(variable);
            }
        }
        System.out.println("Size of variableList: " + variableList.size());
        for (Variable variable : variableList) {
            String toprint = String.format("#{%s,jdbcType=%s}", variable.name, variable.type);
            System.out.println(toprint);
        }
        System.out.println("\n\n");
        // variableList is fully added, now match the variable to the question mark
        int count = 0;
        String xmlToPrint = "";
        for (int i = 0; i < xmlStatement.length(); i++) {
            if (xmlStatement.substring(i, i + 1).equals("?")) {
                String replacementString = "#{";
                Variable variable = variableList.get(count);
                count++;
                replacementString += variable.name + ",jdbcType=";
                replacementString += variable.type + "}";
                xmlToPrint += replacementString;
            } else {
                xmlToPrint += xmlStatement.substring(i, i + 1);
            }
        }
        System.out.println(xmlToPrint);

    }


    public static void generateMapperClassFromSqlColumnsAndSetter() {
        String existingMapper = "";
        boolean isUsingGeneralSqlObject = false;
        String[] existingMapperSplit = null;
        Map<String, Integer> map = new HashMap<>();
        if (!existingMapper.equals("")) {
            existingMapperSplit = existingMapper.split("\n");
            for (int i = 0; i < existingMapperSplit.length; i++) {
                map.put(existingMapperSplit[i].trim(), 1);
            }
        }
        String setter = "flightInfo.setBoardPoint(rs.getString(\"ORG_CD\"));\n" +
                "        flightInfo.setCarrierCode(rs.getString(\"BP_CARRIER_CD\"));\n" +
                "        flightInfo.setCarrierFltNo(rs.getInt(\"BP_FLT_NO\"));\n" +
                "        flightInfo.setFlightDate(rs.getDate(\"FLT_DT\"));\n" +
                "        flightInfo.setMarketCarrierCode(rs.getString(\"TKT_CARRIER_CD\"));\n" +
                "        flightInfo.setMarketFltNo(rs.getInt(\"TKT_FLT_NO\"));\n" +
                "        flightInfo.setOffPoint(rs.getString(\"DES_CD\"));\n" +
                "        flightInfo.setAirCodeShare(rs.getString(\"CD_SHARE_IND\"));";
        String sqlColumns = "";
        boolean variableContainM_ = true;
        boolean variableFirstCharIsLowercase = true;
        String[] sqlColumnsSplit = null;
        String[] setterSplit = setter.split("\n");
        if (!sqlColumns.equals("")) {
            sqlColumnsSplit = sqlColumns.split(",");
        } else {
            sqlColumnsSplit = new String[setterSplit.length];
            // get the column names from setter
            for (int i = 0; i < setterSplit.length; i++) {
                int start = setterSplit[i].indexOf("get");
                start = setterSplit[i].indexOf("\"", start) + 1;
                int end = setterSplit[i].indexOf("\"", start);
                String column = setterSplit[i].substring(start, end).trim();
                sqlColumnsSplit[i] = column;
            }
        }
        int count = 0;
        Map<String, Integer> mapOfSetter = new HashMap<>();
        List<String> setterList = new ArrayList<>();
        List<String> dataTypeList = new ArrayList<>();
        for (String s : setterSplit) {
            if (isUsingGeneralSqlObject) {
                // extract the data type
                String dataType = "";
                int startIndex = s.indexOf("get") + 3;
                int endIndex = s.indexOf("(", startIndex);
                dataType = s.substring(startIndex, endIndex);
                dataTypeList.add(dataType);
            }

            if (s.contains("set")) {
                String variableName = "";
                int start = s.indexOf("set") + 3;
                int end = s.indexOf("(", start);

                String firstChar = s.substring(start, start + 1).toLowerCase();
                variableName = firstChar + s.substring(start + 1, end);

                if (!mapOfSetter.containsKey(variableName)) {
                    mapOfSetter.put(variableName, 1);
                    setterList.add(variableName);
                }
            }
        }
        // all the variables in order is in list
        if (setterList.size() != sqlColumnsSplit.length) {
            throw new RuntimeException("length of the variable list does not match sql columns list");
        }
        for (int i = 0; i < setterList.size(); i++) {
            String column = sqlColumnsSplit[count];
            column = generateSqlVariableFromSqlColumn(column);
            String toPrint = String.format("@Mapping(target = \"%s\", source = \"%s\"", setterList.get(i), column);
            if (isUsingGeneralSqlObject) {
                String type = dataTypeList.get(i);
                if (!type.equals("String")) {
                    toPrint += ", qualifiedByName = \"stringTo" + type + "\")";
                } else {
                    toPrint += ")";
                }
            } else {
                toPrint += ")";
            }

            if (!map.containsKey(toPrint)) {
                map.put(toPrint, 1);
            }
            count++;
        }
        for (String key : map.keySet()) {
            System.out.println(key);
        }
    }

    private static String generateSqlVariableFromSqlColumn(String s) {
        s = s.toLowerCase().trim(); //PHONE_AREA_CD --> phoneAreaCd
        String[] split = s.split("_");
        String toReturn = split[0];
        for (int i = 1; i < split.length; i++) {
            String firstChar = split[i].substring(0, 1).toUpperCase();
            toReturn += firstChar + split[i].substring(1);
        }
        return toReturn;
    }

    public static void generateMethodsFromQueryClass() {
        String s = "query.bind(spokenLang);\n" +
                "                        query.setAuditId(customer.getAgentID());\n" +
                "                        ase.setReturnQuery(ProfileSyncData.class, \"getSpokenLangISOCtryCd\", query);";
        String[] splitStr = s.split("\n");
        List<String> params = new ArrayList<>();
        String className = "";
        String methodName = "";
        // loop through
        for (String sentence : splitStr) {
            if (sentence.contains("bind")) {
                int startIndex = sentence.indexOf("(");
                int endIndex = sentence.indexOf(")");
                String paramName = sentence.substring(startIndex + 1, endIndex);
                params.add(paramName);
            }
            if (sentence.contains(".class")) { // make sure the database.setQuery method call is in one line
                int startIndex = sentence.indexOf("(");
                int endIndex = sentence.indexOf(".class");
                className = sentence.substring(startIndex + 1, endIndex);
                className = className.substring(0, 1).toLowerCase() + className.substring(1);
                startIndex = sentence.indexOf("\"") + 1;
                endIndex = sentence.indexOf("\"", startIndex);
                methodName = sentence.substring(startIndex, endIndex);
            }
            if (sentence.contains("setAuditId")) {
                int start = sentence.indexOf("(") + 1;
                int end = sentence.length() - 2;
                System.out.println(String.format("sqlCommonService.setAuditId(%s);", sentence.substring(start, end)));
            }
        }
        String finalString = String.format("%s.%s(", className, methodName);
        for (int i = 0; i < params.size(); i++) {
            finalString += params.get(i);
            if (i != params.size() - 1) {
                finalString += ",";
            }
        }
        finalString += ");";
        System.out.println(finalString);

    }

    private static void matchGetColumnsWithSqlColumnNames() {
        String variableName = "cusAccount";
        String setStatements = "ps.setString(1, userId);\n" +
                "                ps.setLong(2, rs.getLong(2));\n" +
                "                ps.setString(3, rs.getString(3));\n" +
                "                ps.setString(4, newCusID);\n" +
                "                ps.setString(5, rs.getString(5));\n" +
                "                ps.setDate(6, rs.getDate(6));\n" +
                "                ps.setString(7, rs.getString(7));\n" +
                "                ps.setString(8, rs.getString(8));\n" +
                "                ps.setString(9, rs.getString(9));\n" +
                "                ps.setDate(10, rs.getDate(10));\n" +
                "                ps.setString(11, rs.getString(11));\n" +
                "                ps.setString(12, rs.getString(12));\n" +
                "                ps.setString(13, rs.getString(13));";
        String[] setStatementsSplit = setStatements.split("\n");
        String columns = "ACCT_STATUS_CHG_DT, ACCT_STATUS_IND, ACCT_STATUS_RSN_CD, ACCT_STATUS_RSN_DESC, RCRE_USER_ID, TIER_STATUS_IND, CUS_ID, INT_ID, IS_YEC_FLG, PRG_CD, TIER_STATUS_CHG_DT, LCHG_USER_ID,LCHG_DT ,HV_FLG ,ACV_VALUE ,ACV_OVERRIDE_FLG";
        String[] columnNamesArr = columns.split(",");
        for (int i = 0; i < setStatementsSplit.length; i++) {
            if (setStatementsSplit[i].contains("get")) {
                int startIndex = setStatementsSplit[i].indexOf("(", setStatementsSplit[i].indexOf("get")) + 1;
                int endIndex = setStatementsSplit[i].indexOf(")", startIndex);
                int columnCount = Integer.parseInt(setStatementsSplit[i].substring(startIndex, endIndex));
                startIndex = setStatementsSplit[i].indexOf("rs");
                String replacementColumnName = columnNamesArr[columnCount - 1];
                replacementColumnName = convertSqlColumnNameToFieldName(replacementColumnName);
                setStatementsSplit[i].replace(setStatementsSplit[i].substring(startIndex, endIndex), replacementColumnName);
                String finalString = setStatementsSplit[i].substring(0, startIndex);
                finalString += variableName + "." + replacementColumnName + ");";
                System.out.println(finalString);
            } else {
                System.out.println(setStatementsSplit[i]);
            }
        }
    }

    private static void matchSqlColumnsWithResultSetGet() {
        String variableName = "offpTrvlCls";
        String resultGet = "tvlCls.setFuncType(rs.getString(\"FUNC_TYPE\"));\n" +
                "                tvlCls.setPrtCode(rs.getString(\"OFFP_PRT_CD\"));\n" +
                "                tvlCls.setCabinCls(rs.getString(\"SQ_CABIN_CLS\"));\n" +
                "                tvlCls.setCabinClsDesc(rs.getString(\"SQ_CABIN_CLS_DESC\"));\n" +
                "                tvlCls.setSubCls(rs.getString(\"SQ_SUB_CLS\"));\n" +
                "                tvlCls.setSubClsDesc(rs.getString(\"SQ_SUB_CLS_DESC\"));\n" +
                "                if (rs.getDate(\"START_DT\") != null) {\n" +
                "                    tvlCls.setStartDt(new java.util.Date(rs.getDate(\"START_DT\").getTime()));\n" +
                "                } else {\n" +
                "                    tvlCls.setStartDt(null);\n" +
                "                }\n" +
                "                if (rs.getDate(\"END_DT\") != null) {\n" +
                "                    tvlCls.setEndDt(new java.util.Date(rs.getDate(\"END_DT\").getTime()));\n" +
                "                } else {\n" +
                "                    tvlCls.setEndDt(null);\n" +
                "                }\n" +
                "                tvlCls.setMultiplier(rs.getFloat(\"MULTIPLIER\"));\n" +
                "                tvlCls.setMinMiles(rs.getInt(\"MIN_MILES\"));\n" +
                "                tvlCls.setToleranceLvl(rs.getInt(\"TOLERANCE_LVL\"));\n" +
                "                tvlCls.setMultiplierForFFPA(rs.getFloat(\"MULTIPLIER_FOR_FFPA\"));";
        String[] resultSetStatements = resultGet.split("\n");
        String sqlColumns = "";
        String[] sqlColumnsArr = null;
        if (!sqlColumns.equals("")) {
            sqlColumnsArr = sqlColumns.split(",");
        } else {
            sqlColumnsArr = new String[resultSetStatements.length];
            for (int i = 0; i < resultSetStatements.length; i++) {
                int positionOfRs = resultSetStatements[i].indexOf("rs.");
                int start = resultSetStatements[i].indexOf("(", positionOfRs) + 2;
                int end = resultSetStatements[i].indexOf("\"", start);
                String columnName = resultSetStatements[i].substring(start, end).trim();
                sqlColumnsArr[i] = columnName;
            }
        }
        int count = 0;
        for (int i = 0; i < resultSetStatements.length; i++) {
            if (resultSetStatements[i].contains("ogger")) {
                System.out.println(resultSetStatements[i]);
                continue;
            }
            if (resultSetStatements[i].contains("rs.get")) {
                String sqlColumn = sqlColumnsArr[count].trim();
                int startIndex = resultSetStatements[i].indexOf("rs.get");
                int endIndex = resultSetStatements[i].indexOf(")", startIndex) + 1; // must have all spaces between brackets removed
                String strToBeReplaced = resultSetStatements[i].substring(startIndex, endIndex);
                String strToReplace = variableName + "." + getMethodName(sqlColumn) + "()";
                String toPrint = resultSetStatements[i].replace(strToBeReplaced, strToReplace);
                System.out.println(toPrint);
                count++;
                continue;
            }
            System.out.println(resultSetStatements[i]);
        }
    }

    private static String convertSqlColumnNameToFieldName(String column) {
        String[] split = column.toLowerCase().split("_");
        String finalString = "";
        for (int i = 0; i < split.length; i++) {
            if (i != 0) {
                finalString += split[i].substring(0, 1).toUpperCase();
                finalString += split[i].substring(1);
            } else {
                finalString += split[i];
            }
        }
        return finalString;
    }

    private static void generateXmlResultMapFromSelect() {
        boolean toIncludeM_ = true;
        boolean isAfterM_UpperCase = true;
        String setStatements = "eliteTier.setTierStatus(rs.getString(1));\n" +
                "                eliteTier.setQlfyInd(rs.getString(2));\n" +
                "                eliteTier.setQlfyStartDate(rs.getDate(3));\n" +
                "                eliteTier.setQlfyEndDate(rs.getDate(4));\n" +
                "                eliteTier.setCurMileage(rs.getLong(5));\n" +
                "                eliteTier.setNoYearsQlfd(rs.getInt(6));\n" +
                "                eliteTier.setForceQlfyDate(rs.getDate(7));\n" +
                "                eliteTier.setForceQlfyExtendedDate(rs.getDate(8));\n" +
                "                eliteTier.setNoOfExtendedMonth(rs.getInt(9));\n" +
                "                eliteTier.setOrigExp(rs.getDate(10));\n" +
                "                eliteTier.setQlfyDate(rs.getDate(11));\n" +
                "                eliteTier.setTierExp(rs.getDate(12));\n" +
                "                eliteTier.setTierBonusStartDate(rs.getDate(13));\n" +
                "                eliteTier.setTierBonusEndDate(rs.getDate(14));\n" +
                "                eliteTier.setDisctryTierFlag(rs.getString(15));";
        String columnNames = "TIER_STATUS_IND, QLFY_IND,QLFY_START_DT, QLFY_END_DT,CUR_MILEAGE, NO_YRS_QLFD, FORCE_QLFY_DT,FORCE_QLFY_EXTENDED_DT, NO_OF_EXTENDED_MTH,ORIG_EXP, QLFD_DT,TIER_EXP, TIER_BONUS_AWARD_START_DT,TIER_BONUS_AWARD_END_DT, DISCTRY_TIER_FLG";
        String[] columnsArr = null;

        String[] splitStatements = setStatements.split("\n");
        if (!columnNames.equals("")) {
            columnsArr = columnNames.split(",");
        } else {
            columnsArr = new String[splitStatements.length];
            for (int i = 0; i < splitStatements.length; i++) {
                int positionOfRs = splitStatements[i].indexOf("rs");
                int start = splitStatements[i].indexOf("(", positionOfRs) + 2;
                int end = splitStatements[i].indexOf("\"", start);
                String columnName = splitStatements[i].substring(start, end).trim();
                columnsArr[i] = columnName;
            }
        }
        for (int i = 0; i < splitStatements.length; i++) {
            if (splitStatements[i].contains("set") && splitStatements[i].contains("get")) {
                int startIndex = splitStatements[i].indexOf("set") + 3;
                int endIndex = splitStatements[i].indexOf("(", startIndex);
                String variableName = "";
                if (toIncludeM_) {
                    if (!isAfterM_UpperCase) {
                        variableName = "m_";
                        variableName += splitStatements[i].substring(startIndex, startIndex + 1).toLowerCase();
                        variableName += splitStatements[i].substring(startIndex + 1, endIndex);
                    } else {
                        variableName = "m_" + splitStatements[i].substring(startIndex, endIndex);
                    }
                } else {
                    String firstChar = splitStatements[i].substring(startIndex, startIndex + 1).toLowerCase();
                    variableName = String.format("%s%s", firstChar, splitStatements[i].substring(startIndex + 1, endIndex));
                }

                // startIndex of dataType
                startIndex = splitStatements[i].indexOf("get") + 3;
                endIndex = splitStatements[i].indexOf("(", startIndex);
                String dataTypeOfColumnFromDb = splitStatements[i].substring(startIndex, endIndex);
                String dataTypeInXml = "";
                String dataType = "";
                if (dataTypeOfColumnFromDb.equals("String")) {
                    dataTypeInXml = "VARCHAR";
                } else if (dataTypeOfColumnFromDb.equals("Long") ||
                        dataTypeOfColumnFromDb.equals("Int") ||
                        dataTypeOfColumnFromDb.equals("Double") ||
                        dataTypeOfColumnFromDb.equals("Float") ||
                        dataTypeOfColumnFromDb.equals("BigDecimal")) {
                    dataTypeInXml = "NUMERIC";
                    if (dataTypeOfColumnFromDb.equals("BigDecimal")) {
                        dataType = "java.math.BigDecimal";
                    }
                } else if (dataTypeOfColumnFromDb.equals("Date") || dataTypeOfColumnFromDb.equals("Timestamp")) {
                    dataTypeInXml = "TIMESTAMP";
                    if (dataTypeOfColumnFromDb.equals("Timestamp")) {
                        dataType = "java.sql.Timestamp";
                    }

                } else {
                    System.out.println(i);
                    throw new RuntimeException("Different dataType from those mentioned above");
                }
                String dataTypeToPrint = "";
                if (!dataType.equals("")) {
                    dataTypeToPrint = "javaType=\"" + dataType + "\"";
                }
                System.out.println(String.format("<result column=\"%s\" jdbcType=\"%s\" property=\"%s\" %s/>", columnsArr[i].trim(), dataTypeInXml, variableName, dataTypeToPrint));
            }
        }
    }

    private static void printSetStatementsForTest(String xmlMethodName) {
        System.out.println(String.format("@Test\npublic void %s(){\n\t\t", xmlMethodName));
        String[] sentences = setStatements.split("\n");
        Map<String, Integer> listOfSetterStatements = new HashMap<>();
        Map<String, Integer> listOfClassesToInitialise = new HashMap<>();
        for (int i = 0; i < sentences.length; i++) {

            if (sentences[i].contains("set") && sentences[i].contains("get")) {
                String secondParam = secondParam = sentences[i].split(",")[1].trim();
                int end = 0;
                int numOfGet = countNumOfGet(sentences[i]);
                String finalString = "";
                if (numOfGet > 1) {
                    String[] secondParamSplitOnDot = secondParam.split("\\.");
                    for (int j = 0; j < secondParamSplitOnDot.length; j++) {
                        if (j == 0) {
                            String setterStatement = String.format("%s.%s;",
                                    secondParamSplitOnDot[j], secondParamSplitOnDot[j + 1].replace("get", "set"));
                            if (!listOfSetterStatements.containsKey(setterStatement)) {
                                listOfSetterStatements.put(setterStatement, 1);
                            }
                            continue;
                        }
                        if (j == secondParamSplitOnDot.length - 1) { // if last item
                            break;
                        }
                        int start = secondParamSplitOnDot[j].indexOf("get") + 3;
                        int endIndex = secondParamSplitOnDot[j].indexOf("(");
                        String newClassToInitialise = secondParamSplitOnDot[j].substring(start, endIndex);
                        String classVariable = newClassToInitialise.substring(0, 1).toLowerCase() + newClassToInitialise.substring(1);
                        if (!listOfClassesToInitialise.containsKey(newClassToInitialise)) {
                            listOfClassesToInitialise.put(newClassToInitialise, 1);
                            System.out.println(String.format("%s %s = new %s()", newClassToInitialise,
                                    classVariable, newClassToInitialise));
                        }
                        String replacementStr = secondParamSplitOnDot[j + 1].replace("get", "set");
                        replacementStr = replacementStr.substring(0, replacementStr.length() - 2);
                        System.out.println(String.format("%s.%s;", classVariable, replacementStr));
                    }
                } else {
                    end = secondParam.indexOf(")") + 1;
                    secondParam = secondParam.replace("get", "set");
                    secondParam = secondParam.substring(0, secondParam.length() - 2);
                    System.out.println(secondParam + ";");
                }

            } else {
                String secondParam = sentences[i].split(",")[1].trim();
                secondParam = secondParam.substring(0, secondParam.indexOf(")"));
                int dataTypeStartIndex = sentences[i].indexOf("set") + 3;
                int dataTypeEndIndex = sentences[i].indexOf("(");
                String dataType = sentences[i].substring(dataTypeStartIndex, dataTypeEndIndex);
                System.out.println(String.format("%s %s = ;", dataType, secondParam));
            }
        }

        for (String key : listOfSetterStatements.keySet()) {
            System.out.println(key);
        }
        System.out.println("\n}");
    }

    private static int countNumOfGet(String sentence) {
        String temp = sentence;
        int start = 0;
        int count = 0;
        while (temp.indexOf("get") != -1) {
            start = temp.indexOf("get") + 3;
            temp = temp.substring(start);
            count++;
        }
        return count;
    }

    public void printSelectXml() {
        printSelectXmlWithFrom("PROMO_CD, ARULE_HDR_ID, ITIN_ID, ITIN_TYPE, ORG_CD, DES_CD, TKT_PERIOD_FROM, TKT_PERIOD_TO, REMARKS, VALID_FLG, PRT_CD FROM PRM_WZD_ITIN_DTLS",
                "prmWzdItinDtl", "PromoWzdItinInfo", "PrmWzdItinDtls", "getWzdItinInfo");
    }

    private static String convertQuestionMrkToParam(String s, String sqlOperation, String name, String returnType, boolean toIncludeM_) { // change the params values only or the sql
        String finalString = String.format("<%s id=\"%s\" ", sqlOperation, name);
        if (sqlOperation.equals("select")) {
            if (returnType.equalsIgnoreCase("int") || returnType.contains("Integer")) {
                finalString += "resultType=\"java.lang.Integer\">\n";
            } else if (returnType.contains("String")) {
                finalString += "resultType=\"java.lang.String\">\n";
            } else if (returnType.contains("Long") || returnType.contains("long")) {
                finalString += "resultType=\"java.lang.Long\">\n";
            } else if (returnType.contains("Double") || returnType.contains("double")) {
                finalString += "resultType=\"java.lang.Double\">\n";
            } else if (returnType.contains("Float") || returnType.contains("float")) {
                finalString += "resultType=\"java.lang.Float\">\n";
            } else if (returnType.contains("Date") || returnType.contains("date")) {
                finalString += "resultType=\"java.util.Date\">\n";
            } else if (returnType.contains("GeneralSqlObject")) {
                finalString += "resultMap=\"" + name + "\">\n";
            } else {
                finalString += "resultMap=\"BaseResultMap\">\n";
            }

        } else {
            finalString += ">\n";
        }
        List<Variable> paramsList = null;
        paramsList = getParamsFromSetStatements(toIncludeM_); // comment if not required


        int startIndex = 0;
        for (Variable variable : paramsList) {
            try {
                if (startIndex == 0) {
                    startIndex = s.indexOf("?");
                    finalString += s.substring(0, startIndex);
                }
                if(variable.name.contains("tableName") || variable.name.contains("transTableName")){
                    finalString += String.format("${%s}",variable.name);
                }else {
                    finalString += String.format("#{%s,jdbcType=%s}", variable.name, variable.type);
                }
                startIndex++;
                int endIndex = s.indexOf("?", startIndex);
                if (endIndex == -1) {
                    finalString += s.substring(startIndex, s.length());
                    break;
                }
                finalString += s.substring(startIndex, endIndex);
                startIndex = endIndex;
            } catch (Exception e) {
                System.out.println(variable.name);
            }
        }
        finalString += String.format("\n</%s>", sqlOperation);
        System.out.println(finalString);
        return finalString;
    }

    private static List<Variable> getParamsFromSetStatements(boolean includeM_) {
        boolean toIncludeGetMethod = true;
        boolean variableStartWithLowerCase = true;
        String[] sentences = setStatements.split("\n");
        List<Variable> params = new ArrayList<>();
        List<String> paramsWithoutGetMethod = new ArrayList<>();
        for (int i = 0; i < sentences.length; i++) {
            String dataTypeInXml = getDataType(sentences[i]);
            Variable variable = new Variable();
            variable.type = dataTypeInXml;
            if (sentences[i].contains("set")) {
                if (!sentences[i].contains("get")) {
                    int start = sentences[i].indexOf(",") + 1;
                    int end = sentences[i].indexOf(")", start);
                    variable.name = sentences[i].substring(start, end).trim();
                    params.add(variable);
                    paramsWithoutGetMethod.add(sentences[i].substring(start, end).trim());
                    continue;
                }
                if (toIncludeGetMethod) {
                    int start = sentences[i].indexOf(",") + 1;
                    int end = sentences[i].indexOf("(", start);
                    String cleanedAndReplacedWithM_ = "";
                    if (includeM_) {
                        cleanedAndReplacedWithM_ = sentences[i].substring(start, end).trim().replace("get", "m_");
                        // might want to add logic to change the variable to lowercase after the m_
                    } else {
                        cleanedAndReplacedWithM_ = sentences[i].substring(start, end).trim().replace("get", "");
                        String[] variableNameSplit = cleanedAndReplacedWithM_.split("\\.");
                        String firstChar = variableNameSplit[1].substring(0, 1).toLowerCase();
                        cleanedAndReplacedWithM_ = variableNameSplit[0] + "." + firstChar + variableNameSplit[1].substring(1);
                    }
                    while (sentences[i].substring(end).contains("get")) {
                        start = sentences[i].indexOf("get", end);
                        end = sentences[i].indexOf("(", start);
                        cleanedAndReplacedWithM_ += String.format(".%s", sentences[i].substring(start, end).trim().replace("get", "m_"));
                    }
                    variable.name = cleanedAndReplacedWithM_;
                    params.add(variable);
                } else {
                    int startIdx = sentences[i].indexOf("get") + 3;
                    int endIdx = sentences[i].indexOf("(", startIdx);
                    String firstLetter = sentences[i].substring(startIdx, startIdx + 1).toLowerCase();
                    String param = firstLetter + sentences[i].substring(startIdx + 1, endIdx);
                    variable.name = param;
                    params.add(variable);
                }
            }
        }
        System.out.println("Number of set methods : " + params.size());
        for (String param : paramsWithoutGetMethod) {
            System.out.print(param + ", ");
        }
        System.out.println("\n");
        return params;
    }

    private static String getDataType(String sentence) {
        int startIndex = sentence.indexOf("set") + 3;
        int endIndex = sentence.indexOf("(");
        String dataTypeInString = sentence.substring(startIndex, endIndex);
        String dataTypeInXml = "";
        if (dataTypeInString.equals("String")) {
            dataTypeInXml = "VARCHAR";
        } else if (dataTypeInString.trim().equals("Long") ||
                dataTypeInString.trim().equals("Int") ||
                dataTypeInString.trim().equals("Double") ||
                dataTypeInString.equals("Float")) {
            dataTypeInXml = "NUMERIC";
        } else if (dataTypeInString.equals("Date") || dataTypeInString.equals("Timestamp")) {
            dataTypeInXml = "TIMESTAMP";
        } else {
            System.out.println(dataTypeInString);
            System.out.println("Error for " + sentence);
            throw new RuntimeException("Different dataType from those mentioned above");
        }
        return dataTypeInXml;
    }

    private static void printSelectXmlWithFrom(String query, String variableName, String voClassName, String sqlClassName, String mapperMethodName) {
        // find the columns selected
        String splitFrom = "";
        if (query.contains(" FROM")) {
            splitFrom = " FROM";
        } else if (query.contains(" From")) {
            splitFrom = " From";
        } else {
            splitFrom = " from";
        }
        String columnRaw = query.split(splitFrom)[0];
        String[] columns = columnRaw.split(",");
        // check for 'as' string
        for (int i = 0; i < columns.length; i++) {
            if (columns[i].contains("to_char")) {
                columns[i] = "";
            }
            if (columns[i].contains("nvl")) {
                columns[i] = "";
            }
            if (columns[i].contains("as")) {
                columns[i] = columns[i].split("as")[1];
            }
        }
        for (int i = 1; i <= columns.length; i++) {
            if (columns[i - 1].equals("")) continue;
            System.out.println(String.format("%d : %s", i, columns[i - 1]));
        }

        // print setter method
        System.out.println(String.format("public %s set%s(%s %s){\n\t%s %s = new %s();\n\treturn %s;\n}",
                voClassName, voClassName, sqlClassName, sqlClassName.substring(0, 1).toLowerCase() + sqlClassName.substring(1),
                voClassName, voClassName.substring(0, 1).toLowerCase() + voClassName.substring(1), voClassName,
                voClassName.substring(0, 1).toLowerCase() + voClassName.substring(1)));

        // print the mapper statement
        System.out.println(String.format("List<%s> %ss = %sMapper.%s();",
                sqlClassName, sqlClassName.substring(0, 1).toLowerCase() + sqlClassName.substring(1),
                sqlClassName.substring(0, 1).toLowerCase() + sqlClassName.substring(1), mapperMethodName));

        // print get method names
        for (int i = 0; i < columns.length; i++) {
            if (columns[i].equals("")) continue;
            System.out.println(String.format("%s : %s.%s()", columns[i], variableName, getMethodName(columns[i])));
        }
        for (String column : columns) {
            if (column.equals("")) continue;
            System.out.println("<result column=\"" + column.trim() + "\" />");
        }
    }

    private static String getMethodName(String field) {
        field = field.trim();
        String methodName = "";
        methodName += "get";
        String[] splitName = field.toLowerCase().split("_");
        for (int i = 0; i < splitName.length; i++) {
            methodName += splitName[i].substring(0, 1).toUpperCase() + splitName[i].substring(1);
        }
        return methodName;
    }

    public static void returnGetMethodNames() {
        String name = "gInternalID = rs.getLong(\"GUARDIAN_INT_ID\");\n" +
                "                    relationShipCd = rs.getString(\"RELN_TO_MBR\");\n" +
                "                    relationShip = rs.getString(\"RELN_TO_MBR_TXT\");\n" +
                "                    seqNo = rs.getInt(\"SEQ_NO\");\n" +
                "                    guardianPrgCd = rs.getString(\"GUARDIAN_PRG_CD\");\n" +
                "                    guardianIntID = rs.getString(\"GUARDIAN_INT_ID\");\n" +
                "                    lastChangeDate = rs.getTimestamp(\"LCHG_DT\");";
        String[] sentences = name.split("\n");
        String[] methodNames = returnGetMethodNames(sentences, "cusGuardianInfo");
        for (String s : sentences) {
            System.out.println(s);
        }
    }

    private static String[] returnGetMethodNames(String[] sentences, String variableName) {
        String[] completedNames = new String[sentences.length];
        for (int i = 0; i < sentences.length; i++) {
            try {
                int startIndex = sentences[i].indexOf("rs.get");
                int endIndex = sentences[i].indexOf("\")") + 2;
                if (startIndex == -1 || endIndex == -1) continue;
                String toRemove = sentences[i].substring(startIndex, endIndex);
                String columnName = sentences[i].split("\"")[1];
                String[] columnNamesSplit = columnName.toLowerCase().split("_");
                String completedName = variableName + ".get";
                for (int j = 0; j < columnNamesSplit.length; j++) {
                    completedName += columnNamesSplit[j].substring(0, 1).toUpperCase() + columnNamesSplit[j].substring(1);
                }
                sentences[i] = sentences[i].replace(toRemove, completedName + "()");
            } catch (Exception e) {
                completedNames[i] = "";
            }
        }
        return completedNames;
    }

    public static void cleanseVariableForInterfaceMethodNameCreation() {
        String s = "";
        String[] sentences = s.split("\n");
        List<String> cleansedSentences = new ArrayList<>();
        for (String line : sentences) {
            if (line.contains(")")) {
                // cleanse this line
                String trimmedLine = line.trim();
                int endIndex = trimmedLine.indexOf(")");
                System.out.println(trimmedLine.substring(0, endIndex + 1) + ";");
                cleansedSentences.add(trimmedLine.substring(0, endIndex + 1));
            }
        }
    }

    // change the sql and setSqlExpression variables
    public static void convertMybatisCodeToMethodParams(String sql, String returnType) { // used to convert mybatis xml code to java mapper class methods
        String methodName = getMybatisMethodName(sql);
        // find the # symbol to get the param name
        List<String> paramName = new ArrayList();
        int startIndex = 0;
        for (int i = 0; i < sql.length(); i++) {
            if (sql.substring(i, i + 1).equals("{")) {
                startIndex = i + 1;
            }
            if (sql.substring(i, i + 1).equals("}")) {
                String param = sql.substring(startIndex, i);
                if(param.contains(",")){
                    param = param.substring(0, param.indexOf(","));
                }
                paramName.add(param);
            }
        }
        // place the setString statements here
        String setSqlExpressions = setStatements;
        List<String> dataTypeList = new ArrayList<>();
        String[] sentences = new String[0];
        if (!setSqlExpressions.equals("")) {
            sentences = setSqlExpressions.split("\n");
        }

        for (String line : sentences) {
            if (line.contains("if(") || line.contains("if ("))
                continue;
            int start = line.indexOf("set") + 3;
            int end = line.indexOf("(");
            String type = line.substring(start, end);
            dataTypeList.add(type);
        }
        String[] extraAddition = {};
        for (String s : extraAddition) {
            paramName.add(s);
        }
        // print the method name
        StringBuffer buffer = new StringBuffer();
        buffer.append(String.format("%s %s(", returnType, methodName));
        for (int i = 0; i < dataTypeList.size(); i++) {
            buffer.append(String.format("@Param(\"%s\")%s %s", paramName.get(i), dataTypeList.get(i), paramName.get(i)));
            if (i < dataTypeList.size() - 1) {
                buffer.append(",\n");
            } else {
                buffer.append(");");
            }
        }
        System.out.println(buffer.toString());
    }

    private static String getMybatisMethodName(String s) {
        int startIndex = s.indexOf("id=");
        startIndex = startIndex + 4;
        int endIndex = s.indexOf("\"", startIndex + 1);
        String name = s.substring(startIndex, endIndex);
        return name;
    }

    public static void generateMethodsFromSetAndGetQueryClass() {
        String s = "query.bind(new Long(internalID));\n" +
                "            getQuery(CustomerTypeData.class, \"getCustomerTypeChangeHistory\", query);";
        String[] splitStr = s.split("\n");
        String setAuditId = "";
        List<String> params = new ArrayList<>();
        String className = "";
        String methodName = "";
        // loop through
        for (String sentence : splitStr) {
            if (sentence.contains("bind")) {
                int startIndex = sentence.indexOf("(");
                int endIndex = sentence.length() - 2;
                String paramName = sentence.substring(startIndex + 1, endIndex);
                params.add(paramName);
            }
            if (sentence.contains("setAuditId")) {
                int startIndex = sentence.indexOf("(");
                int endIndex = sentence.length() - 2;
                setAuditId = String.format("sqlCommonService.setAuditId(%s);", sentence.substring(startIndex + 1, endIndex));
            }
            if (sentence.contains("load") || sentence.contains("insert") || sentence.contains("update") || sentence.contains("setQuery") || sentence.contains("setReturnQuery")) {
                if (setAuditId.equals("")) {
                    setAuditId = String.format("sqlCommonService.setAuditId(\"\");");
                }
            }
            if (sentence.contains(".class")) { // make sure the database.setQuery method call is in one line
                int startIndex = sentence.indexOf("(");
                int endIndex = sentence.indexOf(".class");
                className = sentence.substring(startIndex + 1, endIndex);
                className = className.substring(0, 1).toLowerCase() + className.substring(1);
                startIndex = sentence.indexOf("\"");
                endIndex = sentence.indexOf("\"", startIndex + 1);
                methodName = sentence.substring(startIndex + 1, endIndex);
            }
        }
        System.out.println(setAuditId);
        String finalString = String.format("%s.%s(", className, methodName);
        for (int i = 0; i < params.size(); i++) {
            finalString += params.get(i);
            if (i != params.size() - 1) {
                finalString += ",";
            }
        }
        finalString += ");";
        System.out.println(finalString);

    }

    public static String generateSqlStatementsFromString() { // change the variable s only
        String s = "buffer.append(\" UPDATE CUS_PERS SET SPL_CUS_TYPE = ?, LCHG_USER_ID = ?, LCHG_DT = sysdate\");\n" +
                "            buffer.append(\" WHERE INT_ID = ?\");";
        printNumberOfQuestionMark(s);
        String[] splitStr = s.split("\n");
        String finalString = "";
        for (int i = 0; i < splitStr.length; i++) { // only use with buffer.append
            if (splitStr[i].contains("if(") || splitStr[i].contains("if (")) {
                boolean insideChooseBlock = false;
                if ((i + 2) <= splitStr.length && splitStr[i + 2].contains("else")) {
                    insideChooseBlock = true;
                    String temporary = "<choose>\n";
                    while (splitStr[i].contains("if") || splitStr[i].contains("else")) { // if this loop does not continue, then the if else has finished
                        if (splitStr[i].contains("if")) {
                            temporary += "<when test='";
                            temporary = getIfStatement(splitStr[i].indexOf("("), splitStr[i], temporary);
                            temporary = temporary.trim();
                            temporary = temporary.substring(0, temporary.length() - 1);
                            temporary += "'>\n";
                            i++;
                            temporary += getSqlStatementsFromAppend(splitStr[i]) + "\n";
                            temporary += "</when>\n";
                        } else {
                            temporary += "<otherwise>\n";
                            i++;
                            temporary += getSqlStatementsFromAppend(splitStr[i]) + "\n";
                            temporary += "</otherwise>\n";
                        }
                        i++;
                        if (i == splitStr.length) {
                            break;
                        }
                    }
                    temporary += "</choose>\n";
                    finalString += temporary;
                    continue;
                }
                String temp = "<if test='";
                int startIndex = splitStr[i].indexOf("(");
                temp = getIfStatement(startIndex, splitStr[i], temp);
                temp = temp.trim();
                temp = temp.substring(0, temp.length() - 1);
                finalString += temp + "'>\n";
                i++;
                finalString += getSqlStatementsFromAppend(splitStr[i]) + "\n";
                finalString += "</if>\n";
            } else if (splitStr[i].contains(".append")) {
                finalString += getSqlStatementsFromAppend(splitStr[i]);
            }
        }
        return finalString;
    }

    private static void printNumberOfQuestionMark(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.substring(i, i + 1).equals("?")) {
                count++;
            }
        }
        System.out.println("Number of question mark : " + count);
    }

    private static String getIfStatement(int startIndex, String s, String temp) {
        int count = 1;
        int start = startIndex + 1;
        while (count > 0) {
            String charac = s.substring(start, start + 1);
            if (charac.equals("(")) {
                temp += "(";
                count++;
            } else if (charac.equals(")")) {
                temp += ")";
                count--;
            } else if (charac.equals("{")) {
                break;
            } else {
                temp += charac;
            }
            start++;
        }
        return temp;
    }

    private static String getSqlStatementsFromAppend(String s) {
        if (!s.contains("\"")) {
            return s;
        }
        int startIndex = s.indexOf("\"");
        int endIndex = s.indexOf("\"", startIndex + 1);
        String temp = s.substring(startIndex + 1, endIndex).trim();
        temp += " ";
        return temp;
    }

    // select a, b, c --> <result column="a" property=""/>
    public static void populateSqlSelectColumnsToXml() {
        int choice = 3; // 0 --> add "as" expression  1--> add value after . eg. add "as REF_ID" from "pf.REF_ID" column
        // choice = 3 --> dont add anything just use the column as it is
        String columnStr = "TIER_STATUS_IND, QLFY_IND,QLFY_START_DT, QLFY_END_DT,CUR_MILEAGE, NO_YRS_QLFD, FORCE_QLFY_DT,FORCE_QLFY_EXTENDED_DT, NO_OF_EXTENDED_MTH,ORIG_EXP, QLFD_DT,TIER_EXP, TIER_BONUS_AWARD_START_DT,TIER_BONUS_AWARD_END_DT, DISCTRY_TIER_FLG";
        String[] columns = columnStr.split(",");
        String finalSelectStatement = "";
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < columns.length; i++) {
            String property = "column" + String.valueOf(i + 1);
            String row = "";
            finalSelectStatement += columns[i].trim();
            if (choice == 0) {
                int index = 0;
                while (!columns[i].substring(index, index + 1).equals(".")) {
                    index++;
                }
                String columnNameToUse = columns[i].substring(index + 1);
                map.put(columnNameToUse, property);
                row = String.format("<result column=\"%s\" jdbcType=\"VARCHAR\" property=\"%s\"/>", property, property);
                finalSelectStatement += " as " + property;
                if (i != columns.length - 1) {
                    finalSelectStatement += ", ";
                }
            } else if (choice == 1) {
                int index = 0;
                while (!columns[i].substring(index, index + 1).equals(".")) {
                    index++;
                }
                String columnNameToUse = columns[i].substring(index + 1);
                finalSelectStatement += " as " + columnNameToUse;
                if (i != columns.length - 1) {
                    finalSelectStatement += ", ";
                }
                row = String.format("<result column=\"%s\" jdbcType=\"VARCHAR\" property=\"%s\"/>", columnNameToUse, columnNameToUse);
            } else {
                row = String.format("<result column=\"%s\" jdbcType=\"VARCHAR\" property=\"%s\"/>", columns[i].trim(), property);
            }

            System.out.println(row);
        }
        System.out.println(map.size() + "\n");
        for (String key : map.keySet()) {
            System.out.println(String.format("%s --- %s", key, map.get(key)));
        }

        System.out.println(finalSelectStatement);
    }
}

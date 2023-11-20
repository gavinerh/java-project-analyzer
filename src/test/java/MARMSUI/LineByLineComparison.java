package MARMSUI;

public class LineByLineComparison {
    public static void main(String[] args) {
        String statement1 = "{\n" +
                "            \"batchDate\": \"2022-09-11T16:00:00.000+00:00\",\n" +
                "            \"desc2\": \"Extension of miles via SAA\",\n" +
                "            \"batchId\": \"880912\",\n" +
                "            \"feesTransXrefId\": \"F2209120818\",\n" +
                "            \"refundFlg\": \"N\",\n" +
                "            \"suppressFlg\": \"N\",\n" +
                "            \"type\": \"MISC\",\n" +
                "            \"trxn\": \"FD\",\n" +
                "            \"participant\": \"SQKMS\",\n" +
                "            \"date\": \"2022-09-11T16:00:00.000+00:00\",\n" +
                "            \"netMiles\": 0,\n" +
                "            \"promotionMiles\": 0,\n" +
                "            \"feeCode\": \"EXTO\",\n" +
                "            \"tierIndex\": \"S\"\n" +
                "        }," +
                "{\n" +
                "            \"batchDate\": \"2021-03-03T16:00:00.000+00:00\",\n" +
                "            \"desc2\": \"Extension of miles via SAA\",\n" +
                "            \"batchId\": \"880304\",\n" +
                "            \"feesTransXrefId\": \"F2103047668\",\n" +
                "            \"remarks\": \"dsfdsf\",\n" +
                "            \"supervisorId\": \"uat\",\n" +
                "            \"refundFlg\": \"N\",\n" +
                "            \"suppressFlg\": \"N\",\n" +
                "            \"type\": \"MISC\",\n" +
                "            \"trxn\": \"FD\",\n" +
                "            \"participant\": \"SQKMS\",\n" +
                "            \"date\": \"2021-03-03T16:00:00.000+00:00\",\n" +
                "            \"netMiles\": 0,\n" +
                "            \"promotionMiles\": 0,\n" +
                "            \"feeCode\": \"EXTO\",\n" +
                "            \"tierIndex\": \"S\"\n" +
                "        }," +
                "{\n" +
                "            \"batchDate\": \"2021-03-03T16:00:00.000+00:00\",\n" +
                "            \"desc2\": \"Extension of miles via SAA\",\n" +
                "            \"batchId\": \"880304\",\n" +
                "            \"ptsAwded\": 1200,\n" +
                "            \"feesTransXrefId\": \"F2103047665\",\n" +
                "            \"remarks\": \"dsfgsd\",\n" +
                "            \"supervisorId\": \"uat\",\n" +
                "            \"refundFlg\": \"N\",\n" +
                "            \"suppressFlg\": \"N\",\n" +
                "            \"type\": \"MISC\",\n" +
                "            \"trxn\": \"FD\",\n" +
                "            \"participant\": \"SQKMS\",\n" +
                "            \"date\": \"2021-03-03T16:00:00.000+00:00\",\n" +
                "            \"netMiles\": 0,\n" +
                "            \"promotionMiles\": 0,\n" +
                "            \"feeCode\": \"EXTO\",\n" +
                "            \"tierIndex\": \"S\"\n" +
                "        }," +
                "{\n" +
                "            \"batchDate\": \"2021-03-03T16:00:00.000+00:00\",\n" +
                "            \"desc2\": \"Extension of miles via SAA\",\n" +
                "            \"batchId\": \"880304\",\n" +
                "            \"ptsAwded\": 1200,\n" +
                "            \"feesTransXrefId\": \"F2103047665\",\n" +
                "            \"remarks\": \"dsfgsd\",\n" +
                "            \"supervisorId\": \"uat\",\n" +
                "            \"refundFlg\": \"N\",\n" +
                "            \"suppressFlg\": \"N\",\n" +
                "            \"type\": \"MISC\",\n" +
                "            \"trxn\": \"FD\",\n" +
                "            \"participant\": \"SQKMS\",\n" +
                "            \"date\": \"2021-03-03T16:00:00.000+00:00\",\n" +
                "            \"netMiles\": 0,\n" +
                "            \"promotionMiles\": 0,\n" +
                "            \"feeCode\": \"EXTO\",\n" +
                "            \"tierIndex\": \"S\"\n" +
                "        }," +
                "{\n" +
                "            \"batchDate\": \"2021-03-03T16:00:00.000+00:00\",\n" +
                "            \"desc2\": \"Extension of miles via SAA\",\n" +
                "            \"batchId\": \"880304\",\n" +
                "            \"feesTransXrefId\": \"F2103047666\",\n" +
                "            \"remarks\": \"dsfsdf\",\n" +
                "            \"supervisorId\": \"uat\",\n" +
                "            \"refundFlg\": \"N\",\n" +
                "            \"suppressFlg\": \"N\",\n" +
                "            \"type\": \"MISC\",\n" +
                "            \"trxn\": \"FD\",\n" +
                "            \"participant\": \"SQKMS\",\n" +
                "            \"date\": \"2021-03-03T16:00:00.000+00:00\",\n" +
                "            \"netMiles\": 0,\n" +
                "            \"promotionMiles\": 0,\n" +
                "            \"feeCode\": \"EXTO\",\n" +
                "            \"tierIndex\": \"S\"\n" +
                "        }," +
                "{\n" +
                "            \"batchDate\": \"2021-03-03T16:00:00.000+00:00\",\n" +
                "            \"desc2\": \"Extension of miles via SAA\",\n" +
                "            \"batchId\": \"880304\",\n" +
                "            \"feesTransXrefId\": \"F2103047670\",\n" +
                "            \"remarks\": \"sdfdsf\",\n" +
                "            \"supervisorId\": \"uat\",\n" +
                "            \"waivedPts\": 1200,\n" +
                "            \"refundFlg\": \"N\",\n" +
                "            \"suppressFlg\": \"N\",\n" +
                "            \"type\": \"MISC\",\n" +
                "            \"trxn\": \"FD\",\n" +
                "            \"participant\": \"SQKMS\",\n" +
                "            \"date\": \"2021-03-03T16:00:00.000+00:00\",\n" +
                "            \"netMiles\": 0,\n" +
                "            \"promotionMiles\": 0,\n" +
                "            \"feeCode\": \"EXTO\",\n" +
                "            \"tierIndex\": \"S\"\n" +
                "        }," +
                "{\n" +
                "            \"batchDate\": \"2021-03-03T16:00:00.000+00:00\",\n" +
                "            \"desc2\": \"Extension of miles via SAA\",\n" +
                "            \"batchId\": \"880304\",\n" +
                "            \"feesTransXrefId\": \"F2103047667\",\n" +
                "            \"supervisorId\": \"uat\",\n" +
                "            \"refundFlg\": \"N\",\n" +
                "            \"suppressFlg\": \"N\",\n" +
                "            \"type\": \"MISC\",\n" +
                "            \"trxn\": \"FD\",\n" +
                "            \"participant\": \"SQKMS\",\n" +
                "            \"date\": \"2021-03-03T16:00:00.000+00:00\",\n" +
                "            \"netMiles\": 0,\n" +
                "            \"promotionMiles\": 0,\n" +
                "            \"feeCode\": \"EXTO\",\n" +
                "            \"tierIndex\": \"S\"\n" +
                "        }," +
                "{\n" +
                "            \"batchDate\": \"2021-03-03T16:00:00.000+00:00\",\n" +
                "            \"desc2\": \"Extension of miles via SAA\",\n" +
                "            \"batchId\": \"880304\",\n" +
                "            \"feesTransXrefId\": \"F2103047669\",\n" +
                "            \"supervisorId\": \"uat\",\n" +
                "            \"refundFlg\": \"N\",\n" +
                "            \"suppressFlg\": \"N\",\n" +
                "            \"type\": \"MISC\",\n" +
                "            \"trxn\": \"FD\",\n" +
                "            \"participant\": \"SQKMS\",\n" +
                "            \"date\": \"2021-03-03T16:00:00.000+00:00\",\n" +
                "            \"netMiles\": 0,\n" +
                "            \"promotionMiles\": 0,\n" +
                "            \"feeCode\": \"EXTO\",\n" +
                "            \"tierIndex\": \"S\"\n" +
                "        }," +
                "{\n" +
                "            \"batchDate\": \"2019-08-01T16:00:00.000+00:00\",\n" +
                "            \"desc2\": \"Extension of miles via SAA\",\n" +
                "            \"batchId\": \"880802\",\n" +
                "            \"ptsAwded\": 1200,\n" +
                "            \"feesTransXrefId\": \"F1908025266\",\n" +
                "            \"remarks\": \"adfasd\",\n" +
                "            \"refundFlg\": \"N\",\n" +
                "            \"suppressFlg\": \"N\",\n" +
                "            \"type\": \"MISC\",\n" +
                "            \"trxn\": \"FD\",\n" +
                "            \"participant\": \"SQKMS\",\n" +
                "            \"date\": \"2019-08-01T16:00:00.000+00:00\",\n" +
                "            \"netMiles\": 0,\n" +
                "            \"promotionMiles\": 0,\n" +
                "            \"feeCode\": \"EXTO\",\n" +
                "            \"tierIndex\": \"T\"\n" +
                "        }," +
                "{\n" +
                "            \"batchDate\": \"2019-08-01T16:00:00.000+00:00\",\n" +
                "            \"desc2\": \"Extension of miles via SAA\",\n" +
                "            \"batchId\": \"880802\",\n" +
                "            \"ptsAwded\": 1200,\n" +
                "            \"feesTransXrefId\": \"F1908025265\",\n" +
                "            \"remarks\": \"asfds\",\n" +
                "            \"refundFlg\": \"N\",\n" +
                "            \"suppressFlg\": \"N\",\n" +
                "            \"type\": \"MISC\",\n" +
                "            \"trxn\": \"FD\",\n" +
                "            \"participant\": \"SQKMS\",\n" +
                "            \"date\": \"2019-08-01T16:00:00.000+00:00\",\n" +
                "            \"netMiles\": 0,\n" +
                "            \"promotionMiles\": 0,\n" +
                "            \"feeCode\": \"EXTO\",\n" +
                "            \"tierIndex\": \"T\"\n" +
                "        },";
        String statement2 = "";
        String[] line1Arr = statement1.split("\n");
        String[] line2Arr = statement2.split("\n");
        int count1 = 0;
        int count2 = 0;
        int size = line2Arr.length >= line1Arr.length ? line2Arr.length: line1Arr.length;
        for(int i=0; i<size; i++) {
            if(i == 0) {
                count1 = i; count2 = i;
            }
            String l1 = line1Arr[count1].trim();
            String l2 = line2Arr[count2].trim();
            if(!l1.equals(l2)) {
                System.out.println("Different at line " + i);
                System.out.println("Line 1: " + l1);
                System.out.println("Line 2: " + l2 + "\n");
                count2--;
            }
            count1++; count2++;
        }
    }
}

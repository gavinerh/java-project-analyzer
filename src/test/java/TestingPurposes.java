import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.Set;

public class TestingPurposes {
    public static void main(String[] args) throws Exception {
        String kfs = "8998067329,8998067333,8998067344,8998067355,8000000119,8806286115,8000070027,8000015637,8102969246,8878616205,8204208728,8001283245,8530030194,8799465304,8881078256,8085498974,8001120539,8000030149,8003159223,8370018023,8218618917,8086853415,8230159025,8086697989,8004169238,8004464885,8803682546,8806615487,8104588895,8837677195,8236256423,8005412195,8095352325,8129561557,8000133528,8793399664,8104784976,8620048488,8812971826,8839828608,8099522737,8807749196,8998067086,8998067097,8998067108,8168934704,8871212327,8805496957,8818549835,8812849536,8805506164,8130039646,8796973223,8140815936,8874207439,8813787326,8169059617,8807441616,8809979908,8809543377,8002200105,8098831535,8819072418,8818871949,8805803638,8793303657,8810099597,8000449405,8002603176,8104779354,8100048658,8004195617,8040168105,8000067334,8818315335,8987000436,8814193897,8440030888,8086880829,8240003707,8260007187,8240017637,8210029604,8991454923,8991454934,8991455317,8991454945,8991455306,8991455247,8998067226,8991454956,8991455236,8991455225,8998067237,8991455214,8991455284,8814597554,8998067027,8998067016,8991454967,8991455203,8991455199,8991455188,8991455177,8991455166,8991455155,8991454978,8991455144,8991455133,8991455129,8991455085,8991455118,8991455269,8991455273,8998067038,8998067049,8991455074,8991455096,8991455107,8991454643,8991454654,8991454665,8991454676,8991454687,8991454698,8991454709,8991454713,8991454724,8991454735,8991454746,8991454757,8991454768,8991454783,8991454794,8991454816,8991454827,8991454853,8991454864,8991454875,8991454886,8991454993,8991455004,8991455015,8991455026,8991455398,8991455409,8991455413,8998067123,8991455424,8991455435,8991455446,8991455457,8991455549,8991455597,8991455608,8991455553,8991455586,8991455575,8991455619,8991455564,8991455623,8991455634,8991455328,8991455339,8991455343,8991455354,8991455365,8991455376,8991455387,8820010024,8896657025,8808610719,8791286699,8794076689,8797993705,8102413553,8861763878,8793009333,8998067609,8998067613,8998067624,8998067635,8998067646,8998067657,8998067668,8998067679,8991458305,8991458338,8991458839,8991458817,8998067716,8998067727,8991458806,8998067738,8991458795,8991458784,8991458773,8991458769,8991458758,8991458747,8860696986,8860905678,8099972999,8998067683,8998067694,8991458036,8991458047,8991458069,8991458073,8991458084,8991458106,8991458128,8991458139,8991458143,8991458154,8991458165,8991458187,8842879643,8991458198,8991458209,8991458213,8991458235,8991458246,8991458257,8991458268,8991458279,8991458294,8991458316,8987659313,8998051163,8858010117,8951846635,8831111858,8991262633,8991211975,8110477446,8866753139,8105083073,8991458736,8991458725,8991458714,8991458703,8991458677,8991458666,8991458655,8831574853,8796422577,8865386448,8139616648,8824664184,8817195567,8082868348,8099683295,8991458574,8991458644,8999010067,8157966835,8841171035,8857266927,8233935978,8002413137,8004532608,8991459344,8991459469,8991459554,8991459576,8991459683,8991459727,8991460044,8991460099,8991460136,8991459458,8991459318,8991460018,8991460055,8991460125,8991460173,8991459484,8991459506,8991459366,8991459333,8991459705,8991459259,8991459248,8991459285,8991459355,8991459414,8991459495,8991459624,8991459764,8991459775,8991459716,8991460239,8991459937,8991459263,8991459679,8991459753,8991459819,8991459878,8991459893,8991459904,8991459963,8991459974,8991460169,8991460243,8991459528,8991459598,8991459388,8991459403,8991459447,8991459543,8991459926,8991460147,8991460228,8991459274,8991459399,8991460158,8991459845,8991458548,8003126415,8232462397,8127166098,8340018738,8792540156,8802892725,8510007147,8310011919,8000990446,8530001479,8001660324,8002213335,8003037957,8280030407,8520001349,8102377234,8102290828,8832026408,8102688863,8805307139,8795630914,8105398666,8801732405,8933506764,8007198923,8007200043,8007200054,8007200098,8007211394,8007211453,8082011559,8007215207,8999018585,8999018397,8094808053,8813816063,8987010365,8810005329,8810199067,8794100515,8932222739,8799033765,8863696534,8881469788,8114381313,8114509818,8807486519,8115134988,8808519885,8807696243,8005898496,8113064086,8806967683,8808349619,8005503873,8102530438,8108744327,8112960766,8235915976,8236759078,8233617316,8232843499,8802077026,8791421103,8201262863,8115205408,8118452664,8204435005,8084703914,8660015559,8987000388,8290027737,8130589227,8003763006,8114423578,8153206964,8818762226,8998067823,8998067856,8998067889,8998067904,8998067926,8998067959,8998067974,8998068018,8991461665,8991461709,8991461713,8991461735,8998068335,8998068136,8998068158,8998068173,8998068195,8998068217,8998068239,8998068254,8998068276,8998068298,8998068313,8086192243,8087908525,8125454668,8099201485,8540005264,8998067845,8998067867,8998067893,8998067915,8998067937,8998067963,8998067985,8998068029,8991461676,8991461698,8991461724,8991461746,8998068103,8998068125,8998068147,8998068169,8998068184,8998068206,8998068228,8998068243,8998068265,8998068287,8998068309,8998068324,8796140219,8320011423,8840145638,8001779998,8861979725,8000949404,8003645487,8807975167,8823890625,8807072178,8841753026,8859407339,8855757307,8163389584,8162137435,8157462743,8809710154,8809711226,8166019683,8847655938,8847840539,8007884934,8795544718,8157877607,8896222756,8204072007,8040014116,8814509037,8124592784,8000371075,8891037679,8233719855,8238778965,8082182569,8108962248,8866940275,8831836594,5080900373,8845471743,8203794553,8808552936,8932271017,8081433757,8239753984,8081722043,8800203498,8003437808,8112960556,8114991919,8819080973,8003694174,8819498243,8004081248,8000250465,8100302187,8806763644,8000351519,8102088377,8108152256,8000362859,8102661769,8890191939,8101315227,8083609744,8805150063,8808999245,8809416854,8890930443,8814717475,8154477814,8865315339,8871269355,8860810736,8799398675,8871172239,8816298204,8872565858,8239896548,8833383863,8165494775,8815446654,8805911733,8807566319,8987001239,8080062914,8807549766,8815300258,8232996239,8082142496,8991459959,8991459609,8991458386,8991459565,8991459436,8991459587,8991459635,8991460066,8991460029,8991459948,8991460033,8991460103,8991459329,8991459307,8991459539,8991459657,8991459808,8991460195,8991459377,8991459613,8991459473,8991459517,8991459646,8991460077,8991459749,8991459823,8991459889,8991459915,8991460007,8991460674,8991460659,8991460777,8991460718,8991460696,8991460987,8991460729,8991460755,8991460766,8991460943,8991460965,8991460788,8991460814,8991460685,8991460733,8991460799,8991460873,8991460939,8991460976,8991460998,8991460088,8991460184,8991460206,8991459425,8991459834,8991459856,8991459985,8081919638,8092994467,8815309988,8790825996,8100447286,8082502915,8998067948,8998067996,8998068007,8998068033,8998068044,8998068055,8991461315,8991461466,8991461396,8991461289,8991461477,8991461525,8991461337,8991461536,8991461514,8991461429,8991461418,8991461374,8869832498,8991461503,8870241733,8991461455,8991461407,8991461348,8815923634,8154675553,8168337276,8163141854,8815644507,8803592257,8808057487,8867151406,8845261065,8140433559,8111541214,8097149269,8101402145,8238916224,8828897003,8086647733,8082882643,8200365507,8238368555,8841432787,8798237224,8836006247,8861687976,8839498536,8400020373,8001146885,8040105234,8087725029,8817248008,8991461779,8991461783,8998068368,8991461794,8991461805,8991461816,8991461827,8991461849,8991461853,8232457836,8998048289,8202891306,8933134854,8998068099,8987006305,8991263458,8998026519,8080636667,8987017258,8880055206,8991458629,8991458618,8991458607,8991458596,8991458585,8991458504,8991458526,8991458515,8991458493,8991458478,8991458456,8991458434,8991458419,8991458397,8991458375,8991458353,8092304908,8991461595,8991461606,8991461617,8991461628,8004071135,8991461639,8991461654,8234358653,8001146885,8000752818,8234358653,8104278703,8000253578,8823219336,8139342377,8420014974,8390006486,8139754316,8828473116,8832109815,8213896459,8855703429,8861065267, 8861050637,8861155464,8867934334,8828835786,8159776755,8165902186,8801003624,8801815458,8163026877,8802009314,8804275398,8891890887,81531980598802125864,8793232364,8802383976,8803125298,8236535653,8801435336,8813612396,8801606475,8231361857,8814747199,8816758723,88152146598824261087,8480006558,8500014474,8001550328,8203868657,8550039539,8670029133,8530028293,8290019853,8290012455,8815021087,8086171357,8082593738,8836855303, 8221120039, 8081471767,  8216526105, 8132315869,8093590808, 8005947168, 8093590808, 8809196236, 8093590808, 8838631553";
        String[] arr = kfs.split("\n");
        Set<String> uniqueNumbers = new java.util.HashSet<>();
        int notAddedCount = 0;
        int totalCount = 0;
        for (String number : arr) {
            String[] parts = number.split(",");
            for (String part : parts) {
                totalCount++;
                if (!part.trim().isEmpty()) {
                    if(!uniqueNumbers.contains(part)) {
                        uniqueNumbers.add(part.trim());
                    } else {
                        notAddedCount++;
                    }
                }
            }
        }
        System.out.println("Total numbers: " + totalCount);
        System.out.println("Unique numbers: " + uniqueNumbers.size());
        System.out.println("Not added count: " + notAddedCount);
        printUniqueSet(uniqueNumbers);
    }

    private static void printUniqueSet(Set<String> uniqueNumbers) {
        int count = 0;
        int limit = 40;
        for (String number : uniqueNumbers) {
            if(count == limit) {
                count = 0;
                System.out.println();
            }
            System.out.print(number + ",");
            count++;
        }
    }


    private static void generateTableNamesFromXml(File inputFile) throws Exception {
        File[] files = new File("/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/resources/com/sg/sq/marmsui/database/sql/persistence/mappers").listFiles();
        Set<String> tableNames = new java.util.HashSet<>();
        for(File file : files) {
            System.out.println("Parsing file: " + file.getName());
            parseXmlContent(file,tableNames);
        }
        Set<String> cleanedTableNames = cleanTableNames(tableNames);
        for(String tableName : cleanedTableNames) {
            System.out.println(tableName);
        }
    }

    private static Set<String> cleanTableNames(Set<String> tableNames) {
        Set<String> cleanedTableNames = new java.util.HashSet<>();
        for(String tableName : tableNames) {
            if(tableName.contains(" ")) {
                int end = tableName.indexOf(" ");
                cleanedTableNames.add(tableName.substring(0, end).trim());
            }
        }
        return cleanedTableNames;
    }

    private static void parseXmlContent(File inputFile, Set<String> tableNames) throws Exception {

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        // Allowing XMLs with possibly unknown DTDs or entities
        dbFactory.setNamespaceAware(false);
        dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();

        NodeList selectNodes = doc.getElementsByTagName("select");
        System.out.println("Contents inside <select> tag:");
        for (int i = 0; i < selectNodes.getLength(); i++) {
            Node node = selectNodes.item(i);
            String content = getInnerXmlWithoutComments(node).toLowerCase();
            extractTableNameForSelect(content, tableNames);
        }

        NodeList updateNodes = doc.getElementsByTagName("update");
        System.out.println("Contents inside <update> tag:");
        for (int i = 0; i < updateNodes.getLength(); i++) {
            Node node = updateNodes.item(i);
            System.out.println("Contents inside <update> tag #" + (i + 1) + ":");
            String content = getInnerXmlWithoutComments(node).toLowerCase();
            extractTableNameForUpdate(content, tableNames);
        }

        NodeList deleteNodes = doc.getElementsByTagName("delete");
        System.out.println("Contents inside <delete> tag:");
        for (int i = 0; i < deleteNodes.getLength(); i++) {
            Node node = deleteNodes.item(i);
            System.out.println("Contents inside <update> tag #" + (i + 1) + ":");
            String content = getInnerXmlWithoutComments(node).toLowerCase();
            extractTableNameForDelete(content, tableNames);
        }

        NodeList insertNodes = doc.getElementsByTagName("insert");
        System.out.println("Contents inside <insert> tag:");
        for (int i = 0; i < insertNodes.getLength(); i++) {
            Node node = insertNodes.item(i);
            System.out.println("Contents inside <update> tag #" + (i + 1) + ":");
            String content = getInnerXmlWithoutComments(node).toLowerCase();
            extractTableNameForInsert(content, tableNames);
        }

    }

    private static void extractTableNameForDelete(String content, Set<String> tableNames) {
        // remove all \n char
        content = content.replaceAll("\n", " ");
        // remove all \t char
        content = content.replaceAll("\t", " ");
        int start = content.indexOf("delete from");
        int end = 0;
        while (start != -1) {
            start += 12; // move past "delete from"
            end = content.indexOf("where", start);
            if (end == -1) {
                end = content.length();
            }
            String tableName = content.substring(start, end).trim();
            insertTableNamesInSet(tableName, tableNames);
            if (!tableName.isEmpty()) {
                tableNames.add(tableName);
            }
            start = content.indexOf("delete from", end);
        }
    }

    private static void extractTableNameForInsert(String content, Set<String> tableNames) {
        // remove all \n char
        content = content.replaceAll("\n", " ");
        // remove all \t char
        content = content.replaceAll("\t", " ");
        int start = content.indexOf("insert into");
        int end = 0;
        while (start != -1) {
            start += 11; // move past "insert into"
            end = content.indexOf("(", start);
            if (end == -1) {
                end = content.length();
            }
            String tableName = content.substring(start, end).trim();
            insertTableNamesInSet(tableName, tableNames);
            if (!tableName.isEmpty()) {
                tableNames.add(tableName);
            }
            start = content.indexOf("insert into", end);
        }
    }

    private static void extractTableNameForUpdate(String content, Set<String> tableNames) {
        // remove all \n char
        content = content.replaceAll("\n", " ");
        // remove all \t char
        content = content.replaceAll("\t", " ");
        int start = content.indexOf("update");
        int end = 0;
        while (start != -1) {
            start += 6; // move past "update"
            end = content.indexOf("set", start);
            if (end == -1) {
                end = content.length();
            }
            String tableName = content.substring(start, end).trim();
            insertTableNamesInSet(tableName, tableNames);
            if (!tableName.isEmpty()) {
                tableNames.add(tableName);
            }
            start = content.indexOf("update", end);
        }
    }

    private static void extractTableNameForSelect(String content, Set<String> tableNames) {
        // remove all \n char
        content = content.replaceAll("\n", " ");
        // remove all \t char
        content = content.replaceAll("\t", " ");
        int start = content.indexOf("from");
        int end = 0;
        while (start != -1) {
            start += 4; // move past "from"
            end = content.indexOf("where", start);
            if (end == -1) {
                end = content.length();
            }
            String tableName = content.substring(start, end).trim();
            insertTableNamesInSet(tableName, tableNames);
            if (!tableName.isEmpty()) {
                tableNames.add(tableName);
            }
            start = content.indexOf("from", end);
        }

    }

    private static void insertTableNamesInSet(String tableName, Set<String> tableNames) {
        if (tableName.contains(",")) {
            String[] split = tableName.split(",");
            for (String val : split) {
                if (!val.isEmpty()) {
                    tableNames.add(val.trim());
                }
            }
        } else {
            tableNames.add(tableName);
        }
    }


    private static String getInnerXmlWithoutComments(Node node) throws Exception {
        StringBuilder sb = new StringBuilder();
        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            // Skip comment nodes
            if (child.getNodeType() == Node.COMMENT_NODE) {
                continue;
            }
            sb.append(nodeToStringWithoutComments(child));
        }
        return sb.toString().trim();
    }

    private static String nodeToStringWithoutComments(Node node) throws Exception {
        if (node.getNodeType() == Node.COMMENT_NODE) {
            return ""; // skip comments
        } else if (node.getNodeType() == Node.TEXT_NODE || node.getNodeType() == Node.CDATA_SECTION_NODE) {
            return node.getTextContent();
        } else if (node.getNodeType() == Node.ELEMENT_NODE) {
            // Serialize element node, but handle comments inside recursively
            StringBuilder sb = new StringBuilder();
            sb.append('<').append(node.getNodeName());

            // Append attributes
            NamedNodeMap attrs = node.getAttributes();
            for (int j = 0; attrs != null && j < attrs.getLength(); j++) {
                Node attr = attrs.item(j);
                sb.append(" ").append(attr.getNodeName()).append("=\"")
                        .append(attr.getNodeValue()).append("\"");
            }
            sb.append('>');

            // Append children, skipping comments
            NodeList children = node.getChildNodes();
            for (int k = 0; k < children.getLength(); k++) {
                sb.append(nodeToStringWithoutComments(children.item(k)));
            }
            sb.append("</").append(node.getNodeName()).append('>');
            return sb.toString();
        } else {
            // For other node types (rare in SQL), skip
            return "";
        }
    }
}


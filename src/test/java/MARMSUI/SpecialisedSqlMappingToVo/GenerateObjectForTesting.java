package MARMSUI.SpecialisedSqlMappingToVo;

import MARMSUI.SpecialisedSqlMappingToVo.model.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.corba.se.spi.ior.ObjectKey;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class GenerateObjectForTesting {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, ParseException {
        try {
            String response = "[{\"seqNo\":6,\"tierStatus\":\"G\",\"tierTypeInd\":\"ELITE\",\"programCd\":\"KF\",\"hierarchy\":2,\"minPeriod\":0,\"qualProcID\":\"EP\",\"sectReq\":0.0,\"pointOffset\":0,\"sectorOffset\":0.0,\"accPtsReq\":0,\"accSectReq\":0.0,\"ptsReq\":50000,\"accPtsOffset\":0,\"acctSectOffset\":0.0,\"active\":\"Y\",\"applQualInd\":null,\"applQualIndList\":null,\"ruleInfo\":null,\"addFlag\":null,\"deleteFlag\":null,\"updateFlag\":null,\"viewFlag\":null,\"allFlag\":null,\"classCode\":null,\"tierQuadPrd\":0,\"valReq\":0,\"valOffset\":0,\"accValReq\":0,\"accValOffset\":0,\"ruleInd\":\"M\",\"yrsInTier\":0,\"eliteOrPPSpercentage\":0,\"qualInterim\":null,\"startDate\":\"01-01-1999\",\"endDate\":\"31-12-2099\",\"tierBonusFactor\":0,\"tierBonusValue\":0,\"interim\":\"N\"},{\"seqNo\":7,\"tierStatus\":\"S\",\"tierTypeInd\":\"ELITE\",\"programCd\":\"KF\",\"hierarchy\":1,\"minPeriod\":0,\"qualProcID\":\"EP\",\"sectReq\":0.0,\"pointOffset\":0,\"sectorOffset\":0.0,\"accPtsReq\":0,\"accSectReq\":0.0,\"ptsReq\":25000,\"accPtsOffset\":0,\"acctSectOffset\":0.0,\"active\":\"Y\",\"applQualInd\":null,\"applQualIndList\":null,\"ruleInfo\":null,\"addFlag\":null,\"deleteFlag\":null,\"updateFlag\":null,\"viewFlag\":null,\"allFlag\":null,\"classCode\":null,\"tierQuadPrd\":0,\"valReq\":0,\"valOffset\":0,\"accValReq\":0,\"accValOffset\":0,\"ruleInd\":\"M\",\"yrsInTier\":0,\"eliteOrPPSpercentage\":0,\"qualInterim\":null,\"startDate\":\"01-01-1999\",\"endDate\":\"31-12-2099\",\"tierBonusFactor\":0,\"tierBonusValue\":0,\"interim\":\"N\"},{\"seqNo\":23,\"tierStatus\":\"G\",\"tierTypeInd\":\"ELITE\",\"programCd\":\"KF\",\"hierarchy\":2,\"minPeriod\":0,\"qualProcID\":\"ER\",\"sectReq\":0.0,\"pointOffset\":0,\"sectorOffset\":0.0,\"accPtsReq\":0,\"accSectReq\":0.0,\"ptsReq\":50000,\"accPtsOffset\":0,\"acctSectOffset\":0.0,\"active\":\"Y\",\"applQualInd\":null,\"applQualIndList\":null,\"ruleInfo\":null,\"addFlag\":null,\"deleteFlag\":null,\"updateFlag\":null,\"viewFlag\":null,\"allFlag\":null,\"classCode\":null,\"tierQuadPrd\":12,\"valReq\":0,\"valOffset\":0,\"accValReq\":0,\"accValOffset\":0,\"ruleInd\":\"M\",\"yrsInTier\":0,\"eliteOrPPSpercentage\":0,\"qualInterim\":null,\"startDate\":\"01-01-1999\",\"endDate\":\"31-12-2099\",\"tierBonusFactor\":0,\"tierBonusValue\":0,\"interim\":\"N\"},{\"seqNo\":22,\"tierStatus\":\"S\",\"tierTypeInd\":\"ELITE\",\"programCd\":\"KF\",\"hierarchy\":1,\"minPeriod\":0,\"qualProcID\":\"ER\",\"sectReq\":0.0,\"pointOffset\":0,\"sectorOffset\":0.0,\"accPtsReq\":0,\"accSectReq\":0.0,\"ptsReq\":25000,\"accPtsOffset\":0,\"acctSectOffset\":0.0,\"active\":\"Y\",\"applQualInd\":null,\"applQualIndList\":null,\"ruleInfo\":null,\"addFlag\":null,\"deleteFlag\":null,\"updateFlag\":null,\"viewFlag\":null,\"allFlag\":null,\"classCode\":null,\"tierQuadPrd\":12,\"valReq\":0,\"valOffset\":0,\"accValReq\":0,\"accValOffset\":0,\"ruleInd\":\"M\",\"yrsInTier\":0,\"eliteOrPPSpercentage\":0,\"qualInterim\":null,\"startDate\":\"01-01-1999\",\"endDate\":\"31-12-2099\",\"tierBonusFactor\":0,\"tierBonusValue\":0,\"interim\":\"N\"},{\"seqNo\":25,\"tierStatus\":\"Q\",\"tierTypeInd\":\"PPS\",\"programCd\":\"KF\",\"hierarchy\":3,\"minPeriod\":0,\"qualProcID\":\"FR\",\"sectReq\":0.0,\"pointOffset\":0,\"sectorOffset\":0.0,\"accPtsReq\":0,\"accSectReq\":0.0,\"ptsReq\":0,\"accPtsOffset\":0,\"acctSectOffset\":0.0,\"active\":\"Y\",\"applQualInd\":null,\"applQualIndList\":null,\"ruleInfo\":null,\"addFlag\":null,\"deleteFlag\":null,\"updateFlag\":null,\"viewFlag\":null,\"allFlag\":null,\"classCode\":null,\"tierQuadPrd\":12,\"valReq\":10000,\"valOffset\":0,\"accValReq\":0,\"accValOffset\":0,\"ruleInd\":\"V\",\"yrsInTier\":0,\"eliteOrPPSpercentage\":0,\"qualInterim\":null,\"startDate\":\"28-02-2010\",\"endDate\":\"31-01-2011\",\"tierBonusFactor\":0,\"tierBonusValue\":0,\"interim\":\"N\"},{\"seqNo\":26,\"tierStatus\":\"Q\",\"tierTypeInd\":\"PPS\",\"programCd\":\"KF\",\"hierarchy\":3,\"minPeriod\":0,\"qualProcID\":\"FR\",\"sectReq\":0.0,\"pointOffset\":0,\"sectorOffset\":0.0,\"accPtsReq\":0,\"accSectReq\":0.0,\"ptsReq\":0,\"accPtsOffset\":0,\"acctSectOffset\":0.0,\"active\":\"Y\",\"applQualInd\":null,\"applQualIndList\":null,\"ruleInfo\":null,\"addFlag\":null,\"deleteFlag\":null,\"updateFlag\":null,\"viewFlag\":null,\"allFlag\":null,\"classCode\":null,\"tierQuadPrd\":12,\"valReq\":23000,\"valOffset\":0,\"accValReq\":0,\"accValOffset\":0,\"ruleInd\":\"V\",\"yrsInTier\":0,\"eliteOrPPSpercentage\":0,\"qualInterim\":null,\"startDate\":\"30-06-2012\",\"endDate\":\"27-12-2012\",\"tierBonusFactor\":0,\"tierBonusValue\":0,\"interim\":\"N\"},{\"seqNo\":24,\"tierStatus\":\"T\",\"tierTypeInd\":\"PPS\",\"programCd\":\"KF\",\"hierarchy\":4,\"minPeriod\":0,\"qualProcID\":\"FR\",\"sectReq\":0.0,\"pointOffset\":0,\"sectorOffset\":0.0,\"accPtsReq\":0,\"accSectReq\":0.0,\"ptsReq\":0,\"accPtsOffset\":0,\"acctSectOffset\":0.0,\"active\":\"Y\",\"applQualInd\":null,\"applQualIndList\":null,\"ruleInfo\":null,\"addFlag\":null,\"deleteFlag\":null,\"updateFlag\":null,\"viewFlag\":null,\"allFlag\":null,\"classCode\":null,\"tierQuadPrd\":12,\"valReq\":10000,\"valOffset\":0,\"accValReq\":0,\"accValOffset\":0,\"ruleInd\":\"V\",\"yrsInTier\":0,\"eliteOrPPSpercentage\":0,\"qualInterim\":null,\"startDate\":\"28-02-2010\",\"endDate\":\"31-01-2011\",\"tierBonusFactor\":0,\"tierBonusValue\":0,\"interim\":\"N\"},{\"seqNo\":27,\"tierStatus\":\"T\",\"tierTypeInd\":\"PPS\",\"programCd\":\"KF\",\"hierarchy\":4,\"minPeriod\":0,\"qualProcID\":\"FR\",\"sectReq\":0.0,\"pointOffset\":0,\"sectorOffset\":0.0,\"accPtsReq\":0,\"accSectReq\":0.0,\"ptsReq\":0,\"accPtsOffset\":0,\"acctSectOffset\":0.0,\"active\":\"Y\",\"applQualInd\":null,\"applQualIndList\":null,\"ruleInfo\":null,\"addFlag\":null,\"deleteFlag\":null,\"updateFlag\":null,\"viewFlag\":null,\"allFlag\":null,\"classCode\":null,\"tierQuadPrd\":12,\"valReq\":25000,\"valOffset\":0,\"accValReq\":0,\"accValOffset\":0,\"ruleInd\":\"V\",\"yrsInTier\":0,\"eliteOrPPSpercentage\":0,\"qualInterim\":null,\"startDate\":\"30-06-2012\",\"endDate\":\"31-12-2012\",\"tierBonusFactor\":0,\"tierBonusValue\":0,\"interim\":\"N\"},{\"seqNo\":12,\"tierStatus\":\"Q\",\"tierTypeInd\":\"PPS\",\"programCd\":\"KF\",\"hierarchy\":3,\"minPeriod\":0,\"qualProcID\":\"PP\",\"sectReq\":25.0,\"pointOffset\":0,\"sectorOffset\":0.0,\"accPtsReq\":0,\"accSectReq\":0.0,\"ptsReq\":50000,\"accPtsOffset\":0,\"acctSectOffset\":0.0,\"active\":\"Y\",\"applQualInd\":null,\"applQualIndList\":null,\"ruleInfo\":null,\"addFlag\":null,\"deleteFlag\":null,\"updateFlag\":null,\"viewFlag\":null,\"allFlag\":null,\"classCode\":null,\"tierQuadPrd\":0,\"valReq\":0,\"valOffset\":0,\"accValReq\":0,\"accValOffset\":0,\"ruleInd\":\"M\",\"yrsInTier\":0,\"eliteOrPPSpercentage\":0,\"qualInterim\":null,\"startDate\":\"01-09-2007\",\"endDate\":\"14-09-2007\",\"tierBonusFactor\":0,\"tierBonusValue\":0,\"interim\":\"N\"},{\"seqNo\":19,\"tierStatus\":\"Q\",\"tierTypeInd\":\"PPS\",\"programCd\":\"KF\",\"hierarchy\":3,\"minPeriod\":0,\"qualProcID\":\"PP\",\"sectReq\":0.0,\"pointOffset\":0,\"sectorOffset\":0.0,\"accPtsReq\":0,\"accSectReq\":0.0,\"ptsReq\":0,\"accPtsOffset\":0,\"acctSectOffset\":0.0,\"active\":\"Y\",\"applQualInd\":null,\"applQualIndList\":null,\"ruleInfo\":null,\"addFlag\":null,\"deleteFlag\":null,\"updateFlag\":null,\"viewFlag\":null,\"allFlag\":null,\"classCode\":null,\"tierQuadPrd\":0,\"valReq\":25000,\"valOffset\":0,\"accValReq\":0,\"accValOffset\":0,\"ruleInd\":\"V\",\"yrsInTier\":0,\"eliteOrPPSpercentage\":0,\"qualInterim\":null,\"startDate\":\"01-01-1999\",\"endDate\":\"31-12-2099\",\"tierBonusFactor\":0,\"tierBonusValue\":0,\"interim\":\"N\"},{\"seqNo\":17,\"tierStatus\":\"L\",\"tierTypeInd\":\"PPS\",\"programCd\":\"KF\",\"hierarchy\":5,\"minPeriod\":0,\"qualProcID\":\"PR\",\"sectReq\":0.0,\"pointOffset\":0,\"sectorOffset\":0.0,\"accPtsReq\":0,\"accSectReq\":0.0,\"ptsReq\":0,\"accPtsOffset\":0,\"acctSectOffset\":0.0,\"active\":\"Y\",\"applQualInd\":null,\"applQualIndList\":null,\"ruleInfo\":null,\"addFlag\":null,\"deleteFlag\":null,\"updateFlag\":null,\"viewFlag\":null,\"allFlag\":null,\"classCode\":null,\"tierQuadPrd\":12,\"valReq\":0,\"valOffset\":0,\"accValReq\":0,\"accValOffset\":0,\"ruleInd\":\"V\",\"yrsInTier\":0,\"eliteOrPPSpercentage\":0,\"qualInterim\":null,\"startDate\":\"01-01-1999\",\"endDate\":\"31-12-2099\",\"tierBonusFactor\":0,\"tierBonusValue\":0,\"interim\":\"N\"},{\"seqNo\":3,\"tierStatus\":\"Q\",\"tierTypeInd\":\"PPS\",\"programCd\":\"KF\",\"hierarchy\":3,\"minPeriod\":0,\"qualProcID\":\"PR\",\"sectReq\":25.0,\"pointOffset\":-25000,\"sectorOffset\":-12.5,\"accPtsReq\":50000,\"accSectReq\":25.0,\"ptsReq\":50000,\"accPtsOffset\":0,\"acctSectOffset\":0.0,\"active\":\"Y\",\"applQualInd\":null,\"applQualIndList\":null,\"ruleInfo\":null,\"addFlag\":null,\"deleteFlag\":null,\"updateFlag\":null,\"viewFlag\":null,\"allFlag\":null,\"classCode\":null,\"tierQuadPrd\":0,\"valReq\":0,\"valOffset\":0,\"accValReq\":0,\"accValOffset\":0,\"ruleInd\":\"M\",\"yrsInTier\":0,\"eliteOrPPSpercentage\":0,\"qualInterim\":null,\"startDate\":\"01-01-1999\",\"endDate\":\"01-06-2006\",\"tierBonusFactor\":0,\"tierBonusValue\":0,\"interim\":\"N\"},{\"seqNo\":13,\"tierStatus\":\"Q\",\"tierTypeInd\":\"PPS\",\"programCd\":\"KF\",\"hierarchy\":3,\"minPeriod\":0,\"qualProcID\":\"PR\",\"sectReq\":25.0,\"pointOffset\":-500,\"sectorOffset\":-1.0,\"accPtsReq\":0,\"accSectReq\":0.0,\"ptsReq\":50000,\"accPtsOffset\":0,\"acctSectOffset\":0.0,\"active\":\"Y\",\"applQualInd\":null,\"applQualIndList\":null,\"ruleInfo\":null,\"addFlag\":null,\"deleteFlag\":null,\"updateFlag\":null,\"viewFlag\":null,\"allFlag\":null,\"classCode\":null,\"tierQuadPrd\":0,\"valReq\":0,\"valOffset\":0,\"accValReq\":0,\"accValOffset\":0,\"ruleInd\":\"M\",\"yrsInTier\":0,\"eliteOrPPSpercentage\":0,\"qualInterim\":null,\"startDate\":\"01-09-2007\",\"endDate\":\"14-09-2007\",\"tierBonusFactor\":0,\"tierBonusValue\":0,\"interim\":\"N\"},{\"seqNo\":20,\"tierStatus\":\"Q\",\"tierTypeInd\":\"PPS\",\"programCd\":\"KF\",\"hierarchy\":3,\"minPeriod\":0,\"qualProcID\":\"PR\",\"sectReq\":0.0,\"pointOffset\":0,\"sectorOffset\":0.0,\"accPtsReq\":0,\"accSectReq\":0.0,\"ptsReq\":0,\"accPtsOffset\":0,\"acctSectOffset\":0.0,\"active\":\"Y\",\"applQualInd\":null,\"applQualIndList\":null,\"ruleInfo\":null,\"addFlag\":null,\"deleteFlag\":null,\"updateFlag\":null,\"viewFlag\":null,\"allFlag\":null,\"classCode\":null,\"tierQuadPrd\":0,\"valReq\":25000,\"valOffset\":0,\"accValReq\":0,\"accValOffset\":0,\"ruleInd\":\"V\",\"yrsInTier\":0,\"eliteOrPPSpercentage\":0,\"qualInterim\":null,\"startDate\":\"01-01-1999\",\"endDate\":\"31-12-2099\",\"tierBonusFactor\":0,\"tierBonusValue\":0,\"interim\":\"N\"},{\"seqNo\":8,\"tierStatus\":\"T\",\"tierTypeInd\":\"PPS\",\"programCd\":\"KF\",\"hierarchy\":4,\"minPeriod\":0,\"qualProcID\":\"PR\",\"sectReq\":25.0,\"pointOffset\":-25000,\"sectorOffset\":-12.5,\"accPtsReq\":50000,\"accSectReq\":25.0,\"ptsReq\":50000,\"accPtsOffset\":0,\"acctSectOffset\":0.0,\"active\":\"Y\",\"applQualInd\":null,\"applQualIndList\":null,\"ruleInfo\":null,\"addFlag\":null,\"deleteFlag\":null,\"updateFlag\":null,\"viewFlag\":null,\"allFlag\":null,\"classCode\":null,\"tierQuadPrd\":0,\"valReq\":0,\"valOffset\":0,\"accValReq\":0,\"accValOffset\":0,\"ruleInd\":\"M\",\"yrsInTier\":0,\"eliteOrPPSpercentage\":0,\"qualInterim\":null,\"startDate\":\"01-01-1999\",\"endDate\":\"01-08-2006\",\"tierBonusFactor\":0,\"tierBonusValue\":0,\"interim\":\"N\"},{\"seqNo\":11,\"tierStatus\":\"T\",\"tierTypeInd\":\"PPS\",\"programCd\":\"KF\",\"hierarchy\":4,\"minPeriod\":0,\"qualProcID\":\"PR\",\"sectReq\":25.0,\"pointOffset\":-500,\"sectorOffset\":-1.0,\"accPtsReq\":0,\"accSectReq\":0.0,\"ptsReq\":50000,\"accPtsOffset\":0,\"acctSectOffset\":0.0,\"active\":\"Y\",\"applQualInd\":null,\"applQualIndList\":null,\"ruleInfo\":null,\"addFlag\":null,\"deleteFlag\":null,\"updateFlag\":null,\"viewFlag\":null,\"allFlag\":null,\"classCode\":null,\"tierQuadPrd\":0,\"valReq\":0,\"valOffset\":0,\"accValReq\":0,\"accValOffset\":0,\"ruleInd\":\"M\",\"yrsInTier\":0,\"eliteOrPPSpercentage\":0,\"qualInterim\":null,\"startDate\":\"01-09-2007\",\"endDate\":\"14-09-2007\",\"tierBonusFactor\":0,\"tierBonusValue\":0,\"interim\":\"N\"},{\"seqNo\":21,\"tierStatus\":\"T\",\"tierTypeInd\":\"PPS\",\"programCd\":\"KF\",\"hierarchy\":4,\"minPeriod\":0,\"qualProcID\":\"PR\",\"sectReq\":0.0,\"pointOffset\":0,\"sectorOffset\":0.0,\"accPtsReq\":0,\"accSectReq\":0.0,\"ptsReq\":0,\"accPtsOffset\":0,\"acctSectOffset\":0.0,\"active\":\"Y\",\"applQualInd\":null,\"applQualIndList\":null,\"ruleInfo\":null,\"addFlag\":null,\"deleteFlag\":null,\"updateFlag\":null,\"viewFlag\":null,\"allFlag\":null,\"classCode\":null,\"tierQuadPrd\":0,\"valReq\":25000,\"valOffset\":0,\"accValReq\":0,\"accValOffset\":0,\"ruleInd\":\"V\",\"yrsInTier\":0,\"eliteOrPPSpercentage\":0,\"qualInterim\":null,\"startDate\":\"01-01-1999\",\"endDate\":\"31-12-2099\",\"tierBonusFactor\":0,\"tierBonusValue\":0,\"interim\":\"N\"},{\"seqNo\":28,\"tierStatus\":\"T\",\"tierTypeInd\":\"PPS\",\"programCd\":\"KF\",\"hierarchy\":4,\"minPeriod\":0,\"qualProcID\":\"PR\",\"sectReq\":0.0,\"pointOffset\":0,\"sectorOffset\":0.0,\"accPtsReq\":0,\"accSectReq\":0.0,\"ptsReq\":0,\"accPtsOffset\":0,\"acctSectOffset\":0.0,\"active\":\"Y\",\"applQualInd\":null,\"applQualIndList\":null,\"ruleInfo\":null,\"addFlag\":null,\"deleteFlag\":null,\"updateFlag\":null,\"viewFlag\":null,\"allFlag\":null,\"classCode\":null,\"tierQuadPrd\":12,\"valReq\":50000,\"valOffset\":0,\"accValReq\":0,\"accValOffset\":0,\"ruleInd\":\"V\",\"yrsInTier\":0,\"eliteOrPPSpercentage\":0,\"qualInterim\":\"Y\",\"startDate\":\"01-08-2017\",\"endDate\":\"31-05-2018\",\"tierBonusFactor\":0,\"tierBonusValue\":0,\"interim\":\"N\"},{\"seqNo\":31,\"tierStatus\":\"T\",\"tierTypeInd\":\"PPS\",\"programCd\":\"KF\",\"hierarchy\":4,\"minPeriod\":0,\"qualProcID\":\"PR\",\"sectReq\":0.0,\"pointOffset\":0,\"sectorOffset\":0.0,\"accPtsReq\":0,\"accSectReq\":0.0,\"ptsReq\":0,\"accPtsOffset\":0,\"acctSectOffset\":0.0,\"active\":\"Y\",\"applQualInd\":null,\"applQualIndList\":null,\"ruleInfo\":null,\"addFlag\":null,\"deleteFlag\":null,\"updateFlag\":null,\"viewFlag\":null,\"allFlag\":null,\"classCode\":null,\"tierQuadPrd\":12,\"valReq\":50000,\"valOffset\":0,\"accValReq\":0,\"accValOffset\":0,\"ruleInd\":\"V\",\"yrsInTier\":0,\"eliteOrPPSpercentage\":0,\"qualInterim\":\"Y\",\"startDate\":\"01-06-2018\",\"endDate\":\"31-12-2099\",\"tierBonusFactor\":0,\"tierBonusValue\":0,\"interim\":\"N\"},{\"seqNo\":32,\"tierStatus\":\"T\",\"tierTypeInd\":\"PPS\",\"programCd\":\"KF\",\"hierarchy\":4,\"minPeriod\":0,\"qualProcID\":\"PSU\",\"sectReq\":0.0,\"pointOffset\":0,\"sectorOffset\":0.0,\"accPtsReq\":0,\"accSectReq\":0.0,\"ptsReq\":0,\"accPtsOffset\":0,\"acctSectOffset\":0.0,\"active\":\"Y\",\"applQualInd\":null,\"applQualIndList\":null,\"ruleInfo\":null,\"addFlag\":null,\"deleteFlag\":null,\"updateFlag\":null,\"viewFlag\":null,\"allFlag\":null,\"classCode\":null,\"tierQuadPrd\":12,\"valReq\":50000,\"valOffset\":0,\"accValReq\":0,\"accValOffset\":0,\"ruleInd\":\"V\",\"yrsInTier\":0,\"eliteOrPPSpercentage\":0,\"qualInterim\":\"Y\",\"startDate\":\"20-07-2019\",\"endDate\":\"31-12-2099\",\"tierBonusFactor\":0,\"tierBonusValue\":0,\"interim\":\"N\"},{\"seqNo\":16,\"tierStatus\":\"L\",\"tierTypeInd\":\"PPS\",\"programCd\":\"KF\",\"hierarchy\":5,\"minPeriod\":0,\"qualProcID\":\"PU\",\"sectReq\":0.0,\"pointOffset\":0,\"sectorOffset\":0.0,\"accPtsReq\":1875000,\"accSectReq\":1000.0,\"ptsReq\":0,\"accPtsOffset\":0,\"acctSectOffset\":0.0,\"active\":\"Y\",\"applQualInd\":null,\"applQualIndList\":null,\"ruleInfo\":null,\"addFlag\":null,\"deleteFlag\":null,\"updateFlag\":null,\"viewFlag\":null,\"allFlag\":null,\"classCode\":null,\"tierQuadPrd\":0,\"valReq\":0,\"valOffset\":0,\"accValReq\":0,\"accValOffset\":0,\"ruleInd\":\"M\",\"yrsInTier\":0,\"eliteOrPPSpercentage\":0,\"qualInterim\":null,\"startDate\":\"01-01-1999\",\"endDate\":\"31-08-2007\",\"tierBonusFactor\":0,\"tierBonusValue\":0,\"interim\":\"N\"},{\"seqNo\":1,\"tierStatus\":\"T\",\"tierTypeInd\":\"PPS\",\"programCd\":\"KF\",\"hierarchy\":4,\"minPeriod\":5,\"qualProcID\":\"PU\",\"sectReq\":0.0,\"pointOffset\":0,\"sectorOffset\":0.0,\"accPtsReq\":500000,\"accSectReq\":250.0,\"ptsReq\":0,\"accPtsOffset\":0,\"acctSectOffset\":0.0,\"active\":\"Y\",\"applQualInd\":null,\"applQualIndList\":null,\"ruleInfo\":null,\"addFlag\":null,\"deleteFlag\":null,\"updateFlag\":null,\"viewFlag\":null,\"allFlag\":null,\"classCode\":null,\"tierQuadPrd\":0,\"valReq\":0,\"valOffset\":0,\"accValReq\":0,\"accValOffset\":0,\"ruleInd\":\"M\",\"yrsInTier\":0,\"eliteOrPPSpercentage\":0,\"qualInterim\":null,\"startDate\":\"01-01-1999\",\"endDate\":\"01-08-2006\",\"tierBonusFactor\":0,\"tierBonusValue\":0,\"interim\":\"N\"},{\"seqNo\":2,\"tierStatus\":\"T\",\"tierTypeInd\":\"PPS\",\"programCd\":\"KF\",\"hierarchy\":4,\"minPeriod\":0,\"qualProcID\":\"PU\",\"sectReq\":0.0,\"pointOffset\":0,\"sectorOffset\":0.0,\"accPtsReq\":0,\"accSectReq\":0.0,\"ptsReq\":0,\"accPtsOffset\":0,\"acctSectOffset\":0.0,\"active\":\"Y\",\"applQualInd\":null,\"applQualIndList\":null,\"ruleInfo\":null,\"addFlag\":null,\"deleteFlag\":null,\"updateFlag\":null,\"viewFlag\":null,\"allFlag\":null,\"classCode\":null,\"tierQuadPrd\":12,\"valReq\":25000,\"valOffset\":0,\"accValReq\":0,\"accValOffset\":0,\"ruleInd\":\"V\",\"yrsInTier\":0,\"eliteOrPPSpercentage\":0,\"qualInterim\":null,\"startDate\":\"01-01-1999\",\"endDate\":\"31-12-2099\",\"tierBonusFactor\":0,\"tierBonusValue\":0,\"interim\":\"N\"},{\"seqNo\":4,\"tierStatus\":\"T\",\"tierTypeInd\":\"PPS\",\"programCd\":\"KF\",\"hierarchy\":4,\"minPeriod\":0,\"qualProcID\":\"PU\",\"sectReq\":0.0,\"pointOffset\":0,\"sectorOffset\":0.0,\"accPtsReq\":750000,\"accSectReq\":375.0,\"ptsReq\":0,\"accPtsOffset\":0,\"acctSectOffset\":0.0,\"active\":\"Y\",\"applQualInd\":null,\"applQualIndList\":null,\"ruleInfo\":null,\"addFlag\":null,\"deleteFlag\":null,\"updateFlag\":null,\"viewFlag\":null,\"allFlag\":null,\"classCode\":null,\"tierQuadPrd\":0,\"valReq\":0,\"valOffset\":0,\"accValReq\":0,\"accValOffset\":0,\"ruleInd\":\"M\",\"yrsInTier\":0,\"eliteOrPPSpercentage\":0,\"qualInterim\":null,\"startDate\":\"01-01-1999\",\"endDate\":\"31-12-2006\",\"tierBonusFactor\":0,\"tierBonusValue\":0,\"interim\":\"N\"},{\"seqNo\":5,\"tierStatus\":\"T\",\"tierTypeInd\":\"PPS\",\"programCd\":\"KF\",\"hierarchy\":4,\"minPeriod\":0,\"qualProcID\":\"PU\",\"sectReq\":25.0,\"pointOffset\":-25000,\"sectorOffset\":-12.5,\"accPtsReq\":50000,\"accSectReq\":25.0,\"ptsReq\":50000,\"accPtsOffset\":0,\"acctSectOffset\":0.0,\"active\":\"Y\",\"applQualInd\":null,\"applQualIndList\":null,\"ruleInfo\":null,\"addFlag\":null,\"deleteFlag\":null,\"updateFlag\":null,\"viewFlag\":null,\"allFlag\":null,\"classCode\":null,\"tierQuadPrd\":0,\"valReq\":0,\"valOffset\":0,\"accValReq\":0,\"accValOffset\":0,\"ruleInd\":\"M\",\"yrsInTier\":0,\"eliteOrPPSpercentage\":0,\"qualInterim\":null,\"startDate\":\"01-01-1999\",\"endDate\":\"02-08-2006\",\"tierBonusFactor\":0,\"tierBonusValue\":0,\"interim\":\"N\"},{\"seqNo\":9,\"tierStatus\":\"T\",\"tierTypeInd\":\"PPS\",\"programCd\":\"KF\",\"hierarchy\":4,\"minPeriod\":4,\"qualProcID\":\"PU\",\"sectReq\":25.0,\"pointOffset\":0,\"sectorOffset\":0.0,\"accPtsReq\":500000,\"accSectReq\":250.0,\"ptsReq\":50000,\"accPtsOffset\":0,\"acctSectOffset\":0.0,\"active\":\"Y\",\"applQualInd\":null,\"applQualIndList\":null,\"ruleInfo\":null,\"addFlag\":null,\"deleteFlag\":null,\"updateFlag\":null,\"viewFlag\":null,\"allFlag\":null,\"classCode\":null,\"tierQuadPrd\":0,\"valReq\":0,\"valOffset\":0,\"accValReq\":0,\"accValOffset\":0,\"ruleInd\":\"M\",\"yrsInTier\":0,\"eliteOrPPSpercentage\":0,\"qualInterim\":null,\"startDate\":\"01-01-1999\",\"endDate\":\"31-12-2006\",\"tierBonusFactor\":0,\"tierBonusValue\":0,\"interim\":\"N\"},{\"seqNo\":10,\"tierStatus\":\"T\",\"tierTypeInd\":\"PPS\",\"programCd\":\"KF\",\"hierarchy\":4,\"minPeriod\":3,\"qualProcID\":\"PU\",\"sectReq\":25.0,\"pointOffset\":0,\"sectorOffset\":0.0,\"accPtsReq\":500000,\"accSectReq\":250.0,\"ptsReq\":50000,\"accPtsOffset\":0,\"acctSectOffset\":0.0,\"active\":\"Y\",\"applQualInd\":null,\"applQualIndList\":null,\"ruleInfo\":null,\"addFlag\":null,\"deleteFlag\":null,\"updateFlag\":null,\"viewFlag\":null,\"allFlag\":null,\"classCode\":null,\"tierQuadPrd\":0,\"valReq\":0,\"valOffset\":0,\"accValReq\":0,\"accValOffset\":0,\"ruleInd\":\"M\",\"yrsInTier\":0,\"eliteOrPPSpercentage\":0,\"qualInterim\":null,\"startDate\":\"01-01-1999\",\"endDate\":\"31-12-2006\",\"tierBonusFactor\":0,\"tierBonusValue\":0,\"interim\":\"N\"},{\"seqNo\":14,\"tierStatus\":\"T\",\"tierTypeInd\":\"PPS\",\"programCd\":\"KF\",\"hierarchy\":4,\"minPeriod\":0,\"qualProcID\":\"PU\",\"sectReq\":25.0,\"pointOffset\":-500,\"sectorOffset\":-1.0,\"accPtsReq\":0,\"accSectReq\":0.0,\"ptsReq\":50000,\"accPtsOffset\":0,\"acctSectOffset\":0.0,\"active\":\"Y\",\"applQualInd\":null,\"applQualIndList\":null,\"ruleInfo\":null,\"addFlag\":null,\"deleteFlag\":null,\"updateFlag\":null,\"viewFlag\":null,\"allFlag\":null,\"classCode\":null,\"tierQuadPrd\":0,\"valReq\":0,\"valOffset\":0,\"accValReq\":0,\"accValOffset\":0,\"ruleInd\":\"M\",\"yrsInTier\":0,\"eliteOrPPSpercentage\":0,\"qualInterim\":null,\"startDate\":\"01-09-2007\",\"endDate\":\"14-09-2007\",\"tierBonusFactor\":0,\"tierBonusValue\":0,\"interim\":\"N\"},{\"seqNo\":15,\"tierStatus\":\"T\",\"tierTypeInd\":\"PPS\",\"programCd\":\"KF\",\"hierarchy\":4,\"minPeriod\":0,\"qualProcID\":\"PU\",\"sectReq\":0.0,\"pointOffset\":0,\"sectorOffset\":0.0,\"accPtsReq\":500000,\"accSectReq\":250.0,\"ptsReq\":0,\"accPtsOffset\":0,\"acctSectOffset\":0.0,\"active\":\"Y\",\"applQualInd\":null,\"applQualIndList\":null,\"ruleInfo\":null,\"addFlag\":null,\"deleteFlag\":null,\"updateFlag\":null,\"viewFlag\":null,\"allFlag\":null,\"classCode\":null,\"tierQuadPrd\":0,\"valReq\":0,\"valOffset\":0,\"accValReq\":0,\"accValOffset\":0,\"ruleInd\":\"M\",\"yrsInTier\":0,\"eliteOrPPSpercentage\":0,\"qualInterim\":null,\"startDate\":\"01-09-2007\",\"endDate\":\"14-09-2007\",\"tierBonusFactor\":0,\"tierBonusValue\":0,\"interim\":\"N\"},{\"seqNo\":18,\"tierStatus\":\"T\",\"tierTypeInd\":\"PPS\",\"programCd\":\"KF\",\"hierarchy\":4,\"minPeriod\":0,\"qualProcID\":\"PU\",\"sectReq\":0.0,\"pointOffset\":0,\"sectorOffset\":0.0,\"accPtsReq\":0,\"accSectReq\":0.0,\"ptsReq\":0,\"accPtsOffset\":0,\"acctSectOffset\":0.0,\"active\":\"Y\",\"applQualInd\":null,\"applQualIndList\":null,\"ruleInfo\":null,\"addFlag\":null,\"deleteFlag\":null,\"updateFlag\":null,\"viewFlag\":null,\"allFlag\":null,\"classCode\":null,\"tierQuadPrd\":0,\"valReq\":0,\"valOffset\":0,\"accValReq\":250000,\"accValOffset\":0,\"ruleInd\":\"V\",\"yrsInTier\":0,\"eliteOrPPSpercentage\":0,\"qualInterim\":\"N\",\"startDate\":\"01-01-1999\",\"endDate\":\"31-05-2018\",\"tierBonusFactor\":0,\"tierBonusValue\":0,\"interim\":\"N\"},{\"seqNo\":29,\"tierStatus\":\"T\",\"tierTypeInd\":\"PPS\",\"programCd\":\"KF\",\"hierarchy\":4,\"minPeriod\":0,\"qualProcID\":\"PU\",\"sectReq\":0.0,\"pointOffset\":0,\"sectorOffset\":0.0,\"accPtsReq\":0,\"accSectReq\":0.0,\"ptsReq\":0,\"accPtsOffset\":0,\"acctSectOffset\":0.0,\"active\":\"Y\",\"applQualInd\":null,\"applQualIndList\":null,\"ruleInfo\":null,\"addFlag\":null,\"deleteFlag\":null,\"updateFlag\":null,\"viewFlag\":null,\"allFlag\":null,\"classCode\":null,\"tierQuadPrd\":12,\"valReq\":50000,\"valOffset\":0,\"accValReq\":0,\"accValOffset\":0,\"ruleInd\":\"V\",\"yrsInTier\":0,\"eliteOrPPSpercentage\":0,\"qualInterim\":\"Y\",\"startDate\":\"01-06-2018\",\"endDate\":\"31-12-2099\",\"tierBonusFactor\":0,\"tierBonusValue\":0,\"interim\":\"N\"},{\"seqNo\":30,\"tierStatus\":\"T\",\"tierTypeInd\":\"PPS\",\"programCd\":\"KF\",\"hierarchy\":4,\"minPeriod\":0,\"qualProcID\":\"PU\",\"sectReq\":0.0,\"pointOffset\":0,\"sectorOffset\":0.0,\"accPtsReq\":0,\"accSectReq\":0.0,\"ptsReq\":0,\"accPtsOffset\":0,\"acctSectOffset\":0.0,\"active\":\"Y\",\"applQualInd\":null,\"applQualIndList\":null,\"ruleInfo\":null,\"addFlag\":null,\"deleteFlag\":null,\"updateFlag\":null,\"viewFlag\":null,\"allFlag\":null,\"classCode\":null,\"tierQuadPrd\":12,\"valReq\":50000,\"valOffset\":0,\"accValReq\":0,\"accValOffset\":0,\"ruleInd\":\"V\",\"yrsInTier\":0,\"eliteOrPPSpercentage\":0,\"qualInterim\":\"Y\",\"startDate\":\"01-06-2018\",\"endDate\":\"31-12-2099\",\"tierBonusFactor\":0,\"tierBonusValue\":0,\"interim\":\"N\"}]";
            ObjectMapper mapper = new ObjectMapper();
            mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
            List<Tierstat> tierstatList = mapper.readValue(response, new TypeReference<List<Tierstat>>(){});
            printSetterMethods(tierstatList);
            System.out.println(tierstatList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     private static Tierstat reflectionOnDataFromDb(Tierstat tierstat) throws InvocationTargetException, IllegalAccessException, ParseException {
        initializeMonthMapping();
        Method[] methods = tierstat.getClass().getDeclaredMethods();
        Tierstat generated = new Tierstat();
        Method[] methodsToInvoke = Tierstat.class.getDeclaredMethods();
        Map<String, Object> mapOfFieldNameAndValue = new HashMap<>();
        for (Method method : methods) {

            if (method.getName().startsWith("get")) {
                Object val = method.invoke(tierstat);
                String type = method.getReturnType().getTypeName();
                try {
                    if (type.equals("java.util.Date")) {
                        if (val == null) continue;
                    } else if (type.equals("java.lang.String") || type.equals("java.lang.Double") || type.equals("java.lang.Integer") || type.equals("java.lang.Long")) {
                        if (val == null) continue;
                    } else if (type.equals("double")) {
                        if ((double) val == 0.0) continue;
                    } else if (type.equals("float")) {
                        if ((float) val == 0.0f) continue;
                    } else if (type.equals("int") || type.equals("java.lang.Integer")) {
                        if ((int) val == 0) continue;
                    } else if (type.equals("java.lang.Long") || type.equals("long")) {
                        if ((long) val == 0) continue;
                    } else if (type.equals("java.sql.Timestamp")) {
                        if (val == null) continue;
                    } else if (type.equals("java.util.List")) {
                        continue;
                    }
                    mapOfFieldNameAndValue.put(generateSetterName(method.getName()), val);
                } catch (Exception e) {
                    System.out.println(e);
                }

            }

        }
        for (Method method : methodsToInvoke) {
            String name = method.getName();
            if (name.startsWith("set")) {
                Object val = mapOfFieldNameAndValue.get(name);
                if (val == null) continue;
                String toPrint = "";
                if (val.getClass().getTypeName().equals("java.lang.String")) {
                    toPrint = String.format("tierstat.%s(\"%s\");", name, val);
                } else if (val.getClass().getTypeName().equals("java.util.Date")) {
                    String date = generateDateString(val.toString());
                    toPrint = String.format("tierstat.%s(dateFormat.parse(\"%s\"));", name, date);
                } else if (val.getClass().getTypeName().equals("float") || val.getClass().getTypeName().equals("java.lang.Float")) {
                    toPrint = String.format("tierstat.%s(%sf);", name, val);
                } else if (val.getClass().getTypeName().equals("long") || val.getClass().getTypeName().equals("java.lang.Long")) {
                    toPrint = String.format("tierstat.%s(%sl);", name, val);
                } else if (val.getClass().getTypeName().equals("java.sql.Timestamp")) {
                    String date = generateTimestamp((Timestamp) val);
                    toPrint = String.format("tierstat.%s(new Timestamp(dateFormat.parse(\"%s\").getTime()));", name, date);
                } else {
                    toPrint = String.format("tierstat.%s(%s);", name, val);
                }
                method.invoke(generated, val);
                System.out.println(toPrint);
            }
        }
        return generated;
    }

    private static TierMileageSummary reflectionOnDataFromDb(TierMileageSummary tierstat) throws InvocationTargetException, IllegalAccessException, ParseException {
        initializeMonthMapping();
        Method[] methods = tierstat.getClass().getDeclaredMethods();
        TierMileageSummary generated = new TierMileageSummary();
        Method[] methodsToInvoke = TierMileageSummary.class.getDeclaredMethods();
        Map<String, Object> mapOfFieldNameAndValue = new HashMap<>();
        for (Method method : methods) {

            if (method.getName().startsWith("get")) {
                Object val = method.invoke(tierstat);
                String type = method.getReturnType().getTypeName();
                try {
                    if (type.equals("java.util.Date")) {
                        if (val == null) continue;
                    } else if (type.equals("java.lang.String") || type.equals("java.lang.Double") || type.equals("java.lang.Integer") || type.equals("java.lang.Long")) {
                        if (val == null) continue;
                    } else if (type.equals("double")) {
                        if ((double) val == 0.0) continue;
                    } else if (type.equals("float")) {
                        if ((float) val == 0.0f) continue;
                    } else if (type.equals("int") || type.equals("java.lang.Integer")) {
                        if ((int) val == 0) continue;
                    } else if (type.equals("java.lang.Long") || type.equals("long")) {
                        if ((long) val == 0) continue;
                    } else if (type.equals("java.sql.Timestamp")) {
                        if (val == null) continue;
                    } else if (type.equals("java.util.List")) {
                        continue;
                    }
                    mapOfFieldNameAndValue.put(generateSetterName(method.getName()), val);
                } catch (Exception e) {
                    System.out.println(e);
                }

            }

        }
        for (Method method : methodsToInvoke) {
            String name = method.getName();
            if (name.startsWith("set")) {
                Object val = mapOfFieldNameAndValue.get(name);
                if (val == null) continue;
                String toPrint = "";
                if (val.getClass().getTypeName().equals("java.lang.String")) {
                    toPrint = String.format("tierstat.%s(\"%s\");", name, val);
                } else if (val.getClass().getTypeName().equals("java.util.Date")) {
                    String date = generateDateString(val.toString());
                    toPrint = String.format("tierstat.%s(dateFormat.parse(\"%s\"));", name, date);
                } else if (val.getClass().getTypeName().equals("float") || val.getClass().getTypeName().equals("java.lang.Float")) {
                    toPrint = String.format("tierstat.%s(%sf);", name, val);
                } else if (val.getClass().getTypeName().equals("long") || val.getClass().getTypeName().equals("java.lang.Long")) {
                    toPrint = String.format("tierstat.%s(%sl);", name, val);
                } else if (val.getClass().getTypeName().equals("java.sql.Timestamp")) {
                    String date = generateTimestamp((Timestamp) val);
                    toPrint = String.format("tierstat.%s(new Timestamp(dateFormat.parse(\"%s\").getTime()));", name, date);
                } else {
                    toPrint = String.format("tierstat.%s(%s);", name, val);
                }
                method.invoke(generated, val);
                System.out.println(toPrint);
            }
        }
        return generated;
    }

    private static HisCusEliteQual reflectionOnDataFromDb(HisCusEliteQual tierstat) throws InvocationTargetException, IllegalAccessException, ParseException {
        initializeMonthMapping();
        Method[] methods = tierstat.getClass().getDeclaredMethods();
        HisCusEliteQual generated = new HisCusEliteQual();
        Method[] methodsToInvoke = HisCusEliteQual.class.getDeclaredMethods();
        Map<String, Object> mapOfFieldNameAndValue = new HashMap<>();
        for (Method method : methods) {

            if (method.getName().startsWith("get")) {
                Object val = method.invoke(tierstat);
                String type = method.getReturnType().getTypeName();
                try {
                    if (type.equals("java.util.Date")) {
                        if (val == null) continue;
                    } else if (type.equals("java.lang.String") || type.equals("java.lang.Double") || type.equals("java.lang.Integer") || type.equals("java.lang.Long")) {
                        if (val == null) continue;
                    } else if (type.equals("double")) {
                        if ((double) val == 0.0) continue;
                    } else if (type.equals("float")) {
                        if ((float) val == 0.0f) continue;
                    } else if (type.equals("int") || type.equals("java.lang.Integer")) {
                        if ((int) val == 0) continue;
                    } else if (type.equals("java.lang.Long") || type.equals("long")) {
                        if ((long) val == 0) continue;
                    } else if (type.equals("java.sql.Timestamp")) {
                        if (val == null) continue;
                    } else if (type.equals("java.util.List")) {
                        continue;
                    }
                    mapOfFieldNameAndValue.put(generateSetterName(method.getName()), val);
                } catch (Exception e) {
                    System.out.println(e);
                }

            }

        }
        for (Method method : methodsToInvoke) {
            String name = method.getName();
            if (name.startsWith("set")) {
                Object val = mapOfFieldNameAndValue.get(name);
                if (val == null) continue;
                String toPrint = "";
                if (val.getClass().getTypeName().equals("java.lang.String")) {
                    toPrint = String.format("tierstat.%s(\"%s\");", name, val);
                } else if (val.getClass().getTypeName().equals("java.util.Date")) {
                    String date = generateDateString(val.toString());
                    toPrint = String.format("tierstat.%s(dateFormat.parse(\"%s\"));", name, date);
                } else if (val.getClass().getTypeName().equals("float") || val.getClass().getTypeName().equals("java.lang.Float")) {
                    toPrint = String.format("tierstat.%s(%sf);", name, val);
                } else if (val.getClass().getTypeName().equals("long") || val.getClass().getTypeName().equals("java.lang.Long")) {
                    toPrint = String.format("tierstat.%s(%sl);", name, val);
                } else if (val.getClass().getTypeName().equals("java.sql.Timestamp")) {
                    String date = generateTimestamp((Timestamp) val);
                    toPrint = String.format("tierstat.%s(new Timestamp(dateFormat.parse(\"%s\").getTime()));", name, date);
                } else {
                    toPrint = String.format("tierstat.%s(%s);", name, val);
                }
                method.invoke(generated, val);
                System.out.println(toPrint);
            }
        }
        return generated;
    }

    private static CusPpsQual reflectionOnDataFromDb(CusPpsQual tierstat) throws InvocationTargetException, IllegalAccessException, ParseException {
        initializeMonthMapping();
        Method[] methods = tierstat.getClass().getDeclaredMethods();
        CusPpsQual generated = new CusPpsQual();
        Method[] methodsToInvoke = CusPpsQual.class.getDeclaredMethods();
        Map<String, Object> mapOfFieldNameAndValue = new HashMap<>();
        for (Method method : methods) {

            if (method.getName().startsWith("get")) {
                Object val = method.invoke(tierstat);
                String type = method.getReturnType().getTypeName();
                try {
                    if (type.equals("java.util.Date")) {
                        if (val == null) continue;
                    } else if (type.equals("java.lang.String") || type.equals("java.lang.Double") || type.equals("java.lang.Integer") || type.equals("java.lang.Long")) {
                        if (val == null) continue;
                    } else if (type.equals("double")) {
                        if ((double) val == 0.0) continue;
                    } else if (type.equals("float")) {
                        if ((float) val == 0.0f) continue;
                    } else if (type.equals("int") || type.equals("java.lang.Integer")) {
                        if ((int) val == 0) continue;
                    } else if (type.equals("java.lang.Long") || type.equals("long")) {
                        if ((long) val == 0) continue;
                    } else if (type.equals("java.sql.Timestamp")) {
                        if (val == null) continue;
                    } else if (type.equals("java.util.List")) {
                        continue;
                    }
                    mapOfFieldNameAndValue.put(generateSetterName(method.getName()), val);
                } catch (Exception e) {
                    System.out.println(e);
                }

            }

        }
        for (Method method : methodsToInvoke) {
            String name = method.getName();
            if (name.startsWith("set")) {
                Object val = mapOfFieldNameAndValue.get(name);
                if (val == null) continue;
                String toPrint = "";
                if (val.getClass().getTypeName().equals("java.lang.String")) {
                    toPrint = String.format("tierstat.%s(\"%s\");", name, val);
                } else if (val.getClass().getTypeName().equals("java.util.Date")) {
                    String date = generateDateString(val.toString());
                    toPrint = String.format("tierstat.%s(dateFormat.parse(\"%s\"));", name, date);
                } else if (val.getClass().getTypeName().equals("float") || val.getClass().getTypeName().equals("java.lang.Float")) {
                    toPrint = String.format("tierstat.%s(%sf);", name, val);
                } else if (val.getClass().getTypeName().equals("long") || val.getClass().getTypeName().equals("java.lang.Long")) {
                    toPrint = String.format("tierstat.%s(%sl);", name, val);
                } else if (val.getClass().getTypeName().equals("java.sql.Timestamp")) {
                    String date = generateTimestamp((Timestamp) val);
                    toPrint = String.format("tierstat.%s(new Timestamp(dateFormat.parse(\"%s\").getTime()));", name, date);
                } else {
                    toPrint = String.format("tierstat.%s(%s);", name, val);
                }
                method.invoke(generated, val);
                System.out.println(toPrint);
            }
        }
        return generated;
    }

    private static CustomerTier reflectionOnDataFromDb(CustomerTier tierstat) throws InvocationTargetException, IllegalAccessException, ParseException {
        initializeMonthMapping();
        Method[] methods = tierstat.getClass().getDeclaredMethods();
        CustomerTier generated = new CustomerTier();
        Method[] methodsToInvoke = CustomerTier.class.getDeclaredMethods();
        Map<String, Object> mapOfFieldNameAndValue = new HashMap<>();
        for (Method method : methods) {

            if (method.getName().startsWith("get")) {
                Object val = method.invoke(tierstat);
                String type = method.getReturnType().getTypeName();
                try {
                    if (type.equals("java.util.Date")) {
                        if (val == null) continue;
                    } else if (type.equals("java.lang.String") || type.equals("java.lang.Double") || type.equals("java.lang.Integer") || type.equals("java.lang.Long")) {
                        if (val == null) continue;
                    } else if (type.equals("double")) {
                        if ((double) val == 0.0) continue;
                    } else if (type.equals("float")) {
                        if ((float) val == 0.0f) continue;
                    } else if (type.equals("int") || type.equals("java.lang.Integer")) {
                        if ((int) val == 0) continue;
                    } else if (type.equals("java.lang.Long") || type.equals("long")) {
                        if ((long) val == 0) continue;
                    } else if (type.equals("java.sql.Timestamp")) {
                        if (val == null) continue;
                    } else if (type.equals("java.util.List")) {
                        continue;
                    }
                    mapOfFieldNameAndValue.put(generateSetterName(method.getName()), val);
                } catch (Exception e) {
                    System.out.println(e);
                }

            }

        }
        for (Method method : methodsToInvoke) {
            String name = method.getName();
            if (name.startsWith("set")) {
                Object val = mapOfFieldNameAndValue.get(name);
                if (val == null) continue;
                String toPrint = "";
                if (val.getClass().getTypeName().equals("java.lang.String")) {
                    toPrint = String.format("tierstat.%s(\"%s\");", name, val);
                } else if (val.getClass().getTypeName().equals("java.util.Date")) {
                    String date = generateDateString(val.toString());
                    toPrint = String.format("tierstat.%s(dateFormat.parse(\"%s\"));", name, date);
                } else if (val.getClass().getTypeName().equals("float") || val.getClass().getTypeName().equals("java.lang.Float")) {
                    toPrint = String.format("tierstat.%s(%sf);", name, val);
                } else if (val.getClass().getTypeName().equals("long") || val.getClass().getTypeName().equals("java.lang.Long")) {
                    toPrint = String.format("tierstat.%s(%sl);", name, val);
                } else if (val.getClass().getTypeName().equals("java.sql.Timestamp")) {
                    String date = generateTimestamp((Timestamp) val);
                    toPrint = String.format("tierstat.%s(new Timestamp(dateFormat.parse(\"%s\").getTime()));", name, date);
                } else {
                    toPrint = String.format("tierstat.%s(%s);", name, val);
                }
                method.invoke(generated, val);
                System.out.println(toPrint);
            }
        }
        return generated;
    }

    private static TierQual reflectionOnDataFromDb(TierQual tierstat) throws InvocationTargetException, IllegalAccessException, ParseException {
        initializeMonthMapping();
        Method[] methods = tierstat.getClass().getDeclaredMethods();
        TierQual generated = new TierQual();
        Method[] methodsToInvoke = TierQual.class.getDeclaredMethods();
        Map<String, Object> mapOfFieldNameAndValue = new HashMap<>();
        for (Method method : methods) {

            if (method.getName().startsWith("get")) {
                Object val = method.invoke(tierstat);
                String type = method.getReturnType().getTypeName();
                try {
                    if (type.equals("java.util.Date")) {
                        if (val == null) continue;
                    } else if (type.equals("java.lang.String") || type.equals("java.lang.Double") || type.equals("java.lang.Integer") || type.equals("java.lang.Long")) {
                        if (val == null) continue;
                    } else if (type.equals("double")) {
                        if ((double) val == 0.0) continue;
                    } else if (type.equals("float")) {
                        if ((float) val == 0.0f) continue;
                    } else if (type.equals("int") || type.equals("java.lang.Integer")) {
                        if ((int) val == 0) continue;
                    } else if (type.equals("java.lang.Long") || type.equals("long")) {
                        if ((long) val == 0) continue;
                    } else if (type.equals("java.sql.Timestamp")) {
                        if (val == null) continue;
                    } else if (type.equals("java.util.List")) {
                        continue;
                    }
                    mapOfFieldNameAndValue.put(generateSetterName(method.getName()), val);
                } catch (Exception e) {
                    System.out.println(e);
                }

            }

        }
        for (Method method : methodsToInvoke) {
            String name = method.getName();
            if (name.startsWith("set")) {
                Object val = mapOfFieldNameAndValue.get(name);
                if (val == null) continue;
                String toPrint = "";
                if (val.getClass().getTypeName().equals("java.lang.String")) {
                    toPrint = String.format("tierstat.%s(\"%s\");", name, val);
                } else if (val.getClass().getTypeName().equals("java.util.Date")) {
                    String date = generateDateString(val.toString());
                    toPrint = String.format("tierstat.%s(dateFormat.parse(\"%s\"));", name, date);
                } else if (val.getClass().getTypeName().equals("float") || val.getClass().getTypeName().equals("java.lang.Float")) {
                    toPrint = String.format("tierstat.%s(%sf);", name, val);
                } else if (val.getClass().getTypeName().equals("long") || val.getClass().getTypeName().equals("java.lang.Long")) {
                    toPrint = String.format("tierstat.%s(%sl);", name, val);
                } else if (val.getClass().getTypeName().equals("java.sql.Timestamp")) {
                    String date = generateTimestamp((Timestamp) val);
                    toPrint = String.format("tierstat.%s(new Timestamp(dateFormat.parse(\"%s\").getTime()));", name, date);
                } else {
                    toPrint = String.format("tierstat.%s(%s);", name, val);
                }
                method.invoke(generated, val);
                System.out.println(toPrint);
            }
        }
        return generated;
    }


    private static AccountStatusFunc reflectionOnDataFromDb(AccountStatusFunc tierstat) throws InvocationTargetException, IllegalAccessException, ParseException {
        initializeMonthMapping();
        Method[] methods = tierstat.getClass().getDeclaredMethods();
        AccountStatusFunc generated = new AccountStatusFunc();
        Method[] methodsToInvoke = AccountStatusFunc.class.getDeclaredMethods();
        Map<String, Object> mapOfFieldNameAndValue = new HashMap<>();
        for (Method method : methods) {

            if (method.getName().startsWith("get")) {
                Object val = method.invoke(tierstat);
                String type = method.getReturnType().getTypeName();
                try {
                    if (type.equals("java.util.Date")) {
                        if (val == null) continue;
                    } else if (type.equals("java.lang.String") || type.equals("java.lang.Double") || type.equals("java.lang.Integer") || type.equals("java.lang.Long")) {
                        if (val == null) continue;
                    } else if (type.equals("double")) {
                        if ((double) val == 0.0) continue;
                    } else if (type.equals("float")) {
                        if ((float) val == 0.0f) continue;
                    } else if (type.equals("int") || type.equals("java.lang.Integer")) {
                        if ((int) val == 0) continue;
                    } else if (type.equals("java.lang.Long") || type.equals("long")) {
                        if ((long) val == 0) continue;
                    } else if (type.equals("java.sql.Timestamp")) {
                        if (val == null) continue;
                    } else if (type.equals("java.util.List")) {
                        continue;
                    }
                    mapOfFieldNameAndValue.put(generateSetterName(method.getName()), val);
                } catch (Exception e) {
                    System.out.println(e);
                }

            }

        }
        for (Method method : methodsToInvoke) {
            String name = method.getName();
            if (name.startsWith("set")) {
                Object val = mapOfFieldNameAndValue.get(name);
                if (val == null) continue;
                String toPrint = "";
                if (val.getClass().getTypeName().equals("java.lang.String")) {
                    toPrint = String.format("tierstat.%s(\"%s\");", name, val);
                } else if (val.getClass().getTypeName().equals("java.util.Date")) {
                    String date = generateDateString(val.toString());
                    toPrint = String.format("tierstat.%s(dateFormat.parse(\"%s\"));", name, date);
                } else if (val.getClass().getTypeName().equals("float") || val.getClass().getTypeName().equals("java.lang.Float")) {
                    toPrint = String.format("tierstat.%s(%sf);", name, val);
                } else if (val.getClass().getTypeName().equals("long") || val.getClass().getTypeName().equals("java.lang.Long")) {
                    toPrint = String.format("tierstat.%s(%sl);", name, val);
                } else if (val.getClass().getTypeName().equals("java.sql.Timestamp")) {
                    String date = generateTimestamp((Timestamp) val);
                    toPrint = String.format("tierstat.%s(new Timestamp(dateFormat.parse(\"%s\").getTime()));", name, date);
                } else {
                    toPrint = String.format("tierstat.%s(%s);", name, val);
                }
                method.invoke(generated, val);
                System.out.println(toPrint);
            }
        }
        return generated;
    }

    private static GeneralSqlObject reflectionOnDataFromDb(GeneralSqlObject tierstat) throws InvocationTargetException, IllegalAccessException, ParseException {
        initializeMonthMapping();
        Method[] methods = tierstat.getClass().getDeclaredMethods();
        GeneralSqlObject generated = new GeneralSqlObject();
        Method[] methodsToInvoke = GeneralSqlObject.class.getDeclaredMethods();
        Map<String, Object> mapOfFieldNameAndValue = new HashMap<>();
        for (Method method : methods) {

            if (method.getName().startsWith("get")) {
                Object val = method.invoke(tierstat);
                String type = method.getReturnType().getTypeName();
                try {
                    if (type.equals("java.util.Date")) {
                        if (val == null) continue;
                    } else if (type.equals("java.lang.String") || type.equals("java.lang.Double") || type.equals("java.lang.Integer") || type.equals("java.lang.Long")) {
                        if (val == null) continue;
                    } else if (type.equals("double")) {
                        if ((double) val == 0.0) continue;
                    } else if (type.equals("float")) {
                        if ((float) val == 0.0f) continue;
                    } else if (type.equals("int") || type.equals("java.lang.Integer")) {
                        if ((int) val == 0) continue;
                    } else if (type.equals("java.lang.Long") || type.equals("long")) {
                        if ((long) val == 0) continue;
                    } else if (type.equals("java.sql.Timestamp")) {
                        if (val == null) continue;
                    } else if (type.equals("java.util.List")) {
                        continue;
                    }
                    mapOfFieldNameAndValue.put(generateSetterName(method.getName()), val);
                } catch (Exception e) {
                    System.out.println(e);
                }

            }

        }
        for (Method method : methodsToInvoke) {
            String name = method.getName();
            if (name.startsWith("set")) {
                Object val = mapOfFieldNameAndValue.get(name);
                if (val == null) continue;
                String toPrint = "";
                if (val.getClass().getTypeName().equals("java.lang.String")) {
                    toPrint = String.format("tierstat.%s(\"%s\");", name, val);
                } else if (val.getClass().getTypeName().equals("java.util.Date")) {
                    String date = generateDateString(val.toString());
                    toPrint = String.format("tierstat.%s(dateFormat.parse(\"%s\"));", name, date);
                } else if (val.getClass().getTypeName().equals("float") || val.getClass().getTypeName().equals("java.lang.Float")) {
                    toPrint = String.format("tierstat.%s(%sf);", name, val);
                } else if (val.getClass().getTypeName().equals("long") || val.getClass().getTypeName().equals("java.lang.Long")) {
                    toPrint = String.format("tierstat.%s(%sl);", name, val);
                } else if (val.getClass().getTypeName().equals("java.sql.Timestamp")) {
                    String date = generateTimestamp((Timestamp) val);
                    toPrint = String.format("tierstat.%s(new Timestamp(dateFormat.parse(\"%s\").getTime()));", name, date);
                } else {
                    toPrint = String.format("tierstat.%s(%s);", name, val);
                }
                method.invoke(generated, val);
                System.out.println(toPrint);
            }
        }
        return generated;
    }

    private static QualDetailsMonthly reflectionOnDataFromDb(QualDetailsMonthly tierstat) throws InvocationTargetException, IllegalAccessException, ParseException {
        initializeMonthMapping();
        Method[] methods = tierstat.getClass().getDeclaredMethods();
        QualDetailsMonthly generated = new QualDetailsMonthly();
        Method[] methodsToInvoke = QualDetailsMonthly.class.getDeclaredMethods();
        Map<String, Object> mapOfFieldNameAndValue = new HashMap<>();
        for (Method method : methods) {

            if (method.getName().startsWith("get")) {
                Object val = method.invoke(tierstat);
                String type = method.getReturnType().getTypeName();
                try {
                    if (type.equals("java.util.Date")) {
                        if (val == null) continue;
                    } else if (type.equals("java.lang.String") || type.equals("java.lang.Double") || type.equals("java.lang.Integer") || type.equals("java.lang.Long")) {
                        if (val == null) continue;
                    } else if (type.equals("double")) {
                        if ((double) val == 0.0) continue;
                    } else if (type.equals("float")) {
                        if ((float) val == 0.0f) continue;
                    } else if (type.equals("int") || type.equals("java.lang.Integer")) {
                        if ((int) val == 0) continue;
                    } else if (type.equals("java.lang.Long") || type.equals("long")) {
                        if ((long) val == 0) continue;
                    } else if (type.equals("java.sql.Timestamp")) {
                        if (val == null) continue;
                    } else if (type.equals("java.util.List")) {
                        continue;
                    }
                    mapOfFieldNameAndValue.put(generateSetterName(method.getName()), val);
                } catch (Exception e) {
                    System.out.println(e);
                }

            }

        }
        for (Method method : methodsToInvoke) {
            String name = method.getName();
            if (name.startsWith("set")) {
                Object val = mapOfFieldNameAndValue.get(name);
                if (val == null) continue;
                String toPrint = "";
                if (val.getClass().getTypeName().equals("java.lang.String")) {
                    toPrint = String.format("tierstat.%s(\"%s\");", name, val);
                } else if (val.getClass().getTypeName().equals("java.util.Date")) {
                    String date = generateDateString(val.toString());
                    toPrint = String.format("tierstat.%s(dateFormat.parse(\"%s\"));", name, date);
                } else if (val.getClass().getTypeName().equals("float") || val.getClass().getTypeName().equals("java.lang.Float")) {
                    toPrint = String.format("tierstat.%s(%sf);", name, val);
                } else if (val.getClass().getTypeName().equals("long") || val.getClass().getTypeName().equals("java.lang.Long")) {
                    toPrint = String.format("tierstat.%s(%sl);", name, val);
                } else if (val.getClass().getTypeName().equals("java.sql.Timestamp")) {
                    String date = generateTimestamp((Timestamp) val);
                    toPrint = String.format("tierstat.%s(new Timestamp(dateFormat.parse(\"%s\").getTime()));", name, date);
                } else {
                    toPrint = String.format("tierstat.%s(%s);", name, val);
                }
                method.invoke(generated, val);
                System.out.println(toPrint);
            }
        }
        return generated;
    }

    private static CusClubQual reflectionOnDataFromDb(CusClubQual tierstat) throws InvocationTargetException, IllegalAccessException, ParseException {
        initializeMonthMapping();
        Method[] methods = tierstat.getClass().getDeclaredMethods();
        CusClubQual generated = new CusClubQual();
        Method[] methodsToInvoke = CusClubQual.class.getDeclaredMethods();
        Map<String, Object> mapOfFieldNameAndValue = new HashMap<>();
        for (Method method : methods) {

            if (method.getName().startsWith("get")) {
                Object val = method.invoke(tierstat);
                String type = method.getReturnType().getTypeName();
                try {
                    if (type.equals("java.util.Date")) {
                        if (val == null) continue;
                    } else if (type.equals("java.lang.String") || type.equals("java.lang.Double") || type.equals("java.lang.Integer") || type.equals("java.lang.Long")) {
                        if (val == null) continue;
                    } else if (type.equals("double")) {
                        if ((double) val == 0.0) continue;
                    } else if (type.equals("float")) {
                        if ((float) val == 0.0f) continue;
                    } else if (type.equals("int") || type.equals("java.lang.Integer")) {
                        if ((int) val == 0) continue;
                    } else if (type.equals("java.lang.Long") || type.equals("long")) {
                        if ((long) val == 0) continue;
                    } else if (type.equals("java.sql.Timestamp")) {
                        if (val == null) continue;
                    } else if (type.equals("java.util.List")) {
                        continue;
                    }
                    mapOfFieldNameAndValue.put(generateSetterName(method.getName()), val);
                } catch (Exception e) {
                    System.out.println(e);
                }

            }

        }
        for (Method method : methodsToInvoke) {
            String name = method.getName();
            if (name.startsWith("set")) {
                Object val = mapOfFieldNameAndValue.get(name);
                if (val == null) continue;
                String toPrint = "";
                if (val.getClass().getTypeName().equals("java.lang.String")) {
                    toPrint = String.format("tierstat.%s(\"%s\");", name, val);
                } else if (val.getClass().getTypeName().equals("java.util.Date")) {
                    String date = generateDateString(val.toString());
                    toPrint = String.format("tierstat.%s(dateFormat.parse(\"%s\"));", name, date);
                } else if (val.getClass().getTypeName().equals("float") || val.getClass().getTypeName().equals("java.lang.Float")) {
                    toPrint = String.format("tierstat.%s(%sf);", name, val);
                } else if (val.getClass().getTypeName().equals("long") || val.getClass().getTypeName().equals("java.lang.Long")) {
                    toPrint = String.format("tierstat.%s(%sl);", name, val);
                } else if (val.getClass().getTypeName().equals("java.sql.Timestamp")) {
                    String date = generateTimestamp((Timestamp) val);
                    toPrint = String.format("tierstat.%s(new Timestamp(dateFormat.parse(\"%s\").getTime()));", name, date);
                } else {
                    toPrint = String.format("tierstat.%s(%s);", name, val);
                }
                method.invoke(generated, val);
                System.out.println(toPrint);
            }
        }
        return generated;
    }

    private static TransReserveVal reflectionOnDataFromDb(TransReserveVal tierstat) throws InvocationTargetException, IllegalAccessException, ParseException {
        initializeMonthMapping();
        Method[] methods = tierstat.getClass().getDeclaredMethods();
        TransReserveVal generated = new TransReserveVal();
        Method[] methodsToInvoke = TransReserveVal.class.getDeclaredMethods();
        Map<String, Object> mapOfFieldNameAndValue = new HashMap<>();
        for (Method method : methods) {

            if (method.getName().startsWith("get")) {
                Object val = method.invoke(tierstat);
                String type = method.getReturnType().getTypeName();
                try {
                    if (type.equals("java.util.Date")) {
                        if (val == null) continue;
                    } else if (type.equals("java.lang.String") || type.equals("java.lang.Double") || type.equals("java.lang.Integer") || type.equals("java.lang.Long")) {
                        if (val == null) continue;
                    } else if (type.equals("double")) {
                        if ((double) val == 0.0) continue;
                    } else if (type.equals("float")) {
                        if ((float) val == 0.0f) continue;
                    } else if (type.equals("int") || type.equals("java.lang.Integer")) {
                        if ((int) val == 0) continue;
                    } else if (type.equals("java.lang.Long") || type.equals("long")) {
                        if ((long) val == 0) continue;
                    } else if (type.equals("java.sql.Timestamp")) {
                        if (val == null) continue;
                    } else if (type.equals("java.util.List")) {
                        continue;
                    }
                    mapOfFieldNameAndValue.put(generateSetterName(method.getName()), val);
                } catch (Exception e) {
                    System.out.println(e);
                }

            }

        }
        for (Method method : methodsToInvoke) {
            String name = method.getName();
            if (name.startsWith("set")) {
                Object val = mapOfFieldNameAndValue.get(name);
                if (val == null) continue;
                String toPrint = "";
                if (val.getClass().getTypeName().equals("java.lang.String")) {
                    toPrint = String.format("tierstat.%s(\"%s\");", name, val);
                } else if (val.getClass().getTypeName().equals("java.util.Date")) {
                    String date = generateDateString(val.toString());
                    toPrint = String.format("tierstat.%s(dateFormat.parse(\"%s\"));", name, date);
                } else if (val.getClass().getTypeName().equals("float") || val.getClass().getTypeName().equals("java.lang.Float")) {
                    toPrint = String.format("tierstat.%s(%sf);", name, val);
                } else if (val.getClass().getTypeName().equals("long") || val.getClass().getTypeName().equals("java.lang.Long")) {
                    toPrint = String.format("tierstat.%s(%sl);", name, val);
                } else if (val.getClass().getTypeName().equals("java.sql.Timestamp")) {
                    String date = generateTimestamp((Timestamp) val);
                    toPrint = String.format("tierstat.%s(new Timestamp(dateFormat.parse(\"%s\").getTime()));", name, date);
                } else {
                    toPrint = String.format("tierstat.%s(%s);", name, val);
                }
                method.invoke(generated, val);
                System.out.println(toPrint);
            }
        }
        return generated;
    }


    private static void printSetterMethods(List dataFromDb) throws ParseException, InvocationTargetException, IllegalAccessException {
        List<Object> generatedList = new ArrayList<>();
        System.out.println("try{");
        System.out.println("List<Tierstat> generatedList = new ArrayList<>();");
        System.out.println("Tierstat tierstat = null;");
        System.out.println("SimpleDateFormat dateFormat = new SimpleDateFormat(\"yyyy-MM-dd\");");
//        System.out.println("tierstat = new Tierstat();");
        // print setter code
        for (Object tierstat1 : dataFromDb) {
            System.out.println("tierstat = new Tierstat();");
            generatedList.add(bridgingMethod(tierstat1));
            System.out.println("generatedList.add(tierstat);");
        }
        System.out.println("return generatedList;");
        System.out.println("} catch(Exception e) {");
        System.out.println("e.printStackTrace();\n}\nreturn null;");
        System.out.println(generatedList.size());
    }
    private static String generateSetterName(String getter) {
        return "set" + getter.substring(3);
    }

    private static Map<String, String> monthMapper = new HashMap<>();

    private static String generateDateString(String dateStr) throws ParseException {
        String[] dateArr = dateStr.split(" ");
        String s = String.format(String.format("%s-%s-%s", dateArr[5], monthMapper.get(dateArr[1]), dateArr[2]));
        return s;
    }

    private static String generateTimestamp(Timestamp timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(timestamp);
        return dateString;
    }


    private static void initializeMonthMapping() {
        monthMapper.put("Jan", "01");
        monthMapper.put("Feb", "02");
        monthMapper.put("Mar", "03");
        monthMapper.put("Apr", "04");
        monthMapper.put("May", "05");
        monthMapper.put("Jun", "06");
        monthMapper.put("Jul", "07");
        monthMapper.put("Aug", "08");
        monthMapper.put("Sep", "09");
        monthMapper.put("Oct", "10");
        monthMapper.put("Nov", "11");
        monthMapper.put("Dec", "12");
    }
    private static Object bridgingMethod(Object o) throws ParseException, InvocationTargetException, IllegalAccessException {
        if(Tierstat.class.isInstance(o)){
            return reflectionOnDataFromDb((Tierstat) o);
        }
        else if (TierMileageSummary.class.isInstance(o)){
            return reflectionOnDataFromDb((TierMileageSummary) o);
        }
        else if (HisCusEliteQual.class.isInstance(o)){
            return reflectionOnDataFromDb((HisCusEliteQual) o);
        }
        else if (CusPpsQual.class.isInstance(o)){
            return reflectionOnDataFromDb((CusPpsQual) o);
        }
        else if (CustomerTier.class.isInstance(o)){
            return reflectionOnDataFromDb((CustomerTier) o);
        }
        else if (TierQual.class.isInstance(o)){
            return reflectionOnDataFromDb((TierQual) o);
        }
        else if (AccountStatusFunc.class.isInstance(o)){
            return reflectionOnDataFromDb((AccountStatusFunc) o);
        }
        else if (GeneralSqlObject.class.isInstance(o)){
            return reflectionOnDataFromDb((GeneralSqlObject) o);
        }
        else if (QualDetailsMonthly.class.isInstance(o)){
            return reflectionOnDataFromDb((QualDetailsMonthly) o);
        }
        else if (CusClubQual.class.isInstance(o)){
            return reflectionOnDataFromDb((CusClubQual) o);
        }
        else if (TransReserveVal.class.isInstance(o)){
            return reflectionOnDataFromDb((TransReserveVal) o);
        }
        return null;
    }



}

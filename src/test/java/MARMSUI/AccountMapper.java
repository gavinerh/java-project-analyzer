package MARMSUI;

import MARMSUI.migration.model.CusAccount;
import MARMSUI.migration.model.CustomerAccountVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AccountMapper {
    @Mapping(source = "cusId", target = "cusId")
    @Mapping(source = "acctStatusInd", target = "acctStatusInd")
    CustomerAccountVo convert(CusAccount cusAccount);
}

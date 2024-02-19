package MARMSUI.SpecialisedSqlMappingToVo.model;

import com.fasterxml.jackson.annotation.JsonInclude;

public class RdpnItems {
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private long itemNum;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String itemName;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String itemCategory;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String itemBrand;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private long itemQty;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private long itemValue;

    public RdpnItems(long itemNum, String itemName, String itemCategory, String itemBrand, long itemQty, long itemValue) {
        this.itemNum = itemNum;
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.itemBrand = itemBrand;
        this.itemQty = itemQty;
        this.itemValue = itemValue;
    }

    public long getItemNum() {
        return itemNum;
    }

    public void setItemNum(long itemNum) {
        this.itemNum = itemNum;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getItemBrand() {
        return itemBrand;
    }

    public void setItemBrand(String itemBrand) {
        this.itemBrand = itemBrand;
    }

    public long getItemQty() {
        return itemQty;
    }

    public void setItemQty(long itemQty) {
        this.itemQty = itemQty;
    }

    public long getItemValue() {
        return itemValue;
    }

    public void setItemValue(long itemValue) {
        this.itemValue = itemValue;
    }
}

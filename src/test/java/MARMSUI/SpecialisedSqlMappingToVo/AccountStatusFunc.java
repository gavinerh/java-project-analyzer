package MARMSUI.SpecialisedSqlMappingToVo;
import java.sql.Timestamp;

import java.util.Date;




/**
 * Description of the Class
 *
 * @author    Jimmy_Tong
 * @version   1.0
 * @since
 */
public class AccountStatusFunc implements java.io.Serializable
{

    private String funcType = null;
    private String accountStatus = null;
    private String eligibleFlg = null;
    private String programCode = null;
    private String retroCreditInd = null;
    private String prtCd = null;
    private String accountRsnCd = null;


    public void setFuncType( String funcType )
    {
        this.funcType = funcType;
    }

    /**
     * Gets the funcType attribute of the AccountStatusFunc object
     *
     * @return   The funcType value
     */
    public String getFuncType()
    {
        return funcType;
    }

    /**
     * Sets the accountStatus attribute of the AccountStatusFunc object
     *
     * @param accountStatus  The new accountStatus value
     */
    public void setAccountStatus( String accountStatus )
    {
        this.accountStatus = accountStatus;
    }

    /**
     * Gets the accountStatus attribute of the AccountStatusFunc object
     *
     * @return   The accountStatus value
     */
    public String getAccountStatus()
    {
        return accountStatus;
    }

    /**
     * Sets the eligibleFlg attribute of the AccountStatusFunc object
     *
     * @param eligibleFlg  The new eligibleFlg value
     */
    public void setEligibleFlg( String eligibleFlg )
    {
        this.eligibleFlg = eligibleFlg;
    }

    /**
     * Gets the eligibleFlg attribute of the AccountStatusFunc object
     *
     * @return   The eligibleFlg value
     */
    public String getEligibleFlg()
    {
        return eligibleFlg;
    }

    /**
     * Sets the programCode attribute of the AccountStatusFunc object
     *
     * @param programCode  The new programCode value
     */
    public void setProgramCode( String programCode )
    {
        this.programCode = programCode;
    }

    /**
     * Gets the programCode attribute of the AccountStatusFunc object
     *
     * @return   The programCode value
     */
    public String getProgramCode()
    {
        return programCode;
    }

    /**
     * Sets the retroCreditInd attribute of the AccountStatusFunc object
     *
     * @param retroCreditInd  The new retroCreditInd value
     */
    public void setRetroCreditInd( String retroCreditInd )
    {
        this.retroCreditInd = retroCreditInd;
    }

    /**
     * Gets the retroCreditInd attribute of the AccountStatusFunc object
     *
     * @return   The retroCreditInd value
     */
    public String getRetroCreditInd()
    {
        return retroCreditInd;
    }


    /**
     * Sets the accountRsnCd attribute of the AccountStatusFunc object
     *
     * @param accountRsnCd  The new accountRsnCd value
     */
    public void setAccountRsnCd( String accountRsnCd )
    {
        this.accountRsnCd = accountRsnCd;
    }

    /**
     * Gets the accountRsnCd attribute of the AccountStatusFunc object
     *
     * @return   The accountRsnCd value
     */
    public String getAccountRsnCd()
    {
        return accountRsnCd;
    }

    public String getPrtCd() {
        return prtCd;
    }

    public void setPrtCd(String prtCd) {
        this.prtCd = prtCd;
    }
}


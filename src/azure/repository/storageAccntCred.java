/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package azure.repository;

/**
 *
 * @author sadeqzad
 */
public class storageAccntCred {

    public storageAccntCred() {}
    
    String accountName = "portalvhdsjv13w4pk1gh6j";
    String accountKey = "QhgOPUPNUX2ePs0Wy2Ln+qUDp/AFEMCwEiT7iHfu1KBvSNOj8/AWegyEYreNeIYflpBSMGupO8pTNGcSNUiCmA==";
    
    public String getAccountName() {
        return this.accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    
    public String getAccountKey() {
        return this.accountKey;
    }

    public void setAccountKey(String accountKey) {
        this.accountKey = accountKey;
    }
}

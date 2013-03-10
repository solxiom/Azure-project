/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package azure.repository;

import com.microsoft.windowsazure.services.core.storage.CloudStorageAccount;

/**
 *
 * @author sadeqzad
 */
public class AccountConnector {

    private String connStr;
    private CloudStorageAccount account;

    public AccountConnector(AccntCred accntCred) {       
        String accountName = accntCred.getAccountName();
        String accountKey = accntCred.getAccountKey();
        connStr = "DefaultEndpointsProtocol=http;"
                + "AccountName=" + accountName + ";"
                + "AccountKey=" + accountKey;


    }

//            CloudStorageAccount account = CloudStorageAccount.parse(connStr);
    public CloudStorageAccount getAccount() {
        try {
            return this.account.parse(connStr);
        } catch (Exception ex) {
        }
        System.out.println("Problem in creating connection");
        return null;
    }
}

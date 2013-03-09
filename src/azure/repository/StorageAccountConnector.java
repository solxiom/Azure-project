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
public class StorageAccountConnector {

    private String connStr;
    private CloudStorageAccount account;

    public StorageAccountConnector(storageAccntCred accntCred) {       
        String accountName = accntCred.getAccountName();
        String accountKey = accntCred.getAccountKey();
        String connStr = "DefaultEndpointsProtocol=http;"
                + "AccountName=" + accountName + ";"
                + "AccountKey=" + accountKey;


    }

//            CloudStorageAccount account = CloudStorageAccount.parse(connStr);
    public CloudStorageAccount getAccount() {
        try {
            return this.account.parse(connStr);
        } catch (Exception ex) {
        }
        return null;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package azure.repository;

import com.microsoft.windowsazure.services.core.storage.*;
import com.microsoft.windowsazure.services.table.client.*;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;

/**
 *
 * @author sadeqzad
 */
public class retreiveEntry {
    private static AccntCred storageAccntCred;
    
    
   public static void main(String[] args) throws StorageException, InvalidKeyException, URISyntaxException 
    {
        try
        {
            AccountConnector connector = new AccountConnector(storageAccntCred);
            CloudTableClient tableClient = connector.getAccount().createCloudTableClient();
            
            TableOperation retrieved = 
            TableOperation.retrieve("03", "03", descEntity.class);

            // Submit the operation to the table service and get the specific entity.
            descEntity specificEntity =
            tableClient.execute("description", retrieved).getResultAsType();
            
            System.out.println(specificEntity.date);
            System.out.println(specificEntity.desc);
            System.out.println(specificEntity.path);
    
        }    
        catch (StorageException storageExcep)
        {
            System.out.println("StorageException encountered: ");
            System.out.println(storageExcep.getMessage());
            System.exit(-1);
        }
       
        
    } 
}

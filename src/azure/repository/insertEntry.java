/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package azure.repository;

import azure.domain.Album;
import com.microsoft.windowsazure.services.core.storage.*;
import com.microsoft.windowsazure.services.table.client.*;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.UUID;
/**
 *
 * @author sadeqzad
 */
public class insertEntry {

    public static void main(String[] args) throws StorageException, InvalidKeyException, URISyntaxException 
    {
        try
        {
            storageAccntCred accntCred = new storageAccntCred();
            String accountName = accntCred.getAccountName();
            String accountKey = accntCred.getAccountKey();
            UUID uuid = UUID.randomUUID();
            // String counter;
            
            
            
            String connStr =
            "DefaultEndpointsProtocol=http;" + 
            "AccountName=" + accountName + ";" +
            "AccountKey=" + accountKey;
            
            CloudStorageAccount account = CloudStorageAccount.parse(connStr);
            CloudTableClient tableClient = account.createCloudTableClient();
    
            CloudTable table = tableClient.getTableReference("photos");
            table.createIfNotExist();
            
            Album album = new Album(uuid.toString());
            //album.setUniqueKey(uuid);
            album.setTitle("01");
            album.setDescription("01");
            String photos = "01.jpg";
            album.addImagePath(photos);
            String tags = "gfgfd";
            album.addTags(tags);

            TableOperation insertDesc1 = TableOperation.insert(album);

            tableClient.execute("photos", insertDesc1);
            System.out.println(uuid);
        }

        catch (URISyntaxException uriSyntaxException)
        {
            System.out.print("URISyntaxException encountered: ");
            System.out.println(uriSyntaxException.getMessage());
            System.exit(-1);
        }
        catch (StorageException storageExcep)
        {
            System.out.println("StorageException encountered: ");
            System.out.println(storageExcep.getMessage());
            System.out.println(storageExcep.getCause());
            System.out.println(storageExcep.getErrorCode());
            System.out.println(storageExcep.getExtendedErrorInformation());
            System.exit(-1);
        }
        catch (InvalidKeyException keyExcep)
        {
            System.out.println("InvalidKeyException encountered: ");
            System.out.println(keyExcep.getMessage());
            System.exit(-1);
        }
        
    }
}

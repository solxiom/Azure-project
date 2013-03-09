/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package azure.repository;

import azure.domain.Album;
import com.microsoft.windowsazure.services.core.storage.CloudStorageAccount;
import com.microsoft.windowsazure.services.core.storage.StorageException;
import com.microsoft.windowsazure.services.table.client.CloudTable;
import com.microsoft.windowsazure.services.table.client.CloudTableClient;
import com.microsoft.windowsazure.services.table.client.TableOperation;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.List;

/**
 *
 * @author kavansol
 */
public class DataRepoImpl implements DataRepo {

//    AccountConnector connector;

    @Override
    public String savePhoto(File file) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removePhoto(String path) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertAlbum(/*Album album*/) {
        this.insertEntry(/*album*/);
    }

    @Override
    public void removeAlbum(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Album> listAlbums() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void insertEntry(/* Album album*/) {
        try {
            
            AccntCred accntCred = new AccntCred();
            AccountConnector connector = new AccountConnector(accntCred);
           
            CloudStorageAccount account = connector.getAccount();
            CloudTableClient tableClient = account.createCloudTableClient();

            CloudTable table = tableClient.getTableReference("description");
            table.createIfNotExist();
//            UUID uuid = UUID.randomUUID();
//            Album albumEntry = new Album(uuid.toString());
//            albumEntry.setDescription(album.getDescription());
//            albumEntry.setTitle(album.getTitle());
//            albumEntry.setImage_paths(arrayToString(album.getImagePaths()));
//            albumEntry.setTags(arrayToString(album.getTags()));         
//           
            descEntity row1 = new descEntity("002");
            row1.setDesc("blah blah blah 2");
            row1.setDate("2010-10-002");
            
            TableOperation insertDesc1 = TableOperation.insert(row1);
            tableClient.execute("description", insertDesc1);
        } catch (StorageException storageException) {
            System.out.print("StorageException encountered: ");
            System.out.println(storageException.getMessage());
            System.exit(-1);
        } catch (URISyntaxException uriSyntaxException) {
            System.out.print("URISyntaxException encountered: ");
            System.out.println(uriSyntaxException.getMessage());
            System.exit(-1);
        } catch (Exception e) {
            System.out.print("Exception encountered: ");
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }

    private String arrayToString(String[] ar) {
        String result = "";
        for (String s : ar) {
            result += s + ",";
        }
        return result;
    }

    public static void main(String[] args) {

//        UUID uuid = UUID.randomUUID();

//        Album album1 = new Album(uuid.toString());
//        album1.setDescription("test 1");
//        album1.setTitle("title 1");
//        album1.setImage_paths("dasdasd.jpg, dasdasd.jpg, dasdasdas.jpg");
//        album1.setTags("theme 1");
        DataRepoImpl dataRepo = new DataRepoImpl();
        dataRepo.insertAlbum();
    }
}

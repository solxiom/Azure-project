/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package azure.repository;

import azure.domain.Album;
import com.microsoft.windowsazure.services.table.client.CloudTable;
import com.microsoft.windowsazure.services.table.client.CloudTableClient;
import com.microsoft.windowsazure.services.table.client.TableOperation;
import java.io.File;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author kavansol
 */
public class DataRepoImpl implements DataRepo {

    StorageAccountConnector connector;
    
    
    @Override
    public String savePhoto(File file) {
        //this.saveFile();
        throw new UnsupportedOperationException("Not supported yet.");
        }

    @Override
    public void removePhoto(String path) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    @Override
    public void insertAlbum(Album album) {
        this.insertEntry(album);
    }

    @Override
    public void removeAlbum(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Album> listAlbums() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void insertEntry(Album album){
        try
        {       
            CloudTableClient tableClient = connector.getAccount().createCloudTableClient();    
            CloudTable table = tableClient.getTableReference("albumDescription");
            table.createIfNotExist();          
            UUID uuid = UUID.randomUUID();
            Album albumEntry = new Album(uuid.toString());
            albumEntry.setDescription(album.getDescription());
            albumEntry.setTitle(album.getTitle());
            albumEntry.setImage_paths(arrayToString(album.getImagePaths()));
            albumEntry.setTags(arrayToString(album.getTags()));         
           
            
            TableOperation insertDesc1 = TableOperation.insert(album);
            tableClient.execute("albumDescription", insertDesc1);
        }catch (Exception ex)
        {
            System.out.print("Exception encountered: ");
            System.out.println(ex.getMessage());
        }
    } 
    private String arrayToString(String[] ar){
        String result ="";
        for(String s: ar){
            result +=s+",";
        }
        return result;
    }
    
    public static void main(String[] args) 
    {
        UUID uuid = UUID.randomUUID();
        Album album1 = new Album(uuid.toString());
        album1.setDescription("test 1");
        album1.setTitle("title 1");
        album1.setImage_paths("dasdasd.jpg, dasdasd.jpg, dasdasdas.jpg");
        album1.setTags("theme 1");
        DataRepoImpl dataRepo = new DataRepoImpl();
        dataRepo.insertAlbum(album1);
    }
    
    
    
}

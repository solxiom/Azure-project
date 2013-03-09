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
//            Album albumEntry = new Album(uuid.toString());
//            //album.setUniqueKey(uuid);
//            albumEntry.setDescription(album.getDescription());
//            albumEntry.setTitle(album.getTitle());
//            String[] images = album.getImages();
//            albumEntry.setImages(images);
//            String[] tags = album.getTags();
//            albumEntry.setTags(tags);
            
            TableOperation insertDesc1 = TableOperation.insert(album);
            tableClient.execute("albumDescription", insertDesc1);
        }catch (Exception ex)
        {
            System.out.print("URISyntaxException encountered: ");
            System.out.println(ex.getMessage());
            System.exit(-1);
        }
    } 
    
}

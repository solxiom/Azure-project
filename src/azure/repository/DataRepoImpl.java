/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package azure.repository;

import azure.domain.Album;
import com.microsoft.windowsazure.services.blob.client.BlobContainerPermissions;
import com.microsoft.windowsazure.services.blob.client.BlobContainerPublicAccessType;
import com.microsoft.windowsazure.services.blob.client.CloudBlobClient;
import com.microsoft.windowsazure.services.blob.client.CloudBlobContainer;
import com.microsoft.windowsazure.services.blob.client.CloudBlockBlob;
import com.microsoft.windowsazure.services.blob.client.ListBlobItem;
import com.microsoft.windowsazure.services.core.storage.CloudStorageAccount;
import com.microsoft.windowsazure.services.core.storage.StorageException;
import com.microsoft.windowsazure.services.table.client.CloudTable;
import com.microsoft.windowsazure.services.table.client.CloudTableClient;
import com.microsoft.windowsazure.services.table.client.TableOperation;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author kavansol
 */
public class DataRepoImpl implements DataRepo {

//    AccountConnector connector;
    String containerName;
    String blobName;
    CloudBlobClient serviceClient;
    CloudBlobContainer container;
    CloudBlockBlob blob;
    @Override
    public String savePhoto(File file) {
        //this.saveFile();
        
        try {
        return this.savePhotoIml(file);
        } 
        catch (Exception e)
        {
            return "";
        }
        }
        

    @Override
    public void removePhoto(String path) {
        try{
        this.removePhotoImp(path);
        }
        catch (Exception e)
        {
        }
        
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

    private void insertEntry(Album album) {
        try {
            
            AccntCred accntCred = new AccntCred();
            AccountConnector connector = new AccountConnector(accntCred);
           
            CloudStorageAccount account = connector.getAccount();
            CloudTableClient tableClient = account.createCloudTableClient();

            CloudTable table = tableClient.getTableReference("album2");
            table.createIfNotExist();
            
//            album.setDescription(album.getDescription());
//            album.setTitle(album.getTitle());
//            albumEntry.setImage_paths(arrayToString(album.getImagePaths()));
//            albumEntry.setTags(arrayToString(album.getTags()));         
//           
//            descEntity row1 = new descEntity("002");
//            row1.setDesc("blah blah blah 2");
//            row1.setDate("2010-10-002");
//            
            TableOperation insertDesc1 = TableOperation.insert(album);
            tableClient.execute("album2", insertDesc1);
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
        UUID uuid = UUID.randomUUID();
        Album sampleAlbum = new Album(uuid.toString());
        sampleAlbum.setDescription("description 1");
        sampleAlbum.setTitle("Title 1");
        dataRepo.insertAlbum(sampleAlbum);
        System.out.println("Success: The album added to the table album2 ..." + "UUID (row key): " + uuid.toString());
    }
   
        private String savePhotoIml(File file) throws StorageException, URISyntaxException, IOException{
        try{
 
            createStorageAccount();
            container = serviceClient.getContainerReference("album");
            container.createIfNotExist();
            setContainerPermission();
        
            String blockBlobReference= "fileName";
            String filePath= file.getAbsolutePath();
            blob = container.getBlockBlobReference(blockBlobReference);
            File fileReference = new File (filePath);
            blob.upload(new FileInputStream(fileReference), fileReference.length());

        String savedBlobPath= blob.getUri().toString();
        return savedBlobPath;
        }
         catch (FileNotFoundException fileNotFoundException)
        {
            System.out.print("FileNotFoundException encountered: ");
            System.out.println(fileNotFoundException.getMessage());
            return "";
        }
        catch (StorageException storageException)
        {
            System.out.print("StorageException encountered: ");
            System.out.println(storageException.getMessage());
            return "";
        }
        catch (URISyntaxException uriSyntaxException)
        {
            System.out.print("URISyntaxException encountered: ");
            System.out.println(uriSyntaxException.getMessage());
            return "";
        }
        catch (Exception e)
        {
            System.out.print("Exception encountered: ");
            System.out.println(e.getMessage());
            return "";
        }
    }
    private void removePhotoImp(String path) throws StorageException, URISyntaxException, IOException{
        
            splitter(path);
            createStorageAccount();
            container = serviceClient.getContainerReference(containerName);
            blob = container.getBlockBlobReference(blobName);
            blob.delete();
    }
    private void splitter(String path){
        
            String[] temp = path.split(".net/");
            String[] temp2 = temp[1].split("/");
            containerName = temp2[0];
            blobName = temp2[1];     
    }
    private void createStorageAccount(){
        
            AccntCred accntCred = new AccntCred();
            AccountConnector connector = new AccountConnector(accntCred);
            CloudStorageAccount account = connector.getAccount();
            serviceClient = account.createCloudBlobClient();
            
    }
    private void setContainerPermission()throws StorageException{
            BlobContainerPermissions containerPermissions = new BlobContainerPermissions();
            containerPermissions.setPublicAccess(BlobContainerPublicAccessType.CONTAINER);
            container.uploadPermissions(containerPermissions);
    }
}

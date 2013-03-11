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
import com.microsoft.windowsazure.services.core.storage.CloudStorageAccount;
import com.microsoft.windowsazure.services.core.storage.StorageException;
import com.microsoft.windowsazure.services.table.client.CloudTable;
import com.microsoft.windowsazure.services.table.client.CloudTableClient;
import com.microsoft.windowsazure.services.table.client.TableConstants;
import com.microsoft.windowsazure.services.table.client.TableOperation;
import com.microsoft.windowsazure.services.table.client.TableQuery;
import com.microsoft.windowsazure.services.table.client.TableQuery.QueryComparisons;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.LinkedList;
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
    String tableName = "last";
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
        try {
        this.insertEntry(album);
        }
        catch (StorageException e) {
            System.out.print("Exception encountered:\n");
            System.out.println("Exception class: " + e.getClass() + "\nException message: " + e.getMessage() + "\nStack trace: " + e.getStackTrace());
        }
        catch (Exception e) {
            System.out.print("Exception encountered:\n");
            System.out.println("Exception class: " + e.getClass() + "\nException message: " + e.getMessage() + "\nStack trace: " + e.getStackTrace());
        }
    }


    private void insertEntry(Album album) throws StorageException, Exception {
        CloudTableClient tableClient = getTableClient();
        CloudTable table = tableClient.getTableReference(this.tableName);
        table.createIfNotExist();
        TableOperation insertAlbum1 = TableOperation.insert(album);
        tableClient.execute(this.tableName, insertAlbum1);
    }
    
    public Album getRandomAlbum() {
        UUID uuid = UUID.randomUUID();
        Album sampleAlbum = new Album(uuid.toString());
        sampleAlbum.setDescription("description 2");
        sampleAlbum.setTitle("Title 2");
        sampleAlbum.setMail("Javad@Sadeqzadeh.com");
        sampleAlbum.setImage_paths("tes,tPath,ghhgfh,dsff");
        sampleAlbum.setTags("Natu,re,gh,tyhgh");
        return sampleAlbum;
    }
    

    
    
    public Album retrieveAlbum(String rowKey, String tableName) {
        return getAlbumInfo(rowKey, tableName);
    }
    
    @Override
    public void removeAlbum(String rowKey) {
        try {
            removeEntry(rowKey, this.tableName);
        } catch (Exception e) {
            System.out.print("Exception encountered: ");
            System.out.println(e.getMessage());
        }
    }

    private void removeEntry(String rowKey, String tableName) {
        try {
            CloudTableClient tableClient = getTableClient();
            Album specificAlbum =
            tableClient.execute(tableName, TableOperation.retrieve("1", rowKey, Album.class)).getResultAsType();
            tableClient.execute(tableName, TableOperation.delete(specificAlbum));
        } catch (Exception e) {
            System.out.print("Exception encountered: ");
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public List<Album> listAlbums() {
        try {
            
            CloudTableClient tableClient = getTableClient ();
            // Create a filter condition where the partition key is "Smith".
            String partitionFilter = TableQuery.generateFilterCondition(
            TableConstants.PARTITION_KEY, QueryComparisons.EQUAL,"1");

            // Specify a partition query, using "Smith" as the partition key filter.
            TableQuery<Album> partitionQuery =
            TableQuery.from(this.tableName, Album.class).where(partitionFilter);

            return convertToList(tableClient.execute(partitionQuery));
            // Loop through the results, displaying information about the entity.
//            for (Album album : tableClient.execute(partitionQuery)) {
//            System.out.println(album.getPartitionKey() + " " + album.getRowKey() + 
//            "\t" + album.getTitle() + "\t" + album.getDescription() + "\t" + album.getMail() + "\t" + album.getPath());

        }
        catch (Exception e) {
            System.out.print("Exception encountered: ");
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    private List<Album> convertToList(Iterable<Album> col) 
    {
        List<Album> albs = new LinkedList<Album>();
        Iterator<Album> iter = col.iterator();
        while(iter.hasNext()){
            Album al = iter.next();
            albs.add(al);
        }
        
        return albs;
    }
    
    

    
    private Album getAlbumInfo (String rowKey, String tableName) {
        try {
        CloudTableClient tableClient = getTableClient ();
        Album specificAlbum =
        tableClient.execute(tableName, TableOperation.retrieve("1", rowKey, Album.class)).getResultAsType();

        return specificAlbum;
        }
        catch (StorageException storageException) {
            System.out.print("StorageException encountered: ");
            System.out.println(storageException.getMessage());
            return null;
        } catch (Exception e) {
            System.out.print("Exception encountered:\n");
            System.out.println("Exception class: " + e.getClass() + "\nException message: " + e.getMessage() + "\nStack trace: " + e.getStackTrace());
            return null;
        } 
    }
    
    public void printAlbumInfo(Album album) {
        System.out.println(album.getPartitionKey() + "\t" + album.getRowKey() + 
            "\t" + album.getTitle() + "\t" + album.getDescription() +
                "\t" + album.getMail() + "\t" + album.getImage_paths() + "\t" + album.getTags());
       
    }
    
   private CloudTableClient getTableClient () {
       AccntCred accntCred = new AccntCred();
       AccountConnector connector = new AccountConnector(accntCred);
       CloudStorageAccount account = connector.getAccount();
       CloudTableClient tableClient = account.createCloudTableClient();
       return tableClient;
   }
    
    public static void main(String[] args) {

        DataRepoImpl dataRepo = new DataRepoImpl();
        
// -------------  LIST ALL ALBUMS -------------------------------
//        List<Album> allAlbums;
//        allAlbums = dataRepo.listAlbums();
//        
//        for (Album album : allAlbums) {
//            dataRepo.printAlbumInfo(album);
//        }
// ---------------------------------------------------------------

        
//        -----------------------DELETE THE TABLE "LAST" ----------------------------
//        CloudTableClient tableClient = dataRepo.getTableClient ();
//        try {
//        CloudTable table =
//                tableClient.getTableReference("last");
//        table.deleteIfExists();
//            System.out.println("Deleted the table successfully!");
//        }
//        catch (URISyntaxException e) 
//        {
//            System.out.println("URISyntaxException: " + e.getClass() + e.getMessage());
//        }
//        catch (StorageException e) 
//        {
//            System.out.println("StorageException: " + e.getClass() + e.getMessage());
//        }
//        catch (Exception e)
//        {
//            System.out.println("Other type of exception: " + e.getClass() + e.getMessage());
//        }
//        ------------------------------------------------------------------------------
             

//----------------- INSERT AN ALBUM -----------------------------
//        Album randomAlbum = dataRepo.getRandomAlbum();
//  
//        dataRepo.printAlbumInfo(randomAlbum);
//        System.out.println("\n");
//
//        dataRepo.insertAlbum(randomAlbum);
//        
        
// ------------------------------------------------------------------
        
//        Album insertedAlbum = dataRepo.retrieveAlbum(randomAlbum.getUniqueKey(), this.tableName);
//        System.out.println("\n");
//        dataRepo.printAlbumInfo(insertedAlbum);
//        
// -------------  LIST ALL ALBUMS -------------------------------
//        List<Album> allAlbums;
//        allAlbums = dataRepo.listAlbums();
//        
//        for (Album album : allAlbums) {
//            dataRepo.printAlbumInfo(album);
//        }
// ---------------------------------------------------------------
        
// --------------- REMOVE A SPECIFIC ALBUM -------------------------
//        try {
//        dataRepo.removeAlbum("19025e81-b140-4931-872a-f5ba6eb45fef");
//        dataRepo.removeAlbum("1e0e3a49-84a8-4976-baeb-285f6a2b166e");
//        dataRepo.removeAlbum("8fa93b0f-109a-4a4c-b117-d8db5521ca29");
//        dataRepo.removeAlbum("8e423497-ba16-401b-897c-e9ae8b1b69f2");
//        } catch (Exception e) {
//            System.out.print("Exception encountered:\n");
//            System.out.println("Exception class: " + e.getClass() + "\nException message: " + e.getMessage() + "\nStack trace: " + e.getStackTrace());
//        }
//        
//        System.out.println("Album removed successfully. \n");
// ------------------------------------------------------------------
        
//        allAlbums = dataRepo.listAlbums();
//        
//        for (Album album : allAlbums) {
//            dataRepo.printAlbumInfo(album);
//        }
        
        
//-------------------- RETRIEVE A SPECIFIC ALBUM --------------------
        
//        Album retrievedAlbum = dataRepo.retrieveAlbum("656b138d-f0db-4c2f-9c60-4bbeb6706bdc", this.tableName);
//        dataRepo.printAlbumInfo(retrievedAlbum);
// ------------------------------------------------------------------
        
// ---------------- LIST ALL ALBUMS -------------------------------
//        allAlbums = dataRepo.listAlbums();
//        for (Album album : allAlbums) {
//            dataRepo.printAlbumInfo(album);
//        }
// ------------------------------------------------------------------
        
        
        
    }
   
        private String savePhotoIml(File file) throws StorageException, URISyntaxException, IOException, FileNotFoundException{
            if(file== null || !file.exists()){
                throw new FileNotFoundException("Image file not exists!");
            }
            
            createStorageAccount();
            container = serviceClient.getContainerReference("album");
            container.createIfNotExist();
            setContainerPermission();
            String blockBlobReference= "fileName";
            //String filePath= file.getAbsolutePath();
            blob = container.getBlockBlobReference(blockBlobReference);
            //File fileReference = new File (filePath);
            blob.upload(new FileInputStream(file), file.length());

        String savedBlobPath= blob.getUri().toString();
        return savedBlobPath;
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

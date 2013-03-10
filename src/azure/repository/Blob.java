/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package azure.repository;

import com.microsoft.windowsazure.services.core.storage.*;
import com.microsoft.windowsazure.services.blob.client.*;
import com.microsoft.windowsazure.services.table.client.CloudTableClient;
import java.io.*;
import java.net.URISyntaxException;

/**
 *
 * @author bakharzy
 */
public class Blob {
    
    String containerName;
    String blobName;
    Blob testInstance;
    CloudBlobClient serviceClient;
    CloudBlobContainer container;
    CloudBlockBlob blob;
  
    
        private void removePhotoImp(String path) throws StorageException, URISyntaxException, IOException{
        
            splitter(path);
            createStorageAccount();
            container = serviceClient.getContainerReference(containerName);
            blob = container.getBlockBlobReference(blobName);
            blob.delete();
    }
    private void createStorageAccount(){
        
            AccntCred accntCred = new AccntCred();
            AccountConnector connector = new AccountConnector(accntCred);
            CloudStorageAccount account = connector.getAccount();
            serviceClient = account.createCloudBlobClient();
            
    }
    private void splitter(String path){
        
            String[] temp = path.split(".net/");
            String[] temp2 = temp[1].split("/");
            containerName = temp2[0];
            blobName = temp2[1];     
    }
    

    private void createContainer(){
        
    }
    private void setContainerPermission()throws StorageException{
            BlobContainerPermissions containerPermissions = new BlobContainerPermissions();
            containerPermissions.setPublicAccess(BlobContainerPublicAccessType.CONTAINER);
            container.uploadPermissions(containerPermissions);
    }
    
    public static void main(String[] args) throws StorageException, URISyntaxException, IOException
    {
        Blob blob1=new Blob(); 
        String path= "http://portalvhdsjv13w4pk1gh6j.blob.core.windows.net/album/fish";
//            blob1.removePhotoImp(path);
            String tmp=blob1.savePhotoTest();
            System.out.println("url= "+ tmp);
//        try
//        {
//            AccntCred accntCred = new AccntCred();
//            AccountConnector connector = new AccountConnector(accntCred);
//            CloudStorageAccount account = connector.getAccount();
//            CloudBlobClient serviceClient = account.createCloudBlobClient();
//            CloudBlobContainer container;
//            CloudBlockBlob blob;
//            
//            container = serviceClient.getContainerReference("album");
//            container.createIfNotExist();
//            BlobContainerPermissions containerPermissions = new BlobContainerPermissions();
//            containerPermissions.setPublicAccess(BlobContainerPublicAccessType.CONTAINER);
//            container.uploadPermissions(containerPermissions);
//        
//            String blockBlobReference= "fileName";
//            String filePath= "/cs/fs/home/bakharzy/Project/Azure-project/web/WEB-INF/view/images/fish.jpg";
//            blob = container.getBlockBlobReference(blockBlobReference);
//            File fileReference = new File (filePath);
//            blob.upload(new FileInputStream(fileReference), fileReference.length());
//            
//            for (ListBlobItem blobItem : container.listBlobs()) {
//             System.out.println(blobItem.getUri());
//             
//                }
//        String savedBlobPath= blob.getUri().toString();
//            System.out.println("savedBlobPath= "+savedBlobPath);
//
//        }
//        catch (FileNotFoundException fileNotFoundException)
//        {
//            System.out.print("FileNotFoundException encountered: ");
//            System.out.println(fileNotFoundException.getMessage());
//            System.exit(-1);
//        }
//        catch (StorageException storageException)
//        {
//            System.out.print("StorageException encountered: ");
//            System.out.println(storageException.getMessage());
//            System.exit(-1);
//        }
//        catch (URISyntaxException uriSyntaxException)
//        {
//            System.out.print("URISyntaxException encountered: ");
//            System.out.println(uriSyntaxException.getMessage());
//            System.exit(-1);
//        }
//        catch (Exception e)
//        {
//            System.out.print("Exception encountered: ");
//            System.out.println(e.getMessage());
//            System.exit(-1);
//        }
    }

 //----------------------------------------------------------
    public String savePhotoTest() throws StorageException, URISyntaxException, IOException{
        
            createStorageAccount();
            container = serviceClient.getContainerReference("album");
            container.createIfNotExist();
            setContainerPermission();
            String blockBlobReference= "fish";
            String filePath= "/cs/fs/home/bakharzy/Project/Azure-project/web/WEB-INF/view/images/fish.jpg";
           // String filmPath= "/cs/fs/home/bakharzy/Desktop/film.mp4";
            blob = container.getBlockBlobReference(blockBlobReference);
            File fileReference = new File (filePath);
            blob.upload(new FileInputStream(fileReference), fileReference.length());
            
            for (ListBlobItem blobItem : container.listBlobs()) {
             System.out.println(blobItem.getUri());
             
                }
        String savedBlobPath= blob.getUri().toString();
        return savedBlobPath;
    }
    //------------------------------------------------------------------------
}
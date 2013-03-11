/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package azure.repository;

import azure.domain.Album;
import com.microsoft.windowsazure.services.core.storage.CloudStorageAccount;
import com.microsoft.windowsazure.services.core.storage.StorageException;
import com.microsoft.windowsazure.services.table.client.CloudTableClient;
import com.microsoft.windowsazure.services.table.client.TableConstants;
import com.microsoft.windowsazure.services.table.client.TableOperation;
import com.microsoft.windowsazure.services.table.client.TableQuery;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sadeqzad
 */
public class DataRepoTest {
    
    
    
    public DataRepoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of savePhoto method, of class DataRepo.
     */
    @Test
    public void testSavePhoto() {
        System.out.println("savePhoto");
        File file = null;
        DataRepo instance = new DataRepoImpl();
        String expResult = "";
        String result = instance.savePhoto(file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removePhoto method, of class DataRepo.
     */
    @Test
    public void testRemovePhoto() {
        System.out.println("removePhoto");
        String path = "";
        DataRepo instance = new DataRepoImpl();
        instance.removePhoto(path);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertAlbum method, of class DataRepo.
     */
    @Test
    public void testInsertAlbum() {
        try {
            System.out.print("insertAlbum: ");
            Album album = getRandomAlbum();
            DataRepo instance = new DataRepoImpl();
            instance.insertAlbum(album);
            System.out.println("tested successfully!");
        } catch (Exception e) {
            fail("insertAlbum: test failed!");
        }
    }

    /**
     * Test of removeAlbum method, of class DataRepo.
     */
    @Test
    public void testRemoveAlbum() {
        try {
        System.out.print("removeAlbum: ");
        List<Album> allAlbums = listAlbums();
        
        String key = allAlbums.get(0).getUniqueKey();
        DataRepo instance = new DataRepoImpl();
        instance.removeAlbum(key);
        Album retrievedAlbum = getAlbumInfo(key, "last");
        Album expResult = null;
        assertEquals(expResult, retrievedAlbum);
            System.out.println("tested successful!");
        }
        catch (Exception e)
        {
        fail("testRemoveAlbum faild!");
        }
    }

    /**
     * Test of listAlbums method, of class DataRepo.
     */
    @Test
    public void testListAlbums() {
        System.out.print("listAlbums: ");
        DataRepo instance = new DataRepoImpl();
        int expResultSize = 0;
        List<Album> result = instance.listAlbums();
        assertEquals(expResultSize, result.size());
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    private Album getRandomAlbum() {
        Album album = new Album(UUID.randomUUID().toString());
        album.addImagePath("/khhkjh/kjaghjk.jpg");
        album.addTags("adsgj,adjhak,akjsdash,asdgas");
        album.setDescription("dahsfdafjdjha");
        album.setMail("ajdgjgdjgadahfdsf");
        album.setTitle("saacda");

        return album;
    }
 
    private List<Album> listAlbums() {
        try {
            
            CloudTableClient tableClient = getTableClient ();
            // Create a filter condition where the partition key is "Smith".
            String partitionFilter = TableQuery.generateFilterCondition(
            TableConstants.PARTITION_KEY, TableQuery.QueryComparisons.EQUAL,"1");

            // Specify a partition query, using "Smith" as the partition key filter.
            TableQuery<Album> partitionQuery =
            TableQuery.from("last", Album.class).where(partitionFilter);

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
    
    private CloudTableClient getTableClient () {
       AccntCred accntCred = new AccntCred();
       AccountConnector connector = new AccountConnector(accntCred);
       CloudStorageAccount account = connector.getAccount();
       CloudTableClient tableClient = account.createCloudTableClient();
       return tableClient;
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
    
    public void printAlbumInfo(Album album) {
        System.out.println(album.getPartitionKey() + "\t" + album.getRowKey() + 
            "\t" + album.getTitle() + "\t" + album.getDescription() +
                "\t" + album.getMail() + "\t" + album.getImage_paths() + "\t" + album.getTags());
       
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
}

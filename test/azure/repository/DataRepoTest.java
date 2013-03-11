/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package azure.repository;

import azure.domain.Album;
import java.io.File;
import java.util.List;
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
        System.out.println("insertAlbum");
        Album album = null;
        DataRepo instance = new DataRepoImpl();
        instance.insertAlbum(album);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeAlbum method, of class DataRepo.
     */
    @Test
    public void testRemoveAlbum() {
        System.out.println("removeAlbum");
        String key = "";
        DataRepo instance = new DataRepoImpl();
        instance.removeAlbum(key);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listAlbums method, of class DataRepo.
     */
    @Test
    public void testListAlbums() {
        System.out.println("listAlbums");
        DataRepo instance = new DataRepoImpl();
        List expResult = null;
        List result = instance.listAlbums();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

 
}

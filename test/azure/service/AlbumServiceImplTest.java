/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package azure.service;

import azure.domain.Album;
import azure.repository.DataRepo;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
 * @author kavansol
 */
public class AlbumServiceImplTest {
    
    public AlbumServiceImplTest() {
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
     * Test of saveAlbum method, of class AlbumServiceImpl.
     */
    @Test
    public void testSaveAlbum_Album() {
        System.out.println("saveAlbum");
        Album album = null;
        AlbumServiceImpl instance = null;
        instance.saveAlbum(album);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveAlbum method, of class AlbumServiceImpl.
     */
    @Test
    public void testSaveAlbum_Album_List() {
        System.out.println("saveAlbum");
        Album album = null;
        List<File> files = null;
        AlbumServiceImpl instance = null;
        instance.saveAlbum(album, files);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeAlbum method, of class AlbumServiceImpl.
     */
    @Test
    public void testRemoveAlbum() {
        System.out.println("removeAlbum");
        Album album = null;
        AlbumServiceImpl instance = null;
        instance.removeAlbum(album);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAlbumByKey method, of class AlbumServiceImpl.
     */
    @Test
    public void testFindAlbumByKey() {
        System.out.println("findAlbumByKey");
        String key = "";
        AlbumServiceImpl instance = null;
        Album expResult = null;
        Album result = instance.findAlbumByKey(key);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAlbumsByMail method, of class AlbumServiceImpl.
     */
    @Test
    public void testFindAlbumsByMail() {
        System.out.println("findAlbumsByMail");
        String mail = "";
        AlbumServiceImpl instance = null;
        List expResult = null;
        List result = instance.findAlbumsByMail(mail);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listAll method, of class AlbumServiceImpl.
     */
    @Test
    public void testListAll() {
        System.out.println("listAll");
        AlbumServiceImpl instance = null;
        List expResult = null;
        List result = instance.listAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of exist method, of class AlbumServiceImpl.
     */
    @Test
    public void testExist() {
        System.out.println("exist");
        Album album = null;
        AlbumServiceImpl instance = null;
        boolean expResult = false;
        boolean result = instance.exist(album);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    class DataRepoTestImpl implements DataRepo{
        HashMap<String,File> blob;
        HashMap<String,Album> table;
        
        public DataRepoTestImpl(){
            blob = new HashMap<String,File>();
            table = new HashMap<String, Album>();
            
        }
        @Override
        public String savePhoto(File file) {
            String path = "/"+UUID.randomUUID().toString()+".jpg";
            blob.put(path, file);
            return path;
        }

        @Override
        public void removePhoto(String path) {
            blob.remove(path);
        }

        @Override
        public void insertAlbum(Album album) {
            String al_key = UUID.randomUUID().toString();
            table.put(al_key, album);
        }

        @Override
        public void removeAlbum(String key) {
            table.remove(key);
        }

        @Override
        public List<Album> listAlbums() {
             List<Album> results = new ArrayList<Album>(table.values());
             return results;
        }
        
    }
}
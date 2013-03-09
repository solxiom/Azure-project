/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package azure.service;

import azure.domain.Album;
import azure.repository.DataRepo;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
 * @author kavansol
 */
public class AlbumServiceTest {

    private AlbumService service;
    private HashMap<String, File> blob;
    private HashMap<String, Album> table;

    public AlbumServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        service = new AlbumServiceImpl(new AlbumServiceTest.DataRepoTestImpl());
        blob = new HashMap<String, File>();
        table = new HashMap<String, Album>();

    }

    @After
    public void tearDown() {
        service = null;
        blob = null;
        table = null;
    }

    /**
     * Test of saveAlbum method, of class AlbumServiceImpl.
     */
    @Test
    public void testSaveAlbum_Album() {
        System.out.print("Test saveAlbum");
        String path = "/img01.jpg,/img02.jpg";
        String email = "soli@gmail.com";
        Album album = getRandomAlbum();
        album.setTitle("Bahar");
        album.setMail(email);
        album.addImagePath(path);

        AlbumService instance = service;
        instance.saveAlbum(album);
        boolean expResult = true;
        boolean result = instance.exist(album);
        assertEquals(expResult, result);
        assertEquals(album, instance.findAlbumsByMail(email).get(0));
        assertEquals(1, instance.findAlbumsByMail(email).size());

        String newEmail = "kiri@yahoo.com";
        String newPath = album.getImagePaths()[0];
        album.setMail(newEmail);
        album.setImage_paths(newPath);
        instance.saveAlbum(album);
        if (instance.findAlbumsByMail(email).size() != 0) {
            System.out.println("....[failed]");
            fail("Failed saveAlbum in update album phase one.");
        }
        if (instance.findAlbumByKey(album.getUniqueKey()).getImagePaths().length != 1) {
            System.out.println("....[failed]");
            fail("Failed saveAlbum in update album phase two.");
        }
        if (!instance.findAlbumByKey(album.getUniqueKey()).getImagePaths()[0].equals(newPath)) {
            System.out.println("....[failed]");
            fail("Failed saveAlbum in update album phase three.");
        }

        System.out.println("....[OK]");
    }

    /**
     * Test of saveAlbum method, of class AlbumServiceImpl.
     */
    @Test
    public void testSaveAlbum_Album_List() {
        System.out.print("Test saveAlbum_FileList");
        System.out.println("");
        Album updatedAlbum,album = getRandomAlbum();
        updatedAlbum=getRandomAlbum();
        updatedAlbum.setUniqueKey(album.getUniqueKey());
        List<File> files = new LinkedList<File>();

        for (int i = 0; i < 10; i++) {
            files.add(getRandomFile());
        }
        //first delete default path generated by getRandomAlbum()
        album.setImage_paths("");
        updatedAlbum.setImage_paths("");
        
        AlbumService instance = service;
        
        instance.saveAlbum(album, files);
        
        String pathTobeFound = "disk_1/images/" + files.get(4).getName();
        if (!blob.containsKey(pathTobeFound)) {
            System.out.println("....[failed]");
            fail("Failed saveAlbum_FileList in phase one. [fileupload testing]");
        }
       
        List<String> paths = Arrays.asList(album.getImagePaths());
       

        paths = paths.subList(0, 4);
//        System.out.println("size path array " + paths.size());

        String pathStr = "";
        for (String s : paths) {
            pathStr += s + ",";
//            System.out.println("sss= "+s);
        }
//        System.out.println("pathStr size " + pathStr.split(",").length);
        
        updatedAlbum.setImage_paths(pathStr);
//        System.out.println("album array size= "+ album.getImagePaths().length);
//        System.out.println("new array size= "+ updatedAlbum.getImagePaths().length);
        instance.saveAlbum(updatedAlbum);
//        System.out.println("blob size " + blob.size());
        if (blob.size() != 4) {
            System.out.println("....[failed]");
            fail("Failed saveAlbum_FileList in phase one. [file delete testing]");
        }

        System.out.println("....[OK]");
    }

    /**
     * Test of removeAlbum method, of class AlbumServiceImpl.
     */
    @Test
    public void testRemoveAlbum() {
        System.out.print("Test removeAlbum");
        AlbumService instance = service;
        Album toBeRemoved = getRandomAlbum();

        for (int i = 0; i < 10; i++) {
            Album a = getRandomAlbum();
            instance.saveAlbum(a);
        }
        instance.saveAlbum(toBeRemoved);
        if (!instance.exist(toBeRemoved)) {
            System.out.println("....[failed]");
            fail("testRemoveAlbum failed: phase one failed");
        }
        instance.removeAlbum(toBeRemoved);
        if (instance.exist(toBeRemoved)) {
            System.out.println("....[failed]");
            fail("testRemoveAlbum failed: phase two failed");
        }

        System.out.println("....[OK]");
    }

    /**
     * Test of findAlbumByKey method, of class AlbumServiceImpl.
     */
    @Test
    public void testFindAlbumByKey() {
        System.out.print("Test findAlbumByKey");

        AlbumService instance = service;
        List<Album> als = new LinkedList<Album>();
        Album toBeFound = getRandomAlbum();
        String key = toBeFound.getUniqueKey();
        for (int i = 0; i < 100; i++) {
            Album a = getRandomAlbum();
            als.add(a);
            instance.saveAlbum(a);
        }

        instance.saveAlbum(toBeFound);

        Album expResult = toBeFound;
        Album result = instance.findAlbumByKey(key);
        assertEquals(expResult.getUniqueKey(), result.getUniqueKey());

        System.out.println("....[OK]");
    }

    /**
     * Test of findAlbumsByMail method, of class AlbumServiceImpl.
     */
    @Test
    public void testFindAlbumsByMail() {
        System.out.print("Test findAlbumsByMail");
        String mail = "solxiom@gmail.com";
        List<Album> als = new LinkedList<Album>();
        AlbumService instance = service;

        for (int i = 0; i < 100; i++) {
            Album a = getRandomAlbum();
            als.add(a);
            instance.saveAlbum(a);
        }
        List<Album> toBeFound = new LinkedList<Album>();
        for (int i = 0; i < 10; i++) {
            Album a2 = getRandomAlbum();
            a2.setMail(mail);
            a2.setTitle("solxiom's Album");
            instance.saveAlbum(a2);
        }


        List<Album> result = instance.findAlbumsByMail(mail);
        for (Album x : result) {
            if (!x.getMail().equals(mail)) {
                System.out.println("....[failed]");
                fail("FindAlbumsByMail failed");
            }
        }

        assertEquals(10, result.size());

        System.out.println("....[OK]");

    }

    /**
     * Test of listAll method, of class AlbumServiceImpl.
     */
    @Test
    public void testListAll() {
        System.out.print("Test listAll method");
        AlbumService instance = service;
        List<Album> als = new LinkedList<Album>();
        for (int i = 0; i < 10; i++) {
            Album a = getRandomAlbum();
            als.add(a);
            instance.saveAlbum(a);
        }

        List<Album> expResult = als;
        List<Album> result = instance.listAll();

        for (Album a : expResult) {

            if (!result.contains(a)) {
                System.out.println("....[failed]");
                fail("List All Failed.");
            }

        }

        System.out.println("....[OK]");

    }

    /**
     * Test of exist method, of class AlbumServiceImpl.
     */
    @Test
    public void testExist() {
        System.out.print("Test exist (with saved album)");
        //test saved album
        Album album = getRandomAlbum();

        AlbumService instance = service;
        service.saveAlbum(album);
        boolean expResult = true;
        boolean result = instance.exist(album);
        assertEquals(expResult, result);
        System.out.println("....[OK]");
        //test unsaved album
        System.out.print("Test exist (with unsaved album)");
        Album album2 = getRandomAlbum();
        boolean expResult2 = false;
        boolean result2 = instance.exist(album2);
        assertEquals(expResult2, result2);

        System.out.println("....[OK]");
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

    private File getRandomFile() {
        File file = new File(UUID.randomUUID().toString() + ".png");
        return file;
    }

    class DataRepoTestImpl implements DataRepo {

        public DataRepoTestImpl() {
        }

        @Override
        public String savePhoto(File file) {

            String path = "disk_1/images/" + file.getName();//generating fake path for a file  
            blob.put(path, file);

            return path;
        }

        @Override
        public void removePhoto(String path) {
            blob.remove(path);
        }

        @Override
        public void insertAlbum(Album album) {
            table.put(album.getUniqueKey(), album);
        }

        @Override
        public void removeAlbum(String key) {
            Album removedAlbum = table.remove(key);

        }

        @Override
        public List<Album> listAlbums() {
            List<Album> results = new ArrayList<Album>(table.values());
            return results;
        }
    }
}
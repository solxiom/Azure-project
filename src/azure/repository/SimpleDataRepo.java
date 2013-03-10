/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package azure.repository;

import azure.domain.Album;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author kavan
 */
public class SimpleDataRepo implements DataRepo {
    
    HashMap<String,File> blob;
    HashMap<String,Album> table;

    
    public SimpleDataRepo() {
        this.blob = new HashMap<String, File>();
        this.table = new HashMap<String, Album> ();
    }
    
    
    
        @Override
        public String savePhoto(File file) {

            String path = "/images/"+file.getName();  
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

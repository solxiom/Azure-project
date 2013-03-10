/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package azure.repository;

import azure.domain.Album;
import java.io.File;
import java.util.List;

/**
 *
 * @author kavansol
 */
public interface DataRepo {
    
    public String savePhoto(File file);
    
    public void removePhoto(String path);
    
    public void insertAlbum(Album album);
    
    public void removeAlbum(String key);
    
    public List<Album> listAlbums();
    
      
   
}

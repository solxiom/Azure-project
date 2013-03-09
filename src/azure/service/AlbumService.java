/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package azure.service;

import azure.domain.Album;
import java.io.File;
import java.util.List;

/**
 *
 * @author kavansol
 */
public interface AlbumService {

    public void saveAlbum(Album album);
    
    public void saveAlbum(Album album, List<File> files);

    public void removeAlbum(Album album);
    
    public Album findAlbumByKey(String key);

    public List<Album> findAlbumsByMail(String mail);

    public List<Album> listAll();
    
    public boolean exist(Album album);
}

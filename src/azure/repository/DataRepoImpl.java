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
public class DataRepoImpl implements DataRepo {

    
    @Override
    public String savePhoto(File file) {
        //this.saveFile();
        throw new UnsupportedOperationException("Not supported yet.");
        }

    @Override
    public void insertAlbum(Album album) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeAlbum(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Album> listAlbums() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

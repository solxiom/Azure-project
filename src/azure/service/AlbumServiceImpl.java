/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package azure.service;

import azure.domain.Album;
import azure.repository.DataRepo;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author kavansol
 */
public class AlbumServiceImpl implements AlbumService {

    private DataRepo repo;

    public AlbumServiceImpl(DataRepo repo) {
        this.repo = repo;
    }

    @Override
    public void saveAlbum(Album album, List<File> files) {
            if(this.exist(album)){
                Album oldAlbum = findAlbumByKey(album.getUniqueKey());
                for(String p: oldAlbum.getImages()){
                }
                this.removeAlbum(album);
            }
            
            repo.insertAlbum(album);
    }

    @Override
    public void removeAlbum(Album album) {
        
        repo.removeAlbum(album.getUniqueKey());
    }

    @Override
    public Album findAlbumByKey(String key) {
        List<Album> albs = repo.listAlbums();
        for (Album al : albs) {
            if (al.getUniqueKey().equals(key)) {
                return al;
            }

        }
        return null;
    }

    @Override
    public List<Album> findAlbumsByMail(String mail) {
        List<Album> results = new LinkedList<Album>();
        List<Album> albs = repo.listAlbums();
        for (Album al : albs) {
            if (al.getMail().equalsIgnoreCase(mail)) {
                results.add(al);
            }

        }
        return results;
    }

    @Override
    public List<Album> listAll() {
        return repo.listAlbums();
    }

    @Override
    public boolean exist(Album album) {

        if(this.findAlbumByKey(album.getUniqueKey()) != null)
        {
            return true;
        }
        return false;
    }
}

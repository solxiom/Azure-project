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
    public void saveAlbum(Album album) {
        this.saveAlbum(album, null);
    }

    @Override
    public void saveAlbum(Album album, List<File> files) {
        if (this.exist(album)) {
            Album oldAlbum = findAlbumByKey(album.getUniqueKey());
            removeDeletedPhotos(oldAlbum, album);
            this.removeAlbum(oldAlbum);
        }
        if (files != null) {
            this.addPhotosToAlbum(album, files);
        }
        repo.insertAlbum(album);
    }

    @Override
    public void removeAlbum(Album album) {
        for (String p : album.getImagePaths()) {
            repo.removePhoto(p);
        }
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

        if (this.findAlbumByKey(album.getUniqueKey()) != null) {
            return true;
        }
        return false;
    }

    private void addPhotosToAlbum(Album album, List<File> files) {
        for (File f : files) {
            String path = repo.savePhoto(f);
            album.addImagePath(path);
        }
    }

    private void removeDeletedPhotos(Album old, Album updated) {
        for (String p : old.getImagePaths()) {
            if (!imageExistInAlbum(updated, p)) {
                repo.removePhoto(p);
            }
        }
    }

    private boolean imageExistInAlbum(Album album, String path) {
        for (String p : album.getImagePaths()) {
            if (path.equals(path)) {
                return true;
            }
        }
        return false;
    }
}

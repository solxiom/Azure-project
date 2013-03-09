/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package azure.domain;

import com.microsoft.windowsazure.services.table.client.*;
import java.io.Serializable;

/**
 *
 * @author bakharzy
 */
public class Album extends TableServiceEntity {

    private String uniqueKey;
    private String title;
    private String description;
    private String tags;
    private String imag_paths;
    private String mail;

    public Album(String uniqueKey) {
        this.rowKey = uniqueKey;
        this.partitionKey = uniqueKey;
    }

    public Album() {
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void addTags(String tag) {
        tags += tag+ "," ;
    }

    public String[] getTags() {
        return tags.split(",");
    }

    public void addImagePath(String path) {
        imag_paths += path +",";
    }

    public String[] getImagePaths() {
       return imag_paths.split(",");
       
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package azure.domain;

import com.microsoft.windowsazure.services.table.client.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author bakharzy
 */
public class Album extends TableServiceEntity implements Serializable{

    private String uniqueKey;
    private String title;
    private String description;
    private String tags;
    private String image_paths;
    private String mail;
    private String password;
    
    public Album(String uniqueKey) {
        this.rowKey = uniqueKey;
        this.partitionKey = "1";
        this.uniqueKey = uniqueKey;

    }
    
    public Album () {

    }


    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
        this.rowKey = uniqueKey;
        this.partitionKey = "1";
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        tags += tag + ",";
    }

    public String[] getTagsAsArray() {
        if(this.tags == null){
            return new String[0];
        }
        return this.tags.split(",");      
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTags() {
        return tags;
    }
     

    public void addImagePath(String path) {
        this.image_paths += path + ",";
    }

    public void setImage_paths(String paths) {
        this.image_paths = paths;
    }

    public String getImage_paths() {
        return image_paths;
    }
    

    public String[] getImagePathsAsArray() {
        if(this.image_paths == null){
            return new String[0];
        }
        return this.image_paths.split(",");
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

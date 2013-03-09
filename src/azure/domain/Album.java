/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package azure.domain;

import com.microsoft.windowsazure.services.table.TableService;
import com.microsoft.windowsazure.services.table.client.*;
import com.sun.org.apache.bcel.internal.generic.TABLESWITCH;
import java.io.Serializable;

/**
 *
 * @author bakharzy
 */
public class Album extends TableServiceEntity implements Serializable {

    private String uniqueKey;
    private String title;
    private String description;
    private String tags;
    private String image_paths;
    private String mail;
  
    
    public Album(String uniqueKey) {
        this.rowKey = uniqueKey;
        this.partitionKey = uniqueKey;
        this.uniqueKey = uniqueKey;
        image_paths ="";
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
        tags += tag + ",";
    }

    public String[] getTags() {
        return tags.split(",");
    }
    
    public void setTags(String tags){
        this.tags= tags;
    }

    public void addImagePath(String path) {
        this.image_paths += path + ",";
    }
    
    public void setImage_paths(String paths){
        this.image_paths = paths;
    }

    public String[] getImagePaths() {
        return image_paths.split(",");
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

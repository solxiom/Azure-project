/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package azure.domain;

import com.microsoft.windowsazure.services.table.client.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author bakharzy
 */
public class Album extends TableServiceEntity {

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
        image_paths = "";

    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
        this.rowKey = uniqueKey;
        this.partitionKey = uniqueKey;
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
        return this.getStringAsArray(tags);      
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void addImagePath(String path) {
        this.image_paths += path + ",";
    }

    public void setImage_paths(String paths) {
        this.image_paths = paths;
    }

    public String[] getImagePaths() {
        return this.getStringAsArray(image_paths);
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
    
    private String[] getStringAsArray(String str){     
        String[] ar = str.split(",");
        List<String> arx = new LinkedList<String>();
        for (String s : ar) {
            
            if (!(s.equalsIgnoreCase("") || (s.equalsIgnoreCase(" ")))) {
                arx.add(s);
            }
        }
        
        return arx.toArray(new String[arx.size()]);         
    }
}

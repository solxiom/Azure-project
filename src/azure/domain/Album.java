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
public class Album extends TableServiceEntity implements Serializable {
    
    private String uniqueKey;
    private String Title;
    private String Description;
    private String mail;
    private String Tags[];
    private String Images[];
    
    public Album(String uniqueKey) {
        this.rowKey = uniqueKey;
        this.partitionKey = uniqueKey;
    }

    public Album() {
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
  

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String[] getTags() {
        return Tags;
    }

    public void setTags(String[] Tags) {
        this.Tags = Tags;
    }

    public String[] getImages() {
        return Images;
    }

    public void setImages(String[] Images) {
        this.Images = Images;
    }

    

    @Override
    public String toString() {
        return super.toString();
    }

   
    
}

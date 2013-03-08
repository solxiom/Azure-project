/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package azure.domain;

import java.util.UUID;

/**
 *
 * @author bakharzy
 */
public class Album {

    private UUID UniqueKey;
    private String Title;
    private String Description;
    private String Tags[];
    private String Images[];

    public Album() {
    }

    public UUID getUniqueKey() {
        return UniqueKey;
    }

    public void setUniqueKey(UUID UniqueKey) {
        this.UniqueKey = UniqueKey;
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

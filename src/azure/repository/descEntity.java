/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package azure.repository;

import com.microsoft.windowsazure.services.table.client.*;

/**
 *
 * @author sadeqzad
 */
public class descEntity extends TableServiceEntity {
    public descEntity(String id) {
        this.rowKey = id;
        this.partitionKey = id;
        
    }

    public descEntity() { }

    String desc;
    String date;
    String path;

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
}


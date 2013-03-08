/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package azure.repository;

import java.util.UUID;

/**
 *
 * @author kavansol
 */
public class SampleRepo {
    
    public static void main(String[] args){
        UUID id = UUID.randomUUID();
        System.out.println(""+ id);
    }
    
    @Override
    public String toString(){
     
        return "Man object nistam man Mohammadam";
    }
}



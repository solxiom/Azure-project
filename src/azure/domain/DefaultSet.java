/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package azure.domain;

/**
 *
 * @author kavan
 */
public class DefaultSet{
        
        String[] img_paths;

        public DefaultSet() {
            img_paths = new String[]{"band",
                "canon","car",
                "casacade1","cascade2",
                "casacade3","fish",
                "flower","girl",
                "planet","small-world","tiger"
            };
            for(int i=0; i < img_paths.length; i++){
                img_paths[i] = "/photomash/photo/1984-default/"+img_paths[i]+".jpg";
            }
        }
        

        public String[] getImg_paths() {
            return img_paths;
        }

        public void setImg_paths(String[] img_paths) {
            this.img_paths = img_paths;
        }
        
        
    }

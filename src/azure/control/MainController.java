package azure.control;

import azure.domain.SampleObject;
import azure.repository.DataRepo;
import azure.repository.SimpleDataRepo;
import azure.service.AlbumService;
import azure.service.AlbumServiceImpl;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *
 * @author kavansol
 */
@Controller
public class MainController {
    
    private DataRepo repo;
    private AlbumService service;

    public MainController() {
        repo = new SimpleDataRepo();
        service = new AlbumServiceImpl(repo);
    }
     
    @RequestMapping(value = "*")
    public String not_found() {

        System.out.println("Hello from photomash index");
        return "not_found.html";
    }
    @RequestMapping(value = "/")
    public String index() {

        System.out.println("Hello from photomash index");
        return "index.html";
    }
    @RequestMapping(value = "/photo/{album}/{img}")
    public String getPohot(@PathVariable String album, @PathVariable String img){
        String path="";
        if(album.equalsIgnoreCase("1984-default")){              
            path +="/images/"+img+".jpg";
            return path;
        }
        return "redirect:/";
    }
    @RequestMapping(value = "/album/create")
    public String createAlbum() {       
        return "create_album.html";
    }
    @RequestMapping(value = "/album/create/submit", method= RequestMethod.GET)
    public String submitNewAlbum(HttpServletRequest request) {
        
        System.out.println("I got the infos!! " );
        Enumeration enumx = request.getParameterNames();
        
        while(enumx.hasMoreElements()){
            String attr = (String)enumx.nextElement();
            System.out.println( attr + " : " + request.getParameter(attr));
        }
        return "redirect:/";
    }
    
   
    
    @RequestMapping(value = "/photo/{img_id}")
    public @ResponseBody SampleObject getShopInJSON(@PathVariable String img_id) {


        System.out.println("JSON Say Hi to The World");
        SampleObject sample = new SampleObject();
        sample.setName(img_id);
        sample.setInfoArray(new String[]{"info 1", "info 2", "info 3"});

        return sample;

    }
    
  
}

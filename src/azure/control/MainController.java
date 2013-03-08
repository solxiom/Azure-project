package azure.control;

import azure.domain.SampleObject;
import java.util.Enumeration;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Autowired
    private ServletContext servletContext;
    
    
    @RequestMapping(value = "/")
    public String index() {

        System.out.println("Hello from photomash index");
        return "index";
    }
    @RequestMapping(value = "/album/create")
    public String createAlbum() {

        
        return "create_album";
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

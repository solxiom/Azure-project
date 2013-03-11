package azure.control;

import azure.domain.Album;
import azure.domain.DefaultSet;
import azure.domain.SampleObject;
import azure.repository.DataRepo;
import azure.repository.DataRepoImpl;
import azure.service.AlbumService;
import azure.service.AlbumServiceImpl;
import java.io.File;
import java.net.URL;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletContext;
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
//        repo = new SimpleDataRepo();
        repo = new DataRepoImpl();
        service = new AlbumServiceImpl(repo);
        String bikhod = "dashdjasgdjagdjgsj";
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

    @RequestMapping(value = "/album/create")
    public String createAlbum() {
        return "create_album.html";
    }

    @RequestMapping(value = "/album/create/submit", method = RequestMethod.GET)
    public String submitNewAlbum(HttpServletRequest request) {
        ServletContext context = request.getServletContext();
        String realContextPath = context.getRealPath(request.getContextPath());
        System.out.println(" Kiririririririr shir- : " + realContextPath);

        System.out.println("I got the infos!! ");
        List<File> images = new LinkedList<File>();

        Album album = new Album(UUID.randomUUID().toString());
        album.setTitle(request.getParameter("title"));
        album.setMail(request.getParameter("mail"));
        album.setTags(request.getParameter("tags"));
        album.setPassword(request.getParameter("pass"));
        album.setDescription(request.getParameter("description"));

        Enumeration enumx = request.getParameterNames();

        while (enumx.hasMoreElements()) {
            String attrKey = (String) enumx.nextElement();
            String attrValue = request.getParameter(attrKey);
            System.out.println(attrKey + " : " + attrValue);
            if (attrKey.startsWith("img")) {
                ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                URL url = classLoader.getResource("web/WEB-INF/view/images/" + attrValue + ".jpg");
                String root = realContextPath.replace("web/photomash", "") + "/web/WEB-INF/view/images/";
//                "/cs/fs/home/kavansol/Projects/Azure-project/build"
                File image = new File(root + attrValue + ".jpg");

                images.add(image);
            }
        }

        service.saveAlbum(album, images);

        return "redirect:/";
    }

    @RequestMapping(value = "/album/create/submit/async", method = RequestMethod.GET)
    public @ResponseBody String[] submitNewAlbumAsync(HttpServletRequest request) {
        ServletContext context = request.getServletContext();
        String realContextPath = context.getRealPath(request.getContextPath());

        System.out.println("I got the infos!! ");
        List<File> images = new LinkedList<File>();

        Album album = new Album(UUID.randomUUID().toString());
        album.setTitle(request.getParameter("title"));
        album.setMail(request.getParameter("mail"));
        album.setTags(request.getParameter("tags"));
        album.setPassword(request.getParameter("pass"));
        album.setDescription(request.getParameter("description"));

        Enumeration enumx = request.getParameterNames();

        while (enumx.hasMoreElements()) {
            String attrKey = (String) enumx.nextElement();
            String attrValue = request.getParameter(attrKey);
            System.out.println(attrKey + " : " + attrValue);
            if (attrKey.startsWith("img")) {
                ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                URL url = classLoader.getResource("web/WEB-INF/view/images/" + attrValue + ".jpg");
                String root = realContextPath.replace("web/photomash", "") + "/web/WEB-INF/view/images/";
//                "/cs/fs/home/kavansol/Projects/Azure-project/build"

                File image = new File(root + attrValue + ".jpg");

                images.add(image);
            }
        }

        service.saveAlbum(album, images);
        String[] strAr = new String[1];
        strAr[0] = "upload done!";
        return strAr;
    }

    @RequestMapping(value = "/photo/albums/{album}/{img}")
    public String getPohoto(@PathVariable String album, @PathVariable String img) {
        String path = "";
        if (album.equalsIgnoreCase("1984-default")) {
            path += "/images/" + img + ".jpg";
            return path;
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/photo/albums/{album_key}")
    public @ResponseBody
    Album getAlbum(@PathVariable String album_key) {
        Album album = service.findAlbumByKey(album_key);
        return album;
    }

    @RequestMapping(value = "/photo/default/list")
    public @ResponseBody
    String[] getDefaultList() {

        DefaultSet defaultSet = new DefaultSet();
        return defaultSet.getImg_paths();

    }

    @RequestMapping(value = "/photo/albums/list")
    public @ResponseBody
    List<Album> listAlbums() {

        return service.listAll();

    }

    @RequestMapping(value = "/photo/{img_id}")
    public @ResponseBody
    SampleObject getShopInJSON(@PathVariable String img_id) {


        System.out.println("JSON Say Hi to The World");
        SampleObject sample = new SampleObject();
        sample.setName(img_id);
        sample.setInfoArray(new String[]{"info 1", "info 2", "info 3"});

        return sample;


    }

    private String getImagesRoot() {
        String rootPath = System.getProperty("user.dir") + "/web/WEB-INF/view/images/";
        return rootPath;
    }

    private String getImagesAbsoluteRoot() {
        String rootPath = "/cs/fs/home/kavan/Projects/Azure-project/web/WEB-INF/view/images/";
        return rootPath;
    }
    //testMain
//    public static void main(String[] args){
//        String path  = System.getProperty("user.dir")+"/web/WEB-INF/view/images/tiger.jpg";
//        System.out.println(" path:" + path);
//        File f = new File(path);
//        System.out.println("file exist: " + f.exists());
//    }
}

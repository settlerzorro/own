package novel;

import novel.entity.NovelAttribute;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author : zhangshuai
 * @date : 13:43 2020/10/19
 */
public abstract class SpiderNovel {

    private String url = "";

    public static final String LOCAL_FILE = "C:\\N-20HEPF129J9N-Data\\shuaizha\\Desktop\\test.txt";

    public void setUrl(String url){
        this.url = url;
    }

    public String getUrl(){
        return url;
    }

    protected static void writeToLocalFile(StringBuilder txt ,String fictionChapter,String content){
        txt.append(fictionChapter).append("\n").append(content).append("\n");
    }

    protected abstract void run();
    protected abstract Elements getElements(NovelAttribute novelAttribute);

    public static Document getDocByJsoup(String href, String charsetName) {
        String ip = "135.251.33.15";

        int port = 8080;
        StringBuffer bs = new StringBuffer();
        try {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, port));


            URL url = new URL(href);

            URLConnection urlcon = (URLConnection) url.openConnection(proxy);

            urlcon.connect();

            InputStream is = urlcon.getInputStream();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(is, charsetName));
            String l = null;

            while ((l = buffer.readLine()) != null) {

                bs.append(l + "\n");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(bs.toString());
        return Jsoup.parse(bs.toString());
    }
    protected abstract void writeToBD(INovel iNovel, NovelAttribute novelAttribute, Element e);

}

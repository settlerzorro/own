package novel;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import novel.entity.NovelAttribute;
import novel.mongodb.NovelImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;


/**
 * @author : zhangshuai
 * @date : 14:04 2020/9/30
 */
public class SpiderNovel {
    private static final String dingdian = "https://www.booktxt.net/17_17791/";
    public static void main(String[] args) {
        INovel iNovel = (INovel) new NovelImpl();
        Long startTime, endTime;
        startTime = new Date().getTime();
        try {
            NovelAttribute novelAttribute = new NovelAttribute();
            Elements results = getDingdianElements(novelAttribute);
            BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\N-20HEPF129J9N-Data\\shuaizha\\Desktop\\test.txt"));
            StringBuilder txt = new StringBuilder();
            for (Element e : results) {
                String fictionChapter = e.text();
                String fictionUrl = e.attr("href");
                Document doc = getDocByJsoup(dingdian + fictionUrl, "GBK");
                Elements results2 = doc.select("#content");
                String content = Jsoup.clean(results2.toString(), "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false)).replaceAll("&nbsp;", "");
                writeToLocalFile(txt,fictionChapter,content);
            }
            bw.write(txt.toString());
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        endTime = new Date().getTime();
    }

    private static void writeToLocalFile(StringBuilder txt ,String fictionChapter,String content){
        txt.append(fictionChapter).append("\n").append(content).append("\n");
    }

    private static void writeToBD(INovel iNovel,NovelAttribute novelAttribute,Element e){
        String fictionChapter = e.text();
        String fictionUrl = e.attr("href");
        novelAttribute.setFictionUrl(dingdian + fictionUrl);
        Document doc = getDocByJsoup(novelAttribute.getFictionUrl(), "GBK");
        Elements results2 = doc.select("#content");
        String content = Jsoup.clean(results2.toString(), "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false)).replaceAll("&nbsp;", "");
        novelAttribute.setContent(content);
        novelAttribute.setFictionChapter(fictionChapter);
        iNovel.SaveAttribute(novelAttribute);
    }

    private static Elements getDingdianElements(NovelAttribute novelAttribute) {
        Document document = getDocByJsoup(dingdian, "GBK");
        String fictionName = document.select("div>h1").text();
        novelAttribute.setFictionName(fictionName);
        return document.select("dd>a");
    }

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
}

package novel;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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
    private static final String dingdian =  "https://www.booktxt.net/20_20832/";
    private static final String qidian =  "https://book.qidian.com/info/1018027842#Catalog";

    public static void main(String[] args) {
        // ���ݳ־û��������ڽ���Ϣ�������ݿ�
        INovel iNovel = (INovel) new NovelImpl();
        Long startTime, endTime;
        System.out.println("С���濪ʼ�ˡ���������������������");
        startTime = new Date().getTime();
        try {
            NovelAttribute novelAttribute = new NovelAttribute();
            //Elements results = getQidianElements(novelAttribute);
            Elements results = getDingdianElements(novelAttribute);
            for (Element e : results) {
                String fictionChapter = e.text();
                String fictionUrl = e.attr("href");
                //novelAttribute.setFictionUrl("https:" + fictionUrl);//���

                //���
//                if(fictionUrl.contains("vip")){
//                    Document doc = getDocByJsoup(novelAttribute.getFictionUrl(),"UTF-8");
//                    Elements results2 = doc.select("[class=read-content j_readContent");
//                    System.out.println(Jsoup.clean(results2.toString(), "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false)));
//                }

                novelAttribute.setFictionUrl(dingdian+fictionUrl);//����
                //����

                Document doc = getDocByJsoup(novelAttribute.getFictionUrl(),"GBK");
                Elements results2 = doc.select("#content");
                String content = Jsoup.clean(results2.toString(), "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false)).replaceAll("&nbsp;","");
                novelAttribute.setContent(content);
                novelAttribute.setFictionChapter(fictionChapter);
                iNovel.SaveAttribute(novelAttribute);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        endTime = new Date().getTime();
        System.out.println("С��������ˣ���ʱ" + (endTime - startTime) + "ms");
    }

    private static Elements getQidianElements(NovelAttribute novelAttribute){
        Document document = getDocByJsoup(qidian,"UTF-8");
        String fictionName = document.select("h1>em").text();
        novelAttribute.setFictionName(fictionName);
        return document.select("a[data-cid]");
    }

    private static Elements getDingdianElements(NovelAttribute novelAttribute){
        Document document = getDocByJsoup(dingdian,"GBK");
        String fictionName = document.select("div>h1").text();
        novelAttribute.setFictionName(fictionName);
        return document.select("dd>a");
    }
    public static Document getDocByJsoup(String href,String charsetName){
        String ip = "135.251.33.15";

        int port = 8080;
        StringBuffer bs = new StringBuffer();
        try {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, port));



            URL url = new URL(href);

            URLConnection urlcon = (URLConnection)url.openConnection(proxy);

            urlcon.connect();         //��ȡ����

            InputStream is = urlcon.getInputStream();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(is,charsetName));
            String l = null;

            while((l=buffer.readLine())!=null){

                bs.append(l+"\n");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(bs.toString());
        return Jsoup.parse(bs.toString());
    }
}

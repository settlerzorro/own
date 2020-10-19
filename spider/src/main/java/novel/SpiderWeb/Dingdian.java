package novel.SpiderWeb;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import novel.INovel;
import novel.SpiderNovel;
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
public class Dingdian extends SpiderNovel {
    public void run() {
        INovel iNovel = (INovel) new NovelImpl();
        Long startTime, endTime;
        startTime = new Date().getTime();
        try {
            NovelAttribute novelAttribute = new NovelAttribute();
            Elements results = getElements(novelAttribute);
            BufferedWriter bw = new BufferedWriter(new FileWriter(LOCAL_FILE));
            StringBuilder txt = new StringBuilder();
            for (Element e : results) {
                String fictionChapter = e.text();
                String fictionUrl = e.attr("href");
                Document doc = getDocByJsoup(getUrl() + fictionUrl, "GBK");
                Elements results2 = doc.select("#content");
                String content = Jsoup.clean(results2.toString(), "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false)).replaceAll("&nbsp;", "");
                writeToLocalFile(txt,fictionChapter,content);
                //writeToBD(iNovel,novelAttribute,e);
            }
            bw.write(txt.toString());
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void writeToBD(INovel iNovel,NovelAttribute novelAttribute,Element e){
        String fictionChapter = e.text();
        String fictionUrl = e.attr("href");
        novelAttribute.setFictionUrl(getUrl() + fictionUrl);
        Document doc = getDocByJsoup(novelAttribute.getFictionUrl(), "GBK");
        Elements results2 = doc.select("#content");
        String content = Jsoup.clean(results2.toString(), "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false)).replaceAll("&nbsp;", "");
        novelAttribute.setContent(content);
        novelAttribute.setFictionChapter(fictionChapter);
        iNovel.SaveAttribute(novelAttribute);
    }

    @Override
    protected Elements getElements(NovelAttribute novelAttribute) {
        Document document = getDocByJsoup(getUrl(), "GBK");
        String fictionName = document.select("div>h1").text();
        novelAttribute.setFictionName(fictionName);
        return document.select("dd>a");
    }
}

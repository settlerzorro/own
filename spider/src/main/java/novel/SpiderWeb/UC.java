package novel.SpiderWeb;

import novel.INovel;
import novel.SpiderNovel;
import novel.entity.NovelAttribute;
import novel.mongodb.NovelImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Date;


/**
 * @author : zhangshuai
 * @date : 14:04 2020/9/30
 */
public class UC extends SpiderNovel {
    //https://m.sm.cn/api/rest?method=novelintro.menuindex&format=html&title=%E5%A4%A7%E5%94%90%E7%AC%AC%E4%B8%80%E4%B8%96%E5%AE%B6&author=%E6%99%B4%E4%BA%86&novelid=%E6%99%B4%E4%BA%86%2F%E5%A4%A7%E5%94%90%E7%AC%AC%E4%B8%80%E4%B8%96%E5%AE%B6&host=biqufu.com&token=4d3b305716875e3f503c19e30f6a8b61&uc_param_str=dnntnwvepffrgibijbprsvpidsdichei&dn=34348223716-3a315a61&nt=1&nw=4G&ve=13.1.3.1093&pf=145&fr=android&gi=bTkwBPnMf03TV5%2BI2GfJQiwCrwqkGxDXSXN4hmOgO8j14Gg%3D&bi=34464&jb=0&pr=UCMobile&sv=ucrelease&pi=1200x2499&ds=bTkwBCp2FRyoguzZbErkAUnBgJRAhZocLRFFKpLGmlsiGg%3D%3D&di=6276dcca6c703c90&ch=yzappstore%40&ei=bTkwBCp1pSPI6Se8KjcmeEi13%2Bgx%2Ff5OuA%3D%3D&from=wh10026
   // private static final String dingdian = "https://www.booktxt.net/19_19335/";
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
        Document document = getDocByJsoup(getUrl(), "UTF-8");
        String fictionName = document.select("div>h1").text();
        novelAttribute.setFictionName(fictionName);
        return document.select("dd>a");
    }
}

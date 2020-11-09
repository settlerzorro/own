package novel;

import inte.SpiderNovel;
import inte.UrlContent;
import novel.SpiderWeb.Dingdian;

/**
 * @author : zhangshuai
 * @date : 14:00 2020/10/19
 */
public class Main {
    public static void main(String[] args) {
        //顶点
        SpiderNovel ding = new Dingdian(UrlContent.DINGDIAN);
        ding.run();
    }
}

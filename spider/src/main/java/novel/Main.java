package novel;

import novel.SpiderWeb.Dingdian;
import novel.SpiderWeb.UC;

/**
 * @author : zhangshuai
 * @date : 14:00 2020/10/19
 */
public class Main {
    private static final String dingdian = "https://www.booktxt.net/19_19335/";
    private static final String uc = "https://m.sm.cn/api/rest?method=novelintro.menuindex&format=html&title=%E5%A4%A7%E5%94%90%E7%AC%AC%E4%B8%80%E4%B8%96%E5%AE%B6&author=%E6%99%B4%E4%BA%86&novelid=%E6%99%B4%E4%BA%86%2F%E5%A4%A7%E5%94%90%E7%AC%AC%E4%B8%80%E4%B8%96%E5%AE%B6&host=biqufu.com&token=4d3b305716875e3f503c19e30f6a8b61&uc_param_str=dnntnwvepffrgibijbprsvpidsdichei&dn=34348223716-3a315a61&nt=1&nw=4G&ve=13.1.3.1093&pf=145&fr=android&gi=bTkwBPnMf03TV5%2BI2GfJQiwCrwqkGxDXSXN4hmOgO8j14Gg%3D&bi=34464&jb=0&pr=UCMobile&sv=ucrelease&pi=1200x2499&ds=bTkwBCp2FRyoguzZbErkAUnBgJRAhZocLRFFKpLGmlsiGg%3D%3D&di=6276dcca6c703c90&ch=yzappstore%40&ei=bTkwBCp1pSPI6Se8KjcmeEi13%2Bgx%2Ff5OuA%3D%3D&from=wh10026";
    public static void main(String[] args) {
        //顶点
        SpiderNovel ding = new Dingdian();
        ding.setUrl(dingdian);
        ding.run();
//        SpiderNovel u = new UC();
//        u.setUrl(uc);
//        u.run();
    }
}

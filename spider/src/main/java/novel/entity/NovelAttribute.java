package novel.entity;

/**
 * @author : zhangshuai
 * @date : 14:02 2020/9/30
 */
public class NovelAttribute {
    private String id; // id
    private String FictionName; // С˵��
    private String FictionChapter; // С˵�½��Լ��½���
    private String FictionUrl; // �½�����
    private String content;//�½�����

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFictionName() {
        return FictionName;
    }

    public void setFictionName(String fictionName) {
        FictionName = fictionName;
    }

    public String getFictionChapter() {
        return FictionChapter;
    }

    public void setFictionChapter(String fictionChapter) {
        FictionChapter = fictionChapter;
    }

    public String getFictionUrl() {
        return FictionUrl;
    }

    public void setFictionUrl(String fictionUrl) {
        FictionUrl = fictionUrl;
    }

    public String toString() {
        return "NovelAttribute [id=" + id + ",FictionName=" + FictionName + ", FictionChapter=" + FictionChapter + ","
                + " FictionUrl=" + FictionUrl + ",conten=" + content +"]";
    }
}

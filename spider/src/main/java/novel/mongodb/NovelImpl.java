package novel.mongodb;


import com.mongodb.client.MongoCollection;
import novel.INovel;
import novel.entity.NovelAttribute;
import org.bson.Document;

/**
 * @author : zhangshuai
 * @date : 14:03 2020/9/30
 */
public class NovelImpl implements INovel {
    public int SaveAttribute(NovelAttribute attribute) {
        String dbName = "own_test";
        String collName = "novel";
        MongoCollection<Document> coll = MongoUtil.instance.getCollection(dbName, collName);
        Document doc = new Document();
        doc.put("FictionName",attribute.getFictionName());
        doc.put("content",attribute.getContent());
        doc.put("FictionChapter",attribute.getFictionChapter());
        doc.put("FictionUrl",attribute.getFictionUrl());
        coll.insertOne(doc);
        return 1;
    }
}

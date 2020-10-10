package novel.mongodb;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.*;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;

/**
 * @author : zhangshuai
 * @date : 16:23 2020/10/9
 */
public enum  MongoUtil {
    /**
     * ����һ��ö�ٵ�Ԫ�أ�����������һ��ʵ��
     */
    instance;

    private static MongoClient mongoClient;

    static {
        System.out.println("===============MongoDBUtil��ʼ��========================");
        List<ServerAddress> adds = new ArrayList<ServerAddress>();
        ServerAddress serverAddress = new ServerAddress("135.251.103.226", 27017);
        adds.add(serverAddress);
        List<MongoCredential> credentials = new ArrayList<MongoCredential>();
        MongoCredential mongoCredential = MongoCredential.createCredential("enms", "admin", "enms".toCharArray());
        credentials.add(mongoCredential);
        instance.mongoClient = new MongoClient(adds, credentials);
        // �󲿷��û�ʹ��mongodb���ڰ�ȫ�����£��������mongodb��Ϊ��ȫ��֤ģʽ������Ҫ�ڿͻ����ṩ�û��������룺
        // boolean auth = db.authenticate(myUserName, myPassword);
        Builder options = new MongoClientOptions.Builder();
        options.cursorFinalizerEnabled(true);
        // options.autoConnectRetry(true);// �Զ�����true
        // options.maxAutoConnectRetryTime(10); // the maximum auto connect retry time
        options.connectionsPerHost(300);// ���ӳ�����Ϊ300������,Ĭ��Ϊ100
        options.connectTimeout(30000);// ���ӳ�ʱ���Ƽ�>3000����
        options.maxWaitTime(5000); //
        options.socketTimeout(0);// �׽��ֳ�ʱʱ�䣬0������
        options.threadsAllowedToBlockForConnectionMultiplier(5000);// �̶߳���������������߳������˶��оͻ��׳���Out of semaphores to get db������
        options.writeConcern(WriteConcern.SAFE);//
        options.build();
    }

    // ------------------------------------���÷���---------------------------------------------------
    /**
     * ��ȡDBʵ�� - ָ��DB
     *
     * @param dbName
     * @return
     */
    public MongoDatabase getDB(String dbName) {
        if (dbName != null && !"".equals(dbName)) {
            MongoDatabase database = mongoClient.getDatabase(dbName);
            return database;
        }
        return null;
    }

    /**
     * ��ȡcollection���� - ָ��Collection
     *
     * @param collName
     * @return
     */
    public MongoCollection<Document> getCollection(String dbName, String collName) {
        if (null == collName || "".equals(collName)) {
            return null;
        }
        if (null == dbName || "".equals(dbName)) {
            return null;
        }
        MongoCollection<Document> collection = mongoClient.getDatabase(dbName).getCollection(collName);
        return collection;
    }

    /**
     * ��ѯDB�µ����б���
     */
    public List<String> getAllCollections(String dbName) {
        MongoIterable<String> colls = getDB(dbName).listCollectionNames();
        List<String> _list = new ArrayList<String>();
        for (String s : colls) {
            _list.add(s);
        }
        return _list;
    }

    /**
     * ��ȡ�������ݿ������б�
     *
     * @return
     */
    public MongoIterable<String> getAllDBNames() {
        MongoIterable<String> s = mongoClient.listDatabaseNames();
        return s;
    }

    /**
     * ɾ��һ�����ݿ�
     */
    public void dropDB(String dbName) {
        getDB(dbName).drop();
    }

    /**
     * ���Ҷ��� - ��������_id
     *
     * @param collection
     * @param id
     * @return
     */
    public Document findById(MongoCollection<Document> coll, String id) {
        ObjectId _idobj = null;
        try {
            _idobj = new ObjectId(id);
        } catch (Exception e) {
            return null;
        }
        Document myDoc = coll.find(Filters.eq("_id", _idobj)).first();
        return myDoc;
    }

    /** ͳ���� */
    public int getCount(MongoCollection<Document> coll) {
        int count = (int) coll.count();
        return count;
    }

    /** ������ѯ */
    public MongoCursor<Document> find(MongoCollection<Document> coll, Bson filter) {
        return coll.find(filter).iterator();
    }

    /** ��ҳ��ѯ */
    public MongoCursor<Document> findByPage(MongoCollection<Document> coll, Bson filter, int pageNo, int pageSize) {
        Bson orderBy = new BasicDBObject("_id", 1);
        return coll.find(filter).sort(orderBy).skip((pageNo - 1) * pageSize).limit(pageSize).iterator();
    }


    /**
     * ͨ��IDɾ��
     *
     * @param coll
     * @param id
     * @return
     */
    public int deleteById(MongoCollection<Document> coll, String id) {
        int count = 0;
        ObjectId _id = null;
        try {
            _id = new ObjectId(id);
        } catch (Exception e) {
            return 0;
        }
        Bson filter = Filters.eq("_id", _id);
        DeleteResult deleteResult = coll.deleteOne(filter);
        count = (int) deleteResult.getDeletedCount();
        return count;
    }

    /**
     * FIXME
     *
     * @param coll
     * @param id
     * @param newdoc
     * @return
     */
    public Document updateById(MongoCollection<Document> coll, String id, Document newdoc) {
        ObjectId _idobj = null;
        try {
            _idobj = new ObjectId(id);
        } catch (Exception e) {
            return null;
        }
        Bson filter = Filters.eq("_id", _idobj);
        // coll.replaceOne(filter, newdoc); // ��ȫ���
        coll.updateOne(filter, new Document("$set", newdoc));
        return newdoc;
    }

    public void dropCollection(String dbName, String collName) {
        getDB(dbName).getCollection(collName).drop();
    }

    /**
     * �ر�Mongodb
     */
    public void close() {
        if (mongoClient != null) {
            mongoClient.close();
            mongoClient = null;
        }
    }
}

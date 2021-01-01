package top.l1hy.utils;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * @author 11518
 */
public class MongoDBUtil {
    /**
     * mongodb数据库ip地址
     */
    public final static String MONGO_IP_PATH = "127.0.0.1";
    /**
     * 端口号
     */
    public final static Integer MONGO_PORT = 27017;
    /**
     * 数据库名称
     */
    public final static String MONGO_NAME = "crypto";

    /**
     * @MethodDesc 获取collection
     * @Return MongoCollection<Document>
     * @Author l1hy
     */
    public static MongoCollection<Document> getCollection(String collectionName){
        MongoClient mongoClient = new MongoClient(MONGO_IP_PATH, MONGO_PORT);
        System.out.println(mongoClient);
        MongoDatabase mongoDatabase = mongoClient.getDatabase(MONGO_NAME);
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        System.out.println("Connect to mongodb database successfully");
        return collection;
    }

}

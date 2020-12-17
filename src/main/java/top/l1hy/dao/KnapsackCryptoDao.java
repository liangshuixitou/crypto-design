package top.l1hy.dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import top.l1hy.pojo.KnapsackCrypto;
import top.l1hy.utils.MongoDBUtils;

import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author l1hy
 */
public class KnapsackCryptoDao {
    private static MongoCollection<Document> collection = MongoDBUtils.getCollection("knapsack");

    public void insertOneKnapsack (KnapsackCrypto kc, String sessionName) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        Document document = Document.parse(kc.toJsonString());
        document.append("sessionName", sessionName);

        System.out.println(document);
        collection.insertOne(document);
    }

    public List<Map<String, Object>> findAllKnapsack () {
        FindIterable<Document> findIterable = collection.find();

        List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();

        for (Document document : findIterable) {
            KnapsackCrypto kc = new KnapsackCrypto();
            Map<String, Object> result = new HashMap<String, Object>();
            kc.setMAX(document.getInteger("MAX"));
            kc.setK(new BigInteger(String.valueOf(document.getInteger("k"))));
            kc.setT(new BigInteger(String.valueOf(document.getInteger("t"))));
            kc.setSuperSetLong((ArrayList<Integer>) document.get("superSet"));
            kc.setPublicKeySetLong((ArrayList<Integer>) document.get("publicKeySet"));
            System.out.println(kc);

            result.put("sessionName", document.get("sessionName"));
            result.put("knapsackCrypto", kc);
            results.add(result);
        }
        System.out.println(results);
        return results;
    }

    public void updateKnapsack(KnapsackCrypto kc, String sessionName) {
        Document document = Document.parse(kc.toJsonString());
        document.append("sessionName", sessionName);

        System.out.println(document);
        collection.updateOne(Filters.eq("sessionName", sessionName), new Document("$set", document));
    }
}

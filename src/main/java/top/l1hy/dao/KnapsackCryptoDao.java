package top.l1hy.dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import top.l1hy.pojo.KnapsackCrypto;
import top.l1hy.utils.KnapsackCryptoUtil;
import top.l1hy.utils.MongoDBUtil;

import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.util.*;

/**
 * @author l1hy
 */
public class KnapsackCryptoDao {
    private static MongoCollection<Document> collection = MongoDBUtil.getCollection("knapsack");

    public void insertOneKnapsack (KnapsackCrypto kc, String sessionName) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        Document document = Document.parse(kc.toJsonString());
        document.append("sessionName", sessionName);

        collection.insertOne(document);

        String superSetString = document.get("superSet").toString();
        String publicKeySetString = document.get("publicKeySet").toString();
        kc.setSuperSetString(superSetString);
        kc.setPublicKeySetString(publicKeySetString);
    }

    public List<Map<String, Object>> findAllKnapsack () {
        FindIterable<Document> findIterable = collection.find();

        List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();

        for (Document document : findIterable) {
            KnapsackCrypto kc = new KnapsackCrypto();
            Map<String, Object> result = new HashMap<String, Object>();
            kc.setMAX(document.getInteger("MAX"));
            kc.setK(new BigInteger(document.get("k").toString()));
            kc.setT(new BigInteger(document.get("t").toString()));

            String superSetString = document.get("superSet").toString();
            String[] superSet = (superSetString).split(",");

            String publicKeySetString = document.get("publicKeySet").toString();
            String[] publicKeySet = (publicKeySetString).split(",");

            kc.setSuperSetString(superSetString);
            kc.setPublicKeySetString(publicKeySetString);

            kc.setSuperSetLong(superSet);
            kc.setPublicKeySetLong(publicKeySet);

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
        collection.updateOne(Filters.eq("sessionName", sessionName), new Document("$set", document));

        String superSetString = document.get("superSet").toString();
        String publicKeySetString = document.get("publicKeySet").toString();
        kc.setSuperSetString(superSetString);
        kc.setPublicKeySetString(publicKeySetString);
    }

    public KnapsackCrypto findOneSession(String sessionName) {
        FindIterable<Document> findIterable = collection.find(Filters.eq("sessionName", sessionName));
        Document document = findIterable.first();

        KnapsackCrypto kc = new KnapsackCrypto();
        kc.setMAX(document.getInteger("MAX"));
        kc.setK(new BigInteger(document.get("k").toString()));
        kc.setT(new BigInteger(document.get("t").toString()));

        String superSetString = document.get("superSet").toString();
        String[] superSet = (superSetString).split(",");

        String publicKeySetString = document.get("publicKeySet").toString();
        String[] publicKeySet = (publicKeySetString).split(",");

        kc.setSuperSetString(superSetString);
        kc.setPublicKeySetString(publicKeySetString);

        kc.setSuperSetLong(superSet);
        kc.setPublicKeySetLong(publicKeySet);

        return kc;
    }

    public void deleteKnapsack(String sessionName) {
        collection.deleteOne(Filters.eq("sessionName", sessionName));
    }
}

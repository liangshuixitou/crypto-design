package l1hy.top.dao;

import org.junit.Test;
import top.l1hy.dao.KnapsackCryptoDao;
import top.l1hy.pojo.KnapsackCrypto;
import top.l1hy.utils.KnapsackCryptoUtils;

import java.lang.reflect.InvocationTargetException;

public class KnapsackCryptoDaoTest {
    @Test
    public void testInsertOne () throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        KnapsackCryptoDao dao = new KnapsackCryptoDao();
        KnapsackCrypto kc = new KnapsackCrypto();

        dao.insertOneKnapsack(kc, "l1hy");
    }

    @Test
    public void testFindAll () {
        KnapsackCryptoDao dao = new KnapsackCryptoDao();
        dao.findAllKnapsack();
    }

    @Test
    public void testUpdateOne () {
        KnapsackCryptoDao dao = new KnapsackCryptoDao();
        KnapsackCrypto kc = new KnapsackCrypto();
        kc.setMAX(20);
        KnapsackCryptoUtils.generateSetAndPublicKey(kc);

        dao.updateKnapsack(kc, "l2hy");
    }
}

package l1hy.top.dao;

import org.junit.Test;
import top.l1hy.dao.KnapsackCryptoDao;
import top.l1hy.pojo.KnapsackCrypto;
import top.l1hy.utils.KnapsackCryptoUtil;

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
        KnapsackCryptoUtil.generateSetAndPublicKey(kc);

        dao.updateKnapsack(kc, "l2hy");
    }

    @Test
    public void testDelete () {
        KnapsackCryptoDao dao = new KnapsackCryptoDao();
        dao.deleteKnapsack("l3hy");
    }

    @Test
    public void testFindOne () {
        KnapsackCryptoDao dao = new KnapsackCryptoDao();
        System.out.println(dao.findOneSession("l1hy"));
    }

    @Test
    public void testToJson () {
        System.out.println(new KnapsackCrypto().toJsonString());
    }
}

package l1hy.top.utils;

import org.junit.Test;
import top.l1hy.pojo.KnapsackCrypto;
import top.l1hy.utils.KnapsackCryptoUtils;

import java.math.BigInteger;

public class KnapsackCryptoUtilsTest {
    @Test
    public void testUtils (){
        KnapsackCrypto knapsackCrypto = new KnapsackCrypto();

        System.out.println("随机产生的公钥：");
        System.out.print("(");
        BigInteger[] publicKeySet = knapsackCrypto.getPublicKeySet();
        for (int i = 0; i < knapsackCrypto.getMAX()- 1; i++)
            System.out.print(publicKeySet[i] + ",");
        System.out.println(publicKeySet[knapsackCrypto.getMAX() - 1] + ")");

        System.out.println(knapsackCrypto);
        System.out.println("随机产生的超递增背包：");
        System.out.print("(");
        BigInteger[] superSet = knapsackCrypto.getSuperSet();
        for (int i = 0; i < knapsackCrypto.getMAX()- 1; i++)
            System.out.print(superSet[i] + ",");
        System.out.println(superSet[knapsackCrypto.getMAX() - 1] + ")");
        // 随机产生5组消息
        for (int j = 0; j < 5; j++) {
            String x = "";
            for (int i = 0; i < knapsackCrypto.getMAX(); i++) {
                if (Math.random() < 0.5) {
                    x = x.concat("1");
                } else
                    x = x.concat("0");
            }
            // 加密x信息，得到密文S
            System.out.println("第" + (j + 1) + "组随机产生的明文： " + x);
            String S = KnapsackCryptoUtils.encrypt(x, knapsackCrypto).toString();
            System.out.println("加密得到的密文：" + S);
            System.out.println("解密得到的明文：" + KnapsackCryptoUtils.decrypt(new BigInteger(S), knapsackCrypto));

        }
    }
}

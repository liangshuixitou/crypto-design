package l1hy.top.utils;

import org.junit.Test;
import top.l1hy.utils.KnapsackCryptoUtils;

import java.math.BigInteger;

public class KnapsackCryptoUtilsTest {
    @Test
    public void testUtils (){
        KnapsackCryptoUtils kc = new KnapsackCryptoUtils();
        kc.generateSetAndPublicKey();
        System.out.println("随机产生的公钥：");
        System.out.print("(");
        BigInteger[] publicKeySet = kc.getPublicKeySet();
        for (int i = 0; i < kc.getMAX()- 1; i++)
            System.out.print(publicKeySet[i] + ",");
        System.out.println(publicKeySet[kc.getMAX() - 1] + ")");
        // 随机产生5组消息
        for (int j = 0; j < 5; j++) {
            String x = "";
            for (int i = 0; i < kc.getMAX(); i++) {
                if (Math.random() < 0.5) {
                    x = x.concat("1");
                } else
                    x = x.concat("0");
            }
            // 加密x信息，得到密文S
            System.out.println("第" + (j + 1) + "组随机产生的明文： " + x);
            String S = kc.encrypt(x).toString();
            System.out.println("加密得到的密文：" + S);
            System.out.println("解密得到的明文" + kc.decrypt(new BigInteger(S)));

        }
    }
}

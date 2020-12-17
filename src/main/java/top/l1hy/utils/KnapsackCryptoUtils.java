package top.l1hy.utils;

import top.l1hy.pojo.KnapsackCrypto;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author l1hy
 * @Description 背包密码体制工具类，用来随机生成密钥，并进行加解密运算
 */
public class KnapsackCryptoUtils {

    // 生成随机超递增背包向量
    // --------------------

    public static void generateSetAndPublicKey(KnapsackCrypto knapsackCrypto) {
        // 生成超递增背包向量
        BigInteger[] superSet = new BigInteger[knapsackCrypto.getMAX()];
        BigInteger[] publicKeySet = new BigInteger[knapsackCrypto.getMAX()];
        BigInteger k, t;

        superSet[0] = (new BigDecimal("10").multiply(new BigDecimal(Math
                .random() + "")).add(BigDecimal.ONE)).toBigInteger();
        for (int i = 1; i < knapsackCrypto.getMAX(); i++) {
            superSet[i] = (superSet[i - 1].multiply(new BigInteger("2"))
                    .add(new BigDecimal("10")
                            .multiply(new BigDecimal(Math.random() + ""))
                            .add(BigDecimal.ONE).toBigInteger()));
        }

        k = (superSet[knapsackCrypto.getMAX() - 1].multiply(new BigInteger("2"))
                .add(new BigDecimal("10")
                        .multiply(new BigDecimal(Math.random() + ""))
                        .add(BigDecimal.ONE).toBigInteger()));

        t = superSet[knapsackCrypto.getMAX() - 1];

        do {
            t = t.subtract(BigInteger.ONE);
        } while (!k.gcd(t).equals(BigInteger.ONE));

        for (int i = 0; i < knapsackCrypto.getMAX(); i++) {
            publicKeySet[i] = t.multiply(superSet[i]).mod(k);
        }

        knapsackCrypto.setSuperSet(superSet);
        knapsackCrypto.setPublicKeySet(publicKeySet);
        knapsackCrypto.setT(t);
        knapsackCrypto.setK(k);
    }

    public static BigInteger encrypt(String m, KnapsackCrypto knapsackCrypto) {
        if (m == null) {
            return new BigInteger(String.valueOf(0));
        }

        BigInteger s = BigInteger.ZERO;
        for (int i = m.length(); i < knapsackCrypto.getMAX(); i++) {
            m = m.concat("0");
        }

        // local var
        BigInteger[] publicKeySet = knapsackCrypto.getPublicKeySet();
        for (int i = 0; i < knapsackCrypto.getMAX(); i++) {
            if (m.charAt(i) == '1') {
                s = s.add(publicKeySet[i]);
            }
        }
        return s;
    }

    public static String decrypt(BigInteger c, KnapsackCrypto knapsackCrypto) {
        BigInteger s;
        String x = "";
        s = knapsackCrypto.getT().modInverse(knapsackCrypto.getK()).multiply(c).mod(knapsackCrypto.getK());
        int num = knapsackCrypto.getMAX();

        // local var
        BigInteger[] superSet = knapsackCrypto.getSuperSet();
        while (true) {
            if (s.subtract(superSet[num - 1]).signum() > 0) {
                s = s.subtract(superSet[num - 1]);
                x = "1".concat(x);
            } else if (s.subtract(superSet[num - 1]).signum() == 0) {
                x = "1".concat(x);
                break;
            } else {
                x = "0".concat(x);
            }
            num--;
        }
        for (int i = 0; i < num - 1; i++) {
            x = "0".concat(x);
        }
        return x;
    }
}

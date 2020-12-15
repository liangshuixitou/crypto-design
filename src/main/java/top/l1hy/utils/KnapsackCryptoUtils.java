package top.l1hy.utils;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author l1hy
 * @Description 背包密码体制工具类，用来随机生成密钥，并进行加解密运算
 */
public class KnapsackCryptoUtils {

    // 1. 超递增背包向量长度
    // 2. 超递增背包向量
    // 3. 公钥
    // 4. 私钥

    private final int MAX = 10;
    BigInteger[] superSet = new BigInteger[MAX];
    BigInteger[] publicKeySet = new BigInteger[MAX];
    BigInteger k, t;

    public int getMAX() {
        return MAX;
    }

    public BigInteger[] getSuperSet() {
        return superSet;
    }

    public BigInteger[] getPublicKeySet() {
        return publicKeySet;
    }

    public BigInteger getK() {
        return k;
    }

    public BigInteger getT() {
        return t;
    }

    // 生成随机超递增背包向量
    // --------------------

    public void generateSetAndPublicKey() {
        superSet[0] = (new BigDecimal("10").multiply(new BigDecimal(Math
                .random() + "")).add(BigDecimal.ONE)).toBigInteger();
        for (int i = 1; i < MAX; i++) {
            superSet[i] = (superSet[i - 1].multiply(new BigInteger("2"))
                    .add(new BigDecimal("10")
                            .multiply(new BigDecimal(Math.random() + ""))
                            .add(BigDecimal.ONE).toBigInteger()));
        }
        k = (superSet[MAX - 1].multiply(new BigInteger("2"))
                .add(new BigDecimal("10")
                        .multiply(new BigDecimal(Math.random() + ""))
                        .add(BigDecimal.ONE).toBigInteger()));

        t = superSet[MAX - 1];

        do {
            t = t.subtract(BigInteger.ONE);
        } while (!k.gcd(t).equals(BigInteger.ONE));

        for (int i = 0; i < MAX; i++) {
            publicKeySet[i] = t.multiply(superSet[i]).mod(k);
        }
    }

    public BigInteger encrypt(String m) {
        BigInteger s = BigInteger.ZERO;
        for (int i = m.length(); i < MAX; i++) {
            m = m.concat("0");
        }

        for (int i = 0; i < MAX; i++) {
            if (m.charAt(i) == '1') {
                s = s.add(publicKeySet[i]);
            }
        }
        return s;
    }

    public String decrypt(BigInteger c) {
        BigInteger s;
        String x = "";
        s = t.modInverse(k).multiply(c).mod(k);
        int num = MAX;
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

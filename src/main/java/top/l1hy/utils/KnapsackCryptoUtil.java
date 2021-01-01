package top.l1hy.utils;

import top.l1hy.pojo.KnapsackCrypto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author l1hy
 * @Description 背包密码体制工具类，用来随机生成密钥，并进行加解密运算
 */
public class KnapsackCryptoUtil {

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

    // 给定待加密字符串，根据 Ascall 编码后分组成01字符串
    public static List<String> encode(String m, int MAX) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < m.length(); ++i) {
            int ch = m.charAt(i);
            StringBuilder miniBuilder = new StringBuilder();
            for (int j = 7; j >= 0; --j) {
                if (ch >= Math.pow(2, j)) {
                    miniBuilder.append(1);
                    ch -= Math.round(Math.pow(2, j));
                } else {
                    miniBuilder.append(0);
                }
            }
            builder.append(miniBuilder);
        }

        String str = builder.toString();

        List<String> results = new ArrayList<String>();
        int index = 0;
        while (true) {
            if (index + MAX >= str.length()) {
                results.add(str.substring(index));
                break;
            } else {
                results.add(str.substring(index, index + MAX));
            }
            index += MAX;
        }
        return results;
    }

    public static String decode(String code) {
        StringBuilder builder = new StringBuilder();
        int start = 0;
        while (start + 8 < code.length()) {
            String charCode = code.substring(start, start + 8);
            int t = 0;
            for (int i = 0; i < 8; ++i) {
                char c = charCode.charAt(i);
                t += (c == '1') ? Math.round(Math.pow(2, 7 - i)) : 0;
            }
            if (t == 0) {
                break;
            }
            char ch = (char) t;
            builder.append(ch);
            start += 8;
        }
        return builder.toString();
    }
}

package top.l1hy.pojo;

import top.l1hy.utils.KnapsackCryptoUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author l1hy
 */
public class KnapsackCrypto {

    // 1. 超递增背包向量长度
    // 2. 超递增背包向量
    // 3. 公钥
    // 4. 私钥

    private int MAX = 10;
    private BigInteger[] superSet;
    private BigInteger[] publicKeySet;
    private BigInteger k, t;

    public KnapsackCrypto() {
        KnapsackCryptoUtils.generateSetAndPublicKey(this);
    }

    public int getMAX() {
        return MAX;
    }

    public void setMAX(int MAX) {
        this.MAX = MAX;
    }

    public BigInteger[] getSuperSet() { return superSet; }

    public void setSuperSet(BigInteger[] superSet) {
        this.superSet = superSet;
    }

    public BigInteger[] getPublicKeySet() { return publicKeySet; }

    public void setPublicKeySet(BigInteger[] publicKeySet) {
        this.publicKeySet = publicKeySet;
    }

    public BigInteger getK() {
        return k;
    }

    public void setK(BigInteger k) {
        this.k = k;
    }

    public BigInteger getT() {
        return t;
    }

    public void setT(BigInteger t) {
        this.t = t;
    }

    @Override
    public String toString() {
        return "KnapsackCrypto{" +
                "MAX=" + MAX +
                ", superSet=" + Arrays.toString(superSet) +
                ", publicKeySet=" + Arrays.toString(publicKeySet) +
                ", k=" + k +
                ", t=" + t +
                '}';
    }

    public String toJsonString() {
        return "{" +
                "MAX:" + MAX +
                ", superSet:" + Arrays.toString(superSet) +
                ", publicKeySet:" + Arrays.toString(publicKeySet) +
                ", k:" + k +
                ", t:" + t +
                '}';
    }

    public void setPublicKeySetLong(ArrayList<Integer> list) {
        for (int i = 0; i < MAX; ++i) {
            publicKeySet[i] = new BigInteger(String.valueOf(list.get(i)));
        }
    }

    public void setSuperSetLong(ArrayList<Integer> list) {
        for (int i = 0; i < MAX; ++i) {
            superSet[i] = new BigInteger(String.valueOf(list.get(i)));
        }
    }
}

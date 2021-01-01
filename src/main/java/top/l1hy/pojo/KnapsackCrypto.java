package top.l1hy.pojo;

import top.l1hy.utils.JsonStringUtil;
import top.l1hy.utils.KnapsackCryptoUtil;

import java.math.BigInteger;
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
    private String superSetString;
    private String publicKeySetString;

    public KnapsackCrypto() {
        KnapsackCryptoUtil.generateSetAndPublicKey(this);
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

    public String getSuperSetString() {
        return superSetString;
    }

    public void setSuperSetString(String superSetString) {
        this.superSetString = superSetString;
    }

    public String getPublicKeySetString() {
        return publicKeySetString;
    }

    public void setPublicKeySetString(String publicKeySetString) {
        this.publicKeySetString = publicKeySetString;
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
                ", superSet:'" + JsonStringUtil.toJsonString(superSet) +
                "', publicKeySet:'" + JsonStringUtil.toJsonString(publicKeySet) +
                "', k:'" + k.toString() +
                "', t:'" + t.toString() +
                "'}";
    }

    public void setPublicKeySetLong(String[] list) {
        publicKeySet = new BigInteger[this.getMAX()];
        for (int i = 0; i < list.length; ++i) {
            publicKeySet[i] = new BigInteger(list[i]);
        }
    }

    public void setSuperSetLong(String[] list) {
        superSet = new BigInteger[this.getMAX()];
        for (int i = 0; i < list.length; ++i) {
            superSet[i] = new BigInteger(list[i]);
        }
    }
}

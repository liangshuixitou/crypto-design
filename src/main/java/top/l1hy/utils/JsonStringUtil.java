package top.l1hy.utils;

import java.math.BigInteger;

/**
 * @author l1hy
 */
public class JsonStringUtil {
    public static String toJsonString(BigInteger[] bigIntegers) {
        if (bigIntegers == null || bigIntegers.length == 0) {
            return "";
        } else {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < bigIntegers.length; ++i) {
                builder.append(bigIntegers[i]);
                if (i != bigIntegers.length - 1) {
                    builder.append(",");
                }
            }
            return builder.toString();
        }
    }
}

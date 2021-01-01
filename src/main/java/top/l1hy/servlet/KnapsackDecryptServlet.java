package top.l1hy.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import top.l1hy.dao.KnapsackCryptoDao;
import top.l1hy.pojo.KnapsackCrypto;
import top.l1hy.utils.KnapsackCryptoUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author l1hy
 */
@WebServlet("/knapsackDecrypt")
public class KnapsackDecryptServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));

        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        @SuppressWarnings("rawtypes")
        Map map = JSON.parseObject(sb.toString(), HashMap.class);
        String sessionName = (String) map.get("sessionName");

        JSONArray cipherText = JSONObject.parseArray(map.get("c").toString());
        List<BigInteger> bigIntegers = new ArrayList<>();
        for (Object c: cipherText) {
            bigIntegers.add(new BigInteger(c.toString()));
        }

        KnapsackCryptoDao dao = new KnapsackCryptoDao();
        KnapsackCrypto kc = dao.findOneSession(sessionName);

        StringBuilder builder = new StringBuilder();
        for (BigInteger bigInteger: bigIntegers) {
            builder.append(KnapsackCryptoUtil.decrypt(bigInteger, kc));
        }

        String m = KnapsackCryptoUtil.decode(builder.toString());
    }
}

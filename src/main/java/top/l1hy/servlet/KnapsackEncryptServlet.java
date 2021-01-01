package top.l1hy.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import top.l1hy.dao.KnapsackCryptoDao;
import top.l1hy.pojo.KnapsackCrypto;
import top.l1hy.pojo.ResultInfo;
import top.l1hy.utils.KnapsackCryptoUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author l1hy
 */
@WebServlet("/knapsackEncrypt")
public class KnapsackEncryptServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sessionName = req.getParameter("sessionName");
        String m = req.getParameter("m");

        System.out.println(sessionName);

        KnapsackCryptoDao dao = new KnapsackCryptoDao();
        KnapsackCrypto kc = dao.findOneSession(sessionName);


        List<String> byteStrings = KnapsackCryptoUtil.encode(m, kc.getMAX());
        List<String> results = new ArrayList<>();

        for (String byteString: byteStrings) {
            BigInteger result = KnapsackCryptoUtil.encrypt(byteString, kc);
            results.add(result.toString());
        }

        System.out.println(kc);
        System.out.println(byteStrings);
        System.out.println(results);

        ResultInfo resultInfo = new ResultInfo();

        resultInfo.setFlag(true);
        resultInfo.setData(results);

        String json = new ObjectMapper().writeValueAsString(resultInfo);
        System.out.println(json);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
    }
}

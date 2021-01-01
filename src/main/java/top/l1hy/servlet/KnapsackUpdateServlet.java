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
import java.util.HashMap;
import java.util.Map;

/**
 * @author 11518
 */
@WebServlet("/knapsackUpdate")
public class KnapsackUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sessionName = req.getParameter("sessionName");
        int MAX = Integer.parseInt(req.getParameter("max"));

        KnapsackCrypto kc = new KnapsackCrypto();
        kc.setMAX(MAX);
        KnapsackCryptoUtil.generateSetAndPublicKey(kc);

        KnapsackCryptoDao dao = new KnapsackCryptoDao();
        dao.updateKnapsack(kc, sessionName);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sessionName", sessionName);
        map.put("knapsackCrypto", kc);

        ResultInfo resultInfo = new ResultInfo();

        resultInfo.setFlag(true);
        resultInfo.setData(map);

        String json = new ObjectMapper().writeValueAsString(resultInfo);
        System.out.println(json);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
    }
}

package top.l1hy.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import top.l1hy.dao.KnapsackCryptoDao;
import top.l1hy.pojo.KnapsackCrypto;
import top.l1hy.pojo.ResultInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author l1hy
 * @Description 根据sessionName获取新的背包密钥对，并存入数据库中
 */
@WebServlet("/knapsackGenerate")
public class KnapsackGenerateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sessionName = req.getParameter("sessionName");

        KnapsackCryptoDao dao = new KnapsackCryptoDao();
        KnapsackCrypto kc = new KnapsackCrypto();

        Map<String, Object> map = new HashMap<String, Object>();

        try {
            dao.insertOneKnapsack(kc, sessionName);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

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

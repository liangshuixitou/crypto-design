package top.l1hy.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import top.l1hy.dao.KnapsackCryptoDao;
import top.l1hy.pojo.ResultInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

/**
 * @author l1hy
 * @Description 根据sessionName获取新的背包密钥对，并存入数据库中
 */
@WebServlet("/knapsackSessionList")
public class KnapsackSessionListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        KnapsackCryptoDao dao = new KnapsackCryptoDao();
        List<Map<String, Object>> sessionList = dao.findAllKnapsack();

        ResultInfo resultInfo = new ResultInfo();

        if (sessionList.isEmpty()) {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("会话为空");
        } else {
            resultInfo.setFlag(true);
            resultInfo.setData(sessionList);
        }

        String json = new ObjectMapper().writeValueAsString(resultInfo);
        System.out.println(json);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
    }
}

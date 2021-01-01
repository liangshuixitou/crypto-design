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

/**
 * @author 11518
 */
@WebServlet("/knapsackDelete")
public class KnapsackDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sessionName = req.getParameter("sessionName");

        KnapsackCryptoDao dao = new KnapsackCryptoDao();
        dao.deleteKnapsack(sessionName);

        ResultInfo resultInfo = new ResultInfo();

        resultInfo.setFlag(true);

        String json = new ObjectMapper().writeValueAsString(resultInfo);
        System.out.println(json);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
    }
}

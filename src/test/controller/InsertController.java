package test.controller;

import test.dao.BoardDao;
import test.dto.Board;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/insert")
public class InsertController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String writer = req.getParameter("writer");
        String title = req.getParameter("title");
        String content = req.getParameter("content");

        Board board = new Board();
        board.setTitle(writer);
        board.setTitle(title);
        board.setContent(content);

        BoardDao dao = new BoardDao();
        dao.insert(board);
        resp.sendRedirect("home.jsp");
    }

}

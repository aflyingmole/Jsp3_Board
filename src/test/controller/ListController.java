package test.controller;

import test.dao.BoardDao;
import test.dto.Board;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/board/list")
public class ListController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String pNum = req.getParameter("pageNum");

        String field = req.getParameter("field");
        String keyword = req.getParameter("keyword");


        int pageNum = 1;
        if (pNum != null) {
            pageNum = Integer.parseInt(pNum);
        }
        int startRow = (pageNum * 10) - 9;
        int endRow = pageNum * 10;
        BoardDao dao = new BoardDao();
        ArrayList<Board> list = dao.list(startRow, endRow, field, keyword);

        int pageCount=(int)Math.ceil(dao.getCount(field, keyword)/10.0);
        int startPage = ((pageNum-1)/10)*10+1; //시작페이지 번호
        int endPage = startPage+9; //끝페이지 번호
        if(endPage > pageCount){
            endPage = pageCount;
        }

        req.setAttribute("list", list);
        req.setAttribute("pageCount", pageCount);
        req.setAttribute("startPage", startPage);
        req.setAttribute("endPage", endPage);
        req.setAttribute("pageNum", pageNum);
        req.setAttribute("field", field);
        req.setAttribute("keyword", keyword);
        req.getRequestDispatcher("/board/list.jsp").forward(req, resp);
    }
}



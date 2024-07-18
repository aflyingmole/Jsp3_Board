package test.dao;

import db.JdbcUtil;
import test.dto.Board;

import java.sql.*;
import java.util.ArrayList;

public class BoardDao {
    public int insert(Board board) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = JdbcUtil.getCon();
            String sql = "insert into myboard values(myboard_seq.nextval,?,?,?,sysdate)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, board.getWriter());
            pstmt.setString(2, board.getTitle());
            pstmt.setString(3, board.getContent());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        } finally {
            JdbcUtil.close(con, pstmt, null);
        }

    }

    public int getCount(String field, String keyword) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = JdbcUtil.getCon();
            String sql = "select count(*) from myboard";

            if (field != null && !field.equals("")) {
                sql += " where " + field + " like '%" + keyword + "%'";
            }

            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return -1;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return -1;
        } finally {
            JdbcUtil.close(con, pstmt, rs);
        }
    }

    public ArrayList<Board> list(int startRow, int endRow, String field, String keyword) {
        String sql = null;
        if (field == null || field.equals("")) {
            sql = "select * from (" +
                    "select b.*, rownum rnum from (" +
                    "select * from myboard order by num desc" +
                    ")b" +
                    ") where rnum >=? and rnum <=?";
        } else {
            sql = "select * from (" +
                    "select b.*, rownum rnum from (" +
                    "select * from myboard where " + field + " like '%" + keyword + "%' order by num desc" +
                    ")b" +
                    ") where rnum >=? and rnum <=?";
        }
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = JdbcUtil.getCon();

            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, startRow);
            pstmt.setInt(2, endRow);
            rs = pstmt.executeQuery();

            ArrayList<Board> list = new ArrayList<Board>();
            while (rs.next()) {
                int num = rs.getInt("num");
                String writer = rs.getString("writer");
                String title = rs.getString("title");
                String content = rs.getString("content");
                Date regdate = rs.getDate("regdate");
                Board vo = new Board(num, writer, title, content, regdate);
                list.add(vo);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            JdbcUtil.close(con, pstmt, rs);
        }
    }
}




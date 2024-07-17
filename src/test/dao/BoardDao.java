package test.dao;

import db.JdbcUtil;
import test.dto.Board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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


}

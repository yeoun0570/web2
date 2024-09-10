package com.ssg.web2.todo.dao;

import com.ssg.web2.todo.domain.MemberVO;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDAO {


    public MemberVO getWithPassword(String mid, String mpw) throws Exception {
        String sql = "SELECT mid, mpw, mname FROM tbl_member WHERE mid = ? AND mpw = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);

        pstmt.setString(1,mid);
        pstmt.setString(2,mpw);

        @Cleanup ResultSet rs = pstmt.executeQuery();

        rs.next();
        MemberVO vo = MemberVO.builder()
                .mid(rs.getString("mid"))
                .mpw(rs.getString("mpw"))
                .mname(rs.getString("mname"))
                .build();
        return vo;
    }
}

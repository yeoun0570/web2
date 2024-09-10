package com.ssg.web2.todo.dao;

import com.ssg.web2.todo.domain.TodoVO;
import com.ssg.web2.todo.dto.TodoDTO;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TodoDAO {
    public void insert(TodoVO vo) throws Exception {
        String sql = "insert into tbl_todo (tno,title,dueDate,finished) values(null,?,?,?)";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement psmt = connection.prepareStatement(sql);

        psmt.setString(1, vo.getTitle());
        psmt.setDate(2, Date.valueOf(vo.getDueDate()));
        psmt.setBoolean(3, vo.isFinished());

        psmt.executeUpdate();

    }

    public List<TodoVO> selectAllList() throws Exception {

        String sql = "select * from tbl_todo";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement psmt = connection.prepareStatement(sql);

        @Cleanup ResultSet rs = psmt.executeQuery();

        List<TodoVO> list = new ArrayList<>();

        while (rs.next()) {
            TodoVO vo = TodoVO.builder()
                    .tno(rs.getLong("tno"))
                    .title(rs.getString("title"))
                    .dueDate(rs.getDate("dueDate").toLocalDate())
                    .finished(rs.getBoolean("finished")).build();
            list.add(vo);

        }
        return list;

    }

    public TodoVO selectOne(long tno) throws Exception{


        String sql = "select * from tbl_todo where tno = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement psmt = connection.prepareStatement(sql);

        psmt.setLong(1,tno);

        @Cleanup ResultSet rs = psmt.executeQuery();

        rs.next();

        TodoVO vo = TodoVO.builder()
                .tno(rs.getLong("tno"))
                .title(rs.getString("title"))
                .dueDate(rs.getDate("dueDate").toLocalDate())
                .finished(rs.getBoolean("finished")).build();

        return  vo;

    }

    //삭제기능은 조회 와 비슷하지만 쿼리(select)가 아니다.  삭제 특정번호가 필요하다.

    public void deleteOne(Long tno) throws Exception{

          String sql = "delete from tbl_todo where tno = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement psmt = connection.prepareStatement(sql);

        psmt.setLong(1,tno);

        psmt.executeUpdate();
    }
   public void updateOne(TodoVO vo)throws  Exception{

        String sql = "update tbl_todo set title = ? , dueDate = ? , finished =? where tno = ?";

       @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
       @Cleanup PreparedStatement psmt = connection.prepareStatement(sql);

       psmt.setString(1,vo.getTitle());
       psmt.setDate(2,Date.valueOf(vo.getDueDate()));
       psmt.setBoolean(3,vo.isFinished());
       psmt.setLong(4, vo.getTno());

       psmt.executeUpdate();
   }



}

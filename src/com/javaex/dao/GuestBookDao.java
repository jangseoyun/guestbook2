package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.GuestbookVo;

public class GuestBookDao {

	// 필드
	// 0. import java.sql.*;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";

	private void getConnection() {

		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		}

	}

	private void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}
	// 생성자

	// 메소드 g,s

	// 메소드 일반
	// ------------List-----------------------------
	public List<GuestbookVo> getList() {

		List<GuestbookVo> list = new ArrayList<GuestbookVo>();

		try {

			this.getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select  no ";
			query += "         ,name ";
			query += "         ,password ";
			query += "         ,content ";
			query += "         ,to_char(reg_date, 'yyyy-mm-dd hh:mi:ss') reg_date ";
			query += " from guestbook ";
			query += " order by reg_date desc ";

			// 쿼리문으로 변경
			pstmt = conn.prepareStatement(query);

			// 바인딩 없음

			// 실행
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {

				int no = rs.getInt("no");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String content = rs.getString("content");
				String regDate = rs.getString("reg_date");

				GuestbookVo vo = new GuestbookVo(no, name, password, content, regDate);
				list.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		this.close();
		return list;
	}

	// ------------no,password select-----------------------------
	public GuestbookVo guestNoPassword() {

		this.getConnection();

		GuestbookVo guestbookVo = null;

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = ""; // 쿼리문 문자열만들기, ? 주의
			query += " select  no, ";
			query += "         password ";
			query += " from guestbook ";

			pstmt = conn.prepareStatement(query); // 쿼리로 만들기

			rs = pstmt.executeQuery(); // 쿼리문 실행

			// 4.결과처리

			rs.next();
			int no = rs.getInt("no");
			String password = rs.getString("password");

			guestbookVo = new GuestbookVo(no, password);

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();
		return guestbookVo;
	}

	// ------------insert-----------------------------

	public int guestbookInsert(GuestbookVo guestbookVo) {

		int count = 0;
		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " insert into guestbook ";
			query += " values(seq_guestbook_no.nextval, ?,?,?,sysdate) ";

			pstmt = conn.prepareStatement(query);

			// 바인딩
			pstmt.setString(1, guestbookVo.getName());
			pstmt.setString(2, guestbookVo.getPassword());
			pstmt.setString(3, guestbookVo.getContent());

			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();
		return count;
	}

	// ------------delete-----------------------------

	public int guestbookDelete(int index, String password) {

		int count = 0;
		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " delete from guestbook ";
			query += " where no = ? ";
			query += " and password = ? ";

			pstmt = conn.prepareStatement(query);

			// 바인딩 //index가 파라미터로 받은 no
			pstmt.setInt(1, index);
			pstmt.setString(2, password);

			// 4.결과처리
			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();
		return count;
	}

}

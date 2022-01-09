package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestBookDao;
import com.javaex.vo.GuestbookVo;

@WebServlet("/gbc")
public class GuestbookController extends HttpServlet {
	//private static final long serialVersionUID = 1L;
       
	//기본 생성자 삭제

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		GuestBookDao guestbookDao = new GuestBookDao();
		GuestbookVo guestbookVo;
		
		if("addList".equals(action)) {
			
			//테스트
			System.out.println("action=addList");
			
			List<GuestbookVo> guestbookList = guestbookDao.getList();
			
			//1) 데이터 넣어주기
			request.setAttribute("gbList", guestbookList);
			//2-1)컨트롤러 -> 일시키기
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/addList.jsp");
			//2-2)포워드
			rd.forward(request, response);
			
		} else if("add".equals(action)) {
			
			//테스트
			System.out.println("action=add");
			
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			
			guestbookVo = new GuestbookVo(name,password,content);
			guestbookDao.guestbookInsert(guestbookVo);
			
			//리다이렉트
			response.sendRedirect("/guestbook2/gbc?action=addList");
			
		} else if("deleteForm".equals(action)) {
			
			//테스트
			System.out.println("action=deleteForm");
			
			//포워드
			//1-1) 
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/deleteForm.jsp");
			//1-2)
			rd.forward(request, response);
			
		} else if("delete".equals(action)) {
			
			//테스트
			System.out.println("action=delete");
			
			String password = request.getParameter("password");
			int no = Integer.parseInt(request.getParameter("no"));
			
			guestbookDao.guestbookDelete(no, password);
			
			//리다이렉트
			response.sendRedirect("/guestbook2/gbc?action=addList");
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

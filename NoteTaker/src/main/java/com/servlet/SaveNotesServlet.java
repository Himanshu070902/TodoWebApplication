package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entities.Note;
import com.helper.FactoryProvider;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/save1")
public class SaveNotesServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			// title, content fetch
			String title = req.getParameter("title");
			String content = req.getParameter("content");

			Note note = new Note(title, content, new Date());
//			System.out.println(note.getId() + " " + note.getTitle());

			Session s = FactoryProvider.getFactory().openSession();
			Transaction t = s.beginTransaction();

			s.save(note);
			t.commit();
			s.close();
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			out.println("<h1 style='text-align: center;'>Note is added successfully </h1>");
			out.println("<h1 style='text-align: center;'> <a href='all_notes.jsp'>View all Notes </a></h1>");
		} catch (Exception e) {
			System.out.println("errror is databases..." + e.getMessage());
		}
	}
}

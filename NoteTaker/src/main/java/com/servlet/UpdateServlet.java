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

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			String title = req.getParameter("title");
			String content = req.getParameter("content");
			int noteId = Integer.parseInt(req.getParameter("noteId").trim());
			Session session = FactoryProvider.getFactory().openSession();
			Transaction tr = session.beginTransaction();
			Note note = session.get(Note.class, noteId);
			note.setTitle(title);
			note.setContent(content);

			note.setAddDate(new Date());

			session.update(note);

			tr.commit();
			session.close();

			resp.sendRedirect("all_notes.jsp");
			PrintWriter out = resp.getWriter();
			out.println("<h1>Data Update SuccessFully..</h1>");

		} catch (Exception e) {

		}
	}
}

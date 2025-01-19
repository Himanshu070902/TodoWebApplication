package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entities.Note;
import com.helper.FactoryProvider;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int noteId = Integer.parseInt(req.getParameter("note_id").trim());
		Session s = FactoryProvider.getFactory().openSession();
		Transaction tr = s.beginTransaction();
		Note note = s.get(Note.class, noteId);
		s.delete(note);
		tr.commit();
		s.close();
		PrintWriter out = resp.getWriter();

		out.println("<h1>Data deleted successfully....</h1>");
	}

}

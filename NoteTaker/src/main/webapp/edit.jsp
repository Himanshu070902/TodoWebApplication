<%@page import="com.entities.Note"%>
<%@page import="com.helper.FactoryProvider"%>
<%@page import="org.hibernate.Session"%>
<%@page import="jakarta.servlet.http.HttpServletRequest"%>
<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Page</title>
<%@include file="all_js_css.jsp"%>
</head>
<body>
	<div class="container">
		<%@include file="navbar.jsp"%>
		<%
		int noteId = Integer.parseInt(request.getParameter("note_id").trim());
		Session s = FactoryProvider.getFactory().openSession();
		Note note = (Note) s.get(Note.class, noteId);
		%>
		<form
			action="UpdateServlet"
			method="post"
		>
			<input
				value="<%=note.getId()%>"
				name="noteId"
				type="hidden"
			/>
			<div class="form-group">
				<label for="title">Note title</label> <input
					name="title"
					required="required"
					type="text"
					class="form-control"
					id="title"
					aria-describedby="emailHelp"
					placeholder="Enter here"
					value="<%=note.getTitle()%>"
				/>
			</div>
			<div class="form-group">
				<label for="content">Note Content</label>
				<textarea
					name="content"
					required="required"
					class="form-control"
					id="content"
					placeholder="Enter your content here"
					style="height: 200px"
				><%=note.getContent()%>
					
					</textarea>
			</div>
			<div class="container text-center">
				<button
					type="submit"
					class="btn btn-success"
				>Save your note</button>
			</div>
		</form>
	</div>
</body>
</html>
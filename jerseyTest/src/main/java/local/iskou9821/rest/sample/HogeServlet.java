package local.iskou9821.rest.sample;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HogeServlet extends HttpServlet {
	private static final long serialVersionUID = 4709121912577404767L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("URL:" +req.getRequestURL());
		resp.setContentType("text/html; charset=UTF-8");
		try (PrintWriter pw = resp.getWriter()) {
			pw.append("<html><body>hogehoge<body></html>");
			pw.flush();
		}
	}

	
}

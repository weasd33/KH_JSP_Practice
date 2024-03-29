package khie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/join")
public class JoinServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public JoinServlet() {
		super();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청한 데이터에 한글이 있으면 깨지는 현상이 발생
		request.setCharacterEncoding("UTF-8");
		
		String userId = request.getParameter("id");
		String userPwd = request.getParameter("pwd");
		String userName = request.getParameter("name");
		String userPhone = request.getParameter("phone");
		String userAddr = request.getParameter("addr");
		String[] userHobby = request.getParameterValues("hobby");
		
		// 응답
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head></head>");
		out.println("<body>");
		out.println("<table border='1' cellspacing='0'>");
			out.println("<tr>");
				out.println("<th>");
				out.println("아이디");
				out.println("</th>");
				out.println("<td>");
				out.println(userId);
				out.println("</td>");			
			out.println("</tr>");
			
			out.println("<tr>");
				out.println("<th>");
				out.println("비밀번호");
				out.println("</th>");
				out.println("<td>");
				out.println(userPwd);
				out.println("</td>");
			out.println("</tr>");
		
			out.println("<tr>");
				out.println("<th>");
				out.println("이름");
				out.println("</th>");
				out.println("<td>");
				out.println(userName);
				out.println("</td>");
			out.println("</tr>");
	
			out.println("<tr>");
				out.println("<th>");
				out.println("전화번호");
				out.println("</th>");
				out.println("<td>");
				out.println(userPhone);
				out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr>");
				out.println("<th>");
				out.println("주소");
				out.println("</th>");
				out.println("<td>");
				out.println(userAddr);
				out.println("</td>");
			out.println("</tr>");
		
			out.println("<tr>");
				out.println("<th>");
				out.println("취미");
				out.println("</th>");
				out.println("<td>");
//				for(int i = 0; i < userHobby.length; i++) {
//					out.println(userHobby[i] + "&nbsp;&nbsp;&nbsp;");
//				}
				for(String h : userHobby) {
					out.println(h + "&nbsp;&nbsp;&nbsp;");
				}
				out.println("</td>");
			out.println("</tr>");	
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
	}
}

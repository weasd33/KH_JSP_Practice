package khie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/exam")
public class ExamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public ExamServlet() {
		super();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String name = request.getParameter("name");
		int kor = Integer.parseInt(request.getParameter("kor"));
		int eng = Integer.parseInt(request.getParameter("eng"));
		int math = Integer.parseInt(request.getParameter("math"));
		
		int total = kor + eng + math;
		double avg = total / 3;
		String grade = "";
		
		if(avg >= 90) {
			grade = "A학점";
		} else if(avg >= 80) {
			grade = "B학점";
		} else if(avg >= 70) {
			grade = "C학점";
		} else if(avg >= 60) {
			grade = "D학점";
		} else {
			grade = "F학점";
		}
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head></head>");
		out.println("<body>");
		out.println("<h1 align='center'>성적 결과 폼</h1>");
		out.println("<table border='1' cellspacing='0' align='center'>");
			out.println("<tr>");
				out.println("<th>");
				out.println("이름");
				out.println("</th>");
				out.println("<td>");
				out.println(name);
				out.println("</td>");			
			out.println("</tr>");
			
			out.println("<tr>");
				out.println("<th>");
				out.println("국어점수");
				out.println("</th>");
				out.println("<td>");
				out.println(kor + "점");
				out.println("</td>");
			out.println("</tr>");
		
			out.println("<tr>");
				out.println("<th>");
				out.println("영어점수");
				out.println("</th>");
				out.println("<td>");
				out.println(eng + "점");
				out.println("</td>");
			out.println("</tr>");
	
			out.println("<tr>");
				out.println("<th>");
				out.println("수학점수");
				out.println("</th>");
				out.println("<td>");
				out.println(math + "점");
				out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr>");
				out.println("<th>");
				out.println("총점");
				out.println("</th>");
				out.println("<td>");
				out.println(total + "점");
				out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr>");
				out.println("<th>");
				out.println("평균");
				out.println("</th>");
				out.println("<td>");
				out.println(avg + "점");
				out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr>");
				out.println("<th>");
				out.println("학점");
				out.println("</th>");
				out.println("<td>");
				out.println(grade);
				out.println("</td>");
			out.println("</tr>");
	
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
		
	}
}

package khie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Ex08_redirect")
public class Ex08_RedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public Ex08_RedirectServlet() {
		super();
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// service는 method가 Get과 Post 방식을 모두 받음	
		String userId = request.getParameter("id");
		String userPwd = request.getParameter("pwd");

		// 원래는 DB의 회원 관리 테이블에서 입력한 아이디와 비밀번호가 맞는지
		// 확인을 하여 회원이면 메인 페이지로 이동
		String dbId = "SHIM";
		String dbPwd = "1234";

		if (userId.equals(dbId)) { // 입력폼 창에서 입력한 아이디와 DB상의 아이디가 일치하는 경우
			if (userPwd.equals(dbPwd)) { // 입력폼 창에서 입력한 비밀번호와 DB상의 비밀번호가 일치하는 경우
				// 회원이므로 메인 페이지로 이동
				// 정보를 이동하는 페이지로 전달하는 방법
				request.setAttribute("Name", "심규복");
				request.setAttribute("Addr", "서울시 중구 신당동");
			
				response.sendRedirect("Ex08_redirect_01.jsp");
				
			} else { // 비밀번호가 틀린 경우
    			System.out.println("비밀번호가 틀립니다.");
    		}
		} else {
			System.out.println("아이디를 확인해주세요.");
		}
	}
}

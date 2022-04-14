package com.board1.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board1.model.BoardDAO;
import com.board1.model.BoardDTO;

public class BoardSearch implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 검색 폼 페이지에서 넘어온 데이터 처리
		String field = request.getParameter("search_field");
		String keyword = request.getParameter("search_keyword");
		
		int rowsize = 3; 		// 한 페이지당 보여질 게시물의 수
		int block = 3;	 		// 아래에 보여질 페이지의 최대 수 - 예) [1][2][3] / [4][5][6]
		int totalRecord = 0; 	// DB 상의 게시물의 전체 수
		int allPage = 0;		// 전체 페이지 수 
		
		int page = 0;			// 현재 페이지 변수
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		} else {	// 처음으로 "전체 게시물 목록" a 태그를 선택한 경우
			page = 1;
		}
		
		// 해당 페이지 시작 번호
		int startNo = (page * rowsize) - (rowsize - 1);
		
		// 해당 페이지 끝 번호
		int endNo = (page * rowsize);
		
		// 해당 페이지에서 시작 블럭
		int startBlock = (((page - 1) / block) * block) + 1;
		
		// 해당 페이지에서 끝 블럭
		int endBlock = (((page - 1) / block) * block) + block;
		
		BoardDAO dao = BoardDAO.getInstance();
		totalRecord = dao.searchListCount(field, keyword);	// DB상의 검색 게시물 수
		
		// 검색 게시물의 수를 한 페이지당 보여질 게시물의 수로 나눠줌 -> 전체 페이지 수
		// 나눴을 때의 소수점은 무조건 올림 처리
		allPage = (int) Math.ceil(totalRecord / (double)rowsize); 
		
		if(endBlock > allPage) {
			endBlock = allPage;
		}
		
		// 현재 페이지에 해당하는 게시물을 가져옴
		List<BoardDTO> searchList = dao.searchBoardList(field, keyword, page, rowsize);
		
		// 데이터 view 이동
		request.setAttribute("page", page);
		request.setAttribute("rowsize", rowsize);
		request.setAttribute("block", block);
		request.setAttribute("totalRecord", totalRecord);
		request.setAttribute("allPage", allPage);
		request.setAttribute("startNo", startNo);
		request.setAttribute("endNo", endNo);
		request.setAttribute("startBlock", startBlock);
		request.setAttribute("endBlock", endBlock);
		request.setAttribute("searchField", field);
		request.setAttribute("searchKeyword", keyword);
		request.setAttribute("pageList", searchList);
	}

}

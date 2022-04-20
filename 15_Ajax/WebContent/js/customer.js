/**
 * customer 테이블을 이용한 Ajax 실습
 */

$(function() {
	
	// 여러 Ajax에서 동일하게 사용되는 속성
	$.ajaxSetup({
		// 한글 깨짐
		ContentType : "application/x-www-form-urlencoded;charset=UTF-8",
		type : "post"		
	});
	// 모든 데이터를 가져오는 함수 - customer 테이블의 전체 데이터를 가져오는 함수
	function getData() {
		$.ajax({
			url : "/15_Ajax/select.do",
			datatype : "xml", 			// 결과 데이터 타입
			success : function(data) {
				// 테이블 태그의 첫번째 행(타이틀 태그)을 제외하고
				// 모든 행을 제거
				$("#listTable tr:gt(0)").remove();
				
				let table = "";
				
				$(data).find("customer").each(function() {
					table += "<tr>";
					table += "<td>" + $(this).find("no").text() + "</td>";
					table += "<td>" + $(this).find("id").text() + "</td>";
					table += "<td>" + $(this).find("name").text() + "</td>";
					table += "<td>" + $(this).find("age").text() + "</td>";
					table += "<td>" + $(this).find("phone").text() + "</td>";
					table += "<td>" + $(this).find("addr").text() + "</td>";
					table += "<td id='tdDel'> <input type='button' value='삭제'" +
							" id='del' num='" + $(this).find("no").text() + "'></td>";
					table += "</tr>";
				});
				
					// 테이블의 첫번째 행의 아래에 table을 추가
					$('#listTable tr:eq(0)').after(table);
			},
			
			error : function() {
				alert('Error...');
			}
		});
	} // getData() - End
	
	// 아이디 중복 여부 확인
	$("#id").keyup(function() {
		$.ajax({
			url : "/15_Ajax/idCheck.do",
			datatype : "text",
			data : {id : $("#id").val()},
			success : function(data) {
				$("span").html(data);
			},
			error : function() {
				alert('Error...');
			}
		});
	}); // keyup() - End
	
	// 가입하기 버튼을 클릭했을 때 DB에 추가
	$("#btn").click(function() {
		$.ajax({
			url : "/15_Ajax/insert.do",
			datatype : "text",
			data : $("#inForm").serialize(),
			success : function(data) {
				if(data > 0) {
					alert('Success!!');
					
					// 가입 완료 후 다시 전체 레코드 출력
					getData();
					
					// input 태그에 입력된 내용을 지우는 작업
					$("input[type=text]").each(function() {
						$(this).val("");
					});
				} else {
					alert('Fail...');
				}
			},
			error : function() {
				alert('Error...');
			}
		});
	}); // 가입 - End
	
	getData();
	
});
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>


<!-- 1단계 내용 function + 1,2단계 내용 출력페이지 -->
<script>
	$(function() {
		$.ajax({
			url : "/myjquery/productlist",
			method : 'get',
			//data : $("form > div").serialize(), //이 페이지에서는 로그인처럼 가져가야할 data가 없으므로 지워도된다. 
			success : function(result) {
				$("div").html(result); //div에 결과를 html로 작성하라
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert("list 에러남");
			}
		});
	});
</script>





<div></div>

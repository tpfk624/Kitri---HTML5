<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script>
	$(function(){
		$.ajax({
			url:"/myjquery/productlist",
			method: "get",
			success : function(result){
				$("section").html(result);
		
			},
			
			error : function(){
				alert("에러남");
			}
		});
		
		
	});
</script>

</head>
<body>
<section>내용</section> 

</body>
</html>
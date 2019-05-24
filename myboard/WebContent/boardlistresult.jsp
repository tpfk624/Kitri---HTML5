<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var = "pb" value="${requestScope.pb} "/>    
    
<style>
  div.boardlist{width: 70%; }
  div.boardlist>h3{ font-weight: bold; text-align: center;}
  div.boardlist>div.pageInfo{text-align:right; font-style: italic;}
  div.boardlist>div.table{display:table; margin: 10 auto; width: 90%;}
  div.boardlist>div.table>div.tr{display: table-row;}
  div.boardlist>div.table>div.tr>div.th{display:table-cell; font-weight: bold; text-align: center;}
  div.boardlist>div.table>div.tr>div.td{display:table-cell;}
  div.boardlist>div.table, div.boardlist div.th, div.boardlist div.td{
   border: 1px solid #93DAFF; border-collapse: collapse;
  }
  div.boardlist>div.pagegroup{
    width: 90%; 
  }
  div.boardlist>div.pagegroup>ul{
    margin: 0 auto;
  }
  div.boardlist>div.pagegroup>ul>li{
    
    list-style: none;
    display: inline-block;
  }
   
  div.boardlist>div.pagegroup a{
    margin:10px;
    text-decoration: none;    
  }
  
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
 
<script>
$(function(){
	$("div.boardlist>div.table>div.tr").click(function(){
		if($(this).index() != 0){
			var board_seq = $(this).find("div.board_seq").html().trim();
			alert(board_seq+"번 글의 상세정보를 보여줍니다.");
			/* $.ajax({
				url:'boarddetail',
				method:'get',
				data:'board_seq='+board_seq,
				success:function(result){
					//~~~~
				}
			}); */
		}
		return false;
	});
	$("div.boardlist>div.pagegroup a").click(function(){
		var currentPage=$(this).attr("href");
		alert(currentPage+"페이지를 보여줍니다.");
		/* $.ajax({
			url:'boardlist',
			method:'get',
			data:'currentPage='+currentPage,
			success:function(result){
				$("section").html(result.trim());
			}
		}); */
		return false;
	});
});
</script>   
<div class="boardlist">
  <h3>게시글 목록</h3>
  <div class="pageInfo">현재페이지:${pb.currentPage}page, 총페이지:${pb.totalPage} page</div>
  <div class="table">
    <div class="tr">
      <div class="th board_seq">글번호</div>
      <div class="th board_subject">글제목</div>
      <div class="th board_writer">작성자</div>
      <div class="th board_viewcont">조회수</div>
    </div>
    <c:forEach var="repBoard" items="${pb.list}">
    <div class="tr">
      <div class="td board_seq">1</div>
      <div class="td board_subject">1</div>
      <div class="td board_writer">2019-05-23</div>
      <div class="td board_viewcont">0</div>
    </div>
    </c:forEach>
    <div class="tr">
      <div class="td board_seq">8</div>
      <div class="td board_subject">&#10551;1-r2</div>
      <div class="td board_writer">2019-05-23</div>
      <div class="td board_viewcont">0</div>
    </div>   
  </div>
  <div class="pagegroup"> 
      <ul>
       <li><a href="3" >◀</a></li> 
       <li>4</li>
       <li><a href="5" >5</a></li> 
       <li><a href="6" >6</a></li>
       <li><a href="7">▶</a></li>     
      </ul>   
  </div>   
</div>
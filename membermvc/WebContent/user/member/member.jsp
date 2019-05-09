<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/template/header.jsp" %> <!-- include는 자기 프로젝트내에서만 파일을 가져올 수 있어서 파일경로는 안써준다 -->

<script type="text/javascript" src="<%=root%>/js/httpRequest.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	 
	$('#zipcode').focusin(function() {
		$('#zipModal').modal();
	});
	
});
var resultview;
var idcount = 1;

function idcheck(){
	resultview = document.getElementById("idresult");
	var searchId = document.getElementById("id").value;
	if(searchId.length < 5 || searchId.length > 16) {
		resultview.innerHTML = '<font color="gray">아이디는 5자이상 16자이하입니다.</font>';
	}else{
		//여기 부분만 통신해야해서 AJAX사용?
		var params = "act=idcheck&sid=" + searchId;
		sendRequest("<%=root%>/user", params, idcheckResult, "GET"); 
		resultview.innerHTML = '';
	}
}

function idcheckResult(){
	if(httpRequest.readyState == 4){ //모든 데이터가 넘어옴
		if (httpRequest.status == 200) { //정상적으로 넘어왔을때
			var result = httpRequest.responseXML;
			idcount = parseInt(result.getElementsByTagName("cnt")[0].firstChild.data);//무조건 하나뿐이니까 [0]이다 //0또는 1을 얻어옴
			if(idcount == 0){
				resultview.innerHTML = '<font color="steelblue">사용 가능합니다.</font>';
			}else{
				resultview.innerHTML = '<font color="magenta">사용중입니다. 다른 아이디를 검색해보세요.</font>';
			}
		}
	}else{
		//로딩중...
	}
}

function register(){
	if(document.getElementById("name").value == ""){
		alert("이름 입력!!!");
		return;
	}else if(idcount != 0){
		alert("아이디 중복검사를 하세요!!!");
		return;
	}else if(document.getElementById("pass").value == ""){
		alert("비밀번호 입력!!!");
		return;
	}else if(document.getElementById("pass").value != document.getElementById("passcheck").value){
		alert("비밀번호 확인!!!");
		return;
	}else {
		document.getElementById("memberform").action = "<%=root%>/user"; //여기로 갈거다
		document.getElementById("memberform").submit(); //여기서 감
		
	}
}
</script>
<body>

<div class="container" align="center">
	<div class="col-lg-6" align="center">
		<h2>회원가입</h2>
		<form id="memberform" method="post" action="">
			<input type = "hidden" name = "act" value="register">
			<div class="form-group" align="left">
				<label for="name">이름</label>
				<input type="text" class="form-control" id="name" name="name" placeholder="이름입력">
			</div>
			<div class="form-group" align="left">
				<label for="">아이디</label>
				<input type="text" class="form-control" id="id" name="id" onkeyup="javascript:idcheck();" placeholder="4자이상 16자 이하">
				<div id ="idresult"></div>
			</div>
			<div class="form-group" align="left">
				<label for="">비밀번호</label>
				<input type="password" class="form-control" id="pass" name="pass" placeholder="">
			</div>
			<div class="form-group" align="left">
				<label for="">비밀번호재입력</label>
				<input type="password" class="form-control" id="passcheck" name="passcheck" placeholder="">
			</div>
			<div class="form-group" align="left">
				<label for="email">이메일</label><br>
				<div id="email" class="custom-control-inline">
				<input type="text" class="form-control" id="emailid" name="emailid" placeholder="" size="25"> @
				<select class="form-control" id="emaildomain" name="emaildomain">
					<option value="naver.com">naver.com</option>
					<option value="google.com">google.com</option>
					<option value="daum.net">daum.net</option>
					<option value="nate.com">nate.com</option>
					<option value="hanmail.net">hanmail.net</option>
				</select>
				</div>
			</div>
			<div class="form-group" align="left">
				<label for="tel">전화번호</label>
				<div id="tel" class="custom-control-inline">
				<select class="form-control" id="tel1" name="tel1">
					<option value="010">010</option>
					<option value="02">02</option>
					<option value="031">031</option>
					<option value="032">032</option>
					<option value="041">041</option>
					<option value="051">051</option>
					<option value="061">061</option>
				</select> _
				<input type="text" class="form-control" id="tel2" name="tel2" placeholder="1234"> _
				<input type="text" class="form-control" id="tel3" name="tel3" placeholder="5678">
				</div>
			</div>
			<div class="form-group" align="left">
				<label for="">주소</label><br>
				<div id="addressdiv" class="custom-control-inline">
					<input type="text" class="form-control" id="zipcode" name="zipcode" placeholder="우편번호" size="7" maxlength="5" readonly="readonly">
					<!--<button type="button" class="btn btn-primary" onclick="javascript:">우편번호</button>-->
				</div>
				<input type="text" class="form-control" id="address" name="address" placeholder="" readonly="readonly">
				<input type="text" class="form-control" id="address_detail" name="address_detail" placeholder="" >
			</div>
			<div class="form-group" align="center">
				<button type="button" class="btn btn-primary" id="registerBtn" onclick="javascript:register();">회원가입</button>
				<button type="reset" class="btn btn-warning">초기화</button>
			</div>
		</form>
	</div>
</div>

<%@ include file="/user/member/zipsearch.jsp" %>   
  
<%@ include file="/template/footer.jsp" %>   
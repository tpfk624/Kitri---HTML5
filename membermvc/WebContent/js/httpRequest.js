function getXMLHttpRequest(){
	if(window.ActiveXObject){
		try{
			return new ActiveXObject("Msxml2.XMLHTTP");
		}catch(e1){
			try{
				return new ActiveXObject("Microsoft.XMLHTTP");
			}catch(e2){
				return null;
			}
		}
	}else if(window.XMLHttpRequest){
		return new XMLHttpRequest(); //우리는 이쪽 //서버와 통신 할 수 있는 자바스크립트 객체
	}else{
		return null;
	}
}

var httpRequest = null;

function sendRequest(url, params, callback, method){
	httpRequest = getXMLHttpRequest();
	
	var httpMethod = method ? method : 'GET';  //메소드에 값이 있으면 그 놈을 쓰고 없으면 겟방식을 써라
	if(httpMethod != 'GET' && httpMethod != 'POST'){ //겟도아니고 포스트도 아니면 겟방식(주로 오타)
		httpMethod = 'GET';
	}
	var httpParams = (params == null || params == '') ? null : params; //파람이 널이거나 비어있다면 널로 하고 아니면 그대로 해라
	var httpUrl = url;													//
	if(httpMethod == 'GET' && httpParams != null){ //넘겨줄값이 있는데 겟방식이면
		httpUrl = httpUrl + "?" + httpParams;		// url뒤에 붙여서 보내라
	}
	
	//alert("method == " + httpMethod + "\turl == " + httpUrl + "\tparam == " + httpParams);
	httpRequest.open(httpMethod, httpUrl, true); //true: 비동기방식  / false : 동기방식
	httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	httpRequest.onreadystatechange = callback; //상태가 바뀔때마다 callback? 할거다 (에버노트 에러부분 필기보기)
	//alert(httpMethod == 'POST' ? httpParams : null);
	httpRequest.send(httpMethod == 'POST' ? httpParams : null); //보내라 포스트방식이면 send안에다가 보내라
}
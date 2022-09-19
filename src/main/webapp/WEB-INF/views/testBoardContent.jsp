<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<input type="hidden" name="board_seq" value=${param.board_seq}></input>
	<h1>${param.board_seq}</h1>
	<div class = "uploadResult" id = "downFile">
	</div>

	<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
   		$(function(){
        	let board_seq = $("[name='board_seq']").val();
        	console.log(board_seq);
        	$.ajax({
            	url: "/boards/uploadList/"+board_seq,
            	method: "get",
            	dataType: "json",
            	success:function (data) {
                	
                	let uploadFilesList = data.uploadFilesList
                	console.log(uploadFilesList);
                	
					let output = "";
                	$.each(uploadFilesList, function () {
						output += "<div>";
                    	output += "<p>"+this.fileName+"</p>"
                    	output += "<img src='/boards/display?fileName=" + this.thumbnailURL+"'>";
                    	output += "<form action = 'boards/download/" + this.img_seq +"'>"
                    	output += "<input type = 'submit' value = 'download'></input></form>"
                    	output += "</div>"
                    	
                	});
                	document.getElementById('downFile').innerHTML = output;

            	},
            	error: function (jqXHR, textStatus, errorThrown) {
                	console.log(textStatus);
            	}
        	})
    	});
    	

</script>

	
</body>
</html>

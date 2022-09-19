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


<%--<form action="board" method="post" enctype="multipart/form-data">--%>
    <input type="text" name="board_writer" placeholder="작성자"><br>
    <input type="text" name="board_subject" placeholder="제목 입력"><br>
    <input type="text" name="board_content" placeholder="내용 입력"><br>
    <input type="file" name="uploadFiles" multiple="multiple">
    <button id="uploadBtn">Upload</button><br>
    <div class="uploadResult">

    </div>
    <input type="hidden" id="requestVO" value="">
    <button id="insertBtn">입력</button>
<%--</form>--%>


<%--        <script type="text/javascript" src="/webjars/jquery/3.6.0/jquery.min.js">--%>
<%--        </script>--%>
        <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script>
            let uploadFiles = null;
            $("#uploadBtn").click(function ( ) {

                let formData = new FormData(); //FormData 객체 생성

                let inputFile = $("input[type='file']");
                // input 태그의 type이 file인것을 찾아서 inputFile이라는 변수로 지정

                let files = inputFile[0].files;
                // files : 선택한 모든 파일을 나열하는 FileList 객체입니다.
                // multiple 특성을 지정하지 않았다면 두 개 이상의 파일을 포함하지 않습니다.

                for (let i = 0; i < files.length; i++) {
                    console.log(files[i]);
                    formData.append("uploadFiles", files[i]);
                }

                // 실제 업로드 부분
                // upload ajax
                $.ajax({
                    url: '/boards/uploadAjax', // 경로
                    processData: false, // 기본값은 true
                    // ajax 통신을 통해 데이터를 전송할 때, 기본적으로 key와 value값을 Query String으로 변환해서 보냅니다.
                    contentType: false, // multipart/form-data타입을 사용하기위해 false로 지정합니다.
                    data: formData,
                    type: 'POST',
                    dataType: 'json',
                    success: function (result) {
                        // 나중에 화면 처리
                        console.log(result);
                        showUploadedImage(result);
                        uploadFiles = result;
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.log(textStatus);
                    }
                }); //$.ajax
            });

            $("#insertBtn").click(function ( ) {

                let board_writer = $("[name='board_writer']").val();
                let board_subject = $("[name='board_subject']").val();
                let board_content = $("[name='board_content']").val();
                console.log(board_writer);
                console.log(board_subject);
                console.log(board_content);

                let jsonBoard = {
                    'board_writer' : board_writer,
                    'board_subject' : board_subject,
                    'board_content' : board_content
                }
                let uploadFilesList = JSON.stringify(uploadFiles);
                console.log(jsonBoard);
                console.log(uploadFilesList);

                let requestVO = jsonBoard + uploadFilesList;
                console.log(requestVO);


                $.ajax({
                    method: 'post',
                    url: '/boards', // 경로
                    data: {
                        'jsonBoard' : JSON.stringify(jsonBoard),
                        'uploadFilesList' : uploadFilesList},

                    success: function (data) {
                        console.log(data);
                    },
                    error: function () {

                    }
                }); //$.ajax
            });

            // Ajax 업로드 이후 이미지들을 호출하는 함수
            function showUploadedImage(arr){

                console.log(arr);

                let divArea = $(".uploadResult");

                let str = "";

                for (let i = 0; i<arr.length; i++) {
                    // divArea.append("<img src='/display?fileName=" + arr[i].thumbnailURL+"'>");
                    str += "<div>";
                    str += "<p>"+arr[i].fileName+"</p>"
                    str += "<img src='/boards/display?fileName=" + arr[i].thumbnailURL+"'>";
                    str += "<button class='removeBtn' data-name='"+arr[i].imageURL+"'>Remove</button>";
                    str += "</div>"
                }
                divArea.append(str);
            }

            $(".uploadResult").on("click", ".removeBtn", function (e){

                let target = $(this);
                let fileName = target.data("name");
                let targetDiv = $(this).closest("div");

                console.log(target);
                console.log(fileName);
                console.log(targetDiv);

                $.post('/boards/removeFile',{fileName : fileName}, function (result){

                    console.log(result);
                    if (result === true){
                        targetDiv.remove();
                    }
                })
            })

        </script>

</body>
</html>

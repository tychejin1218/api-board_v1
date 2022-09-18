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

<div class="container">

    <div class="panel panel-default">
        <div class="panel-heading">BOARD</div>
        <div class="panel-body">
            <table id="table">

            </table>
        </div>

    </div>
</div>


<script type="text/javascript" src="/webjars/jquery/3.6.0/jquery.min.js">
</script>
<script>
    $(function(){
        let boardList = null;
        $.ajax({
            url: "/board",
            method: "get",
            dataType: "json",
            success:function (data) {
                boardList = data.boards;

                let output = '<tr> <td>번호</td> <td>제목</td> <td>작성자</td> <td>작성일</td> </tr>';
                $.each(boardList, function () {

                    output += '<tr>'
                    output += '<td>' + this.board_seq + '</td>'
                    output += "<td><a href='/testBoardContent?board_seq="+this.board_seq+"'>" + this.board_subject + "</td>"
                    output += '<td>' + this.board_writer + '</td>'
                    output += '<td>' + this.ins_date + '</td>'
                    output += '</tr>'
                });
                document.getElementById('table').innerHTML = output;

            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(textStatus);
            }
        })
    });

</script>
</body>
</html>

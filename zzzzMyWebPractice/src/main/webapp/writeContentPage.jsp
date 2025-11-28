<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CKEditor Test</title>

<script src="${pageContext.request.contextPath}/ckeditor.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

</head>
<body>
<form action="writeContent.do" id="writeForm" method="POST">
    <textarea id="editor" name="content"></textarea>
    <button type="submit">작성</button>
</form>

<script>
let editorInstance;

ClassicEditor
.create( document.querySelector('#editor'), {
  simpleUpload: {
    uploadUrl: '${pageContext.request.contextPath}/ImageUploadServlet'
  },
  image: {
      toolbar: [
        'imageStyle:alignLeft',
        'imageStyle:alignCenter',
        'imageStyle:alignRight',
        'imageStyle:side',
        '|',
        'imageTextAlternative'
      ],
      // 사용할 스타일 (클래스)
      styles: [
        'alignLeft',
        'alignCenter',
        'alignRight',
        'side'
      ]
    }
})
.then(
// 생성 성공시 수행할 콜백함수 설정		
editor => {	
  // 생성후 받는 에디터 객체를 전역 변수로 지정하여 이후에도 사용 가능하게 만들기
  editorInstance = editor;
	
  // 에디터 높이 설정
  editor.editing.view.change(writer => {
    writer.setStyle('height', '700px', editor.editing.view.document.getRoot());
  });

})
.catch(
// 실패시 수행할 콜백함수 설정
error => console.error(error)
);

// form속 submit버튼을누르면 editor객체의 값을 textarea의 value로 설정한 뒤 POST되게 만들기
$("#writeForm").on("submit", function(e){
		e.preventDefault();
		$("#editor").val(editorInstance.getData());
		this.submit();
});

editorInstance.setData('hello world!');

</script>
</body>
</html>
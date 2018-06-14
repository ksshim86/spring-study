<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/include/taglib.jspf" %>
<script src="/js/util/html2canvas.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		$( "#btn_capture" ).click(function(){
			capture();
		});
	});
	
	function capture() {
		html2canvas($(".test1"), {
			onrendered: function(canvas) {
				//document.body.appendChild(canvas);
				//alert(canvas.toDataURL("image/png"));
					  
				$("#imgSrc").val(canvas.toDataURL("image/png"));
				
				console.log( $("#imgSrc").val() );
				
				$.ajax({
					type:     "post",
					data : $("form").serialize(),
					url:     "/util/imageCreate.do",
					error: function(a, b, c){        
						alert("fail!!");
					},
					success: function (data) {
						try{
							console.log( data );
							var html = "";
							html +=
								'<img src="/upload/' + data.imgName + '">';
								
							$( "#image1" ).html( html );
							
						}catch(e){                
							alert('server Error!!');
						}
					}
				});
			}
		});
	}
		
	  
</script>

	
<div class="test1" >
	<img src="/upload/background.jpg" style="width: 100%; max-width: 760px; vertical-align: middle">
</div>
<form>
	<input type="hidden" name="imgSrc" id="imgSrc" />
</form>
<button id="btn_capture">캡쳐하기</button>
<div id="image1">
</div>


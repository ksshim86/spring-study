<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/include/taglib.jspf" %>

<script type="text/javascript">
	$(document).ready(function() {
		$( "#btn_runWebCrawling" ).click(function(){
			runWebCrawling();
		});
	});

	function runWebCrawling() {
		if( $( "#crawlingUrl" ).val() == "" ) 
		{
			$( "#crawlingUrl" ).attr( "placeholder", "URL을 입력하세요." );
			return;
		}
		else
		{
			$.ajax({
				type	: "post",
				url		: "<c:url value='/util/runWebCrawling.do'/>",
				data	: 
				{
					"crawlingUrl" : $( "#crawlingUrl" ).val()
				},
				dataType: "json",
				async : false,
				success : function( response )
				{
					console.log( response );
					$( "#resultText" ).html( response.message );

					var html = "";

					for( var i=0; i<response.list.length; i++ )
					{
						console.log( response.list[i] );
						html += "<p>" + response.list[i] + "</p>";
						
					}

					$( "#resultList" ).html( html );
				}
			});
		}

		
	}


</script>

<div class="st-content">
	<div class="st-content-title">
		<h2>WebCrawling</h2>
	</div>
	<div class="st-content-ct">
		<div>
			<p class="inputs"><input type="text" id="crawlingUrl" placeholder="URL 입력" value="http://www.kma.go.kr/weather/typoon/report.jsp"></p>
			<button id="btn_runWebCrawling">Run WebCrawling</button>
			<span id="resultText"></span>
		</div>
		<div id="resultList">
			
		</div>
	</div>
</div>


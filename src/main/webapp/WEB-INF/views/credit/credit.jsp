<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>Credit Query</title>
</head>

<body>
	<form id="inputForm" action="${ctx}/credit/query" method="post"
		class="form-horizontal">
		<fieldset>
			<legend>
				<small>录入淘宝账号</small>
			</legend>
			<div class="control-group">
				<label for="description" class="control-label">账号列表:</label>
				<div class="controls">
					<textarea id="accounts" name="accounts" class="input-large"></textarea>
				</div>
			</div>
			<div class="form-actions">
				<input id="submit_btn" class="btn btn-primary" type="button"
					value="提交" />&nbsp; <input id="cancel_btn" class="btn"
					type="button" value="返回" onclick="history.back()" />
			</div>
		</fieldset>
	</form>
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>账号</th>
				<th>注册时间</th>
				<th>买家信用(点)</th>
				<th>最近一周(点)</th>
				<th>最近一月(点)</th>
				<th>账号等级</th>
			</tr>
		</thead>
		<tbody id="tableBody">
			<tr>
				<td></td>
			</tr>
		</tbody>
	</table>
	<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			//为inputForm注册validate函数
			$("#submit_btn").click(function() {
				var hash = {};
				var obj = $("#accounts").val();
				var nickArray = obj.split(",");
				for (var i = 0; i < nickArray.length; i++) {
					var nick = nickArray[i];
					var token = $.Token(nick);
					var u={};
					u['nick']=nick
					u['token']=token;
					hash[i]=u;
					//hash[nick] = token;
				}
				var data={};
				data["data"]=JSON.stringify(hash);
				$("#tableBody").html("");
				$.ajax({
					type : "post",
					url : "credit/query",
					data : data,
					success : function(returnData) {
						var html="";
						for(var i=0;i<returnData.length;i++){
							var obj=returnData[i];
							html+="<tr>";
							html+="<td>"+obj.userName+"</td>";
							html+="<td>"+obj.registerTime+"</td>";
							html+="<td>"+obj.buyerPoints+"</td>";
							html+="<td>"+obj.b_pointOfLastWeek+"</td>";
							html+="<td>"+obj.b_pointOfLastMonth+"</td>";
							html+="<td><img src='"+obj.securityLevel+"'</img></td>";
						}
						$("#tableBody").html(html);
					}
				});
			});

		});
	</script>
</body>
</html>

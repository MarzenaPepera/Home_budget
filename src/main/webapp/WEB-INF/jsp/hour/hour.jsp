<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><s:message code="menu.hour"/></title>

	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
	<link rel="stylesheet" href="/resources/css/main.css">
	<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700&amp;subset=latin-ext" rel="stylesheet">

	<!--[if lt IE 9]>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
	<![endif]-->
</head>
<body>
<header>
	<%@include file="/WEB-INF/incl/menuNew.app" %>
</header>
<main>

	<section class="adozl">

		<div class="container">

			<header>
				<h1><s:message code="menu.hour" /></h1>
				<p><c:out value="${message }" /></p>
			</header>

			<div class="row">

				<div class="col-sm-10 offset-sm-1">

					<table class="table table-striped table-dark">
						<thead>
						<tr>

							<td ><s:message code="hour.hour_from"/></td>
							<td ><s:message code="hour.hour_to"/></td>
						</tr>
						</thead>
						<tbody>
						<c:forEach var="user" items="${hourList }">
							<sf:form id="${user.id_hours}" action="hour/edit" modelAttribute="hour" enctype="multipart/form-data" method="POST">
								<sf:hidden value="${user.id_hours }" path="id_hours"/>
								<tr>

									<td ><c:out value="${user.hour_from }" /></td>
									<td ><c:out value="${user.hour_to }" /></td>
									<td ><input type="submit" value="<s:message code="button.edit"/>" /></td>
								</tr>
							</sf:form>
						</c:forEach>
						</tbody>
					</table>
				</div>

			</div>

		</div>
	</section>

</main>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>

<script src="/resources/js/bootstrap.min.js"></script>

<script>
	document.getElementById("allhour").classList.add("active");
</script>
</body>
</html>
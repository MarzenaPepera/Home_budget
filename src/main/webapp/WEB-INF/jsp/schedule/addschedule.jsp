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
	<title><s:message code="menu.hourAdd"/></title>

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
				<h1><s:message code="menu.hourAdd" /></h1>
				<p><c:out value="${message }" /></p>
			</header>

			<div class="row">


				<div class="col-sm-6 offset-sm-3">

					<sf:form id="sxheduleForm" action="addschedule" modelAttribute="schedule" enctype="multipart/form-data" method="POST">

						<div class="form-group">
							<input name="onlyDate_from_string" type="date" class="form-control" id="onlyDate_from_string" value="<c:out value="${schedule.onlyDate_from_string}" />" >
							<label for="hour_from_string"><s:message code="schedule.hour_from"/></label>
							<input value="<c:out value="${schedule.hour_from_string}" />" name="hour_from_string" type="time" class="form-control" id="hour_from_string" placeholder="<s:message code="schedule.hour_from.sample"/>">
							<small id="hour_from_stringHelp" class="form-text text-danger"><sf:errors path="hour_from_string"/></small>
						</div>

						<div class="form-group">
							<label for="hour_to_string"><s:message code="schedule.hour_to"/></label>
							<input value="<c:out value="${schedule.hour_to_string}" />" name="hour_to_string" type="time" class="form-control" id="hour_to_string" placeholder="<s:message code="schedule.hour_to.sample"/>">
							<small class="form-text text-danger"><sf:errors path="hour_to_string"/></small>
						</div>

<%--						<div class="form-group">
							<label for="id_user"><s:message code="schedule.id_user"/></label>
							<input value="<c:out value="${schedule.id_user}" />" name="hour_to_string" type="text" class="form-control" id="id_user" placeholder="<s:message code="schedule.id_user"/>">
							<small class="form-text text-danger"><sf:errors path="id_user"/></small>
						</div>--%>

						<div class="form-group">
							<label for="id_user"><s:message code="schedule.user" /></label>
							<sf:select class="form-control" path="id_user" items="${userMap}"/>
						</div>

						<div class="form-group">
							<label for="id_role"><s:message code="profil.rola" /></label>
							<sf:select class="form-control" path="id_role" items="${roleMap}"/>
						</div>

						<div class="form-group">
							<label for="id_places"><s:message code="places.address" /></label>
							<sf:select class="form-control" path="id_places" items="${placeMap}"/>
						</div>

						<button type="submit" class="btn btn-primary" name="action" value="save"><s:message code="button.save" /></button>
						<button type="submit" class="btn btn-primary" name="action" value="search"><s:message code="button.search" /></button>
						<button type="reset" class="btn btn-primary" onclick="window.location.href='/'"><s:message code="button.cancel" /></button>

					</sf:form>
				</div>
<%--dodaj margines pomiędzy tymi div--%>
				<div class="col-sm-10 offset-sm-1">

					<table class="table table-striped table-dark">
						<thead>
						<tr>
							<td ><s:message code="hour.id_user"/></td>
							<td ><s:message code="register.name"/></td>
							<td ><s:message code="register.lastName"/></td>
							<td ><s:message code="profil.rola"/></td>
							<td ><s:message code="hour.hour_from"/></td>
							<td ><s:message code="hour.hour_to"/></td>
						</tr>
						</thead>
						<tbody>
						<c:forEach var="user" items="${hourList }">
							<tr>
								<td ><c:out value="${user.user.id }" /></td>
								<td ><c:out value="${user.user.name }" /></td>
								<td ><c:out value="${user.user.lastName }" /></td>
								<td align="center">
									<c:forEach var="role" items="${user.user.roles }">
										<c:choose>
											<c:when test="${role.id == 1 }">
												<span color="text-success"><s:message code="word.admin"/></span>
											</c:when>
											<c:when test="${role.id == 3 }">
												<span class="text-warning"><s:message code="word.controller"/></span>
											</c:when>
											<c:otherwise>
												<span class="text-info"><s:message code="word.user"/></span>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</td>
								<td ><c:out value="${user.hour_from }" /></td>
								<td ><c:out value="${user.hour_to }" /></td>
							</tr>
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

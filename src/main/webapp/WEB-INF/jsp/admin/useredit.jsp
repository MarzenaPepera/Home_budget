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
	<title><s:message code="profilEdit.pageName"/></title>

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

	<section class="budget">

		<div class="container">

			<header>
				<h1><s:message code="profilEdit.pageName" /></h1>
				<p><c:out value="${message }" /></p>
			</header>

			<div class="row">

				<div class="col-sm-6 offset-sm-3">
					<sf:form id="usersForm" action="${pageContext.request.contextPath}/admin/updateuser/${user.id}" modelAttribute="user" enctype="multipart/form-data" method="POST">
                        <sf:hidden path="id" value="${user.id}"/>

						<div class="form-group">
							<label for="name"><s:message code="register.name"/></label>
							<sf:input path="name" class="form-control" readonly="true" />
							<small id="nameHelp" class="form-text text-danger"><sf:errors path="name"/></small>
						</div>

						<div class="form-group">
                            <label for="lastName"><s:message code="register.lastName"/></label>
                            <sf:input class="form-control" path="lastName" readonly="true"/>
							<small class="form-text text-danger"><sf:errors path="lastName"/></small>
						</div>

						<div class="form-group">
							<label for="telephone"><s:message code="register.telephone"/></label>
							<sf:input class="form-control" path="telephone" readonly="true"/>
							<small class="form-text text-danger"><sf:errors path="telephone"/></small>
						</div>

						<div class="form-group">
							<label for="email"><s:message code="register.email" /></label>
							<sf:input class="form-control" path="email" readonly="true"/>
							<small class="form-text text-danger"><sf:errors path="email"/></small>
                        </div>

						<div class="form-group">
							<label for="is_fired"><s:message code="profil.czyZwolniony" /></label>
							<sf:select class="form-control" path="is_fired" items="${isFiredMap}"/>
							<small class="form-text text-danger"><sf:errors path="is_fired"/></small>
						</div>

                        <div class="form-group">
                            <label for="nrRoli"><s:message code="profil.rola" /></label>
                            <sf:select class="form-control" path="nrRoli" items="${roleMap}"/>
							<small class="form-text text-danger"><sf:errors path="nrRoli"/></small>
                        </div>

						<div class="form-group">
							<label for="active"><s:message code="profil.czyAktywny"/></label>
							<sf:select class="form-control" path="active" items="${activityMap}"/>
							<small class="form-text text-danger"><sf:errors path="active"/></small>
						</div>

						<button type="submit" class="btn btn-primary"><s:message code="button.save" /></button>
						<button type="reset" class="btn btn-primary" onclick="window.location.href='${pageContext.request.contextPath}/'"><s:message code="button.cancel" /></button>

					</sf:form>
				</div>

			</div>

		</div>
	</section>

</main>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>

<script src="/resources/js/bootstrap.min.js"></script>

<script>
	document.getElementById("users").classList.add("active");
</script>
</body>
</html>

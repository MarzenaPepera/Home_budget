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
	<title><s:message code="hourEdit.pageName"/></title>

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
				<h1><s:message code="hourEdit.pageName" /></h1>
				<p><c:out value="${message }" /></p>
			</header>

			<div class="row">


				<div class="col-sm-6 offset-sm-3">
					<sf:form id="transactionForm" action="edit/updatetransaction" modelAttribute="transaction" enctype="multipart/form-data" method="POST">
						<sf:hidden path="id_transaction"/>
						<div class="form-group">
							<label for="date_string"><s:message code="transaction.date"/></label>
							<sf:input path="date_string" type="datetime-local"  class="date_string" />
							<small id="date_stringHelp" class="form-text text-danger"><sf:errors path="date_string"/></small>
						</div>

						<div class="form-group">
							<label for="amount"><s:message code="transaction.amount"/></label>
							<sf:input path="amount" class="form-control" />
							<small id="amount" class="form-text text-danger"><sf:errors path="amount"/></small>
						</div>

						<div class="form-group">
							<label for="description"><s:message code="transaction.description"/></label>
							<sf:input path="description" class="form-control" />
							<small id="description" class="form-text text-danger"><sf:errors path="description"/></small>
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
	document.getElementById("allplan").classList.add("active");
</script>
</body>
</html>

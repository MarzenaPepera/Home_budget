<nav class="navbar navbar-dark bg-budget navbar-expand-lg">

			<a class="navbar-brand" href="/"><img src="/resources/images/logo.png" width="30" height="30" class="d-inline-block mr-1 align-bottom" alt=""> <s:message code="menu.budget"/></a>

			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#mainmenu" aria-controls="mainmenu" aria-expanded="false" aria-label="Przełącznik nawigacji">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="mainmenu">

				<ul class="navbar-nav mr-auto">

					<li class="nav-item">
						<a id="index" class="nav-link"  href="/"> <s:message code="menu.mainPage"/> </a>
					</li>

<sec:authorize access="hasRole('ROLE_USER')">
					<li class="nav-item dropdown">
						<a id="allhour" class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" role="button" aria-expanded="false" id="submenu" aria-haspopup="true"> <s:message code="menu.transaction"/> </a>
						<div class="dropdown-menu" aria-labelledby="submenu">
							<a id="transaction" class="dropdown-item" href="/transaction"> <s:message code="menu.transaction"/> </a>
							<div class="dropdown-divider"></div>
							<a id="hourAdd" class="dropdown-item" href="/transaction/addtransaction"> <s:message code="menu.hourAdd"/> </a>
						</div>
					</li>
					<li class="nav-item dropdown">
						<a id="allplan" class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" role="button" aria-expanded="false" id="submenu" aria-haspopup="true"> <s:message code="menu.plan"/> </a>
						<div class="dropdown-menu" aria-labelledby="submenu">
							<a id="plan" class="dropdown-item" href="/plan"> <s:message code="menu.plan"/> </a>
						<div class="dropdown-divider"></div>
							<a id="hourAdd" class="dropdown-item" href="/plan/addplan"> <s:message code="menu.planAdd"/> </a>
					</div>
					</li>



</sec:authorize>
<sec:authorize access="hasRole('ROLE_ADMIN')">

					<li class="nav-item dropdown">
						<a id="alladmin" class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" role="button" aria-expanded="false" id="submenu" aria-haspopup="true"> <s:message code="menu.adminPage"/> </a>
						<div class="dropdown-menu" aria-labelledby="submenu">
							<a id="users" class="dropdown-item" href="/admin/users/1"> <s:message code="menu.users"/> </a>
						</div>
					</li>


</sec:authorize>
<sec:authorize access="isAuthenticated()">
					<li class="nav-item">
						<a id="profil" class="nav-link" href="/profil"> <s:message code="menu.profil"/> </a>
					</li>
</sec:authorize>
				</ul>
<div class="navbar-nav mrl-auto">
<sec:authorize access="isAuthenticated()">
				<a class="nav-link" href="/logout"> Wyloguj </a>
</sec:authorize>
<sec:authorize access="hasRole('ANONYMOUS')">
				<a id="login" class="nav-link" href="/login"> <s:message code="menu.login"/> </a>
				<a id="register" class="nav-link" href="/register"> <s:message code="menu.register"/> </a>
</sec:authorize>
			</div>
</div>

		</nav>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page import="ipint15.glp.api.dto.AncienEtudiantDTO"%>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript"
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>		
<script type="text/javascript"
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<link
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<!-- libraries pour les alertes  -->
<script
	src="${pageContext.request.contextPath}/resources/js/alertify.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/edition.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/alertify.core.css" />
<!-- include a theme, can be included into the core instead of 2 separate files -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/alertify.default.css" />

<%
	String section = (String) session.getAttribute("section");
%>

<div class="navbar navbar-default navbar-static-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#navbar-ex-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Lille University Meetup Platform<br>
			</a>
			<p class="text-primary">Connecté en tant qu'administrateur </p>
		</div>
		<div class="collapse navbar-collapse" id="navbar-ex-collapse">
			<ul class="nav navbar-nav navbar-right">
				<li <%if (section.equals("accueilgroupes")) {%> class="active" <%}%>>
					<a href="${pageContext.request.contextPath}/admin">Accueil</a>
				</li>
				<li <%if (section.equals("groupes")) {%> class="active" <%}%>>
					<a href="${pageContext.request.contextPath}/admin/groupes">Gestion
						Groupes</a>
				</li>

				<li <%if (section.equals("moderateurs")) {%> class="active" <%}%>><a
					href="${pageContext.request.contextPath}/admin/moderateurs">Gestion
						Modérateurs
				</a></li>
				<li <%if (section.equals("animateurs")) {%> class="active" <%}%>>
					<a href="${pageContext.request.contextPath}/admin/animateurs">Gestion
						Animateurs</a>
				</li>
				<li><a
					href="${pageContext.request.contextPath}/deconnectionAdmin">Déconnexion<br></a>
				</li>
			</ul>
		</div>
	</div>
</div>
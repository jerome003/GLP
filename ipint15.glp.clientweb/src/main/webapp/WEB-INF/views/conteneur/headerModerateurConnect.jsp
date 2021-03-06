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
			<img class="pull-left"
				src="${pageContext.request.contextPath}/resources/img/Logo.png"
				alt="Logo" /> <a class="navbar-brand" href="#">&nbsp; Lille University
				Meetup Platform<br>
			</a>
			<p class="text-primary">Connecté en tant que modérateur </p>
			
		</div>


		<div class="collapse navbar-collapse" id="navbar-ex-collapse">
			<ul class="nav navbar-nav navbar-right">
					<li <%if (section.equals("accueilmoderateur")) {%> class="active" <%}%>>
					<a href="${pageContext.request.contextPath}/moderateur">Accueil</a>
				</li>
				<li><a href="${pageContext.request.contextPath}/deconnectionModerateur">Déconnexion<br></a>
				</li>
			</ul>
		</div>
	</div>
</div>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page import="ipint15.glp.api.dto.EtudiantDTO"%>
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
<link href="${pageContext.request.contextPath}/resources/css/moncss.css" rel="stylesheet" >


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
			<a class="navbar-brand" href="#">Annuaire des anciens de
				l'université de Lille<br>
			</a>
		</div>

		<div class="collapse navbar-collapse" id="navbar-ex-collapse">
			<ul class="nav navbar-nav navbar-right">
				<li <%if (section.equals("accueil")) {%> class="active" <% } %>>
				<a href="${pageContext.request.contextPath}/">Accueil</a></li>

				<li <%if (section.equals("inscription")) {%> class="active" <% } %>>
				<a href="${pageContext.request.contextPath}/inscription">Inscription<br></a>
				</li>
				<li <%if (section.equals("contact")) {%> class="active" <% } %>>
				<a href="${pageContext.request.contextPath}/contact">Contact<br></a>
				</li>
				<li <%if (section.equals("connexion")) {%> class="active" <% } %>>
				<a href="${pageContext.request.contextPath}/connexion">Connexion<br></a>
				</li>
			</ul>
		</div>
	</div>
</div>

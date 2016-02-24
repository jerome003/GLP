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
<link href="${pageContext.request.contextPath}/resources/css/moncss.css" rel="stylesheet" >
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
			<a class="navbar-brand" href="#">Annuaire des anciens de
				l'université de Lille<br>
			</a>
		</div>


		<div class="collapse navbar-collapse" id="navbar-ex-collapse">
			<ul class="nav navbar-nav navbar-right">
				<li>

						<div class="form-group">
							<FORM class="navbar-form navbar-right inline-form" action="research" method="POST">
							<div class="form-group">
								<input type="text" name="recherche" class="form-control"
									placeholder="Search for..."> <input type="submit"
									value="GO !" />
									</div>
							</FORM>
					</div>
				</li>
				<li <%if (section.equals("actualite")) {%> class="active" <%}%>>
					<a href="${pageContext.request.contextPath}/fil-actualite">Fil
						d'actualité</a>
				</li>

				<li <%if (section.equals("profil")) {%> class="active" <%}%>><a
					href="${pageContext.request.contextPath}/profil/${etudiant.id}">${etudiant.prenom}<br></a>
				</li>

				<li <%if (section.equals("contact")) {%> class="active" <%}%>>
					<a href="${pageContext.request.contextPath}/contact">Contact<br></a>
				</li>
				<li><a href="${pageContext.request.contextPath}/deconnection">Déconnexion<br></a>
				</li>
			</ul>
		</div>
	</div>
</div>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="ipint15.glp.api.dto.EtudiantDTO"%>
<!DOCTYPE html>
<html>

<head>
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
	href="http://pingendo.github.io/pingendo-bootstrap/themes/default/bootstrap.css"
	rel="stylesheet" type="text/css">
<%
	String choix = (String) session.getAttribute("choixPublication");
	if (choix == null) {
		choix = "lesPublications";
	}
	EtudiantDTO etudiant = (EtudiantDTO) session.getAttribute("etudiant");
%>
</head>

<body>

	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<%
		if (choix.equals("lesPublications")) {
	%>
					<h1>Fil d'actualité : Toutes les publications</h1>
					<br>

					<%
		} else {
	%>
					<h1>Fil d'actualité : Toutes mes publications</h1>
					<br>
					<%
		} 
	%>
				</div>
			</div>

			<div class="row">
				<div class="col-md-12">
					<h3>Créer une publication</h3>
					<br>
				</div>
			</div>

			<div class="row">
				<div class="col-md-10">
					<form:form class="form-horizontal" role="form" method="post"
						commandName="command" action="addPublication">
						<div class="form-group">
							<div class="col-sm-2">
								<form:label path="titre" class="control-label">Titre</form:label>
							</div>
							<div class="col-sm-8">
								<form:input path="titre" type="text" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2">
								<form:label path="message" class="control-label">Message</form:label>
							</div>
							<div class="col-sm-8">
								<form:input path="message" type="text" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" value="Add Publication"
									class="btn btn-default">
									Publier <br>
								</button>
							</div>
						</div>
					</form:form>
				</div>

				<div class="col-md-2">
					<p>
						<form:form role="form" method="get" commandName="choixPublication"
							action="myPublication">
							<button type="submit" class="btn btn-primary">Mes
								Publications</button>
						</form:form>
					</p>
					<p>
						<form:form role="form" method="get" commandName="choixPublication"
							action="allPublication">
							<button type="submit" class="btn btn-secondary">Les
								Publications</button>
						</form:form>

					</p>
				</div>
			</div>
		</div>

	</div>
	<%
		if (choix.equals("lesPublications")) {
	%>
	<c:forEach items="${myInjectedBean.getPublications()}"
		var="publication">

		<div class="section">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="btn-group btn-group-justified">
							<a href="${pageContext.request.contextPath}/profil/${publication.profil.etudiant.id}" class="btn btn-info">${publication.profil.etudiant.nom}
								${publication.profil.etudiant.prenom}</a> <a href="#"
								class="btn btn-default"><fmt:formatDate type="both"
									dateStyle="short" timeStyle="short" value="${publication.date}" />
								: ${publication.titre}</a>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<p>${publication.message}</p>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>
	<%
		} else {
	%>
	<c:forEach items="${myInjectedBean.getPublications(etudiant)}"
		var="publication">

		<div class="section">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="btn-group btn-group-justified">
							<a href="#" class="btn btn-info">${publication.profil.etudiant.nom}
								${publication.profil.etudiant.prenom}</a> <a href="#"
								class="btn btn-default"><fmt:formatDate type="both"
									dateStyle="short" timeStyle="short" value="${publication.date}" />
								: ${publication.titre}</a>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<p>${publication.message}</p>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>
	<%
		}
	%>
</body>

</html>
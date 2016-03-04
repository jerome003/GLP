<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="section">
	<div class="container">
		<div class="row">
			<div class="col-md-6">
				<h1>Interface Administrateur</h1>
			</div>
		</div>
		<div class="row">
		<div class="well well-lg">
				<a class="col-md-3" class="btn mini blue-stripe"
							href="${pageContext.request.contextPath}/admin/groupes"><span
								class="glyphicon glyphicon-pencil"></span> Gestion groupes</a> Permet de créer, editer et supprimer des groupes
		</div>
		</div>
				<div class="row">
		<div class="well well-lg">
				<a class="col-md-3" class="btn mini blue-stripe"
							href="${pageContext.request.contextPath}/admin/moderateurs"><span
								class="glyphicon glyphicon-pencil"></span> Gestion modérateurs</a> Permet de créer des modérateurs
		</div>
		</div>
		<div class="row">
		<div class="well well-lg">
				<a class="col-md-3" class="btn mini blue-stripe"
							href="${pageContext.request.contextPath}/admin/animateurs"><span
								class="glyphicon glyphicon-pencil"></span> Gestion animateurs</a> Permet d'ajouter et de supprimer des animateurs pour les groupes
		</div>
		</div>
	</div>
</div>
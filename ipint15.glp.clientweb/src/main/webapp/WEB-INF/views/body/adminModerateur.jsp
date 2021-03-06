<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%if (request.getParameter("creation") != null && request.getParameter("creation").equals("ok")) { %>
	<script>
	window.onload = function(e) {
		alertify.success('Le mod�rateur a bien �t� cr��');
	};
</script> 

<%} else if (request.getParameter("creation") != null && request.getParameter("creation").equals("ko")) { %>
	<script>
	window.onload = function(e) {
		alertify.error('Impossible : Cette adresse existe d�j�');
	};
</script> 

<%} %>

<%if (request.getParameter("delete") != null && request.getParameter("delete").equals("ok")) { %>
	<script>
	window.onload = function(e) {
		alertify.success('Le mod�rateur a bien �t� supprim�');
	};
</script> 

<%} else if (request.getParameter("delete") != null && request.getParameter("delete").equals("ko")) { %>
	<script>
	window.onload = function(e) {
		alertify.error('Impossible : le mod�rateur est assign� � un groupe');
	};
</script> 

<%} %>	


<div class="section">
	<div class="container">
		<div class="row">
			<div class="col-md-4">
				<h1>Cr�ation Moderateur</h1>
			</div>
		</div>
		<div class="row">
			<form:form class="form-horizontal" role="form" method="post"
				commandName="command" action="saveModerateur">
				<div class="form-group">
					<div class="col-sm-2">
						<form:label path="prenom" class="control-label">Prenom :</form:label>
					</div>
					<div class="col-sm-6">
						<form:input path="prenom" type="text" class="form-control" />
						<form:errors path="prenom" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2">
						<form:label path="nom" class="control-label">Nom :</form:label>
					</div>
					<div class="col-sm-6">
						<form:input path="nom" type="text" class="form-control" />
						<form:errors path="nom" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2">
						<form:label path="email" class="control-label">Email :</form:label>
					</div>
					<div class="col-sm-6">
						<form:input path="email" type="email" class="form-control" />
						<form:errors path="email" />
					</div>

					<div class="col-sm-3">
						<input type="submit" value="Enregistrer" />
					</div>
			
				</div>
				
			</form:form>
		</div>
		<div class="row">
			<div class="col-md-4">
				<h1>Liste des Moderateurs</h1>
			</div>
		</div>
		<div class="row">
			<table class="table table-striped table-hover table-users">
				<thead>
					<tr>
						<th>Prenom</th>
						<th>Nom</th>
						<th>Email</th>
						<th>Groupes</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach items="${listeModo}" var="results">
						<tr>
							<td>${results.prenom}</td>
							<td>${results.nom}</td>
							<td>${results.email}</td>
							<td>
							<c:forEach items="${results.groupes}" var="resultsGroupe">
								${resultsGroupe.name}
							</c:forEach>
							</td>
							<td>
							<a
							 href="${pageContext.request.contextPath}/admin/removeModerateur/${results.id}" 
							class="confirm-delete btn mini red-stripe" role="button"> 
							<span class="glyphicon glyphicon-trash"></span> Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>
	</div>
</div>
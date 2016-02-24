<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%if (request.getParameter("edition1") != null && request.getParameter("edition1").equals("ok")) { %>
	<script>
	window.onload = function(e) {
		alertify.success('La nouvelle description est enregistrée');
	};
</script> 

<%} else if (request.getParameter("edition1") != null && request.getParameter("edition1").equals("ko")) { %>
	<script>
	window.onload = function(e) {
		alertify.error('Pas de changement de description ');
	};
</script> 

<%} %>

<%if (request.getParameter("edition2") != null && request.getParameter("edition2").equals("ok")) { %>
	<script>
	window.onload = function(e) {
		alertify.success('Le nouveau modérateur est enregistré');
	};
</script> 

<%} else if (request.getParameter("edition2") != null && request.getParameter("edition2").equals("ko")) { %>
	<script>
	window.onload = function(e) {
		alertify.error('Impossible d\'ajouter le même modérateur ');
	};
</script> 

<%} %>

<%if (request.getParameter("delete") != null && request.getParameter("delete").equals("ok")) { %>
	<script>
	window.onload = function(e) {
		alertify.success('Le modérateur est supprimé du groupe');
	};
</script> 

<%} else if (request.getParameter("delete") != null && request.getParameter("delete").equals("ko")) { %>
	<script>
	window.onload = function(e) {
		alertify.error('Impossible : Il doit en avoir au moins un modérateur dans le groupe ');
	};
</script> 

<%} %>

<div class="section">
	<div class="container">
		<div class="row">
			<div class="col-md-4">
				<h1>Edition Groupe</h1>
			</div>
		</div>
		<div class="row">
			<form  name='form1'  class="form-horizontal" role="form" method="post"  action="editGroupe1/${groupe.id}">
				<div class="form-group">
					<div class="col-sm-2">
						<label class="control-label">Nom du groupe :</label>
					</div>
					<div class="col-sm-6">
						<input class="form-control" id="nameGroupe" value="${groupe.name}" name="nameGroupe"
							disabled="disabled" required="required"  type="text" />	
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2"> 
						<label class="control-label">Description du groupe :</label>
					</div>
					<div class="col-sm-6">
						<input class="form-control" id="descriptionGroupe" name="descriptionGroupe"
						value="${groupe.description}" required="required" type="text" />
					</div>
					<div class="form-group">
						<div class="col-sm-2"></div>
						<div class="col-sm-3">
							<input id ="enregistrer" type="submit" value="Enregistrer" />
						</div>
					</div>
				</div>
			</form>
			<form  name='form1'  class="form-horizontal" role="form" method="post"  action="editGroupe2/${groupe.id}">
				<div class="form-group">
					<div class="col-sm-2">
						<label class="control-label">Moderateur :</label>
					</div>
					<div class="col-sm-3">
						<div class="form-inline">
							<select id="modo" name="modo">
								<c:forEach items="${moderateurList}" var="item"
									varStatus="status">
									<option value="${item.id}">${item.email}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="col-sm-1">
						<a class="btn mini blue-stripe"
							href="${pageContext.request.contextPath}/admin/moderateurs">Besoin
							d'ajouter un modérateur ?</a>
					</div>
				
					<div class="form-group">
						<div class="col-sm-2"></div>
						<div class="col-sm-3">
							<input id ="ajouter" type="submit" value="Ajouter" />
						</div>
					</div>
				</div>
			</form>
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
					</tr>
				</thead>

				<tbody>
					<c:forEach items="${listeModo}" var="results">
						<tr>
							<td>${results.prenom}</td>
							<td>${results.nom}</td>
							<td>${results.email}</td>
							<td>
							<a
							 href="${pageContext.request.contextPath}/admin/editerGroupe/removeModerateurFromGroupe/Modo=${results.id}/Groupe=${groupe.id}" 
							class="confirm-delete btn mini red-stripe" role="button"> 
							<span class="glyphicon glyphicon-trash"></span> Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>
	</div>
</div>
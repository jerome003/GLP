<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<%if (request.getParameter("ajoutAnim") != null && request.getParameter("ajoutAnim").equals("ok")) { %>
	<script>
	window.onload = function(e) {
		alertify.success('Animateur assigné à ce groupe');
	};
</script> 

<%} else if (request.getParameter("ajoutAnim") != null && request.getParameter("ajoutAnim").equals("ko")) { %>
	<script>
	window.onload = function(e) {
		alertify.error('Impossible d\'ajouter le même animateur ');
	};
</script> 

<%} %>

<%if (request.getParameter("delete") != null && request.getParameter("delete").equals("ok")) { %>
	<script>
	window.onload = function(e) {
		alertify.success('L\'animateur est supprimé du groupe');
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
			<form  name='form1'  class="form-horizontal" role="form" method="post"  action="ajoutAnimToGroupe/${groupe.id}">
				<div class="form-group">
					<div class="col-sm-2">
						<label class="control-label">Animateur :</label>
					</div>
					<div class="col-sm-3">
						<div class="form-inline">
							<select id="anim" name="anim">
								<c:forEach items="${animateurList}" var="item"
									varStatus="status">
									<option value="${item.id}">${item.mail}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2"></div>
						<div class="col-sm-3">
							<input id ="ajouter" type="submit" value="Ajouter animateur" />
						</div>
					</div>
				</div>
			</form>
		</div>	
			
	
		<div class="row">
			<div class="col-md-4">
				<h1>Liste des Animateurs</h1>
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
					<c:forEach items="${listeAnimateur}" var="results">
						<tr>
							<td>${results.prenom}</td>
							<td>${results.nom}</td>
							<td>${results.mail}</td>
							<td>
							<a
							 href="${pageContext.request.contextPath}/admin/editerAnimateur/removeAnimateurFromGroupe/Anim=${results.id}/Groupe=${groupe.id}" 
							class="confirm-delete btn mini red-stripe" role="button"> 
							<span class="glyphicon glyphicon-trash"></span> Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>
	</div>
</div>
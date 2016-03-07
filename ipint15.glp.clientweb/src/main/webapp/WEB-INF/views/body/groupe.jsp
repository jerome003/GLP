<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	/** Fonction basculant la visibilit� d'un �l�ment dom
	 * @parameter anId string l'identificateur de la cible � montrer, cacher
	 */
	function toggle(anId) {
		node = document.getElementById(anId);
		if (node.style.display == "none") {
			// Contenu cach�, le montrer
			node.style.display = "block";
			node.style.height = "auto"; // Optionnel r�tablir la hauteur
		} else {
			// Contenu visible, le cacher
			node.style.display = "none";
			node.style.height = "0"; // Optionnel lib�rer l'espace
		}
	}
</script>
<div class="section">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<h1>${groupe.name}</h1>
				<c:if test="${peutRejoindreGroupe == true}"> 
				
				<form:form role="form" method="get"
					action="${pageContext.servletContext.contextPath}/rejoindreGroupe/${groupe.id}" class="row col-md-12">
				<button type="submit" class="col-md-2 btn btn-primary">Rejoindre</button>
				</form:form>
				
			     </c:if>
			     
			     
			     
			     
			     <c:if test="${peutRejoindreGroupe == false}"> 
				
				<form:form role="form" method="get"
					action="${pageContext.servletContext.contextPath}/quitterGroupe/${groupe.id}" class="row col-md-12">
				<button type="submit" class="col-md-2 btn btn-primary">Quitter le groupe</button>
				</form:form>
				
			     </c:if>
			     
			     
			     
			     
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
			   	<c:if test="${peutPublier == true}">	<span class="glyphicon glyphicon-pencil"></span> <a class="pHoover"
					onclick="toggle('formulaire')">Cr�er une publication </a></c:if> <br>
			</div>
		</div>
		<div id="formulaire" style="display: none; height: 0px;">
			<div class="col-md-10">
				<form:form class="form-horizontal" role="form" method="post"
					commandName="command" action="addPublicationGroupe">
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
							<form:textarea path="message" rows="5" cols="30"
								class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-8">
							<form:input path="groupeDTO.id" type="hidden" id="groupes"
								name="groupes" value="${groupe.id}" />
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
		</div>
		<div class="col-md-4">
			<div class="row">
				<h3>Membres</h3>
			</div>
			<div class="well">
			<h4>Animateurs</h4>
				<c:forEach items="${groupe.animateurs}" var="anim">
					<a class="row"
						href="${pageContext.request.contextPath}/profilEnseignant/${anim.id}">${anim.prenom}
						${anim.nom}</a>
				</c:forEach>
			</div>
			<div class="well">
				<h4>Anciens Etudiants</h4>
				<c:forEach items="${groupe.ancienEtudiants}" var="ancienEtudiant">
					<a class="row"
						href="${pageContext.request.contextPath}/profil/${ancienEtudiant.id}">${ancienEtudiant.prenom}
						${ancienEtudiant.nom}</a>
				</c:forEach>
				
				<h4>Personnel</h4>
				<c:forEach items="${groupe.enseignants}" var="enseign">
					<a class="row"
						href="${pageContext.request.contextPath}/profilEnseignant/${enseign.id}">${enseign.prenom}
						${enseign.nom}</a>
				</c:forEach>
				<h4>Etudiants</h4>
				<c:forEach items="${groupe.etudiants}" var="etudiant">
					<a class="row"
						href="${pageContext.request.contextPath}/profilEtudiant/${etudiant.id}">${etudiant.prenom}
						${etudiant.nom}</a>
				</c:forEach>
				
			</div>
		</div>
		<div class="col-md-8">
			<div class="row">
				<h3>Publications</h3>
			</div>
			<c:forEach items="${groupe.listPublications}" var="publication">
				<div class="section">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<p class="">
								<span class="bold-font">${publication.titre}</span> <span
									class="pull-right"> <span
									class="glyphicon glyphicon-share-alt linkGroup"></span><a
									href="${pageContext.request.contextPath}/profil/${publication.profil.etudiant.id}"
									class="linkUser ">${publication.profil.etudiant.prenom}
										${publication.profil.etudiant.nom}</a><span><fmt:formatDate
											type="both" dateStyle="short" timeStyle="short"
											value="${publication.date}" /></span>
								</span>
							</p>
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-md-12">
									<p>${publication.message}</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</div>

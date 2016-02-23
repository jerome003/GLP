<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="section">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<h1>${groupe.name}</h1>
			</div>
		</div>
		<div class="col-md-4">
			<div class="row">
				<h3>Membres</h3>
			</div>
			<c:forEach items="${groupe.etudiants}" var="ancienEtudiant">
				<a class="col-md-12"
					href="${pageContext.request.contextPath}/profil/${ancienEtudiant.id}">${ancienEtudiant.prenom}
					${ancienEtudiant.nom}</a>
			</c:forEach>
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

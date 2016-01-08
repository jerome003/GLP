<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="section">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<h1>Résultat de la recherche personne : ${recherche}</h1>
				<c:forEach items="${liste}" var="results">
					<a href="${pageContext.request.contextPath}/profil/${results.id}"
						type="submit" class="btn btn-info">${results.prenom}
						${results.nom}</a>
					</br>
					</br>
				</c:forEach>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<h1>Résultat de la recherche groupe : ${recherche}</h1>
				<ul>
				<c:forEach items="${listeGroupe}" var="results">
					<li>${results.name}</li>
				</c:forEach>
				</ul>
			</div>
		</div>
	</div>
</div>

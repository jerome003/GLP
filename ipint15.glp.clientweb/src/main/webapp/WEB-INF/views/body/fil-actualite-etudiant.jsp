<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="ipint15.glp.api.dto.EtudiantDTO"%>
<%
	String choix = (String) session.getAttribute("choixPublication");
	Integer idGroupe = (Integer) session.getAttribute("idGroupe");
	if (choix == null) {
		choix = "lesPublications";
	}
	if (idGroupe == null || idGroupe == 0) {
		idGroupe = -1;
	}
	
	EtudiantDTO etudiant = (EtudiantDTO) session.getAttribute("etudiant");

%>

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
		<div class="row"></div>
		<h3>Mes groupes</h3>
		<div class="well well-g">
		<c:forEach items="${listeGroupes}" var="results">
			<div class="row">
				<div class="col-md-12"> <a
						href="${pageContext.request.contextPath}/groupe/${results.id}">${results.name}</a> :
					${results.description} <c:if test="${results.institutionnel}"> (Institutionnel)</c:if></div>
			</div>

		</c:forEach>

	</div>
		<div class="row">
			<div class="col-md-12">
				<span class="glyphicon glyphicon-pencil"></span><a class="pHoover"
					onclick="toggle('formulaire')">Cr�er une publication</a> <br>
			</div>
		</div>
		<div id="formulaire" style="display: none; height: 0px;">
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
							<form:textarea path="message" rows="5" cols="30"
								class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2">
							<form:label path="message" class="control-label">Groupe</form:label>
						</div>
						<div class="col-sm-8">
							<form:select path="groupeDTO.id" id="groupes" name="groupes">
								<form:option value="-1" label="Tout le monde" />
								<c:forEach items="${listeGroupes}" var="groupe">
									<form:option value="${groupe.id}">${groupe.name}</form:option>
								</c:forEach>
							</form:select>
						</div>
					</div>
					<!-- 						<div class="form-group"> -->
					<!-- 							<div class="col-sm-2"> -->
					<%-- 								<form:label path="message" class="control-label">Public</form:label> --%>
					<!-- 							</div> -->
					<!-- 							<div class="col-sm-8"> -->
					<%-- 								<form:checkbox path="publicationPublic" class="form-control" /> --%>
					<!-- 							</div> -->
					<!-- 						</div> -->
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
		<div class="row paddingBottom">
			<div class="col-md-7 col-md-offset-3">
				<form:form role="form" method="get" commandName="choixPublication"
					action="getPublication" class="row col-md-12">
					<div class="col-md-8">
						<div class="form-group">
							<div class="col-md-10 col-md-offset-2">
								<%
									if (choix.equals("lesPublications")) {
								%>
								<label class="radio-inline"> <input type="radio"
									name="myPublications" value="true" /> Mes publications
								</label> <label class="radio-inline"> <input type="radio"
									checked="checked" name="myPublications" value="false"
									${choix.equals("lesPublications") ? 'checked="checked"' : ''} />
									Les publications
								</label>
								<%
									} else {
								%>
								<label class="radio-inline"> <input type="radio"
									name="myPublications" value="true" checked="checked" />Mes
									publications
								</label> <label class="radio-inline"> <input type="radio"
									name="myPublications" value="false" class="radio-inline"
									${choix.equals("lesPublications") ? 'checked="checked"' : ''} />
									Les publications
								</label>
								<%
									}
								%>
							</div>
						</div>
						<div class="form-group">
							<select id="groupes" name="idGroupe"
								class="col-md-6 col-md-offset-3">
								<option value="-2" ${idGroupe == '-2' ? 'selected' : ''}>------------------</option>
								<option value="-1" ${idGroupe == '-1' ? 'selected' : ''}>Tout
									le monde</option>
								<c:forEach items="${listeGroupes}" var="groupe">
									<option value="${groupe.id}"
										${idGroupe == groupe.id ? 'selected' : ''}>${groupe.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<button type="submit" class="col-md-2 btn btn-primary">Filtrer</button>
				</form:form>
				<!-- 				<p> -->
				<%-- 					<form:form role="form" method="get" commandName="choixPublication" --%>
				<%-- 						action="myPublication"> --%>
				<!-- 						<button type="submit" class="btn btn-primary">Mes -->
				<!-- 							Publications</button> -->
				<%-- 					</form:form> --%>
				<!-- 				</p> -->
				<!-- 				<p> -->
				<%-- 					<form:form role="form" method="get" commandName="choixPublication" --%>
				<%-- 						action="allPublication"> --%>
				<!-- 						<button type="submit" class="btn btn-secondary">Les -->
				<!-- 							Publications</button> -->
				<%-- 					</form:form> --%>
				<!-- 				</p> -->
			</div>
		</div>

	</div>
</div>
<%
	if (choix.equals("lesPublications")) {
%>
<c:forEach
	items="${myInjectedBean.getAllPublicationsEtudiant(etudiant, idGroupe)}"
	var="publication">
	<div class="section">
		<div class="container">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<p class="">
						<span class="bold-font">${publication.titre}</span> <span
							class="pull-right"> <span
							class="glyphicon glyphicon-share-alt linkGroup"></span><a
							href="${pageContext.request.contextPath}/profil/${publication.etudiant.id}"
							class="linkUser ">${publication.etudiant.prenom}
								${publication.etudiant.nom}</a> <c:if
								test="${publication.groupeDTO != null}">
								<span class="glyphicon glyphicon-share-alt linkGroup"></span>
								<a
									href="${pageContext.request.contextPath}/groupe/${publication.groupeDTO.id}"
									class="linkGroup ">${publication.groupeDTO.name}</a>
							</c:if> <span><fmt:formatDate type="both" dateStyle="short"
									timeStyle="short" value="${publication.date}" /></span>
						</span>
					</p>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-12">
							<!-- 							<div class="btn-group btn-group-justified"> -->


							<!-- 							</div> -->
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<p>${publication.message}</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</c:forEach>
<%
	} else {
%>
<c:forEach
	items="${myInjectedBean.getMyPublicationsEtudiant(etudiant, idGroupe)}"
	var="publication">
	<div class="section">
		<div class="container">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<p class="">
						<span class="bold-font">${publication.titre}</span> <span
							class="pull-right"> <a
							href="${pageContext.request.contextPath}/profil/${publication.etudiant.id}"
							class="glyphicon glyphicon-share-alt linkUser ">${publication.etudiant.prenom}
								${publication.etudiant.nom}</a> <c:if
								test="${publication.groupeDTO != null}">
								<a
									href="${pageContext.request.contextPath}/groupe/${publication.groupeDTO.id}"
									class="glyphicon glyphicon-share-alt linkGroup ">${publication.groupeDTO.name}</a>
							</c:if> <span><fmt:formatDate type="both" dateStyle="short"
									timeStyle="short" value="${publication.date}" /></span>
						</span>
					</p>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-12">
							<!-- 							<div class="btn-group btn-group-justified"> -->


							<!-- 							</div> -->
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<p>${publication.message}</p>
						</div>
					</div>
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
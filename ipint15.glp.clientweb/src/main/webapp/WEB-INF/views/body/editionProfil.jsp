<%@page import="ipint15.glp.api.dto.HobbieDTO"%>
<%@page import="ipint15.glp.api.dto.EcoleDTO"%>
<%@page import="ipint15.glp.api.dto.CompetenceDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ipint15.glp.api.dto.ExperienceDTO"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script type="text/javascript">
	function AddChamp(divId, champId, boutonId) {
		//alert (divId +" "+ champId+" "+boutonId+ " "+document.getElementsByName(champId).length);
		var taille = document.getElementsByName(champId).length;
		if (taille == 0) {
			document.getElementById(divId).innerHTML += "<textarea id=\""+ champId +"0\" name=\""+ champId +"\" rows=\"2\" cols=\"50\"></textarea> <a id=\""+ boutonId +"0\" name=\""+ boutonId +"\" class=\"btn btn-primary\" onClick=\"suppressionChampEtBouton("+champId+"0, "+boutonId+"0)\">-</a>";

		} else {

			//recuperer l'identifiant du dernier élément | attention : l'identifiant ne correspondant pas au nombre d'element dont le nom est expPro car en cas de suppression id exPro15 alors qu'il ya que 10 elem
			var element = document.getElementsByName(champId);
			var idelement = element[taille - 1].getAttribute('id');
			var sizeId = idelement.length; //recup la taille de l'identifiant
			var nvId = parseInt(idelement.substring(champId.length, sizeId)) + 1;
			//alert(idelement + "  " + sizeId + "  " + nvId);

			//Faire une vérification sur l'ajout de l'element !!!
			// voir création d'un nouveau node !
			document.getElementById(divId).innerHTML += "<textarea id=\""+champId+""+nvId+"\" name=\""+ champId +"\" rows=\"2\" cols=\"50\"></textarea> <a id=\""+boutonId+""
					+ nvId
					+ "\" name=\""+ boutonId +"\" class=\"btn btn-primary\" onClick=\"suppressionChampEtBouton("+champId+""
					+ nvId + ","+ boutonId+"" + nvId + ")\">-</a>";
		}
	}

	/**
	* Suppression d'un textarea et du bouton de suppression
	*
	*@param le textarea
	*@param bouton le bouton de suppression
	*/
	function suppressionChampEtBouton(champ, bouton) {
		var r = confirm("Voulez-vous réellement supprimer ce champ ?");
		//alert(champ, idBouton);
		if(r == true){
			champ.parentNode.removeChild(champ);
			bouton.parentNode.removeChild(bouton);
		}
	}
</script>



<%
	List<ExperienceDTO> listExperience = new ArrayList<ExperienceDTO>();
	ExperienceDTO e = new ExperienceDTO();
	e.setLibelle("Grand vainqueur de la belette de Winchester");
	ExperienceDTO e2 = new ExperienceDTO();
	e2.setLibelle("Auteur du livre \"Le druidisme expliqué aux personnes âgées\"");
	ExperienceDTO e3 = new ExperienceDTO();
	e3.setLibelle("Test");
	listExperience.add(e);
	listExperience.add(e2);
	listExperience.add(e3);
	request.setAttribute("expList", listExperience);

	List<CompetenceDTO> listCompetence = new ArrayList<CompetenceDTO>();
	CompetenceDTO c1 = new CompetenceDTO();
	c1.setLibelle("Faire des crepes");
	listCompetence.add(c1);
	request.setAttribute("compList", listCompetence);

	List<EcoleDTO> listFormation = new ArrayList<EcoleDTO>();
	request.setAttribute("formList", listFormation);

	List<HobbieDTO> listLoisirs = new ArrayList<HobbieDTO>();
	request.setAttribute("loisirList", listLoisirs);
%>

	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-4">
					<h1 class="col-md-2 text-success">Profil</h1>
					<p>
						<br> <br>
					</p>
				</div>
				<div class="col-md-4">
					<a class="btn btn-primary">Enregistrer les modifications<br></a>
				</div>
			</div>
		</div>
	</div>
	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<h2 id="UserName">${etudiant.prenom}${etudiant.nom}</h2>
					<div class="col-md-12">
						<ul class="list-group">
							<li>
								<p>Poste actuel :</p>
								<form>
									<textarea name="nom" rows="1" cols="20"></textarea>
								</form>
								<p></p>
							</li>
							<li>
								<p>Lieu:</p>
								<form>
									<textarea name="nom" rows="1" cols="20"></textarea>
								</form>
								<p></p>
							</li>
							<li>
								<p>Nom de l'entreprise :</p>
								<form>
									<textarea name="nom" rows="1" cols="20"></textarea>
								</form>
								<p></p>
							</li>
							<li>
								<p>Mail :</p>
								<form>
									<textarea name="nom" rows="1" cols="20"></textarea>
								</form>
								<p></p>
							</li>
						</ul>
					</div>
				</div>
				<div class="col-md-6">
					<h2 class="text-success">
						Expériences Professionnelles <br>
					</h2>

					<div class="col-md-12">
						<div id="lesExpPro">

							<a class="btn btn-primary"
								onClick="AddChamp('lesExpPro', 'expPro', 'deleteExpPro');">Ajouter
								une exprérience</a>
							<c:forEach items="${expList}" var="experience" varStatus="loop">
								<textarea id="expPro${loop.index}" name="expPro" rows="2"
									cols="50">${experience.libelle}</textarea>
								<a id="deleteExpPro${loop.index}" name="deleteExpPro"
									class="btn btn-primary"
									onClick='suppressionChampEtBouton(expPro${loop.index}, deleteExpPro${loop.index})'>-</a>
							</c:forEach>

							<div>
								<ul class="list-group"></ul>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="section">
				<div class="container">
					<div class="row">
						<div class="col-md-6">
							<h2 class="text-success">Compétences</h2>
							<div id="lesCompetences" class="col-md-12">
								<a class="btn btn-primary"
									onClick="AddChamp('lesCompetences', 'competence', 'deleteCompetence');">Ajouter
									une compétence</a>
								<ul class="list-group">
									<c:forEach items="${compList}" var="competence"
										varStatus="loop">
										<textarea id="competence${loop.index}" name="competence"
											rows="2" cols="50">${competence.libelle}</textarea>
										<a id="deleteCompetence${loop.index}" name="deleteCompetence"
											class="btn btn-primary"
											onClick="suppressionChampEtBouton(competence${loop.index}, deleteCompetence${loop.index})">-</a>
									</c:forEach>
								</ul>
							</div>
						</div>
						<div class="col-md-6">
							<h2 class="text-success">Formation</h2>
							<div id="lesFormations" class="col-md-12">
								<a class="btn btn-primary"
									onClick="AddChamp('lesFormations', 'formation', 'deleteFormation');">Ajouter
									une formation</a>
								<ul class="list-group">
									<c:forEach items="${formList}" var="formation" varStatus="loop">
										<textarea id="formation${loop.index}" name="formation"
											rows="2" cols="50">${formation.libelle}</textarea>
										<a id="deleteFormation${loop.index}" name="deleteFormation"
											class="btn btn-primary"
											onClick="suppressionChampEtBouton(formation${loop.index}, deleteFormation${loop.index})">-</a>
									</c:forEach>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="section">
				<div class="container">
					<div class="row">
						<div class="col-md-6">
							<h2 class="text-success">Loisirs</h2>
							<div id="lesLoisirs" class="col-md-12">
								<a class="btn btn-primary"
									onClick="AddChamp('lesLoisirs', 'loisir', 'deleteLoisir');">Ajouter
									un loisir</a>
								<ul class="list-group">
									<c:forEach items="${loisirList}" var="loisir" varStatus="loop">
										<textarea id="loisir${loop.index}" name="loisir" rows="2"
											cols="50">${formation.libelle}</textarea>
										<a id="deleteLoisir${loop.index}" name="deleteLoisir"
											class="btn btn-primary"
											onClick="suppressionChampEtBouton(loisir${loop.index}, deleteLoisir${loop.index})">-</a>
									</c:forEach>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
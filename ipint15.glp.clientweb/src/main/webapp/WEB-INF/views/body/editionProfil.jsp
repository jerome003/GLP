<%@page import="ipint15.glp.api.dto.HobbieDTO"%>
<%@page import="ipint15.glp.api.dto.EcoleDTO"%>
<%@page import="ipint15.glp.api.dto.CompetenceDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ipint15.glp.api.dto.ExperienceDTO"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<!-- ----------------------------------------------------------------------- -->
<!-- ----------------------------------------------------------------------- -->
<!-- -----------------------------Code JS----------------------------------- -->
<!-- ----------------------------------------------------------------------- -->
<script type="text/javascript">

function saveExpPro(){
	var name = 'lesExpPro';
	var url = 'saveExpPro';
	var tmp = "";
	var mail = document.getElementById('mail');
	var maClass= document.getElementById(name);
	var noeuds = maClass.getElementsByTagName('input'); 
	for(i = 0 ; i< noeuds.length;){
		tmp += noeuds[i++].value+"|";
		tmp += noeuds[i++].value+"|";
		tmp += noeuds[i++].value+"|";
 		tmp += noeuds[i++].value+"%";
 	}
//	var test = "toto";
	//alert(tmp+" "+test);
	var res = { mail : mail.value,maListe : tmp } ; 	
 	$.ajax({
         type: "POST",
         url: url,
         data: res,
         success: function (result) {
        	 /*window.location = "/ipint15.glp.clientweb/profil/3";*/
         },
         error: function (result) {
             // do something.
        }
     });
}


function save(name, url){
	var mail = document.getElementById('mail');	
	var tmp = "";	
 	var maClass= document.getElementById(name);
 	var noeuds = maClass.getElementsByTagName('input'); 	
 	for(i = 0 ; i< noeuds.length; i++){
 		tmp += noeuds[i].value+"%";
 	}
 	var res = { mail : mail.value,maListe : tmp } ; 	
 	$.ajax({
         type: "POST",
         url: url,
         data: res,
         success: function (result) {
             // do something.
         },
         error: function (result) {
             // do something.
        }
     });
}

function AddChamp(divId, champId, boutonId, fctSave) {
	var taille = document.getElementsByName(champId).length;
	if (taille == 0) {
			var input = document.createElement("input");
			input.type = "text";
			input.id=champId+"0";
			input.name=champId;
			document.getElementById(divId).appendChild(input);
			var adresse = document.createElement("a");
			var link = document.createTextNode("-");
			adresse.setAttribute("class", "btn btn-primary");
			adresse.setAttribute("id",boutonId+"0");
			adresse.setAttribute("name",boutonId);
			adresse.setAttribute("onClick","suppressionChampEtBouton("+champId+"0"+","+ boutonId+"0"+ ");");
			adresse.appendChild(link);
			
			
		document.getElementById(divId).appendChild(input);
		document.getElementById(divId).appendChild(adresse);
		document.getElementById(divId).appendChild(document.createElement("br"));
	} else {

		//recuperer l'identifiant du dernier élément | attention : l'identifiant ne correspondant pas au nombre d'element dont le nom est expPro car en cas de suppression id exPro15 alors qu'il ya que 10 elem
			var element = document.getElementsByName(champId);
			var idelement = element[taille - 1].getAttribute('id'); 	
			var sizeId = idelement.length; //recup la taille de l'identifiant
			var nvId = parseInt(idelement.substring(champId.length, sizeId)) + 1;
		
			var input = document.createElement("input");
			input.type = "text";
			input.id=champId+nvId;
			input.name=champId;
			document.getElementById(divId).appendChild(input);

			var adresse = document.createElement("a");
			var link = document.createTextNode("-");
			adresse.setAttribute("class", "btn btn-primary");
			adresse.setAttribute("id",boutonId+""+nvId);
			adresse.setAttribute("name", boutonId);
			adresse.setAttribute("onClick", "suppressionChampEtBouton("+champId+""+ nvId + ","+ boutonId+"" + nvId + ");");
			adresse.appendChild(link);
			
			document.getElementById(divId).appendChild(input);
			document.getElementById(divId).appendChild(adresse);
			document.getElementById(divId).appendChild(document.createElement("br"));
		
	}
}

	function AddExpPro(lesExpPro, expPro, deleteExpPro){
		var taille = document.getElementsByName("lesExpPro").length;
		if (taille == 0){
			//
			var label = document.createElement("label");
			label.id = "labelExpProPoste0";
			label.htmlFor = "expProPoste0";
			label.innerHTML = "Poste : ";
			document.getElementById("lesExpPro").appendChild(label);
			
			var input = document.createElement("input");
			input.type = "text";
			input.id="expProPoste0";
			input.name="expPro";
			document.getElementById("lesExpPro").appendChild(input);
			//
			label = document.createElement("label");
			label.id = "labelExpProEntreprise0";
			label.htmlFor = "expProEntreprise0";
			label.innerHTML = "Entreprise : ";
			document.getElementById("lesExpPro").appendChild(label);
			
			input = document.createElement("input");
			input.type = "text";
			input.id="expProEntreprise0";
			input.name="expPro";
			document.getElementById("lesExpPro").appendChild(input);
			//
			label = document.createElement("label");
			label.id = "labelExpProDebut0";
			label.htmlFor = "expProDebut0";
			label.innerHTML = "Début : ";
			document.getElementById("lesExpPro").appendChild(label);
			
			input = document.createElement("input");
			input.type = "text";
			input.id="labelExpProDebut0";
			input.name="expPro";
			document.getElementById("lesExpPro").appendChild(input);
			//
			label = document.createElement("label");
			label.id = "expProDuree0";
			label.htmlFor = "expProDuree0";
			label.innerHTML = "Durée : ";
			document.getElementById("lesExpPro").appendChild(label);
			
			input = document.createElement("input");
			input.type = "text";
			input.id="expProDuree0";
			input.name="expPro";
			document.getElementById("lesExpPro").appendChild(input);
			//
			
			var adresse = document.createElement("a");
			var link = document.createTextNode("-");
			adresse.setAttribute("class", "btn btn-primary");
			adresse.setAttribute("id","deleteExpPro0");
			adresse.setAttribute("name","deleteExpPro");
			adresse.setAttribute("onClick","suppressionChampEtBoutonExpPro(0, deleteExpPro0);");
			adresse.appendChild(link);
			
			document.getElementById("lesExpPro").appendChild(adresse);
			document.getElementById("lesExpPro").appendChild(document.createElement("br"));
			
		}else {
			var element = document.getElementsByName("lesExpPro");
			var idelement = element[taille - 1].getAttribute('id');  	
			var sizeId = idelement.length; //recup la taille de l'identifiant
			var nvId = parseInt(idelement.substring("expPro".length, sizeId)) + 1;
			//
			var label = document.createElement("label");
			label.id = "labelExpProPoste"+nvId;
			label.htmlFor = "expProPoste"+nvId;
			label.innerHTML = "Poste : ";
			document.getElementById("lesExpPro").appendChild(label);
			var input = document.createElement("input");
			input.type = "text";
			input.id="expProPoste"+nvId;
			input.name="expPro";
			document.getElementById("lesExpPro").appendChild(input);
			//
			input = document.createElement("input");
			input.type = "text";
			input.id="expProEntreprise"+nvId;
			input.name="expPro";
			document.getElementById("lesExpPro").appendChild(input);
			//
			input = document.createElement("input");
			input.type = "text";
			input.id="expProDebut"+nvId;
			input.name="expPro";
			document.getElementById("lesExpPro").appendChild(input);
			//label
			input = document.createElement("input");
			input.type = "text";
			input.id="expProDuree"+nvId;
			input.name="expPro";
			document.getElementById("lesExpPro").appendChild(input);
			
			var adresse = document.createElement("a");
			var link = document.createTextNode("-");
			adresse.setAttribute("class", "btn btn-primary");
			adresse.setAttribute("id","deleteExpPro"+nvId);
			adresse.setAttribute("name","deleteExpPro");
			adresse.setAttribute("onClick","suppressionChampEtBoutonExpPro("+nvId+", deleteExpPro"+nvId+");");
			adresse.appendChild(link);
			
			document.getElementById("lesExpPro").appendChild(adresse);
			document.getElementById("lesExpPro").appendChild(document.createElement("br"));
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
		if(r == true){
			champ.parentNode.removeChild(champ);
			bouton.parentNode.removeChild(bouton);
		}
	}
	
	function suppressionChampEtBoutonExpPro(index, bouton){
		var r = confirm("Voulez-vous réellement supprimer ce champ ?");
		if(r == true){
			var element = document.getElementById("expProPoste"+index);
			element.parentNode.removeChild(element);
			element = document.getElementById("expProEntreprise"+index);
			element.parentNode.removeChild(element);
			element = document.getElementById("expProDebut"+index);
			element.parentNode.removeChild(element);
			element = document.getElementById("expProDuree"+index);
			element.parentNode.removeChild(element);
			element = document.getElementById("labelExpProPoste"+index);
			element.parentNode.removeChild(element);
			element = document.getElementById("labelExpProEntreprise"+index);
			element.parentNode.removeChild(element);
			element = document.getElementById("labelExpProDebut"+index);
			element.parentNode.removeChild(element);
			element = document.getElementById("labelExpProDuree"+index);
			element.parentNode.removeChild(element);
			bouton.parentNode.removeChild(bouton);
		}
	}
</script>
<!-- ----------------------------------------------------------------------- -->
<!-- ----------------------------------------------------------------------- -->
<!-- ----------------------------Fin Code JS-------------------------------- -->
<!-- ----------------------------------------------------------------------- -->
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/jquery-1.11.0.js"></script>
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
</head>
<body>
	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-4">
					<h1 class="col-md-2">Profil</h1>
					<p></p>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<h2 id="UserName">${etudiant.prenom}${etudiant.nom}</h2>
					<form method="post" action="saveProfile">
						<input type="hidden" id="idEtu" name="idEtu" value="${profil.id}" />
						<ul class="list-group">
							<li><label>Poste actuel :</label> <input type="text"
								id="posteActu" name="posteActu" value="${profil.posteActu}" /></li>
							<li><label>Ville Actuelle:</label> <input type="text"
								id="villeActu" name="villeActu" value="${profil.villeActu}" /></li>
							<li><label>Nom de l'entreprise :</label> <input type="text"
								id="nomEntreprise" name="nomEntreprise"
								value="${profil.nomEntreprise}" /></li>
							<li><label>Mail :</label> <input type="mail" id="mail"
								name="mail" value="${profil.email}" disabled="disabled" /></li>
							<li><label>Numéro de téléphone : </label><input type="tel"
								id="numtelephone" name="numTelephone"
								value="${profil.numTelephone}"></li>

							<li><label>Facebook : </label><input type="text"
								id="facebook" name="facebook" value="${profil.facebook}"></li>
							<li><label>Twitter : </label><input type="text" id="twitter"
								name="twitter" value="${profil.twitter}"></li>
							<li><label>Viadeo : </label><input type="text" id="viadeo"
								name="viadeo" value="${profil.viadeo}"></li>
							<li><label>Linkedin : </label><input type="text"
								id="linkedin" name="linkedin" value="${profil.linkedin}"></li>
							<input type="submit" value="Envoyer" class="btn btn-default" />
						</ul>
				</div>
				</form>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="well well-lg">
						<h2>Expériences Professionnelles</h2>
						<div class="lesExpPro" id="lesExpPro">


							<a class="btn btn-primary"
								onClick="AddExpPro('lesExpPro', 'expPro', 'deleteExpPro');">Ajouter
								une exprérience</a><a class="btn btn-primary"
								onClick="saveExpPro();">Enregistrer</a>
							<c:forEach items="${profil.profil.mesExperiences}"
								var="experience" varStatus="loop">
								<label id="labelExpProPoste${loop.index}"
									for="expProPoste${loop.index}">Poste :</label>
								<input id="expProPoste${loop.index}" name="expPro"
									value="${experience.libelle}">
								<label id="labelExpProEntreprise${loop.index}"
									for="expProEntreprise${loop.index}">Entreprise :</label>
								<input id="expProEntreprise${loop.index}" name="expPro"
									value="${experience.entreprise}">
								<label id="labelExpProDebut${loop.index}"
									for="expProDebut${loop.index}">Année de début :</label>
								<input id="expProDebut${loop.index}" name="expPro"
									value="${experience.anneeDebut}">
								<label id="labelExpProDuree${loop.index}"
									for="expProDuree${loop.index}">Durée :</label>
								<input id="expProDuree${loop.index}" name="expPro"
									value="${experience.duree}">
								<a id="deleteExpPro${loop.index}" name="deleteExpPro"
									class="btn btn-primary"
									onClick='suppressionChampEtBoutonExpPro(${loop.index},
												deleteExpPro${loop.index}); saveExpPro();'>-</a>

								</br>
							</c:forEach>
						</div>
					</div>
				</div>

				<div class="col-md-6">
					<div class="well well-lg">
						<h2>Compétences</h2>
						<div id="lesCompetences" class="lesCompetences">
							<a class="btn btn-primary"
								onClick="AddChamp('lesCompetences', 'competence', 'deleteCompetence','saveCompetence()');">Ajouter
								une compétence</a><a class="btn btn-primary"
								onClick="save('lesCompetences', 'saveCompetence');">Enregistrer</a>
							<ul class="list-group">
								<c:forEach items="${profil.profil.mesCompetences}"
									var="competence" varStatus="loop">
									<input id="competence${loop.index}" name="competence"
										value="${competence.libelle}">
									<a id="deleteCompetence${loop.index}" name="deleteCompetence"
										class="btn btn-primary"
										onClick="suppressionChampEtBouton(competence${loop.index}, deleteCompetence${loop.index}); save('lesCompetences', 'saveCompetence');">-</a>
									</br>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="well well-lg">
						<h2>Formation</h2>
						<div id="lesFormations" class="lesFormations">
							<a class="btn btn-primary"
								onClick="AddChamp('lesFormations', 'formation', 'deleteFormation','saveFormation()');">Ajouter
								une formation</a><a class="btn btn-primary"
								onClick="save('lesFormations', 'saveFormation');">Enregistrer</a>
							<ul class="list-group">
								<c:forEach items="${profil.profil.mesEcoles}" var="formation"
									varStatus="loop">
									<input id="formation${loop.index}" name="formation"
										value="${formation.libelle}">
									<a id="deleteFormation${loop.index}" name="deleteFormation"
										class="btn btn-primary"
										onClick="suppressionChampEtBouton(formation${loop.index}, deleteFormation${loop.index}); save('lesFormations', 'saveFormation');">-</a>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>

				<div class="col-md-6">
					<div class="well well-lg">
						<h2>Loisirs</h2>
						<div id="lesLoisirs" class="lesLoisirs">
							<a class="btn btn-primary"
								onClick="AddChamp('lesLoisirs', 'loisir', 'deleteLoisir','saveLoisir()');">Ajouter
								un loisir</a><a class="btn btn-primary"
								onClick="save('lesLoisirs', 'saveLoisir');">Enregistrer</a>
							<ul class="list-group">
								<c:forEach items="${profil.profil.mesHobbies}" var="loisir"
									varStatus="loop">
									<input id="loisir${loop.index}" name="loisir"
										value="${loisir.libelle}">
									<a id="deleteLoisir${loop.index}" name="deleteLoisir"
										class="btn btn-primary"
										onClick="suppressionChampEtBouton(loisir${loop.index}, deleteLoisir${loop.index}); save('lesLoisirs', 'saveLoisir');">-</a>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
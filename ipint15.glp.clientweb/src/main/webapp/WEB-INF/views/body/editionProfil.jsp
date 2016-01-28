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
		tmp += noeuds[i++].value+"|";
		tmp += noeuds[i++].value+"|";
		tmp += noeuds[i++].value+"|";
		tmp += noeuds[i++].value+"|";
 		tmp += noeuds[i++].value+"%";
 	}
	
	var res = { mail : mail.value,maListe : tmp } ; 	
 	$.ajax({
         type: "POST",
         url: url,
         data: res,
         success: function (result) {
        	 alert ("Modifications enregistrées");
         },
         error: function (result) {
        	 window.location = "/ipint15.glp.clientweb/error";
        }
     });
}





function saveFormation(){
	var name = 'lesFormations';
	var url = 'saveFormation';
	var tmp = "";
	var mail = document.getElementById('mail');
	var maClass= document.getElementById(name);
	var noeuds = maClass.getElementsByTagName('input'); 
	for(i = 0 ; i< noeuds.length;){
		tmp += noeuds[i++].value+"|";
		tmp += noeuds[i++].value+"|";
		tmp += noeuds[i++].value+"|";
		tmp += noeuds[i++].value+"|";
		tmp += noeuds[i++].value+"|";
		tmp += noeuds[i++].value+"|";
 		tmp += noeuds[i++].value+"%";
 	}
	var res = { mail : mail.value,maListe : tmp } ; 	
 	$.ajax({
         type: "POST",
         url: url,
         data: res,
         success: function (result) {
        	 alert ("Modifications enregistrées");
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
        	 alert ("Modifications enregistrées");
         },
         error: function (result) {
        	 window.location = "/ipint15.glp.clientweb/error";
        }
     });
}

function saveCompetence(name, url){
	var mail = document.getElementById('mail');	
	var tmp = "";	
 	var maClass= document.getElementById(name);
 	var noeuds = maClass.getElementsByTagName('input'); 
 	var selects = maClass.getElementsByTagName('select');
 	for(i = 0 ; i< noeuds.length; i++){
 		tmp += noeuds[i].value+"|"+$("#"+selects[i].id+" option:selected").val()+"%";
 	}
 	var res = { mail : mail.value,maListe : tmp } ; 	
 	$.ajax({
         type: "POST",
         url: url,
         data: res,
         success: function (result) {

        	 alert ("Modifications enregistrées");
         },
         error: function (result) {
        	 window.location = "/ipint15.glp.clientweb/error";
        }
     });
}

function saveProfil (){
	var idEtu = document.getElementById('idEtu');
	var posteActu = document.getElementById('posteActu');
	var villeActu = document.getElementById('villeActu');
	var nomEntreprise = document.getElementById('nomEntreprise');
	var mail = document.getElementById('mail');	
	var numTelephone = document.getElementById('numTelephone');
	var facebook = document.getElementById('facebook');
	var twitter = document.getElementById('twitter');
	var viadeo = document.getElementById('viadeo');
	var linkedin = document.getElementById('linkedin');
	var attentes = document.getElementById('attentes');
	
	var res = { idEtu : idEtu.value, posteActu : posteActu.value, villeActu : villeActu.value, nomEntreprise : nomEntreprise.value, mail : mail.value, numTelephone : numTelephone.value, facebook : facebook.value, twitter : twitter.value, viadeo : viadeo.value, linkedin : linkedin.value, attentes : attentes.value} ;
	$.ajax({
        type: "POST",
        url: "saveProfile",
        data: res,
        success: function (result) {
       	 alert ("Modifications enregistrées");
        },
        error: function (result) {
       	 window.location = "/ipint15.glp.clientweb/error";
       }
    });
}

function AddChamp(divId, champId, boutonId, fctSave) {
	var taille = document.getElementsByName(champId).length;
	var nvId;
	if (taille == 0) {
		nvId = 0;
	} else {
		//recuperer l'identifiant du dernier élément | attention : l'identifiant ne correspondant pas au nombre d'element dont le nom est expPro car en cas de suppression id exPro15 alors qu'il ya que 10 elem
			var element = document.getElementsByName(champId);
			var idelement = element[taille - 1].getAttribute('id'); 	
			var sizeId = idelement.length; //recup la taille de l'identifiant
			nvId = parseInt(idelement.substring(champId.length, sizeId)) + 1;
	}
	var input = document.createElement("input");
	input.type = "text";
	input.id=champId+nvId;
	input.name=champId;
	input.setAttribute("maxlength","20");
	document.getElementById(divId).appendChild(input);
	
	if(divId==="lesCompetences"){
		input.setAttribute("class","col-md-9");
		var label = document.createElement("label");
		label.setAttribute("class","col-md-1");
		label.appendChild(document.createTextNode("Niveau :"));
		label.id = "competenceSelectLabel"+nvId;
		label.name = "competenceSelectLabel";
		
		var select = document.createElement("select");
		select.setAttribute("class","col-md-1");
		select.id="competenceSelect"+nvId
		select.name="competenceSelect";
		for(i=1;i<6;i++){
			var option = document.createElement("option");
			option.setAttribute("value",i);
			option.appendChild(document.createTextNode(i));
			select.appendChild(option);
		}
		
		var adresse = document.createElement("a");
		var link = document.createTextNode("");
		adresse.setAttribute("class", "btn btn-primary col-md-1 glyphicon glyphicon-minus-sign");
		adresse.setAttribute("id",boutonId+""+nvId);
		adresse.setAttribute("name", boutonId);
		adresse.setAttribute("onClick", "suppressionChampEtBoutonCompetence("+champId+""+ nvId + ","+ boutonId+"" + nvId + ", competenceSelectLabel"+nvId+", competenceSelect"+nvId+");");
		adresse.appendChild(link);
		
		document.getElementById(divId).appendChild(input);
		document.getElementById(divId).appendChild(label);
		document.getElementById(divId).appendChild(select);
		document.getElementById(divId).appendChild(adresse);
		document.getElementById(divId).appendChild(document.createElement("br"));
	}else{
		input.setAttribute("class","col-md-11");
		var adresse = document.createElement("a");
		var link = document.createTextNode("");
		adresse.setAttribute("class", "btn btn-primary col-md-1 glyphicon glyphicon-minus-sign");
		adresse.setAttribute("id",boutonId+""+nvId);
		adresse.setAttribute("name", boutonId);
		adresse.setAttribute("onClick", "suppressionChampEtBouton("+champId+""+ nvId + ","+ boutonId+"" + nvId + ");");
		adresse.appendChild(link);
		document.getElementById(divId).appendChild(input);
		document.getElementById(divId).appendChild(adresse);
		document.getElementById(divId).appendChild(document.createElement("br"));
	}
	
}

	function createInput(type, id, name, eltDiv){
		var input = document.createElement("input");
		input.setAttribute("maxlength","15");
		input.type = type;
		input.id= id;
		input.name= name;
		eltDiv.appendChild(input);
	}
	function createLabel(id, htmlFor, innerHtml, idElements, eltDiv){
		var label = document.createElement("label");
		label.id = id;
		label.htmlFor = htmlFor;
		label.innerHTML = innerHtml;
		eltDiv.appendChild(label);
	}
	
	

	
	
	function AddExpPro(lesExpPro, expPro, deleteExpPro){
		var taille = document.getElementsByName("divExpPro").length;
		
		if (taille == 0){
			var nvId=0;
		}
		else{
			var element = document.getElementsByName("divExpPro");
			var nvId = element.length;	
		}		
			var div = document.createElement("div");
			div.id = "divExpPro"+nvId;
			div.setAttribute("name", "divExpPro");
			createLabel("labelExpProPoste"+nvId,"expProPoste"+nvId, "Poste : ","lesExpPro", div);
			createInput("text", "expProPoste"+nvId, "expPro", div);	
			createLabel("labelExpProEntreprise"+nvId,"expProEntreprise"+nvId, "Entreprise : ","lesExpPro",div);
			createInput("text", "expProEntreprise"+nvId, "expPro", div);
			createLabel("labelExpProVille"+nvId,"expProVille"+nvId, "Ville : ","lesExpPro", div);
			createInput("text", "expProVille"+nvId, "expPro", div);
			createLabel("labelExpProRegion"+nvId,"expProRegion"+nvId, "Région : ","lesExpPro", div);
			createInput("text", "expProRegion"+nvId, "expPro", div);			
			createLabel("labelExpProPays"+nvId,"expProPays"+nvId, "Pays : ","lesExpPro", div);
			createInput("text", "expProPays"+nvId, "expPro", div);			
			createLabel("labelExpProDebut"+nvId,"expProDebut"+nvId, "Début : ","lesExpPro", div);
			createInput("text", "expProDebut"+nvId, "expPro", div);	
			createLabel("labelExpProFin"+nvId,"expProFin"+nvId, "Fin : ","lesExpPro",div);
			createInput("text", "expProFin"+nvId, "expPro", div);			
			createLabel("labelExpProDescription"+nvId,"expProDescription"+nvId, "Description : ","lesExpPro",div);
			createInput("text", "expProDescription"+nvId, "expPro", div);		

			var adresse = document.createElement("a");
			var link = document.createTextNode("");
			adresse.setAttribute("class", "btn btn-primary col-md-1 glyphicon glyphicon-minus-sign");
			adresse.setAttribute("id","deleteExpPro"+nvId);
			adresse.setAttribute("name","deleteExpPro");
			adresse.setAttribute("onClick","suppressionChampEtBoutonExpPro("+nvId+", deleteExpPro"+nvId+"); saveExpPro");
			adresse.appendChild(link);
			div.appendChild(adresse);
			div.appendChild(document.createElement("br"));
			document.getElementById("lesExpPro").appendChild(div);
		}

	
	
	
	/**
	*Fonction qui permet l'ajout d'une formation
	*/
	
	function AddFormation(lesFormation, formation, deleteFormation){
		var taille = document.getElementsByName("divFormation").length;
		if (taille == 0){
			var nvId=0;
		}
		else{
			var element = document.getElementsByName("divFormation");
			var nvId = element.length;	
		}		
			var div = document.createElement("div");
			div.id = "divFormation"+nvId;
			div.setAttribute("name", "divFormation");
			createLabel("labelFormIntit"+nvId,"formIntit"+nvId, "Intitulé : ","lesFormations", div);
			createInput("text", "formIntit"+nvId, "formation", div);	
			
			createLabel("labelFormEtabl"+nvId,"formEtabl"+nvId, "Établissement : ","lesFormations",div);
			createInput("text", "formEtabl"+nvId, "formation", div);
			
			createLabel("labelFormDebut"+nvId,"formDebut"+nvId, "Début : ","lesFormations", div);
			createInput("text", "formDebut"+nvId, "formation", div);
			
			createLabel("labelFormFin"+nvId,"formFin"+nvId, "Fin : ","lesFormations", div);
			createInput("text", "formFin"+nvId, "formation", div);
			
			createLabel("labelFormVille"+nvId,"formVille"+nvId, "Ville : ","lesFormations", div);
			createInput("text", "formVille"+nvId, "formation", div);
			
			createLabel("labelFormRegion"+nvId,"formRegion"+nvId, "Région : ","lesFormations", div);
			createInput("text", "formRegion"+nvId, "formation", div);
			
			createLabel("labelFormPays"+nvId,"formPays"+nvId, "Pays : ","lesFormations", div);
			createInput("text", "formPays"+nvId, "formation", div);
			
						
			var adresse = document.createElement("a");
			var link = document.createTextNode("");
			adresse.setAttribute("class", "btn btn-primary  glyphicon glyphicon-minus-sign");
			adresse.setAttribute("id","deleteFormation"+nvId);
			adresse.setAttribute("name","deleteFormation");
			adresse.setAttribute("onClick","suppressionChampEtBoutonFormation("+nvId+", deleteFormation"+nvId+");");
			adresse.appendChild(link);
			div.appendChild(adresse);
			div.appendChild(document.createElement("br"));
			document.getElementById("lesFormations").appendChild(div);		
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
	
	function suppressionChampEtBoutonCompetence(champ, bouton, label, select) {
		var r = confirm("Voulez-vous réellement supprimer ce champ ?");
		if(r == true){
			champ.parentNode.removeChild(champ);
			bouton.parentNode.removeChild(bouton);
			label.parentNode.removeChild(label);
			select.parentNode.removeChild(select);
		}
		
	}
	
	/**
	*Fonction permettant de supprimer un input et un label grace a leur identifiant 
	*/
	function suppressionInputlab(idinput, idlabel){
		var element = document.getElementById(idlabel);
		element.parentNode.removeChild(element);
		element = document.getElementById(idinput);
		element.parentNode.removeChild(element);
	}
	
	/**
	*Fonction permettant de supprimer une formation
	*/
	function suppressionChampEtBoutonFormation(index, bouton){
		var r = confirm("Voulez-vous réellement supprimer ce champ ?");
		if(r == true){
			suppressionInputlab("formIntit"+index,"labelFormIntit"+index);
			suppressionInputlab("formEtabl"+index, "labelFormEtabl"+index);
			suppressionInputlab("formDebut"+index, "labelFormDebut"+index);
			suppressionInputlab("formFin"+index, "labelFormFin"+index);
			suppressionInputlab("formVille"+index, "labelFormVille"+index);
			suppressionInputlab("formRegion"+index, "labelFormRegion"+index);
			suppressionInputlab("formPays"+index, "labelFormPays"+index);
			bouton.parentNode.removeChild(bouton);
		}
		
	}
	
	
	/**
	*Fonction qui permet la suppression d'une expérience professionnelle
	*--> suppression des input et des labe
	s
	*/
	function suppressionChampEtBoutonExpPro(index, bouton){
		var r = confirm("Voulez-vous réellement supprimer ce champ ?");
		if(r == true){
			suppressionInputlab("expProPoste"+index,"labelExpProPoste"+index);
			suppressionInputlab("expProEntreprise"+index, "labelExpProEntreprise"+index);
			suppressionInputlab("expProVille"+index, "labelExpProVille"+index);
			suppressionInputlab("expProRegion"+index, "labelExpProRegion"+index);
			suppressionInputlab("expProPays"+index, "labelExpProPays"+index);
			suppressionInputlab("expProDebut"+index, "labelExpProDebut"+index);
			suppressionInputlab("expProFin"+index, "labelExpProFin"+index);
			suppressionInputlab("expProDescription"+index, "labelExpProDescription"+index);
			bouton.parentNode.removeChild(bouton);
		}
		
	}
	
	window.onbeforeunload = function(){
		  return 'Les modifications non enregistrées seront perdues';
		};
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
				<div class="col-md-8">
					<h1 class="col-md-8">Edition de votre Profil</h1>
					<p></p>
				</div>
			</div>
			<div class="row">
				<div class="row">
					<div class="col-md-12">
						<div class="row">
							<h2 id="UserName">${etudiant.prenom} ${etudiant.nom}</h2>
							<div class="col-md-6">
								<input type="hidden" id="idEtu" name="idEtu"
									value="${profil.id}" /> <label class="col-md-4">Poste
									actuel :</label> <input type="text" id="posteActu" name="posteActu"
									value="${profil.posteActu}" class="col-md-8" maxlength="20"/> <label
									class="col-md-4">Ville Actuelle:</label> <input type="text"
									id="villeActu" name="villeActu" value="${profil.villeActu}"
									class="col-md-8" maxlength="20"/> <label class="col-md-4">Nom de
									l'entreprise :</label> <input type="text" id="nomEntreprise"
									name="nomEntreprise" value="${profil.nomEntreprise}"
									class="col-md-8" maxlength="20"/> <label class="col-md-4">Mail :</label> <input
									type="mail" id="mail" name="mail" value="${profil.email}"
									disabled="disabled" class="col-md-8" /> <label
									class="col-md-4">Numéro de téléphone : </label><input
									type="tel" id="numTelephone" name="numTelephone"
									value="${profil.numTelephone}" class="col-md-8" />
							</div>
							<div class="col-md-6">
								<label class="col-md-4">Facebook : </label><input type="text"
									id="facebook" name="facebook" value="${profil.facebook}"
									class="col-md-8" /> <label class="col-md-4">Twitter :
								</label><input type="text" id="twitter" name="twitter"
									value="${profil.twitter}" class="col-md-8" /> <label
									class="col-md-4">Viadeo : </label><input type="text"
									id="viadeo" name="viadeo" value="${profil.viadeo}"
									class="col-md-8" /> <label class="col-md-4">Linkedin :
								</label><input type="text" id="linkedin" name="linkedin"
									value="${profil.linkedin}" class="col-md-8" />
							</div>
						</div>
						<div class="col-md-12">
							<label class="row col-md-4">Ce que je recherche :</label>
							<textarea id="attentes" class="row col-md-12 fixe" rows="4"
								cols="1">
									${profil.attentes}
							</textarea>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-offset-5 col-sm-2 text-center">
						<a
							class="btn btn-primary center-block  glyphicon glyphicon-floppy-disk"
							onClick="saveProfil();"> Enregistrer</a>
					</div>
				</div>
			</div>
			<div class="row col-md-12">
				<div class="well well-lg">
					<h2>Expériences Professionnelles</h2>
					<div class="lesExpPro" id="lesExpPro">
						<a class="btn btn-primary glyphicon glyphicon-plus-sign"
							onClick="AddExpPro('lesExpPro', 'expPro', 'deleteExpPro');"></a>
						<a class="btn btn-primary glyphicon glyphicon-floppy-disk"
							onClick="saveExpPro();"> Enregistrer</a>
						<c:forEach items="${profil.profil.mesExperiences}"
							var="experience" varStatus="loop">
							<div class="row col-md-12">
								<label id="labelExpProPoste${loop.index}"
									for="expProPoste${loop.index}" class="col-md-2">Poste :</label>
								<input id="expProPoste${loop.index}" name="expPro"
									value="${experience.libelle}" maxlength="15" class="col-md-4">
	
								<label id="labelExpProEntreprise${loop.index}"
									for="expProEntreprise${loop.index}" class="col-md-2">Entreprise :</label>
								<input id="expProEntreprise${loop.index}" name="expPro"
									value="${experience.entreprise}" maxlength="15" class="col-md-4">
							</div>
							<div class="row col-md-12">
								<label id="labelExpProVille${loop.index}"
									for="expProVille${loop.index}" class="col-md-2">Ville :</label>
								<input id="expProVille${loop.index}" name="expPro"
									value="${experience.ville}" maxlength="15" class="col-md-2">
	
								<label id="labelExpProRegion${loop.index}"
									for="expProRegion${loop.index}" class="col-md-2">Région :</label>
								<input id="expProRegion${loop.index}" name="expPro"
									value="${experience.region}" maxlength="15" class="col-md-2">
								<label id="labelExpProPays${loop.index}"
									for="expProPays${loop.index}" class="col-md-2">Pays :</label>
								<input id="expProPays${loop.index}" name="expPro"
									value="${experience.pays}" maxlength="15" class="col-md-2">
							</div>
							<div class="row col-md-12">
								<label id="labelExpProDebut${loop.index}"
									for="expProDebut${loop.index}" class="col-md-1">Début :</label>
								<input id="expProDebut${loop.index}" name="expPro"
									value="${experience.debut}" maxlength="15" class="col-md-2">
	
								<label id="labelExpProFin${loop.index}"
									for="expProFin${loop.index}" class="col-md-1">Fin :</label>
								<input id="expProFin${loop.index}" name="expPro"
									value="${experience.fin}" maxlength="15" class="col-md-2">
	
								<label id="labelExpProDescription${loop.index}"
									for="expProDescription${loop.index}" class="col-md-2">Description :</label>
								<input id="expProDescription${loop.index}" name="expPro"
									value="${experience.description}" maxlength="15" class="col-md-3">
	
								<a id="deleteExpPro${loop.index}" name="deleteExpPro"
									class="btn btn-primary  glyphicon glyphicon-minus-sign col-md-1"
									onClick='suppressionChampEtBoutonExpPro(${loop.index},
													deleteExpPro${loop.index}); saveExpPro();'></a>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="row col-md-12">
				<div class="well well-lg">
					<h2>Compétences</h2>
					<div id="lesCompetences" class="lesCompetences">
						<a class="btn btn-primary glyphicon glyphicon-plus-sign"
							onClick="AddChamp('lesCompetences', 'competence', 'deleteCompetence','saveCompetence()');"></a><a
							class="btn btn-primary glyphicon glyphicon-floppy-disk"
							onClick="saveCompetence('lesCompetences', 'saveCompetence');">
							Enregistrer</a>
						<ul class="list-group">
							<c:forEach items="${profil.profil.mesCompetences}"
								var="competence" varStatus="loop">
								<input id="competence${loop.index}" name="competence"
									value="${competence.libelle}" class="col-md-9" maxlength="15"/>
								<label id="competenceSelectLabel${loop.index}" class="col-md-1">Niveau
									:</label>
								<select id="competenceSelect${loop.index}"
									name="competenceSelect" class="col-md-1">
									<c:choose>
										<c:when test="${competence.niveau == 1}">
											<option value="1" selected="selected">1</option>
											<option value="2">2</option>
											<option value="3">3</option>
											<option value="4">4</option>
											<option value="5">5</option>
										</c:when>
										<c:when test="${competence.niveau == 2}">
											<option value="1">1</option>
											<option value="2" selected="selected">2</option>
											<option value="3">3</option>
											<option value="4">4</option>
											<option value="5">5</option>
										</c:when>
										<c:when test="${competence.niveau == 3}">
											<option value="1">1</option>
											<option value="2">2</option>
											<option value="3" selected="selected">3</option>
											<option value="4">4</option>
											<option value="5">5</option>
										</c:when>
										<c:when test="${competence.niveau == 4}">
											<option value="1">1</option>
											<option value="2">2</option>
											<option value="3">3</option>
											<option value="4" selected="selected">4</option>
											<option value="5">5</option>
										</c:when>
										<c:otherwise>
											<option value="1">1</option>
											<option value="2">2</option>
											<option value="3">3</option>
											<option value="4">4</option>
											<option value="5" selected="selected">5</option>
										</c:otherwise>
									</c:choose>
								</select>
								<a id="deleteCompetence${loop.index}" name="deleteCompetence"
									class="btn btn-primary col-md-1 glyphicon glyphicon-minus-sign"
									onClick="suppressionChampEtBoutonCompetence(competence${loop.index}, deleteCompetence${loop.index}, competenceSelectLabel${loop.index}, competenceSelect${loop.index}); saveCompetence('lesCompetences', 'saveCompetence');"></a>
								</br>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
			<div class="row col-md-12">
				<div class="well well-lg">
					<h2>Les formations</h2>
					<div class="lesFormations" id="lesFormations">
						<a class="btn btn-primary glyphicon glyphicon-plus-sign"
							onClick="AddFormation('lesFormations', 'formations', 'deleteFormation');"></a>
						<a class="btn btn-primary glyphicon glyphicon-floppy-disk"
							onClick="saveFormation();"> Enregistrer</a>

						<c:forEach items="${profil.profil.mesEcoles}" var="ecole"
							varStatus="loop">
							<label id="labelFormIntit${loop.index}"
								for="formIntit${loop.index}">Intitulé :</label>
							<input id="formIntit${loop.index}" name="formation"
								value="${ecole.libelle}" maxlength="15">

							<label id="labelFormEtabl${loop.index}"
								for="formEtabl${loop.index}">Établissement :</label>
							<input id="formEtabl${loop.index}" name="formation"
								value="${ecole.etablissement}" maxlength="15">

							<label id="labelFormDebut${loop.index}"
								for="formDebut${loop.index}">Début :</label>
							<input id="formDebut${loop.index}" name="formation"
								value="${ecole.debut}" maxlength="15">

							<label id="labelFormFin${loop.index}" for="formFin${loop.index}">Fin
								:</label>
							<input id="formFin${loop.index}" name="formation"
								value="${ecole.fin}" maxlength="15">

							<label id="labelFormVille${loop.index}"
								for="formVille${loop.index}">Ville :</label>
							<input id="formVille${loop.index}" name="formation"
								value="${ecole.ville}" maxlength="15">

							<label id="labelFormRegion${loop.index}"
								for="formRegion${loop.index}">Région :</label>
							<input id="formRegion${loop.index}" name="formation"
								value="${ecole.region}" maxlength="15">

							<label id="labelFormPays${loop.index}"
								for="formPays${loop.index}">Pays :</label>
							<input id="formPays${loop.index}" name="formation"
								value="${ecole.pays}" maxlength="15">
							<a id="deleteFormation${loop.index}" name="deleteFormation"
								class="btn btn-primary  glyphicon glyphicon-minus-sign"
								onClick='suppressionChampEtBoutonFormation(${loop.index},
												deleteFormation${loop.index}); saveFormation();'></a>
							</br>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="row col-md-12">
				<div class="well well-lg">
					<h2>Loisirs</h2>
					<div id="lesLoisirs" class="lesLoisirs">
						<a class="btn btn-primary glyphicon glyphicon-plus-sign"
							onClick="AddChamp('lesLoisirs', 'loisir', 'deleteLoisir','saveLoisir()');"></a><a
							class="btn btn-primary glyphicon glyphicon-floppy-disk"
							onClick="save('lesLoisirs', 'saveLoisir');"> Enregistrer</a>
						<ul class="list-group">
							<c:forEach items="${profil.profil.mesHobbies}" var="loisir"
								varStatus="loop">
								<input id="loisir${loop.index}" name="loisir"
									value="${loisir.libelle}" class="col-md-11" maxlength="15"/>
								<a id="deleteLoisir${loop.index}" name="deleteLoisir"
									class="btn btn-primary col-md-1 glyphicon glyphicon-minus-sign"
									onClick="suppressionChampEtBouton(loisir${loop.index}, deleteLoisir${loop.index}); save('lesLoisirs', 'saveLoisir');"></a>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
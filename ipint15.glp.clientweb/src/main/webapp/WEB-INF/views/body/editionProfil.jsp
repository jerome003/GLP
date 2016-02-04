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
        	 alertify.success("Les modifications ont bien été enregistré");
         },
         error: function (result) {
        	 alertify.error("Les modifications n'ont pas pu être enregistré");
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
        	 alertify.success("Les modifications ont bien été enregistré");
         },
         error: function (result) {
        	 alertify.error("Les modifications n'ont pas pu être enregistré");
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
        	 alertify.success("Les modifications ont bien été enregistré");
         },
         error: function (result) {
        	 alertify.error("Les modifications n'ont pas pu être enregistré");
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
        	 alertify.success("Les modifications ont bien été enregistré");
         },
         error: function (result) {
        	 alertify.error("Les modifications n'ont pas pu être enregistré");
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
       	 alertify.success("Les modifications ont bien été enregistré");
        },
        error: function (result) {
       	 alertify.error("Les modifications n'ont pas pu être enregistré");
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

	function createInput(type, id, name, eltDiv, colsize){
		var input = document.createElement("input");
		input.setAttribute("maxlength","15");
		input.type = type;
		input.id= id;
		input.name= name;
		input.setAttribute("class", colsize);
		eltDiv.appendChild(input);
	}
	function createLabel(id, htmlFor, innerHtml, idElements, eltDiv, colsize){
		var label = document.createElement("label");
		label.id = id;
		label.htmlFor = htmlFor;
		label.innerHTML = innerHtml;
		label.setAttribute("class", colsize);
		eltDiv.appendChild(label);
	}
	function createRow(eltDiv){
		var row = document.createElement("div");
		row.setAttribute("class", "row");
		eltDiv.appendChild(row);
		return row;
	}
	
	function AddExpPro(){
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
			var row = createRow(div);
			createLabel("labelExpProPoste"+nvId,"expProPoste"+nvId, "Poste : ","lesExpPro", row, "col-md-2");
			createInput("text", "expProPoste"+nvId, "expPro", row, "col-md-4");	
			createLabel("labelExpProEntreprise"+nvId,"expProEntreprise"+nvId, "Entreprise : ","lesExpPro",row, "col-md-2");
			createInput("text", "expProEntreprise"+nvId, "expPro", row, "col-md-4");
			var row2 = createRow(div);
			createLabel("labelExpProVille"+nvId,"expProVille"+nvId, "Ville : ","lesExpPro", row2, "col-md-2");
			createInput("text", "expProVille"+nvId, "expPro", row2, "col-md-4");
			createLabel("labelExpProRegion"+nvId,"expProRegion"+nvId, "Région : ","lesExpPro", row2, "col-md-2");
			createInput("text", "expProRegion"+nvId, "expPro", row2, "col-md-4");
			var row3 = createRow(div);
			createLabel("labelExpProPays"+nvId,"expProPays"+nvId, "Pays : ","lesExpPro", row3, "col-md-2");
			createInput("text", "expProPays"+nvId, "expPro", row3, "col-md-4");			
			createLabel("labelExpProDebut"+nvId,"expProDebut"+nvId, "Début : ","lesExpPro", row3, "col-md-2");
			createInput("text", "expProDebut"+nvId, "expPro", row3, "col-md-4");	
			var row4 = createRow(div);
			createLabel("labelExpProFin"+nvId,"expProFin"+nvId, "Fin : ","lesExpPro",row4, "col-md-2");
			createInput("text", "expProFin"+nvId, "expPro", row4, "col-md-4");			
			createLabel("labelExpProDescription"+nvId,"expProDescription"+nvId, "Description : ","lesExpPro",row4, "col-md-2");
			createInput("text", "expProDescription"+nvId, "expPro", row4, "col-md-3");		

			var adresse = document.createElement("a");
			var link = document.createTextNode("");
			adresse.setAttribute("class", "btn btn-primary col-md-1 glyphicon glyphicon-minus-sign");
			adresse.setAttribute("id","deleteExpPro"+nvId);
			adresse.setAttribute("name","deleteExpPro");
			adresse.setAttribute("onClick","suppressionChampEtBoutonExpPro("+nvId+", deleteExpPro"+nvId+"); saveExpPro");
			adresse.appendChild(link);
			row4.appendChild(adresse);
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
			
			var row = createRow(div);
			createLabel("labelFormIntit"+nvId,"formIntit"+nvId, "Intitulé : ","lesFormations", row, "col-md-2");
			createInput("text", "formIntit"+nvId, "formation", row, "col-md-2");	
			createLabel("labelFormEtabl"+nvId,"formEtabl"+nvId, "Établissement : ","lesFormations",row, "col-md-2");
			createInput("text", "formEtabl"+nvId, "formation", row, "col-md-2");
			createLabel("labelFormDebut"+nvId,"formDebut"+nvId, "Début : ","lesFormations", row, "col-md-2");
			createInput("text", "formDebut"+nvId, "formation", row, "col-md-2");
			
			var row2 = createRow(div);
			createLabel("labelFormFin"+nvId,"formFin"+nvId, "Fin : ","lesFormations", row2, "col-md-2");
			createInput("text", "formFin"+nvId, "formation", row2, "col-md-2");
			createLabel("labelFormVille"+nvId,"formVille"+nvId, "Ville : ","lesFormations", row2, "col-md-2");
			createInput("text", "formVille"+nvId, "formation", row2, "col-md-2");
			createLabel("labelFormRegion"+nvId,"formRegion"+nvId, "Région : ","lesFormations", row2, "col-md-2");
			createInput("text", "formRegion"+nvId, "formation", row2, "col-md-2");
			
			var row3 = createRow(div);
			createLabel("labelFormPays"+nvId,"formPays"+nvId, "Pays : ","lesFormations", row3, "col-md-2");
			createInput("text", "formPays"+nvId, "formation", row3, "col-md-2");
			var adresse = document.createElement("a");
			var link = document.createTextNode("");
			adresse.setAttribute("class", "btn btn-primary  glyphicon glyphicon-minus-sign  col-md-1  col-sm-offset-7");
			adresse.setAttribute("id","deleteFormation"+nvId);
			adresse.setAttribute("name","deleteFormation");
			adresse.setAttribute("onClick","suppressionChampEtBoutonFormation("+nvId+", deleteFormation"+nvId+");");
			adresse.appendChild(link);
			row3.appendChild(adresse);
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
		alertify.confirm('Voulez-vous réellement supprimer ce champ ?', function (e) {
			if (e) {
				champ.parentNode.removeChild(champ);
				bouton.parentNode.removeChild(bouton);
			} else {
				
			}
		});

	}
	
	function suppressionChampEtBoutonCompetence(champ, bouton, label, select) {
		alertify.confirm('Voulez-vous réellement supprimer ce champ ?', function (e) {
			if (e) {
				champ.parentNode.removeChild(champ);
				bouton.parentNode.removeChild(bouton);
				label.parentNode.removeChild(label);
				select.parentNode.removeChild(select);
			} else {
				
			}
		});			
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
		alertify.confirm('Voulez-vous réellement supprimer ce champ ?', function (e) {
			if (e) {
				suppressionInputlab("formIntit"+index,"labelFormIntit"+index);
				suppressionInputlab("formEtabl"+index, "labelFormEtabl"+index);
				suppressionInputlab("formDebut"+index, "labelFormDebut"+index);
				suppressionInputlab("formFin"+index, "labelFormFin"+index);
				suppressionInputlab("formVille"+index, "labelFormVille"+index);
				suppressionInputlab("formRegion"+index, "labelFormRegion"+index);
				suppressionInputlab("formPays"+index, "labelFormPays"+index);
				bouton.parentNode.removeChild(bouton);
			} else {
				
			}
		});		
	}
	
	
	/**
	*Fonction qui permet la suppression d'une expérience professionnelle
	*--> suppression des input et des labe
	s
	*/
	function suppressionChampEtBoutonExpPro(index, bouton){
		alertify.confirm('Voulez-vous réellement supprimer ce champ ?', function (e) {
			if (e) {
				suppressionInputlab("expProPoste"+index,"labelExpProPoste"+index);
				suppressionInputlab("expProEntreprise"+index, "labelExpProEntreprise"+index);
				suppressionInputlab("expProVille"+index, "labelExpProVille"+index);
				suppressionInputlab("expProRegion"+index, "labelExpProRegion"+index);
				suppressionInputlab("expProPays"+index, "labelExpProPays"+index);
				suppressionInputlab("expProDebut"+index, "labelExpProDebut"+index);
				suppressionInputlab("expProFin"+index, "labelExpProFin"+index);
				suppressionInputlab("expProDescription"+index, "labelExpProDescription"+index);
				bouton.parentNode.removeChild(bouton);
			} else {
				
			}
		});				
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
<script type="text/javascript">
window.onload = function(){
	var elem = document.getElementById('attentes');
	elem.focus();
	elem.selectionStart = elem.value.length;
}
</script>
<body>
	<div class="section">
		<div class="container">
			<div class="row">
				<form action="#" method="post">
					<legend>Informations personelles</legend>
					<fieldset>
						<div class='row'>
							<div class='col-sm-4'>
								<div class='form-group'>
									<input type="hidden" id="idEtu" name="idEtu"
										value="${profil.id}" /> <label for="mail">Mail</label> <input
										class="form-control" id="mail" value="${profil.email}"
										name="mail" disabled="disabled" required="true" size="30"
										type="text" />
								</div>
							</div>
							<div class='col-sm-4'>
								<div class='form-group'>
									<label for="numTelephone">Téléphone</label> <input
										class="form-control" id="numTelephone"
										value="${profil.numTelephone}" name="numTelephone"
										required="true" size="30" type="text" />
								</div>
							</div>
						</div>
						<div class='row'>
							<div class='col-sm-8'>
								<div class="form-group">
									<label for="attentes">Ce que je recherche</label>
									<textarea class="form-control" id="attentes"><c:out
											value="${profil.attentes}" /></textarea>
								</div>
							</div>
						</div>
					</fieldset>
					<legend>Situation actuelle</legend>
					<fieldset>
						<div class="row">
							<div class='col-sm-4'>
								<div class='form-group'>
									<label class="radio-inline"><input type="radio"
										name="optradio">En emploi</label> <label class="radio-inline"><input
										type="radio" name="optradio">Sans emploi</label> <label
										class="radio-inline"><input type="radio"
										name="optradio">Freelance</label>
								</div>
							</div>
						</div>
						<div class='row'>
							<div class='col-sm-4'>
								<div class='form-group'>
									<label for="posteActu">Poste</label> <input
										class="form-control" id="posteActu"
										value="${profil.posteActu}" name="posteActu" required="true"
										size="30" type="text" />
								</div>
							</div>
							<div class='col-sm-4'>
								<div class='form-group'>
									<label for="villeActu">Ville actuelle</label> <input
										class="form-control" id="villeActu"
										value="${profil.villeActu}" name="villeActu" required="true"
										size="30" type="text" />
								</div>
							</div>
							<div class='col-sm-4'>
								<div class='form-group'>
									<label for="nomEntreprise">Nom de l'entreprise</label> <input
										class="form-control" id="nomEntreprise"
										value="${profil.nomEntreprise}" name="nomEntreprise"
										required="true" size="30" type="text" />
								</div>
							</div>
						</div>
					</fieldset>
					<legend>Réseaux sociaux</legend>
					<fieldset>
						<div class='row'>
							<div class='col-sm-3'>
								<div class='form-group'>
									<label for="twitter">Twitter</label> <input
										class="form-control" id="twitter" value="${profil.twitter}"
										name="twitter" required="true" size="30" type="text" />
								</div>
							</div>
							<div class='col-sm-3'>
								<div class='form-group'>
									<label for="facebook">Facebook</label> <input
										class="form-control" id="facebook" value="${profil.facebook}"
										name="facebook" required="true" size="30" type="text" />
								</div>
							</div>
							<div class='col-sm-3'>
								<div class='form-group'>
									<label for="viadeo">Viadeo</label> <input class="form-control"
										id="viadeo" value="${profil.viadeo}" name="viadeo"
										required="true" size="30" type="text" />
								</div>
							</div>
							<div class='col-sm-3'>
								<div class='form-group'>
									<label for="linkedin">Linkedin</label> <input
										class="form-control" id="linkedin" value="${profil.linkedin}"
										name="linkedin" required="true" size="30" type="text" />
								</div>
							</div>
						</div>
					</fieldset>
				</form>
			</div>
			<div class="row">
				<div class="col-sm-offset-5 col-sm-2 text-center">
					<a
						class="btn btn-primary center-block  glyphicon glyphicon-floppy-disk"
						onClick="saveProfil();"> Enregistrer</a>
				</div>
			</div>
			<div class="row">
				<legend>Expériences Professionnelles</legend>
				<div class="well well-lg" id="lesExpPro">
					<a class="btn btn-primary glyphicon glyphicon-plus-sign"
						onClick="AddExpPro();"></a> <a
						class="btn btn-primary glyphicon glyphicon-floppy-disk"
						onClick="saveExpPro();"> Enregistrer</a>
					<c:forEach items="${profil.profil.mesExperiences}" var="experience"
						varStatus="loop">
						<div id="divExpPro${loop.index}" name="divExpPro">
							<div class="row">
								<label id="labelExpProPoste${loop.index}"
									for="expProPoste${loop.index}" class="col-md-2">Poste :</label>
								<input id="expProPoste${loop.index}" name="expPro"
									value="${experience.libelle}" maxlength="15" class="col-md-4">

								<label id="labelExpProEntreprise${loop.index}"
									for="expProEntreprise${loop.index}" class="col-md-2">Entreprise
									:</label> <input id="expProEntreprise${loop.index}" name="expPro"
									value="${experience.entreprise}" maxlength="15"
									class="col-md-4">
							</div>
							<div class="row">
								<label id="labelExpProVille${loop.index}"
									for="expProVille${loop.index}" class="col-md-2">Ville :</label>
								<input id="expProVille${loop.index}" name="expPro"
									value="${experience.ville}" maxlength="15" class="col-md-4">

								<label id="labelExpProRegion${loop.index}"
									for="expProRegion${loop.index}" class="col-md-2">Région
									:</label> <input id="expProRegion${loop.index}" name="expPro"
									value="${experience.region}" maxlength="15" class="col-md-4">
							</div>
							<div class="row">
								<label id="labelExpProPays${loop.index}"
									for="expProPays${loop.index}" class="col-md-2">Pays :</label> <input
									id="expProPays${loop.index}" name="expPro"
									value="${experience.pays}" maxlength="15" class="col-md-4">
								<label id="labelExpProDebut${loop.index}"
									for="expProDebut${loop.index}" class="col-md-2">Début :</label>
								<input id="expProDebut${loop.index}" name="expPro"
									value="${experience.debut}" maxlength="15" class="col-md-4">
							</div>
							<div class="row">
								<label id="labelExpProFin${loop.index}"
									for="expProFin${loop.index}" class="col-md-2">Fin :</label> <input
									id="expProFin${loop.index}" name="expPro"
									value="${experience.fin}" maxlength="15" class="col-md-4">

								<label id="labelExpProDescription${loop.index}"
									for="expProDescription${loop.index}" class="col-md-2">Description
									:</label> <input id="expProDescription${loop.index}" name="expPro"
									value="${experience.description}" maxlength="15"
									class="col-md-3"> <a id="deleteExpPro${loop.index}"
									name="deleteExpPro"
									class="btn btn-primary  glyphicon glyphicon-minus-sign col-md-1"
									onClick='suppressionChampEtBoutonExpPro(${loop.index},
													deleteExpPro${loop.index}); saveExpPro();'></a>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
			<div class="row">
				<legend>Compétences</legend>
				<div id="lesCompetences" class="well well-lg">
					<a class="btn btn-primary glyphicon glyphicon-plus-sign"
						onClick="AddChamp('lesCompetences', 'competence', 'deleteCompetence','saveCompetence()');"></a><a
						class="btn btn-primary glyphicon glyphicon-floppy-disk"
						onClick="saveCompetence('lesCompetences', 'saveCompetence');">
						Enregistrer</a>
					<ul class="list-group">
						<c:forEach items="${profil.profil.mesCompetences}"
							var="competence" varStatus="loop">
							<input id="competence${loop.index}" name="competence"
								value="${competence.libelle}" class="col-md-9" maxlength="15" />
							<label id="competenceSelectLabel${loop.index}" class="col-md-1">Niveau
								:</label>
							<select id="competenceSelect${loop.index}"
								name="competenceSelect" class="col-md-1">
								<option value="1" ${competence.niveau == '1' ? 'selected' : ''}>1</option>
								<option value="2" ${competence.niveau == '2' ? 'selected' : ''}>2</option>
								<option value="3" ${competence.niveau == '3' ? 'selected' : ''}>3</option>
								<option value="4" ${competence.niveau == '4' ? 'selected' : ''}>4</option>
								<option value="5" ${competence.niveau == '5' ? 'selected' : ''}>5</option>
							</select>
							<a id="deleteCompetence${loop.index}" name="deleteCompetence"
								class="btn btn-primary col-md-1 glyphicon glyphicon-minus-sign"
								onClick="suppressionChampEtBoutonCompetence(competence${loop.index}, deleteCompetence${loop.index}, competenceSelectLabel${loop.index}, competenceSelect${loop.index}); saveCompetence('lesCompetences', 'saveCompetence');"></a>
							</br>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div class="row">
				<legend>Formations</legend>
				<div class="well well-lg">
					<div class="lesFormations" id="lesFormations">
						<a class="btn btn-primary glyphicon glyphicon-plus-sign"
							onClick="AddFormation('lesFormations', 'formations', 'deleteFormation');"></a>
						<a class="btn btn-primary glyphicon glyphicon-floppy-disk"
							onClick="saveFormation();"> Enregistrer</a>

						<c:forEach items="${profil.profil.mesEcoles}" var="ecole"
							varStatus="loop">
							<div class="divFormation" name="divFormation">
								<div class="row">
									<label id="labelFormIntit${loop.index}"
										for="formIntit${loop.index}" class="col-md-2">Intitulé
										:</label> <input id="formIntit${loop.index}" name="formation"
										value="${ecole.libelle}" maxlength="15" class="col-md-2">

									<label id="labelFormEtabl${loop.index}"
										for="formEtabl${loop.index}" class="col-md-2">Établissement
										:</label> <input id="formEtabl${loop.index}" name="formation"
										value="${ecole.etablissement}" maxlength="15" class="col-md-2">

									<label id="labelFormDebut${loop.index}"
										for="formDebut${loop.index}" class="col-md-2">Début :</label>
									<input id="formDebut${loop.index}" name="formation"
										value="${ecole.debut}" maxlength="15" class="col-md-2">
								</div>
								<div class="row">
									<label id="labelFormFin${loop.index}"
										for="formFin${loop.index}" class="col-md-2">Fin :</label> <input
										id="formFin${loop.index}" name="formation"
										value="${ecole.fin}" maxlength="15" class="col-md-2">
									<label id="labelFormVille${loop.index}"
										for="formVille${loop.index}" class="col-md-2">Ville :</label>
									<input id="formVille${loop.index}" name="formation"
										value="${ecole.ville}" maxlength="15" class="col-md-2">

									<label id="labelFormRegion${loop.index}"
										for="formRegion${loop.index}" class="col-md-2">Région
										:</label> <input id="formRegion${loop.index}" name="formation"
										value="${ecole.region}" maxlength="15" class="col-md-2">
								</div>
								<div class="row">
									<label id="labelFormPays${loop.index}"
										for="formPays${loop.index}" class="col-md-2">Pays :</label> <input
										id="formPays${loop.index}" name="formation"
										value="${ecole.pays}" maxlength="15" class="col-md-2">
									<a id="deleteFormation${loop.index}" name="deleteFormation"
										class="btn btn-primary  glyphicon glyphicon-minus-sign col-md-1 col-sm-offset-7"
										onClick='suppressionChampEtBoutonFormation(${loop.index},
													deleteFormation${loop.index}); saveFormation();'></a>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="row">
				<legend>Loisirs</legend>
				<div class="well well-lg">
					<div id="lesLoisirs" class="lesLoisirs">
						<a class="btn btn-primary glyphicon glyphicon-plus-sign"
							onClick="AddChamp('lesLoisirs', 'loisir', 'deleteLoisir','saveLoisir()');"></a><a
							class="btn btn-primary glyphicon glyphicon-floppy-disk"
							onClick="save('lesLoisirs', 'saveLoisir');"> Enregistrer</a>
						<ul class="list-group">
							<c:forEach items="${profil.profil.mesHobbies}" var="loisir"
								varStatus="loop">
								<input id="loisir${loop.index}" name="loisir"
									value="${loisir.libelle}" class="col-md-11" maxlength="15" />
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
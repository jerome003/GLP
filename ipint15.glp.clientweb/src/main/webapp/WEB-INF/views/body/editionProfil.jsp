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
        	 alertify.success("Les modifications ont bien �t� enregistr�");
         },
         error: function (result) {
        	 alertify.error("Les modifications n'ont pas pu �tre enregistr�");
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
        	 alertify.success("Les modifications ont bien �t� enregistr�");
         },
         error: function (result) {
        	 alertify.error("Les modifications n'ont pas pu �tre enregistr�");
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
        	 alertify.success("Les modifications ont bien �t� enregistr�");
         },
         error: function (result) {
        	 alertify.error("Les modifications n'ont pas pu �tre enregistr�");
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
        	 alertify.success("Les modifications ont bien �t� enregistr�");
         },
         error: function (result) {
        	 alertify.error("Les modifications n'ont pas pu �tre enregistr�");
        }
     });
}

function AddChamp(divId, champId, boutonId, fctSave) {
	var taille = document.getElementsByName(champId).length;
	var nvId;
	if (taille == 0) {
		nvId = 0;
	} else {
		//recuperer l'identifiant du dernier �l�ment | attention : l'identifiant ne correspondant pas au nombre d'element dont le nom est expPro car en cas de suppression id exPro15 alors qu'il ya que 10 elem
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
	
	
	function AddExpPro2(){
		var taille = document.getElementsByName("ExpPro").length;
		
		var div = document.createElement("div");
		div.setAttribute("class","well well-lg");
		div.id = "MonExperience"+taille;
		div.setAttribute("name","ExpPro");
		document.getElementById("Experiences").appendChild(div);
		
		var legend = document.createElement("legend");
		legend.innerHTML = "Experience";
		div.appendChild(legend);
		
		var div2 = document.createElement("div");
		div2.setAttribute("class","form-group");
		div.appendChild(div2);
		
		var row1 = createRow(div2);
		div2.appendChild(document.createElement("br"));
		var row2 = createRow(div2);
		var row3 = createRow(div2);
		div2.appendChild(document.createElement("br"));
		var row4 = createRow(div2);
		div2.appendChild(document.createElement("br"));
		var row5 = createRow(div2);
		
		// Ligne 1
		var divPoste = document.createElement("div");
		divPoste.setAttribute("class","col-sm-6");
		row1.appendChild(divPoste);
		var divEntreprise = document.createElement("div");
		divEntreprise.setAttribute("class","col-sm-6");
		row1.appendChild(divEntreprise);
		
		//Poste
		var divLabelPoste = document.createElement("div");
		divLabelPoste.setAttribute("class","col-sm-2");
		divPoste.appendChild(divLabelPoste);
		var divInputPoste = document.createElement("div");
		divInputPoste.setAttribute("class","col-sm-10");
		divPoste.appendChild(divInputPoste);
		var labelPoste = document.createElement("label");
		labelPoste.htmlFor = "poste";
		labelPoste.innerHTML = "Poste";
		divLabelPoste.appendChild(labelPoste);
		var inputPoste = document.createElement("input");
		inputPoste.id= "expProPoste"+taille;
		inputPoste.name= "poste";
		inputPoste.setAttribute("class","form-control");
		divInputPoste.appendChild(inputPoste);
		
		//Entreprise
		var divLabelEntreprise = document.createElement("div");
		divLabelEntreprise.setAttribute("class","col-sm-2");
		divEntreprise.appendChild(divLabelEntreprise);
		var divInputEntreprise = document.createElement("div");
		divInputEntreprise.setAttribute("class","col-sm-10");
		divEntreprise.appendChild(divInputEntreprise);
		var labelEntreprise = document.createElement("label");
		labelEntreprise.htmlFor = "entreprise";
		labelEntreprise.innerHTML = "Entreprise";
		divLabelEntreprise.appendChild(labelEntreprise);
		var inputEntreprise = document.createElement("input");
		inputEntreprise.id= "expProEntreprise"+taille;
		inputEntreprise.name= "entreprise";
		inputEntreprise.setAttribute("class","form-control");
		divInputEntreprise.appendChild(inputEntreprise);
		
		//Ligne 2
		var divVille = document.createElement("div");
		divVille.setAttribute("class","col-sm-6");
		row2.appendChild(divVille);
		var divRegion = document.createElement("div");
		divRegion.setAttribute("class","col-sm-6");
		row2.appendChild(divRegion);
		
		//Ville
		var divLabelVille = document.createElement("div");
		divLabelVille.setAttribute("class","col-sm-2");
		divVille.appendChild(divLabelVille);
		var divInputVille = document.createElement("div");
		divInputVille.setAttribute("class","col-sm-10");
		divVille.appendChild(divInputVille);
		var labelVille = document.createElement("label");
		labelVille.htmlFor = "ville";
		labelVille.innerHTML = "Ville";
		divLabelVille.appendChild(labelVille);
		var inputVille = document.createElement("input");
		inputVille.id= "expProVille"+taille;
		inputVille.name= "ville";
		inputVille.setAttribute("class","form-control");
		divInputVille.appendChild(inputVille);
		
		//Region
		var divLabelRegion = document.createElement("div");
		divLabelRegion.setAttribute("class","col-sm-2");
		divRegion.appendChild(divLabelRegion);
		var divInputRegion = document.createElement("div");
		divInputRegion.setAttribute("class","col-sm-10");
		divRegion.appendChild(divInputRegion);
		var labelRegion = document.createElement("label");
		labelRegion.htmlFor = "region";
		labelRegion.innerHTML = "Region";
		divLabelRegion.appendChild(labelRegion);
		var inputRegion = document.createElement("input");
		inputRegion.id= "expProRegion"+taille;
		inputRegion.name= "region";
		inputRegion.setAttribute("class","form-control");
		divInputRegion.appendChild(inputRegion);
		
		//Ligne 3
		var divPays = document.createElement("div");
		divPays.setAttribute("class","col-sm-6");
		row2.appendChild(divPays);

		//Pays
		var divLabelPays = document.createElement("div");
		divLabelPays.setAttribute("class","col-sm-2");
		divPays.appendChild(divLabelPays);
		var divInputPays = document.createElement("div");
		divInputPays.setAttribute("class","col-sm-10");
		divPays.appendChild(divInputPays);
		var labelPays = document.createElement("label");
		labelPays.htmlFor = "pays";
		labelPays.innerHTML = "Pays";
		divLabelPays.appendChild(labelRegion);
		var inputPays = document.createElement("input");
		inputPays.id= "expProPays"+taille;
		inputPays.name= "pays";
		inputPays.setAttribute("class","form-control");
		divInputPays.appendChild(inputPays);
		
		//Ligne 4
		var divDebut = document.createElement("div");
		divDebut.setAttribute("class","col-sm-6");
		row4.appendChild(divDebut);
		var divFin = document.createElement("div");
		divFin.setAttribute("class","col-sm-6");
		row4.appendChild(divFin);
		
		//Debut
		var divLabelDebut = document.createElement("div");
		divLabelDebut.setAttribute("class","col-sm-2");
		divDebut.appendChild(divLabelDebut);
		var divInputDebut = document.createElement("div");
		divInputDebut.setAttribute("class","col-sm-10");
		divDebut.appendChild(divInputDebut);
		var labelDebut = document.createElement("label");
		labelDebut.htmlFor = "debut";
		labelDebut.innerHTML = "Date d�but";
		divLabelDebut.appendChild(labelDebut);
		var inputDebut = document.createElement("input");
		inputDebut.id= "expProDebut"+taille;
		inputDebut.name= "debut";
		inputDebut.setAttribute("class","form-control");
		divInputDebut.appendChild(inputDebut);
		
		//Fin
		var divLabelFin = document.createElement("div");
		divLabelFin.setAttribute("class","col-sm-2");
		divFin.appendChild(divLabelFin);
		var divInputFin = document.createElement("div");
		divInputFin.setAttribute("class","col-sm-10");
		divFin.appendChild(divInputFin);
		var labelFin = document.createElement("label");
		labelFin.htmlFor = "fin";
		labelFin.innerHTML = "Date fin";
		divLabelFin.appendChild(labelFin);
		var inputFin = document.createElement("input");
		inputFin.id= "expProFin"+taille;
		inputFin.name= "fin";
		inputFin.setAttribute("class","form-control");
		divInputFin.appendChild(inputFin);
		
		//Ligne 5 Description
		var divLabelDescription = document.createElement("div");
		divLabelDescription.setAttribute("class","col-sm-1");
		row5.appendChild(divLabelDescription);
		var divTextareaDescription = document.createElement("div");
		divTextareaDescription.setAttribute("class","col-sm-11");
		row5.appendChild(divTextareaDescription);
		var labelDescription = document.createElement("label");
		labelDescription.htmlFor = "description";
		labelDescription.innerHTML = "Description";
		divLabelDescription.appendChild(labelDescription);
		var textareaDescription = document.createElement("textarea");
		textareaDescription.id= "expProDescription"+taille;
		textareaDescription.name= "description";
		textareaDescription.setAttribute("class","form-control");
		divTextareaDescription.appendChild(textareaDescription);
		
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
			createLabel("labelExpProRegion"+nvId,"expProRegion"+nvId, "R�gion : ","lesExpPro", row2, "col-md-2");
			createInput("text", "expProRegion"+nvId, "expPro", row2, "col-md-4");
			var row3 = createRow(div);
			createLabel("labelExpProPays"+nvId,"expProPays"+nvId, "Pays : ","lesExpPro", row3, "col-md-2");
			createInput("text", "expProPays"+nvId, "expPro", row3, "col-md-4");			
			createLabel("labelExpProDebut"+nvId,"expProDebut"+nvId, "D�but : ","lesExpPro", row3, "col-md-2");
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
			createLabel("labelFormIntit"+nvId,"formIntit"+nvId, "Intitul� : ","lesFormations", row, "col-md-2");
			createInput("text", "formIntit"+nvId, "formation", row, "col-md-2");	
			createLabel("labelFormEtabl"+nvId,"formEtabl"+nvId, "�tablissement : ","lesFormations",row, "col-md-2");
			createInput("text", "formEtabl"+nvId, "formation", row, "col-md-2");
			createLabel("labelFormDebut"+nvId,"formDebut"+nvId, "D�but : ","lesFormations", row, "col-md-2");
			createInput("text", "formDebut"+nvId, "formation", row, "col-md-2");
			
			var row2 = createRow(div);
			createLabel("labelFormFin"+nvId,"formFin"+nvId, "Fin : ","lesFormations", row2, "col-md-2");
			createInput("text", "formFin"+nvId, "formation", row2, "col-md-2");
			createLabel("labelFormVille"+nvId,"formVille"+nvId, "Ville : ","lesFormations", row2, "col-md-2");
			createInput("text", "formVille"+nvId, "formation", row2, "col-md-2");
			createLabel("labelFormRegion"+nvId,"formRegion"+nvId, "R�gion : ","lesFormations", row2, "col-md-2");
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
		alertify.confirm('Voulez-vous r�ellement supprimer ce champ ?', function (e) {
			if (e) {
				champ.parentNode.removeChild(champ);
				bouton.parentNode.removeChild(bouton);
			} else {
				
			}
		});
	}
	
	function suppressionChampEtBoutonCompetence(champ, bouton, label, select) {
		alertify.confirm('Voulez-vous r�ellement supprimer ce champ ?', function (e) {
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
		alertify.confirm('Voulez-vous r�ellement supprimer ce champ ?', function (e) {
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
	*Fonction qui permet la suppression d'une exp�rience professionnelle
	*--> suppression des input et des labe
	s
	*/
	function suppressionChampEtBoutonExpPro(index, bouton){
		alertify.confirm('Voulez-vous r�ellement supprimer ce champ ?', function (e) {
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
		  return 'Les modifications non enregistr�es seront perdues';
		};
</script>
<!-- ----------------------------------------------------------------------- -->
<!-- ----------------------------------------------------------------------- -->
<!-- ----------------------------Fin Code JS-------------------------------- -->
<!-- ----------------------------------------------------------------------- -->
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
			<form action="saveProfil" method="post">
				<div class="row">
					<div class="row">

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
										<label for="numTelephone">T�l�phone</label> <input
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

										<textarea class="form-control" id="attentes" name="attentes"><c:out
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
										<label class="radio-inline"> <input type="radio"
											name="statut" value="En emploi"
											${profil.statut == 'En emploi' ? 'checked' : ''}>En
											emploi
										</label> <label class="radio-inline"> <input type="radio"
											name="statut" value="Sans emploi"
											${profil.statut == 'Sans emploi' ? 'checked' : ''}>Sans
											emploi
										</label> <label class="radio-inline"> <input type="radio"
											name="statut" value="Freelance"
											${profil.statut == 'Freelance' ? 'checked' : ''}>Freelance
										</label>
									</div>
								</div>
							</div>
							<div class='row'>
								<div class='col-sm-4'>
									<div class='form-group'>
										<label for="posteActu">Poste</label> <input
											class="form-control" id="posteActu"
											value="${profil.posteActu}" name="posteActu" size="30"
											type="text" />
									</div>
								</div>
								<div class='col-sm-4'>
									<div class='form-group'>
										<label for="villeActu">Ville actuelle</label> <input
											class="form-control" id="villeActu"
											value="${profil.villeActu}" name="villeActu" size="30"
											type="text" />
									</div>
								</div>
								<div class='col-sm-4'>
									<div class='form-group'>
										<label for="nomEntreprise">Nom de l'entreprise</label> <input
											class="form-control" id="nomEntreprise"
											value="${profil.nomEntreprise}" name="nomEntreprise"
											size="30" type="text" />
									</div>
								</div>
							</div>
						</fieldset>
						<legend>R�seaux sociaux</legend>
						<fieldset>
							<div class='row'>
								<div class='col-sm-3'>
									<div class='form-group'>
										<label for="twitter">Twitter</label> <input
											class="form-control" id="twitter" value="${profil.twitter}"
											name="twitter" size="30" type="text" />
									</div>
								</div>
								<div class='col-sm-3'>
									<div class='form-group'>
										<label for="facebook">Facebook</label> <input
											class="form-control" id="facebook" value="${profil.facebook}"
											name="facebook" size="30" type="text" />
									</div>
								</div>
								<div class='col-sm-3'>
									<div class='form-group'>
										<label for="viadeo">Viadeo</label> <input class="form-control"
											id="viadeo" value="${profil.viadeo}" name="viadeo" size="30"
											type="text" />
									</div>
								</div>
								<div class='col-sm-3'>
									<div class='form-group'>
										<label for="linkedin">Linkedin</label> <input
											class="form-control" id="linkedin" value="${profil.linkedin}"
											name="linkedin" size="30" type="text" />
									</div>
								</div>
							</div>
						</fieldset>
						<div class="row">
							<div class="col-sm-offset-5 col-sm-2 text-center">
								<button type="submit" class="btn btn-success">Enregistrer</button>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<h3>Exp�riences Professionnelles <a class="btn btn-default glyphicon glyphicon-plus-sign"
							onClick="AddExpPro2();"></a></h3>
					<div class="row" id="Experiences">
						<c:forEach items="${profil.profil.mesExperiences}"
							var="experience" varStatus="loop">
							<div class="well well-lg" id="MonExperience${loop.index}" name="ExpPro">
								<legend>Experience</legend>
								<div class='form-group'>
									<div class="row">
										<div class='col-sm-6'>
											<div class='col-sm-2'>
												<label for="poste">Poste</label>
											</div>
											<div class='col-sm-10'>
												<input id="expProPoste${loop.index}" name="poste"
													value="${experience.libelle}" maxlength="15"
													class="form-control">
											</div>
										</div>
										<div class='col-sm-6'>
											<div class='col-sm-2'>
												<label for="entreprise">Entreprise</label>
											</div>
											<div class='col-sm-10'>
												<input id="expProEntreprise${loop.index}" name="entreprise"
													value="${experience.entreprise}" maxlength="15"
													class="form-control">
											</div>
										</div>
									</div>
									<br>
									<div class="row">
										<div class='col-sm-6'>
											<div class='col-sm-2'>
												<label for="ville">Ville</label>
											</div>
											<div class='col-sm-10'>
												<input id="expProVille${loop.index}" name="ville"
													value="${experience.ville}" maxlength="15"
													class="form-control">
											</div>
										</div>
										<div class='col-sm-6'>
											<div class='col-sm-2'>
												<label for="region">Region</label>
											</div>
											<div class='col-sm-10'>
												<input id="expProRegion${loop.index}" name="region"
													value="${experience.region}" maxlength="15"
													class="form-control">
											</div>
										</div>
									</div>
									<div class="row">
										<div class='col-sm-6'>
											<div class='col-sm-2'>
												<label for="ville">Pays</label>
											</div>
											<div class='col-sm-10'>
												<input id="expProPays${loop.index}" name="pays"
													value="${experience.pays}" maxlength="15"
													class="form-control">
											</div>
										</div>
									</div>
									<br>
									<div class="row">
										<div class='col-sm-6'>
											<div class='col-sm-2'>
												<label for="ville">Date d�but</label>
											</div>
											<div class='col-sm-10'>
												<input id="expProDebut${loop.index}" name="debut"
													value="${experience.debut}" maxlength="15"
													class="form-control">
											</div>
										</div>
										<div class='col-sm-6'>
											<div class='col-sm-2'>
												<label for="ville">Date fin</label>
											</div>
											<div class='col-sm-10'>
												<input id="expProFin${loop.index}" name="fin"
													value="${experience.fin}" maxlength="15"
													class="form-control">
											</div>
										</div>
									</div>
									<br>
									<div class="row">
										<div class='col-sm-1'>
											<label for="ville">Description</label>
										</div>
										<div class='col-sm-11'>
											<textarea class="form-control"
												id="expProDescription${loop.index}" name="description"><c:out
													value="${experience.description}" /></textarea>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				<div class="row">
					<h3>Exp�riences Professionnelles</h3>
					<div class="well well-lg" id="lesExpPro">
						<a class="btn btn-primary glyphicon glyphicon-plus-sign"
							onClick="AddExpPro();"></a> <a
							class="btn btn-primary glyphicon glyphicon-floppy-disk"
							onClick="saveExpPro();"> Enregistrer</a>
						<c:forEach items="${profil.profil.mesExperiences}"
							var="experience" varStatus="loop">
							<div id="divExpPro${loop.index}" name="divExpPro">
								<div class="row">
									<label id="labelExpProPoste${loop.index}"
										for="expProPoste${loop.index}" class="col-md-2">Poste
										:</label> <input id="expProPoste${loop.index}" name="expPro"
										value="${experience.libelle}" maxlength="15" class="col-md-4">

									<label id="labelExpProEntreprise${loop.index}"
										for="expProEntreprise${loop.index}" class="col-md-2">Entreprise
										:</label> <input id="expProEntreprise${loop.index}" name="expPro"
										value="${experience.entreprise}" maxlength="15"
										class="col-md-4">
								</div>
								<div class="row">
									<label id="labelExpProVille${loop.index}"
										for="expProVille${loop.index}" class="col-md-2">Ville
										:</label> <input id="expProVille${loop.index}" name="expPro"
										value="${experience.ville}" maxlength="15" class="col-md-4">

									<label id="labelExpProRegion${loop.index}"
										for="expProRegion${loop.index}" class="col-md-2">R�gion
										:</label> <input id="expProRegion${loop.index}" name="expPro"
										value="${experience.region}" maxlength="15" class="col-md-4">
								</div>
								<div class="row">
									<label id="labelExpProPays${loop.index}"
										for="expProPays${loop.index}" class="col-md-2">Pays :</label>
									<input id="expProPays${loop.index}" name="expPro"
										value="${experience.pays}" maxlength="15" class="col-md-4">
									<label id="labelExpProDebut${loop.index}"
										for="expProDebut${loop.index}" class="col-md-2">D�but
										:</label> <input id="expProDebut${loop.index}" name="expPro"
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
				<div class="row col-md-12">
					<h3>Comp�tences</h3>
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
				<div class="row col-md-12">
					<h3>Formations</h3>
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
											for="formIntit${loop.index}" class="col-md-2">Intitul�
											:</label> <input id="formIntit${loop.index}" name="formation"
											value="${ecole.libelle}" maxlength="15" class="col-md-2">

										<label id="labelFormEtabl${loop.index}"
											for="formEtabl${loop.index}" class="col-md-2">�tablissement
											:</label> <input id="formEtabl${loop.index}" name="formation"
											value="${ecole.etablissement}" maxlength="15"
											class="col-md-2"> <label
											id="labelFormDebut${loop.index}" for="formDebut${loop.index}"
											class="col-md-2">D�but :</label> <input
											id="formDebut${loop.index}" name="formation"
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
											for="formRegion${loop.index}" class="col-md-2">R�gion
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
				<div class="row col-md-12">
					<h3>Loisirs</h3>
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
			</form>
		</div>
	</div>
</body>
</html>
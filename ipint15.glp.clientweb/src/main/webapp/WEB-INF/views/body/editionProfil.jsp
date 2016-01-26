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
        	 //var id = document.getElementById('idEtu').value;
        	 //window.location = "/ipint15.glp.clientweb/profil/"+id;
        	 alert ("Modifications enregistr�es");
         },
         error: function (result) {
        	 window.location = "/ipint15.glp.clientweb/error";
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
 	//alert(tmp);
 	var res = { mail : mail.value,maListe : tmp } ; 	
 	$.ajax({
         type: "POST",
         url: url,
         data: res,
         success: function (result) {
//         	 var id = document.getElementById('idEtu').value;
//         	 window.location.replace("/ipint15.glp.clientweb/profil/"+id);
        	 alert ("Modifications enregistr�es");
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
 		//alert($("#"+selects[i].id+" option:selected").val());
 		//tmp += noeuds[i].value+"|"+selects[i].text()+"%";
 		
 		//TODO
 		
 		
 		
 		tmp += noeuds[i].value+"|"+$("#"+selects[i].id+" option:selected").val()+"%";
 	}
 	var res = { mail : mail.value,maListe : tmp } ; 	
 	$.ajax({
         type: "POST",
         url: url,
         data: res,
         success: function (result) {
//         	 var id = document.getElementById('idEtu').value;
//         	 window.location.replace("/ipint15.glp.clientweb/profil/"+id);
        	 alert ("Modifications enregistr�es");
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
	
	var res = { idEtu : idEtu.value, posteActu : posteActu.value, villeActu : villeActu.value, nomEntreprise : nomEntreprise.value, mail : mail.value, numTelephone : numTelephone.value, facebook : facebook.value, twitter : twitter.value, viadeo : viadeo.value, linkedin : linkedin.value} ;
	$.ajax({
        type: "POST",
        url: "saveProfile",
        data: res,
        success: function (result) {
       	 alert ("Modifications enregistr�es");
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

	function AddExpPro(lesExpPro, expPro, deleteExpPro){
		var taille = document.getElementsByName("lesExpPro").length;
		if (taille == 0){
			//
			var label = document.createElement("label");
			label.setAttribute("class","col-md-1");
			label.id = "labelExpProPoste0";
			label.htmlFor = "expProPoste0";
			label.innerHTML = "Poste : ";
			document.getElementById("lesExpPro").appendChild(label);
			
			var input = document.createElement("input");
			input.setAttribute("class","col-md-2");
			input.type = "text";
			input.id="expProPoste0";
			input.name="expPro";
			document.getElementById("lesExpPro").appendChild(input);
			//
			label = document.createElement("label");
			label.setAttribute("class","col-md-2");
			label.id = "labelExpProEntreprise0";
			label.htmlFor = "expProEntreprise0";
			label.innerHTML = "Nom de l'Entreprise : ";
			document.getElementById("lesExpPro").appendChild(label);
			
			input = document.createElement("input");
			input.setAttribute("class","col-md-2");
			input.type = "text";
			input.id="expProEntreprise0";
			input.name="expPro";
			document.getElementById("lesExpPro").appendChild(input);
			//
			label = document.createElement("label");
			label.setAttribute("class","col-md-1");
			label.id = "labelExpProDebut0";
			label.htmlFor = "expProDebut0";
			label.innerHTML = "D�but : ";
			document.getElementById("lesExpPro").appendChild(label);
			
			input = document.createElement("input");
			input.setAttribute("class","col-md-1");
			input.type = "text";
			input.id="labelExpProDebut0";
			input.name="expPro";
			document.getElementById("lesExpPro").appendChild(input);
			//
			label = document.createElement("label");
			label.setAttribute("class","col-md-1");
			label.id = "expProDuree0";
			label.htmlFor = "expProDuree0";
			label.innerHTML = "Dur�e : ";
			document.getElementById("lesExpPro").appendChild(label);
			
			input = document.createElement("input");
			input.setAttribute("class","col-md-1");
			input.type = "text";
			input.id="expProDuree0";
			input.name="expPro";
			document.getElementById("lesExpPro").appendChild(input);
			//
			
			var adresse = document.createElement("a");
			var link = document.createTextNode("");
			adresse.setAttribute("class", "btn btn-primary col-md-1 glyphicon glyphicon-minus-sign");
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
			label.setAttribute("class","col-md-1");
			label.id = "labelExpProPoste"+nvId;
			label.htmlFor = "expProPoste"+nvId;
			label.innerHTML = "Poste : ";
			document.getElementById("lesExpPro").appendChild(label);
			
			var input = document.createElement("input");
			input.setAttribute("class","col-md-1");
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
			var link = document.createTextNode("");
			adresse.setAttribute("class", "btn btn-primary col-md-1 glyphicon glyphicon-minus-sign");
			adresse.setAttribute("id","deleteExpPro"+nvId);
			adresse.setAttribute("name","deleteExpPro");
			adresse.setAttribute("onClick","suppressionChampEtBoutonExpPro("+nvId+", deleteExpPro"+nvId+"); saveExpPro");
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
		var r = confirm("Voulez-vous r�ellement supprimer ce champ ?");
		if(r == true){
			champ.parentNode.removeChild(champ);
			bouton.parentNode.removeChild(bouton);
		}
		
	}
	
	function suppressionChampEtBoutonCompetence(champ, bouton, label, select) {
		var r = confirm("Voulez-vous r�ellement supprimer ce champ ?");
		if(r == true){
			champ.parentNode.removeChild(champ);
			bouton.parentNode.removeChild(bouton);
			label.parentNode.removeChild(label);
			select.parentNode.removeChild(select);
		}
		
	}
	
	function suppressionChampEtBoutonExpPro(index, bouton){
		var r = confirm("Voulez-vous r�ellement supprimer ce champ ?");
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
	
	window.onbeforeunload = function(){
		  return 'Les modifications non enregistr�es seront perdues';
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
						<h2 id="UserName">${etudiant.prenom} ${etudiant.nom}</h2>
						<div class="col-md-6">
							<input type="hidden" id="idEtu" name="idEtu" value="${profil.id}" />
							<label class="col-md-4">Poste actuel :</label> <input type="text"
								id="posteActu" name="posteActu" value="${profil.posteActu}"
								class="col-md-8" /> <label class="col-md-4">Ville
								Actuelle:</label> <input type="text" id="villeActu" name="villeActu"
								value="${profil.villeActu}" class="col-md-8" /> <label
								class="col-md-4">Nom de l'entreprise :</label> <input
								type="text" id="nomEntreprise" name="nomEntreprise"
								value="${profil.nomEntreprise}" class="col-md-8" /> <label
								class="col-md-4">Mail :</label> <input type="mail" id="mail"
								name="mail" value="${profil.email}" disabled="disabled"
								class="col-md-8" /> <label class="col-md-4">Num�ro de
								t�l�phone : </label><input type="tel" id="numTelephone"
								name="numTelephone" value="${profil.numTelephone}"
								class="col-md-8" />
						</div>
						<div class="col-md-6">
							<label class="col-md-4">Facebook : </label><input type="text"
								id="facebook" name="facebook" value="${profil.facebook}"
								class="col-md-8" /> <label class="col-md-4">Twitter : </label><input
								type="text" id="twitter" name="twitter"
								value="${profil.twitter}" class="col-md-8" /> <label
								class="col-md-4">Viadeo : </label><input type="text" id="viadeo"
								name="viadeo" value="${profil.viadeo}" class="col-md-8" /> <label
								class="col-md-4">Linkedin : </label><input type="text"
								id="linkedin" name="linkedin" value="${profil.linkedin}"
								class="col-md-8" />
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
					<h2>Exp�riences Professionnelles</h2>
					<div class="row">
						<a class="btn btn-primary glyphicon glyphicon-plus-sign"
							onClick="AddExpPro('lesExpPro', 'expPro', 'deleteExpPro');"></a><a
							class="btn btn-primary glyphicon glyphicon-floppy-disk"
							onClick="saveExpPro();"> Enregistrer</a>
					</div>
					<div class="lesExpPro" id="lesExpPro">
						<c:forEach items="${profil.profil.mesExperiences}"
							var="experience" varStatus="loop">
							<label id="labelExpProPoste${loop.index}"
								for="expProPoste${loop.index}" class="col-md-1">Poste :</label>
							<input id="expProPoste${loop.index}" name="expPro"
								value="${experience.libelle}" class="col-md-2">
							<label id="labelExpProEntreprise${loop.index}"
								for="expProEntreprise${loop.index}" class="col-md-2">Nom
								de l'Entreprise :</label>
							<input id="expProEntreprise${loop.index}" name="expPro"
								value="${experience.entreprise}" class="col-md-2">
							<label id="labelExpProDebut${loop.index}"
								for="expProDebut${loop.index}" class="col-md-1">D�but :</label>
							<input id="expProDebut${loop.index}" name="expPro"
								value="${experience.anneeDebut}" class="col-md-1">
							<label id="labelExpProDuree${loop.index}"
								for="expProDuree${loop.index}" class="col-md-1">Dur�e :</label>
							<input id="expProDuree${loop.index}" name="expPro"
								value="${experience.duree}" class="col-md-1">
							<a id="deleteExpPro${loop.index}" name="deleteExpPro"
								class="btn btn-primary col-md-1 glyphicon glyphicon-minus-sign"
								onClick='suppressionChampEtBoutonExpPro(${loop.index},
												deleteExpPro${loop.index}); saveExpPro();'></a>
							</br>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="row col-md-12">
				<div class="well well-lg">
					<h2>Comp�tences</h2>
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
									value="${competence.libelle}" class="col-md-9" />
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
					<h2>Formation</h2>
					<div id="lesFormations" class="lesFormations">
						<a class="btn btn-primary glyphicon glyphicon-plus-sign"
							onClick="AddChamp('lesFormations', 'formation', 'deleteFormation','saveFormation()');"></a><a
							class="btn btn-primary glyphicon glyphicon-floppy-disk"
							onClick="save('lesFormations', 'saveFormation');">
							Enregistrer</a>
						<c:forEach items="${profil.profil.mesEcoles}" var="formation"
							varStatus="loop">
							<input id="formation${loop.index}" name="formation"
								value="${formation.libelle}" class="col-md-11" />
							<a id="deleteFormation${loop.index}" name="deleteFormation"
								class="btn btn-primary col-md-1 glyphicon glyphicon-minus-sign"
								onClick="suppressionChampEtBouton(formation${loop.index}, deleteFormation${loop.index}); save('lesFormations', 'saveFormation');"></a>
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
									value="${loisir.libelle}" class="col-md-11" />
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
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
		var taille = document.getElementsByName("ExpPro").length;
		
		var div = document.createElement("div");
		div.setAttribute("class","well well-lg");
		div.id = "MonExperience"+taille;
		div.setAttribute("name","ExpPro");
		document.getElementById("Experiences").appendChild(div);
		
		var legend = document.createElement("legend");
		legend.innerHTML = "Experience ";
		div.appendChild(legend);
		var boutonSupression = document.createElement("a");
		boutonSupression.setAttribute("class","btn btn-default glyphicon glyphicon-minus-sign");
		boutonSupression.setAttribute("onClick","suprrimerExp("+taille+");")
		legend.appendChild(boutonSupression);
		
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
		row3.appendChild(divPays);

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
		divLabelPays.appendChild(labelPays);
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
		labelDebut.innerHTML = "Date début";
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
	
	function suprrimerExp(id){
		alertify.confirm('Voulez-vous réellement supprimer ce champ ?', function (e) {
			if (e) {
				document.getElementById("MonExperience"+id).remove();
			} else {
				
			}
		});			
		
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
	
	function SoumettreFormulaire(){  
				document.forms["modif_form"].submit();
	 }
	
	window.onload = function(){
		var elem = document.getElementById('attentes');
		elem.focus();
		elem.selectionStart = elem.value.length;
	}
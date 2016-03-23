function saveFormation() {
	var name = 'lesFormations';
	var url = 'saveFormation';
	var tmp = "";
	var mail = document.getElementById('mail');
	var maClass = document.getElementById(name);
	var noeuds = maClass.getElementsByTagName('input');
	for (i = 0; i < noeuds.length;) {
		tmp += noeuds[i++].value + "|";
		tmp += noeuds[i++].value + "|";
		tmp += noeuds[i++].value + "|";
		tmp += noeuds[i++].value + "|";
		tmp += noeuds[i++].value + "|";
		tmp += noeuds[i++].value + "|";
		tmp += noeuds[i++].value + "%";
	}
	var res = {
		mail : mail.value,
		maListe : tmp
	};
	$.ajax({
		type : "POST",
		url : url,
		data : res,
		success : function(result) {
			alertify.success("Les modifications ont bien été enregistré");
		},
		error : function(result) {
			alertify.error("Les modifications n'ont pas pu être enregistré");
		}
	});
}
function save(name, url) {
	var mail = document.getElementById('mail');
	var tmp = "";
	var maClass = document.getElementById(name);
	var noeuds = maClass.getElementsByTagName('input');
	for (i = 0; i < noeuds.length; i++) {
		tmp += noeuds[i].value + "%";
	}
	var res = {
		mail : mail.value,
		maListe : tmp
	};
	$.ajax({
		type : "POST",
		url : url,
		data : res,
		success : function(result) {
			alertify.success("Les modifications ont bien été enregistré");
		},
		error : function(result) {
			alertify.error("Les modifications n'ont pas pu être enregistré");
		}
	});
}
function saveCompetence(name, url) {
	var mail = document.getElementById('mail');
	var tmp = "";
	var maClass = document.getElementById(name);
	var noeuds = maClass.getElementsByTagName('input');
	var selects = maClass.getElementsByTagName('select');
	for (i = 0; i < noeuds.length; i++) {
		tmp += noeuds[i].value + "|"
				+ $("#" + selects[i].id + " option:selected").val() + "%";
	}
	var res = {
		mail : mail.value,
		maListe : tmp
	};
	$.ajax({
		type : "POST",
		url : url,
		data : res,
		success : function(result) {
			alertify.success("Les modifications ont bien été enregistré");
		},
		error : function(result) {
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
	input.id = champId + nvId;
	input.name = champId;
	input.setAttribute("maxlength", "20");
	document.getElementById(divId).appendChild(input);

	if (divId === "lesCompetences") {
		input.setAttribute("class", "col-md-9");
		var label = document.createElement("label");
		label.setAttribute("class", "col-md-1");
		label.appendChild(document.createTextNode("Niveau :"));
		label.id = "competenceSelectLabel" + nvId;
		label.name = "competenceSelectLabel";

		var select = document.createElement("select");
		select.setAttribute("class", "col-md-1");
		select.id = "competenceSelect" + nvId
		select.name = "competenceSelect";
		for (i = 1; i < 6; i++) {
			var option = document.createElement("option");
			option.setAttribute("value", i);
			option.appendChild(document.createTextNode(i));
			select.appendChild(option);
		}

		var adresse = document.createElement("a");
		var link = document.createTextNode("");
		adresse.setAttribute("class",
				"btn btn-primary col-md-1 glyphicon glyphicon-minus-sign");
		adresse.setAttribute("id", boutonId + "" + nvId);
		adresse.setAttribute("name", boutonId);
		adresse.setAttribute("onClick", "suppressionChampEtBoutonCompetence("
				+ champId + "" + nvId + "," + boutonId + "" + nvId
				+ ", competenceSelectLabel" + nvId + ", competenceSelect"
				+ nvId + ");");
		adresse.appendChild(link);

		document.getElementById(divId).appendChild(input);
		document.getElementById(divId).appendChild(label);
		document.getElementById(divId).appendChild(select);
		document.getElementById(divId).appendChild(adresse);
		document.getElementById(divId)
				.appendChild(document.createElement("br"));
	} else {
		input.setAttribute("class", "col-md-11");
		var adresse = document.createElement("a");
		var link = document.createTextNode("");
		adresse.setAttribute("class",
				"btn btn-primary col-md-1 glyphicon glyphicon-minus-sign");
		adresse.setAttribute("id", boutonId + "" + nvId);
		adresse.setAttribute("name", boutonId);
		adresse.setAttribute("onClick", "suppressionChampEtBouton(" + champId
				+ "" + nvId + "," + boutonId + "" + nvId + ");");
		adresse.appendChild(link);
		document.getElementById(divId).appendChild(input);
		document.getElementById(divId).appendChild(adresse);
		document.getElementById(divId)
				.appendChild(document.createElement("br"));
	}

}
function createInput(type, id, name, eltDiv, colsize) {
	var input = document.createElement("input");
	input.setAttribute("maxlength", "15");
	input.type = type;
	input.id = id;
	input.name = name;
	input.setAttribute("class", colsize);
	eltDiv.appendChild(input);
}
function createLabel(id, htmlFor, innerHtml, idElements, eltDiv, colsize) {
	var label = document.createElement("label");
	label.id = id;
	label.htmlFor = htmlFor;
	label.innerHTML = innerHtml;
	label.setAttribute("class", colsize);
	eltDiv.appendChild(label);
}
function createRow(eltDiv) {
	var row = document.createElement("div");
	row.setAttribute("class", "row");
	eltDiv.appendChild(row);
	return row;
}

function AddExpPro() {
	var taille = document.getElementsByName("ExpPro").length;

	var div = document.createElement("div");
	div.setAttribute("class", "well well-lg");
	div.id = "MonExperience" + taille;
	div.setAttribute("name", "ExpPro");
	document.getElementById("Experiences").appendChild(div);

	var legend = document.createElement("legend");
	legend.innerHTML = "Experience ";
	div.appendChild(legend);
	var boutonSupression = document.createElement("a");
	boutonSupression.setAttribute("class",
			"btn btn-default glyphicon glyphicon-minus-sign");
	boutonSupression.setAttribute("onClick", "suprrimerExp(" + taille + ");")
	legend.appendChild(boutonSupression);

	var div2 = document.createElement("div");
	div2.setAttribute("class", "form-group");
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
	divPoste.setAttribute("class", "col-sm-6");
	row1.appendChild(divPoste);
	var divEntreprise = document.createElement("div");
	divEntreprise.setAttribute("class", "col-sm-6");
	row1.appendChild(divEntreprise);

	//Poste
	var divLabelPoste = document.createElement("div");
	divLabelPoste.setAttribute("class", "col-sm-2");
	divPoste.appendChild(divLabelPoste);
	var divInputPoste = document.createElement("div");
	divInputPoste.setAttribute("class", "col-sm-10");
	divPoste.appendChild(divInputPoste);
	var labelPoste = document.createElement("label");
	labelPoste.htmlFor = "poste";
	labelPoste.innerHTML = "Poste";
	divLabelPoste.appendChild(labelPoste);
	var inputPoste = document.createElement("input");
	inputPoste.id = "expProPoste" + taille;
	inputPoste.name = "poste";
	inputPoste.setAttribute("class", "form-control");
	divInputPoste.appendChild(inputPoste);

	//Entreprise
	var divLabelEntreprise = document.createElement("div");
	divLabelEntreprise.setAttribute("class", "col-sm-2");
	divEntreprise.appendChild(divLabelEntreprise);
	var divInputEntreprise = document.createElement("div");
	divInputEntreprise.setAttribute("class", "col-sm-10");
	divEntreprise.appendChild(divInputEntreprise);
	var labelEntreprise = document.createElement("label");
	labelEntreprise.htmlFor = "entreprise";
	labelEntreprise.innerHTML = "Entreprise";
	divLabelEntreprise.appendChild(labelEntreprise);
	var inputEntreprise = document.createElement("input");
	inputEntreprise.id = "expProEntreprise" + taille;
	inputEntreprise.name = "entreprise";
	inputEntreprise.setAttribute("class", "form-control");
	divInputEntreprise.appendChild(inputEntreprise);

	//Ligne 2
	var divVille = document.createElement("div");
	divVille.setAttribute("class", "col-sm-6");
	row2.appendChild(divVille);
	var divRegion = document.createElement("div");
	divRegion.setAttribute("class", "col-sm-6");
	row2.appendChild(divRegion);

	//Ville
	var divLabelVille = document.createElement("div");
	divLabelVille.setAttribute("class", "col-sm-2");
	divVille.appendChild(divLabelVille);
	var divInputVille = document.createElement("div");
	divInputVille.setAttribute("class", "col-sm-10");
	divVille.appendChild(divInputVille);
	var labelVille = document.createElement("label");
	labelVille.htmlFor = "ville";
	labelVille.innerHTML = "Ville";
	divLabelVille.appendChild(labelVille);
	var inputVille = document.createElement("input");
	inputVille.id = "expProVille" + taille;
	inputVille.name = "ville";
	inputVille.setAttribute("class", "form-control");
	divInputVille.appendChild(inputVille);

	//Region
	var divLabelRegion = document.createElement("div");
	divLabelRegion.setAttribute("class", "col-sm-2");
	divRegion.appendChild(divLabelRegion);
	var divInputRegion = document.createElement("div");
	divInputRegion.setAttribute("class", "col-sm-10");
	divRegion.appendChild(divInputRegion);
	var labelRegion = document.createElement("label");
	labelRegion.htmlFor = "region";
	labelRegion.innerHTML = "Region";
	divLabelRegion.appendChild(labelRegion);
	var inputRegion = document.createElement("input");
	inputRegion.id = "expProRegion" + taille;
	inputRegion.name = "region";
	inputRegion.setAttribute("class", "form-control");
	divInputRegion.appendChild(inputRegion);

	//Ligne 3
	var divPays = document.createElement("div");
	divPays.setAttribute("class", "col-sm-6");
	row3.appendChild(divPays);

	//Pays
	var divLabelPays = document.createElement("div");
	divLabelPays.setAttribute("class", "col-sm-2");
	divPays.appendChild(divLabelPays);
	var divInputPays = document.createElement("div");
	divInputPays.setAttribute("class", "col-sm-10");
	divPays.appendChild(divInputPays);
	var labelPays = document.createElement("label");
	labelPays.htmlFor = "pays";
	labelPays.innerHTML = "Pays";
	divLabelPays.appendChild(labelPays);

	//Create array of options to be added
	var array = ["France","Royaume-Unis","Belgique"];
	//Create and append select list
	var selectList = document.createElement("select");
	selectList.id = "expProPays"+taille;
	selectList.name="pays";
	selectList.setAttribute("class", "form-control");
	divInputPays.appendChild(selectList);
	//Create and append the options
	for (var i = 0; i < array.length; i++) {
	    var option = document.createElement("option");
	    option.value = array[i];
	    option.text = array[i];
	    selectList.appendChild(option);
	}
	
	//Ligne 4
	var divDebut = document.createElement("div");
	divDebut.setAttribute("class", "col-sm-6");
	row4.appendChild(divDebut);
	var divFin = document.createElement("div");
	divFin.setAttribute("class", "col-sm-6");
	row4.appendChild(divFin);

	//Debut
	var divLabelDebut = document.createElement("div");
	divLabelDebut.setAttribute("class", "col-sm-2");
	divDebut.appendChild(divLabelDebut);
	var divInputDebut = document.createElement("div");
	divInputDebut.setAttribute("class", "col-sm-10");
	divDebut.appendChild(divInputDebut);
	var labelDebut = document.createElement("label");
	labelDebut.htmlFor = "debut";
	labelDebut.innerHTML = "Date d&eacute;but";
	divLabelDebut.appendChild(labelDebut);
	var inputDebut = document.createElement("input");
	inputDebut.id = "expProDebut" + taille;
	inputDebut.name = "debut";
	inputDebut.setAttribute("class", "form-control");
	divInputDebut.appendChild(inputDebut);

	//Fin
	var divLabelFin = document.createElement("div");
	divLabelFin.setAttribute("class", "col-sm-2");
	divFin.appendChild(divLabelFin);
	var divInputFin = document.createElement("div");
	divInputFin.setAttribute("class", "col-sm-10");
	divFin.appendChild(divInputFin);
	var labelFin = document.createElement("label");
	labelFin.htmlFor = "fin";
	labelFin.innerHTML = "Date fin";
	divLabelFin.appendChild(labelFin);
	var inputFin = document.createElement("input");
	inputFin.id = "expProFin" + taille;
	inputFin.name = "fin";
	inputFin.setAttribute("class", "form-control");
	divInputFin.appendChild(inputFin);

	//Ligne 5 Description
	var divLabelDescription = document.createElement("div");
	divLabelDescription.setAttribute("class", "col-sm-1");
	row5.appendChild(divLabelDescription);
	var divTextareaDescription = document.createElement("div");
	divTextareaDescription.setAttribute("class", "col-sm-11");
	row5.appendChild(divTextareaDescription);
	var labelDescription = document.createElement("label");
	labelDescription.htmlFor = "description";
	labelDescription.innerHTML = "Description";
	divLabelDescription.appendChild(labelDescription);
	var textareaDescription = document.createElement("textarea");
	textareaDescription.id = "expProDescription" + taille;
	textareaDescription.name = "description";
	textareaDescription.setAttribute("class", "form-control");
	divTextareaDescription.appendChild(textareaDescription);

}

function AddFormation() {
	var taille = document.getElementsByName("Formation").length;

	var div = document.createElement("div");
	div.setAttribute("class", "well well-lg");
	div.id = "MaFormation" + taille;
	div.setAttribute("name", "Formation");
	document.getElementById("Formations").appendChild(div);

	var legend = document.createElement("legend");
	legend.innerHTML = "Formation";
	div.appendChild(legend);
	var boutonSupression = document.createElement("a");
	boutonSupression.setAttribute("class",
			"btn btn-default glyphicon glyphicon-minus-sign");
	boutonSupression.setAttribute("onClick", "suprrimerFormation(" + taille + ");")
	legend.appendChild(boutonSupression);

	var div2 = document.createElement("div");
	div2.setAttribute("class", "form-group");
	div.appendChild(div2);

	var row1 = createRow(div2);
	div2.appendChild(document.createElement("br"));
	var row2 = createRow(div2);
	div2.appendChild(document.createElement("br"));
	var row3 = createRow(div2);
	var row4 = createRow(div2);
	

	// Ligne 1
	var divNom = document.createElement("div");
	divNom.setAttribute("class", "col-sm-6");
	row1.appendChild(divNom);
	var divEtablissement = document.createElement("div");
	divEtablissement.setAttribute("class", "col-sm-6");
	row1.appendChild(divEtablissement);

	//Nom
	var divLabelNom = document.createElement("div");
	divLabelNom.setAttribute("class", "col-sm-2");
	divNom.appendChild(divLabelNom);
	var divInputNom = document.createElement("div");
	divInputNom.setAttribute("class", "col-sm-10");
	divNom.appendChild(divInputNom);
	var labelNom = document.createElement("label");
	labelNom.htmlFor = "intitule";
	labelNom.innerHTML = "Intitul&eacute;";
	divLabelNom.appendChild(labelNom);
	var inputNom = document.createElement("input");
	inputNom.id = "formationIntitule" + taille;
	inputNom.name = "intitule";
	inputNom.setAttribute("class", "form-control");
	divInputNom.appendChild(inputNom);

	//Etablissement
	var divLabelEtablissement = document.createElement("div");
	divLabelEtablissement.setAttribute("class", "col-sm-3");
	divEtablissement.appendChild(divLabelEtablissement);
	var divInputEtablissement = document.createElement("div");
	divInputEtablissement.setAttribute("class", "col-sm-9");
	divEtablissement.appendChild(divInputEtablissement);
	var labelEtablissement = document.createElement("label");
	labelEtablissement.htmlFor = "etablissement";
	labelEtablissement.innerHTML = "Etablissement";
	divLabelEtablissement.appendChild(labelEtablissement);
	var inputEtablissement = document.createElement("input");
	inputEtablissement.id = "formationEtablissement" + taille;
	inputEtablissement.name = "etablissement";
	inputEtablissement.setAttribute("class", "form-control");
	divInputEtablissement.appendChild(inputEtablissement);
	
	//Ligne 2
	var divDebut = document.createElement("div");
	divDebut.setAttribute("class", "col-sm-6");
	row2.appendChild(divDebut);
	var divFin = document.createElement("div");
	divFin.setAttribute("class", "col-sm-6");
	row2.appendChild(divFin);

	//Debut
	var divLabelDebut = document.createElement("div");
	divLabelDebut.setAttribute("class", "col-sm-2");
	divDebut.appendChild(divLabelDebut);
	var divInputDebut = document.createElement("div");
	divInputDebut.setAttribute("class", "col-sm-10");
	divDebut.appendChild(divInputDebut);
	var labelDebut = document.createElement("label");
	labelDebut.htmlFor = "dateDebut";
	labelDebut.innerHTML = "Date d&eacute;but";
	divLabelDebut.appendChild(labelDebut);
	var inputDebut = document.createElement("input");
	inputDebut.id = "formationDebut" + taille;
	inputDebut.name = "debutForm";
	inputDebut.setAttribute("class", "form-control");
	divInputDebut.appendChild(inputDebut);

	//Fin
	var divLabelFin = document.createElement("div");
	divLabelFin.setAttribute("class", "col-sm-3");
	divFin.appendChild(divLabelFin);
	var divInputFin = document.createElement("div");
	divInputFin.setAttribute("class", "col-sm-9");
	divFin.appendChild(divInputFin);
	var labelFin = document.createElement("label");
	labelFin.htmlFor = "dateFin";
	labelFin.innerHTML = "Date fin";
	divLabelFin.appendChild(labelFin);
	var inputFin = document.createElement("input");
	inputFin.id = "formationFin" + taille;
	inputFin.name = "finForm";
	inputFin.setAttribute("class", "form-control");
	divInputFin.appendChild(inputFin);

	//Ligne 3
	var divVille = document.createElement("div");
	divVille.setAttribute("class", "col-sm-6");
	row3.appendChild(divVille);
	var divRegion = document.createElement("div");
	divRegion.setAttribute("class", "col-sm-6");
	row3.appendChild(divRegion);

	//Ville
	var divLabelVille = document.createElement("div");
	divLabelVille.setAttribute("class", "col-sm-2");
	divVille.appendChild(divLabelVille);
	var divInputVille = document.createElement("div");
	divInputVille.setAttribute("class", "col-sm-10");
	divVille.appendChild(divInputVille);
	var labelVille = document.createElement("label");
	labelVille.htmlFor = "ville";
	labelVille.innerHTML = "Ville";
	divLabelVille.appendChild(labelVille);
	var inputVille = document.createElement("input");
	inputVille.id = "formationVille$" + taille;
	inputVille.name = "villeForm";
	inputVille.setAttribute("class", "form-control");
	divInputVille.appendChild(inputVille);

	//Region
	var divLabelRegion = document.createElement("div");
	divLabelRegion.setAttribute("class", "col-sm-3");
	divRegion.appendChild(divLabelRegion);
	var divInputRegion = document.createElement("div");
	divInputRegion.setAttribute("class", "col-sm-9");
	divRegion.appendChild(divInputRegion);
	var labelRegion = document.createElement("label");
	labelRegion.htmlFor = "region";
	labelRegion.innerHTML = "Region";
	divLabelRegion.appendChild(labelRegion);
	var inputRegion = document.createElement("input");
	inputRegion.id = "formationRegion" + taille;
	inputRegion.name = "regionForm";
	inputRegion.setAttribute("class", "form-control");
	divInputRegion.appendChild(inputRegion);

	//Ligne 4
	var divPays = document.createElement("div");
	divPays.setAttribute("class", "col-sm-6");
	row4.appendChild(divPays);

	//Pays
	var divLabelPays = document.createElement("div");
	divLabelPays.setAttribute("class", "col-sm-2");
	divPays.appendChild(divLabelPays);
	var divInputPays = document.createElement("div");
	divInputPays.setAttribute("class", "col-sm-10");
	divPays.appendChild(divInputPays);
	var labelPays = document.createElement("label");
	labelPays.htmlFor = "pays";
	labelPays.innerHTML = "Pays";
	divLabelPays.appendChild(labelPays);
	var inputPays = document.createElement("input");
	inputPays.id = "formationPays" + taille;
	inputPays.name = "paysForm";
	inputPays.setAttribute("class", "form-control");
	divInputPays.appendChild(inputPays);

}

function AddLoisir(){
	var taille = document.getElementsByName("Loisir").length;
	
	var div = document.createElement("div");
	div.setAttribute("class", "col-sm-3 well well-lg col-sm-offset-1");
	div.id = "MonLoisir" + taille;
	div.setAttribute("name", "Loisir");
	document.getElementById("Loisirs").appendChild(div);
	
	var div2 = document.createElement("div");
	div2.setAttribute("class", "col-sm-10");
	div.appendChild(div2);
	
	var div3 = document.createElement("div");
	div3.setAttribute("class", "col-sm-2");
	div.appendChild(div3);
	
	var inputNom = document.createElement("input");
	inputNom.id = "loisirNom" + taille;
	inputNom.name = "loisirNom";
	inputNom.setAttribute("class", "form-control");
	inputNom.setAttribute("required", "true");
	div2.appendChild(inputNom);
	
	var boutonSupression = document.createElement("a");
	boutonSupression.setAttribute("class",
			"btn btn-default glyphicon glyphicon-minus-sign");
	boutonSupression.setAttribute("onClick", "suprrimerLoisir(" + taille + ");")
	div3.appendChild(boutonSupression);
	
}

function suprrimerExp(id) {
	alertify.confirm('Voulez-vous r&eacute;ellement supprimer ce champ ?',
			function(e) {
				if (e) {
					document.getElementById("MonExperience" + id).remove();
				} else {

				}
			});

}

function suprrimerComp(id) {
	alertify.confirm('Voulez-vous r&eacute;ellement supprimer ce champ ?',
			function(e) {
				if (e) {
					document.getElementById("MaComp" + id).remove();
				} else {

				}
			});

}

function suprrimerLoisir(id) {
	alertify.confirm('Voulez-vous r&eacute;ellement supprimer ce champ ?',
			function(e) {
				if (e) {
					document.getElementById("MonLoisir" + id).remove();
				} else {

				}
			});

}

function suprrimerFormation(id) {
	alertify.confirm('Voulez-vous r&eacute;ellement supprimer ce champ ?',
			function(e) {
				if (e) {
					document.getElementById("MaFormation" + id).remove();
				} else {

				}
			});

}

/**
 * Suppression d'un textarea et du bouton de suppression
 *
 *@param le textarea
 *@param bouton le bouton de suppression
 */
function suppressionChampEtBouton(champ, bouton) {
	alertify.confirm('Voulez-vous réellement supprimer ce champ ?',
			function(e) {
				if (e) {
					champ.parentNode.removeChild(champ);
					bouton.parentNode.removeChild(bouton);
				} else {

				}
			});
}

function suppressionChampEtBoutonCompetence(champ, bouton, label, select) {
	alertify.confirm('Voulez-vous réellement supprimer ce champ ?',
			function(e) {
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
function suppressionInputlab(idinput, idlabel) {
	var element = document.getElementById(idlabel);
	element.parentNode.removeChild(element);
	element = document.getElementById(idinput);
	element.parentNode.removeChild(element);
}

/**
 *Fonction permettant de supprimer une formation
 */
function suppressionChampEtBoutonFormation(index, bouton) {
	alertify.confirm('Voulez-vous réellement supprimer ce champ ?',
			function(e) {
				if (e) {
					suppressionInputlab("formIntit" + index, "labelFormIntit"
							+ index);
					suppressionInputlab("formEtabl" + index, "labelFormEtabl"
							+ index);
					suppressionInputlab("formDebut" + index, "labelFormDebut"
							+ index);
					suppressionInputlab("formFin" + index, "labelFormFin"
							+ index);
					suppressionInputlab("formVille" + index, "labelFormVille"
							+ index);
					suppressionInputlab("formRegion" + index, "labelFormRegion"
							+ index);
					suppressionInputlab("formPays" + index, "labelFormPays"
							+ index);
					bouton.parentNode.removeChild(bouton);
				} else {

				}
			});
}

function AddComp() {
	var taille = document.getElementsByName("Comp").length;

	var div = document.createElement("div");
	div.setAttribute("class", "col-sm-5 well well-lg col-sm-offset-1");
	div.id = "MaComp" + taille;
	div.setAttribute("name", "Comp");
	document.getElementById("Competences").appendChild(div);
	
	var div2 = document.createElement("div");
	div2.setAttribute("class", "col-sm-6");
	div.appendChild(div2);
	
	var div3 = document.createElement("div");
	div3.setAttribute("class", "col-sm-2");
	div.appendChild(div3);
	
	var div4 = document.createElement("div");
	div4.setAttribute("class", "col-sm-3");
	div.appendChild(div4);
	
	var div5 = document.createElement("div");
	div5.setAttribute("class", "col-sm-1");
	div.appendChild(div5);
	
	var inputNom = document.createElement("input");
	inputNom.id = "compNom" + taille;
	inputNom.name = "compNom";
	inputNom.setAttribute("class", "form-control");
	div2.appendChild(inputNom);
	
	var labelNote = document.createElement("label");
	labelNote.htmlFor = "note";
	labelNote.innerHTML = "Note";
	div3.appendChild(labelNote);

	//Create array of options to be added
	var array = ["1","2","3","4","5"];
	//Create and append select list
	var selectList = document.createElement("select");
	selectList.id = "compNote"+taille;
	selectList.name="compNote";
	selectList.setAttribute("class", "form-control");
	div4.appendChild(selectList);
	//Create and append the options
	for (var i = 0; i < array.length; i++) {
	    var option = document.createElement("option");
	    option.value = array[i];
	    option.text = array[i];
	    selectList.appendChild(option);
	}
	
	var boutonSupression = document.createElement("a");
	boutonSupression.setAttribute("class",
			"btn btn-default glyphicon glyphicon-minus-sign");
	boutonSupression.setAttribute("onClick", "suprrimerComp(" + taille + ");")
	div5.appendChild(boutonSupression);
	
}

function changementVilleExp(id) {
	var x = document.getElementById("expProPays"+id).value;
	var region = document.getElementById("expProRegion"+id);
	if (x=="France"){
		region.style.visibility = "visible";
	}
	else {
		region.style.visibility = "hidden";
	}
}

window.onload = function() {
	var elem = document.getElementById('attentes');
	elem.focus();
	elem.selectionStart = elem.value.length;
	var fieldPoste = document.getElementById("posteActu");
	var fieldEntreprise = document.getElementById("nomEntreprise");
	var fieldVille = document.getElementById("villeActu");
	
	var statut = document.querySelector('input[name = "statut"]:checked').value;
	
	if(statut=="En emploi"){
		fieldPoste.disabled=false;
		fieldEntreprise.disabled=false;
		fieldVille.disabled=false;
	}
	else if (statut=="Sans emploi"){
		fieldPoste.value = "";
		fieldEntreprise.value="";
		fieldVille.value="";
		fieldPoste.disabled=true;
		fieldEntreprise.disabled=true;
		fieldVille.disabled=true;
	}
	else if(statut=="Freelance"){
		fieldPoste.value = "";
		fieldEntreprise.value="";
		fieldVille.value="";
		fieldPoste.disabled=true;
		fieldEntreprise.disabled=true;
		fieldVille.disabled=true;
	}
}

function changeStatut(myRadio){
	
	var fieldPoste = document.getElementById("posteActu");
	var fieldEntreprise = document.getElementById("nomEntreprise");
	var fieldVille = document.getElementById("villeActu");
	
	if(myRadio.value=="En emploi"){
		fieldPoste.disabled=false;
		fieldEntreprise.disabled=false;
		fieldVille.disabled=false;
	}
	else if (myRadio.value=="Sans emploi"){
		fieldPoste.value = "";
		fieldEntreprise.value="";
		fieldVille.value="";
		fieldPoste.disabled=true;
		fieldEntreprise.disabled=true;
		fieldVille.disabled=true;
	}
	else if(myRadio.value=="Freelance"){
		fieldPoste.value = "";
		fieldEntreprise.value="";
		fieldVille.value="";
		fieldPoste.disabled=true;
		fieldEntreprise.disabled=true;
		fieldVille.disabled=true;
	}
}
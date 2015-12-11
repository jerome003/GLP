<%@ page session="true" %>
<html><head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
        <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="http://pingendo.github.io/pingendo-bootstrap/themes/default/bootstrap.css" rel="stylesheet" type="text/css">
    </head><body>
        <div class="section">
            <div class="container">
                <div class="row">
                    <div class="col-md-4">
                        <h1 class="col-md-2 text-success">Profil</h1>
                        <p>
                            <br>
                            <br>
                        </p>
                    </div>
                    
                    <div class="col-md-4">
                        <a class="btn btn-primary">Editer profil<br></a>
                    </div>
                </div>
            </div>
        </div>
        <div class="section">
            <div class="container">
                <div class="row">
                    <div class="col-md-6">
                        <h2 id="UserName">${etudiant.prenom} ${etudiant.nom} ${etudiant.civilite}</h2>
                        <div class="col-md-12">
                            <ul class="list-group">
                                <li>
                                    <p>Poste actuel :
                                        <strong>jardinier</strong>
                                    </p>
                                </li>
                                <li>
                                    <p>Lieu:
                                        <strong>yolo</strong>
                                    </p>
                                </li>
                                <li>
                                    <p>Nom de l'entreprise :
                                        <strong></strong>
                                    </p>
                                </li>
                                <li>
                                    <p>Mail :
                                        <strong>${etudiant.email}</strong>
                                    </p>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <h2 class="text-success">Expériences Professionnelles
                            <br>
                        </h2>
                        <div class="col-md-12">
                            <ul class="list-group">
                                <li class="list-group-item"></li>
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
                        <h2 class="text-success">Compétences</h2>
                        <div class="col-md-12">
                            <ul class="list-group">
                                <li class="list-group-item"></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <h2 class="text-success">Formation</h2>
                        <div class="col-md-12">
                            <ul class="list-group">
                                <li class="list-group-item"></li>
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
                        <div class="col-md-12">
                            <ul class="list-group">
                                <li class="list-group-item"></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    

</body></html>
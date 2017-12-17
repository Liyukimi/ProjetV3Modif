<%@include file="header.jsp" %>
<%@include file="nav.jsp" %>
<!------------------------------------------------ DEBUIT DE LA PAGE PRINCIPALE ------------------------------------------------>
        <div id="page-wrapper">
            <div class="container-fluid">
				<!------------------------------ CONTENU HEADING ------------------------------>
                <!-- Page Heading -->
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                            <i class="fa fa fa-edit"></i> Création d'un nouveau client
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i>  <a href="index.jsp">Tableau de bord</a>
                            </li>
                            <li class="active">
                                <i class="fa fa fa-edit"></i> Création client
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /Page heading -->
				<!------------------------------ FIN CONTENU HEADING ------------------------------>
				<!------------------------------ CONTENU PRINCIPAL ------------------------------>             
				<div class="row">                    
					<div class="col-lg-12">
						<%@include file="etatRequete.jsp" %>
						
						<form class="form" method="post" action="CreationClient">
							<fieldset>
								<legend>Création d'un nouveau client</legend>
								<div class="form-group">
									<label  for="nom">Nom :</label>
									<input type="text" class="form-control" required="required" name="nom" maxlength="20">
								</div>
								<div class="form-group">
									<label  for="prenom">Prénom :</label>
									<input type="text" class="form-control" required="required" name="prenom" maxlength="20">
								</div>
								<div class="form-group">
									<label for="adresse">Adressee :</label>
									<input type="text" class="form-control" name="adresse" maxlength="60">
								</div>
								<div class="form-group">
									<label  for="codePostal">Code Postal :</label>
									<input type="number" class="form-control" name="codePostal" minlenght="5" maxlength="5">
								</div>
								<div class="form-group">
									<label  for="ville">Ville :</label>
									<input type="text" class="form-control" name="ville" maxlength="20">
								</div>						
								<div class="form-group">
									<label  for="telephone">Téléphone :</label>
									<input type="tel" class="form-control" name="telephone" minlenght="10" maxlength="10">
								</div>
								<div class="form-group">
									<label  for="mail">email :</label>
									<input type="email" class="form-control" name="mail" maxlength="40">
								</div>
									<input type="hidden" name="login" value=${conseiller.login}>
								<button type="submit" class="btn btn-primary">Valider</button>
							</fieldset>
						</form>
					</div>
				</div>
                <!-- /.row -->

				<!------------------------------ FIN CONTENU PRINCIPAL ------------------------------>
            </div>
            <!-- /.container-fluid -->
		</div>
<!------------------------------------------------ FIN DE LA PAGE PRINCIPALE ------------------------------------------------>
<%@include file="footer.jsp" %>        

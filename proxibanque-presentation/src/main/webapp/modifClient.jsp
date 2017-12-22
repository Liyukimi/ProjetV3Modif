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
					<i class="fa fa fa-edit"></i> Modfier les information d'un client
				</h1>
				<ol class="breadcrumb">
					<li>
						<i class="fa fa-dashboard"></i>  <a href="index.jsp">Tableau de bord</a>
					</li>
					<li class="active">
						<i class="fa fa fa-edit"></i> Modifier client
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

				<form class="form" method="post" action="ModifClient">
					<fieldset>
						<legend>Modifier les informations du client</legend>
						<div class="form-group">
							<label  for="nom">Nom :</label>
							<input type="text" class="form-control" placeholder="${client.nom}" name="nom"  title="Maximum 20 caract�res non accentu�s" pattern="[aA-Z][a-zA-Z-]+">
						</div>
						<div class="form-group">
							<label  for="prenom">Pr�nom :</label>
							<input type="text" class="form-control" placeholder="${client.prenom}" name="prenom" title="Maximum 20 caract�res non accentu�s" pattern="[aA-Z][a-zA-Z-]+">
						</div>
						<div class="form-group">
							<label for="adresse">Adressee :</label>
							<input type="text" class="form-control" placeholder="${client.adresse}" name="adresse" title="Num�ro de rue et adresse. Maximum 60 caract�res non accentu�s" pattern="[0-9]+\s[a-zA-Z-\s]+" maxlength="60">
						</div>
						<div class="form-group">
							<label  for="codePostal">Code Postal :</label>
							<input type="text" class="form-control" placeholder="${client.codePostal}" title="5 chiffres" pattern="[0-9]{5}" name="codePostal">
						</div>
						<div class="form-group">
							<label  for="ville">Ville :</label>
							<input type="text" class="form-control" placeholder="${client.ville}" pattern="[a-zA-Z][a-zA-Z-\s]+" title="Maximum 20 caract�res" name="ville" maxlength="20">
						</div>
						<div class="form-group">
							<label  for="mail">email :</label>
							<input id="email" type="text" class="form-control" placeholder="${client.mail}" title="Exemple : toto@toto.fr" name="mail" maxlength="40">
						</div>
						<input type="hidden" name="idClientSelect" value=${client.idClient}>
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

<%@include file="../includes/header.jsp" %>
<%@include file="../includes/nav.jsp" %>
<!------------------------------------------------ DEBUIT DE LA PAGE PRINCIPALE ------------------------------------------------>
<div id="page-wrapper">
	<div class="container-fluid">
		<!------------------------------ CONTENU HEADING ------------------------------>
		<!-- Page Heading -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">
					<i class="fa fa fa-edit"></i> Cr�ation d'un nouveau client
				</h1>
				<ol class="breadcrumb">
					<li>
						<i class="fa fa-dashboard"></i>  <a href="index.jsp">Tableau de bord</a>
					</li>
					<li class="active">
						<i class="fa fa fa-edit"></i> Cr�ation client
					</li>
				</ol>
			</div>
		</div>
		<!-- /Page heading -->
		<!------------------------------ FIN CONTENU HEADING ------------------------------>
		<!------------------------------ CONTENU PRINCIPAL ------------------------------>             
		<div class="row">                    
			<div class="col-lg-12">
				<%@include file="../includes/etatRequete.jsp" %>

				<form class="form" method="post" action="CreationClient">
					<fieldset>
						<legend>Cr�ation d'un nouveau client</legend>
						<div class="form-group">
							<label  for="nom">Nom :</label>
							<input type="text" class="form-control" required="required" name="nom" title="Maximum 20 caract�res non accentu�s" pattern="[aA-Z][a-zA-Z-]+">
						</div>
						<div class="form-group">
							<label  for="prenom">Pr�nom :</label>
							<input type="text" class="form-control" required="required" name="prenom" title="Maximum 20 caract�res non accentu�s" pattern="[a-zA-Z][a-zA-Z-]+">
						</div>
						<div class="form-group">
							<label for="adresse">Adressee :</label>
							<input type="text" class="form-control" name="adresse" name="adresse" title="Num�ro de rue et adresse. Maximum 60 caract�res non accentu�s" pattern="[0-9]+\s[a-zA-Z-\s]+" maxlength="60">
						</div>
						<div class="form-group">
							<label  for="codePostal">Code Postal :</label>
							<input type="text" class="form-control" name="codePostal" title="5 chiffres" pattern="[0-9]{5}" name="codePostal">
						</div>
						<div class="form-group">
							<label  for="ville">Ville :</label>
							<input type="text" class="form-control" name="ville" pattern="[a-zA-Z][a-zA-Z-\s]+" title="Maximum 20 caract�res non accentu�s" name="ville" maxlength="20">
						</div>						
						<div class="form-group">
							<label  for="telephone">T�l�phone :</label>
							<!-- pattern = expression r�guli�re, un 0 en 1er et 9 chiffres -->
							<input type="tel" class="form-control" name="telephone" pattern="^0[1-9][0-9]{8}$"> 
						</div>
						<div class="form-group">
							<label  for="mail">email :</label>
							<input type="email" class="form-control" name="mail" title="Exemple : toto@toto.fr" name="mail" maxlength="40">
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
<%@include file="../includes/footer.jsp" %>        

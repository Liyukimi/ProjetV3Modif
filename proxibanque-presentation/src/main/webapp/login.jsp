<%@include file="head.jsp" %>
<body>
<!------------------------------------------------ DEBUIT DE LA PAGE PRINCIPALE ------------------------------------------------>
	<div class="login">
		<c:if test = "${!empty error}">
			<div class="alert alert-danger">
				<h3><strong>Erreur !</strong></h3>
				<h5>${error}</h5>				
			</div>
		</c:if>
			
		<div class="panel panel-primary">
			<div class="panel-heading">Connexion</div>
			<div class="panel-body">
				<form class="form" method="post" action="ConnectionConseiller">
					<div class="form-group">
						<label for="login">Identifiant</label>
						<input type="text" class="form-control" required="required" name="login" placeholder="Entrez votre identifiant">
					</div>
					<div class="form-group">
						<label for="mdp">Mot de passe</label>
						<input type="password" class="form-control" required="required" name="password" placeholder="Entrez votre mot de passe">
					</div>
					<input type=submit class="btn btn-primary btn-block" value="Se connecter"></input>
				</form>
				<div class="text-center">
				</div>			
			</div>
		</div>
	</div>
<!------------------------------------------------ FIN DE LA PAGE PRINCIPALE ------------------------------------------------>
	<!-- jQuery -->
    <script src="assets/js/jquery.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="assets/js/bootstrap.min.js"></script>

</body>
</html>
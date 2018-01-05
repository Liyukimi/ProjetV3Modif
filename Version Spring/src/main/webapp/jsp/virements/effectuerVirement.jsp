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
					<i class="fa fa-credit-card"></i> Virement compte à compte
				</h1>
				<ol class="breadcrumb">
					<li>
						<i class="fa fa-dashboard"></i>  <a href="index.jsp">Tableau de bord</a>
					</li>
					<li class="active">
						<i class="fa fa-credit-card"></i> Virement compte à compte
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
				<c:if test = "${!empty success}">
					<legend>Comptes modifiés</legend>
					<table class="table-striped">
						<thead>
							<tr>
								<th>Numéro de compte</th>
								<th>Client</th>
								<th>Ancien Solde</th>
								<th>Nouveau Solde</th>
							</tr>
						</thead> 
						<tbody>
							<tr>
								<td>${compteDebiteur.numeroCompte}</td>
								<td>${compteDebiteur.client.prenom} ${compteDebiteur.client.nom}</td>
								<td>${compteDebiteur.solde + montant}</td>									
								<td>${compteDebiteur.solde}</td>
							</tr>
							<tr>
								<td>${compteCrediteur.numeroCompte}</td>
								<td>${compteCrediteur.client.prenom} ${compteCrediteur.client.nom}</td>
								<td>${compteCrediteur.solde - montant}</td>									
								<td>${compteCrediteur.solde}</td>
							</tr>
						</tbody>				
					</table>						
					<br></br>	
				</c:if>
				<h5>Vous pouvez effectuer un virement depuis l'un des comptes de vos clients vers l'ensemble des comptes de la banque.</h5>
				<fieldset>
					<legend>Effectuer un virement</legend>					
					<form class="form" method="post" action="EffectuerVirement">
						<div class="form-group">
							<label  for="numCompteDebit">Compte à débiter :</label>
							<c:if test = "${!empty ListeComptesClients}">
								<select class="form-control" required="required" id="numCompteDebit" name="numCompteDebit">
									<c:forEach items="${ListeComptesClients}" var="compteDebit" >
										<option  name="numCompteDebit" required="required" value="${compteDebit.numeroCompte}">
											n°${compteDebit.numeroCompte} - Client : ${compteDebit.client.prenom} ${compteDebit.client.nom}	
										</option>
									</c:forEach>
								</select>
							</c:if>
							<c:if test = "${empty ListeComptesClients}">
								Vous n'avez pas de client ayant un compte !
								<select class="form-control" required="required"></select>
							</c:if>									    
						</div>

						<div class="form-group">
							<label  for="numCompteCredit">Compte à créditer :</label>
							<c:if test = "${!empty ListeComptesTotale}">
								<select class="form-control" required="required" id="numCompteCredit" name="numCompteCredit">
									<c:forEach items="${ListeComptesTotale}" var="compteCredit" >
										<option  name="numCompteCredit" required="required" value="${compteCredit.numeroCompte}">
											n°${compteCredit.numeroCompte} - 
											Client : ${compteCredit.client.prenom} ${compteCredit.client.nom}				
										</option>
									</c:forEach>
								</select>
							</c:if>
							<c:if test = "${empty ListeComptesTotale}">
								Il n'y a pas de compte dans votre banque !
								<select class="form-control" required="required"></select>
							</c:if>		
						</div>
						<div class="form-group">
							<label  for="amout">Montant de la transaction :</label>
							<input type="number" min="0" data-bind="value:replyNumber" class="form-control" required="required" name="montant">
						</div>
						<input type="hidden" name="login" value=${conseiller.login}>
						<input type=submit class="btn btn-primary" value="Effectuer le virement"></input>
					</form>
				</fieldset>
				<br></br>
			</div>
		</div>
		<!-- /.row -->

		<!------------------------------ FIN CONTENU PRINCIPAL ------------------------------>
	</div>
	<!-- /.container-fluid -->
</div>
<!------------------------------------------------ FIN DE LA PAGE PRINCIPALE ------------------------------------------------>
<%@include file="../includes/footer.jsp" %>  
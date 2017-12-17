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
						<%@include file="etatRequete.jsp" %>
						<c:if test = "${!empty success}">

							<legend>Comptes modifiés</legend>
							<table class="table-striped">
								<thead>
									<tr>
										<th>Numéro de compte</th>
										<th>Ancien Solde</th>
										<th>Nouveau Solde</th>
									</tr>
								</thead> 
								<tbody>
									<tr>
										<td>${compteDebiteur.numeroCompte}</td>
										<td>${compteDebiteur.solde + montant}</td>									
										<td>${compteDebiteur.solde}</td>
									</tr>
									<tr>
										<td>${compteCrediteur.numeroCompte}</td>
										<td>${compteCrediteur.solde - montant}</td>									
										<td>${compteCrediteur.solde}</td>
									</tr>
								</tbody>				
							</table>						
							<br></br>	
						</c:if>
						<fieldset>
							<legend>Effectuer un virement</legend>

							<form class="form" method="post" action="Virement">
									<div class="form-group">
											<label  for="numCompteDebit">Compte à débiter :</label>
											<input type="number" min="0" data-bind="value:replyNumber" class="form-control" required="required" name="numCompteDebit">
									</div>
									<div class="form-group">
											<label  for="numCompteCredit">Compte à créditer :</label>
											<input type="number" min="0" data-bind="value:replyNumber" class="form-control" required="required" name="numCompteCredit">
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
<%@include file="footer.jsp" %>  
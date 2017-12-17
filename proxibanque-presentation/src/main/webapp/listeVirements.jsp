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
						
                        <fieldset>
                                <legend>Choisissez le compte dont vous souhaitez afficher la liste des virements</legend>

                                <form class="form" method="post" action="ListeVirements">
                                        <div class="form-group">
                                                <label  for="numCompte">Compte  :</label>
                                                <input type="number" min="0" data-bind="value:replyNumber" class="form-control" required="required" name="numCompte">
                                        </div>
                                        <input type="hidden" name="login" value=${conseiller.login}>
                                        <input type=submit class="btn btn-primary" value="Afficher les virements"></input>
                                </form>
                        </fieldset>
                        </br></br>               

                        <c:if test = "${!empty ListeDebits}">		
                            <legend>Ensemble des opérations débitant le compte ${numCompte}</legend>
                            <table class="table-striped">                            
                                <thead><tr>
                                                <th>Date du virement</th>
                                                <th>Compte Créditeur</th>
                                                <th>Montant</th>
                                        </tr>
                                </thead> 
                                <tbody>
                                    <c:forEach items="${ListeDebits}" var="debit">
                                        <tr>
                                            <td>${debit.dateVirement}</td>
                                            <td>${debit.compteCrediteur.numeroCompte}</td>
                                            <td>${debit.montant}</td>
                                        </tr>    
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:if>
                        <c:if test = "${!empty ListeCredits}">		
                            <legend>Ensemble des opérations créditant le compte ${numCompte}</legend>
                            <table class="table-striped">                            
                                <thead>
                                        <tr>
                                            <th>Date du virement</th>
                                            <th>Compte débiteur</th>
                                            <th>Montant</th>
                                        </tr>
                                </thead> 
                                <tbody>
                                    <c:forEach items="${ListeCredits}" var="credit">
                                        <tr>
                                            <td>${credit.dateVirement}</td>
                                            <td>${credit.compteDebiteur.numeroCompte}</td>
                                            <td>${credit.montant}</td>
                                        </tr>    
                                    </c:forEach>
                                </tbody>
                            </table>						
                        </c:if>
                    </div>
                </div>
                <!------------------------------ FIN CONTENU PRINCIPAL ------------------------------>
            </div>
            <!-- /.container-fluid -->
		</div>
<!------------------------------------------------ FIN DE LA PAGE PRINCIPALE ------------------------------------------------>
<%@include file="footer.jsp" %>  
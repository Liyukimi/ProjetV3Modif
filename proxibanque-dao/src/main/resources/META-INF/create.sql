create sequence seq_client start with 1 increment by 1;
create sequence seq_compte start with 1000 increment by 1;
create sequence seq_virement start with 1 increment by 1;

create table Conseiller(login varchar(20) constraint pk_login primary key, password varchar(20), nom varchar(20), prenom varchar(20));
create table Client(id_client number(10,0) constraint pk_id_client primary key, nom varchar(20), prenom varchar(20), adresse varchar(60), code_postal varchar(5), ville varchar(20), telephone varchar(20), mail varchar(40), login_conseiller varchar(20) constraint fk_login_conseiller references Conseiller(login));
create table Compte(numero_compte number(10,0) constraint pk_numeroCompte primary key , solde number(10,2),  date_ouverture date, type_compte varchar(20), decouvert number, taux number, id_client number constraint fk_id_client references Client(id_client));
create table Virement(id_virement number(10,0) constraint pk_id_virement primary key, num_compte_debiteur number(10,0) constraint fk_num_compte_deb references Compte(numero_compte), num_compte_crediteur number(10,0) constraint fk_num_compte_cred references Compte(numero_compte), montant number(10,0), date_virement Date, login_conseiller varchar(20) constraint fk_login_conseiller_virement references Conseiller(login));


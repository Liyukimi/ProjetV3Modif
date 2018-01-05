# Description de l'application ProxibanqueV3
ProxiBanqueV3 est une application visant à exécuter des services bancaires pour les conseillers de l'agence Proxibanque à travers une interface web.

Les fonctionnalités de l'application sont :

* gérer l'authentification des conseillers grâce à des identifiants et des mdp
* visualiser la liste des clients de chaque conseiller
* effectuer des virements pour les clients
* garder une trace de chacun des virements effectué par un conseiller

Pour chaque client le conseiller peut :

* visualiser les informations du clients
* éditer des informations du client
* lister les comptes du client

L'application prend en compte un systeme de gestion des erreurs afin d'indiquer aux utilsiateurs
les dysfocntionnements.

## Prérequis pour l'utilisation
* Installation Apache Tomcat v8.5
* Installation OracleXE

## Documentation :
La documentation est disponnible dans le dossier du projet

	1. Veuillez accéder au dossier 'javadoc/'
	2. Double cliquez sur 'index.html'.

## Execution de l'application :
Avant de commencer, veuillez vous assurer de remplir les préquis d'utilisation listés ci-dessus.

Pour lancer l'application, veuillez suivre indications suivantes :
#### A. Lancer la base de données
	1. Lancez le programme Run SQL command line (nécessite l'installation d'OracleXE)
	2. Connectez vous au system en saisissant la commande suivante : 'connect system/' suivi de votre mot de passe
	   (configuré lors de l'installation d'OracleXE)
	3. Creez un nouvel utilsiateur en tapant les commandes suivantes
		 create user proxibanque identified by proxibanque;
		 grant connect, resource to proxibanque;	 

#### B. Lancer le serveur Apache Tomcat
	1. Vérifiez que le programme n'utilise pas le port 8080 (utilisé par défaut par Oracle et Tomcat)
		i. Dans le dossier d'intallation du programme Apache Tomcat 8.5.23,
		   -> accédez au dossier \conf
		ii. Ouvrez le fichier 'server.xml' avec un éditeur de texte 
		    (type bloc-note sous Windows, text-edit sous OS X)
		iii. Vérifiez que le port 8080 n'apparait pas.
		     S'il apparait remplacez toutes les occurences par un autre port
		     (nous recommandons le port 8081)
		iv. Sauvegardez et quittez
	2. Charger l'application dans le serveur Apache Tomcat
		i. Dans le dossier d'intallation du programme Apache Tomcat 8.5.23,
		   -> accédez au dossier \webapps
		ii. Copiez-collez dans le dossier \webapps le fichier 'ProxibanqueV3.war'
	   	    (le fichier 'ProxibanqueV3.war' se trouve à la racine du dossier du projet)
	3. Lancez le serveur
		i. Dans le dossier d'intallation du programme Apache Tomcat 8.5.23,
		   -> accédez au dossier \bin
		ii. Double-cliquez sur le fichier 'startup.bat' -> le serveur et lancez	 

#### C. Accéder à la page d'acceuil de l'application ProxibanqueV3
	1. Lancez votre navigateur et accédez à l'url : http://localhost:8081/ProxibanqueV3/
	   Veillez à remplacer 8081 par le port indiqué à l'étape B.1.iii précédente
	2. Pour vous identifier saisissez un login/mot de passe valides pour un conseiller dont la liste est la suivante 
	   (attention le login est sensible à la casse)
	   	i. Conseiller1 / password1
		ii. Conseiller2 / password2
		iii. Conseiller3 / password3

	L'ensemble des données de connexion se trouve dans la table 'Conseiller' de la base de données créée à l'étape A)

#### D. Importer le projet Maven
	1. Rendez vous sur le repository Github à l'adresse suivante : https://github.com/Liyukimi/ProxibanqueV3
	2. Choisissez "Clone or Download" et téléchargez l'archive zip
	3. Décompressez l'archive et importez le projet via votre IDE habituel 
	   (import -> existing Maven project dans la plupart)
	4. Lancez un invite de commande et faites un 'mvn install clean package'

Pour obtenir de l'aide, nous vous invitons à contacter notre support technique : google.com ;)

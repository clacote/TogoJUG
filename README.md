Démo pour le TogoJUG
====================

Ce projet est une démonstration pour le TogoJUG (session du 23/08/2011)
Il met en oeuvre :

* [Maven](http://maven.apache.org), qui permet de compiler et construire le projet, en exécutant notamment les tests unitaire automatiquement.
* [JUnit](http://junit.sourceforge.net/) et [Mockito](http://mockito.org) pour les tests unitaires avec des mock-objects.
* Quelques plugins d'analyse de code pour la génération de rapports via Maven.

Il se contente de montrer le codage de tests unitaires (cf. UserServiceImplTest.java) avec des objets bouchonnés.
La classe testée est UserServiceImpl, un exemple de service fonctionnelle utilisant une dépendance (UserRepository) lui fournissant l'accès à la base de données. Aucune implémentation n'existe pour cette interface!

Utilisation
-----------

* Installer [Maven](http://maven.apache.org/download.html)
* Installer [GIT](http://git-scm.com/download)
* Cloner le repository : _git clone git@github.com:Fluor/TogoJUG.git_
* Compilation Maven : _mvn clean install_ (les tests sont automatiquement exécutés)
* Génération des rapports d'analyse : _mvn clean site_
* Consultation des rapports d'analyse : afficher _TogoJUG\target\site\index.html_

Questions, support, félicitations, insultes
-----------

Cyril Lacôte, Twitter [@clacote](http://www.twitter.com/clacote)
Démo pour le TogoJUG
====================

Ce projet est une démonstration pour le TogoJUG.

Il met en oeuvre :

* Maven.
* JUnit et Mockito pour les tests unitaires avec des mock-objects.
* Quelques plugins d'analyse de code pour la génération de rapports

Utilisation
-----------

* Installer Maven et GIT sur un ordinateur avec une connexion Internet active.
* Cloner le repository : git clone git@github.com:Fluor/TogoJUG.git
* Compilation Maven : mvn clean install (les tests sont automatiquement exécutés)
* Génération des rapports d'analyse : mvn clean site
* Consultation des rapports d'analyse : TogoJUG\target\site\index.html
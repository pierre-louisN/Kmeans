# Kmeans
Project Kmeans scala from scratch 
Attention, le programme requiert l'installation de 2 librairies pour l'affichage graphique:
jcommon-1.0.23.jar 
jfreechart-1.0.19.jar
Le gros inconvénient est qu'il faut les "installer" à la main de la manière suivante :
- aller là où scala est installé, souvent C:\Program Files (x86)\scala\
- se rendre dans le dossier "lib"
- y coller les 2 librairies (souvent il faut le faire en tant qu'admin) 


On peut ensuite ouvrir le fichier et extraire les classes et lancer le programme:
- lancer le terminal 
- vérifier que java est bien installé (sinon voir : https://www.java.com/fr/download/) 
- vérifier que scala est bien installé (sinon voir : https://www.scala-lang.org/download/)
- on compile les fichiers avec "scalac *.scala" 
- et on lance le programme avec "scala test"
Attention : le nom du fichier est "iris.data" et il est impossible (si on utilise ce fichier de créer 
plus de 150 lignes) 

Pour tester l'algo avec un autre fichier, il faut le créer en respectant certaines conditions :
(en résumé le fichier doit avoir le même format que "iris.data")
- pas d'espace entre les coordonnées, ni de saut entre les lignes 
- les représentations graphiques sont faites à partir des 2 premières coordonées 
- la classe se trouve sur la dernière colonne et est un "String" 
- les coordonnées sont soit des "Int","Float","Double" et rien d'autre
- il y a 4 coordonnées par ligne 
(en résumé le fichier doit avoir le même format que "iris.data")
Si ces conditions sont respectés, l'algo devrait marcher correctement 
Pour rentrer le chemin du fichier il est parfois nécessaire de rajouter "src/" devant ou autre extention 
cela dépend de l'IDE 
(Vérifier la capture du rendu final pour confirmer que le rendu obtenu est bien le bon)

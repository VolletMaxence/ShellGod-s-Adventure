# ShellGod-s-Adventure

Vollet, Maxence
Golomer, Félix-Lloyd

Shell God's Aventure est un jeu dans lequel Shell God (Petit bonhomme en bleu) doit passer de salle en salle en atteignant les escaliers.
Cependant, sur son chemin, deux types d'ennemis, les Wanderers (type chelou à perruques) se déplacent aléatoirement et bloquent la route.
Les followers (mecs à lunettes de soleil armés), eux, vous suivent et vous bloquent. Indice, ils existent un moyen de leur passer devant en se servant d'un des côté de la salle.

Les salles sont générés aléatoirement et le nombre d'ennemu aussi. Heureusement, les followers ont moins de chance d'apparaître que les wanderers.
Trois Design Patterns ont été utilisés ici, un Singleton, un Factory et un Decorator.

Le singleton, qui permet de créer une classe dont une seule instance peut être créée dans toute l'application Java, garantissant ainsi qu'il n'y aura qu'une seule copie de cette instance et que cette instance sera accessible de manière globale a été utilisée dans la création du Menu de jeu, afin que celui-ci ne s'ouvre pas plusieurs fois lorsqu'on redémarre le jeu.

Le Factory, qui fournit une méthode centralisée pour créer des objets d'un certain type, sans exposer les détails de création au code client et qui permet d'encapsuler la logique de création d'objets dans une classe distincte, appelée Factory, plutôt que de la répartir dans tout le code client, ce qui favorise la séparation des responsabilités et la modularité du code, nous a servi à créer les différentes cases dans lesquelles le personnage ainsi que les ennemis se déplacent.

Le Decorator, qui lui permet d'ajouter des fonctionnalités supplémentaires à des objets existants de manière dynamique, sans modifier leur structure ni affecter les objets clients qui les utilisent, nous a permis d'ajouter un escalier (la porte de sortie et l'objectif de chaque niveau) dans les salles.

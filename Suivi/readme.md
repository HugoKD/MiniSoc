Binôme :
* Hugo Cadet
* Alex Aïdan

Ce fichier contient et contiendra des remarques de suivi sur votre
projet tant sur la modélisation que sur la programmation. Un nouveau
suivi est indiqué par une nouvelle section datée.

Certaines remarques demandent des actions de votre part, vous les
repérerez par une case à cocher.

- []  Action (à réaliser) 

Merci de nous indiquer que vous avez pris en compte la remarque en
cochant la case. N'hésitez pas à écrire dans ce fichier et à nous
exposer votre point de vue.

- [x] Action (réalisée)
    - RÉPONSE et éventuelles remarques de votre part, 

 ---
# Suivi du mar. 13 févr. 2024 14:07:37
Chantal Taconet

Les fichiers auxquels j'ai accès dans la branche develop n'ont pas été modifié  depuis le fork initial ... cela ne me permet pas de faire le suivi ... 

# Suivi du mar. 05 mars 2024 
Chantal Taconet


Le travail est à compléter sans trop tarder. 
Les diagrammes de séquence sont à revoir. 
Bonne continuation ! 

* [] GIT-06-Répartition-travail-sur-dépôt
    * Nous attendons que les deux membres du binôme soient à l'aise
      avec Git et nous encourageons chaque membre à faire des mises à
      jour sur le dépôt (`commit`/`push`).


## Nouveaux retours rapides : Spécification et préparation des tests de validation


### Diagrammes de cas d'utilisation
Pourquoi l'administrateur n'a pas le rôle de modérateur ? 

### Préconditions et postconditions
Manque des données en entrée 
### Tables de décision des tests de validation
à mettre à jour, lorsque vous aurez mis à jour les pré conditions 

## 3. Conception

### Diagramme de classes

- [] Envisager des compositions depuis la classe qui crée les instances 
 [] DIAGCLAS-27-Association-role-et-nom-association
    * Il est important de distinguer les noms d'association des noms
      de rôle. Le nom d'association est positionné au milieu d'un lien
      d'association. Un nom de rôle est positionné à un bout de
      l'association, il permet de donner à une classe un rôle dans
      l'association. Cela peut être intéressant lorsque plusieurs
      associations existent entre deux classes. Par exemple, si une
      classe Client possède 2 adresses, nous pouvons avoir deux
      associations nommées "habite" et "a pour adresse de facturation"
      et on pourra distinguer deux rôles dans les adresses
      "adresseDeLivraison" et "adresseDeFacturation".
      - [] Manque des noms d'associations

- [] Est-ce l'utilisateur ou le membre qui est associé avec un  message


### Diagrammes de séquence

1. Cas d'utilisation « créer un réseau social »
- [] u==true --> u != null 
* [] DIAGSEQ-16-Message-pb-cohérence-nom-participant-objet-trouvé
    * Un ou plusieurs objets cherchés et trouvés dans la séquence
      semblent utilisés par la suite dans la séquence. Si ce sont bien
      les mêmes objets trouvés qui sont utilisés, le nom de la
      référence retournée par la méthode de recherche (par exemple,
      par le message « d = chercherDocument(code) : Document ») doit
      être le nom du participant utilisé par la suite, ici « d ».
      * par exemple le réseau social s'appelle rs dans la suite du diagramme de classes 
* [] DIAGSEQ-18-Message-pb-arguments
    * Un ou plusieurs arguments d'un message ne sont pas corrects.
	  * create(nomRs) d'où vient nomRs pb 

	  
- [] 

2. Cas d'utilisation « ajouter un membre à un réseau social »



3. Cas d'utilisation « poster un message »

- [] manque les fragments optionnels, le teste des préconditions, à vérifier ... 

- [] 

### Raffinement du diagramme de classes

1. Fiche de la classe « Message »



### Diagramme de machine à états et invariant

1. Diagramme de machine à états de la classe « Message »
* [] DIAGMACHETATS-02-Compréhension-étude-de-cas
    * Une ou plusieurs diagrammes de machine à états montrent un
      erreur de compréhension de l'étude de cas.
      * Message caché ?? 
	  * Un message refusé est-il détruit ? 
* [] DIAGMACHETATS-07-Transition-pb-syntaxe-événement-condition-action
    * Une ou plusieurs transitions ne respectent pas la
      syntaxe : `événement[condition]/action`.


2. Invariant de la classe « Message »

- [] A faire 

## 4. Préparation des tests unitaires

1. Table de décision des tests unitaires de la méthode Message::constructeur

- [] A faire 

2. Table de décision des tests unitaires de la méthode Message::modérer

- [] A faire 
 
---
# Suivi du mar. 19 mars 2024 16:08:38
Chantal Taconet

Travail en cours ... mais en retard pour ce qui est évalué dans le suivi 3, il m'est difficile de vous faire des retours, vous est-il possible d'avancer prochainement ? 

- [] GEN-03-Images-PlantUML-non-mises-à-jour
  - vos diagrammes de séquences sont semble-til maj mais ils ne sont pas insérés dans votre readme.md, je ne peux pas vous faire de retour ...
- [] GEN-01-Indiquer-remarque-prise-en-compte
  - Veuillez indiquer lorsque vous avez pris en compte les remarques en mettant un « x » dans la case à cocher, comme indiqué en début de fichier
  
## Programmation du logiciel

### Utilisation des outils de programmation

1. Module Maven et tests avec JUnit
- maven OK 
- Junit il semble que vous n'ayez pas encore ajouté de tests 

### Programmation de la solution

#### Classes du diagramme de classes avec leurs attributs

- Un bon début

#### Méthodes des cas d'utilisation de base

- pas encore programmés

#### Cohérence entre le code et le modèle

- A vérifier quand les premiers cas d'utilisation seront réalisés


## Programmation et exécution des tests

- pas encore programmés

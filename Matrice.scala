import java.util.HashSet

import scala.io.Source
import scala.math.Ordering.Double.TotalOrdering // sert à calculer le minimum d'un tableau de double

class Matrice(private val fileName:String){
  private val données :Array[Données] = Array.ofDim[Données](150)
  private val classement:Array[String] = Array.ofDim[String](150)
  private var lines: Iterator[String] = Source.fromFile(fileName).getLines

  def lirefichier(): Unit = {
      while (lines.hasNext) {
        for (i <- 0 until données.length) // pour chaque ligne, on aurait pu mettre le  nombre de ligne du fichier en parametre
        {
          val ligne = (lines.next().split(",")) // on "explose" la chaîne de caractères et on récupère les données dans un tableau
          //val data:Données = new Données()
          this.données(i) = new Données() // on instancie la case courante de données
          for(j <- 0 until données(i).gettaille()) // on parcourt toute la ligne pour récupérer les valeurs
          {
            j match {
              case 4 => this.classement(i) = ligne(j) // on récupère la classe pour la derniere colonne
              case _ => this.données(i).setValeur((ligne(j)).toDouble,j) // sinon on récupère la valeure
            }
          }
        }
      }
      println("fin du fichier")
  }

  def getDonnée(indice:Int): Données = {return this.données(indice)}

  def tri(clusters:Array[Cluster]): Unit ={
    for(i<-0 until this.données.length){ // pour chaque donneé
      val dist:Array[Double] = Array.ofDim[Double](clusters.length)
      for(y<-0 until clusters.length){ // pour chaque cluster
        dist(y) =  this.données(i).calculerDistance(clusters(y).getCentroide()) // on calcule la distance du point courant à chaque centre
      }
      this.données(i).setValeur(dist.indexOf(dist.min),4) //on trouve le numéro du centroide le plus proche => avec la distance la plus petite
    }
  }

  def majCentre(centre:Données, num:Int): Données ={
    for(i<-0 until centre.gettaille()-1){  //pour chaque coordonée
      var moy: Double = 0
      var nombre:Int = 0
      for(y<-0 until this.données.length){ // pour chaque points
        if(this.données(y).getValeur(4)==num){ // on vérifie que l'on est dans le bon cluster
          moy = moy + this.données(y).calculerDistance(i,centre) // on calcule la distance du point courant au centre
          nombre = nombre + 1 // on comptre le nombre de points du clusters
        }
      }
      println("il y a : "+nombre+" points dans le cluster n°"+num)
      moy = moy/nombre // on calcule la moyenne
      centre.setValeur(centre.getValeur(i)+moy,i) // on modifie la valeur de la coordonée du centre
    }
    return centre // on retourne le centre pour le comparer à la valeur ancienne pour la condition d'arrêt
  }

  def affichage(): Unit = {
    for(i<-0 until this.données.length){
      println(this.données(i).toString())
    }
  }

  def affClusters(): Unit ={
    for(i<-0 until this.données.length){
      println("le numéro du cluster est : "+this.données(i).getValeur(4))
    }
  }

  def getDonnéesTaille: Int ={
    return this.données.length
  }

  def getClassement(i:Int): String ={
    return this.classement(i)
  }

  def getClasses: HashSet[String] ={ // les classes peuvent être de n'importe quel type
    val doublons:HashSet[String] = new HashSet[String]()
    val Nodoublons:HashSet[String] = new HashSet[String]()

    for(i<-0 until this.classement.length){
      if (! Nodoublons.contains(this.classement(i))) { // on veut enlever les doublons
        Nodoublons.add(this.classement(i))
      }
    }
    return Nodoublons
  }
  def getDonnées: Array[Données] ={
    return this.données
  }

  def getIndice(d:Données): Int={
    var trouve:Boolean = false
    var i:Int = 0
    while(i<this.données.length-1 && trouve==false){
      if(this.données(i).equals(d)){
        trouve = true
      }
      i = i+1
    }
    return i
  }

}

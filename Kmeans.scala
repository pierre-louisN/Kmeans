import scala.util.Random
class Kmeans(private val mat:Matrice, private val k:Int, private var clusters:Array[Cluster]) {

  def initCentres(): Array[Int] ={ // choisit k centres au hasard
    val tab:Array[Int] = Array.ofDim(this.k)
    for(i<-0 until tab.length){
      var num: Int = Random.nextInt(150)
      while(tab.contains(num)){
        num = Random.nextInt(150)
      }
      tab(i) = num
    }
    return tab
  }

  def initClusters(tab:Array[Int]): Unit ={ // initialise les clusters
    this.clusters = Array.ofDim(tab.length)
    for(i<-0 until tab.length){
      this.clusters(i) = new Cluster(i,this.mat.getDonnée(tab(i)))
      this.mat.getDonnée(tab(i)).setValeur(i,4)
    }
  }

  def majCentres: Unit ={ // met à jour les centres en les plaçant le plus possible à équidistances des points du cluster
    for(i<-0 until this.clusters.length){

      val centre:Données = this.mat.majCentre(this.clusters(i).getCentroide(),this.clusters(i).getnum())
      this.clusters(i).setCentroide(centre)
    }
  }


  def kmeans(centres:Array[Int]): Matrice ={
    initClusters(centres)
    //this.affichage()
    this.mat.tri(this.clusters)
    this.mat.affClusters()
    this.majCentres
    var i:Int = 0
    while(i<this.mat.getDonnéesTaille || !(finKmeans())){ // pour chaque point (choisi arbitrairement)
     this.mat.tri(this.clusters)
     this.majCentres
      i = i+1
    }
    println("Fin du Kmeans")
    return this.mat
  }

  def affichage(): Unit ={
    for(i<-0 until clusters.length){
      println(this.clusters(i).toString)
    }
  }

  def getMatrice: Matrice ={
     return this.mat
  }

  def effectifCluster: Array[Int] ={ // donne pour chaque cluster le nombre de données
    val tab:Array[Int] = Array.ofDim[Int](this.clusters.length)
    for(i<-0 until this.clusters.length){
      for(y<-0 until this.mat.getDonnéesTaille){
        if(this.mat.getDonnée(y).getValeur(4)==this.clusters(i).getnum()){
          tab(i) = tab(i)+1
        }
      }
    }
    return tab
  }


  def tauxClasses(num:Int): Array[String]={ // calcul le taux de chaque classe pour le cluster en paramètre
    val taille:Int = this.getMatrice.getClasses.toArray().length+2
    val classes = this.getMatrice.getClasses.toArray()
    val tab:Array[Int] = Array.ofDim[Int](taille)
    val tab1:Array[String] = Array.ofDim[String](taille)
    tab1(0) = Integer.toString(num) // la premiere valeur est le numéro du cluster
    //tab1(1) =  "cluster "+num//
    tab1(1) =  this.mat.getClassement(this.mat.getIndice(this.clusters(num).getCentroide))
      for(i<-2 until taille){ //pour chaque classe
      for(y<-0 until this.getMatrice.getDonnéesTaille){ // pour chaque données
        if(this.getMatrice.getDonnée(y).getValeur(4)==num){ // si la donnée fait bien parti du cluster en paramètre
          if(this.getMatrice.getClassement(y)==classes(i-2)){ // si la données a pour classe la classe courante
            tab(i) = tab(i)+1
          }
        }
      }
      tab1(i) = Integer.toString(tab(i)) // c'est un tableau de string (object) obligatoirement
    }
    return tab1
  }

  def finKmeans(): Boolean ={ //condition d'arret (à définir ?)
    return true
  }


}

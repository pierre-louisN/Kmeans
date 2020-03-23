import scala.math.sqrt
import scala.math.pow

class Données(private val coord:Array[Double] = Array.ofDim(5)){
  // une donnée est identifié par ses coordonéées dont la dernière est le numéro du cluster auquel elle appartient

  def calculerDistance(centroide:Données):Double ={
    var dist: Double = 0
    for (i <- 0 until this.coord.length){
      dist = dist + pow(this.coord(i)-centroide.coord(i),2).toFloat
    }
    sqrt(dist)
  }

  def calculerDistance(i:Int, centroide:Données): Double ={
    //return sqrt(pow(this.coord(i)-centroide.coord(i),2))
    return this.coord(i)-centroide.coord(i)
  }

  def getValeur(i:Int):Double ={return this.coord(i)}
  // autre solution, on met en variable la matrice et on a une méthode dans matrice qui nous donne ses coordonnées à partir de son id
  def gettaille():Int = {return this.coord.length}
  def setValeur(nouvValeur:Double,i:Int)={this.coord(i) = nouvValeur}

  override def toString():String = {
    return ("longeur du sépale : " + this.coord(0) + "\nlargeur du sépale : " + this.coord(1) + "\nlongeur du pétale : " + this.coord(2) + "\nlargeur du pétale : " + this.coord(3) + "\nnuméro de cluster : "+this.coord(4))
  }
}

class Cluster(private val num:Int, private var centroide:Données) {


  def getCentroide():Données ={return this.centroide}
  def getnum():Int ={return this.num}
  def setCentroide(centre:Données): Unit = {this.centroide = centre}

  override def toString: String = {return this.centroide.toString()+" num de cluster : "+this.getnum()}
}

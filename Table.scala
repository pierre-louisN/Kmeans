import java.util.LinkedHashSet
import javax.swing.JTable
import java.util.HashSet

class Table(private val km: Kmeans, private val title:String){

  def init: JTable ={
    val data:Array[Array[Object]] = this.createData()
    val colonnes:Array[Object] = this.createColumns()
    val table = new JTable(data,colonnes)
    return table
  }

  def createData(): Array[Array[Object]] ={
    val tab:Array[Array[Object]] = Array.ofDim[Array[Object]](km.effectifCluster.length)
    for(i<-0 until km.effectifCluster.length){ // pour chaque cluster
      tab(i) = km.tauxClasses(i).asInstanceOf[Array[Object]] //on donne le nombre de points pour chaque classe pour chaque cluster
    }
    return tab
  }

  def createColumns(): Array[Object] ={
    val colonnes:HashSet[String] = km.getMatrice.getClasses
    var tree:LinkedHashSet[String] = new LinkedHashSet[String]()
    tree.add("Cluster n°")
    tree.add("Type du cluster")
    tree.addAll(colonnes)
    return tree.toArray()
    }
}

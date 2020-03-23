
import java.util.HashSet;

object test {
  def main(args: Array[String]): Unit = {
    println("Début du programme")
    val fen:Fenetre = new Fenetre()
    //demande la saisie à l'utilisateur
    fen.start
    //fen.init("src/iris.data",4)

    println("Fin du programme")
  }
}

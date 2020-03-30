import java.awt.event.KeyListener
import java.awt.{BorderLayout, Dimension, FlowLayout, GridLayout}
import java.io.FileNotFoundException
import java.util.Scanner
import javax.swing.border._
import javax.swing._


import java.io._

import javax.swing.{JButton, JFrame, JLabel, JPanel, JScrollPane, JTextArea, JTextField, SwingConstants}
import javax.swing.WindowConstants.EXIT_ON_CLOSE

class Fenetre extends App {

  def start: Unit ={
    println("Entrer le nom du fichier ou tapez espace ou Entrée pour la valeur par défaut(iris.data) :")
    val s = new Scanner(System.in)
    var fileName:String = s.nextLine() // le nom du fichier peut être src/fileName ou autre
    if (fileName.matches("")){
      fileName = "iris.data"
      println("la valeur par défaut (iris.data) a été entregistrée")
    }
    else {
      var f:File = new File(fileName)
      while(!f.exists()){
        println("Saisie invalide le fichier n'existe pas ou le nom est incorrect, réésayez :")
        fileName = s.nextLine() // le nom du fichier peut être src/fileName ou autre
        f = new File(fileName)
      }
    }
    println("Entrer le nombre de clusters désiré :")
    var k = s.nextInt()
    while (k>getLineNumber(fileName) || k==0){ // pas possible d'avoir plus de clusters que de données ni 0 clusters
      println("Saisi invalide, Entrer le nombre de clusters désiré (doit être positif et différent de 0) :")
      k = s.nextInt()
    }
    init(fileName,k)
  }

  def getLineNumber(fileName: String): Integer = { // compte le nombre de lignes du fichier càd le nombre de données, Attention!! => pas de saut de ligne dans le
    val src = io.Source.fromFile(fileName)
    try {
      src.getLines.size
    } catch {
      case error: FileNotFoundException => -1
      case error: Exception => -1
    }
  }

  def init(fileName:String,k:Int): Unit = { // on initialise la fenetre et on lance le kmeans
    val frame = new JFrame("Projet Kmeans") // crée la fenetre
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE)
    frame.setSize(new Dimension(1200, 800))
    frame.setResizable(true)
    frame.setLocationRelativeTo(null)
    frame.setLayout(new GridLayout(2, 2))
    var matrice = new Matrice(fileName)
    matrice.lirefichier() //remplit la matrice avec les données du fichier (attention, respecter les conditions de la notice)
    var km = new Kmeans(matrice, k, Array())
    val centres: Array[Int] = km.initCentres()
    val graph: Graphique = new Graphique(km.getMatrice, centres, "Kmeans Initialisation")
    //graph.setLayout(new GridLayout(5, 5))
    frame.add(graph.init())
    val nouvgraph: Graphique = new Graphique(km.kmeans(centres), centres, "Kmeans Efféctué")
    km.kmeans(centres).affichage()
    frame.add(nouvgraph.init())
    val cheese: Cheese = new Cheese(km, "Diagramme circulaire de la répartition des clusters")
    frame.add(cheese.init())
    val table:Table = new Table(km,"Tableau des taux de points par classes pour chaque cluster" )
    val panel:JPanel = new JPanel();
    val tableContainer:JScrollPane = new JScrollPane(table.init);
    panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Tableau de répartition des clusters", TitledBorder.CENTER, TitledBorder.TOP))
    panel.add(tableContainer)
    frame.add(panel);
    frame.setVisible(true)
  }
}

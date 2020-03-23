
import java.awt.Color
import java.awt.geom.Ellipse2D

import javax.swing.{JFrame, JPanel}
import org.jfree.util.ShapeUtilities
import org.jfree.chart.{ChartFactory, ChartFrame, ChartPanel, JFreeChart}
import org.jfree.chart.plot.{PlotOrientation, XYPlot}
import org.jfree.data.xy.{XYDataset, XYSeries, XYSeriesCollection}
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer



class Graphique (private val matrice: Matrice, private val centres:Array[Int], private val titre:String) extends JPanel{

  def init():ChartPanel ={
    val chart:JFreeChart = ChartFactory.createScatterPlot(
      this.titre, //chart title
      "Sépale x", //abcisse
      "Sépale y", //ordonnée
      this.createDataset(), //données
      PlotOrientation.VERTICAL,
      true, //legendes
      true, //tooltips
      false //urls
    );
    paint(chart)
    val panel:ChartPanel = new ChartPanel(chart)
    return panel
  }

  def createDataset(): XYDataset = {
    val res: XYSeriesCollection = new XYSeriesCollection()
    val series: Array[XYSeries] = Array.ofDim[XYSeries](this.centres.length) // on a k série (avec k = nbre de centres)
    val seriesc: XYSeries = new XYSeries("Centres") //la série contenant les centres
    for (i <- 0 until this.centres.length) { // pour chaque centre (on commence à 1 car 0 c'est les centres)
      series(i) = new XYSeries("Cluster n° " + i)
      for (k <- 0 until this.matrice.getDonnéesTaille) {
        if (this.centres.contains(k) ) { // si c'est un centre on le met en noir grâce à la série à part
          val x: Double = this.matrice.getDonnée(k).getValeur(0)
          val y: Double = this.matrice.getDonnée(k).getValeur(1)
          seriesc.add(x, y)
        }
        else if (this.matrice.getDonnée(k).getValeur(4) == this.matrice.getDonnée(centres(i)).getValeur(4)) { // si la données appartient au clusters courant
          val x: Double = this.matrice.getDonnée(k).getValeur(0)
          val y: Double = this.matrice.getDonnée(k).getValeur(1)
          series(i).add(x, y) // on la met dans la série courante , une série par cluster
        }
      }
      res.addSeries(series(i))
    }
      res.addSeries(seriesc)
      return res
    }

    def paint(chart: JFreeChart): Unit = {
      val plot: XYPlot = chart.getPlot().asInstanceOf[XYPlot];
      val renderer: XYLineAndShapeRenderer = plot.getRenderer().asInstanceOf[XYLineAndShapeRenderer]
      for (i <- 0 until this.centres.length) {
        renderer.setSeriesShape(i, new Ellipse2D.Double(-3, -3, 6, 6)); // les points sont tous des cercles sauf les centres
      }
      renderer.setSeriesPaint(this.centres.length, Color.black) //les centres sont en noirs
      val cross = ShapeUtilities.createDiagonalCross(3, 1)
      renderer.setSeriesShape(this.centres.length, cross)
    }
}
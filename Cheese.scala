import javax.swing.{JFrame, JPanel}
import org.jfree.chart.labels.{PieSectionLabelGenerator, StandardPieSectionLabelGenerator}
import org.jfree.chart.{ChartFactory, ChartFrame, ChartPanel, JFreeChart}
import org.jfree.chart.plot.{PiePlot, PlotOrientation, XYPlot}
import org.jfree.data.general.{DefaultPieDataset, PieDataset}

class Cheese (private val km: Kmeans, private val titre:String) extends JPanel {

  def init(): ChartPanel = {
    val chart: JFreeChart = ChartFactory.createPieChart(
      this.titre, //chart title
      this.createDataset(), //données
      true, //legendes
      true, //tooltips
      false //urls
    );
    format(chart)
    val panel:ChartPanel = new ChartPanel(chart)
    return panel
  }

  def createDataset(): PieDataset ={
    val pie:DefaultPieDataset = new DefaultPieDataset();
    for(i<-0 until this.km.effectifCluster.length){ // pour chaque cluster
      pie.setValue("Cluster "+i,this.km.effectifCluster(i)) // on définit le nombre de points de chaque cluster
    }
    return pie
  }

  def format(chart: JFreeChart): Unit ={ //le format d'une part
    //Format Label
    val labelGenerator:PieSectionLabelGenerator = new StandardPieSectionLabelGenerator(
      " {0} = {1} points");
    val plot: PiePlot = chart.getPlot().asInstanceOf[PiePlot]
    plot.setLabelGenerator(labelGenerator)
  }
}


import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.chart.plot.PlotOrientation
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer
import org.jfree.data.xy.XYDataset
import org.jfree.data.xy.XYSeries
import org.jfree.data.xy.XYSeriesCollection
import java.awt.BasicStroke
import java.awt.Color
import java.awt.Dimension
import javax.swing.JFrame

class MyKotlinPlot(title: String, listOfX: List<Double>, listOfY: List<Double>) : JFrame(title) {

    init {
        val dataset: XYDataset = createDataset(listOfX, listOfY)

        val chart: JFreeChart =
            ChartFactory.createXYLineChart(title, "X", "Y", dataset, PlotOrientation.VERTICAL, true, true, false)

        val panel = ChartPanel(chart)
        panel.preferredSize = Dimension(800, 400)

        val renderer = XYLineAndShapeRenderer()
        renderer.setSeriesPaint(0, Color.RED)
        renderer.setSeriesStroke(0, BasicStroke(4.0f));

        val plot = chart.xyPlot
        plot.renderer = renderer

        contentPane = panel
    }

    private fun createDataset(x: List<Double>, y: List<Double>): XYDataset {

        val seriesX = XYSeries("x")
        for (k in x.indices) seriesX.add(x[k], y[k])

        val dataset = XYSeriesCollection()
        dataset.addSeries(seriesX)

        return dataset
    }
}
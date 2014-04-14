package energybox;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
/**
 * @author Rihards Polis
 * Linkoping University
 */
public class ResultsFormController implements Initializable
{
    @FXML
    private LineChart<Long, Integer> chart;
    @FXML
    private AnchorPane ActionPane1;
    @FXML
    private TableView<Packet> packetTable;
    @FXML
    private TableColumn<?, ?> timeCol;
    @FXML
    private TableColumn<?, ?> lengthCol;
    @FXML
    private TableColumn<?, ?> sourceCol;
    @FXML
    private TableColumn<?, ?> destinationCol;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){}
    
    void initData(ObservableList<Packet> packetList) 
    {
        chart.getXAxis().setAutoRanging(true);
        chart.getYAxis().setAutoRanging(true);
        chart.setTitle("Packet length for every 10th packet");
        XYChart.Series<Long, Integer> series = new XYChart.Series();
        series.setName("Length");
        for (int i = 0; i < packetList.size(); i=i+10) {
            // Adds a point to the series for every 10th packet in the list.
            // The time is relative to the first packet (chart starts at 0)
            series.getData().add(new XYChart.Data(
                    packetList.get(i).getTime() - packetList.get(0).getTime(), 
                    packetList.get(i).getLength()));
        }
        chart.getData().add(series);
        
        // Populates the packet detail list
        packetTable.getItems().setAll(packetList);
        // TODO: put Property Factories in control instead of FXML
        /*
        timeCol.setCellFactory(new PropertyValueFactory<Packet, Long>("time"));
        lengthCol.setCellFactory(new PropertyValueFactory<Packet, Integer>("length"));
        sourceCol.setCellFactory(new PropertyValueFactory<Packet, String>("source"));
        destinationCol.setCellFactory(new PropertyValueFactory<Packet, String>("destination"));
        */
    }
}

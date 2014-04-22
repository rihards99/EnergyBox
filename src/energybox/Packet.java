package energybox;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class Packet
{
    private final SimpleLongProperty time;
    private final SimpleIntegerProperty length;
    private final SimpleStringProperty source;
    private final SimpleStringProperty destination;
    private final SimpleBooleanProperty uplink = new SimpleBooleanProperty(false);
    
    public Packet(long time, int length, String source, String destination)
    {
        this.time = new SimpleLongProperty(time);
        this.length = new SimpleIntegerProperty(length);
        this.source = new SimpleStringProperty(source);
        this.destination = new SimpleStringProperty(destination);
    }
    
    // GETTERS
    public long getTime() {return time.get();}
    public int getLength() {return length.get();}
    public String getSource() {return source.get();}
    public String getDestination() {return destination.get();}
    public Boolean getUplink() {return uplink.get();}
    public String getLink()
    {
        if (uplink.get()) return "Uplink";
        else return "Downlink";
    }
    
    // SETTERS
    public void setTime(long fName) {time.set(fName);}
    public void setLength(int fName) {length.set(fName);}
    public void setSource(String fName) {source.set(fName);}
    public void setDestination(String fName) {destination.set(fName);}
    public void setUplink(Boolean fName) {uplink.set(fName);}
}
package xml;

import javax.xml.bind.annotation.XmlElement;

public class Cell {
    private String data;

    @XmlElement(name = "Data")
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
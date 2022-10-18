package com.example.taskcurrencyconverter.models.xml;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement(name = "Valute")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class CourseDtoOnce implements Serializable {

    @XmlAttribute(name = "ID")
    private String valuteID;

    @XmlElement(name = "NumCode")
    private String numCode;

    @XmlElement(name = "CharCode")
    private String charCode;

    @XmlElement(name = "Nominal")
    private Integer nominal;

    @XmlElement(name = "Name")
    private String name;

    @XmlElement(name = "Value")
    private String value;
}
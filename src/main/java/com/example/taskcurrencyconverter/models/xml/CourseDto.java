package com.example.taskcurrencyconverter.models.xml;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "ValCurs")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class CourseDto implements Serializable {

    @XmlElement(name = "Valute")
    private List<CourseDtoOnce> valute;
    @XmlAttribute(name = "Date")
    private String date;

}
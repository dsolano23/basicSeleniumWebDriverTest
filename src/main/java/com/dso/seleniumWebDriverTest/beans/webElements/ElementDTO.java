package com.dso.seleniumWebDriverTest.beans.webElements;

import org.openqa.selenium.By;

public class ElementDTO {


    private String elementKey;
    private By idElement;
    private String type;
    private String classAtr;


    public ElementDTO(String elementKey, By idElement, String elementType) {
        this.elementKey = elementKey;
        this.idElement = idElement;
        this.type = elementType;
        this.classAtr = "";

    }

    public String getElementKey() {
        return elementKey;
    }


    public By getIdElement() {
        return idElement;
    }


    public String getType() {
        return type;
    }


    public String getClassAtr() {
        return classAtr;
    }

    public void setClassAtr(String classAtrValue) {
        this.classAtr = classAtrValue;
    }

}

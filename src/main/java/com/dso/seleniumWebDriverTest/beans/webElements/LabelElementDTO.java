package com.dso.seleniumWebDriverTest.beans.webElements;

import org.openqa.selenium.By;

public class LabelElementDTO extends ElementDTO {

    private String labelText;

    public LabelElementDTO(String elementKey, By idElement, String elementType, String labelText) {
        super(elementKey,idElement,elementType);
        this.labelText = labelText;
    }

    public LabelElementDTO(ElementDTO elementDTO, String labelText) {
        super(elementDTO.getElementKey(), elementDTO.getIdElement(), elementDTO.getType());
        this.labelText = labelText;
    }

    public String getLabelText() {
        return labelText;
    }

    public void setLabelText(String labelText) {
        this.labelText = labelText;
    }
}

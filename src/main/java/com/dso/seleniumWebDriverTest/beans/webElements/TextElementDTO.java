package com.dso.seleniumWebDriverTest.beans.webElements;

import org.openqa.selenium.By;

public class TextElementDTO extends ElementDTO {

    private String placeholder;

    public TextElementDTO(String elementKey, By idElement, String elementType, String placeholder) {
        super(elementKey,idElement,elementType);
        this.placeholder = placeholder;
    }

    public TextElementDTO(ElementDTO elementDTO, String placeholder) {
        super(elementDTO.getElementKey(), elementDTO.getIdElement(), elementDTO.getType());
        this.placeholder = placeholder;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }
}

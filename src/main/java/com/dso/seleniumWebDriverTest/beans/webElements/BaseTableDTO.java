package com.dso.seleniumWebDriverTest.beans.webElements;

import org.openqa.selenium.By;

public class BaseTableDTO extends ElementDTO {


    private String xpathBase;
    private int firstRow;
    private String baseCurrentRow;
    private String mainSearchColumn;
    private String secondarySearchColumn;

    public BaseTableDTO(String elementKey, By idElement, String elementType, String xpathBase, int firstRow, String baseCurrentRow, String mainSearchColumn) {
        super(elementKey,idElement,elementType);
        this.xpathBase = xpathBase;
        this.firstRow = firstRow;
        this.baseCurrentRow = baseCurrentRow;
        this.mainSearchColumn = mainSearchColumn;
        this.secondarySearchColumn = "";
    }

    public BaseTableDTO(ElementDTO elementDTO, String xpathBase, int firstRow, String baseCurrentRow, String mainSearchColumn, String secondarySearchColumn) {
        super(elementDTO.getElementKey(), elementDTO.getIdElement(), elementDTO.getType());
        this.xpathBase = xpathBase;
        this.firstRow = firstRow;
        this.baseCurrentRow = baseCurrentRow;
        this.mainSearchColumn = mainSearchColumn;
        this.secondarySearchColumn = secondarySearchColumn;
    }

    public String getXpathBase() {
        return xpathBase;
    }

    public int getFirstRow() {
        return firstRow;
    }

    public String getBaseCurrentRow() {
        return baseCurrentRow;
    }

    public String getMainSearchColumn() {
        return mainSearchColumn;
    }

    public String getSecondarySearchColumn() {
        return secondarySearchColumn;
    }
}

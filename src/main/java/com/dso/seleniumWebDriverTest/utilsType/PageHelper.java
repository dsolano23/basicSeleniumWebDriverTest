package com.dso.seleniumWebDriverTest.utilsType;

import com.dso.seleniumWebDriverTest.beans.webElements.BaseTableDTO;
import com.dso.seleniumWebDriverTest.beans.webElements.ElementDTO;
import com.dso.seleniumWebDriverTest.beans.webElements.LabelElementDTO;
import com.dso.seleniumWebDriverTest.beans.webElements.TextElementDTO;
import com.dso.seleniumWebDriverTest.enviroment.Hooks;
import com.dso.seleniumWebDriverTest.exception.NotFoundResourceException;
import com.dso.seleniumWebDriverTest.utilsType.constans.front.common.ElementAttributeKeys;
import com.dso.seleniumWebDriverTest.utilsType.constans.front.common.WebElementTypesKeys;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PageHelper {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(PageHelper.class);

    public static ElementDTO createNewWebElementDTO (String keyWebComponent, String elementKey, String elementType) throws NotFoundResourceException {
        ElementDTO virtualWebElement;
        By idElement = WebSelector.getElementAttribute(keyWebComponent, elementKey);
        virtualWebElement = new ElementDTO(elementKey,idElement,elementType);
        return virtualWebElement;
    }

    public static Hashtable<String, TextElementDTO> updatePlaceholderWebElements (Hashtable<String, TextElementDTO> virtualWebElementsAtrList) throws NotFoundResourceException {
        String txtBoxTypeElement = WebElementTypesKeys.txtBox.name();
        WebElement virtualWebElement;
        TextElementDTO virtualWebElementArt;
        By virtualElementId;
        String virtualPlaceholder;
        for (String key : virtualWebElementsAtrList.keySet()) {
            if (virtualWebElementsAtrList.get(key).getType().equalsIgnoreCase(txtBoxTypeElement)){
                virtualWebElementArt = virtualWebElementsAtrList.get(key);
                virtualElementId = virtualWebElementArt.getIdElement();
                virtualWebElement = Hooks.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(virtualElementId));
                virtualPlaceholder = virtualWebElement.getAttribute(ElementAttributeKeys.placeholder.name());
                virtualWebElementArt.setPlaceholder(virtualPlaceholder);
            }
        }
        return virtualWebElementsAtrList;
    }

    public static String findItemRowNum(WebElement itemsResultSearch, String descriptionItem, BaseTableDTO tableDTO ) throws NotFoundResourceException {
        String xpathBase = tableDTO.getXpathBase();
        List<WebElement> rows  = itemsResultSearch.findElement(By.xpath(xpathBase)).findElements(By.tagName("tr"));
        int firstRow = tableDTO.getFirstRow();
        String baseCurrentRow = tableDTO.getBaseCurrentRow();
        String mainSearchColumn = tableDTO.getMainSearchColumn();
        String secondarySearchColumn = tableDTO.getSecondarySearchColumn();
        String trRowValue="";
        WebElement virtualMainSearchColumn;
        String searchedMainCellValue = "";
        WebElement virtualSecondarySearchColumn;
        String searchedSecondaryCellValue = "";
        LOGGER.debug("Value of rows.size():" + rows.size() );
        LOGGER.debug("Value of firstRow: " + firstRow );
        LOGGER.debug("Value of search item: " + descriptionItem);
        LOGGER.debug("Value of mainSearchColumn: " + mainSearchColumn);
        LOGGER.debug("Value of secondarySearchColumn: " + secondarySearchColumn);
        for(int rowNum=firstRow;rowNum<=rows.size();rowNum++){
            String currentRow = baseCurrentRow + rowNum + "]";
            String xpathValue = xpathBase + currentRow + mainSearchColumn;
            LOGGER.debug( "xpath " + xpathValue );
            virtualMainSearchColumn = itemsResultSearch.findElement(By.xpath(xpathValue));
            LOGGER.debug("New Item found mainSearchColumn:  " + virtualMainSearchColumn.getText());
            searchedMainCellValue =  virtualMainSearchColumn.getText();

            if (!secondarySearchColumn.equalsIgnoreCase("")){
                xpathValue = xpathBase + currentRow + secondarySearchColumn;
                LOGGER.debug( "xpath " + xpathValue );
                virtualSecondarySearchColumn = itemsResultSearch.findElement(By.xpath(xpathValue));
                LOGGER.debug("New Item found secondarySearchColumn:  " + virtualSecondarySearchColumn.getText());
                searchedSecondaryCellValue = virtualSecondarySearchColumn.getText();
            }

            LOGGER.info("The search item is:  " + descriptionItem);
            if (searchedMainCellValue.trim().equalsIgnoreCase(descriptionItem) || searchedSecondaryCellValue.trim().equalsIgnoreCase(descriptionItem)){
                LOGGER.info("Found item with description: " + searchedMainCellValue + " in the " + tableDTO.getElementKey());
                LOGGER.info("Found item with description: " + searchedSecondaryCellValue + " in the " + tableDTO.getElementKey());
                trRowValue = currentRow;
                break;
            }
        }

        return trRowValue;
    }

    public static LabelElementDTO getLabelText (LabelElementDTO virtualWebElementDTO) throws NotFoundResourceException {
        By elementId = virtualWebElementDTO.getIdElement();
        WebElement labelLogText = Hooks.getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(elementId));
        String labelText;
        labelText = labelLogText.getText();
        virtualWebElementDTO.setLabelText(labelText);
        return virtualWebElementDTO;
    }

    public static void setTxtBoxText(ElementDTO virtualWebElementDTO, String textValue) throws NotFoundResourceException {
        By elementId = virtualWebElementDTO.getIdElement();
        WebElement txtbxInsert = Hooks.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(elementId));
        txtbxInsert.clear();
        txtbxInsert.sendKeys(textValue);
    }

    public static String getElementAttributeValue (ElementDTO virtualWebElementDTO, String attribute) throws NotFoundResourceException {
        By elementId = virtualWebElementDTO.getIdElement();
        //WebElement virtualElement =Hooks.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(elementId));
        LOGGER.debug("Value of ID: " + elementId.toString());
        WebElement virtualElement =Hooks.getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(elementId));
        String  attributeValue;
        attributeValue = virtualElement.getAttribute(attribute.trim());
        LOGGER.debug("Value of attributeValue: " +attributeValue);
        return attributeValue;
    }

    public static boolean elementClickable(ElementDTO virtualWebElementDTO) throws NotFoundResourceException {
        By elementId = virtualWebElementDTO.getIdElement();
        WebElement virtualElement = Hooks.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(elementId));
        Boolean elementClickable;
        elementClickable = virtualElement.isDisplayed();
        return elementClickable;
    }

    public static boolean elementVisible(ElementDTO virtualWebElementDTO) throws NotFoundResourceException {
        By elementId = virtualWebElementDTO.getIdElement();
        WebElement virtualElement = Hooks.getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(elementId));
        Boolean elementVisible;
        elementVisible = virtualElement.isDisplayed();
        return elementVisible;
    }

    public static boolean elementEnabled (ElementDTO virtualWebElementDTO) throws NotFoundResourceException {
        By elementId = virtualWebElementDTO.getIdElement();
        WebElement virtualElement = Hooks.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(elementId));
        Boolean elementEnabled;
        elementEnabled = virtualElement.isEnabled();
        return elementEnabled;
    }

    public static void clickOnElement (ElementDTO virtualWebElementDTO) throws NotFoundResourceException {
        By elementId = virtualWebElementDTO.getIdElement();
        WebElement virtualElement = Hooks.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(elementId));
        virtualElement.click();
    }

    public static void clickOnElement (WebElement itemsResultSearch, ElementDTO virtualWebElementDTO) throws NotFoundResourceException {
        By elementId = virtualWebElementDTO.getIdElement();
        WebElement virtualElement = itemsResultSearch.findElement(elementId);
        virtualElement.click();
    }


    public static void selectAutoCompResult(int resultNumber ) throws AWTException, InterruptedException {
        Robot robot = new Robot();
        robot.setAutoDelay(10);
        robot.setAutoWaitForIdle(true);
        for(int i=0; i< resultNumber; i++){
            robot.keyPress(KeyEvent.VK_DOWN);
            TimeUnit.SECONDS.sleep(1);
        }
        TimeUnit.SECONDS.sleep(1);
        robot.keyPress(KeyEvent.VK_ENTER);
        TimeUnit.SECONDS.sleep(1);
    }

    public static void moveMouseToCoordinates ( int x, int y) throws AWTException, InterruptedException {
        Robot robot = new Robot();
        robot.setAutoDelay(10);
        robot.setAutoWaitForIdle(true);
        robot.mouseMove(x,y);
        TimeUnit.SECONDS.sleep(1);
        robot.mousePress(InputEvent.BUTTON1_MASK );
    }

    public static void getElementCoordinates(ElementDTO virtualWebElementDTO) throws NotFoundResourceException, InterruptedException, AWTException {
        By elementId = virtualWebElementDTO.getIdElement();
        WebElement virtualElement = Hooks.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(elementId));

        Point classname = virtualElement.getLocation();
        int xcordi = classname.getX();
        LOGGER.debug("Element's Position from left side "+xcordi +" pixels.");
        int ycordi = classname.getY();
        LOGGER.debug("Element's Position from top "+ycordi +" pixels.");
        PageHelper.moveMouseToCoordinates(xcordi,ycordi);
    }

    public static WebElement getItemsInTable( ElementDTO virtualWebElementDTO ) throws NotFoundResourceException {
        By elementId = virtualWebElementDTO.getIdElement();
        WebElement itemsResultSearch;
        itemsResultSearch = Hooks.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(elementId));
        return itemsResultSearch;
    }
}

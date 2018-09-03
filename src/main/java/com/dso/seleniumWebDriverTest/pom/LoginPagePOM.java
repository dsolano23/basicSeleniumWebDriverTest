package com.dso.seleniumWebDriverTest.pom;

import com.dso.seleniumWebDriverTest.beans.webElements.ElementDTO;
import com.dso.seleniumWebDriverTest.beans.webElements.LabelElementDTO;
import com.dso.seleniumWebDriverTest.exception.NotFoundResourceException;
import com.dso.seleniumWebDriverTest.utilsType.PageHelper;
import com.dso.seleniumWebDriverTest.utilsType.WebSelector;
import com.dso.seleniumWebDriverTest.utilsType.constans.front.common.ElementAttributeKeys;
import com.dso.seleniumWebDriverTest.utilsType.constans.front.common.WebComponentKeys;
import com.dso.seleniumWebDriverTest.utilsType.constans.front.common.WebElementTypesKeys;
import com.dso.seleniumWebDriverTest.utilsType.constans.front.specific.LoginFormKeys;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Hashtable;

public class LoginPagePOM {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(LoginPagePOM.class);
    private String keyWebComponent =  WebComponentKeys.loginForm.name();
    private String txtBoxTypeElement = WebElementTypesKeys.txtBox.name();
    private String submitTypeElement = WebElementTypesKeys.submit.name();
    private String labelTypeElement = WebElementTypesKeys.label.name();

    private Hashtable<String, ElementDTO> webElementsList = new Hashtable<String, ElementDTO>();

    private String AccessToLoginPageStepOneKey = LoginFormKeys.AccessToLoginPageStepOne.name();
    private String AccessToLoginPageStepTowKey = LoginFormKeys.AccessToLoginPageStepTwo.name();
    private String insertIdUserKey =  LoginFormKeys.InsertName.name();
    private String insertPasswordKey = LoginFormKeys.InsertPassword.name();
    private String submitButtonKey = LoginFormKeys.SubmitButton.name();
    private String logInUnsuccessfullyKey = LoginFormKeys.UnsuccessfullyLabel.name();
    //TODO
    //private String logInSuccessfullyKey = LoginFormKeys.IdUserNameSessionLabel.name();
    //private String accessToCreateUserButtonKey = LoginFormKeys.AccessToCreateUser.name();

    private  WebDriver currentDriver;

    public LoginPagePOM(WebDriver currentDriver) throws NotFoundResourceException {
        this.currentDriver = currentDriver;
        loadByIdElements();
    }

    private void loadByIdElements() throws NotFoundResourceException {
        Hashtable<String, ElementDTO> virtualWebElementsAtr = new Hashtable<String, ElementDTO>();
        ElementDTO virtualWebElement;

        By idElement = WebSelector.getElementAttribute(keyWebComponent, AccessToLoginPageStepOneKey);
        virtualWebElement = new ElementDTO(AccessToLoginPageStepOneKey,idElement,submitTypeElement);
        virtualWebElementsAtr.put(AccessToLoginPageStepOneKey,virtualWebElement);

        idElement = WebSelector.getElementAttribute(keyWebComponent, insertIdUserKey);
        virtualWebElement = new ElementDTO(insertIdUserKey,idElement,txtBoxTypeElement);
        virtualWebElementsAtr.put(insertIdUserKey,virtualWebElement);

        idElement = WebSelector.getElementAttribute(keyWebComponent, AccessToLoginPageStepTowKey);
        virtualWebElement = new ElementDTO(AccessToLoginPageStepTowKey,idElement,submitTypeElement);
        virtualWebElementsAtr.put(AccessToLoginPageStepTowKey,virtualWebElement);

        idElement = WebSelector.getElementAttribute(keyWebComponent, insertPasswordKey);
        virtualWebElement = new ElementDTO(insertPasswordKey,idElement,txtBoxTypeElement);
        virtualWebElementsAtr.put(insertPasswordKey,virtualWebElement);

        idElement = WebSelector.getElementAttribute(keyWebComponent, submitButtonKey);
        virtualWebElement = new ElementDTO(submitButtonKey,idElement,submitTypeElement);
        virtualWebElementsAtr.put(submitButtonKey,virtualWebElement);

        idElement = WebSelector.getElementAttribute(keyWebComponent, logInUnsuccessfullyKey);
        String labelText = WebSelector.getElementAttribute(keyWebComponent, logInUnsuccessfullyKey, ElementAttributeKeys.labelText.name());
        virtualWebElement = new LabelElementDTO(logInUnsuccessfullyKey,idElement,labelTypeElement,labelText);
        virtualWebElementsAtr.put(logInUnsuccessfullyKey,virtualWebElement);

        //TODO
        /*
        idElement = WebSelector.getElementAttribute(keyWebComponent, logInSuccessfullyKey);
        virtualWebElement = new ElementDTO(logInSuccessfullyKey,idElement,labelTypeElement);
        virtualWebElementsAtr.put(logInSuccessfullyKey,virtualWebElement);

        idElement = WebSelector.getElementAttribute(keyWebComponent, accessToCreateUserButtonKey);
        virtualWebElement = new ElementDTO(accessToCreateUserButtonKey,idElement,submitTypeElement);
        virtualWebElementsAtr.put(accessToCreateUserButtonKey,virtualWebElement);*/

        setWebElementsList(virtualWebElementsAtr);
    }


    //TODO
    /*
    public void loadPlaceholderWebElements () throws NotFoundResourceException {
        Hashtable<String, TextElementDTO> virtualWebElementsAtrList = PageHelper.updatePlaceholderWebElements(this.getWebElementsList());
        ElementDTO virtualWebElementDTO;
        for (String key : virtualWebElementsAtrList.keySet()) {
            virtualWebElementDTO = virtualWebElementsAtrList.get(key);
            this.updateWebElementAtrList(key,virtualWebElementDTO);
        }
    }*/

    public void accessToLoginPage() throws NotFoundResourceException {
        ElementDTO virtualWebElementDTO = this.getWebElementsList().get(AccessToLoginPageStepOneKey);
        PageHelper.clickOnElement(virtualWebElementDTO);
    }

    public void goToInsertPWD() throws NotFoundResourceException {
        ElementDTO virtualWebElementDTO = this.getWebElementsList().get(AccessToLoginPageStepTowKey);
        PageHelper.clickOnElement(virtualWebElementDTO);
    }

    public void setUserName(String userName) throws NotFoundResourceException {
        ElementDTO virtualWebElementDTO = this.getWebElementsList().get(insertIdUserKey);
        PageHelper.setTxtBoxText(virtualWebElementDTO,userName);
    }

    public void setPassword(String password) throws NotFoundResourceException {
        ElementDTO virtualWebElementDTO = this.getWebElementsList().get(insertPasswordKey);
        PageHelper.setTxtBoxText(virtualWebElementDTO,password);
    }

    public void commit() throws NotFoundResourceException {
        ElementDTO virtualWebElementDTO = this.getWebElementsList().get(submitButtonKey);
        PageHelper.clickOnElement(virtualWebElementDTO);
    }

    public Boolean logInUnsuccessfullyIsDisplayed() throws NotFoundResourceException{
        ElementDTO virtualWebElementDTO = this.getWebElementsList().get(logInUnsuccessfullyKey);
        Boolean logInUnsuccessfullyDisplayed;
        logInUnsuccessfullyDisplayed = PageHelper.elementClickable(virtualWebElementDTO);
        return logInUnsuccessfullyDisplayed;
    }

    public String getLogInUnsuccessfullyMessage() throws NotFoundResourceException{
        LabelElementDTO virtualWebElementDTO = (LabelElementDTO) this.getWebElementsList().get(logInUnsuccessfullyKey);
        virtualWebElementDTO = PageHelper.getLabelText(virtualWebElementDTO);
        String logInUnsuccessfullyMessage = virtualWebElementDTO.getLabelText();
        return logInUnsuccessfullyMessage;
    }

    //TODO
/*
    public String getLogInSuccessfullyMessage() throws NotFoundResourceException{
        ElementDTO virtualWebElementDTO = this.getWebElementsList().get(logInSuccessfullyKey);
        virtualWebElementDTO = PageHelper.getLabelText(virtualWebElementDTO);
        String logInSuccessfullyMessage = virtualWebElementDTO.getText();
        return logInSuccessfullyMessage;
    }

    public void navigateToCreateUser() throws NotFoundResourceException {
        ElementDTO virtualWebElementDTO = this.getWebElementsList().get(accessToCreateUserButtonKey);
        PageHelper.clickOnElement(virtualWebElementDTO);
    }*/

    public Hashtable<String, ElementDTO> getWebElementsList() {
        return webElementsList;
    }

    private void setWebElementsList(Hashtable<String, ElementDTO> webElementsList) {
        this.webElementsList = webElementsList;
    }

    private void updateWebElementAtrList ( String webElementKey , ElementDTO webElementArt){
        this.getWebElementsList().replace(webElementKey,webElementArt);
    }
}

package com.dso.seleniumWebDriverTest.utilsType.constans;

public enum KeyElementsLoginPage {

    ACCESS_TO_LOGIN("nav-signin-tooltip","nav-icon", "Hola. Identifícate"),
    INSERT_EMAIL("ap_email","a-input-text","Dirección de e-mail o número de teléfono móvil"),
    INSERT_PASSWORD("ap_password","a-input-text","password"),
    NEXT_BUTTON("continue","submit","Continuar"),
    SUMIT_BUTTON("signInSubmit","submit","Iniciar sesión"),;

    String idElement;
    String type;
    String label;

    private KeyElementsLoginPage(String idElement, String name, String textContent){

        this.idElement = idElement;
        this.type = name;
        this.label = textContent;

    }

    public String getIdElement() {
        return idElement;
    }

    public String getType() {
        return type;
    }

    public String getLabel() {
        return label;
    }


}

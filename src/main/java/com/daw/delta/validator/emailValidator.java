package com.daw.delta.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class emailValidator implements Validator{

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        String email = o.toString();
        if(!email.matches("((?=(.*[a-z]))?)((?=(.*[A-Z]))?)((?=(.*[0-9]))?)[a-zA-Z\\d?]{4,}\\@[a-z]{2,}\\.[a-z]{2,}")){
            throw new ValidatorException(new FacesMessage("El  email debe cumplir con el formato: minimo 4 caracteres, @, dominio y extension"));
        } 
    }
    
}

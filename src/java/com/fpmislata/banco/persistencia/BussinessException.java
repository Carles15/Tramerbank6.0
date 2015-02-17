package com.fpmislata.banco.persistencia;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolation;

public class BussinessException extends Exception {

    private List<BussinessMessage> bussinessMessages = new ArrayList();

    public BussinessException(List<BussinessMessage> bussinessMessages) {
        this.bussinessMessages.addAll(bussinessMessages);
    }

    public BussinessException(BussinessMessage bussinessMessage) {
        this.bussinessMessages.add(bussinessMessage);
    }

    public BussinessException(String message) {
        BussinessMessage bussinessMessage = new BussinessMessage(message);
        this.bussinessMessages.add(bussinessMessage);
    }

    public List<BussinessMessage> getMessagesList() {
        return this.bussinessMessages;
    }

    public BussinessException(javax.validation.ConstraintViolationException cve) {
        for (ConstraintViolation constraintViolation : cve.getConstraintViolations()) {
            String message;
            
            message = constraintViolation.getPropertyPath() + ": " +constraintViolation.getMessage();
            
            BussinessMessage bussinessMessage = new BussinessMessage(message);
            bussinessMessages.add(bussinessMessage);
        }
    }

}
package com.platzi.pizza.Service.Exception;

public class EmailApiException extends RuntimeException{
    public EmailApiException(){
        super("Error sending email......");
    }

}

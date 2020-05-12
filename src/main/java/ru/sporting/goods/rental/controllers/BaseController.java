package ru.sporting.goods.rental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

abstract public class BaseController {

    public static final Locale RUSSIAN = new Locale("ru", "RU");

    private MessageSource messageSource;

    //Метод для добавления всех ошибок валидаций во FlashAttribute
    protected void addValidationMessage(RedirectAttributes redirectAttributes, BindingResult bindingResult){
        for(FieldError error : bindingResult.getFieldErrors()){
            redirectAttributes.addFlashAttribute("validation." + error.getField(), error.getDefaultMessage());
        }
    }

    //Метод для добавления сообщения о том что всё хорошо во FlashAttribute
    protected void addSuccessMessage(RedirectAttributes redirectAttributes, String messageCode){
        Map<String, Object> flashAttributes = (Map<String, Object>) redirectAttributes.getFlashAttributes();
        List<String> successMessages = (List<String>) flashAttributes.getOrDefault("successMessages", new ArrayList<>());
    }

    public String getMessage(String code, Object... args){
        return messageSource.getMessage(code, args, RUSSIAN);
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource){
        this.messageSource = messageSource;
    }

}

package ru.sporting.goods.rental.exceptions;

public class TypeOfItemNotFoundException extends RuntimeException {

    public TypeOfItemNotFoundException(Long id){
        super("Тип товара с номером " + id + " не найден!");
    }
}

package ru.sporting.goods.rental.Exceptions;

public class ProductTypeNotFoundException extends RuntimeException {

    public ProductTypeNotFoundException(Long id){
        super("Тип товара с " + id + " не найден!");
    }
}

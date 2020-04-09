package ru.sporting.goods.rental.exceptions;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(Long id){
        super("Товар с номером " + id + " не найден!");
    }
}

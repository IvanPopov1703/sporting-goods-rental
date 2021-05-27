package ru.sporting.goods.rental.exceptions;

public class RecordNotFound extends RuntimeException {
    public RecordNotFound(Long id){
        super("Запись с номером " + id + " не найдена!");
    }
}

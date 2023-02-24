package pl.sklep.opinion.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.sklep.purchaseditem.PurchasedItem;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OpinionNotFoundExcepiton extends RuntimeException {

    public OpinionNotFoundExcepiton(Long id) {
        super("Opinia o id " + id + " nie istnieje");
    }

    public OpinionNotFoundExcepiton(PurchasedItem purchasedItem) {
        super("Opinia dla zakupu o id " + purchasedItem.getId() + " nie istnieje");
    }

}

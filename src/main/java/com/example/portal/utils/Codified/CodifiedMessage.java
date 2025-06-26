package com.example.portal.utils.Codified;

public interface CodifiedMessage<E extends Number> {
    String getDescription();

    E getCode();

    boolean is(Long id);
}
package com.example.portal.api;



import com.example.portal.utils.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@JsonView(Views.AllView.class)
public abstract class Response implements Serializable {
    private final Boolean result;
}
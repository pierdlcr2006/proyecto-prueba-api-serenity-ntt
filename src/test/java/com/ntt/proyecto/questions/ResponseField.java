package com.ntt.proyecto.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ResponseField implements Question<String> {

    private final String field;

    public ResponseField(String field) {
        this.field = field;
    }

    public static Question<String> valueOf(String field) {
        return new ResponseField(field);
    }

    @Override
    public String answeredBy(Actor actor) {
        return SerenityRest.lastResponse().path(field).toString();
    }
}

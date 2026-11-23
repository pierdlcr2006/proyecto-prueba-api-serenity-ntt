package com.ntt.proyecto.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.List;

public class ResponseBodySize implements Question<Integer> {

    public static Question<Integer> value() {
        return new ResponseBodySize();
    }

    @Override
    public Integer answeredBy(Actor actor) {
        List<?> response = SerenityRest.lastResponse().jsonPath().getList("");
        return response.size();
    }
}

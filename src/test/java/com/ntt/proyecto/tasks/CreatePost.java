package com.ntt.proyecto.tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CreatePost implements Task {

    private final com.ntt.proyecto.models.Post post;

    public CreatePost(com.ntt.proyecto.models.Post post) {
        this.post = post;
    }

    public static Performable with(com.ntt.proyecto.models.Post post) {
        return instrumented(CreatePost.class, post);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to("/posts").with(request -> request
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .body(post)
                        .log().all())
        );
    }
}

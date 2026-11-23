package com.ntt.proyecto.stepdefinitions;

import com.ntt.proyecto.apis.JsonPlaceholderApi;
import com.ntt.proyecto.models.Post;
import com.ntt.proyecto.questions.ResponseBodySize;
import com.ntt.proyecto.questions.ResponseCode;
import com.ntt.proyecto.questions.ResponseField;
import com.ntt.proyecto.tasks.CreatePost;
import com.ntt.proyecto.tasks.GetPosts;
import io.cucumber.java.Before;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class PostStepDefinition {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("el {actor} establece el endpoint de posts")
    public void elActorEstableceElEndpointDePosts(Actor actor) {
        actor.whoCan(CallAnApi.at(JsonPlaceholderApi.BASE_URL));
    }

    @When("el {actor} consulta todos los posts")
    public void elActorConsultaTodosLosPosts(Actor actor) {
        actor.attemptsTo(GetPosts.fromEndpoint(JsonPlaceholderApi.POSTS));
    }

    @When("el {actor} consulta el post con id {string}")
    public void elActorConsultaElPostConId(Actor actor, String id) {
        actor.attemptsTo(GetPosts.fromEndpoint(JsonPlaceholderApi.POSTS + "/" + id));
    }

    @When("el {actor} crea un post con los siguientes datos")
    public void elActorCreaUnPost(Actor actor, DataTable dataTable) {
        Map<String, String> postData = dataTable.asMaps(String.class, String.class).get(0);
        actor.attemptsTo(
                CreatePost.with(new Post(
                        postData.get("title"),
                        postData.get("body"),
                        Integer.parseInt(postData.get("userId"))
                ))
        );
    }

    @Then("el codigo de respuesta debe ser {int}")
    public void elCodigoDeRespuestaDebeSer(int responseCode) {
        theActorInTheSpotlight().should(
                seeThat("el codigo de respuesta", ResponseCode.value(), equalTo(responseCode))
        );
    }

    @Then("la respuesta debe contener posts")
    public void laRespuestaDebeContenerPosts() {
        theActorInTheSpotlight().should(
                seeThat("la cantidad de posts", ResponseBodySize.value(), greaterThan(0))
        );
    }

    @Then("el campo {string} de la respuesta debe ser {string}")
    public void elCampoDeLaRespuestaDebeSer(String field, String expectedValue) {
        theActorInTheSpotlight().should(
                seeThat("el campo " + field, ResponseField.valueOf(field), equalTo(expectedValue))
        );
    }
}

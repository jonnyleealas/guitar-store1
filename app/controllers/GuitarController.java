package controllers;

import com.google.inject.Inject;
import play.db.jpa.Transactional;
import models.*;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.GuitarRepository;



public class GuitarController extends Controller {

    @Inject
    public GuitarRepository guitarRepository;

     @Transactional
    public Result findAll() {
        return ok(Json.toJson(guitarRepository.findAll())).as("application/json");
    }

    @Transactional
    public Result findById(int id) {
        return ok(Json.toJson(guitarRepository.findById(id))).as("application/json");
    }

    @Transactional
    public Result create() {
        Guitar guitarRequest = Json.fromJson(request().body().asJson(), Guitar.class);

        guitarRepository.add(guitarRequest);

        return ok(Json.toJson(guitarRequest)).as("application/json");
    }

    @Transactional
    public Result update(int id) {
        Guitar guitarRequest = Json.fromJson(request().body().asJson(), Guitar.class);

        guitarRequest.setId(id);

        guitarRepository.update(guitarRequest);

        return ok(Json.toJson(guitarRequest)).as("application/json");
    }

    @Transactional
    public Result delete(int id) {
        guitarRepository.delete(id);
        return ok("{}").as("application/json");
    }
}

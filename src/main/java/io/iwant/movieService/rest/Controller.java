package io.iwant.movieService.rest;

import io.iwant.movieService.model.Model;
import io.iwant.movieService.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @Autowired
    MovieService service;

        @PostMapping("/movies/create")
    public Model create(@RequestBody Model movie){
        return service.create(movie);
    }

    @GetMapping("/movies/{id}")
    public Model find(@PathVariable String id) throws ChangeSetPersister.NotFoundException {
        return service.findById(id);
    }
}

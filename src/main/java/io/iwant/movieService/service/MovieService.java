package io.iwant.movieService.service;

import io.iwant.movieService.model.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

@Component
public class MovieService {

    private static final Logger logger = LoggerFactory.getLogger(MovieService.class);

    private static final Class<Model> model = Model.class;

    @Autowired
    protected MongoTemplate mongoService;

    public Model create(Model movie){
        Model response = mongoService.insert(movie);
        logger.trace("Movie Added");
        return response;
    }

    public Model findById(String id) throws NotFoundException {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Model selected = mongoService.findOne(query, model );
        if (selected == null) {
            throw new NotFoundException();
        }
        return selected;
    }


}

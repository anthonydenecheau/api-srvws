package com.scc.onlinedogshow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import com.scc.onlinedogshow.model.Dog;
import com.scc.onlinedogshow.services.DogService;
import com.scc.onlinedogshow.template.ResponseObject;
import com.scc.onlinedogshow.template.ResponseObjectList;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping(value="v1/dogs")
public class DogServiceController {
   
	@Autowired
    private DogService dogService;

    @RequestMapping(value="/{dogId}",method = RequestMethod.GET)
    public Dog getDog( @PathVariable("dogId") int dogId) {
        return dogService.getDogById(dogId);
    }
    
    @RequestMapping(value="/searchByToken/{token}",method = RequestMethod.GET)
    public ResponseObjectList<ResponseObject> getDogByToken( @PathVariable("token") String token) {
        return dogService.getDogByToken(token);
    }    

    @RequestMapping(value="{dogId}",method = RequestMethod.PUT)
    public String updateDog( @PathVariable("dogId") int dogId) {
        return String.format("This is the put");
    }

    @RequestMapping(value="{dogId}",method = RequestMethod.POST)
    public String saveDog( @PathVariable("dogId") int dogId) {
        return String.format("This is the post");
    }

    @RequestMapping(value="{dogId}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteDog( @PathVariable("dogId") int dogId) {
        return String.format("This is the Delete");
    }
}

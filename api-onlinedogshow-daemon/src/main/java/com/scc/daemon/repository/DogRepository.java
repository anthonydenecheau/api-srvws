package com.scc.daemon.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.scc.daemon.model.OdsDog;

@Repository
public interface DogRepository extends CrudRepository<OdsDog,String>  {
    public OdsDog findById(long id);
}

package com.scc.daemon.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.scc.daemon.model.OdsBreeder;

@Repository
public interface BreederRepository extends CrudRepository<OdsBreeder,String>  {
    public List<OdsBreeder> findById(int id);
    public OdsBreeder findByIdDog(int idDog);
    
}

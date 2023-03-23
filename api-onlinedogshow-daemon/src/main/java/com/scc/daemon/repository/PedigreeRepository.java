package com.scc.daemon.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.scc.daemon.model.OdsPedigree;

@Repository
public interface PedigreeRepository extends CrudRepository<OdsPedigree,String>  {
    public OdsPedigree findById(long id);
}

package com.scc.daemon.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.scc.daemon.model.OdsParent;

@Repository
public interface ParentRepository extends CrudRepository<OdsParent,String>  {
    public OdsParent findById(int id);
}

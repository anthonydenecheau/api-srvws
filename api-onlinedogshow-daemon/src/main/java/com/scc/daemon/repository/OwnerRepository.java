package com.scc.daemon.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.scc.daemon.model.OdsOwner;

@Repository
public interface OwnerRepository extends CrudRepository<OdsOwner,String>  {
    public List<OdsOwner> findById(int id);
    public OdsOwner findByIdDog(int idDog);
}

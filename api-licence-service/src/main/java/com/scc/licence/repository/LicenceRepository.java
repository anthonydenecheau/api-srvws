package com.scc.licence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.scc.licence.model.Holder;

@Repository
public interface LicenceRepository extends CrudRepository<Holder, String> {
	
    public Optional<Holder> findById(int id);
    
    public List<Holder> findByIdCommission(int idCommission);

}

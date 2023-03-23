package com.scc.daemon.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.scc.daemon.model.OdsTitle;

@Repository
public interface TitleRepository extends CrudRepository<OdsTitle,String>  {
    
	public OdsTitle findById(long id);

}

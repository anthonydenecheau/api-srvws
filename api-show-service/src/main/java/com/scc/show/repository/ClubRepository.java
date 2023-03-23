package com.scc.show.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.scc.show.model.Club;

@Repository
public interface ClubRepository extends CrudRepository<Club, String> {

   public List<Club> findAll();
}

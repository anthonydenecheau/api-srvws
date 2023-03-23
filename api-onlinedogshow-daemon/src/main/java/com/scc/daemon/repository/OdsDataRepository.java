package com.scc.daemon.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.scc.daemon.model.OdsSyncData;

@Repository
public interface OdsDataRepository extends CrudRepository<OdsSyncData,String>  {
    public List<OdsSyncData> findByTransfertAndDomaine(String transfert, String domaine);
    public OdsSyncData save(OdsSyncData dog);
    public void delete(OdsSyncData dog);
}

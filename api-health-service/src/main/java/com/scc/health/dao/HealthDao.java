package com.scc.health.dao;

import java.util.List;

import com.scc.health.domain.Reviewer;
import com.scc.health.exception.RegisterStudyException;
import com.scc.health.template.RegisterEventObject;

public interface HealthDao {

   public List<Reviewer> getReaderMyPacsByIdDog(int idDog, int idMaladie);

   public boolean registerMyPacsStudy(RegisterEventObject event) throws RegisterStudyException;
}

package com.scc.health.service;

import com.scc.health.exception.RegisterStudyException;
import com.scc.health.template.ReviewerObject;
import com.scc.health.template.RegisterEventObject;
import com.scc.health.template.ResponseObjectList;
import com.scc.health.utils.DiseaseEnum;

public interface HealthService {

   public ResponseObjectList<ReviewerObject> getReaderMyPacsByIdDog(int idDog, DiseaseEnum disease);

   public void registerMyPacsStudy(RegisterEventObject event) throws RegisterStudyException;
}

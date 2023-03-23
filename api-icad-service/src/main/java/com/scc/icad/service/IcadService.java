package com.scc.icad.service;

import com.scc.icad.domain.Dog;

public interface IcadService {

    public Dog getIcadDogByToken(String token);
}

package com.scc.icad.dao;

import com.scc.icad.domain.Dog;

public interface IcadDao {

    public Dog getIcadDogByToken(String token);
}

package com.scc.health.service;

import java.io.File;

public interface DocumentService {

   public boolean save(File document, String reference) throws Exception;

}

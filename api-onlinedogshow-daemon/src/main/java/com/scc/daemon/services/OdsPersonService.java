package com.scc.daemon.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scc.daemon.model.OdsBreeder;
import com.scc.daemon.model.OdsOwner;
import com.scc.daemon.model.OdsSyncData;
import com.scc.daemon.repository.OwnerRepository;
import com.scc.daemon.repository.BreederRepository;
import com.scc.daemon.repository.OdsDataRepository;
import com.scc.events.source.SimpleSourceBean;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class OdsPersonService {

    private static final Logger logger = LoggerFactory.getLogger(OdsPersonService.class);

    @Autowired
    private SimpleSourceBean simpleSourceBean;
    
    @Autowired
    private OdsDataRepository odsDataRepository;

    @Autowired
    private BreederRepository breederRepository;

    @Autowired
    private OwnerRepository ownerRepository;
    
    public List<OdsSyncData> getAllPersons(){
        try {
        	return odsDataRepository.findByTransfertAndDomaine("N","PERSONNE");
        }
        finally{
        }

    }
    
    public OdsSyncData savePerson(OdsSyncData person){
        try {
        	return odsDataRepository.save(person);
        }
        finally{
        }
    }

    public List<OdsBreeder> getBreederById(int personId){
        try {
        	return breederRepository.findById(personId);
        }
        finally{
        }
    }
    
    public void refreshBreeder(List<OdsBreeder> breeders, String action){
    	try {

    		if (breeders != null && breeders.size()>0) {
	    		switch(action){
	            	case "U":
	            		simpleSourceBean.publishBreederChange("UPDATE", breeders);
	            		break;
	            	case "I":
	            		simpleSourceBean.publishBreederChange("SAVE", breeders);
	            		break;
	            	case "D":
	            		simpleSourceBean.publishBreederChange("DELETE", breeders);
	            		break;
	                default:
	                    logger.error("Received an UNKNOWN event from the agria service of type {}", action);
	                    break;      
	    		}
    		}
        }
        finally{
        }
    	
    }
    
    public List<OdsOwner> getOwnerById(int personId){
    	
        try {
        	return ownerRepository.findById(personId);
        }
        finally{
        }
    }
    

    public void refreshOwner(List<OdsOwner> owners, String action){
    	try {

    		if (owners != null && owners.size()>0) {
	    		switch(action){
	            	case "U":
	            		simpleSourceBean.publishOwnerChange("UPDATE", owners);
	            		break;
	            	case "I":
	            		simpleSourceBean.publishOwnerChange("SAVE", owners);
	            		break;
	            	case "D":
	            		simpleSourceBean.publishOwnerChange("DELETE", owners);
	            		break;
	                default:
	                    logger.error("Received an UNKNOWN event from the ods person service of type {}", action);
	                    break;      
	    		}
    		}
        }
        finally{
        }
    	
    }
    
    public void deletePerson (OdsSyncData person) {
        try {
        	odsDataRepository.delete(person);
        }
        finally{
        }
    }
}

package com.scc.daemon.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scc.daemon.model.OdsBreeder;
import com.scc.daemon.model.OdsDog;
import com.scc.daemon.model.OdsOwner;
import com.scc.daemon.model.OdsParent;
import com.scc.daemon.model.OdsPedigree;
import com.scc.daemon.model.OdsSyncData;
import com.scc.daemon.model.OdsTitle;
import com.scc.daemon.repository.BreederRepository;
import com.scc.daemon.repository.DogRepository;
import com.scc.daemon.repository.OdsDataRepository;
import com.scc.daemon.repository.OwnerRepository;
import com.scc.daemon.repository.ParentRepository;
import com.scc.daemon.repository.PedigreeRepository;
import com.scc.daemon.repository.TitleRepository;
import com.scc.events.source.SimpleSourceBean;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class OdsDogService {

    private static final Logger logger = LoggerFactory.getLogger(OdsDogService.class);

    @Autowired
    private SimpleSourceBean simpleSourceBean;

    @Autowired
    private ParentRepository parentRepository;    

    @Autowired
    private PedigreeRepository pedigreeRepository;    
    
    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private BreederRepository breederRepository;

    @Autowired
    private DogRepository dogRepository;

    @Autowired
    private OdsDataRepository odsDataRepository;

    
    public List<OdsSyncData> getAllDogs(){
        try {
        	return odsDataRepository.findByTransfertAndDomaine("N","CHIEN");
        }
        finally{
        }

    }
    
    public List<OdsSyncData> getAllBreeders(){
        try {
        	return odsDataRepository.findByTransfertAndDomaine("N","ELEVEUR");
        }
        finally{
        }

    }
    
    public List<OdsSyncData> getAllOwners(){

        try {
        	return odsDataRepository.findByTransfertAndDomaine("N","PROPRIETAIRE");
        }
        finally{
        }

    }    
    
    public List<OdsSyncData> getAllTitles(){

       List<OdsSyncData> _titles = new ArrayList<OdsSyncData>();

        try {
        	_titles  = odsDataRepository.findByTransfertAndDomaine("N","TITRE_FRANCAIS");
        	_titles.addAll(odsDataRepository.findByTransfertAndDomaine("N","TITRE_ETRANGER"));
        }
        finally{
        }
        
        return _titles;

    }    
    
    public List<OdsSyncData> getAllPedigree(){
        try {
        	return odsDataRepository.findByTransfertAndDomaine("N","LIVRE");
        }
        finally{
        }

    } 
    
    public List<OdsSyncData> getAllParent(){
        try {
        	return odsDataRepository.findByTransfertAndDomaine("N","GENITEUR");
        }
        finally{
        }

    } 
    
    public OdsDog getDogById(long dogId){

        try {
        	return dogRepository.findById(dogId);
        }
        finally{
        }
    }
    
    public OdsSyncData saveDog(OdsSyncData dog){
        
        try {
        	return odsDataRepository.save(dog);
        }
        finally{
        }
    }
    
    public void refreshDog(OdsDog dog, String action){
    	
    	try {

    		if (dog != null) {
	    		switch(action){
	            	case "U":
	            		simpleSourceBean.publishDogChange("UPDATE", dog);
	            		break;
	            	case "I":
	            		simpleSourceBean.publishDogChange("SAVE", dog);
	            		break;
	            	case "D":
	            		simpleSourceBean.publishDogChange("DELETE", dog);
	            		break;
	                default:
	                    logger.error("Received an UNKNOWN event from the ods dog service of type {}", action);
	                    break;      
	    		}
    		}
        }
        finally{
        }
    	
    }
    
    public void refreshBreeder(OdsBreeder dog, String action){
    	
    	try {

    		if (dog != null) {
    			
    			List<OdsBreeder> breeders = new ArrayList<OdsBreeder>();
    			breeders.add(dog);
    			
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
	                    logger.error("Received an UNKNOWN event from the ods breeder service of type {}", action);
	                    break;      
	    		}
    		}
        }
        finally{
        }
    	
    }
    
    public OdsBreeder getBreederByIdDog(int dogId){
    	
        try {
        	return breederRepository.findByIdDog(dogId);
        }
        finally{
        }
    }    
    
    
    public void refreshOwner(OdsOwner dog, String action){
    	
    	try {

    		if (dog != null) {
    			
    			List<OdsOwner> owners = new ArrayList<OdsOwner>();
    			owners.add(dog);
    			
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
	                    logger.error("Received an UNKNOWN event from the ods owner service of type {}", action);
	                    break;      
	    		}
    		}
        }
        finally{
        }
    	
    }
    
    public OdsOwner getOwnerByIdDog(int dogId){
        try {
        	return ownerRepository.findByIdDog(dogId);
        }
        finally{
        }
    }   
    
    public void refreshTitle(OdsTitle title, String action){
    	try {

    		if ( title != null ) {
	    		switch(action){
	            	case "U":
	            		simpleSourceBean.publishTitleChange("UPDATE", title);
	            		break;
	            	case "I":
	            		simpleSourceBean.publishTitleChange("SAVE", title);
	            		break;
	            	case "D":
	            		simpleSourceBean.publishTitleChange("DELETE", title);
	            		break;
	                default:
	                    logger.error("Received an UNKNOWN event from the ods title service of type {}", action);
	                    break;      
	    		}
    		}
        }
        finally{
        }
    	
    }
    
    public OdsTitle getTitleById(long id){
        try {
        	return titleRepository.findById(id);
        }
        finally{
        }
    }    
    
    public void refreshPedigree(OdsPedigree pedigree, String action){
    	try {

    		if (pedigree != null) {
	    		switch(action){
	            	case "U":
	            		simpleSourceBean.publishPedigreeChange("UPDATE", pedigree);
	            		break;
	            	case "I":
	            		simpleSourceBean.publishPedigreeChange("SAVE", pedigree);
	            		break;
	            	case "D":
	            		simpleSourceBean.publishPedigreeChange("DELETE", pedigree);
	            		break;
	                default:
	                    logger.error("Received an UNKNOWN event from the ods pedigree service of type {}", action);
	                    break;      
	    		}
    		}
        }
        finally{
        }
    	
    }
        
    public OdsPedigree getPedigreeById(long id){
        try {
        	return pedigreeRepository.findById(id);
        }
        finally{
        }
    }        
    
    public void refreshParent(OdsParent dog, String action){
    	try {

    		if (dog != null) {
	    		switch(action){
	            	case "U":
	            		simpleSourceBean.publishParentChange("UPDATE", dog);
	            		break;
	            	case "I":
	            		simpleSourceBean.publishParentChange("SAVE", dog);
	            		break;
	            	case "D":
	            		simpleSourceBean.publishParentChange("DELETE", dog);
	            		break;
	                default:
	                    logger.error("Received an UNKNOWN event from the ods pedigree service of type {}", action);
	                    break;      
	    		}
    		}
        }
        finally{
        }
    	
    }
            
    public OdsParent getParentByIdDog(int dogId){
    	
        try {
        	return parentRepository.findById(dogId);
        }
        finally{
        }
    }     
    
    public void deleteDog (OdsSyncData dog) {
        try {
        	odsDataRepository.delete(dog);
        }
        finally{
        }
    }

    
    public void syncDogInfos() {
    	
    	List<OdsSyncData> dogList = new ArrayList<OdsSyncData>();
    	long id = 0;
    	
        try {

        	dogList = getAllDogs();
	    	if (dogList.size() > 0 ) {

	    		logger.debug("syncDogInfos :: dogList {}", dogList.size());

	    		// [[Boucle]] s/ le chien
		    	for (OdsSyncData syncDog : dogList) {
		    		
		            try {
		    				
		    	    	// 1. Maj du chien de la table (ODS_SYNC_DATA)
		            	id = syncDog.getId();
		    			syncDog.setTransfert("O");
		    			saveDog(syncDog);
		    			
		    	    	// 2. Lecture des infos pour le chien à synchroniser 
		    			// Note : vue ODS_CHIEN (Oracle) == image de la table ODS_CHIEN (PostGRE)
		    			// Si UPDATE/INSERT et dog == null alors le chien n'est pas dans le périmètre -> on le supprime de la liste
		    			// + DELETE, dog == null -> on publie uniquement l'id à supprimer
		    			OdsDog dog = new OdsDog();
		    			if (!syncDog.getAction().equals("D")) {
		    				dog = getDogById(id);
		    				if (dog == null ) {
		    					deleteDog(syncDog);
		    					continue;		    	
		    				}		    			
		    			} else
		    				dog.withId(id);	
		    			
		    	    	// 3. Envoi du message à ods-service pour maj Postgre
		    			// Pour le chien lui-même, ses titres, ses livres, ses parents, son proprietaire ?	
		    			refreshDog(dog, syncDog.getAction());
		    			
		    		} catch (Exception e) {
		    			logger.error(" id {} : {}", id, e.getMessage());
		    		} finally {
		    		}
		    	}	    	
		    	// [[Boucle]]

	    	}

        }
        finally{
	    	dogList.clear();
        }
    	
    }
    
    public void syncBreederInfos() {

    	List<OdsSyncData> dogList = new ArrayList<OdsSyncData>();
    	int idDog = 0;
    	
        try {

	    	dogList = getAllBreeders();
	    	if (dogList.size() > 0 ) {


	    		logger.debug("syncBreederInfos :: dogList {}", dogList.size());

	    		// [[Boucle]] s/ le chien
		    	for (OdsSyncData syncDog : dogList) {

		            try {

		    	    	// 1. Maj du chien de la table (ODS_SYNC_DATA)
		    			idDog = (int) syncDog.getId();
		    			syncDog.setTransfert("O");
		    			saveDog(syncDog);
	
		    	    	// 2. Lecture des infos pour le chien à synchroniser 
		    			// Note : vue ODS_ELEVEUR (Oracle) == image de la table ODS_ELEVEUR (PostGRE)
		    			// Si UPDATE/INSERT et dog == null alors le chien n'est pas dans le périmètre -> on le supprime de la liste
		    			// + DELETE, dog == null -> on publie uniquement l'id à supprimer
		    			OdsBreeder breeder = new OdsBreeder();
		    			if (!syncDog.getAction().equals("D")) {
		    				breeder = getBreederByIdDog(idDog);
		    				if (breeder == null ) {
		    					deleteDog(syncDog);
		    					continue;		    	
		    				}		    			
		    			} else
		    				breeder.withIdDog(idDog);	
		    			
		    	    	// 3. Envoi du message à ods-service pour maj Postgre
		    			refreshBreeder(breeder, syncDog.getAction());
		    			
		    		} catch (Exception e) {
		    			logger.error(" idDog {} : {}", idDog, e.getMessage());
		    		} finally {
		    		}
		    	}	    	
		    	// [[Boucle]]

	    	}

        }
        finally{
	    	dogList.clear();
        }
        	
    }
    
    
    public void syncOwnerInfos() {
    
    	List<OdsSyncData> dogList = new ArrayList<OdsSyncData>();
    	int idDog = 0;
    	
        try {
        	
	    	dogList = getAllOwners();
	    	if (dogList.size() > 0 ) {

	    		logger.debug("getAllOwners :: dogList {}", dogList.size());

	    		// [[Boucle]] s/ le chien
		    	for (OdsSyncData syncDog : dogList) {

		            try {

		    	    	// 1. Maj du chien de la table (ODS_SYNC_DATA)
		    			idDog = (int) syncDog.getId();
		    			syncDog.setTransfert("O");
		    			saveDog(syncDog);
	
		    	    	// 2. Lecture des infos pour le chien à synchroniser 
		    			// Note : vue ODS_PROPRIETAIRE (Oracle) == image de la table ODS_PROPRIETAIRE (PostGRE)
		    			// Si UPDATE/INSERT et dog == null alors le chien n'est pas dans le périmètre -> on le supprime de la liste
		    			// + DELETE, dog == null -> on publie uniquement l'id à supprimer
		    			OdsOwner owner = new OdsOwner();
		    			if (!syncDog.getAction().equals("D")) {
		    				owner = getOwnerByIdDog(idDog);
		    				if (owner == null ) {
		    					deleteDog(syncDog);
		    					continue;		    	
		    				}		    			
		    			} else
		    				owner.withId(idDog);	
		    			
		    	    	// 3. Envoi du message à ods-service pour maj Postgre
		    			refreshOwner(owner, syncDog.getAction());
		    			
		    		} catch (Exception e) {
		    			logger.error(" idDog {} : {}", idDog, e.getMessage());
		    		} finally {
		    		}
		    	}	    	
		    	// [[Boucle]]

	    	}
        	
        }
        finally{
	    	dogList.clear();
        }
        	
    }
    
    public void syncTitleInfos() {
    	
    	List<OdsSyncData> titles = new ArrayList<OdsSyncData>();
    	long id = 0;
    	String country ="";
    	
        try {
            	
        	titles = getAllTitles();
	    	if (titles.size() > 0 ) {

	    		logger.debug("getAllTitles :: titles {}", titles.size());

	    		// [[Boucle]] s/ les titres
		    	for (OdsSyncData syncTitle : titles) {

		            try {

		    	    	// 1. Maj du titre de la table (ODS_SYNC_DATA)
		            	id = syncTitle.getId();
		            	country = (syncTitle.getDomaine().equals("TITRE_FRANCAIS") ? "FR" : "ETR");
		            	syncTitle.setTransfert("O");
		    			saveDog(syncTitle);
	
		    	    	// 2. Lecture des infos pour le chien à synchroniser 
		    			// Note : vue ODS_TITRES (Oracle) == image de la table ODS_TITRES (PostGRE)
		    			// Si UPDATE/INSERT et dog == null alors le chien n'est pas dans le périmètre -> on le supprime de la liste
		    			// + DELETE, dog == null -> on publie uniquement l'id à supprimer
		    			OdsTitle title = new OdsTitle();
		    			if (!syncTitle.getAction().equals("D")) {
		    				title = getTitleById(id);
		    				if (title == null) {
		    					deleteDog(syncTitle);
		    					continue;		    	
		    				}		    			
		    			} else
		    				title.withId(id);	
		    			
		    	    	// 3. Envoi du message à ods-service pour maj Postgre
		    			refreshTitle(title, syncTitle.getAction());
		    			
		    		} catch (Exception e) {
		    			logger.error(" id {} : {}", id, e.getMessage());
		    		} finally {
		    		}
		    	}	    	
		    	// [[Boucle]]

	    	}
        	
        }
        finally{
        	titles.clear();
        }

    }  
    
    public void syncPedigreeInfos(){
    	
    	List<OdsSyncData> pedigrees = new ArrayList<OdsSyncData>();
    	long id = 0;
    	
        try {

        	pedigrees = getAllPedigree();
	    	if (pedigrees.size() > 0 ) {

	    		logger.debug("getAllPedigree :: pedigrees {}", pedigrees.size());

	    		// [[Boucle]] s/ le chien
		    	for (OdsSyncData syncPedigree : pedigrees) {

		            try {

		    	    	// 1. Maj du chien de la table (ODS_SYNC_DATA)
		            	id = syncPedigree.getId();
		    			syncPedigree.setTransfert("O");
		    			saveDog(syncPedigree);
	
		    	    	// 2. Lecture des infos pour le chien à synchroniser 
		    			// Note : vue ODS_LIVRES (Oracle) == image de la table ODS_LIVRES (PostGRE)
		    			// Si UPDATE/INSERT et dog == null alors le chien n'est pas dans le périmètre -> on le supprime de la liste
		    			// + DELETE, dog == null -> on publie uniquement l'id à supprimer
		    			OdsPedigree pedigree = new OdsPedigree();
		    			if (!syncPedigree.getAction().equals("D")) {
		    				pedigree = getPedigreeById(id);
		    				if (pedigree == null) {
		    					deleteDog(syncPedigree);
		    					continue;		    	
		    				}		    			
		    			} else
		    				pedigree.withId(id);	
		    			
		    	    	// 3. Envoi du message à ods-service pour maj Postgre
		    			refreshPedigree(pedigree, syncPedigree.getAction());
		    			
		    		} catch (Exception e) {
		    			logger.error(" id {} : {}", id, e.getMessage());
		    		} finally {
		    		}
		    	}	    	
		    	// [[Boucle]]

	    	}
        	
        
        }
        finally{
        	pedigrees.clear();
        }

    }

    public void syncParentInfos(){
    	
    	List<OdsSyncData> dogList = new ArrayList<OdsSyncData>();
    	int idDog = 0;
    	
        try {

	    	dogList = getAllParent();
	    	if (dogList.size() > 0 ) {

	    		logger.debug("getAllParent :: dogList {}", dogList.size());

	    		// [[Boucle]] s/ le chien
		    	for (OdsSyncData syncDog : dogList) {

		            try {

		    	    	// 1. Maj du chien de la table (ODS_SYNC_DATA)
		    			idDog = (int) syncDog.getId();
		    			syncDog.setTransfert("O");
		    			saveDog(syncDog);
	
		    	    	// 2. Lecture des infos pour le chien à synchroniser 
		    			// Note : vue ODS_GENITEURS (Oracle) == image de la table ODS_GENITEURS (PostGRE)
		    			// Si UPDATE/INSERT et dog == null alors le chien n'est pas dans le périmètre -> on le supprime de la liste
		    			// + DELETE, dog == null -> on publie uniquement l'id à supprimer
		    			OdsParent parent = new OdsParent();
		    			if (!syncDog.getAction().equals("D")) {
		    				parent = getParentByIdDog(idDog);
		    				if (parent == null ) {
		    					deleteDog(syncDog);
		    					continue;		    	
		    				}		    			
		    			} else
		    				parent.withId(idDog);	
		    			
		    	    	// 3. Envoi du message à ods-service pour maj Postgre
		    			refreshParent(parent, syncDog.getAction());
		    			
		    		} catch (Exception e) {
		    			logger.error(" idDog {} : {}", idDog, e.getMessage());
		    		} finally {
		    		}
		    	}	    	
		    	// [[Boucle]]

	    	}
        	
        
        }
        finally{
	    	dogList.clear();
        }

    }
    
}

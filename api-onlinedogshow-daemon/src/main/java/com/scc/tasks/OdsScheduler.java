package com.scc.tasks;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.scc.daemon.model.OdsBreeder;
import com.scc.daemon.model.OdsOwner;
import com.scc.daemon.model.OdsSyncData;
import com.scc.daemon.services.OdsPersonService;
import com.scc.daemon.services.OdsDogService;

@Component
public class OdsScheduler {
    
	private static final Logger logger = LoggerFactory.getLogger(OdsScheduler.class);
	
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	@Autowired
	OdsDogService odsDogService;

	@Autowired
	OdsPersonService odsPersonService;

	@Scheduled (fixedDelayString = "${fixedDelay.in.milliseconds}")
    public void synchronizeDog() {

		try {
        
	    	// I. Lecture dans la table demande de synchro pour l'ensemble des chiens sur lesquels 1 maj est demandée
	    	odsDogService.syncDogInfos();
	    	
	    	// II. Lecture dans la table demande de synchro pour l'ensemble des éleveurs sur lesquels 1 maj est demandée
	    	odsDogService.syncBreederInfos();
	    	
	    	// III. Lecture dans la table demande de synchro pour l'ensemble des propriétaires sur lesquels 1 maj est demandée
	    	odsDogService.syncOwnerInfos();
	    	
	    	// IV. Lecture dans la table demande de synchro pour l'ensemble des titres sur lesquels 1 maj est demandée
	    	odsDogService.syncTitleInfos();
	    	
	    	// V. Lecture dans la table demande de synchro pour l'ensemble des livres sur lesquels 1 maj est demandée
	    	odsDogService.syncPedigreeInfos();

	    	// VI. Lecture dans la table demande de synchro pour l'ensemble des géniteurs sur lesquels 1 maj est demandée
	    	odsDogService.syncParentInfos();
	    	
		} catch (Exception e) {
			logger.error(" synchronizeDog : {}", e.getMessage());
		} finally {
		}
    }

	@Scheduled (fixedDelayString = "${fixedDelay.in.milliseconds}")
    public void synchronizePerson() {

		try {
        
	    	//logger.debug("readDog :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
	    	List<OdsSyncData> personList = new ArrayList<OdsSyncData>();
	    	int idPerson = 0;
	    	
	    	// 0. Lecture dans la table demande de synchro pour l'ensemble des personnes (eleveurs et propriétaires) sur lesquelles 1 maj est demandée
	    	personList = odsPersonService.getAllPersons();
	    	
	    	if (personList.size() > 0 ) {

		    	logger.debug("getAllPersons :: personList {}", personList.size());

		    	// [[Boucle]] s/ la personne
		    	for (OdsSyncData syncPers : personList) {
		    		
		            try {
		    				
		    	    	// 1. Maj du chien de la table (ODS_SYNC_PERSONNE)
		    			idPerson = (int) syncPers.getId();
		    			syncPers.setTransfert("O");
		    			odsPersonService.savePerson(syncPers);
		    			
		    	    	// 2. Lecture des infos pour l'éleveur/propriétaire à synchroniser 

			            // PARTIE 1. Info ELEVEUR
		    			// Note : vue ODS_ELEVEUR (Oracle) == image de la table ODS_ELEVEUR (PostGRE)
		    			// Si UPDATE/INSERT et breeder == null alors l'éleveur n'est pas dans le périmètre -> on le supprime de la liste
		    			// + DELETE, breeder == null -> on publie uniquement l'id à supprimer
		    			List<OdsBreeder> breeders = new ArrayList<OdsBreeder>();
		    			if (!syncPers.getAction().equals("D"))
		    				breeders = odsPersonService.getBreederById(idPerson);
		    			else
		    				breeders.add(new OdsBreeder().withId(idPerson));
		    				
		    	    	// Envoi du message à ods-service pour maj Postgre
	    				if (breeders != null && breeders.size() > 0 )
	    					odsPersonService.refreshBreeder(breeders, syncPers.getAction());
			    			
			            // PARTIE 2. Info PROPRIETAIRE
		    			// Note : vue ODS_PROPRIETAIRE (Oracle) == image de la table ODS_PROPRIETAIRE (PostGRE)
		    			// Si UPDATE/INSERT et owner == null alors le propriétaire n'est pas dans le périmètre -> on le supprime de la liste
		    			// + DELETE, owner == null -> on publie uniquement l'id à supprimer
	    				List<OdsOwner> owners = new ArrayList<OdsOwner>();
		    			if (!syncPers.getAction().equals("D"))
		    				owners = odsPersonService.getOwnerById(idPerson);
		    			else
		    				owners.add(new OdsOwner().withId(idPerson));
		    			
		    	    	// Envoi du message à ods-service pour maj Postgre
	    				if (owners != null && owners.size() > 0 )
	    					odsPersonService.refreshOwner(owners, syncPers.getAction());
	    				
	    				if ( (owners == null  && owners.size() == 0 ) && (breeders == null && breeders.size() == 0))
	    					odsPersonService.deletePerson(syncPers);
		    			
		    		} catch (Exception e) {
		    			logger.error(" idPerson {} : {}", idPerson, e.getMessage());
		    		} finally {
		    		}

		    	}	    	
		    	// [[Boucle]]
	    	}
		
		} finally {
		}
    }

}

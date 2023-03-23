package com.scc.events.source;

import java.time.Instant;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;

import com.scc.daemon.model.OdsBreeder;
import com.scc.daemon.model.OdsDog;
import com.scc.daemon.model.OdsOwner;
import com.scc.daemon.model.OdsParent;
import com.scc.daemon.model.OdsPedigree;
import com.scc.daemon.model.OdsTitle;
import com.scc.events.CustomChannels;
import com.scc.events.models.OdsBreederChangeModel;
import com.scc.events.models.OdsDogChangeModel;
import com.scc.events.models.OdsOwnerChangeModel;
import com.scc.events.models.OdsParentChangeModel;
import com.scc.events.models.OdsPedigreeChangeModel;
import com.scc.events.models.OdsTitleChangeModel;

@EnableBinding(CustomChannels.class)
public class SimpleSourceBean {

    private static final Logger logger = LoggerFactory.getLogger(SimpleSourceBean.class);

    @Autowired
    private CustomChannels customChannels;

    @SendTo("outboundBreederChanges")
    public void publishBreederChange(String action, List<OdsBreeder> breeders){

    	Instant instant = Instant.now();
    	try {
	       
    		for (OdsBreeder breeder : breeders) {
    	        logger.debug("Sending PubSub message {} for Ods Breeder Id: {} at {} ", action, breeder.toString(), instant);
	    
    	        OdsBreederChangeModel change =  new OdsBreederChangeModel(
		    		   OdsBreederChangeModel.class.getTypeName(),
		                action,
		                breeder,
		                "x1",
		                instant.toEpochMilli());
		
	    		customChannels.outputBreeder().send(MessageBuilder.withPayload(change).build());
    		}
        }
        finally{
        }
    	
    }

    @SendTo("outboundOwnerChanges")
    public void publishOwnerChange(String action, List<OdsOwner> owners){
    	Instant instant = Instant.now();

    	try {
	       
    		for (OdsOwner owner : owners) {
    	        logger.debug("Sending PubSub message {} for Ods Owner Id: {} at {} ", action, owner.toString(), instant);

    	        OdsOwnerChangeModel change =  new OdsOwnerChangeModel(
		    		   OdsOwnerChangeModel.class.getTypeName(),
		                action,
		                owner,
		                "x2",
		                instant.toEpochMilli());
		
		    	customChannels.outputOwner().send(MessageBuilder.withPayload(change).build());
    		}
        }
        finally{
        }
    	
    }

    @SendTo("outboundDogChanges")
    public void publishDogChange(String action, OdsDog dog){
    	Instant instant = Instant.now();
        logger.debug("Sending PubSub message {} for Ods Dog Id: {} at {} ", action, dog.toString(), instant);

    	try {
	       
	    	OdsDogChangeModel change =  new OdsDogChangeModel(
	    		   OdsDogChangeModel.class.getTypeName(),
	                action,
	                dog,
	                "x3",
	                instant.toEpochMilli());
	
	    	customChannels.outputDog().send(MessageBuilder.withPayload(change).build());
    
        }
        finally{
        }
    	
    }
    
    @SendTo("outboundTitleChanges")
    public void publishTitleChange(String action, OdsTitle title){
    	Instant instant = Instant.now();

    	try {
    		
	        logger.debug("Sending PubSub message {} for Ods Dog Id: {} at {} ", action, title.toString(), instant);
	        
	        OdsTitleChangeModel change =  new OdsTitleChangeModel(
    			OdsTitleChangeModel.class.getTypeName(),
                action,
                title,
                "x5",
                instant.toEpochMilli());

	        customChannels.outputTitle().send(MessageBuilder.withPayload(change).build());
    
        }
        finally{
        }
    	
    }    
    
    @SendTo("outboundPedigreeChanges")
    public void publishPedigreeChange(String action, OdsPedigree pedigree){
    	Instant instant = Instant.now();

    	try {
	       
	        logger.debug("Sending PubSub message {} for Ods Dog Id: {} at {} ", action, pedigree.toString(), instant);

	        OdsPedigreeChangeModel change =  new OdsPedigreeChangeModel(
	    			OdsPedigreeChangeModel.class.getTypeName(),
	                action,
	                pedigree,
	                "x6",
	                instant.toEpochMilli());
	
	    	customChannels.outputPedigree().send(MessageBuilder.withPayload(change).build());
    
        }
        finally{
        }
    	
    }
    
    @SendTo("outboundParentChanges")
    public void publishParentChange(String action, OdsParent parent){
    	Instant instant = Instant.now();

    	try {
	       
	        logger.debug("Sending PubSub message {} for Ods Owner Id: {} at {} ", action, parent.toString(), instant);

	        OdsParentChangeModel change =  new OdsParentChangeModel(
	        		OdsParentChangeModel.class.getTypeName(),
	                action,
	                parent,
	                "x7",
	                instant.toEpochMilli());
	
	    	customChannels.outputParent().send(MessageBuilder.withPayload(change).build());

        }
        finally{
        }
    	
    }    
}

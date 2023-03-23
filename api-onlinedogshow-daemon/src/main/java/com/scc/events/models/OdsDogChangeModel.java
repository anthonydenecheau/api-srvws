package com.scc.events.models;

import com.scc.daemon.model.OdsDog;

public class OdsDogChangeModel {

    private String type;
    private String action;
    private OdsDog dog;
    private String traceId;
    private long timestamp;

    public  OdsDogChangeModel(String type, String action, OdsDog dog, String traceId, long timestamp) {
        super();
        this.type   = type;
        this.action = action;
        this.dog = dog;
        this.traceId = traceId;
        this.timestamp = timestamp;
    }

	public String getType() { return type; }
    public void setType(String type) { this.type = type;}

    public String getAction() { return action;}
    public void setAction(String action) {this.action = action; }

    public OdsDog getDog() { return dog; }
    public void setDog(OdsDog dog) { this.dog = dog;}

    public String getTraceId() { return traceId; }
	public void setTraceId(String traceId) { this.traceId = traceId; }

	public long getTimestamp() { return timestamp; }
	public void setTimestamp(long timestamp) { this.timestamp = timestamp; }

}

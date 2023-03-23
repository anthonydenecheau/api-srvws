package com.scc.events.models;

import com.scc.daemon.model.OdsPedigree;

public class OdsPedigreeChangeModel {

    private String type;
    private String action;
    private OdsPedigree pedigree;
    private String traceId;
    private long timestamp;

    public  OdsPedigreeChangeModel(String type, String action, OdsPedigree pedigree, String traceId, long timestamp) {
        super();
        this.type   = type;
        this.action = action;
        this.pedigree = pedigree;
        this.traceId = traceId;
        this.timestamp = timestamp;
    }

	public String getType() { return type; }
    public void setType(String type) { this.type = type;}

    public String getAction() { return action;}
    public void setAction(String action) {this.action = action; }

    public OdsPedigree getPedigree() { return pedigree; }
    public void setPedigree(OdsPedigree pedigree) { this.pedigree = pedigree;}

    public String getTraceId() { return traceId; }
	public void setTraceId(String traceId) { this.traceId = traceId; }

	public long getTimestamp() { return timestamp; }
	public void setTimestamp(long timestamp) { this.timestamp = timestamp; }

}

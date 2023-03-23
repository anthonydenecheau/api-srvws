package com.scc.events.models;

import com.scc.daemon.model.OdsParent;

public class OdsParentChangeModel {

    private String type;
    private String action;
    private OdsParent parent;
    private String traceId;
    private long timestamp;

    public  OdsParentChangeModel(String type, String action, OdsParent parent, String traceId, long timestamp) {
        super();
        this.type   = type;
        this.action = action;
        this.parent = parent;
        this.traceId = traceId;
        this.timestamp = timestamp;
    }

	public String getType() { return type; }
    public void setType(String type) { this.type = type;}

    public String getAction() { return action;}
    public void setAction(String action) {this.action = action; }

    public OdsParent getParent() { return parent; }
    public void setParent(OdsParent parent) { this.parent = parent;}

    public String getTraceId() { return traceId; }
	public void setTraceId(String traceId) { this.traceId = traceId; }

	public long getTimestamp() { return timestamp; }
	public void setTimestamp(long timestamp) { this.timestamp = timestamp; }

}

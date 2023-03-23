package com.scc.events.models;

import com.scc.daemon.model.OdsTitle;

public class OdsTitleChangeModel {

    private String type;
    private String action;
    private OdsTitle title;
    private String traceId;
    private long timestamp;

    public  OdsTitleChangeModel(String type, String action, OdsTitle title, String traceId, long timestamp) {
        super();
        this.type   = type;
        this.action = action;
        this.title = title;
        this.traceId = traceId;
        this.timestamp = timestamp;
    }

	public String getType() { return type; }
    public void setType(String type) { this.type = type;}

    public String getAction() { return action;}
    public void setAction(String action) {this.action = action; }

    public OdsTitle getTitle() { return title; }
    public void setTitle(OdsTitle title) { this.title = title;}

    public String getTraceId() { return traceId; }
	public void setTraceId(String traceId) { this.traceId = traceId; }

	public long getTimestamp() { return timestamp; }
	public void setTimestamp(long timestamp) { this.timestamp = timestamp; }

}

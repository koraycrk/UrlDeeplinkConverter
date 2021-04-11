package com.Url.Deeplink.Converter.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Transaction {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;

  private int custNo;
  private String transactionName;
  private int contentId;
  private int flowId;
  
public int getCustNo() {
	return custNo;
}

public void setCustNo(int custNo) {
	this.custNo = custNo;
}	

public String getTransactionName() {
	return transactionName;
}

public void setTransactionName(String transactionName) {
	this.transactionName = transactionName;
}

public int getContentId() {
	return contentId;
}

public void setContentId(int contentId) {
	this.contentId = contentId;
}

public int getFlowId() {
	return flowId;
}

public void setFlowId(int flowId) {
	this.flowId = flowId;
}

public String getFlowName() {
	return flowName;
}

public void setFlowName(String flowName) {
	this.flowName = flowName;
}

private String flowName;
  

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

 
}

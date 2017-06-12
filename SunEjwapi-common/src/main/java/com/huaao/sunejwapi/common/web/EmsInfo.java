package com.huaao.sunejwapi.common.web;

public class EmsInfo {

	private String txLogisticID;
	
	private EmsAddress collect;
	
	private EmsAddress sender;
	
	private EmsAddress receiver;
	
	private int orderType;

	public String getTxLogisticID() {
		return txLogisticID;
	}

	public void setTxLogisticID(String txLogisticID) {
		this.txLogisticID = txLogisticID;
	}

	public EmsAddress getCollect() {
		return collect;
	}

	public void setCollect(EmsAddress collect) {
		this.collect = collect;
	}

	public EmsAddress getSender() {
		return sender;
	}

	public void setSender(EmsAddress sender) {
		this.sender = sender;
	}

	public EmsAddress getReceiver() {
		return receiver;
	}

	public void setReceiver(EmsAddress receiver) {
		this.receiver = receiver;
	}

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}
	
	
}

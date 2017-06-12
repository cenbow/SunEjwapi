package com.huaao.sunejwapi.api.dto.residence;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.huaao.sunejwapi.api.dto.pub.DtoPublicRequest;
import com.huaao.sunejwapi.common.web.ApiDemoData;
import com.huaao.sunejwapi.common.web.ApiDescription;

public class DtoResidenceAppraiseRequest extends DtoPublicRequest{

	@ApiDescription("落户表数据ID")
	@ApiDemoData("12101")
    private Integer id;
	
	@ApiDescription("评价_服务态度")
	@ApiDemoData("5")
    private Integer apprasieAttitude;

	@ApiDescription("评价_按期办理")
	@ApiDemoData("5")
    private Integer apprasieDate;

	@ApiDescription("评价_着装规范")
	@ApiDemoData("5")
    private Integer apprasieDressing;

	@ApiDescription("评价_整体：0 非常满意, 1 满意 , 2 不满意")
	@ApiDemoData("1")
	@NotNull(message="请选择整体评价")
    private Integer apprasieTotal;

	@ApiDescription("原因")
	@ApiDemoData("")
	@Length(max=200,message="字数不能超过200")
    private String appraiseReason;
	
	@ApiDescription("建议")
	@ApiDemoData("")
	@Length(max=200,message="字数不能超过200字")
    private String advice;

	@ApiDescription("处理人员警号")
	@ApiDemoData("J12345")
	@Length(max=10,message="字数不能超过10")
    private String policeCard;
	
	
	public String getPoliceCard() {
		return policeCard;
	}

	public void setPoliceCard(String policeCard) {
		this.policeCard = policeCard;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getApprasieAttitude() {
		return apprasieAttitude;
	}

	public void setApprasieAttitude(Integer apprasieAttitude) {
		this.apprasieAttitude = apprasieAttitude;
	}

	public Integer getApprasieDate() {
		return apprasieDate;
	}

	public void setApprasieDate(Integer apprasieDate) {
		this.apprasieDate = apprasieDate;
	}

	public Integer getApprasieDressing() {
		return apprasieDressing;
	}

	public void setApprasieDressing(Integer apprasieDressing) {
		this.apprasieDressing = apprasieDressing;
	}

	public Integer getApprasieTotal() {
		return apprasieTotal;
	}

	public void setApprasieTotal(Integer apprasieTotal) {
		this.apprasieTotal = apprasieTotal;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	public String getAppraiseReason() {
		return appraiseReason;
	}

	public void setAppraiseReason(String appraiseReason) {
		this.appraiseReason = appraiseReason;
	}
	
	
}

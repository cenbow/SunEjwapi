package com.huaao.sunejwapi.api.dto.residence;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.huaao.sunejwapi.api.dto.pub.DtoPublicRequest;
import com.huaao.sunejwapi.common.web.ApiDemoData;
import com.huaao.sunejwapi.common.web.ApiDescription;

public class DtoResidenceAppointmentRequest extends DtoPublicRequest{
	
	@ApiDescription("落户表数据ID")
	@ApiDemoData("12101")
	@NotNull
    private Integer id;

	@ApiDescription("预约日期:2017-05-26")
	@ApiDemoData("2017-05-26")
	@NotBlank
    private String appointmentDate;

	@ApiDescription("预约时段 08:00-09:00")
	@ApiDemoData("08:00-09:00")
	@NotBlank
    private String appointmentTime;
	
	
	public String getAppointmentDate() {
        return appointmentDate;
    }

    
    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    
    public String getAppointmentTime() {
        return appointmentTime;
    }

    
    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

    
}

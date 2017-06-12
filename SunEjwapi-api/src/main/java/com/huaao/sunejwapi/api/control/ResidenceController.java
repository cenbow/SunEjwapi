package com.huaao.sunejwapi.api.control;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huaao.sunejwapi.api.dto.pub.DtoPublicRequest;
import com.huaao.sunejwapi.api.dto.pub.DtoPublicResponse;
import com.huaao.sunejwapi.api.dto.pub.DtoPublicResponseList;
import com.huaao.sunejwapi.api.dto.pub.DtoPublicResponseObj;
import com.huaao.sunejwapi.api.dto.residence.DtoResidenceAppointmentRequest;
import com.huaao.sunejwapi.api.dto.residence.DtoResidenceAppraiseRequest;
import com.huaao.sunejwapi.api.dto.residence.DtoResidenceConfirmDoneRequest;
import com.huaao.sunejwapi.api.dto.residence.DtoResidenceCreateRequest;
import com.huaao.sunejwapi.api.dto.residence.DtoResidenceQueryAppointmentDateRequest;
import com.huaao.sunejwapi.api.dto.residence.ResidenceAppointmentDate;
import com.huaao.sunejwapi.api.dto.residence.ResidenceInfo;
import com.huaao.sunejwapi.api.util.ResidenceAppointmentDateUtil;
import com.huaao.sunejwapi.common.util.DateTool;
import com.huaao.sunejwapi.common.util.FuncTool;
import com.huaao.sunejwapi.common.web.ApiDescription;
import com.huaao.sunejwapi.common.web.SystemException;
import com.huaao.sunejwapi.dao.JwUser;
import com.huaao.sunejwapi.dao.SpsResidence;
import com.huaao.sunejwapi.dao.SpsSDictionary;
import com.huaao.sunejwapi.mapper.JwUserMapper;
import com.huaao.sunejwapi.service.DictService;
import com.huaao.sunejwapi.service.ResidenceService;

@ApiDescription("落户信息")
@Controller
@RequestMapping("apis-sun/residence")
public class ResidenceController {
	@Autowired
	private ResidenceService residenceService;
	@Autowired
	private JwUserMapper jwUserMapper;
	@Autowired
	private DictService dictService;
	
	@ApiDescription("落户信息查询;负责人:fuwei")
	@RequestMapping("query")
	@ResponseBody
	public DtoPublicResponseList<ResidenceInfo> query(@Valid DtoPublicRequest dtoRequest) throws Exception {
		DtoPublicResponseList<ResidenceInfo> dtoResponse = new DtoPublicResponseList<>();
		List<SpsResidence> list = residenceService.query(dtoRequest.getUidInt());
		List<ResidenceInfo> listInfo = FuncTool.copyPropertiesList(list, ResidenceInfo.class);
		for(ResidenceInfo item : listInfo){
			
			if(!StringUtils.isBlank(item.getRejectReason()) ){
				//驳回类型: 0 材料不全， 1 不符合政策， 2 其他
				if("材料不全".equals(item.getRejectReason().trim())){
					item.setRejectType(0);
				}
				else if("不符合政策".equals(item.getRejectReason().trim())){
					item.setRejectType(1);
				}
				else{
					item.setRejectType(2);
				}
			}
		}
		fillResidenceInfoData(listInfo.toArray(new ResidenceInfo[listInfo.size()]));
		dtoResponse.setData(listInfo);
		return dtoResponse;
	}

	
	@ApiDescription("落户信息确认办理;负责人:lihao")
	@RequestMapping("confirmDone")
	@ResponseBody
	public DtoPublicResponse confirmDone(@Valid DtoResidenceConfirmDoneRequest dtoRequest) throws Exception {
		DtoPublicResponse dtoResponse = new DtoPublicResponse();
		residenceService.confirmDone(Integer.parseInt(dtoRequest.getId()));
		return dtoResponse;
	}
	
	@ApiDescription("落户信息创建;负责人:fuwei")
	@RequestMapping("createAndUpdate")
	@ResponseBody
	public DtoPublicResponseObj<ResidenceInfo> createAndUpdate(@Valid DtoResidenceCreateRequest dtoRequest) throws Exception {
		DtoPublicResponseObj<ResidenceInfo> dtoResponse = new DtoPublicResponseObj<>();
		SpsResidence spsResidence = FuncTool.copyPropertiesClass(dtoRequest, SpsResidence.class);
		spsResidence.setCreatorId(dtoRequest.getUidInt());
		spsResidence.setUpdaterId(dtoRequest.getUidInt());
		spsResidence.setUpdateTime(DateTool.nowTimeStap()/1000);
		spsResidence.setCreateTime(DateTool.nowTimeStap()/1000);
		spsResidence.setStatus(1);
		JwUser jwUser = jwUserMapper.selectByPrimaryKey(dtoRequest.getUidInt());
		if(jwUser != null){
			spsResidence.setCommunityid(jwUser.getCommunityid());
		}
		residenceService.createAndUpdate(spsResidence);
		ResidenceInfo info = FuncTool.copyPropertiesClass(spsResidence, ResidenceInfo.class);
		fillResidenceInfoData(info);
		dtoResponse.setData(info);
		return dtoResponse;
	}

	private void fillResidenceInfoData(ResidenceInfo ... listInfo ){
		List<SpsSDictionary> listStation = dictService.findByParentCode("residenceStation");
		List<SpsSDictionary> listType = dictService.findByParentCode("residenceType");
		for(ResidenceInfo obj : listInfo){
			if(obj.getStationCode() != null && obj.getStationCode() == 0){
				obj.setStationName("江岸区公安分局户政服务中心（江岸区胜利街360号）二楼审批");
			}
			for(SpsSDictionary item : listStation){
				if(obj.getStationCode() != null && obj.getStationCode() == Integer.parseInt(item.getDictionaryCode()) ){
					obj.setStationName(item.getDictionaryName()+"户籍室");
				}
			}
			for(SpsSDictionary item : listType){
				if(obj.getCatelog() != null && obj.getCatelog() == Integer.parseInt(item.getDictionaryCode()) ){
					obj.setCatelogName(item.getDictionaryName()+"户籍室");
				}
			}
		}
	}
	@ApiDescription("落户信息评价;负责人:luping")
	@RequestMapping("appraise")
	@ResponseBody
	public DtoPublicResponse appraise(@Valid DtoResidenceAppraiseRequest dtoRequest) throws Exception {
		DtoPublicResponse dtoResponse = new DtoPublicResponse();
		if (dtoRequest.getId() != null && dtoRequest.getApprasieAttitude() != null && dtoRequest.getApprasieDate() != null
				&& dtoRequest.getApprasieDressing() != null && dtoRequest.getApprasieTotal() != null) {
			SpsResidence residence = new SpsResidence();
			residence.setId(dtoRequest.getId());
			residence.setApprasieAttitude(dtoRequest.getApprasieAttitude());
			residence.setApprasieDate(dtoRequest.getApprasieDate());
			residence.setApprasieDressing(dtoRequest.getApprasieDressing());
			residence.setApprasieTotal(dtoRequest.getApprasieTotal());
			residence.setAdvice(dtoRequest.getAdvice());
			residence.setAppraiseReason(dtoRequest.getAppraiseReason());
			residence.setPoliceCard(dtoRequest.getPoliceCard());
			residenceService.appraiseResidence(residence);
		} else {
			throw SystemException.init("信息为空");
		}
		return dtoResponse;
	}

	@ApiDescription("落户信息预约;负责人:luping")
	@RequestMapping("appointment")
	@ResponseBody
	public DtoPublicResponseObj<ResidenceInfo> appointment(@Valid DtoResidenceAppointmentRequest dtoRequest)throws Exception {
		DtoPublicResponseObj<ResidenceInfo> dtoResponse = new DtoPublicResponseObj<>();
			SpsResidence spsResidence = new SpsResidence();
			spsResidence.setId(dtoRequest.getId());
			spsResidence.setAppointmentDate(dtoRequest.getAppointmentDate());
			spsResidence.setAppointmentTime(dtoRequest.getAppointmentTime());
			spsResidence=residenceService.residenceAppointment(spsResidence);
 			ResidenceInfo info = FuncTool.copyPropertiesClass(spsResidence, ResidenceInfo.class);
 			fillResidenceInfoData(info);
 			dtoResponse.setData(info);
		return dtoResponse;
	}

	@ApiDescription("查询预约日期;负责人:luping;办理地点: 0 江岸区公安分局,1 二七街派出所 , 2 新村街派出所 , 3 丹水街派出所 ,4 后湖街派出所 , 5 谌家矶街派出所 ,6 百步亭派出所 , 7 塔子湖街派出所")
	@RequestMapping("queryAppointmentDate")
	@ResponseBody
	public DtoPublicResponseList<ResidenceAppointmentDate> queryAppointmentDate(@Valid DtoResidenceQueryAppointmentDateRequest dtoRequest)
			throws Exception {
		DtoPublicResponseList<ResidenceAppointmentDate> dtoResponse = new DtoPublicResponseList<>();
		List<ResidenceAppointmentDate> list = new ArrayList<ResidenceAppointmentDate>();
		String dateSunday = DateTool.dateToNormalString(ResidenceAppointmentDateUtil.getLastWeekSunday());// 获取上周日
		String dateLast = DateTool.dateToNormalString(DateTool.addDays(DateTool.parseDate(dateSunday), 21));// 获取截止日期
		String dateList[] = ResidenceAppointmentDateUtil.display(dateSunday, dateLast).split(",");// 获取符合的所以时间段
		String dateListTrue = "";// 获取需要显示微工作日的时间段
		String amTime="08:30-09:00,09:00-09:30,09:30-10:00,10:30-11:00,11:30-12:00" ;
		String pmTime ="02:30-03:00,03:00-03:30,03:30-04:00,04:00-04:30,04:30-05:00";

		for (int i = 1; i < 8; i++) {
			ResidenceAppointmentDate appointmentDate = new ResidenceAppointmentDate();
			Calendar one = Calendar.getInstance();
			String date = DateTool.dateToNormalString(ResidenceAppointmentDateUtil.addDateByWorkDay(one, i).getTime());
			dateListTrue = dateListTrue.equals("") ? date : dateListTrue + "," + date;
			appointmentDate.setDate(date);
			appointmentDate.setAmTime(amTime);
			appointmentDate.setPmTime(pmTime);
			List<SpsResidence> li = residenceService.queryResidenceAppointmentTime(date, dtoRequest.getAddressType());
			String am1 = "0";
			String am2 = ",0";
			String am3 = ",0";
			String am4 = ",0";
			String am5 = ",0";
			String pm1 = "0";
			String pm2 = ",0";
			String pm3 = ",0";
			String pm4 = ",0";
			String pm5 = ",0";
			if (li != null && li.size() > 0) {
				for (SpsResidence spsResidence : li) {
					String time = spsResidence.getAppointmentTime();
					if (time != null) {
						if (time.equals(amTime.split(",")[0])) {
							am1 = "1";
						} else if (time.equals(amTime.split(",")[1])) {
							am2 = ",1";
						} else if (time.equals(amTime.split(",")[2])) {
							am3 = ",1";
						}else if (time.equals(amTime.split(",")[3])) {
							am4 = ",1";
						}else if (time.equals(amTime.split(",")[4])) {
							am5 = ",1";
						} else if (time.equals(pmTime.split(",")[0])) {
							pm1 = "1";
						} else if (time.equals(pmTime.split(",")[1])) {
							pm2 = ",1";
						} else if (time.equals(pmTime.split(",")[2])) {
							pm3 = ",1";
						}else if (time.equals(pmTime.split(",")[3])) {
							pm4 = ",1";
						}else if (time.equals(pmTime.split(",")[4])) {
							pm5 = ",1";
						}
					}
				}
			}
			appointmentDate.setAmFlag(am1 + am2 + am3+am4+am5);
			appointmentDate.setPmFlag(pm1 + pm2 + pm3+pm4+pm5);
			list.add(appointmentDate);
		}
		String dateListFalse[] = ResidenceAppointmentDateUtil.arrContrast(dateList, dateListTrue.split(","));// 不显示的时间段
		for (int j = 0; j < dateListFalse.length; j++) {
			ResidenceAppointmentDate app = new ResidenceAppointmentDate();
			app.setDate(dateListFalse[j]);
			list.add(app);
		}
		sort(list);
		dtoResponse.setData(list);
		return dtoResponse;
	}

	
	 public static void sort(List<ResidenceAppointmentDate> data) {
	        Collections.sort(data, new Comparator<ResidenceAppointmentDate>() {
	            public int compare(ResidenceAppointmentDate o1, ResidenceAppointmentDate o2) {
	                String a = (String) o1.getDate();
	                String b = (String) o2.getDate();
	                return a.compareTo(b);
	            }
	        });
	    }
	 
}

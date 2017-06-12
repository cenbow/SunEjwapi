package com.huaao.sunejwapi.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huaao.sunejwapi.common.util.DateTool;
import com.huaao.sunejwapi.common.web.SystemException;
import com.huaao.sunejwapi.dao.SpsResidence;
import com.huaao.sunejwapi.dao.SpsResidenceExample;
import com.huaao.sunejwapi.mapper.SpsResidenceMapper;
@Service
public class ResidenceService {
	@Autowired
	private SpsResidenceMapper residenceMapper;
	
	/*********lihao*********************************/
	
	public String createAndUpdate(SpsResidence spsResidence) throws Exception{
		if(spsResidence.getId() == null || spsResidence.getId() == 0){
			residenceMapper.insertSelective(spsResidence);
		}else{
			residenceMapper.updateByPrimaryKey(spsResidence);
		}
		return String.valueOf(spsResidence.getId());
	}
	
	public List<SpsResidence> query(Integer uid) throws Exception{
		SpsResidenceExample example = new SpsResidenceExample();
		example.createCriteria().andCreatorIdEqualTo(uid);
		example.setOrderByClause(" create_time desc");
		return residenceMapper.selectByExample(example);
	}
	
	public void confirmDone(Integer residenceId){
		SpsResidence sr = new SpsResidence();
		sr.setId(residenceId);
		sr.setStatus(5);
		residenceMapper.updateByPrimaryKeySelective(sr);
	}
	
	public int updateAppointmentAndApprasie() throws Exception{
		Date date = new Date();
		Date dateLine = DateTool.parseDateTime(DateTool.formatDate(date)+" 19:00:00");
		if(date.getTime() > dateLine.getTime()){
			SpsResidenceExample example = new SpsResidenceExample();
			example.createCriteria().andAppointmentDateLessThanOrEqualTo(DateTool.formatDate(date)).andStatusEqualTo(4);
			SpsResidence spsResidence = new SpsResidence();
			spsResidence.setStatus(7);
			return residenceMapper.updateByExampleSelective(spsResidence, example);
		}
		return 0;
	}
	
	
	/*********yuhai*********************************/
	
	
	
	
	
	
	
	/*********fuwei*********************************/
	
	
	
	
	
	
	
	
	
	/*********luping*********************************/
	
	/**
	 * 落户评价.
	 * @param alertApraise
	 * @throws Exception
	 */
	public void appraiseResidence(SpsResidence spsResidence) throws Exception {
		Integer id = spsResidence.getId();
		// 判断是否是待评价
		SpsResidence residence = residenceMapper.selectByPrimaryKey(id);
		if (residence == null) {
			throw SystemException.init("落户信息不存在");
		}
		if (residence.getStatus()!=null && residence.getStatus()==6 ) {
			throw SystemException.init("已经评价");
		}
		residence.setApprasieAttitude(spsResidence.getApprasieAttitude());
		residence.setApprasieDate(spsResidence.getApprasieDate());
		residence.setApprasieDressing(spsResidence.getApprasieDressing());
		residence.setApprasieTotal(spsResidence.getApprasieTotal());
		residence.setAdvice(spsResidence.getAdvice());
		residence.setAppraiseReason(spsResidence.getAppraiseReason());
		residence.setPoliceCard(spsResidence.getPoliceCard());
		residence.setStatus(6); // 已评价 6
		residenceMapper.updateByPrimaryKeySelective(residence);
	}
	/**
	 * 预约
	 * @param spsResidence
	 * @throws Exception
	 */
	public SpsResidence residenceAppointment(SpsResidence spsResidence) throws Exception {
		Integer id = spsResidence.getId();
		// 判断是否已经预约
		SpsResidence residence = residenceMapper.selectByPrimaryKey(id);
		if (residence == null) {
			throw SystemException.init("数据不存在");
		}
		if (residence != null && residence.getAppointmentDate()!= null) {
			throw SystemException.init("已经预约");
		}
		residence.setStatus(4);//预约
		residence.setAppointmentDate(spsResidence.getAppointmentDate());
		residence.setAppointmentTime(spsResidence.getAppointmentTime());
		residenceMapper.updateByPrimaryKeySelective(residence);
		return residence;
	}
	
	/**
	 * 查询该时间内已经预约时间段
	 * 
	 * @param spsResidence
	 * @throws Exception
	 */
	public List<SpsResidence> queryResidenceAppointmentTime(String date,Integer addressType) throws Exception {
		SpsResidenceExample example=new SpsResidenceExample();
		example.createCriteria().andAppointmentDateEqualTo(date).andStationCodeEqualTo(addressType);
		 List<SpsResidence>  list = residenceMapper.selectByExample(example);
		 return list;
	}
		
	
	
}

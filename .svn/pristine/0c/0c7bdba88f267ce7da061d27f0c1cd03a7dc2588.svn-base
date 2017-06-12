package com.huaao.sunejwapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huaao.sunejwapi.dao.SpsSDictionary;
import com.huaao.sunejwapi.dao.SpsSDictionaryExample;
import com.huaao.sunejwapi.mapper.SpsSDictionaryMapper;

@Service
public class DictService {
	
	@Autowired
	private SpsSDictionaryMapper spsSDictionaryMapper; 
	
	public SpsSDictionary findByCode(String code){
		SpsSDictionaryExample example = new SpsSDictionaryExample();
		example.createCriteria().andDictionaryCodeEqualTo(code);
		List<SpsSDictionary> list = spsSDictionaryMapper.selectByExample(example);
		if(list.size() == 0){
			return null;
		}else{
			return list.get(0);
		}
	}
	
	public List<SpsSDictionary> findByParent(Integer parentId) {
		SpsSDictionaryExample example = new SpsSDictionaryExample();
		example.createCriteria().andDictionaryParentIdEqualTo(parentId);
		List<SpsSDictionary> list = spsSDictionaryMapper.selectByExample(example);
		return list;
	}
	
	public List<SpsSDictionary> findByParentCode(String parentCode) {
		SpsSDictionary ssd = this.findByCode(parentCode);
		if(ssd == null){
			return new ArrayList<>();
		}
		SpsSDictionaryExample example = new SpsSDictionaryExample();
		example.createCriteria().andDictionaryParentIdEqualTo(ssd.getId());
		return spsSDictionaryMapper.selectByExample(example);
	}
}

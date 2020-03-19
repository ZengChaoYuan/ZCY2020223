package com.cyzy.service;

import java.util.List;

import com.cyzy.bean.Title;
import com.cyzy.bean.User;
import com.cyzy.util.Page;

public interface TitleService {
	  //增加题目
	  public int addTitle(Title title);
	  
	  //新建一个titleId
	  public int createTitleId();
	
	  //通过领域ID查询所有的菜单
	  public List<Title> queryTitles(int areaId);
	  
	  //通过题目ID查询所有的菜单
	  public List<Title> queryTitleBytitleId(int titleId);
	  
	  //通过领域id来查询出该领域下面的题目
	  public Title getTitleByareaId(int areaId);
	  
	  //通过题目ID来查询出题目的所有信息
	  public Title getTitleBytitleId(int titleId);
	  
	  public int deleteTitleBytitleId(int titleId);
	  
	  //修改
	  public int updateTitle(Title title);
	  
	  // 分页查询不带条件
	  //public Page<Title> queryTitles(Title title, int currentPageNum);
}

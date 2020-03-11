package com.cyzy.service;

import java.util.List;

import com.cyzy.bean.Title;

public interface TitleService {
	  //������Ŀ
	  public int addTitle(Title title);
	  
	  //�½�һ��titleId
	  public int createTitleId();
	
	  //ͨ������ID��ѯ���еĲ˵�
	  public List<Title> queryTitles(int areaId);
	  
	  //ͨ����ĿID��ѯ���еĲ˵�
	  public List<Title> queryTitleBytitleId(int titleId);
	  
	  //ͨ������id����ѯ���������������Ŀ
	  public Title getTitleByareaId(int areaId);
	  
	  //ͨ����ĿID����ѯ����Ŀ��������Ϣ
	  public Title getTitleBytitleId(int titleId);
	  
	  public int deleteTitleBytitleId(int titleId);
	  
	  //�޸�
	  public int updateTitle(Title title);
}

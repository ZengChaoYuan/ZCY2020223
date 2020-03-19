package com.cyzy.dao;

import java.util.List;

import com.cyzy.bean.Title;

public interface TitleDao {
	
  //ͨ������ID��ѯ���еĲ˵�
  public List<Title> queryTitles(int areaId);
  //ͨ����ĿID��ѯ���еĲ˵�
  public List<Title> queryTitleBytitleId(int titleId);
  
  //������Ŀ
  public int addTitle(Title title);
  
  //�½�һ��titleId
  public int createTitleId();
  
  //ͨ������id����ѯ���������������Ŀ
  public Title getTitleByareaId(int areaId);
  
  //ͨ����ĿID����ѯ����Ŀ��������Ϣ
  public Title getTitleBytitleId(int titleId);
  
  public int deleteTitleBytitleId(int titleId);
  
  public int updateTitle(Title title);
  
  //��Ŀ�б��ҳ
  public int queryTitleCount(Title title);
  public List<Title> queryTitleList(Title title,int startIndex,int endIndex);
}

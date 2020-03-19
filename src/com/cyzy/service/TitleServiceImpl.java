package com.cyzy.service;

import java.util.List;

import com.cyzy.bean.Title;
import com.cyzy.bean.User;
import com.cyzy.dao.TitleDao;
import com.cyzy.util.DaoFactory;
import com.cyzy.util.Page;

public class TitleServiceImpl implements TitleService {

	@Override
	public List<Title> queryTitles(int areaId) {
		TitleDao titleDao=(TitleDao)DaoFactory.getDaoImpl(TitleDao.class.getName());
		return titleDao.queryTitles(areaId);
	}
	
	@Override
	public List<Title> queryTitleBytitleId(int titleId) {
		TitleDao titleDao=(TitleDao)DaoFactory.getDaoImpl(TitleDao.class.getName());
		return titleDao.queryTitleBytitleId(titleId);
	}

	@Override
	public Title getTitleByareaId(int areaId) {
		TitleDao titleDao=(TitleDao)DaoFactory.getDaoImpl(TitleDao.class.getName());
		return titleDao.getTitleByareaId(areaId);
	}

	@Override
	public int deleteTitleBytitleId(int titleId) {
		TitleDao titleDao=(TitleDao)DaoFactory.getDaoImpl(TitleDao.class.getName());
		return titleDao.deleteTitleBytitleId(titleId);
	}

	@Override
	public Title getTitleBytitleId(int titleId) {
		TitleDao titleDao=(TitleDao)DaoFactory.getDaoImpl(TitleDao.class.getName());
		return titleDao.getTitleBytitleId(titleId);
	}

	@Override
	public int addTitle(Title title) {
		TitleDao titleDao=(TitleDao)DaoFactory.getDaoImpl(TitleDao.class.getName());
		return titleDao.addTitle(title);
	}

	@Override
	public int createTitleId() {
		TitleDao titleDao=(TitleDao)DaoFactory.getDaoImpl(TitleDao.class.getName());
		return titleDao.createTitleId();
	}

	@Override
	public int updateTitle(Title title) {
		TitleDao titleDao=(TitleDao)DaoFactory.getDaoImpl(TitleDao.class.getName());
		return titleDao.updateTitle(title);
	}

//	@Override
//	public Page<Title> queryTitles(Title title, int currentPageNum) {
//		TitleDao titleDao=(TitleDao)DaoFactory.getDaoImpl(TitleDao.class.getName());
//		int totalRecordsNum=titleDao.queryTitleCount(title);
//		Page<User> page=new Page<User>(currentPageNum,totalRecordsNum,5);
//		List<Title> titleList=titleDao.queryTitleList(title, page.getStartIndex(),page.getEndIndex());
//		page.setRecords(titleList);
//		return titleList;
//	}

}

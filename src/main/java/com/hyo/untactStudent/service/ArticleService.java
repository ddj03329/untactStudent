package com.hyo.untactStudent.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyo.untactStudent.dao.ArticleDao;
import com.hyo.untactStudent.dto.Article;
import com.hyo.untactStudent.dto.ResultData;
import com.hyo.untactStudent.util.Util;

@Service
public class ArticleService {
	@Autowired
	private ArticleDao articleDao;
			
	public Article getArticle(int id) { 
		return articleDao.getArticle(id);
		
	}



	public ResultData addArticle(String title, String body) {
		
		int id = articleDao.addArticle(title, body);
		
		return new ResultData("S-1", "게시물이 추가 되었습니다.", "id", id);

	}

	public ResultData deleteArticle(int id) {
		boolean rs =articleDao.deleteArticle(id);
		
		if (rs == false) {
			return new ResultData("F-1", "게시물이 존재하지 않습니다.");
		}
		
		return new ResultData("S-1", "게시물이 삭제되었습니다.");

	}

	public ResultData doModifyArticle(int id, String title, String body) {
		
		articleDao.doModifyArticle(id, title, body);
		
		
		return new ResultData("S-1", "게시물이 수정 되었습니다.", "id", id);

	}



	public List<Article> getArticles(String searchKeywordType, String searchKeyword) {
		
		return articleDao.getArticles(searchKeywordType, searchKeyword);
	}
}

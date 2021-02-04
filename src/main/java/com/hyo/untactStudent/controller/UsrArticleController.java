package com.hyo.untactStudent.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hyo.untactStudent.dto.Article;
import com.hyo.untactStudent.dto.ResultData;
import com.hyo.untactStudent.util.Util;

@Controller
public class UsrArticleController {
	private int articlesLastId;
	private List<Article> articles;
	
	public UsrArticleController() {
		articlesLastId = 0;
		articles = new ArrayList<>();
		articles.add(new Article(++articlesLastId, "2021-02-03 12:12:12", "2021-02-03 12:12:12", "제목1", "내용1"));
		articles.add(new Article(++articlesLastId, "2021-02-03 12:12:12", "2021-02-03 12:12:12", "제목2", "내용2"));
	}
	
	
	@RequestMapping("/usr/article/detail")
	@ResponseBody
	public Article showDetail(int id) {
		
		for(Article article : articles) {
			if(article.getId() == id) {
				return article;
			}
		}
			
		return null;
	}
	

	
	@RequestMapping("/usr/article/list")
	@ResponseBody
	
	public List<Article> showList() {
		return articles;
	}
	
	
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public ResultData doAdd( String title, String body) {
		
		String regDate = Util.getNowDateStr();
		String updateDate = regDate;
		articles.add(new Article(++articlesLastId, regDate, updateDate, title, body));
		
		return new ResultData("S-1", "게시물이 추가 되었습니다.","id", articlesLastId);
	}
	
	


	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(int id) {
		
		boolean deleteArticleRs = deleteArticle(id);
	
		if(deleteArticleRs == false) {
			return new ResultData( "F-1", "없는 게시물입니다.");
			
		}
		
		return new ResultData("S-1", String.format("%d번 게시물이 삭제되었습니다.", id));

		
		
	}
	


	private boolean deleteArticle(int id) {
		for(Article article : articles) {
			if(article.getId() == id) {
				articles.remove(article);
				return true;
			}
		}	
		return false;
	
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData doModify(int id, String title, String body) {
		Article selArticle = null;
		
		for(Article article : articles) {
			if( article.getId() == id) {
				selArticle = article;
				break;
			}
		}
		
		
		if(selArticle == null) {	
			
			return new ResultData( "F-1",String.format("%d번 게시물이 존재하지 않습니다.", id));
			
		}
			
		selArticle.setUpdateDate(Util.getNowDateStr());
		selArticle.setTitle(title);
		selArticle.setBody(body);
		
		return new ResultData("S-1", String.format("%d번 게시물이 수정되었습니다.", id));

		
		
	}
}

package com.hyo.untactStudent.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hyo.untactStudent.dto.Article;
import com.hyo.untactStudent.dto.ResultData;
import com.hyo.untactStudent.service.ArticleService;
import com.hyo.untactStudent.util.Util;

@Controller
public class UsrArticleController {
	@Autowired
	private ArticleService articleService;
	
	
	@RequestMapping("/usr/article/detail")
	@ResponseBody
	public Article showDetail(int id) {
		
		
		Article article = articleService.getArticle(id);
			
		return article;
	}
	

	
	@RequestMapping("/usr/article/list")
	@ResponseBody
	
	public List<Article> showList( String searchKeywordType, String searchKeyword) {
		if ( searchKeywordType != null) {
			searchKeywordType = searchKeywordType.trim();
		}
		
		
		if ( searchKeywordType == null || searchKeywordType.length() == 0) {
			searchKeywordType = "titleAndBody";
		}
		
		
		
		
		if( searchKeyword != null && searchKeyword.length() == 0) {
			searchKeyword = null;
		}
		
		if ( searchKeyword != null) {
			searchKeyword = searchKeyword.trim();
		}
		
		return articleService.getArticles(searchKeywordType, searchKeyword);
	}
	
	
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public ResultData doAdd( String title, String body) {
		
		if( title == null) {
			return new ResultData("F-1", "제목을 입력해주세요");

		}
		if( body == null) {
			return new ResultData("F-1", "내용을 입력해주세요");

		}
		
		return articleService.addArticle(title, body);
			
	}
	
	


	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(Integer id) {
		
		if( id == null) {
			return new ResultData("F-1", "번호를 입력해주세요");
		}
		
		Article article =articleService.getArticle(id);
		
		if( article == null) {
			return new ResultData( "F-1", "없는 게시물입니다.");
		}
	
		
		
		return articleService.deleteArticle(id);
	
	}
	



	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData doModify(Integer id, String title, String body) {
		if( id == null) {
			return new ResultData("F-1", "번호를 입력해주세요");
		}
		
		if( title == null) {
			return new ResultData("F-1", "제목을 입력해주세요");

		}
		if( body == null) {
			return new ResultData("F-1", "내용을 입력해주세요");

		}
		
		Article article =articleService.getArticle(id);
		if( article == null) {
			return new ResultData( "F-1", "없는 게시물입니다.");
		}
		return articleService.doModifyArticle(id, title, body );
		
	}
}

package com.hyo.untactStudent.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hyo.untactStudent.dto.Article;

@Controller
public class UsrArticleController {
	private int articlesLastId;
	private List<Article> articles;
	
	public UsrArticleController() {
		articlesLastId = 0;
		articles = new ArrayList<>();
		articles.add(new Article(++articlesLastId, "2021-02-03 12:12:12", "제목1", "내용1"));
		articles.add(new Article(++articlesLastId, "2021-02-03 12:12:12", "제목2", "내용2"));
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
	
	public Map<String, Object> doAdd(String regDate, String title, String body) {
		articles.add(new Article(++articlesLastId, regDate, title, body));
		
		Map<String, Object> rs = new HashMap<>();
		rs.put("resultCode", "S-1");
		rs.put("msg", "게시물이 추가 되었습니다.");
		rs.put("id", articlesLastId);
		return rs;
	}
}

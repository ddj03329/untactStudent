package com.hyo.untactStudent.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hyo.untactStudent.dto.Article;

@Controller
public class UsrArticleController {
	private List<Article> articles;
	
	public UsrArticleController() {
		articles = new ArrayList<>();
		articles.add(new Article(1, "2021-02-03 12:12:12", "제목1", "내용1"));
		articles.add(new Article(2, "2021-02-03 12:12:12", "제목2", "내용2"));
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
}

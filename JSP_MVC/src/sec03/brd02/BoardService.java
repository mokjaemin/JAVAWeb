package sec03.brd02;

import java.util.List;

public class BoardService {
	BoardDAO boardDAO;
	public BoardService() {
		boardDAO = new BoardDAO();
	}
	
	// 글목록 불러오기 기능
	public List<ArticleVO> listArticles() {
		List<ArticleVO> articlesList = boardDAO.selectAllArticles();
		return articlesList;
	}
	
	// 기사 추가 기능
	public void addArticle(ArticleVO article){
		boardDAO.insertNewArticle(article);	
	}

}
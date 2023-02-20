package sec03.brd06;

import java.util.List;

public class BoardService {
	BoardDAO boardDAO;

	public BoardService() {
		boardDAO = new BoardDAO();
	}

	public List<ArticleVO> listArticles() {
		List<ArticleVO> articlesList = boardDAO.selectAllArticles();
		return articlesList;
	}

	public int addArticle(ArticleVO article) {
		return boardDAO.insertNewArticle(article);
	}

	public ArticleVO viewArticle(int articleNO) {
		ArticleVO article = null;
		article = boardDAO.selectArticle(articleNO);
		return article;
	}

	public void modArticle(ArticleVO article) {
		boardDAO.updateArticle(article);
	}

	public List<Integer> removeArticle(int  articleNO) {
		// 해당 기사 번호에 맞는 기사에 맞춰서 삭제해야될 기사들 받아옴
		// 자식 기사까지
		List<Integer> articleNOList = boardDAO.selectRemovedArticles(articleNO);
		// 해당 기사 삭
		boardDAO.deleteArticle(articleNO);
		// 삭제해야될 모든 기사 리스트 반환
		return articleNOList;
	}

}
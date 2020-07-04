import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 ==");
		Scanner sc = new Scanner(System.in);

		List<Article> articles = new ArrayList<>();
		int lastArticleId = 0;

		while (true) {
			System.out.printf("명령어) ");
			String command = sc.nextLine();

			command = command.trim();

			if (command.length() == 0) {
				continue;
			}

			if (command.equals("system exit")) {
				break;
			} else if (command.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시글이 존재하지 않습니다.");
					continue;
				}

				System.out.println("번호 / 날짜                / 제목");

				/*
				// v1
				for ( int i = 0; i < articles.size(); i++ ) {
					Article article = articles.get(i);
					System.out.printf("%d    / %s / %s\n", article.id, article.regDate, article.title);
				}
				*/
				for ( Article article : articles ) {
					System.out.printf("%d    / %s / %s\n", article.id, article.regDate, article.title);
				}

			} else if (command.equals("article write")) {
				int id = lastArticleId + 1;
				lastArticleId = id; // 이제 lastArticleId를 갱신한다.
				String regDate = Util.getNowDateStr();
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				Article article = new Article(id, regDate, title, body);

				articles.add(article);

				System.out.printf("%d번 글이 %s 분에 작성되었습니다.\n", article.id, article.regDate);
			} else {
				System.out.printf("%s(은)는 존재하지 않는 명령어 입니다.\n", command);
			}
		}

		sc.close();
		System.out.println("== 프로그램 종료 ==");
	}
}

class Util {
	public static String getNowDateStr() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
		Date currentTime = new Date();
		return format.format(currentTime);
	}
}

class Article {
	int id;
	String regDate;
	String title;
	String body;

	Article(int id, String regDate, String title, String body) {
		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.body = body;
	}
}

package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import model.NewsDTO;

// View: 사용자 입출력 전담
public class NewsView {
    private Scanner sc = new Scanner(System.in);
 
    // ================= 메인 메뉴 출력 + 유효성 검사 =================
    public int showMenu() {
        int menu; // 사용자 입력값
        while (true) { // 무한 반복, break로 탈출
            System.out.println("===== 뉴스 관리 프로그램 =====");
            System.out.println("1. 뉴스 추가");
            System.out.println("2. 뉴스 전체 조회");
            System.out.println("3. 뉴스 상세 검색");
            System.out.println("4. 뉴스 요약 수정");
            System.out.println("5. 뉴스 삭제");
            System.out.println("0. 종료");
            System.out.print("메뉴 선택 >> ");

            try { //정수만 입력하도록 트라이캐치 설정
                menu = sc.nextInt(); // 사용자 입력
                sc.nextLine(); // 버퍼 비우기 int 다음 String 입력받을 때 남아있었음ㅜㅜ
                if (menu >= 0 && menu <= 5) { // 0~5 범위 확인
                    break; // 올바른 입력이면 반복 탈출
                } 
                else {
                    System.out.println("⚠️ 0~5 사이 숫자를 입력해주세요.");
                }
            } 
            catch (Exception e) { // 숫자가 아닌 값 입력 시
                sc.nextLine(); // 버퍼 비우기 = 탈출을 위해서 필수
                System.out.println("⚠️ 숫자만 입력 가능합니다.");
            }
        }
        return menu; // 올바른 입력값 반환
    }

    // ================= 뉴스 추가 메서드용 =================
   /*
    public NewsDTO getNewNews() { //새로운 뉴스 정보 받을거야
        sc.nextLine(); // 이전 입력 버퍼 정리
        NewsDTO newsDTO = new NewsDTO(); //포장지 생성

        String title; //스코프이슈로 와일문 밖에 선언 - 타이틀 검사 시작
        // 1. 제목 입력 유효성 검사
        while (true) {
            System.out.print("제목 입력: ");
            title = sc.nextLine().trim(); // 앞뒤 공백 제거
            if (!title.isEmpty()) //타이틀이 공백이 아니라면 = 아무것도 입력안한게 아니라면
            	break; //무한루프 탈출
            System.out.println("⚠️ 제목은 공백일 수 없습니다.");
        }
        newsDTO.setTitle(title); //내가 보내는 타이틀로 DB타이틀값 바꿔줘

        // 2. 언론사 입력 유효성 검사
        String press;
        while (true) {
            System.out.print("언론사 입력: ");
            press = sc.nextLine().trim();
            if (!press.isEmpty()) break;
            System.out.println("⚠️ 언론사는 공백일 수 없습니다.");
        }
        newsDTO.setPress(press);
        
     // 3. URL 입력
        String url;
        while (true) {
            System.out.print("뉴스 URL 입력: ");
            url = sc.nextLine().trim();
            if (!url.isEmpty()) break;
            System.out.println("⚠️ URL은 공백일 수 없습니다.");
        }
        newsDTO.setUrl(url);
        
        // 4. 날짜 입력
        String date;
        while (true) {
            System.out.print("날짜 입력 (YYYYMMDD) >> ");
            date = sc.nextLine().trim();
            if (date.matches("\\d{8}")) 
            	break; // 숫자 8자리만 허용
            System.out.println("⚠️ 날짜는 숫자 8자리 형식 YYYYMMDD로 입력해주세요.");
        }
        newsDTO.setDate(date);
        
        // 5. 초기 summary는 비워두기
        newsDTO.setSummary("null");
    return newsDTO;        
    }
    */
    //=============뉴스 추가 개선 코드 - 분리
    public void getNewNews() { //새로운 뉴스 정보 받을거야
    	System.out.println("[신규 뉴스 추가]");
    }
    
    public String inputTitle() {
        String title;
        while (true) {
            System.out.print("제목 입력: ");
            title = sc.nextLine().trim();
            if (!title.isEmpty()) 
            	break;
            System.out.println("⚠️ 제목은 비어 있을 수 없습니다.");
        }
        return title;
    }
    
    public String inputPress() {
        String press;
        while (true) {
            System.out.print("언론사 입력: ");
            press = sc.nextLine().trim();
            if (!press.isEmpty()) 
            	break;
            System.out.println("⚠️ 언론사를 입력하세요.");
        }
        return press;
    }
    
    public String inputUrl() {
        String url;
        while (true) {
            System.out.print("뉴스 URL 입력: ");
            url = sc.nextLine().trim();
            if (url.startsWith("http")) //http로 시작하지 않으면 모두 예외처리
            	break;
            System.out.println("⚠️ 올바른 URL 형식이 아닙니다.");
        }
        return url;
    }
    
    public String inputDate() {
        String date;
        // 사용자가 입력한 날짜를 저장할 변수
        // while 루프 안에서 계속 새로운 값을 입력받게 되므로
        // 루프 밖에서 선언해 스코프(유효 범위)를 넓혀둔다.
        
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        // 오늘 날짜를 "yyyyMMdd" 형식의 문자열로 변환하여 저장
        // 예를 들어 오늘이 2025년 10월 30일이면 "20251030"이 된다.
        // LocalDate.now() → 현재 날짜 객체 생성
        // DateTimeFormatter.ofPattern("yyyyMMdd") → 출력 포맷 지정
        // 그래서 위 코드처럼 쓰면 현재 날짜 즉 오늘의 날짜 객체가 20251030 형식으로 지정된다.

        while (true) {
            System.out.print("날짜 입력 ("+today+") >> ");
            date = sc.nextLine().trim();
            // 사용자 입력을 받음
            // sc.nextLine() → 한 줄 전체 입력
            // trim() → 앞뒤 공백 제거 (공백 입력 실수를 방지)
            
            if (date.matches("\\d{8}")) {
            // 입력값이 숫자 8자리로만 구성되어 있다면 루프 종료
            // 정규식 "\\d{8}"의 의미:
            // \\d → 숫자 1개, {8} → 정확히 8개
            // 즉, "20251030" 같은 형식만 허용됨
            break;
            }
            System.out.println("⚠️ 날짜는 8자리 숫자로 입력해주세요 (예: " + today + ").");
        }
        return date;
    }
    
    public String inputSummary() {
        System.out.print("뉴스 요약 입력 (미입력 시 'null'로 설정) >> ");
        String summary = sc.nextLine().trim();  // 입력받고 공백 제거

        // 입력이 비어있으면 기본값 "null" 사용
        if (summary.isEmpty()) {
            summary = "null";
        }
        return summary;
    }
    
    // ================= 뉴스 번호 입력 + 유효성 검사 =================
    public int inputNewsId() {
    	int id;
    	while (true) { // 무한 반복
    		System.out.print("선택할 뉴스 번호 입력: ");
    		try {
    			id = sc.nextInt(); // 사용자 입력                
    			if (id > 1000) break; // 0보다 큰 번호만 허용
    			System.out.println("⚠️ 1000보다 큰 번호를 입력해주세요.");
    		} catch (Exception e) { // 숫자가 아닌 값 입력 시
    			sc.nextLine(); // 버퍼 비우기
    			System.out.println("⚠️ 숫자만 입력 가능합니다.");
    		}
    	}
    	return id;
    }

    // ================= 전체 뉴스 출력 + 유효성 검사 =================
    public void showAllNews(ArrayList<NewsDTO> datas) {
       //개선사항
       //상세검색이랑 삭제에서도 써야하는 메서드인데
       //모든 정보가 출력되기 때문에 전체선택지 볼 때 불편하다
       //컨디션 분기 추가해서 ALL 이면 data 보여주고
       //SELECT면 id랑 타이틀 정도만 보여줘도 될 것 같음
        System.out.println("===== 전체 뉴스 =====");
        if (datas.isEmpty()) {
            System.out.println("❌ 저장된 뉴스가 없습니다...");
        } 
        else {
           for (NewsDTO data : datas) {
            System.out.println(data);
            }
        }
        System.out.println("===================");
    }

    // ================= 상세 뉴스 출력 =================
    public void showOneNews(NewsDTO newsDTO) {
        if (newsDTO == null) {
            System.out.println("❌ 저장된 뉴스가 없습니다...");
        } 
        else {
            System.out.println("===== 뉴스 상세 =====");
            System.out.println("번호: " + newsDTO.getId());
            System.out.println("제목: " + newsDTO.getTitle());
            System.out.println("요약: " + newsDTO.getSummary());
            System.out.println("URL: " + newsDTO.getUrl());
            //System.out.println("언론사: " + newsDTO.getPress());
            //System.out.println("날짜: " + newsDTO.getDate());
            System.out.println("===================");
        }
    }
    // ================= 뉴스 요약 수정 입력 =================
    public String updateSummary() {
        sc.nextLine(); // 이전 입력 버퍼 정리
        String summary;            	
        while(true) {
            System.out.print("새 요약 입력: ");
            summary = sc.nextLine().trim();
            if(!summary.isEmpty()) break; // 빈 문자열 방지
            System.out.println("⚠️ 요약은 공백일 수 없습니다.");
        }
        return summary;
    }
    
    // ================= 뉴스 추가 결과 =================
    public void showInsertResult(boolean flag) {
    	System.out.println(flag ? "✅ 뉴스가 성공적으로 추가되었습니다!" : "❌ 뉴스 추가 실패...");
    }
    // ================= 요약 수정 결과 =================
    public void showUpdateResult(boolean flag) {
        System.out.println(flag ? "🛠 뉴스 요약이 수정되었습니다!" : "❌ 뉴스 요약 수정 실패...");
    }
    // ================= 삭제 결과 =================
    public void showDeleteResult(boolean flag) {
        System.out.println(flag ? "🗑 뉴스가 삭제되었습니다!" : "❌ 뉴스 삭제 실패...");
    }
}
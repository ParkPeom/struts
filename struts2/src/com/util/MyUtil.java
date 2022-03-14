package com.util;

public class MyUtil {

	// currentPageSetup page numPerBlock 
	
	//전체페이지
	public int getPageCount(int numPerPage,int dataCount) { 
							//       3            34 
							// numPerPage : 한 페이지에 몇개의 데이터
							// 전체 데이터 개수와 몇개를 뿌릴건지
							// 34개와 하나의 페이지에 몇개를 뿌릴건지 
	int pageCount = 0;
	
	// 전체 데이터 개수를 하나의 페이지에 몇개를 뿌릴건지 나눔
	pageCount = dataCount / numPerPage;  // 페이지 몇장이 필요한지 알아냄
	
	if(dataCount % numPerPage != 0) { // 34 % 3 == 0으로 떨어지지않으면 페이지 하나더 필요
		pageCount++; 
		}
		return pageCount; // 전체 페이지 개수
	}
	
	// 페이지 처리 메소드
	// 현재 내가 어떤페이지를 보고있는지 , 전체페이지 몇장 , list.jsp
	public String pageIndexList(int currentPage, int totalPage, String listUrl) { 
	
		int numPerBlock = 5; // 5개 표시 ◀이전 6 7 8 9 10 다음▶
		int currentPageSetup; //  ◀   내가 이전을 누르게되면 5페이지가 나옴 (왼쪽 화살표 값)
		int page; // for문의 i 6 7 8 9 10	
		StringBuffer sb = new StringBuffer(); 
		
		if(currentPage == 0 || totalPage == 0) {
			// 현재페이지또는 전체페이지 0이면 null 반환
			return "null";
		}
		
		// list.jsp?pageNum=2 이거나    	
		// list.jsp 오면서 ?검색했을때 있으면 
		// list.jsp? searchKey=subject & searchValue=aa & pageNum=2
		//  로 String listUrl로 매개변수로 오게됨
		// ? 가 있는지 찾아본다. 
		if(listUrl.indexOf("?") != -1) {
			// ?가 있으면
			listUrl = listUrl + "&";
		} else {
			// 물음표가 없으면 물음표를 붙임
			listUrl = listUrl + "?";
		}
		// ◀이전 6 7 8 9 10 다음▶
		// 현재 8페이지를 5개씩으므로 numPerBlock = 5 하면 나머지 3에 5를 곱합
		// 5 =  1 * 5 
		
		// 1 2 3 4 5 다음▶
		// ◀이전 6 7 8 9 10 다음▶
		
		// ◀이전 값을 구하는 공식 
		
		// ◀이전값  5           6           5            5 
		currentPageSetup = (currentPage/numPerBlock)*numPerBlock;
		//			10            5
			if(currentPage % numPerBlock == 0) { // 10으로 나눴을때 0이므로		
		//				5				5					5
				currentPageSetup = currentPageSetup - numPerBlock; //  10이여도 5가 나옴
				
			}
			// 위에 공식으로 5가 들어가면 ◀이전
			
		// ◀이전
			//		12         5       뒤에 조건 5 이면  ◀이전 만들어라.   
			// 
			
			//◀이전 필요하면 한번만 만들고 필요하지않으면 안만듬
			
			//		15			5				6
			if(totalPage > numPerBlock && currentPageSetup > 0) {
			
				// 조건에 만족하면 ◀이전 만듬
				//<a href="list.jsp?pageNum=2>◀이전</a>"를 만드는 코딩
				sb.append("<a href=\"" + listUrl + "pageNum=" 
						+ currentPageSetup + "\">◀이전</a>&nbsp;");
			}
			// 바로가기 페이지  6 7 8 9 10
			// 5개찍어야함
			// page = 현재페이지
		//   6           5
			page = currentPageSetup + 1; // 시작값 ◀이전 부터  6,7,8,9,10 
			
			//	   6        12       6               5(◀이전값)      5       
			while(page <= totalPage && page <= (currentPageSetup + numPerBlock)) {
				// 만족하면 6부터 10까지 찍힘
				
				// 현재 페이지가 내가 보고있는 페이지랑 같으면 색깔로 지정 
				if(page == currentPage) {
					sb.append("<font color=\"Fuchsia\">" + page + "</font>&nbsp;");
					//<font color="Fuchsia">9</font>
				} else {
					// 같지않으면
					// 7 페이지면?
					//<a href="list.jsp?pageNum=7">7</a>&nbsp;
					sb.append("<a href=\"" + listUrl + "pageNum=" + page + 
							"\">" + page + "</a>&nbsp;");
				}		
				page++; // 6 7 8 9 10 찍음
			}
		// 다음▶
		// ◀이전 6 7 8 9 10 다음▶
		// ◀이전 11 12
		//(위에꺼)   12             5              5   다음버튼생김
		//(밑에꺼)   35            10              5   다음버튼안생김
			if(totalPage - currentPageSetup > numPerBlock) {
			//<a href="list.jsp?pageNum=11">다음▶</a>&nbsp;
			sb.append("<a href=\"" + listUrl + "pageNum=" + 
					page + "\">다음▶</a>&nbsp;");
		}
		return sb.toString();
	}
}							 

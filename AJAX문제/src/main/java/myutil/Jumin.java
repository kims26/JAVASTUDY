package myutil;

import java.util.Calendar;


public class Jumin {

	private String jumin_no;

	public void setJumin_no(String jumin_no) {
		this.jumin_no = jumin_no;
	}
	
	//             01234567890123   <- index
	// jumin_no = "801212-1234560"
	
	/*
	    주민번호 성별자리 정보
	            
	            내국인      외국인
	            남  여      남  여  
	    1800    9   0  
	    1900    1   2       5   6  
	    2000    3   4       7   8 
	*/
	
	
	public int getYear() {
		
		//주민번호 년도자리 2자리 추출
		String str_year = this.jumin_no.substring(0, 2);// "80"
		//문자열->정수로 변환
		int year = Integer.parseInt(str_year);// "80" -> 80
		
		char gender_code = jumin_no.charAt(7);// '1'
		switch(gender_code)
		{
		    case '1':
		    case '2':
		    case '5':
		    case '6': year = year + 1900 ; break; // year += 1900;
		    case '3': 
		    case '4': 
		    case '7': 
		    case '8': year = year + 2000; break;  // year += 2000;
		    default : year = year + 1800;
		}
		
		return year;
	}
	
	
	//나이 구하기
	public int getAge() {
		
		//현재년도를 Calendar를 통해서 구한다
		Calendar now = Calendar.getInstance();
		int current_year = now.get(Calendar.YEAR);
		
		//나이  =  현재년도    -  출생년도
		int age = current_year - this.getYear();
		
		return age;
	}
	
	//성별 구하기
	public String getGender() {
		
		//방법1:문자->숫자로변환
		//char gender = jumin_no.charAt(7);// '1'  
		//숫자형문자->숫자로 변환(1자일때만 가능)
		//int  gender_number = gender - '0'; //0~9
		//                     49   -  48
		// '0':48  '1':49  '2':50
		
		//방법2:문자열->숫자로변환
		String str_gender = jumin_no.substring(7,8);// "1"
		int gender_number = Integer.parseInt(str_gender);
				
		
	    // 홀수면 남자
		if(gender_number%2==1) return "남자";
		
		//if(gender_number==1 || gender_number==3 ||gender_number==5)
		//	return "남자";
		
		return "여자";
	}
	
	//계절 구하기
	public String getSeason() {
		
		String str_month = jumin_no.substring( 2, 4 ); // "12"
		int    month     = Integer.parseInt(str_month);
		
		//방법1:if
//		if(month>=3 && month<=5)  return "봄";
//		if(month>=6 && month<=8)  return "여름";
//		if(month>=9 && month<=11) return "가을";
		
		//방법2:switch
		switch(month/3)
		{
			case 1: return "봄";
			case 2: return "여름";
			case 3: return "가을";
		}
		
				
		return "겨울";
	}
	
	//띠 구하기
	public String getTti() {
		
		//방법1:배열활용
		//띠배열                    0      1      2      3     4 <= tti_index
//		String [] tti_array = { "원숭이", "닭" , "개" ,"돼지","쥐","소","호랑이","토끼","용","뱀","말","양"  };
//		int tti_index = this.getYear()%12;// 0~11
//		return tti_array[tti_index];
		
		//방법2:switch활용
		int tti_index = this.getYear()%12;
		switch(tti_index)
		{
			case  0: return "원숭이";
			case  1: return "닭";
			case  2: return "개";
			case  3: return "돼지";
			case  4: return "쥐";
			case  5: return "소";
			case  6: return "호랑이";
			case  7: return "토끼";
			case  8: return "용";
			case  9: return "뱀";
			case 10: return "말";
		}
		
		return "양";
		
	}
	
	//10간 
	// 4  5  6  7  8  9  0  1  2  3        <= 년도%10(0~9) 
	// 갑 을 병 정 무 기 경 신 임 계         
	
	//12지
	// 4  5  6  7  8  9  10 11 0  1  2  3  <= 년도%12(0~11)
	// 자 축 인 묘 진 사 오 미 신 유 술 해
	
	public String getGanji() {
		
		int year = this.getYear();
		
		//배열                  0    1    2    3    4
		//char [] gan_array = {'경','신','임','계','갑','을','병','정','무','기'};
		
		//                 0 1 2 3 4 5 6 7 8 9      <- 년도%10
		String gan_list = "경신임계갑을병정무기";
		// '계' -> gan_list.charAt(3)
		//                 0 1 2 3 4 5 6 7 8 9 1011 <- 년도%12
		String ji_list  = "신유술해자축인묘진사오미";
		// '묘' -> ji_list.charAt(7);
		
	    char gan = gan_list.charAt(year%10);
	    char ji  = ji_list.charAt(year%12);
	    	        
		String ganji = String.format("%c%c년", gan,ji);
		
		return ganji;
	}
	
	
	
	
	
	//             01234567890123   <- index
	// jumin_no = "801212-1234560"	
	
	//출생지역 구하기
	public String getLocal() {
		
		String str_local = jumin_no.substring(8, 10);//"23"
		int    local     = Integer.parseInt(str_local);
		
		if(local>=0 && local<=8) return "서울";
		if(local>=9 && local<=12) return "부산";
		if(local>=13 && local<=15) return "인천";
		if(local>=16 && local<=25) return "경기도";
		if(local>=26 && local<=34) return "강원도";
		if(local>=35 && local<=39) return "충청북도";
		if(local==40) return "대전";
		if(local==44 || local==49) return "세종특별시";
		if(local>=41 && local<=47) return "충청남도";
		if(local>=48 && local<=54) return "전라북도";
		if(local>=55 && local<=56) return "광주";
		if(local>=57 && local<=66) return "전라남도";
		if(local>=67 && local<=69 || local==76) return "대구";
		if(local>=70 && local<=81) return "경상북도";
		if(local==85) return "울산광역시";
		if(local>=82 && local<=92) return "경상북도";
		if(local>=93 && local<=95) return "제주";
		
		
		return "기타";
	}
	
	
	//             01234567890123   <- index
	// jumin_no = "801212-1234560"	
	//             234567 892345  
	
	
	public boolean isValid() {
		
		int sum = 0;
		//'8'-'0' -> 8
		sum = sum + ( (jumin_no.charAt(0)-'0') * 2 );
		sum = sum + ( (jumin_no.charAt(1)-'0') * 3 );
		sum = sum + ( (jumin_no.charAt(2)-'0') * 4 );
		sum = sum + ( (jumin_no.charAt(3)-'0') * 5 );
		sum = sum + ( (jumin_no.charAt(4)-'0') * 6 );
		sum = sum + ( (jumin_no.charAt(5)-'0') * 7 );
		// - SKIP
		sum = sum + ( (jumin_no.charAt(7)-'0')  * 8 );
		sum = sum + ( (jumin_no.charAt(8)-'0')  * 9 );
		sum = sum + ( (jumin_no.charAt(9)-'0')  * 2 );
		sum = sum + ( (jumin_no.charAt(10)-'0') * 3 );
		sum = sum + ( (jumin_no.charAt(11)-'0') * 4 );
		sum = sum + ( (jumin_no.charAt(12)-'0') * 5 );
		
		int check_num = sum%11;
		check_num     = 11 - check_num;
		check_num     = check_num%10; 
		
		System.out.println(check_num);
		
		//주민번호의 마지막 숫자
		int last_num = jumin_no.charAt(13)-'0';
		
		return (check_num == last_num);
	}
	
	
	public boolean isValid2() {
		
		int sum = 0;
		//'8'-'0' -> 8
	
//		sum = sum + ( (jumin_no.charAt(0)-'0') * 2 );
//		sum = sum + ( (jumin_no.charAt(1)-'0') * 3 );
//		sum = sum + ( (jumin_no.charAt(2)-'0') * 4 );
//		sum = sum + ( (jumin_no.charAt(3)-'0') * 5 );
//		sum = sum + ( (jumin_no.charAt(4)-'0') * 6 );
//		sum = sum + ( (jumin_no.charAt(5)-'0') * 7 );
//		// - SKIP
//		sum = sum + ( (jumin_no.charAt(7)-'0')  * 8 );
//		sum = sum + ( (jumin_no.charAt(8)-'0')  * 9 );
//		sum = sum + ( (jumin_no.charAt(9)-'0')  * 2 );
//		sum = sum + ( (jumin_no.charAt(10)-'0') * 3 );
//		sum = sum + ( (jumin_no.charAt(11)-'0') * 4 );
//		sum = sum + ( (jumin_no.charAt(12)-'0') * 5 );
		
		//for문을 활용
		int su = 2;
		
		for(int i=0;i<=12;i++) { // i = 0 1 2 3 ..... 12
			
			if(i==6) continue;

			sum = sum + ( (jumin_no.charAt(i)-'0') * su );
			su++;
			if(su>9) su = 2;
			
		}
		
		int check_num = sum%11;
		check_num     = 11 - check_num;
		check_num     = check_num%10; 
		
		System.out.println(check_num);
		
		//주민번호의 마지막 숫자
		int last_num = jumin_no.charAt(13)-'0';
		
		return (check_num == last_num);
	}
	
	
	public boolean isValid3() {
		
		int sum = 0;
		//'8'-'0' -> 8
	
//		sum = sum + ( (jumin_no.charAt(0)-'0') * 2 );
//		sum = sum + ( (jumin_no.charAt(1)-'0') * 3 );
//		sum = sum + ( (jumin_no.charAt(2)-'0') * 4 );
//		sum = sum + ( (jumin_no.charAt(3)-'0') * 5 );
//		sum = sum + ( (jumin_no.charAt(4)-'0') * 6 );
//		sum = sum + ( (jumin_no.charAt(5)-'0') * 7 );
//		// - SKIP                      6         0
//		sum = sum + ( (jumin_no.charAt(7)-'0')  * 8 );
//		sum = sum + ( (jumin_no.charAt(8)-'0')  * 9 );
//		sum = sum + ( (jumin_no.charAt(9)-'0')  * 2 );
//		sum = sum + ( (jumin_no.charAt(10)-'0') * 3 );
//		sum = sum + ( (jumin_no.charAt(11)-'0') * 4 );
//		sum = sum + ( (jumin_no.charAt(12)-'0') * 5 );
		
		//for문을 활용+체크수 배열
		//                0 1 2 3 4 5 6 7 8 9 101112  
		int [] check_su= {2,3,4,5,6,7,0,8,9,2,3,4,5};
		
		for(int i=0;i<=12;i++) { // i = 0 1 2 3 ..... 12
			
			if(i==6) continue;

			sum = sum + ( (jumin_no.charAt(i)-'0') * check_su[i] );
			
		}
		
		int check_num = sum%11;
		check_num     = 11 - check_num;
		check_num     = check_num%10; 
		
		System.out.println(check_num);
		
		//주민번호의 마지막 숫자
		int last_num = jumin_no.charAt(13)-'0';
		
		return (check_num == last_num);
	}
	
	
	public boolean isValid4() {
		
		int sum = 0;
		//'8'-'0' -> 8
	
//		sum = sum + ( (jumin_no.charAt(0)-'0') * 2 );
//		sum = sum + ( (jumin_no.charAt(1)-'0') * 3 );
//		sum = sum + ( (jumin_no.charAt(2)-'0') * 4 );
//		sum = sum + ( (jumin_no.charAt(3)-'0') * 5 );
//		sum = sum + ( (jumin_no.charAt(4)-'0') * 6 );
//		sum = sum + ( (jumin_no.charAt(5)-'0') * 7 );
//		// - SKIP                      6         0
//		sum = sum + ( (jumin_no.charAt(7)-'0')  * 8 );
//		sum = sum + ( (jumin_no.charAt(8)-'0')  * 9 );
//		sum = sum + ( (jumin_no.charAt(9)-'0')  * 2 );
//		sum = sum + ( (jumin_no.charAt(10)-'0') * 3 );
//		sum = sum + ( (jumin_no.charAt(11)-'0') * 4 );
//		sum = sum + ( (jumin_no.charAt(12)-'0') * 5 );
		
		//for문을 활용+체크수 배열
				
		//                  0123456789012  <- 
		String check_str = "2345670892345";
		
		for(int i=0;i<=12;i++) { // i = 0 1 2 3 ..... 12
			
			if(i==6) continue;

			sum = sum + ( (jumin_no.charAt(i)-'0') * (check_str.charAt(i)-'0') );
			
		}
		
		int check_num = sum%11;
		check_num     = 11 - check_num;
		check_num     = check_num%10; 
		
		System.out.println(check_num);
		
		//주민번호의 마지막 숫자
		int last_num = jumin_no.charAt(13)-'0';
		
		return (check_num == last_num);
	}
	
	
	
	
}

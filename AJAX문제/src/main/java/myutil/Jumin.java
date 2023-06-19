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
	    �ֹι�ȣ �����ڸ� ����
	            
	            ������      �ܱ���
	            ��  ��      ��  ��  
	    1800    9   0  
	    1900    1   2       5   6  
	    2000    3   4       7   8 
	*/
	
	
	public int getYear() {
		
		//�ֹι�ȣ �⵵�ڸ� 2�ڸ� ����
		String str_year = this.jumin_no.substring(0, 2);// "80"
		//���ڿ�->������ ��ȯ
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
	
	
	//���� ���ϱ�
	public int getAge() {
		
		//����⵵�� Calendar�� ���ؼ� ���Ѵ�
		Calendar now = Calendar.getInstance();
		int current_year = now.get(Calendar.YEAR);
		
		//����  =  ����⵵    -  ����⵵
		int age = current_year - this.getYear();
		
		return age;
	}
	
	//���� ���ϱ�
	public String getGender() {
		
		//���1:����->���ڷκ�ȯ
		//char gender = jumin_no.charAt(7);// '1'  
		//����������->���ڷ� ��ȯ(1���϶��� ����)
		//int  gender_number = gender - '0'; //0~9
		//                     49   -  48
		// '0':48  '1':49  '2':50
		
		//���2:���ڿ�->���ڷκ�ȯ
		String str_gender = jumin_no.substring(7,8);// "1"
		int gender_number = Integer.parseInt(str_gender);
				
		
	    // Ȧ���� ����
		if(gender_number%2==1) return "����";
		
		//if(gender_number==1 || gender_number==3 ||gender_number==5)
		//	return "����";
		
		return "����";
	}
	
	//���� ���ϱ�
	public String getSeason() {
		
		String str_month = jumin_no.substring( 2, 4 ); // "12"
		int    month     = Integer.parseInt(str_month);
		
		//���1:if
//		if(month>=3 && month<=5)  return "��";
//		if(month>=6 && month<=8)  return "����";
//		if(month>=9 && month<=11) return "����";
		
		//���2:switch
		switch(month/3)
		{
			case 1: return "��";
			case 2: return "����";
			case 3: return "����";
		}
		
				
		return "�ܿ�";
	}
	
	//�� ���ϱ�
	public String getTti() {
		
		//���1:�迭Ȱ��
		//��迭                    0      1      2      3     4 <= tti_index
//		String [] tti_array = { "������", "��" , "��" ,"����","��","��","ȣ����","�䳢","��","��","��","��"  };
//		int tti_index = this.getYear()%12;// 0~11
//		return tti_array[tti_index];
		
		//���2:switchȰ��
		int tti_index = this.getYear()%12;
		switch(tti_index)
		{
			case  0: return "������";
			case  1: return "��";
			case  2: return "��";
			case  3: return "����";
			case  4: return "��";
			case  5: return "��";
			case  6: return "ȣ����";
			case  7: return "�䳢";
			case  8: return "��";
			case  9: return "��";
			case 10: return "��";
		}
		
		return "��";
		
	}
	
	//10�� 
	// 4  5  6  7  8  9  0  1  2  3        <= �⵵%10(0~9) 
	// �� �� �� �� �� �� �� �� �� ��         
	
	//12��
	// 4  5  6  7  8  9  10 11 0  1  2  3  <= �⵵%12(0~11)
	// �� �� �� �� �� �� �� �� �� �� �� ��
	
	public String getGanji() {
		
		int year = this.getYear();
		
		//�迭                  0    1    2    3    4
		//char [] gan_array = {'��','��','��','��','��','��','��','��','��','��'};
		
		//                 0 1 2 3 4 5 6 7 8 9      <- �⵵%10
		String gan_list = "����Ӱ谩����������";
		// '��' -> gan_list.charAt(3)
		//                 0 1 2 3 4 5 6 7 8 9 1011 <- �⵵%12
		String ji_list  = "�������������ι��������";
		// '��' -> ji_list.charAt(7);
		
	    char gan = gan_list.charAt(year%10);
	    char ji  = ji_list.charAt(year%12);
	    	        
		String ganji = String.format("%c%c��", gan,ji);
		
		return ganji;
	}
	
	
	
	
	
	//             01234567890123   <- index
	// jumin_no = "801212-1234560"	
	
	//������� ���ϱ�
	public String getLocal() {
		
		String str_local = jumin_no.substring(8, 10);//"23"
		int    local     = Integer.parseInt(str_local);
		
		if(local>=0 && local<=8) return "����";
		if(local>=9 && local<=12) return "�λ�";
		if(local>=13 && local<=15) return "��õ";
		if(local>=16 && local<=25) return "��⵵";
		if(local>=26 && local<=34) return "������";
		if(local>=35 && local<=39) return "��û�ϵ�";
		if(local==40) return "����";
		if(local==44 || local==49) return "����Ư����";
		if(local>=41 && local<=47) return "��û����";
		if(local>=48 && local<=54) return "����ϵ�";
		if(local>=55 && local<=56) return "����";
		if(local>=57 && local<=66) return "���󳲵�";
		if(local>=67 && local<=69 || local==76) return "�뱸";
		if(local>=70 && local<=81) return "���ϵ�";
		if(local==85) return "��걤����";
		if(local>=82 && local<=92) return "���ϵ�";
		if(local>=93 && local<=95) return "����";
		
		
		return "��Ÿ";
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
		
		//�ֹι�ȣ�� ������ ����
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
		
		//for���� Ȱ��
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
		
		//�ֹι�ȣ�� ������ ����
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
		
		//for���� Ȱ��+üũ�� �迭
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
		
		//�ֹι�ȣ�� ������ ����
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
		
		//for���� Ȱ��+üũ�� �迭
				
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
		
		//�ֹι�ȣ�� ������ ����
		int last_num = jumin_no.charAt(13)-'0';
		
		return (check_num == last_num);
	}
	
	
	
	
}

package javagame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	
	public static final int SCREEN_WIDTH = 1280; //게임 화면 넓이 지정 
	public static final int SCREEN_HEIGHT = 720;  //게임 화면 높이 지정 바뀔 필요가 없기 때문에 final 사용
	
	   static ArrayList<String> Sign_id = new ArrayList<>(); // 회원가입 된 아이디
	   static ArrayList<String> Sign_pw = new ArrayList<>(); // 회원가입 된 비밀번호

	    public static void main(String [] args) throws IOException {

	        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in)); // 회원가입 및 로그인 입력을 위한 입력문 선언
	        String ID = ""; // 아이디
	        String PW = ""; // 비밀번호
	        while(true){
	            Sign_id = new ArrayList<>(); // 초기화
	            Sign_pw = new ArrayList<>();
	            Sign_up(); // 회원가입 된 아이디 & 비밀번호 불러오기
	            System.out.print("1.회원가입\n2.로그인\n3.프로그램 종료\n선택 : ");
	            String Ch = bf.readLine();


	            if("1".equals(Ch)){
	                System.out.print("ID :");
	                String ID_Ch = bf.readLine(); // [회원가입] 아이디 입력

	                System.out.print("PW :");
	                String PW_Ch = bf.readLine(); // [회원가입] 비밀번호 입력

	                ID = ID_Ch; //입력한 아이디 저장
	                PW = PW_Ch; // 입력한 비빌번호 저장

	                String result = save(ID , PW);
	                if("Fall".equals(result)){ // 반환값이 fall과 success뿐이기때문에 fall이 아닌 다른값은 success만 넘어옵니다.
	                    System.out.println("이미 존재하는 유저 입니다");
	                }else{
	                    System.out.println("회원가입 성공!!");
	                }

	            }else if("2".equals(Ch)){
	                System.out.print("ID :");
	                String ID_Ch = bf.readLine();

	                System.out.print("PW :");
	                String PW_Ch = bf.readLine();
	                boolean YN = true; // 조건을 걸기위한 boolean
	                for(int i = 0; i < Sign_id.size(); i++ ){
	                    if(Sign_id.get(i).equals(ID_Ch) && Sign_pw.get(i).equals(PW_Ch)){ // 아이디와 비밀번호가 같으면 성공
	                        System.out.println("로그인 성공!!");
	                        YN = true;
	                        new GameEnviorment();
	                        break; // 다시 루프문 처음으로 돌아가기
	                    }else{
	                        YN = false;
	                    }
	                }	
	                if(YN == false){
	                    System.out.println("입력하신 정보가 존재하지 않습니다"); // 하나라도 다르면 실패
	                }

	            }else if("3".equals(Ch)){
	            	bf.close();
	                return; // 종료
	            }else{
	                System.out.println("다시 입력해주세요");
	            }

	        }


	    }


	    public static void Sign_up(){ // 저장된 아이디 & 비밀번호 불러오기
	        File snig = new File("../snig.txt"); // 메모장 불러오기

	        try{
	            BufferedReader br = new BufferedReader(new FileReader(snig));
	            String[] str = br.readLine().split(","); // "," 를 기준으로 나눈다.
	            while(str != null){
	                Sign_id.add(str[0]); // 아이디
	                Sign_pw.add(str[1]); // 비밀번호
	                str = br.readLine().split(",");
	            }
	            br.close();
	        } catch (NullPointerException e){
	            e.getStackTrace();
	        } catch (FileNotFoundException e){
	            e.getStackTrace();
	        } catch (IOException e){
	            e.getStackTrace();
	        }
	    }

	    public static String save(String id, String pw){ // 입력한 아이디와 비밀번호 저장 메소드
	        File snig = new File("../snig.txt"); // 메모장 불러오기
	        for(int i = 0; i < Sign_id.size(); i++ ){
	            if(id.equals(Sign_id.get(i))){
	                return "Fall"; // 아이디가 중복이면 fall 반환
	            }
	        }
	        Sign_id.add(id); // 아이디 배열에 추가
	        Sign_pw.add(pw); // 비밀번호 배열에 추가
	        try{
	            FileWriter fw = new FileWriter(snig, true); // false 메모장 초기화후 새로 저장
	            for(int i = 0; i < Sign_id.size(); i++){
	                fw.write(Sign_id.get(i)+","+Sign_pw.get(i)+"\n"); // 아이디 & 비밀번호를 같이 입력
	                fw.flush();
	            }
	            fw.close();
	        } catch (Exception e){
	            e.getStackTrace();
	        }
	        return "success"; //성공적으로 저장되면 success 반환
	    }
	}
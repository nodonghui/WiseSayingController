package com.llwiseSaying.Controller;

import com.llwiseSaying.Service.WiseSayingService;
import com.llwiseSaying.WiseSaying;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class WiseSayingController {

    static WiseSayingService wiseSayingService=new WiseSayingService();

    static String inputValue;
    static BufferedReader br;
    public void pragrarmStart() throws IOException {

        System.out.println("== 명언 앱 ==");
        init();

        while(true) {

            System.out.print("명령) ");
            inputValue = br.readLine();
            inputValue=inputValue.trim();

            if(inputValue.equals("종료")) {
                wiseSayingService.saveId();
                break;
            }
            if(inputValue.equals("등록")) { enrollProcess(); }
            if(inputValue.equals("목록")) { viewProcess(); }
            if(inputValue.startsWith("삭제")) { deleteProcess(); }
            if(inputValue.startsWith("수정")) { modifyProcess(); }
            if(inputValue.equals("초기화")) { resetProcess(); break;}

        }
    }

    void init() {
        br=new BufferedReader(new InputStreamReader(System.in));
        wiseSayingService.init();
    }

    public static void enrollProcess() throws IOException {
        System.out.print("명언 : ");
        String content=br.readLine();
        System.out.print("작가 : ");
        String writer= br.readLine();
        //등록후 등록한 일련번호를 반환받는다
        int id=wiseSayingService.enrollWiseSaying(content,writer);
        System.out.println(id+"번 명언이 등록되었습니다.");

    }

    public static void viewProcess() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        List<String> wiseSayingList=wiseSayingService.getWiseSayingList();
        wiseSayingList.stream()
                .forEach(s -> System.out.println(s));
    }

    public static void deleteProcess() {
        String cmd=inputValue;

        try {
            int id=wiseSayingService.containId(cmd);
            //삭제하며 삭제된 일련번호 반환
            wiseSayingService.deleteWiseSaying(id);
            System.out.println(id+"번 명언이 삭제되었습니다.");


        } catch (NumberFormatException e) {
            System.out.println("삭제 명령어 형식을 맞춰주세요");
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void modifyProcess() {
        String cmd=inputValue;

        try {
            int id=wiseSayingService.containId(cmd);
            WiseSaying modifyWiseSaying=wiseSayingService.findWiseSaying(id);
            System.out.println("명언(기존) : "+ modifyWiseSaying.getContent());
            System.out.print("명언 : ");
            String content= br.readLine();
            System.out.println("작가(기존) : " + modifyWiseSaying.getWriter());
            System.out.print("작가 : ");
            String writer= br.readLine();

            wiseSayingService.modifyWiseSaying(id,content,writer);

            System.out.println(id+"번 명언이 수정되었습니다.");
            //수정된 내용 db반영

        } catch (NumberFormatException e) {
            System.out.println("삭제 명령어 형식을 맞춰주세요");
        }
        catch (IllegalArgumentException | IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public void resetProcess() {
        wiseSayingService.reset();

    }

    public void saveId() {
        wiseSayingService.saveId();
    }

}

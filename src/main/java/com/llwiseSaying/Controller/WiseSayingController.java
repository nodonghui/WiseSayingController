package com.llwiseSaying.Controller;

import com.llwiseSaying.Service.WiseSayingService;
import com.llwiseSaying.State;
import com.llwiseSaying.WiseSaying;
import static com.llwiseSaying.App.br;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class WiseSayingController {


    public WiseSayingService wiseSayingService=new WiseSayingService();

    String inputValue;


    public WiseSayingController() {
        init();
    }

    public State run(String inputValue) throws IOException {

        State state=State.PROCESS;
        this.inputValue=inputValue;

        if(inputValue ==null) { state=State.EXIT; }
        if(inputValue.equals("종료")) { exitProcess(); state= State.EXIT;}
        if(inputValue.equals("등록")) { enrollProcess(); }
        if(inputValue.equals("목록")) { viewProcess(); }
        if(inputValue.startsWith("삭제")) { deleteProcess(); }
        if(inputValue.startsWith("수정")) { modifyProcess(); }
        if(inputValue.equals("초기화")) { resetProcess(); state=State.EXIT; }

        return state;

    }

    void init() {

        wiseSayingService.init();
    }

    void enrollProcess() throws IOException {
        System.out.print("명언 : ");
        String content=br.readLine();
        System.out.print("작가 : ");
        String writer= br.readLine();
        //등록후 등록한 일련번호를 반환받는다
        int id=wiseSayingService.enrollWiseSaying(content,writer);
        System.out.println(id+"번 명언이 등록되었습니다.");

    }

    void viewProcess() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        List<String> wiseSayingList=wiseSayingService.getWiseSayingList();
        wiseSayingList.stream()
                .forEach(s -> System.out.println(s));
    }

    void deleteProcess() {
        String cmd=inputValue;

        try {
            int id=wiseSayingService.containId(cmd);
            //삭제하며 삭제된 일련번호 반환
            wiseSayingService.deleteWiseSaying(id);
            System.out.println(id+"번 명언이 삭제되었습니다.");


        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

    void modifyProcess() {
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
            System.out.println(e.getMessage());
        }
        catch (IllegalArgumentException | IOException e) {
            System.out.println(e.getMessage());
        }

    }

    void resetProcess() {
        wiseSayingService.reset();


    }

    void exitProcess() {
        wiseSayingService.saveId();
        System.out.println("프로그램 종료");

    }

    void saveId() {
        wiseSayingService.saveId();
    }

}

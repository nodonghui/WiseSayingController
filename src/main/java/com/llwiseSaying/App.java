package com.llwiseSaying;

import com.llwiseSaying.Controller.WiseSayingController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {

    WiseSayingController wiseSayingController=new WiseSayingController();
    public static BufferedReader br;

    public void run() throws IOException {
        br=new BufferedReader(new InputStreamReader(System.in));
        String UserInput;
        State state;
        System.out.println("== 명언 앱 ==");
        System.out.println("명령어 -> 종료, 등록, 목록, 삭제, 수정, 초기화");
        System.out.println("삭제와 수정 명령어 형식 ( 삭제?id=1 )");

        while(true) {

            System.out.print("명령) ");
            UserInput=br.readLine();
            state=wiseSayingController.run(UserInput);

            if(state.equals(State.EXIT)) { break; }
        }
    }
}

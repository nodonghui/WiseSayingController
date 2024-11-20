package com.llwiseSaying;

import com.llwiseSaying.Controller.WiseSayingController;

import java.io.IOException;

public class Application {

    static WiseSayingController wiseSayingController=new WiseSayingController();
    public static void main(String[] args) {

        try {
            wiseSayingController.pragrarmStart();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        System.out.println("프로그램 종료");
    }
}

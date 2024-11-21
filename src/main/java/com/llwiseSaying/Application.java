package com.llwiseSaying;

import com.llwiseSaying.Controller.WiseSayingController;

import java.io.IOException;

public class Application {

    static WiseSayingController wiseSayingController=new WiseSayingController();
    public static void main(String[] args) {

        try {
            wiseSayingController.run();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }


    }
}

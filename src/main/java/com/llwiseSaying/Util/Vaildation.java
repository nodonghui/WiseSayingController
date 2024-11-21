package com.llwiseSaying.Util;

import com.llwiseSaying.WiseSaying;

import java.util.Map;

public class Vaildation {

    public void vaildationId(int id, Map<Integer, WiseSaying> wiseSayings) {
        if(!wiseSayings.containsKey(id)) {
            throw new IllegalArgumentException(id+"번 명언은 존재하지 않습니다.");
        }
    }
}

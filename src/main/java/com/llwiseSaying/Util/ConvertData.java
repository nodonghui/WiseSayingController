package com.llwiseSaying.Util;

public class ConvertData {

    public int splitData(String cmd) {
        String[] splitData1=cmd.split("\\?");
        if(splitData1.length !=2) {throw new IllegalArgumentException("명령어 형식을 맞춰주세요.");}
        String[] splitData2=splitData1[1].split("=");
        if(splitData2.length !=2) {throw  new IllegalArgumentException("명령어 형식을 맞춰주세요.");}

        return Integer.parseInt(splitData2[1]);
    }
}

package com.llwiseSaying.Util;

public class ConvertData {
    Vaildation vaildation=new Vaildation();
    public int splitDeleteAndModifyCmd(String cmd) {
        vaildation.vaildationKeyword(cmd,"\\?");
        String[] splitData1=cmd.split("\\?");

        vaildation.vaildationKeyword(cmd,"=");
        String[] splitData2=splitData1[1].split("=");

        //id return
        return Integer.parseInt(splitData2[1]);
    }

    public String [] splitSearchCmd(String cmd) {
        vaildation.vaildationKeyword(cmd,"\\?");
        String[] splitData1=cmd.split("\\?");

        vaildation.vaildationKeyword(splitData1[1],"&");
        String[] splitData2=splitData1[1].split("&");

        vaildation.vaildationKeyword(splitData2[0],"=");
        String[] splitDataType=splitData2[0].split("=");
        vaildation.vaildationKeyword2("keywordType",splitDataType[0]);
        //keywordType author 에서 keywordType을 타이핑 한게 맞는지

        vaildation.vaildationKeyword(splitData2[1],"=");
        String[] splitDataKeyword=splitData2[1].split("=");
        vaildation.vaildationKeyword2("keyword",splitDataKeyword[0]);

        String[] result=new String[2];
        result[0]=splitDataType[1];
        result[1]=splitDataKeyword[1];

        return result;
    }
}

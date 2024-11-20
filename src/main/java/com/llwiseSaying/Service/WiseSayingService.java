package com.llwiseSaying.Service;

import com.llwiseSaying.Repository.WiseSayingRepository;
import com.llwiseSaying.WiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WiseSayingService {

    static WiseSayingRepository wiseSayingRepository=new WiseSayingRepository();
    static Map<Integer, WiseSaying> wiseSayings;
    static int id;
    public void init() {
        id= wiseSayingRepository.loadId();
        wiseSayings=wiseSayingRepository.loadWiseSays();
    }

    public int enrollWiseSaying(String content,String writer) {
        WiseSaying wiseSaying=new WiseSaying(id,content,writer);
        wiseSayings.put(id,wiseSaying);

        id+=1;
        wiseSayingRepository.makeWiseSayingFile(wiseSaying);

        //이미 다음 일련번호로 증가시킴
        return id-1;
    }

    public List<String> getWiseSayingList() {
        List<String> wiseSayingList=new ArrayList<>();

        for (Map.Entry<Integer, WiseSaying> entry : wiseSayings.entrySet()) {
            wiseSayingList.add(entry.getValue().toString());
        }

        return wiseSayingList;
    }

    public int containId(String cmd) {
        int id=splitData(cmd);
        vaildationId(id,wiseSayings);
        return id;
    }


    public void deleteWiseSaying(int id) {

        wiseSayings.remove(id);
        wiseSayingRepository.deleteWiseSayingFile(id);

    }


    void vaildationId(int id,Map<Integer, WiseSaying> wiseSayings) {
        if(!wiseSayings.containsKey(id)) {
            throw new IllegalArgumentException(id+"번 명언은 존재하지 않습니다.");
        }
    }

    public int splitData(String cmd) {
        String[] splitData1=cmd.split("\\?");
        if(splitData1.length !=2) {throw new IllegalArgumentException("삭제 명령어 형식을 맞춰주세요.");}
        String[] splitData2=splitData1[1].split("=");
        if(splitData2.length !=2) {throw  new IllegalArgumentException("삭제 명령어 형식을 맞춰주세요");}

        return Integer.parseInt(splitData2[1]);
    }

    public WiseSaying findWiseSaying(int id) {
        return wiseSayings.get(id);
    }


    public void modifyWiseSaying(int id,String content,String writer) {

        WiseSaying wiseSaying=wiseSayings.get(id);
        wiseSaying.setContent(content);
        wiseSaying.setWriter(writer);
        wiseSayingRepository.makeWiseSayingFile(wiseSaying);

    }

    public void reset() {
        wiseSayingRepository.reset();
        wiseSayings.clear();
    }

    public void saveId() {
        wiseSayingRepository.saveId(id-1);
    }



}

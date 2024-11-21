package com.llwiseSaying.Service;

import com.llwiseSaying.Repository.WiseSayingRepository;
import com.llwiseSaying.Util.ConvertData;
import com.llwiseSaying.Util.Vaildation;
import com.llwiseSaying.WiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WiseSayingService {

    public  WiseSayingRepository wiseSayingRepository=new WiseSayingRepository();
    Vaildation vaildation=new Vaildation();
    ConvertData convertData=new ConvertData();

    Map<Integer, WiseSaying> wiseSayings;
    int id;
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
        int id=convertData.splitData(cmd);
        vaildation.vaildationId(id,wiseSayings);
        return id;
    }


    public void deleteWiseSaying(int id) {

        wiseSayings.remove(id);
        wiseSayingRepository.deleteWiseSayingFile(id);

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

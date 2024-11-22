package com.llwiseSaying.Service;

import com.llwiseSaying.Repository.WiseSayingRepository;
import com.llwiseSaying.Util.ConvertData;
import com.llwiseSaying.Util.Vaildation;
import com.llwiseSaying.WiseSaying;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class WiseSayingService {

    final int PAGESIZE=5;

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

    public List<String> getWiseSayingList(String keywordType,String keyword,int page) {
        List<WiseSaying> wiseSayingList=new ArrayList<>();
        //index관리를 위해 1부터 시작하도록


        String searchResult="";
        WiseSaying result;
        for (Map.Entry<Integer, WiseSaying> entry : wiseSayings.entrySet()) {
            result=entry.getValue();

            if(keywordType.equals("author")){ searchResult=entry.getValue().getWriter(); }
            if(keywordType.equals("content")){ searchResult=entry.getValue().getContent(); }

            if(containKeyword(keyword,searchResult)) {
                wiseSayingList.add(result);
            }

        }

        List<String> PagingWiseSayingList=makePagingList(wiseSayingList,page);

        return PagingWiseSayingList;
    }

    public boolean containKeyword(String keyword,String searchResult) {
        return keyword.equals("none") || searchResult.contains(keyword);
    }

    List<String> makePagingList(List<WiseSaying> wiseSayingList,int page) {
        List<String> pagingWiseSayingList=new ArrayList<>();

        int totalWiseSaying=wiseSayingList.size();
        int offset=(page-1)*PAGESIZE;
        int totalPage=(totalWiseSaying/PAGESIZE)+1;
        //만약 최대 범위가 리스트 범위를 넘어서면 리스트 최대 크기로 설정한다.
        int maxRange=Math.min(offset+PAGESIZE,totalWiseSaying);
        for(int i=offset;i<(maxRange);i++) {
            pagingWiseSayingList.add(wiseSayingList.get(i).toString());
        }

        //최근 게시물부터 출력
        Collections.reverse(pagingWiseSayingList);
        //마지막 요소값에 전체 페이지 수를 저장한다.
        //출력시 마지막 요소값 제외하고 출력하면됨
        pagingWiseSayingList.add(String.valueOf(totalPage));

        return pagingWiseSayingList;
    }


    public int containId(String cmd) {
        int id=convertData.splitDeleteAndModifyCmd(cmd);
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

    public boolean containSerachKeyword(String cmd) {

        return !cmd.equals("목록");
    }

    public String[] separateKeywordTypeAndKeyword(String query) {
        return convertData.splitSearchQuery(query);
    }

    public int separatePage(String query) {
        return convertData.splitPage(query);
    }

}

package com.llwiseSaying.Repository;

import com.llwiseSaying.WiseSaying;

import java.util.Map;

public class WiseSayingRepository {

    static IdGenerator idGenerator=new IdGenerator();
    static WiseSayingGenerator wiseSayingGenerator=new WiseSayingGenerator();
    static LoadWiseSayingList loadWiseSayingList=new LoadWiseSayingList();
    static DatabaseReset databaseReset=new DatabaseReset();

    public int loadId() {

        int id=idGenerator.loadFile();

        return id;
    }

    public Map<Integer, WiseSaying> loadWiseSays() {
        return loadWiseSayingList.loadWiseSayings();
    }

    public void makeWiseSayingFile(WiseSaying wiseSaying) {
        wiseSayingGenerator.wirteFile(wiseSaying);
    }

    public void deleteWiseSayingFile(int id) {
        wiseSayingGenerator.deleteFile(id);
    }

    public void reset() {
        databaseReset.reset();
    }

    public void saveId(int id) {
        idGenerator.makeFile(id);
    }

}

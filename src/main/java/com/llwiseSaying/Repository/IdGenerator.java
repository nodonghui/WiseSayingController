package com.llwiseSaying.Repository;

import java.io.*;

public class IdGenerator {

    public void makeFile(int id) {
        String filePath = "db/wiseSaying/lastId.txt";
        String content = String.valueOf(id);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content); // 데이터 쓰기

        } catch (IOException e) {
            System.out.println("id.txt파일 작성 예외 발생");
        }

    }

    public int loadFile() {
        String directoryPath = "db/wiseSaying";
        String fileName="lastId.txt";
        File file = new File(directoryPath, fileName);
        if (file.exists() && file.isFile()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(directoryPath+"/"+fileName))) {
                String line;
                line = reader.readLine();
                //마지막 일련번호 다음 부터 등록해야함
                return Integer.parseInt(line)+1;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return 1;
    }
}

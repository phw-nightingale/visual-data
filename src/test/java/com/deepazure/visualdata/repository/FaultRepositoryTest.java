package com.deepazure.visualdata.repository;

import com.deepazure.visualdata.VisualDataApplicationTests;
import com.deepazure.visualdata.entity.Fault;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.var;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FaultRepositoryTest extends VisualDataApplicationTests {

    @Autowired
    private FaultRepository faultRepository;

    @Test
    public void test() {
//        try {
//            StringBuilder sb = new StringBuilder();
//            File file = new File("C:\\Workspaces\\IdeaProjects\\visualdata\\src\\main\\resources\\static\\faults.json");
//            System.out.println(file);
//            Scanner scanner = new Scanner(file, "utf-8");
//            while (scanner.hasNext()) {
//                sb.append(scanner.nextLine());
//            }
//            scanner.close();
//            System.out.println(sb.toString());
//            List<Fault> faults = new ArrayList<>();
//            Gson gson = new Gson();
//            faults = gson.fromJson(sb.toString(), new TypeToken<List<Fault>>() {
//            }.getType());
//            System.out.println(faults.size());
//
//            for (var item :
//                    faults) {
//                faultRepository.save(item);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}

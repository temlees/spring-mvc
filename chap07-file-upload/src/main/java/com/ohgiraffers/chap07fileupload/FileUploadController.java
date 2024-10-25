package com.ohgiraffers.chap07fileupload;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class FileUploadController {
//"/img/single/**
    @PostMapping("/single-file")
    public String singleFileUpload(@RequestParam MultipartFile singleFile,
                                   String singleFileDescription, Model model) {

        System.out.println("singleFile: " + singleFile);
        /*
        * StandardMultipartHttpServletRequest
        *  - spring에서 multipart 요청을 처리하기 위한 클래스
        *
        * $StandardMultipartFile@52bc671e
        * - 업로드 된 파일을 나타내는 객체
        * -실제 파일 데이터에 대한 접근을 제공
        * */
        System.out.println("singleFileDescription: " + singleFileDescription);

        //파일을 저장할 경로 설정
        String filePath = "C:/uploads/single";
        File fileDir = new File(filePath);
        if(!fileDir.exists()) {
            //파일 저장 전 폴더 존재 유무 확인
            //경로가 없으면 생성한다..
            fileDir.mkdirs();
        }
        //사용자가 업로드한 원본파일 이름
        String originFileName = singleFile.getOriginalFilename();
        //고유한 파일이름을 만들어서 같이저장

        //확장자 추출  뒤에서부터 .을 기준으로(확장자만 잘리게됨)
        String ext =originFileName.substring(originFileName.lastIndexOf("."));


        //특정한 고유한 식별자 생성해주는 메소드
        String savedName = UUID.randomUUID().toString().replace("-","")+ext;

        //업로드된 파일을 지정된 경로와 고유한 파일이름으로 저장한다
        try {
            singleFile.transferTo(new File(filePath+"/"+savedName));

            //여기에 db 저장 로직 추가하면 된다..
            model.addAttribute("message","파일 업로드 성공!!");

            model.addAttribute("img","/img/single/"+savedName);

        } catch (IOException e) {
           e.printStackTrace();
            model.addAttribute("message","파일 업로드 실패 ㅠㅠㅠㅠ");
        }

        return "result";
    }

    @PostMapping("/multi-file")
    public String multiUpLoad(@RequestParam MultipartFile[] multipartFile, Model model) {
        String path = "C:/uploads/multi";
        File fileDir = new File(path);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        List<String> fileList = new ArrayList<>();

        for (MultipartFile multi : multipartFile) {
            String originalFileName = multi.getOriginalFilename();
            String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
            String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
            try {
                multi.transferTo(new File(path + "/" + savedName));
                fileList.add("/img/multi/"+savedName);
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("message", "파일 업로드 실패 ㅠㅠㅠㅠ");
                return "result";  // 실패하면 바로 반환
            }
        }

        model.addAttribute("message", "파일 업로드 성공!!");
        model.addAttribute("fileList", fileList);  // 업로드된 파일 리스트를 모델에 추가

        return "result";
    }
}

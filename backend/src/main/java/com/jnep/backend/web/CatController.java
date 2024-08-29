package com.jnep.backend.web;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.jnep.backend.Util.CommUtil;
import com.jnep.backend.Util.FileUploadUtil;
import com.jnep.backend.Util.MessageUtil;
import com.jnep.backend.domain.Cat;
import com.jnep.backend.dto.CatDto;
import com.jnep.backend.dto.UploadFile;
import com.jnep.backend.service.CatService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequiredArgsConstructor
@Slf4j
public class CatController {
    
    private final CatService catService;

    private final FileUploadUtil fileUploadUtil;
    @GetMapping("/catList")
    public String catList(Model model) {

        String keyword = "";

        List<Cat> searchList = catService.search(keyword);

        log.info("searchList  ::  " + searchList.toString());

        model.addAttribute("searchList", searchList);

        return "cat/catList";
    }

    @GetMapping("/catSave")
    public String goCatSave(Model model) {
        model.addAttribute("catDto", new CatDto());
        return "cat/catSave";
    }

    @PostMapping("/catSave")
    public String catSave(CatDto catDto, Model model) {
        
        log.info("sssss ");
        
        
        if(catDto.getFile() != null){
            
            MultipartFile file = catDto.getFile();
            
            try {
                UploadFile uploadFile = fileUploadUtil.storeFile(file);

                Cat cat = catDto.toEntity(null, uploadFile);

                catService.catSave(cat);

                log.info("catDto :: " + catDto.toString());
                log.info("uploadFile :: " + uploadFile.toString());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        CommUtil.goSuccessPage(model, MessageUtil.SaveSuccessMessage(), "이전 페이지로 돌아가기", "/cat/catList");

        return "cat/catSave";
    }

    
    @PostMapping("/catUpdate")
    public String goCatUpdate(Model model, HttpServletRequest request) {

        String catId = String.valueOf(request.getParameter("catId"));

        Cat byCatId = catService.findByCatId(Long.valueOf(catId));

        UploadFile uploadFile = new UploadFile(byCatId.getFileName(), byCatId.getSaveFileName(), byCatId.getFileSize(), byCatId.getFileExt(), byCatId.getFileCours());

        CatDto catDto = new CatDto(
            byCatId.getCatId()
            , byCatId.getCatName()
            , byCatId.getCatAge()
            , byCatId.getCatDis()
            , byCatId.getCatSpci()
            , byCatId.getCatWeight()
            , byCatId.getCatSex()
            , uploadFile);

        model.addAttribute("catDto", catDto);

        return "cat/catUpdate";
    }
}

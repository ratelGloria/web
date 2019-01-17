package com.two.controller.backend;

import com.two.controller.ServerResponse;
import com.two.service.ProductManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
@RequestMapping("/manage/product")
public class UploadController {

    @Autowired
    ProductManageService productManageService;

   /*@RequestMapping(value = "/upload",method = RequestMethod.GET)
    public String upload(){

        return "upload";
    }*/

    @RequestMapping(value = "/upload")
    @ResponseBody
    public ServerResponse upload2(@RequestParam(value = "upload") MultipartFile upload){


        String paht = "D:\\ftpfile";

        return productManageService.upload(upload,paht);


    }

    @RequestMapping(value = "/upload12")

    public String upload0(){

System.out.println("-----------------------------------------");
        String paht = "D:\\ftpfile";

        return "frontpage/test";


    }
}

package com.example.helloworld.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
public class UploadFileController {

    // 从配置文件注入图片访问基础URL（无需硬编码，部署时修改配置即可）
    @Value("${image.base.url}")
    private String imageBaseUrl;

    // 从配置文件注入图片存储本地路径（与上传逻辑统一，避免不一致）
    @Value("${file.upload.local-path}")
    private String uploadLocalPath;

    @RequestMapping(value = "/upload", method=RequestMethod.POST)
    public String upload(String nickname, MultipartFile photo, HttpServletRequest request) throws IOException{
        // 上传文件格式的数据，前端需要将数据格式修改为form-data类型
        // 前端需要设置请求头Content-Type: multipart/form-data
        System.out.println(nickname);
        // 获取上传图片的原始名称
        System.out.println(photo.getOriginalFilename());
        // 获取图片类型
        System.out.println(photo.getContentType());
        // 获取图片大小
        System.out.println(photo.getSize());
        
        // 设置保存图片的路径 - 这里设置到配置环境中，这里放在项目的根目录下，生产环境设置到项目外的文件夹，数据与代码分离
        // String path = "/home/withboom/uploadImages/";
        // 保存文件到指定路径
        String fileName = saveFile(photo, uploadLocalPath);

        // 若文件上传成功，返回完整访问URL；否则返回错误信息
        if (fileName != null) {
            return "上传成功！图片访问地址：" + imageBaseUrl + fileName;
        } else {
            return "上传失败：文件为空";
        }
    }
    
    // 保存文件到指定路径
    private String saveFile(MultipartFile photo, String path) throws IOException {
        // 判断存储文件的目录是否存在
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String fileName = null;
        if (!photo.isEmpty()) {
            // 获取原始文件名
            String originalFileName = photo.getOriginalFilename();
            // 生成UUID文件名（避免重名）
            String suffix = originalFileName.substring(originalFileName.lastIndexOf("."));
            // 文件名结构：UUID + 后缀名，如：3f7c17a0-d56e-4d9f-8d9d-a7d5d5555555.jpg
            fileName = UUID.randomUUID().toString() + suffix;
            
            // 拼接文件完整路径并转存
            File targetFile = new File(path + fileName);
            photo.transferTo(targetFile);
            System.out.println("图片保存成功：" + targetFile.getAbsolutePath());
        }
        return fileName;
    }
}

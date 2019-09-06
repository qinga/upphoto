package com.upphoto.controller;

import com.upphoto.common.FileUtilOp;
import com.upphoto.common.ServerResponse;
import com.upphoto.entity.PhoneType;
import com.upphoto.service.UploadImageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;


@RequestMapping(value = "/")
@Controller
public class UploadImageController {
    private final UploadImageService uploadImageService;

    @Autowired
    private UploadImageController(UploadImageService uploadImageService) {
        this.uploadImageService = uploadImageService;
    }

    @RequestMapping(value = "/thumb", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse GenerateImage(
            @RequestParam(value = "obverse_addr", required = false) CommonsMultipartFile obverse_file,
            @RequestParam(value = "reverse_addr", required = false) CommonsMultipartFile reverse_file,
            @RequestParam(value = "top_addr", required = false) CommonsMultipartFile top_file,
            @RequestParam(value = "bottom_addr", required = false) CommonsMultipartFile bottom_file,
            @RequestParam(value = "left_addr", required = false) CommonsMultipartFile left_file,
            @RequestParam(value = "right_addr", required = false) CommonsMultipartFile right_file,

            @RequestParam(value = "left_top_addr", required = false) CommonsMultipartFile left_top_file,
            @RequestParam(value = "right_top_addr", required = false) CommonsMultipartFile right_top_file,
            @RequestParam(value = "left_bottom_addr", required = false) CommonsMultipartFile left_bottom_file,
            @RequestParam(value = "right_bottom_addr", required = false) CommonsMultipartFile right_bottom_file,
            HttpServletRequest request) {

        //根据相对路径获取绝对路径，图片上传后位于元数据中
        String realUploadPath = request.getServletContext().getRealPath("/") + "images/rawImages";
        System.out.println(realUploadPath);
        System.out.println(request.getSession().getServletContext().getRealPath("/"));
        System.out.println(request.getSession().getServletContext().getRealPath(""));
        Map<String, CommonsMultipartFile> files = new HashMap<>();
        files.put("obverse_addr", obverse_file);
        files.put("reverse_addr", reverse_file);
        files.put("top_addr", top_file);
        files.put("bottom_addr", bottom_file);
        files.put("left_addr", left_file);
        files.put("right_addr", right_file);


        files.put("left_top_addr", left_top_file);
        files.put("right_top_addr", right_top_file);
        files.put("left_bottom_addr", left_bottom_file);
        files.put("right_bottom_addr", right_bottom_file);

        //获取上传后原图的相对地址
        Map<String, String> urls = this.handlerImage(files, realUploadPath);
        String classify = request.getParameter("classify");
        String arr_id = request.getParameter("arr_id");
        String price = request.getParameter("price");

        if (!StringUtils.isBlank(classify) && urls.size() != 0 && arr_id != null) {
            uploadImageService.insertPic(classify, arr_id, price, urls);
            ServerResponse response = ServerResponse.createBySuccessMessage("SUCCESS");
            return response;
        } else {
            ServerResponse response = ServerResponse.createByErrorCodeMessage(401, "EMPTY");
            return response;
        }
    }


    @ResponseBody
    @RequestMapping(value = "/delete/{typeId}/{arr_id}")
    public ServerResponse deleteByTypeIdArrId(@PathVariable("typeId") String typeId,
                                              @PathVariable("arr_id") String arr_id) {
        ServerResponse response = null;
        if (!StringUtils.isBlank(typeId) || !StringUtils.isBlank(arr_id)) {
            Integer count = uploadImageService.deleteByTypeIdArrId(typeId, arr_id);
            // 删除成功
            if (count > 0) {
                response = ServerResponse.createBySuccessMessage("SUCCESS");
            } else {
                // 数据库没有这条数据
                response = ServerResponse.createByErrorCodeMessage(401, "EMPTY");
            }
        } else {
            // 前端传递参数有误
            response = ServerResponse.createByErrorCodeMessage(400, "传递参数有误");
        }
        return response;
    }

    /**
     * @return
     * @desc 获取某个phone类型的所有图片数据
     */
    @RequestMapping(value = "/getOnePhone/{typeId}", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<List<Map<String, String>>> getOnePhone(@PathVariable(value = "typeId") String typeId) {
        if (!StringUtils.isBlank(typeId)) {
            List<Map<String, String>> typePhone = uploadImageService.getOnePhone(typeId);

            if (typePhone != null && typePhone.size() != 0) {
                ServerResponse<List<Map<String, String>>> resData =
                        ServerResponse.createBySuccess("SUCCESS", typePhone);
                return resData;
            } else {
                // 暂无数据
                ServerResponse resData =
                        ServerResponse.createByErrorCodeMessage(401, "EMPTY");
                return resData;
            }
        } else {
            // 传递参数有误，处理为暂无数据
            ServerResponse resData =
                    ServerResponse.createByErrorCodeMessage(30, "ERROR_PARAM");
            return resData;
        }
    }


    @RequestMapping(value = "/getPhoneById/{phoneId}", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<Map<String, String>> getPhoneById(@PathVariable(value = "phoneId") String phoneId) {
        if (!StringUtils.isBlank(phoneId)) {
            Map<String, String> onePhone = uploadImageService.getPhoneById(phoneId);

            if (onePhone != null) {
                ServerResponse<Map<String, String>> resData =
                        ServerResponse.createBySuccess("SUCCESS", onePhone);
                return resData;
            } else {
                // 暂无数据
                ServerResponse resData =
                        ServerResponse.createByErrorCodeMessage(401, "EMPTY");
                return resData;
            }
        } else {
            // 传递参数有误，处理为暂无数据
            ServerResponse resData =
                    ServerResponse.createByErrorCodeMessage(30, "ERROR_PARAM");
            return resData;
        }
    }

    /**
     * @return
     * @desc 返回所有的手机类型，id type name
     */
    @RequestMapping(value = "/getAllPhoneType", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<List<PhoneType>> getPhoneType() {
        List<PhoneType> resData = uploadImageService.getPhoneType();

        if (resData != null && resData.size() > 0) {
            ServerResponse<List<PhoneType>> response =
                    ServerResponse.createBySuccess("SUCCESS", resData);
            return response;
        } else {
            // 数据为空
            ServerResponse<List<PhoneType>> response =
                    ServerResponse.createByErrorCodeMessage(401, "EMPTY");
            return response;
        }
    }

    /**
     * @param files
     * @param realUploadPath
     * @return Map<position, str>
     * @desc 图片封装成map集合
     */
    private Map<String, String> handlerImage(Map<String, CommonsMultipartFile> files,
                                             String realUploadPath) {
        Map<String, String> urls = new HashMap<>();
        //如果目录不存在则创建目录
        File uploadFile = new File(realUploadPath);
        if (!uploadFile.exists()) {
            uploadFile.mkdirs();
        }
        //创建输入流
        InputStream inputStream;
        OutputStream outputStream;
        try {
            for (Map.Entry<String, CommonsMultipartFile> file : files.entrySet()) {
                if (file.getValue() != null) {
                    inputStream = file.getValue().getInputStream();
                    //生成输出地址URL
                    String outputPath = realUploadPath + "/" + FileUtilOp.setPicName(".jpeg");
                    urls.put(file.getKey(), outputPath.substring(outputPath.indexOf("images")));
                    //创建输出流
                    outputStream = new FileOutputStream(outputPath);
                    //设置缓冲区
                    byte[] buffer = new byte[1024];
                    //输入流读入缓冲区，输出流从缓冲区写出
                    while ((inputStream.read(buffer)) > 0) {
                        outputStream.write(buffer);
                    }
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //返回原图上传后的相对地址
        return urls;
    }
}


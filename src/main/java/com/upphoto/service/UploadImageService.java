package com.upphoto.service;

import com.upphoto.entity.PhoneType;

import java.util.List;
import java.util.Map;

public interface UploadImageService {
    /**
     * @param typeId
     * @param photos
     * @return 影响数据库的条数
     */
    Integer insertPic(String typeId, String arr_id, String price, Map<String, String> photos);

    /**
     * @param typeId
     * @return
     * @desc 根据typeId获取phone的所有图片
     */
    List<Map<String, String>> getOnePhone(String typeId);

    /**
     * @return
     * @desc 获取所有手机类型
     */
    List<PhoneType> getPhoneType();

    /**
     * @param phoneId
     * @return
     * @desc 根据id获取某个手机
     */
    Map<String, String> getPhoneById(String phoneId);

    /**
     * @param typeId
     * @param arr_id
     * @return
     * @desc 根据液晶类型和编号删除某个液晶
     */
    Integer deleteByTypeIdArrId(String typeId, String arr_id);
}

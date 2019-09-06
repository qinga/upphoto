package com.upphoto.dao;

import com.upphoto.entity.PhoneType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UploadImageDao {
    /**
     * 往数据库插入上传的图片
     *
     * @param typeId
     * @param photos
     * @return 影响数据库的条数
     */
    Integer insertPic(@Param("typeId") String typeId,
                      @Param("arr_id") String arr_id,
                      @Param("price") String price,
                      @Param("photos") Map<String, String> photos);

    /**
     * @param typeId
     * @return
     * @desc 根据typeId获取某个类型phone的所有图片
     */
    List<Map<String, String>> getOnePhone(String typeId);

    /**
     * @return
     * @desc 获取所有的phone类型
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

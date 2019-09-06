package com.upphoto.service.impl;

import com.upphoto.dao.UploadImageDao;
import com.upphoto.entity.PhoneType;
import com.upphoto.service.UploadImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UploadImagesServiceImpl implements UploadImageService {

    private final UploadImageDao uploadImageDao;

    @Autowired
    private UploadImagesServiceImpl(UploadImageDao uploadImageDao) {
        this.uploadImageDao = uploadImageDao;
    }

    @Override
    public Map<String, String> getPhoneById(String phoneId) {
        return uploadImageDao.getPhoneById(phoneId);
    }

    /**
     * @param typeId
     * @param arr_id
     * @return
     * @desc 根据液晶类型和编号删除某个液晶
     */
    @Override
    public Integer deleteByTypeIdArrId(String typeId, String arr_id) {
        return uploadImageDao.deleteByTypeIdArrId(typeId, arr_id);
    }

    @Override
    public Integer insertPic(String typeId, String arr_id, String price, Map<String, String> photos) {
        return uploadImageDao.insertPic(typeId, arr_id, price, photos);
    }

    @Override
    public List<Map<String, String>> getOnePhone(String typeId) {
        return uploadImageDao.getOnePhone(typeId);
    }

    @Override
    public List<PhoneType> getPhoneType() {
        return uploadImageDao.getPhoneType();
    }
}

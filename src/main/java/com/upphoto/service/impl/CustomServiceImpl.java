package com.upphoto.service.impl;

import com.upphoto.dao.CustomDao;
import com.upphoto.entity.Custom;
import com.upphoto.service.CustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomServiceImpl implements CustomService {

    private final CustomDao customDao;

    @Autowired
    private CustomServiceImpl(CustomDao customDao) {
        this.customDao = customDao;
    }

    @Override
    public Custom customLogin(Custom custom) {
        return customDao.customLogin(custom);
    }
}

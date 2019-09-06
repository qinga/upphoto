package com.upphoto.dao;

import com.upphoto.entity.Custom;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomDao {
    /**
     * @param custom
     * @return Custom
     * @author zgq
     */
    Custom customLogin(Custom custom);
}

package com.lseg.adapters.service;

import com.lseg.adapters.dto.DataDto;

public interface DataService<T> {
    void processAndSaveToRedis(DataDto<T> kafkaDto);
    void processAndSaveToPostgres(DataDto<T> kafkaDto);
    T retrieveDataFromRedis(Long id);
}

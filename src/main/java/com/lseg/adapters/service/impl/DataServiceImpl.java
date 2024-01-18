package com.lseg.adapters.service.impl;

import com.lseg.adapters.dto.DataDto;
import com.lseg.adapters.entity.DataEntity;
import com.lseg.adapters.repo.KafkaRepo;
import com.lseg.adapters.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@Service
public class DataServiceImpl<T> implements DataService {

    @Autowired
    private RedisTemplate<String, T> redisTemplate;
    @Autowired
    private KafkaRepo<T> kafkaRepository;
    @Override
    public void processAndSaveToRedis(DataDto kafkaDto) {
        T data = (T) kafkaDto.getData();
        redisTemplate.opsForValue().set("redis-key", data);
    }

    @Override
    public void processAndSaveToPostgres(DataDto kafkaDto) {
        T data = (T)kafkaDto.getData();

        // Save to Postgres
        DataEntity<T> kafkaEntity = new DataEntity<>(data);
        kafkaRepository.save(kafkaEntity);
    }

    @Override
    public Object retrieveDataFromRedis(Long id) {
        return redisTemplate.opsForValue().get("your-redis-key-" + id);
    }
}

package com.lseg.adapters.repo;

import com.lseg.adapters.entity.DataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KafkaRepo<T> extends JpaRepository<DataEntity<T>, Long> {

}

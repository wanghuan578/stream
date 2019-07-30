package com.spirit.stream.dao.repository;


import com.spirit.stream.dao.entity.TranslateBizInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TranslateRepository extends JpaRepository<TranslateBizInfo, Long> {
    List<TranslateBizInfo> findByEvent_ResourceId(String rid);
}

package com.gkh.springboot.mapper.elasticsearch;


import com.gkh.springboot.entity.UserES;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author gekaihui
 * @Description
 * @date 2020/11/11
 */
public interface UserESRepository extends ElasticsearchRepository<UserES, Long>{

}

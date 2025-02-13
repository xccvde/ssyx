package com.ssyx.search.repository;

import com.ssyx.model.search.SkuEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SkuRepository extends ElasticsearchRepository<SkuEs, Long> {
}

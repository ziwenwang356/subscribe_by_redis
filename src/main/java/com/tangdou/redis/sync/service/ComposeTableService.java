package com.tangdou.redis.sync.service;


import com.tangdou.redis.sync.entity.ComposeTable;

import java.util.List;

public interface ComposeTableService {
    List<ComposeTable> getAllData();

    ComposeTable getOne(Long id);
}

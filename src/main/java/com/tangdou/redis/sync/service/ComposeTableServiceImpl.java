package com.tangdou.redis.sync.service;


import com.tangdou.redis.sync.entity.ComposeTable;
import com.tangdou.redis.sync.repository.ComposeTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComposeTableServiceImpl implements ComposeTableService {


    @Autowired
    private ComposeTableRepository composeTableRepository;


    @Override
    public List<ComposeTable> getAllData() {
        return composeTableRepository.findAll();
    }

    @Override
    public ComposeTable getOne(Long id) {
        return composeTableRepository.getOne(id);
    }
}

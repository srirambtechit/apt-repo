package com.aptikraft.spring.dao;

import java.util.List;

public interface HibernateBulkOperationSupport<T> {
    
    int BLUK_INSERT_SIZE = 20;
    
    public void addBlukData(List<T> doObject);

    
}

package com.wwh.Service;

import com.wwh.Repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeService {

    @Autowired
    TypeRepository typeRepository;


}

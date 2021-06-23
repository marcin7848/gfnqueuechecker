package com.gfnqueuechecker.backend.service;

import com.gfnqueuechecker.backend.repository.LastSearchedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.gfnqueuechecker.backend.entity.LastSearched;

import java.util.List;

@Service
public class LastSearchedService {

    final LastSearchedRepository lastSearchedRepository;

    @Autowired
    public LastSearchedService(LastSearchedRepository lastSearchedRepository) {
        this.lastSearchedRepository = lastSearchedRepository;
    }

    public List<LastSearched> getLastSearched(){
        return this.lastSearchedRepository.getAllByOrderBySearchTimeDesc(PageRequest.of(0, 10));
    }


}

package com.gfnqueuechecker.backend.service;

import com.gfnqueuechecker.backend.entity.ServerGroup;
import com.gfnqueuechecker.backend.repository.ServerGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerGroupService {

    private final ServerGroupRepository serverGroupRepository;

    @Autowired
    public ServerGroupService(ServerGroupRepository serverGroupRepository) {
        this.serverGroupRepository = serverGroupRepository;
    }

    public Iterable<ServerGroup> getAll(){
        return serverGroupRepository.findAll();
    }

    public ServerGroup addNew(ServerGroup serverGroup){
        return serverGroupRepository.save(serverGroup);
    }

}

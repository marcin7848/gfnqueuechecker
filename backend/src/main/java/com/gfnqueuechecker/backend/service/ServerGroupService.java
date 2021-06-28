package com.gfnqueuechecker.backend.service;

import com.gfnqueuechecker.backend.entity.ServerGroup;
import com.gfnqueuechecker.backend.repository.ServerGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServerGroupService {

    private final ServerGroupRepository serverGroupRepository;

    @Autowired
    public ServerGroupService(ServerGroupRepository serverGroupRepository) {
        this.serverGroupRepository = serverGroupRepository;
    }

    public List<ServerGroup> getAll(){
        return serverGroupRepository.findAll();
    }

    public ServerGroup addNew(ServerGroup serverGroup){
        return serverGroupRepository.save(serverGroup);
    }

    public ServerGroup getById(Long id){
        return serverGroupRepository.findById(id).orElse(null);
    }

    public void delete(ServerGroup serverGroup){
        serverGroupRepository.delete(serverGroup);
    }

}

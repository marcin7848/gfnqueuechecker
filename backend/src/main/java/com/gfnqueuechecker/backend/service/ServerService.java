package com.gfnqueuechecker.backend.service;

import com.gfnqueuechecker.backend.entity.Server;
import com.gfnqueuechecker.backend.entity.ServerGroup;
import com.gfnqueuechecker.backend.repository.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerService {

    private final ServerRepository serverRepository;

    @Autowired
    public ServerService(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    public Server addNew(ServerGroup serverGroup, Server server){
        server.setServerGroup(serverGroup);
        return serverRepository.save(server);
    }

    public void delete(Server server){
        serverRepository.delete(server);
    }

    public void delete(ServerGroup serverGroup, Server server){
        serverGroup.getServers().removeIf(s -> s.getId().equals(server.getId())); //unpin bidirectional relationship
        serverRepository.delete(server);
    }

    public Server getByIdAndServerGroup(Long id, ServerGroup serverGroup){
        return serverRepository.findByIdAndServerGroup(id, serverGroup);
    }
}

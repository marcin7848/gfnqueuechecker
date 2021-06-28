package com.gfnqueuechecker.backend.service;
import com.gfnqueuechecker.backend.entity.CheckQueue;
import com.gfnqueuechecker.backend.entity.Game;
import com.gfnqueuechecker.backend.entity.ServerGroup;
import com.gfnqueuechecker.backend.repository.CheckQueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class CheckQueueService {

    private final CheckQueueRepository checkQueueRepository;
    private final GameService gameService;
    private final ServerGroupService serverGroupService;

    @Autowired
    public CheckQueueService(CheckQueueRepository checkQueueRepository, GameService gameService,
                             ServerGroupService serverGroupService) {
        this.checkQueueRepository = checkQueueRepository;
        this.gameService = gameService;
        this.serverGroupService = serverGroupService;
    }

    public String generateCheckQueue(Game game){
        List<ServerGroup> serverGroups = this.serverGroupService.getAll();
        List<CheckQueue> checkQueues = new ArrayList<>();
        String searchKey = this.generateString(15, "QWERTYUIOPASDFGHJKLZXCVBNM1234567890");
        serverGroups.forEach(sg -> {
            sg.getServers().forEach(s -> {
                checkQueues.add(new CheckQueue(0L, searchKey, new Timestamp(System.currentTimeMillis()),
                        0L, -1L, -1L, game, s));
            });
        });

        if(!this.checkQueueRepository.saveAll(checkQueues).iterator().hasNext()){
            return "";
        }

        return searchKey;
    }

    private String generateString(int len, String possibleCharacters){
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);
        for(int i = 0; i < len; i++)
            sb.append(possibleCharacters.charAt(rnd.nextInt(possibleCharacters.length())));
        return sb.toString();
    }

    public List<CheckQueue> getBySearchKey(String searchKey){
        return this.checkQueueRepository.findBySearchKey(searchKey);
    }
}

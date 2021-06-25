package com.gfnqueuechecker.backend.controller;

import com.gfnqueuechecker.backend.ErrorMessage;
import com.gfnqueuechecker.backend.entity.ServerGroup;
import com.gfnqueuechecker.backend.service.ServerGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/serverGroup")
public class ServerGroupController {

    private final ServerGroupService serverGroupService;

    @Autowired
    public ServerGroupController(ServerGroupService serverGroupService) {
        this.serverGroupService = serverGroupService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllServerGroups(){
        return new ResponseEntity<>(this.serverGroupService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> editSettings(@RequestBody ServerGroup serverGroup) {
        ServerGroup newServerGroup = serverGroupService.addNew(serverGroup);
        if (newServerGroup == null) {
            return ErrorMessage.send("The server group has not been added! Error!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(newServerGroup, HttpStatus.OK);
    }
}

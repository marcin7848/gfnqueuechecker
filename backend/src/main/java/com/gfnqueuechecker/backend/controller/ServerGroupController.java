package com.gfnqueuechecker.backend.controller;

import com.gfnqueuechecker.backend.ErrorMessage;
import com.gfnqueuechecker.backend.entity.Server;
import com.gfnqueuechecker.backend.entity.ServerGroup;
import com.gfnqueuechecker.backend.service.ServerGroupService;
import com.gfnqueuechecker.backend.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/serverGroup")
public class ServerGroupController {

    private final ServerGroupService serverGroupService;
    private final ServerService serverService;

    @Autowired
    public ServerGroupController(ServerGroupService serverGroupService, ServerService serverService) {
        this.serverGroupService = serverGroupService;
        this.serverService = serverService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllServerGroups(){
        return new ResponseEntity<>(this.serverGroupService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNew(@RequestBody ServerGroup serverGroup) {
        if(serverGroup.getServerGroupName() == null || serverGroup.getServerGroupName().trim().isEmpty()){
            return ErrorMessage.send("The server group name is empty! Error!", HttpStatus.BAD_REQUEST);
        }

        ServerGroup newServerGroup = serverGroupService.addNew(serverGroup);
        if (newServerGroup == null) {
            return ErrorMessage.send("The server group has not been added! Error!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(newServerGroup, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        ServerGroup serverGroup = serverGroupService.getById(id);
        if (serverGroup == null) {
            return ErrorMessage.send("The server group does not exist! Error!", HttpStatus.BAD_REQUEST);
        }
        serverGroupService.delete(serverGroup);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/{serverGroupId}/server/add")
    public ResponseEntity<?> addNewServer(@PathVariable("serverGroupId") Long serverGroupId,
                                          @RequestBody Server server) {

        ServerGroup serverGroup = serverGroupService.getById(serverGroupId);
        if (serverGroup == null) {
            return ErrorMessage.send("The server group does not exist! Error!", HttpStatus.BAD_REQUEST);
        }


        if(server.getServerHost() == null || server.getServerHost().trim().isEmpty()
                || server.getServerName() == null || server.getServerName().trim().isEmpty()){
            return ErrorMessage.send("The server fields are empty! Error!", HttpStatus.BAD_REQUEST);
        }

        Server newServer = serverService.addNew(serverGroup, server);
        if (newServer == null) {
            return ErrorMessage.send("The server has not been added! Error!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(newServer, HttpStatus.OK);
    }

    @DeleteMapping("/{serverGroupId}/server/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable("serverGroupId") Long serverGroupId, @PathVariable("id") Long id) {
        ServerGroup serverGroup = serverGroupService.getById(id);
        if (serverGroup == null) {
            return ErrorMessage.send("The server group does not exist! Error!", HttpStatus.BAD_REQUEST);
        }
        Server server = serverService.getByIdAndServerGroup(id, serverGroup);
        if (server == null) {
            return ErrorMessage.send("The server does not exist! Error!", HttpStatus.BAD_REQUEST);
        }

        serverService.delete(server);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}

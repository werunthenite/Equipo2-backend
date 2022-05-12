package com.itesm.azul.controllers;

import com.itesm.azul.models.Video;
import com.itesm.azul.dto.VideoDTO;
import com.itesm.azul.repositories.VideoRepository;
import com.itesm.azul.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;

import java.util.List;

@RestController
@RequestMapping("/v1/video")
public class VideoController {

    @Autowired
    VideoService videoService;
    //https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/java_dynamodb_code_examples.html

    //Create
    //insert a tuple
    @PostMapping("/save")
    public VideoDTO save(@RequestBody VideoDTO e) throws Exception{
        videoService.createEmployee(e);
        return e;
    }
    //select all tuples
    @GetMapping("/all")
    public ResponseEntity<Iterable<Video>> getAll(){
        return ResponseEntity.ok(videoService.lista());
    }

    //Read
    //select 1 tuple
    @GetMapping("/get/{agent_id}")
    public ResponseEntity<Iterable<Video>> getOne(@PathVariable("agent_id") Integer agent_id){
        return ResponseEntity.ok(videoService.get(agent_id));
    }

    @GetMapping("/getone/{agent_id}/{timestamp}")
    public ResponseEntity<Video> getOne(@PathVariable("agent_id") Integer agent_id, @PathVariable("timestamp") String timestamp){
        return ResponseEntity.ok(videoService.getOne(agent_id, timestamp));
    }

    //Delete a tuple
    @DeleteMapping("/delete/{agent_id}/{timestamp}")
    public ResponseEntity<?> delete(@PathVariable("agent_id") Integer agent_id, @PathVariable("timestamp") String timestamp){
        videoService.delete(agent_id, timestamp);
        return new ResponseEntity("video removed", HttpStatus.OK);
    }

    //Update a tuple
    @PutMapping("/update")
    public ResponseEntity<Video> update(@RequestBody VideoDTO employeeDTO){
        return ResponseEntity.ok(videoService.update(employeeDTO));
    }
}

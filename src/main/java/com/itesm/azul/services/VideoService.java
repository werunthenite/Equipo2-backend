package com.itesm.azul.services;

import com.itesm.azul.models.Video;
import com.itesm.azul.dto.VideoDTO;
import com.itesm.azul.repositories.EmployeeRepository;
import com.itesm.azul.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;

import java.util.List;

@Service
public class VideoService {

    @Autowired
    VideoRepository videoRepository;

    //CREATE
    public void createEmployee(final VideoDTO employee){
        Video e = new Video();
        e.setAgent_id(employee.getAgent_id());
        e.setTimestamp(employee.getTimestamp());
        e.setId(employee.getId());
        e.setUser_id(employee.getUser_id());
        e.setCall_reason(employee.getCall_reason());
        e.setLength(employee.getLength());
        e.setPermissions(employee.getPermissions());
        e.setTags(employee.getTags());
        e.setValidity_timestamp(employee.getValidity_timestamp());
        e.setVideo_time_limit(employee.getVideo_time_limit());
        e.setVideo_weight(employee.getVideo_weight());
        videoRepository.save(e);
    }

    //READ
    public Iterable<Video> lista(){
        return videoRepository.findAll();
    }
    public Iterable<Video> get(Integer agent_id){
        return videoRepository.findVideo(agent_id);
    }
    public Video getOne(Integer agent_id, String timestamp){
        return videoRepository.findById(agent_id, timestamp);
    }

    //DELETE
    public void delete(Integer id, String name){
        videoRepository.deleteById(id, name);
    }

    //UPDATE
    public Video update(VideoDTO dto){
        Video vid = new Video();
        vid.setAgent_id(dto.getAgent_id());
        vid.setTimestamp(dto.getTimestamp());
        vid.setId(dto.getId());
        vid.setUser_id(dto.getUser_id());
        vid.setCall_reason(dto.getCall_reason());
        vid.setLength(dto.getLength());
        vid.setPermissions(dto.getPermissions());
        vid.setTags(dto.getTags());
        vid.setValidity_timestamp(dto.getValidity_timestamp());
        vid.setVideo_time_limit(dto.getVideo_time_limit());
        vid.setVideo_weight(dto.getVideo_weight());

        return videoRepository.update(vid);
    }
}

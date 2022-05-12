package com.itesm.azul.models;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

import java.util.List;
import java.util.Set;

@DynamoDbBean
public class Video {
    private Integer agent_id;
    private String timestamp;
    private Integer id;
    private Integer user_id;
    private Integer call_reason;
    private Integer length;
    private List<String> permissions;
    private Set<Integer> tags;
    private String validity_timestamp;
    private String video_time_limit;
    private String video_weight;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("agent_id")
    public Integer getAgent_id() {
        return agent_id;
    }
    public void setAgent_id(Integer agent_id) {
        this.agent_id = agent_id;
    }

    @DynamoDbSortKey
    @DynamoDbAttribute("timestamp")
    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getCall_reason() {
        return call_reason;
    }
    public void setCall_reason(Integer call_reason) {
        this.call_reason = call_reason;
    }

    public Integer getLength() {
        return length;
    }
    public void setLength(Integer length) {
        this.length = length;
    }

    public List<String> getPermissions() {
        return permissions;
    }
    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public Set<Integer> getTags() {
        return tags;
    }
    public void setTags(Set<Integer> tags) {
        this.tags = tags;
    }

    public String getValidity_timestamp() {
        return validity_timestamp;
    }
    public void setValidity_timestamp(String validity_timestamp) {
        this.validity_timestamp = validity_timestamp;
    }

    public String getVideo_time_limit() {
        return video_time_limit;
    }
    public void setVideo_time_limit(String video_time_limit) {
        this.video_time_limit = video_time_limit;
    }

    public String getVideo_weight() {
        return video_weight;
    }
    public void setVideo_weight(String video_weight) {
        this.video_weight = video_weight;
    }
}

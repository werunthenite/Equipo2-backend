package com.itesm.azul.repositories;

import com.itesm.azul.models.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.DeleteItemEnhancedRequest;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;

@Repository
public class VideoRepository {
    @Autowired
    private DynamoDbEnhancedClient dynamoDbEnhancedClient;

    public VideoRepository(){
        super();
    }

    public void save(final Video video) {
        DynamoDbTable<Video> videoTable = getTable();
        videoTable.putItem(video);
    }

    /**
     * @return
     */
    private DynamoDbTable<Video> getTable() {
        DynamoDbTable<Video> videoTable = dynamoDbEnhancedClient.table("video", TableSchema.fromBean(Video.class));
        return videoTable;
    }
    public Iterable<Video> findAll() {
        DynamoDbTable<Video> videoTable = getTable();
        return videoTable.scan().items();
    }

    public Iterable<Video> findVideo(final Integer agent_id) {
        DynamoDbTable<Video> videoTable = getTable();


        // Create a QueryConditional object that is used in the query operation
        QueryConditional queryConditional = QueryConditional
                .keyEqualTo(Key.builder().partitionValue(agent_id)
                        .build());

        Iterable<Video> result = videoTable.query(r -> r.queryConditional(queryConditional)).items();
        return result;
    }

    public Video findById(final Integer agent_id, final String timestamp) {
        DynamoDbTable<Video> videoTable = getTable();
        Key key = Key.builder().partitionValue(agent_id).sortValue(timestamp).build();

        Video result = videoTable.getItem(key);
        return result;
    }
    public Video update(final Video video){
        DynamoDbTable<Video> videoTable = getTable();

        Key key = Key.builder().partitionValue(video.getAgent_id()).sortValue(video.getTimestamp()).build();

        Video registroVideo = videoTable.getItem(r->r.key(key));
        registroVideo.setAgent_id(video.getAgent_id());
        registroVideo.setTimestamp(video.getTimestamp());
        videoTable.updateItem(registroVideo);

        return video;
    }
    public void deleteById(final Integer agent_id, final String timestamp) {
        DynamoDbTable<Video> videoTable = getTable();

        Key key = Key.builder().partitionValue(agent_id).sortValue(timestamp).build();

        DeleteItemEnhancedRequest deleteRequest = DeleteItemEnhancedRequest
                .builder()
                .key(key)
                .build();

        videoTable.deleteItem(deleteRequest);
    }
}

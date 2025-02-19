package com.majorProject.UserService.config.kafka;

import java.util.Collections;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.errors.TopicExistsException;
import org.springframework.stereotype.Service;

import com.majorProject.utils.CommonConstants;

import jakarta.annotation.PostConstruct;

@Service
public class kafkaTopicCreation {
    private AdminClient adminClient;

    public kafkaTopicCreation(AdminClient adminClient){
        this.adminClient = adminClient;
    }

    // after the bean of the above code has been created 
    @PostConstruct
    public void createTopic(){
        // meathod -> topicName, partition, replication.
        createTopicIfNotExist(CommonConstants.TXN_CREATION_TOPIC,2, (short) 2);
    }
            
    public void createTopicIfNotExist(String topicName, int partition, short rFactor) {

        NewTopic topic = new NewTopic(topicName, partition, rFactor);

        try {
                    
            // will get all the topic from collection, and will create only 1 topic if it does not exist.
            adminClient.createTopics(Collections.singleton(topic)).all().get();

        } catch (TopicExistsException e) {
            System.out.println("Topic already exist");
        }
        catch(InterruptedException e){
            System.out.println("failed to create topic.");
        }
        catch(ExecutionException e){
            System.out.println("failed to create topic.");
        }

    }
        
}

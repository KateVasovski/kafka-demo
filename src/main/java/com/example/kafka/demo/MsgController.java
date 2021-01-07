package com.example.kafka.demo;

import com.example.kafka.demo.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("msg")
public class MsgController {

    @Autowired
    private KafkaTemplate<Long, UserDto> kafkaTemplate;

    @PostMapping(consumes = "application/json")
    public void sendMsg(@RequestBody UserDto msg){
        ListenableFuture<SendResult<Long, UserDto>> future = kafkaTemplate.send("msg", 1L, msg);
        future.addCallback(System.out::println, System.err::println);
        kafkaTemplate.flush();
    }
}
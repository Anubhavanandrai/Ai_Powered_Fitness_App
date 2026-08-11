package com.fitness.activityservice.controller;

import com.fitness.activityservice.dto.ActivityRequest;
import com.fitness.activityservice.dto.ActivityResponse;
import com.fitness.activityservice.service.ActivityService;
import com.fitness.activityservice.service.KafkaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    private ActivityService activityService;
    private KafkaService kafkaService;

    public ActivityController(ActivityService activityService ,KafkaService kafkaService)
    {
       this.activityService=activityService;
       this.kafkaService=kafkaService;
    }

    @GetMapping("/")
    public String getActivity(){
        String s="hello";
        return s;
    }

    @PostMapping("/registeractivity")
    public ResponseEntity<ActivityResponse> trackActivity(@RequestBody ActivityRequest request){
        System.out.println("Inside Activity register controller");
        return ResponseEntity.ok(activityService.trackActivity(request));
    }


    @PostMapping("/sendEvents/{message}")
    public ResponseEntity<?> sendEvents(@PathVariable String message) {
        System.out.println("Controller reached");
        System.out.println("Message = " + message);
        try {
            kafkaService.sendActivityEvent(message);
            return ResponseEntity.accepted().body("Event submitted");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to submit event");
        }
    }

}

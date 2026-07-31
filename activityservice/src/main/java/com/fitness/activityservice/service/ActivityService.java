package com.fitness.activityservice.service;

import com.fitness.activityservice.ActivityRepository;
import com.fitness.activityservice.config.WebClientConfig;
import com.fitness.activityservice.dto.ActivityRequest;
import com.fitness.activityservice.dto.ActivityResponse;
import com.fitness.activityservice.model.Activity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ActivityService {


    private final ActivityRepository activityRepository;
    private final WebClientConfig webclientconfig;

    public ActivityService(ActivityRepository activityrepository,WebClientConfig webclientconfig)
    {
        this.activityRepository = activityrepository;
        this.webclientconfig = webclientconfig;
    }


    public ActivityResponse trackActivity(ActivityRequest request) {
        Activity activity = Activity.builder()
                .types(request.getType())
                .startTime(request.getStartTime())
                .additionalMetrics(request.getAdditionalMetrics())
                .duration(request.getDuration())
                .caloriesBurned(request.getCaloriesBurned())
                .userId(request.getUserId())
                .build();

        Activity savedActivity = activityRepository.save(activity);
        return mapToResponse(savedActivity);
    }

    private ActivityResponse mapToResponse(Activity activity) {

        ActivityResponse response = new ActivityResponse();

        response.setId(activity.getId());
        response.setUserId(activity.getUserId());
        response.setTypes(activity.getTypes());
        response.setDuration(activity.getDuration());
        response.setCaloriesBurned(activity.getCaloriesBurned());
        response.setStartTime(activity.getStartTime());
        response.setAdditionalMetrics(activity.getAdditionalMetrics());
        response.setCreatedAt(activity.getCreatedAt());
        response.setUpdatedAt(activity.getUpdatedAt());

        return response;
    }

    public List<ActivityResponse> getUserActivities(String userId) {
        return activityRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
}
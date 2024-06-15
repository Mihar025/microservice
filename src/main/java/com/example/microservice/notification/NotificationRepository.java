package com.example.microservice.notification;

import com.example.microservice.notification.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface NotificationRepository extends MongoRepository<Notification, String> {
}

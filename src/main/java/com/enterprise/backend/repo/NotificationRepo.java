package com.enterprise.backend.repo;

import com.enterprise.backend.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepo extends JpaRepository<Notification, Long> {


}

package com.erp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.erp.model.Notification;

@Repository("notificationRepository")
public interface NotificationRepository extends CrudRepository<Notification, Integer>{

}

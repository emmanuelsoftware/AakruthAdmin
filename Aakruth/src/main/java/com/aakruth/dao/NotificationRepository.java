package com.aakruth.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.aakruth.model.Notification;

@Repository("notificationRepository")
public interface NotificationRepository extends CrudRepository<Notification, Integer>,PagingAndSortingRepository<Notification, Integer>{
	List<Notification> findAllByNotIdGreaterThan(Integer notId);
}

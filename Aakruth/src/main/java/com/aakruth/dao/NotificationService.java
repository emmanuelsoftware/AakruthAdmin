package com.aakruth.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.aakruth.model.Notification;

public interface NotificationService {

	List<Notification> findAllByNotIdGreaterThan(Integer notId);
	List<Notification> findAll(Pageable pageable);
}

package com.aakruth.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aakruth.model.Notification;

@Service("notificationService")
public class NotificationServiceImpl implements NotificationService{

	@Autowired
	NotificationRepository notificationRepository;
	@Override
	public List<Notification> findAllByNotIdGreaterThan(Integer notId) {
		// TODO Auto-generated method stub
		return notificationRepository.findAllByNotIdGreaterThan(notId);
	}

	@Override
	public List<Notification> findAll(Pageable pageable) {
		return notificationRepository.findAll(pageable).getContent();
	}

	
}

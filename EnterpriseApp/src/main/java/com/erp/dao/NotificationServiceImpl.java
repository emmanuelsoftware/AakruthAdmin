package com.erp.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.model.Notification;
@Service("notificationService")
public class NotificationServiceImpl implements NotificationService{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	NotificationRepository notificationRepository;
	@Override
	public Notification findOne(Integer notifyId) {
		// TODO Auto-generated method stub
		return notificationRepository.findOne(notifyId);
	}

	@Override
	public boolean save(Notification notification) {
		try {
			notificationRepository.save(notification);
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(Notification notification) {
		try {
			notificationRepository.save(notification);
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(Integer notifyId) {
		try {
			notificationRepository.delete(notifyId);
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

}

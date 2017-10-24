package com.erp.dao;

import com.erp.model.Notification;

public interface NotificationService {

	Notification findOne(Integer notifyId);
	boolean save(Notification notification);
	boolean delete(Notification notification);
	boolean delete(Integer notifyId);
	
}

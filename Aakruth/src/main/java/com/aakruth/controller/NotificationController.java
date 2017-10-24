package com.aakruth.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aakruth.dao.NotificationService;
import com.aakruth.model.Notification;

@RestController
public class NotificationController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	NotificationService notificationService;
	
	@RequestMapping(value = "/notification", method = RequestMethod.GET)
	public List<Notification> getNotifications() {
		List<Notification> notifications = notificationService.findAll(new PageRequest(0, 10,Sort.Direction.DESC,"notId"));
		/*List<Notification> notifications = new ArrayList<>();
		notifications.add(new Notification(new UsrTbl("issac", "4644", "545212", "sdfghjhgf",new Date("01/01/1991") , new Date("01/01/1991"), 'L', "fdsfsdf"),new Date("01/01/1991"), "inserted new bill 2"));
		notifications.add(new Notification(new UsrTbl("issac", "4644", "545212", "sdfghjhgf",new Date("01/01/1991") , new Date("01/01/1991"), 'L', "fdsfsdf"),new Date("01/01/1991"), "updated  product 2"));
		notifications.add(new Notification(new UsrTbl("issac", "4644", "545212", "sdfghjhgf",new Date("01/01/1991") , new Date("01/01/1991"), 'L', "fdsfsdf"),new Date("01/01/1991"), "deleted purchase 2"));
		notifications.add(new Notification(new UsrTbl("issac", "4644", "545212", "sdfghjhgf",new Date("01/01/1991") , new Date("01/01/1991"), 'L', "fdsfsdf"),new Date("01/01/1991"), "inserted sale 2"));
		notifications.add(new Notification(new UsrTbl("issac", "4644", "545212", "sdfghjhgf",new Date("01/01/1991") , new Date("01/01/1991"), 'L', "fdsfsdf"),new Date("01/01/1991"), "updated user 2"));
		notifications.add(new Notification(new UsrTbl("issac", "4644", "545212", "sdfghjhgf",new Date("01/01/1991") , new Date("01/01/1991"), 'L', "fdsfsdf"),new Date("01/01/1991"), "inserted new bill 2"));
		*/
		return notifications;
	}
	
	@RequestMapping(value = "/notificationLatest", method = RequestMethod.GET)
	public List<Notification> getLatestNotifications(@RequestParam(name = "notId") Integer notId) {
		logger.info("searching for notification :"+notId);
		List<Notification> notifications = notificationService.findAllByNotIdGreaterThan(notId);
		return notifications;
	}
	
}

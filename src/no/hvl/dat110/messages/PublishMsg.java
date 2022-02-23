package no.hvl.dat110.messages;

import no.hvl.dat110.common.TODO;

public class PublishMsg extends Message {
	
	// message sent from client to create publish a message on a topic 

	// TODO:
	// Implement object variables - a topic and a message is required

	private String message;
	private String topic;
	// Constructor, get/set-methods, and toString method
	// as described in the project text
	
	public PublishMsg(String user, String topic, String message) {
		// TODO Auto-generated constructor stub
		super(MessageType.PUBLISH, user);
		this.message = message;
		this.topic = topic;
	}

	public String getMessage() {
		
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	@Override
	public String toString() {
		return "PublishMsg [topic=" + topic + ", type=" + getType() + ",user=" + getUser() + ", messgae=" + message + "]";
	}
	
	
}

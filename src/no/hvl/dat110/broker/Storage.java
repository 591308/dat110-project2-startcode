package no.hvl.dat110.broker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import no.hvl.dat110.common.TODO;
import no.hvl.dat110.messages.Message;
import no.hvl.dat110.common.Logger;
import no.hvl.dat110.messagetransport.Connection;

public class Storage {

	// data structure for managing subscriptions
	// maps from a topic to set of subscribed users
	protected ConcurrentHashMap<String, Set<String>> subscriptions;
	
	// data structure for managing currently connected clients
	// maps from user to corresponding client session object
	
	protected ConcurrentHashMap<String, ClientSession> clients;
	
	//data structure for managing users that currently disconnected
	//maps from user to set of messages
	protected ConcurrentHashMap<String, ArrayList<Message>> offlineMessageBuffer;
	
	protected ConcurrentHashMap<String, Boolean> connected;

	public Storage() {
		subscriptions = new ConcurrentHashMap<String, Set<String>>();
		clients = new ConcurrentHashMap<String, ClientSession>();
		offlineMessageBuffer = new ConcurrentHashMap<String, ArrayList<Message>>();
		connected = new ConcurrentHashMap<String, Boolean>();
	}

	public Collection<ClientSession> getSessions() {
		return clients.values();
	}

	public Set<String> getTopics() {

		return subscriptions.keySet();

	}

	// get the session object for a given user
	// session object can be used to send a message to the user
	
	public ClientSession getSession(String user) {

		ClientSession session = clients.get(user);

		return session;
	}

	public Set<String> getSubscribers(String topic) {

		return (subscriptions.get(topic));

	}

	public void addClientSession(String user, Connection connection) {

		// TODO: add corresponding client session to the storage
		// See ClientSession class
		
		clients.put(user, new ClientSession(user, connection));
		
	}

	public void removeClientSession(String user) {

		// TODO: disconnet the client (user) 
		// and remove client session for user from the storage
		
		clients.remove(user);
		
	}

	public void createTopic(String topic) {

		// TODO: create topic in the storage

		subscriptions.put(topic, new HashSet<>());
	
	}

	public void deleteTopic(String topic) {

		// TODO: delete topic from the storage

		subscriptions.remove(topic);
		
	}

	public void addSubscriber(String user, String topic) {

		// TODO: add the user as subscriber to the topic
		
		subscriptions.get(topic).add(user);
		
	}

	public void removeSubscriber(String user, String topic) {

		// TODO: remove the user as subscriber to the topic

		subscriptions.get(topic).remove(user);
	}
	
	public void disconnectUser(String user) {
		connected.put(user, false);
		clients.get(user).disconnect();
	}
	
	public boolean isConnected(String user) {
		return connected.get(user);
	}
	
	public void reconnectUser(String user, Connection connection) {
		connected.put(user, true);
		clients.put(user, new ClientSession(user, connection));
	}
	public void addToMessageBuffer(String user, Message msg) {
		offlineMessageBuffer.get(user).add(msg);
	}
	
	public ArrayList<Message> getMessageBuffer(String user) {
		return offlineMessageBuffer.get(user);
	}

	public void emptyMessageBuffer(String user) {
		offlineMessageBuffer.get(user).clear();
	}
}

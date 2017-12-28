package fun.forAlice.AlicePi.core.pubsub.bean;

import java.util.function.Consumer;


public class Handle implements Comparable<Handle>{


	public String event;
	public String tag;
	public Consumer<String> consumer;
	
	public Handle(String _event, String _tag, Consumer<String> _consumer) {
		event = _event;
		tag = _tag;
		consumer = _consumer;
	}
	public Handle(String _event, String _tag) {
		event = _event;
		tag = _tag;
		consumer = (a)->{};
	}
	public Handle() {
		event = "/";
		tag = "";
		consumer = (a)->{};
	}
	public void accept(String t) {
		this.consumer.accept(t);
	}
	
	// TreeSet
	public int compareTo(Handle obj) {
		String hashLeft = this.toString();
		String hashRight = obj.toString();
				
		if(hashLeft.equals(hashRight)) {
			return 0;
		}
		else {
			return -1;
		}
	}
	
	// HashSet
	public boolean equals(Handle obj) {
		return event.equals(obj.event) 
				&& tag.equals(obj.tag);
	}
	// HashSet
	public int hashCode() {
		String hash = this.toString();
		int hashCode=0;
		char[] charArr =  hash.toCharArray();

	    for (int i = 0; i < charArr.length; ++i) {
	    	hashCode = 31 * hashCode + charArr[i];
	    }	    
		return hashCode;
	}
	
	public String toString() {
		return event+"->"+tag;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public Consumer<String> getConsumer() {
		return consumer;
	}
	public void setConsumer(Consumer<String> consumer) {
		this.consumer = consumer;
	}
}
print("engine init");
var console={"log":print};
console.log("console.log enable");

var Thread = Java.type("java.lang.Thread");
var Executors = Java.type("java.util.concurrent.Executors");
var TimeUnit = Java.type("java.util.concurrent.TimeUnit");

//---------
var MqSub = Java.type("fun.forAlice.AlicePi.core.pubsub.impl.SubscriberRedisImpl");
var MqPub = Java.type("fun.forAlice.AlicePi.core.pubsub.impl.PublisherRedisImpl");


var sub = new MqSub();
var pub = new MqPub();

sub.on("/gpios","1",function(msg){
	print(msg)
})

/****
 阻塞执行
 
 //block program
var r = new java.lang.Runnable() {
    run: function() { 
    	Thread.sleep(10*1000);
    	pub.publish("/gpios","fdaf");
    	print("run"); }
}
 
r.run();
**///

var scheduledThreadPool = Executors.newScheduledThreadPool(5);
var r = new java.lang.Runnable() {
    run: function() { 
    	print()
    	Thread.sleep(10*1000);
    	pub.publish("/gpios","fdaf");
    	print("run"); }
};
function setInterval(fun, ms){
	var r = new java.lang.Runnable() {
	    run: fun
	};
	return scheduledThreadPool
		.scheduleAtFixedRate(
					r,
					0,//initialDelay
					ms,
					TimeUnit.MILLISECONDS
				);
}
function clearInterval(future){
	future.cancel(true);
}
function setTimeout(){
	scheduledThreadPool
	.schedule(
				r,
				ms,
				TimeUnit.MILLISECONDS
			);
}

/*** test demo
var i = 0;
var task = 
setInterval(function(){
	print("setInterval")
	print(i++);
	if(i>10){
		clearInterval(task);
	}
},1000*2)
while(true){
	
}

**/

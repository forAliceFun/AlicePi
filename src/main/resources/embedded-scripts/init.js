print("engine init");
var console={"log":print};
console.log("console.log enable");


//---------
var MqSub = Java.type("fun.forAlice.AlicePi.core.pubsub.impl.SubscriberRedisImpl");
var MqPub = Java.type("fun.forAlice.AlicePi.core.pubsub.impl.PublisherRedisImpl");

var sub = new MqSub();
var pub = new MqPub();

sub.on("/gpios","1",function(msg){
	print(msg)
})

window.setInterval(function(){
	pub.publish("/gpios","fdaf");
},1000*5);
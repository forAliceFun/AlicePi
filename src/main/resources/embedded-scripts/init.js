print("engine init");
var console={"log":print};
console.log("console.log enable");


//---------
var MqSub = Java.type("fun.forAlice.AlicePi.core.pubsub.impl.SubscriberRedisImpl");

var sub = new MqSub();

sub.on("/gpios","1",function(msg){
	print(msg)
})
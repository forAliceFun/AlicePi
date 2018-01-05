$(function(){				
    $('.gpio-checkbox').on('change', function(e){        

        var gpio = $(this).data("gpio");
        var ouput = this.checked?1:0;
     
        var data = {
      		  "id": gpio,
      		  "raspiPinNum": gpio,
      		  "type": "output",
      		  "mode": "pull-up",
      		  "input": 1,
      		  "output": ouput,
      		  "hook": "",
      		  "key": "gpio_"+gpio
      		};        
        console.log(data);
        $.ajax({
            type: 'POST',
            data: JSON.stringify(data),
            contentType: 'application/json',
            url: '/gpios/'+data.id,		
            dataType:"json",
            success:function(data){
            },
            error: function(data) {
                alert("Error");
            }
        });
    });			

    setInterval(function pullPoller(){
        $.ajax({
            type: 'GET',
            url: '/gpios/',		
            dataType:"json",
            success:function(data){
            		for(var key in data){
            			var gpio = data[key];
            			var node = $('[data-gpio="'+gpio.raspiPinNum+'"]')
		                  if(gpio.output===1){
		                	  node.bootstrapToggle('on') 
		                  }
		                  else{
		                	  node.bootstrapToggle('off')
		                  }
            		}
            },
            error: function(data) {
                alert("Error");
            }
        });

    },2000);	
});
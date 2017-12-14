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
            error: function(data) {
                alert("Error");
            }
        });
    });				
});
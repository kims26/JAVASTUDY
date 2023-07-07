/**
 * 
 */
 
  window.onload = function(){

		 if (navigator.geolocation) {
		     navigator.geolocation.getCurrentPosition(function(position){
		    	  var latitude =  position.coords.latitude;
				  var longitude=  position.coords.longitude;
				  
				  console.log(latitude,longitude);
				  
				  document.getElementById("latitude").value  = latitude;
				  document.getElementById("longitude").value = longitude;
				  
				  document.getElementById("latitude1").value  = latitude;
				  document.getElementById("longitude1").value = longitude;
				  
				  document.getElementById("latitude2").value  = latitude;
				  document.getElementById("longitude2").value = longitude;
		    	 
		     });
		 } 

   };


   function send(f){
	   
	   var query = f.query.value.trim();
	   
	   if(query==''){
	
		   f.query.vlaue='';
		   f.query.focus();
		   return;   
	   }
	  
	   f.action = "kakao/keyword.do";
	   f.submit();
	   
   }
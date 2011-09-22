var tabid = 0;
$('scrollUp').observe("click",function(){
	switchhotnewup(++tabid);
})
$('scrollDown').observe("click",function(){
	switchhotnewdown(--tabid);
})

function switchhotnewup(id)
{
    if(id> 3){
 	tabid = 0;
         $("hotnews0").style.display = "block"
         for(var i =0 ; i<=3; i++)
         {
         		if(i != 0)
	   		$("hotnews"+i).style.display = "none";
         }
     }
    else {
         $("hotnews"+id).style.display = "block"
         for(var i =0 ; i<=3; i++)
         {
         		if(i != id)
	   		$("hotnews"+i).style.display = "none";
         } 
    }

    clearInterval(int);
    int = setInterval("switchhotnewup(++tabid)",2500);
}

function switchhotnewdown(id)
{
    if(id < 0){
 	tabid = 3;
         $("hotnews3").style.display = "block"
         for(var i =0 ; i<=3; i++)
         {
         		if(i != 3)
	   		$("hotnews"+i).style.display = "none";
         }
     }
    else {
         $("hotnews"+id).style.display = "block"
         for(var i =0 ; i<=3; i++)
         {
         		if(i != id)
	   		$("hotnews"+i).style.display = "none";
         } 
    }
    clearInterval(int);
    int = setInterval("switchhotnewdown(--tabid)",2500);
}

var int = setInterval("switchhotnewup(++tabid)",2500);

function initPage(){
	hiddenAllMenu('menuDiv');
	var visit = Wade.context.getPageVisit();
	var privMap = new Wade.DataMap(visit.epachyName);
	var menuMap = privMap.get("MENUS_SET");
	var rightMap = privMap.get("RIGHTS_SET");
	//员工权限处理
	if(rightMap){
		rightMap.eachKey(function(key){
			//超级用户权限
			/*
			if(key == 'SYS_SUPERUSR'){
				displayAllMenu('menuDiv');
			}
			*/					
		});
	}
	//员工菜单显示处理
	if(menuMap){
		menuMap.eachKey(function(key){
			var childMenuMap = menuMap.get(key);
			var parentMenuObj = document.getElementById(key);
			if(parentMenuObj){
				parentMenuObj.style.display = "block";
				childMenuMap.eachKey(function(key2){
					var menuObj = document.getElementById(key2);
					if(menuObj){
						menuObj.style.display = "block";
					}
				});
			}
		});
	}
	
}
//'menuDiv'
function hiddenAllMenu(divId){
	var menuObjs = getChildsByRecursion(divId, 'LI');
	for(var i=0; i<menuObjs.length; i++){
		menuObjs[i].style.display = "none";
	}
}
function displayAllMenu(divId){
	var menuObjs = getChildsByRecursion(divId);
	for(var i=0; i<menuObjs.length; i++){
		menuObjs[i].style.display = "block";
	}
}
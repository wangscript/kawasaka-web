function checkBeforeSubmit(){
	var staff1 = getElement('cond_NEW_STAFF_PWD1');
	var staff2 = getElement('cond_NEW_STAFF_PWD2');
	if(staff1.value != staff2.value){
		alert("两次密码输入不一致，请重新输入！");
		staff1.value = "";
		staff2.value = "";
		return false;
	}
	return true;
}

function changeDisplay(operType){
	if(operType == "A"){
		getElement('oldStaffDiv').style.display = "block";
		getElement('newStaff1Div').style.display = "block";
		getElement('newStaff2Div').style.display = "block";
		getElement('bquery').style.display = "block";
		getElement('breset').style.display = "none";
	}else if(operType == "B"){
		getElement('oldStaffDiv').style.display = "none";
		getElement('newStaff1Div').style.display = "none";
		getElement('newStaff2Div').style.display = "none";
		getElement('bquery').style.display = "none";
		getElement('breset').style.display = "block";
	}
}
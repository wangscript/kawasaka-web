var dom = Wade.dom;
var nav = Wade.nav;
/** shrink sidebar */
function shrinkSiderbar(result) {
	var fset = parent.document.getElementById("fset");
	var tipon = dom.getElement("tipon");
	var tipoff = dom.getElement("tipoff");
	if (result) {
		fset.cols = "0,5,*";
		dom.hidden(tipon, true);
		dom.hidden(tipoff, false);
	} else {
		fset.cols = "200,5,*";
		dom.hidden(tipon, false);
		dom.hidden(tipoff, true);
	}
};
/** shrink header */
function shrinkHeader(result) {
	var aset = parent.document.getElementById("aset");
	var tipon = dom.getElement("tipon");
	var tipoff = dom.getElement("tipoff");
	if (result) {
		aset.rows = "0,5,*,26,0";
		dom.hidden(tipon, true);
		dom.hidden(tipoff, false);
	} else {
		aset.rows = "57,5,*,26,0";
		dom.hidden(tipon, false);
		dom.hidden(tipoff, true);
	}
};
/** selected loyout */
function selectedLayout(srcelement, laytype) {
	var selname = "SELECTED_LAYOUT";
	var selelement = dom.getElement(selname);
	if (selelement != null) {
		selelement.id = null;
		selelement.className = null;
	}
	var txtelement = srcelement.parentNode;
	txtelement.id = selname;
	txtelement.className = "on";
	
	var sliptop = nav.getFrame("sliptopframe");
	var slipleft = nav.getFrame("slipleftframe");
	if (laytype == "1") {
		sliptop.shrinkHeader(false);
		slipleft.shrinkSiderbar(false);
	} else if (laytype == "4") {
		sliptop.shrinkHeader(true);
		slipleft.shrinkSiderbar(true);
	}
};
/** selected menu */
function selectedMenu(srcelement) {
	var selname = "SELECTED_MENU";
	var selelement = dom.getElement(selname);
	if (selelement != null) {
		selelement.id = null;
		selelement.className = "fold";
	}
	srcelement.id = selname;
	srcelement.className = "unfold";
};
/** init sidebar */
function initSidebar() {
	var fSide=System.fly(document.body).child("div[class=f_side]");
	if(fSide){
		var hc=fSide.select("h4[class]");
		if(hc && hc.length){
			var lk;
			for(var i=0;i<hc.length;i++){
				lk=System.fly(hc[i]).child("a:first-child");
				if(lk){
						lk.dom.setAttribute("num",i);
						lk.on("click",function(e){
					   	var n=(parseInt(this.dom.getAttribute("num"))+1)*2;
						var h4=this.findParentNode("h4",2);
						selectedMenu(h4);
						var ul=fSide.child("ul:nth-child(" + n + ")");
						if(ul){
							ul.enableDisplayMode("");
							if(ul.isVisible()){ul.setVisible(false)}else{ul.setVisible(true);}
						}
				})}
			}
		}
		var uc=fSide.select("ul");
		if(uc && uc.length){
			for(var i=0;i<uc.length;i++){
				if(uc[i].style.display!=="block")
					uc[i].style.display="none";
			}
		}uc=null;
	}
}
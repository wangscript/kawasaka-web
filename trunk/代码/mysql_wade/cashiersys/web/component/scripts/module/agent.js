Wade.agent=(function(){ var util = Wade.util; var dom = Wade.dom; var heightAdd = 30; var obj = window.dialogArguments; if (Browser.isFF) {  heightAdd = 0;  obj = new Object();  obj.title = util.getParameter("title");  obj.width = util.getParameter("width");  obj.height = util.getParameter("height");  obj.url = util.getParameter("url"); } var title = obj.title; var width = obj.width; var height = parseInt(obj.height) + heightAdd; document.title = title + getBlankStr(document.title, width - title.length); document.getElementsByTagName("HTML")[0].style.height = height + "px";   function getBlankStr(_char, _count) {  var str = "";  for (var i=0; i<_count; i++) {   str += _char;  }  return str; };  function focus(field) {  try {   field.focus();  } catch (e) {  } };  return {    initAgent : function() {   var frame = dom.getElement("contentframe");   frame.src = obj.url;   focus(frame);  } }})();
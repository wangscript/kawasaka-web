if(typeof(Wade)!="undefined" && typeof(Wade.uploadimage)=="undefined"){ Wade.uploadimage=(function(){  var dh=System.DomHelper;  var page=Wade.page;    var parentWin=parent.window,parentEditorManager=null,parentEditorDialogManager=null;  var upload_image_iframe=null,editdoc=null,editorInstance=null;  if(parentWin){parentEditorManager=parentWin.System.component.EditorManager;  parentEditorDialogManager=parentWin.System.component.EditorDialogManager;}  var imageUrl,imagePath,imageDesc,imageHeight,imageWidth,imageAlign,imageBorder,imageHori,imageVeri,uploadModes;    function getSettingObjects(){   upload_image_iframe=document.getElementById("upload_image_iframe");   imagePath=document.getElementById("IMAGE_PATH");   imageUrl=document.getElementById("IMAGE_URL");   imageDesc=document.getElementById("IMAGE_DESC");   imageHeight=document.getElementById("IMAGE_HEIGHT");   imageWidth=document.getElementById("IMAGE_WIDTH");   imageAlign=document.getElementById("IMAGE_ALIGN");   imageBorder=document.getElementById("IMAGE_BORDER");   imageHori=document.getElementById("IMAGE_HORI");   imageVeri=document.getElementById("IMAGE_VERT");   uploadModes=document.getElementsByName("UPLOAD_MODE");  }    function clearSettings(){   imagePath.value="";   imageUrl.value="http://";   imageDesc.value ="";   imageHeight.value ="";   imageWidth.value = "";   imageAlign.value = "";   imageBorder.value = "";   imageHori.value = "";   imageVeri.value="";   uploadModes[0].disabled = false;   uploadModes[1].checked=true;    }    function getInitSettings(){   if(!editorInstance)return;    var seltext=editorInstance.getSel();   if(/^<img.*?\/?>$/i.test(seltext)){    upload_image_iframe.contentWindow.document.body.innerHTML="";    var image=dh.insertHtml("afterbegin",upload_image_iframe.contentWindow.document.body,seltext);    if (image && image.nodeType) {     var w=null,h=null;     if(image.style.width){w=dh.removeUnits(image.style.width);}     if(image.style.height){h=dh.removeUnits(image.style.height);}     imagePath.value="";     imageUrl.disabled=false;          imageUrl.value = image.src.replace(/^[^*]*(\*\*\*)/, "$1");          imageDesc.value = image.alt;          imageHeight.value = h?h:image.height;          imageWidth.value  = w?w:image.width;          imageAlign.value = image.align;          imageBorder.value = image.border;          imageHori.value = image.hspace;          imageVeri.value = image.vspace;          uploadModes[0].disabled = true;       }      try{imageUrl.focus();}catch(e){}   }else{clearSettings();}  }    function setImageInfo(inputStr){   if(!editorInstance)return;   if(typeof(inputStr)=="string" && inputStr!=""){editorInstance.insertText(inputStr);return;}    var img = "<img src='" + imageUrl.value + "'"      + (imageWidth.value != "" ? " width='" + imageWidth.value + "'" : "")      + (imageHeight.value != "" ? " height='" + imageHeight.value + "'" : "")      + (imageHori.value != "" ? " hspace='" + imageHori.value + "'" : "")      + (imageVeri.value != "" ? " vspace='" + imageVeri.value + "'" : "")      + (imageBorder.value != "" ? " border='" + imageBorder.value + "'" : "")      + (imageDesc.value != "" ? " alt='" + imageDesc.value + "'" : "")      + (imageAlign.value != "" ? " align='" + imageAlign.value + "'" : "")      + "/>";      editorInstance.insertText(img);  }    return {   setImageInfo:function(str){    setImageInfo(str);   },   init:function(){    getSettingObjects();    if(parentEditorDialogManager){     parentEditorDialogManager.addInsertImageDialogOnShowListener(this.initUploadImage,this);    }this.initUploadImage();   },   initUploadImage : function(){    if(parentEditorManager){editorInstance=parentEditorManager.getActive();}    page.endPageLoading();      uploadModes[1].checked = true;      imagePath.value="";    imagePath.disabled = true;    getInitSettings();   },   selectUploadMode : function() {    if (uploadModes[0].checked) {     imagePath.disabled = false;     imageUrl.disabled = true;    }    if (uploadModes[1].checked) {     imagePath.disabled = true;     imageUrl.disabled = false;    }   },   uploadImage : function() {    if (uploadModes[0].checked && imagePath.value == "") {     alert("\u56FE\u50CF\u8DEF\u5F84\u4E0D\u80FD\u4E3A\u7A7A!");     imagePath.focus();     return false;    }    if (uploadModes[1].checked && imageUrl.value == "") {     alert("\u56FE\u50CF\u8DEF\u5F84\u4E0D\u80FD\u4E3A\u7A7A!");     imageUrl.focus();     return false;    }    if (imageWidth.value != "" && isNaN(imageWidth.value)) {     alert("\u5BBD\u5EA6\u5FC5\u987B\u4E3A\u6574\u6570!");     imageWidth.focus();     return false;    }    if (imageHeight.value != "" && isNaN(imageHeight.value)) {     alert("\u9AD8\u5EA6\u5FC5\u987B\u4E3A\u6574\u6570!");     imageHeight.focus();     return false;    }    if (imageBorder.value != "" && isNaN(imageBorder.value)) {     alert("\u56FE\u50CF\u8FB9\u6846\u5FC5\u987B\u4E3A\u6574\u6570!");     imageBorder.focus();     return false;    }    if (imageHori.value != "" && isNaN(imageHori.value)) {     alert("\u6C34\u5E73\u95F4\u8DDD\u5FC5\u987B\u4E3A\u6574\u6570!");     imageHori.focus();     return false;    }    if (imageVeri.value != "" && isNaN(imageVeri.value)) {     alert("\u5782\u76F4\u95F4\u8DDD\u5FC5\u987B\u4E3A\u6574\u6570!");     imageVeri.focus();     return false;    }    if (uploadModes[1].checked) {     setImageInfo();     this.closeWindow();    }else{     page.beginPageLoading();     page.updateLoadingMsg("\u4E0A\u4F20\u4E2D...");     document.forms[0].submit();    }    return true;   },   closeWindow:function(){    parentEditorDialogManager.closeInsertImageDialog();   }  } })();}
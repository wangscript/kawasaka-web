if(typeof(Wade)!="undefined" && typeof(Wade.uploadfile)=="undefined"){ Wade.uploadfile=(function(){  var dh=System.DomHelper;  var upload = Wade.upload;  var page=Wade.page;    var parentWin=parent.window,parentEditorManager=null,parentEditorDialogManager=null;  var upload_file_iframe=null,editorInstance=null;  if(parentWin){parentEditorManager= parentWin.System.component.EditorManager;if(parentEditorManager){   parentEditorDialogManager=parentWin.System.component.EditorDialogManager;  }}  var filePath,fileUrl,fileDesc,uploadModes;    function getSettingObjects(){   upload_file_iframe=document.getElementById("upload_file_iframe");   filePath=document.getElementById("FILE_PATH");   fileUrl=document.getElementById("FILE_URL");   fileDesc=document.getElementById("FILE_DESC");   uploadModes=document.getElementsByName("UPLOAD_MODE");  }      function setFileInfo(inputStr) {    if(!editorInstance)return;    if(typeof(inputStr)=="string" && inputStr!=""){editorInstance.insertText(inputStr);return;}     var image = upload.getFileImage(fileUrl.value);     var file = "<a href='" + fileUrl.value + "'"       + (fileDesc.value != "" ? " title='" + fileDesc.value + "'" : "")       + ">" + (fileUrl.value.lastIndexOf(".") != -1 ? upload.getFileName(fileUrl.value) : fileUrl.value)       + "</a>";    editorInstance.insertText('<img src="' + image + '" border="0" />' + file);  }  return {   setFileInfo:function(str){    setFileInfo(str);   },   init:function(){    getSettingObjects();    if(parentEditorDialogManager){     parentEditorDialogManager.addUploadFileDialogOnShowListener(this.initUploadFile,this);    }this.initUploadFile();    },   initUploadFile:function(){    if(parentEditorManager){editorInstance=parentEditorManager.getActive();}    page.endPageLoading();    filePath.value="";    filePath.disabled=false;    fileUrl.value="http://";    fileUrl.disabled = true;    uploadModes[0].checked = true;   },   selectUploadMode : function() {    if (uploadModes[0].checked) {     filePath.disabled = false;     fileUrl.disabled = true;    }    if (uploadModes[1].checked) {     filePath.disabled = true;     fileUrl.disabled = false;    }   },   uploadFile : function() {    if (uploadModes[0].checked && filePath.value == "") {     alert("\u6587\u4EF6\u8DEF\u5F84\u4E0D\u80FD\u4E3A\u7A7A!");     filePath.focus();     return false;    }    if (uploadModes[1].checked && fileUrl.value == "") {     alert("\u6587\u4EF6\u8DEF\u5F84\u4E0D\u80FD\u4E3A\u7A7A!");     file_url.focus();     return false;    }    if (uploadModes[1].checked) {     setFileInfo();     this.closeWindow();    }else{     page.beginPageLoading();     page.updateLoadingMsg("\u4E0A\u4F20\u4E2D...");     document.forms[0].submit();    }    return true;   },   closeWindow:function(){    parentEditorDialogManager.closeUploadFileDialog();   }  } })();}
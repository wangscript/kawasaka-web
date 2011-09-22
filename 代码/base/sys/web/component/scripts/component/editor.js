(function(){
if('undefined'==typeof(System)){alert('need System namespace support');return}
if(typeof(System.component)=="undefined"){System.component={};}

var dh=System.DomHelper;
var evm=System.EventManager;

var postminchars = parseInt('0');
var postmaxchars = parseInt('100000');
var disablepostctrl = parseInt('0');
var typerequired = parseInt('');
var allowswitcheditor=true;

var allowhtml = false;
var forumallowhtml = false;
var allowsmilies = true;
var allowbbcode = true;
var allowimgcode =true;

function in_array(needle, haystack){
    if(typeof needle == 'string') {
        for(var i in haystack) {
	        if(haystack[i] == needle) {
			        return true;
	        }
        }
    }
    return false;
}

function trim(str){
    if(typeof(str)=="string" && str!=""){
        return str.trim();
    }
    return "";
}		

function mb_strlen(str) {
	return (System.UserAgent.ie && str.indexOf('\n') != -1) ? str.replace(/\r?\n/g, '_').length : str.length;
}			

function htmlspecialchars(str) {
    var f = new Array(
        (System.UserAgent.mac && System.UserAgent.ie ? new RegExp('&', 'g') : new RegExp('&(?!#[0-9]+;)', 'g')),
        new RegExp('<', 'g'),
        new RegExp('>', 'g'),
        new RegExp('"', 'g')
    );
    var r = new Array(
        '&amp;',
        '&lt;',
        '&gt;',
        '&quot;'
    );
    for(var i = 0; i < f.length; i++) {
        str = str.replace(f[i], r[i]);
    }
    return str;
}
        
function readNodes(root, toptag) {
	var html = "";
	var moz_check = /_moz/i;

	switch(root.nodeType) {
		case Node.ELEMENT_NODE:
		case Node.DOCUMENT_FRAGMENT_NODE:
			var closed;
			if(toptag) {
				closed = !root.hasChildNodes();
				html = '<' + root.tagName.toLowerCase();
				var attr = root.attributes;
				for(var i = 0; i < attr.length; ++i) {
					var a = attr.item(i);
					if(!a.specified || a.name.match(moz_check) || a.value.match(moz_check)) {
						continue;
					}
					html += " " + a.name.toLowerCase() + '="' + a.value + '"';
				}
				html += closed ? " />" : ">";
			}
			for(var i = root.firstChild; i; i = i.nextSibling) {
				html += readNodes(i, true);
			}
			if(toptag && !closed) {
				html += "</" + root.tagName.toLowerCase() + ">";
			}
			break;

		case Node.TEXT_NODE:
			html = htmlspecialchars(root.data);
			break;
	}
	return html;
}
					
if(typeof(System.component.EditorResources)=="undefined"){
    System.component.EditorResources={
        lang:{
                "enter_tag_option":"\u8BF7\u8F93\u5165%1\u6807\u7B7E\u7684\u9009\u9879\u003A",
				"enter_list_item":"\u8F93\u5165\u4E00\u4E2A\u5217\u8868\u9879\u76EE\u002E\r\n\u7559\u7A7A\u6216\u8005\u70B9\u51FB\u53D6\u6D88\u5B8C\u6210\u6B64\u5217\u8868\u002E",
				"enter_link_url":"\u8BF7\u8F93\u5165\u94FE\u63A5\u7684\u5730\u5740\u003A",
				"enter_image_url":"\u8BF7\u8F93\u5165\u56FE\u7247\u94FE\u63A5\u5730\u5740\u003A",
				"enter_email_link":"\u8BF7\u8F93\u5165\u6B64\u94FE\u63A5\u7684\u90AE\u7BB1\u5730\u5740\u003A",
				"enter_table_rows":"\u8BF7\u8F93\u5165\u884C\u6570\uFF0C\u6700\u591A\u0020\u0033\u0030\u0020\u884C\u003A",
				"enter_table_columns":"\u8BF7\u8F93\u5165\u5217\u6570\uFF0C\u6700\u591A\u0020\u0033\u0030\u0020\u5217\u003A",
				"fontname":"\u5B57\u4F53",
				"fontsize": "\u5927\u5C0F"
			 },
		custombbcodes:{
		            "qq":"[qq=8]000000[/qq]",
					"flash":"Flash Movie",
					"rm":"[rm]rtsp://your.com/example.rm[/rm]",
					"wmv":"[wmv]mms://your.com/example.wmv[/wmv]",
					"page":"[page][/page]"
				},		
	    fontoptions:[
	            "FangSong_GB2312", 
	            "SimHei", 
	            "KaiTi_GB2312", 
	            "SimSun", 
	            "NSimSun", 
	            "Tahoma", 
	            "Arial", 
	            "Impact", 
	            "Verdana", 
	            "Times New Roman"
	            ],
	    smilies:[
                    {'code' : ':)', 'url' : 'em01.gif'},
					{'code' : ':(', 'url' : 'em02.gif'},
					{'code' : ':D', 'url' : 'em03.gif'},
					{'code' : ':\'(', 'url' : 'em04.gif'},
					{'code' : ':@', 'url' : 'em05.gif'},
					{'code' : ':o', 'url' : 'em06.gif'},
					{'code' : ':P', 'url' : 'em07.gif'},
					{'code' : ':$', 'url' : 'em08.gif'},
					{'code' : ';P', 'url' : 'em09.gif'},
					{'code' : ':L', 'url' : 'em10.gif'},
					{'code' : ':Q', 'url' : 'em11.gif'},
					{'code' : ':lol', 'url' : 'em12.gif'},
					{'code' : ':hug:', 'url' : 'em13.gif'},
					{'code' : ':victory:', 'url' : 'em14.gif'},
					{'code' : ':time:', 'url' : 'em15.gif'},
					{'code' : ':kiss:', 'url' : 'em16.gif'},
					{'code' : ':handshake', 'url' : 'em17.gif'},
					{'code' : ':call:', 'url' : 'em18.gif'},
					{'code' : ':calld:', 'url' : 'em24.gif'},
					{'code' : ':callc:', 'url' : 'em23.gif'},
					{'code' : ':callb:', 'url' : 'em22.gif'},
					{'code' : ':calla:', 'url' : 'em21.gif'},
					{'code' : ':loveliness:', 'url' : 'em19.gif'},
					{'code' : ':funk:', 'url' : 'em20.gif'},
					{'code' : ':calle:', 'url' : 'em25.gif'}	        
	    ],
	    BORDERCOLOR:"#7AC4EA",
		ALTBG2:"#FFFFFF"        
    };
}

if(typeof(System.component.EditorBBCode)=="undefined"){
    System.component.EditorBBCode=(function(){
        var re;
        var pcodecount = -1;
        var codecount = 0;
        var codehtml = new Array();   

        function strpos(haystack, needle, offset) {
	        if(typeof offset == 'undefined') {
		        offset = 0;
	        }

	        index = haystack.toLowerCase().indexOf(needle.toLowerCase(), offset);

	        return index == -1 ? false : index;
        }   
             
        function fetchCheckbox(cbn) {
	        return document.getElementById(cbn) && document.getElementById(cbn).checked == true ? 1 : 0;
        }       
        
        function fetchoptionvalue(option, text) {
	        if((position = strpos(text, option)) !== false) {
		        delimiter = position + option.length;
		        if(text.charAt(delimiter) == '"') {
			        delimchar = '"';
		        } else if(text.charAt(delimiter) == '\'') {
			        delimchar = '\'';
		        } else {
			        delimchar = ' ';
		        }
		        delimloc = strpos(text, delimchar, delimiter + 1);
		        if(delimloc === false) {
			        delimloc = text.length;
		        } else if(delimchar == '"' || delimchar == '\'') {
			        delimiter++;
		        }
		        return trim(text.substr(delimiter, delimloc - delimiter));
	        } else {
		        return '';
	        }
        }  
        
        function getoptionvalue(option, text) {
            re = new RegExp(option + "(\s+?)?\=(\s+?)?[\"']?(.+?)([\"']|$|>)", "ig");
            var matches = re.exec(text);
            if(matches != null && matches.length) {
	            return trim(matches[3]);
            }
            return "";
        }                 
        
        function addslashes(str) {
            var searcharray = ['\\\\', '\\\'', '\\\/', '\\\(', '\\\)', '\\\[', '\\\]', '\\\{', '\\\}', '\\\^', '\\\$', '\\\?', '\\\.', '\\\*', '\\\+', '\\\|'];
            var replacearray = ['\\\\', '\\\'', '\\/', '\\(', '\\)', '\\[', '\\]', '\\{', '\\}', '\\^', '\\$', '\\?', '\\.', '\\*', '\\+', '\\|'];
            var len = searcharray.length;
			if(typeof(str)=="string" && str!=""){
            for(var i = 0; i < len; i++) {
	            re = new RegExp(searcharray[i], "g");
	            str = str.replace(re, replacearray[i]);
            }}
            return str;
        }  
         
        function atag(aoptions, text) {
            if(trim(text) == '') {
                return '';
            }

            href = getoptionvalue('href', aoptions);

            if(href.substr(0, 11) == 'javascript:') {
                return trim(recursion('a', text, 'atag'));
            } else if(href.substr(0, 7) == 'mailto:') {
                tag = 'email';
                href = href.substr(7);
            } else {
                tag = 'url';
            }

            return '[' + tag + '=' + href + ']' + trim(recursion('a', text, 'atag')) + '[/' + tag + ']';
        }

        function dpstag(options, text, tagname) {
            if(trim(text) == '') {
	            return '';
            }
            var pend = parsestyle(options, '', '');
            var prepend = pend['prepend'];
            var append = pend['append'];
            if(in_array(tagname, ['div', 'p'])) {
	            align = getoptionvalue('align', options);
	            if(in_array(align, ['left', 'center', 'right'])) {
		            prepend = '[align=' + align + ']' + prepend;
		            append += '[/align]';
	            } else {
		            append += "\n";
	            }
            }
            return prepend + recursion(tagname, text, 'dpstag') + append;
        }
 
        function fonttag(fontoptions, text) {
            var prepend = '';
            var append = '';
            var tags = new Array();
            tags = {'font' : 'face=', 'size' : 'size=', 'color' : 'color='};
            for(bbcode in tags) {
	            optionvalue = fetchoptionvalue(tags[bbcode], fontoptions);
	            if(optionvalue) {
		            prepend += '[' + bbcode + '=' + optionvalue + ']';
		            append = '[/' + bbcode + ']' + append;
	            }
            }

            var pend = parsestyle(fontoptions, prepend, append);
            return pend['prepend'] + recursion('font', text, 'fonttag') + pend['append'];
        }
 
        function imgtag(attributes) {
            var width = '';
            var height = '';

            re = /src=(["']?)([\s\S]*?)(\1)/i;
            var matches = re.exec(attributes);
            if(matches != null) {
	            var src = matches[2];
            } else {
	            return '';
            }

            re = /width=(["']?)(\d+)(\1)/i;
            var matches = re.exec(attributes);
            if(matches != null) {
	            width = matches[2];
            }

            re = /height=(["']?)(\d+)(\1)/i;
            var matches = re.exec(attributes);
            if(matches != null) {
	            height = matches[2];
            }

            return width > 0 && height > 0 ?
	            '[img=' + width + ',' + height + ']' + src + '[/img]' :
	            '[img]' + src + '[/img]';
        }

        function litag(listoptions, text) {
            return '[*]' + text.replace(/(\s+)$/g, '');
        }
            
        function listtag(listoptions, text, tagname) {
            text = text.replace(/<li>(([\s\S](?!<\/li))*?)(?=<\/?ol|<\/?ul|<li|\[list|\[\/list)/ig, '<li>$1</li>') + (System.UserAgent.opera ? '</li>' : '');
            text = recursion('li', text, 'litag');
            var opentag = '[list]';
            if(tagname == 'ol') {
	            var listtype = fetchoptionvalue('type=', listoptions);
	            listtype = listtype != '' ? listtype : '1';
	            if(in_array(listtype, ['1', 'a', 'A'])) {
		            opentag = '[list=' + listtype + ']';
	            }
            }
            return text ? opentag + recursion(tagname, text, 'listtag') + '[/list]' : '';
        }

        function tdtag(attributes) {
	        var colspan = 1;
	        var rowspan = 1;
	        var width = '';

	        re = /colspan=(["']?)(\d{1,2})(\1)/ig;
	        var matches = re.exec(attributes);
	        if(matches != null) {
		        colspan = matches[2];
	        }

	        re = /rowspan=(["']?)(\d{1,2})(\1)/ig;
	        var matches = re.exec(attributes);
	        if(matches != null) {
		        rowspan = matches[2];
	        }

	        re = /width=(["']?)(\d{1,3}%?)(\1)/ig;
	        var matches = re.exec(attributes);
	        if(matches != null) {
		        width = matches[2];
	        }

	        return in_array(width, ['', '0', '100%']) ?
		        (colspan == 1 && rowspan == 1 ? '[td]' : '[td=' + colspan + ',' + rowspan + ']') :
		        '[td=' + colspan + ',' + rowspan + ',' + width + ']';
        }    

        function parsestyle(tagoptions, prepend, append) {
	        var searchlist = [
		        ['align', true, 'text-align:\\s*(left|center|right);?', 1],
		        ['color', true, '^(?:\\s|)color:\\s*([^;]+);?', 1],
		        ['font', true, 'font-family:\\s*([^;]+);?', 1],
		        ['size', true, 'font-size:\\s*(\\d+(px|pt|in|cm|mm|pc|em|ex|%|));?', 1],
		        ['b', false, 'font-weight:\\s*(bold);?'],
		        ['i', false, 'font-style:\\s*(italic);?'],
		        ['u', false, 'text-decoration:\\s*(underline);?']
	        ];
	        var style = getoptionvalue('style', tagoptions);
	        re = /^(?:\s|)color:\s*rgb\((\d+),\s*(\d+),\s*(\d+)\)(;?)/ig;
	        style = style.replace(re, function($1, $2, $3, $4, $5) {return("color:#" + parseInt($2).toString(16) + parseInt($3).toString(16) + parseInt($4).toString(16) + $5);});
	        var len = searchlist.length;
	        for(var i = 0; i < len; i++) {
		        re = new RegExp(searchlist[i][2], "ig");
		        match = re.exec(style);
		        if(match != null) {
			        opnvalue = match[searchlist[i][3]];
			        prepend += '[' + searchlist[i][0] + (searchlist[i][1] == true ? '=' + opnvalue + ']' : ']');
			        append = '[/' + searchlist[i][0] + ']' + append;
		        }
	        }
	        return {'prepend' : prepend, 'append' : append};
        }

        function parsetable(width, str) {
	        if(typeof width == 'undefined') {
		        var width = '';
	        } else {
		        width = width.substr(width.length - 1, width.length) == '%' ? (width.substr(0, width.length - 1) <= 98 ? width : '98%') : (width <= 560 ? width : '98%');
	        }

	        var string = '<table '
		        + (width == '' ? '' : 'width="' + width + '" ')
		        + 'align="center" class="t_table">';

	        str = str.replace(/\[td=(\d{1,2}),(\d{1,2})(,(\d{1,3}%?))?\]/ig, '<td colspan="$1" rowspan="$2" width="$4">');
	        str = str.replace(/\[tr\]/ig, '<tr>');
	        str = str.replace(/\[td\]/ig, '<td>');
	        str = str.replace(/\[\/td\]/ig, '</td>');
	        str = str.replace(/\[\/tr\]/ig, '</tr>');

	        string += str;
	        string += '</table>';

	        return string;
        }
          
        function simpletag(options, text, tagname, parseto) {
            if(trim(text) == '') {
	            return '';
            }
            text = recursion(tagname, text, 'simpletag', parseto);
            return '[' + parseto + ']' + text + '[/' + parseto + ']';
        }
        
        function tabletag(attributes) {
            var width = '';
            re = /width=(["']?)(\d{1,3}%?)(\1)/ig;
            var matches = re.exec(attributes);
            if(matches != null && matches.length) {
	            width = matches[2].substr(matches[2].length - 1, matches[2].length) == '%' ?
		            (matches[2].substr(0, matches[2].length - 1) <= 98 ? matches[2] : '98%') :
		            (matches[2] <= 560 ? matches[2] : '98%');
            } else {
	            re = /width\s?:\s?(\d{1,3})([px|%])/ig;
	            var matches = re.exec(attributes);
	            if(matches != null && matches.length) {
		            width = matches[2] == '%' ? (matches[1] <= 98 ? matches[1] : '98%') : (matches[1] <= 560 ? matches[1] : '98%');
	            }
            }
            return width == '' ? '[table]' : '[table=' + width + ']';
        }
        
        function parsecode(text) {
            pcodecount++;

            text = text.replace(/^[\n\r]*([\s\S]+?)[\n\r]*$/ig, '$1');
            text = htmlspecialchars(text);

            codehtml[pcodecount] = '[code]' + text + '[/code]';

            codecount++;
            return "[\tDISCUZ_CODE_" + pcodecount + "\t]";
        }
        
        function codetag(text) {
            pcodecount++;

            text = text.replace(/<br[^\>]*>/ig, "\n");
            text = text.replace(/^[\n\r]*([\s\S]+?)[\n\r]*$/ig, '$1');
            text = text.replace(/<(\/|)[A-Za-z].*?>/ig, '');

            codehtml[pcodecount] = "[code]" + text + "[/code]";
            codecount++;
            return "[\tDISCUZ_CODE_" + pcodecount + "\t]";
        }
        
        function cuturl(url) {
            var length = 65;
            var urllink = '<a href="' + (url.toLowerCase().substr(0, 4) == 'www.' ? 'http://' + url : url) + '" target="_blank">';
            if(url.length > length) {
	            url = url.substr(0, parseInt(length * 0.5)) + ' ... ' + url.substr(url.length - parseInt(length * 0.3));
            }
            urllink += url + '</a>';
            return urllink;
        }  
                                                                     
        function recursion(tagname, text, dofunction, extraargs) {
            if(extraargs == null) {
	            extraargs = '';
            }
            tagname = tagname.toLowerCase();

            var open_tag = '<' + tagname;
            var open_tag_len = open_tag.length;
            var close_tag = '</' + tagname + '>';
            var close_tag_len = close_tag.length;
            var beginsearchpos = 0;

            do {
	            var textlower = text.toLowerCase();
	            var tagbegin = textlower.indexOf(open_tag, beginsearchpos);
	            if(tagbegin == -1) {
		            break;
	            }

	            var strlen = text.length;

	            var inquote = '';
	            var found = false;
	            var tagnameend = false;
	            var optionend = 0;
	            var t_char = '';

	            for(optionend = tagbegin; optionend <= strlen; optionend++) {
		            t_char = text.charAt(optionend);
		            if((t_char == '"' || t_char == "'") && inquote == '') {
			            inquote = t_char;
		            } else if((t_char == '"' || t_char == "'") && inquote == t_char) {
			            inquote = '';
		            } else if(t_char == '>' && !inquote) {
			            found = true;
			            break;
		            } else if((t_char == '=' || t_char == ' ') && !tagnameend) {
			            tagnameend = optionend;
		            }
	            }

	            if(!found) {
		            break;
	            }
	            if(!tagnameend) {
		            tagnameend = optionend;
	            }

	            var offset = optionend - (tagbegin + open_tag_len);
	            var tagoptions = text.substr(tagbegin + open_tag_len, offset)
	            var acttagname = textlower.substr(tagbegin * 1 + 1, tagnameend - tagbegin - 1);

	            if(acttagname != tagname) {
		            beginsearchpos = optionend;
		            continue;
	            }

	            var tagend = textlower.indexOf(close_tag, optionend);
	            if(tagend == -1) {
		            break;
	            }

	            var nestedopenpos = textlower.indexOf(open_tag, optionend);
	            while(nestedopenpos != -1 && tagend != -1) {
		            if(nestedopenpos > tagend) {
			            break;
		            }
		            tagend = textlower.indexOf(close_tag, tagend + close_tag_len);
		            nestedopenpos = textlower.indexOf(open_tag, nestedopenpos + open_tag_len);
	            }

	            if(tagend == -1) {
		            beginsearchpos = optionend;
		            continue;
	            }

	            var localbegin = optionend + 1;
	            var localtext = eval(dofunction)(tagoptions, text.substr(localbegin, tagend - localbegin), tagname, extraargs);

	            text = text.substring(0, tagbegin) + localtext + text.substring(tagend + close_tag_len);

	            beginsearchpos = tagbegin + localtext.length;

            } while(tagbegin != -1);

            return text;
        }         
    
         
        return {

            bbcode2html:function(str) {
	            str = trim(str);
	            if(str == '') {
		            return '';
	            }
	            if(!fetchCheckbox('bbcodeoff') && allowbbcode) {
		            str= str.replace(/\s*\[code\]([\s\S]+?)\[\/code\]\s*/ig, function($1, $2) {return parsecode($2);});
	            }
	            if(!forumallowhtml && !(allowhtml && fetchCheckbox('htmlon'))) {
		            str = str.replace(/</ig, '&lt;');
		            str = str.replace(/>/ig, '&gt;');
	            }

	            if(!fetchCheckbox('smileyoff') && allowsmilies) {
		            for(var id=0;id<System.component.EditorResources.smilies.length;id++) {
			            re = new RegExp(addslashes(System.component.EditorResources.smilies[id]['code']), "g");
			            str = str.replace(re, '<img src="./images/smilies/' + System.component.EditorResources.smilies[id]['url'] + '" border="0" smilieid="' + id + '" alt="' + System.component.EditorResources.smilies[id]['code'] + '" />');
		            }
	            }

	            if(!fetchCheckbox('parseurloff')) {
		            str = str.replace(/^((http|https|ftp|rtsp|mms):\/\/[A-Za-z0-9\.\/=\?%\-&_~`@':+!]+)/ig, '<a href="$1" target="_blank">$1</a>');
		            str = str.replace(/((http|https|ftp|rtsp|mms):\/\/[A-Za-z0-9\.\/=\?%\-&_~`@':+!]+)$/ig, '<a href="$1" target="_blank">$1</a>');
		            str = str.replace(/[^>=\]""]((http|https|ftp|rtsp|mms):\/\/[A-Za-z0-9\.\/=\?%\-&_~`@':+!]+)/ig, '<a href="$1" target="_blank">$1</a>');
	            }

	            if(!fetchCheckbox('bbcodeoff') && allowbbcode) {
		            str= str.replace(/\[url\]\s*(www.|https?:\/\/|ftp:\/\/|gopher:\/\/|news:\/\/|telnet:\/\/|rtsp:\/\/|mms:\/\/|callto:\/\/|ed2k:\/\/){1}([^\[\"']+?)\s*\[\/url\]/ig, function($1, $2, $3) {return cuturl($2 + $3);});
		            str= str.replace(/\[url=www.([^\[\"']+?)\](.+?)\[\/url\]/ig, '<a href="http://www.$1" target="_blank">$2</a>');
		            str= str.replace(/\[url=(https?|ftp|gopher|news|telnet|rtsp|mms|callto|ed2k){1}:\/\/([^\[\"']+?)\]([\s\S]+?)\[\/url\]/ig, '<a href="$1://$2" target="_blank">$3</a>');

		            str= str.replace(/\[email\](.*?)\[\/email\]/ig, '<a href="mailto:$1">$1</a>');

		            str= str.replace(/\[email=(.[^\[]*)\](.*?)\[\/email\]/ig, '<a href="mailto:$1" target="_blank">$2</a>');

		            str = str.replace(/\[color=([^\[\<]+?)\]/ig, '<font color="$1">');

		            str = str.replace(/\[size=(\d+?)\]/ig, '<font size="$1">');
		            str = str.replace(/\[size=(\d+(px|pt|in|cm|mm|pc|em|ex|%)+?)\]/ig, '<font style="font-size: $1">');
		            str = str.replace(/\[font=([^\[\<]+?)\]/ig, '<font face="$1">');
		            str = str.replace(/\[align=([^\[\<]+?)\]/ig, '<p align="$1">');

		            re = /\s*\[table(=(\d{1,3}%?))?\][\n\r]*([\s\S]+?)[\n\r]*\[\/table\]\s*/ig;
		            str = str.replace(re, function($1, $2, $3, $4) {return parsetable($3, $4);});
		            str = str.replace(re, function($1, $2, $3, $4) {return parsetable($3, $4);});
		            str = str.replace(re, function($1, $2, $3, $4) {return parsetable($3, $4);});
		            str = str.replace(re, function($1, $2, $3, $4) {return parsetable($3, $4);});

		            var searcharray = new Array(
			            '\\\[\\\/color\\\]', '\\\[\\\/size\\\]', '\\\[\\\/font\\\]', '\\\[\\\/align\\\]', '\\\[b\\\]', '\\\[\\\/b\\\]',
			            '\\\[i\\\]', '\\\[\\\/i\\\]', '\\\[u\\\]', '\\\[\\\/u\\\]', '\\\[list\\\]', '\\\[list=1\\\]', '\\\[list=a\\\]',
			            '\\\[list=A\\\]', '\\\[\\\*\\\]', '\\\[\\\/list\\\]', '\\\[indent\\\]', '\\\[\\\/indent\\\]'
		            );
		            var replacearray = new Array(
			            '</font>', '</font>', '</font>', '</p>', '<b>', '</b>', '<i>',
			            '</i>', '<u>', '</u>', '<ul>', '<ol type=1>', '<ol type=a>',
			            '<ol type=A>', '<li>', '</ul></ol>', '<blockquote>', '</blockquote>'
		            );
		            var len = searcharray.length;
		            for(var i = 0; i < len; i++) {
			            re = new RegExp(searcharray[i], "ig");
			            str = str.replace(re, replacearray[i]);
		            }
	            }

	            if(!fetchCheckbox('bbcodeoff')) {
		            if(allowimgcode) {
			            str = str.replace(/\[img\]\s*([^\[\<\r\n]+?)\s*\[\/img\]/ig, '<img src="$1" border="0" onload="if(this.width>screen.width*0.7) {this.resized=true; this.width=screen.width*0.7; this.alt=\'Click here to open new window\\nCTRL+Mouse wheel to zoom in/out\';}" onmouseover="if(this.width>screen.width*0.7) {this.resized=true; this.width=screen.width*0.7; this.style.cursor=\'hand\'; this.alt=\'Click here to open new window\\nCTRL+Mouse wheel to zoom in/out\';}" onclick="if(!this.resized) {return true;} else {window.open(\'$1\');}" onmousewheel="return imgzoom(this);" alt="" />');
			            str = str.replace(/\[img=(\d{1,3})[x|\,](\d{1,3})\]\s*([^\[\<\r\n]+?)\s*\[\/img\]/ig, '<img width="$1" height="$2" src="$3" border="0" alt="" />');

		            } else {
			            str = str.replace(/\[img\]\s*([^\[\<\r\n]+?)\s*\[\/img\]/ig, '<a href="$1" target="_blank">$1</a>');
			            str = str.replace(/\[img=(\d{1,3})[x|\,](\d{1,3})\]\s*([^\[\<\r\n]+?)\s*\[\/img\]/ig, '<a href="$1" target="_blank">$1</a>');
		            }
	            }

	            for(var i = 0; i <= pcodecount; i++) {
		            str = str.replace("[\tDISCUZ_CODE_" + i + "\t]", codehtml[i]);
	            }

	            if(!forumallowhtml && !(allowhtml && fetchCheckbox('htmlon'))) {
		            str = str.replace(/\t/ig, '&nbsp; &nbsp; &nbsp; &nbsp; ');
		            str = str.replace(/   /ig, '&nbsp; &nbsp;');
		            str = str.replace(/  /ig, '&nbsp;&nbsp;');
		            str = str.replace(/\r\n/ig, '<br />');
		            str = str.replace(/[\r\n]/ig, '<br />');
	            }

	            return(str);
            },
            html2bbcode:function(str) {

	            str = trim(str);

	            if(str == '' || forumallowhtml || (allowhtml && fetchCheckbox('htmlon'))) {
		            return str;
	            }

	            str= str.replace(/\s*\[code\]([\s\S]+?)\[\/code\]\s*/ig, function($1, $2) {return codetag($2);});
	            str = str.replace(/<style.*?>[\s\S]*?<\/style>/ig, '');
	            str = str.replace(/<script.*?>[\s\S]*?<\/script>/ig, '');
	            str = str.replace(/<noscript.*?>[\s\S]*?<\/noscript>/ig, '');
	            str = str.replace(/<select.*?>[\s\S]*?<\/select>/ig, '');
	            str = str.replace(/<object.*?>[\s\S]*?<\/object>/ig, '');
	            str = str.replace(/<!--[\s\S]*?-->/ig, '');
	            str = str.replace(/on[a-zA-Z]{3,16}\s?=\s?(["'])[\s\S]*?\1/ig, '');
	            str = str.replace(/(\r\n|\n|\r)/ig, '');

	            str = str.replace(/<table([^>]*width[^>]*)>/ig, function($1, $2) {return tabletag($2);});
	            str = str.replace(/<table[^>]*>/ig, '[table]');
	            str = str.replace(/<tr[^>]*>/ig, '[tr]');
	            str = str.replace(/<td>/ig, '[td]');
	            str = str.replace(/<td([^>]+)>/ig, function($1, $2) {return tdtag($2);});
	            str = str.replace(/<\/td>/ig, '[/td]');
	            str = str.replace(/<\/tr>/ig, '[/tr]');
	            str = str.replace(/<\/table>/ig, '[/table]');

	            str = str.replace(/<h([0-9]+)[^>]*>(.*)<\/h\\1>/ig, "[size=$1]$2[/size]\n\n");
	            str = str.replace(/<img[^>]+smilieid=(["']?)(\d+)(\1)[^>]*>/ig, function($1, $2, $3) {return System.component.EditorResources.smilies[$3]['code'];});
	            str = str.replace(/<img([^>]*src[^>]*)>/ig, function($1, $2) {return imgtag($2);});
	            str = str.replace(/<a\s+?name=(["']?)(.+?)(\1)[\s\S]*?>([\s\S]*?)<\/a>/ig, '$4');
	            str = str.replace(/<br[^\>]*>/ig, "\n");

	            str = recursion('b', str, 'simpletag', 'b');
	            str = recursion('strong', str, 'simpletag', 'b');
	            str = recursion('i', str, 'simpletag', 'i');
	            str = recursion('em', str, 'simpletag', 'i');
	            str = recursion('u', str, 'simpletag', 'u');
	            str = recursion('a', str, 'atag');
	            str = recursion('font', str, 'fonttag');
	            str = recursion('blockquote', str, 'simpletag', 'indent');
	            str = recursion('ol', str, 'listtag');
	            str = recursion('ul', str, 'listtag');
	            str = recursion('div', str, 'dpstag');
	            str = recursion('p', str, 'dpstag');
	            str = recursion('span', str, 'dpstag');

	            str = str.replace(/<[\/\!]*?[^<>]*?>/ig, '');

	            for(var i = 0; i <= pcodecount; i++) {
		            str = str.replace("[\tDISCUZ_CODE_" + i + "\t]", codehtml[i]);
	            }

	            str = str.replace(/&amp;/ig, '&');
	            str = str.replace(/&nbsp;/ig, ' ');
	            str = str.replace(/&lt;/ig, '<');
	            str = str.replace(/&gt;/ig, '>');

	            return str
            }
        };
    })();    
}

if(typeof(System.component.EditorManager)=="undefined"){
    System.component.EditorManager=(function(){
        var editorCollection=new System.MixedCollection();
        var activeEditorID=-1;
        return {
            editor_AUTOID:0,
			getUniqueID:function(){
				return ++this.editor_AUTOID;
			},
			createEditor:function(domID){
			    if(typeof(domID)!=="string" || domID==""){alert("\u9519\u8BEF\u7684 Editor DomID£¡");return;} 
			    var objID=this.getUniqueID();
			    /*if(!editorCollection.containsKey(domID)){
			    	editorCollection.add(domID,new System.component.Editor(objID,domID));
			    }else{*/
			    	editorCollection.add(objID,new System.component.Editor(objID,domID));
			    //}
			},
			getInstance:function(key){
			    return editorCollection.get(key);
			},
			setActive:function(objID){
				activeEditorID=objID;
			},
			getActive:function(){
				return this.getInstance(activeEditorID);
			},
			enableEditor:function(editorid){
				if(!editorid)return;
				var fctpanel=document.getElementById(editorid+ "_fctpanel");
				if(fctpanel && fctpanel.nodeType){
						fctpanel.style.display="block";
				}fctpanel=null;
				var controlpanel=document.getElementById(editorid+ "_controlpanel");
				if(controlpanel && controlpanel.nodeType){
					controlpanel.style.display="";
				}controlpanel=null;
				/*var editbox=document.getElementById(editorid+ "_editbox");
				if(editbox && editbox.nodeType){
						editbox.style.display="";
				}editbox=null;*/
				var editIframe=document.getElementById(editorid + "_container_Iframe");
				if(editIframe && editIframe.nodeType){
					editIframe.style.display="";
				}editIframe=null;
				var editviewer=document.getElementById(editorid+ "_editviewer");
				if(editviewer && editviewer.nodeType){
						editviewer.style.display="none";
				}editviewer=null;
			},
			disableEditor:function(editorid){
				if(!editorid)return;
				var fctpanel=document.getElementById(editorid+ "_fctpanel");
				if(fctpanel && fctpanel.nodeType){
						fctpanel.style.display="none";
				}fctpanel=null;
				var controlpanel=document.getElementById(editorid+ "_controlpanel");
				if(controlpanel && controlpanel.nodeType){
					controlpanel.style.display="none";
				}controlpanel=null;
				/*var editbox=document.getElementById(editorid+ "_editbox");
				if(editbox && editbox.nodeType){
						editbox.style.display="none";
				}editbox=null;*/
				var editIframe=document.getElementById(editorid + "_container_Iframe");
				if(editIframe && editIframe.nodeType){
					editIframe.style.display="none";
				}editIframe=null;
				var editviewer=document.getElementById(editorid+ "_editviewer");
				if(editviewer && editviewer.nodeType){
						editviewer.style.display="";
				}editviewer=null;
			}   
        };        
    })();
} 

if(typeof(System.component.EditorDialogManager)=="undefined"){
	System.component.EditorDialogManager=(function(){
		var context=Wade.context;
		var inserImageDialog=null,uploadFileDialog=null;
		return {
			openInsertTableDialog:function(){
				
			},
			openInsertImageDialog:function(btn){
				if(!inserImageDialog){
					var url = context.getContextName() + "?service=page/component.popups.editor.upload_image";
					inserImageDialog=new System.UI.Components.BasicDialog("mfeditor_insertimage_dialog", {
						autoCreate:true,
	                    shadow: false,
	                    draggable: false,
	                    resizable:false,
	                    modal: true,
	                    fixedcenter:true,
	                    shim:false,
	                    width:400, height:290,
	                    title:"\u63D2\u5165\u56FE\u7247"
	                });
	                var f=inserImageDialog.body.createChild({
		                tag:"iframe",
		                style:{border:"none",width:"100%",height:"100%"},
		                scroll:"no",frameborder:"0",src:""
		            });f.dom.contentWindow.location=url;f=null;
		            /*inserImageDialog.refreshClick=function(e){
			        	var dom=document.getElementById("wade_dlg_frames_" + currentOpenFrameIndex);
			        	if(dom){
			        		if(typeof(wadeDialogFrames[currentOpenFrameIndex])!='undefined'){
			        			dom.src=util.setRandomParam(wadeDialogFrames[currentOpenFrameIndex]);
			        		}
			        	}dom=null;
			        };
			        wadeDialog.refreshBtn = wadeDialog.toolbox.createChild({cls:"x-dlg-refresh"});
			        wadeDialog.refreshBtn.on("click", wadeDialog.refreshClick, wadeDialog);
			        wadeDialog.refreshBtn.addClassOnOver("x-dlg-refresh-over");*/
				}
				inserImageDialog.show();
			},
			addInsertImageDialogOnShowListener:function(fn,scope){
				if(inserImageDialog && typeof(fn)=="function"){
					inserImageDialog.on("show",fn,scope);
				}
			},
			closeInsertImageDialog:function(){
				if(inserImageDialog)inserImageDialog.hide();
				System.CG();
			},
			openUploadFileDialog:function(){
				if(!uploadFileDialog){
					var url = context.getContextName() + "?service=page/component.popups.editor.upload_file";
					uploadFileDialog=new System.UI.Components.BasicDialog("mfeditor_uploadfile_dialog", {
						autoCreate:true,
	                    shadow: false,
	                    draggable: false,
	                    resizable:false,
	                    modal: true,
	                    fixedcenter:true,
	                    shim:false,
	                    width:400, height:190,
	                    title:"\u63D2\u5165\u6587\u4EF6"
	                });
	                var f=uploadFileDialog.body.createChild({
		                tag:"iframe",
		                style:{border:"none",width:"100%",height:"100%"},
		                scroll:"no",frameborder:"0",src:""
		            });f.dom.contentWindow.location=url;f=null;
				}
				uploadFileDialog.show();				
			},
			addUploadFileDialogOnShowListener:function(fn,scope){
				if(uploadFileDialog && typeof(fn)=="function"){
					uploadFileDialog.on("show",fn,scope);
				}
			},
			closeUploadFileDialog:function(){
				if(uploadFileDialog)uploadFileDialog.hide();
				System.CG();
			}			
		}
	})();
}

if(typeof(System.component.EditorHelper)=="undefined"){
    System.component.EditorHelper=(function(){
    
        function str_pad(text, length, padstring) {
	        text += '';
	        padstring += '';

	        if(text.length < length) {
		        padtext = padstring;

		        while(padtext.length < (length - text.length)) {
			        padtext += padstring;
		        }

		        text = padtext.substr(0, (length - text.length)) + text;
	        }

	        return text;
        }
        
        function stripos(haystack, needle, offset) {
	        if(typeof(offset)=="undefined") {
		        offset = 0;
	        }
	        var index = haystack.toLowerCase().indexOf(needle.toLowerCase(), offset);

	        return (index == -1 ? false : index);
        }
        
        function rgbhexToColor(r, g, b) {
	        var coloroptions = {'#000000' : 'Black', '#a0522d' : 'Sienna', '#556b2f' : 'DarkOliveGreen', '#006400' : 'DarkGreen', '#483d8b' : 'DarkSlateBlue', '#000080' : 'Navy', '#4b0082' : 'Indigo', '#2f4f4f' : 'DarkSlateGray', '#8b0000' : 'DarkRed', '#ff8c00' : 'DarkOrange', '#808000' : 'Olive', '#008000' : 'Green', '#008080' : 'Teal', '#0000ff' : 'Blue', '#708090' : 'SlateGray', '#696969' : 'DimGray', '#ff0000' : 'Red', '#f4a460' : 'SandyBrown', '#9acd32' : 'YellowGreen', '#2e8b57' : 'SeaGreen', '#48d1cc' : 'MediumTurquoise', '#4169e1' : 'RoyalBlue', '#800080' : 'Purple', '#808080' : 'Gray', '#ff00ff' : 'Magenta', '#ffa500' : 'Orange', '#ffff00' : 'Yellow', '#00ff00' : 'Lime', '#00ffff' : 'Cyan', '#00bfff' : 'DeepSkyBlue', '#9932cc' : 'DarkOrchid', '#c0c0c0' : 'Silver', '#ffc0cb' : 'Pink', '#f5deb3' : 'Wheat', '#fffacd' : 'LemonChiffon', '#98fb98' : 'PaleGreen', '#afeeee' : 'PaleTurquoise', '#add8e6' : 'LightBlue', '#dda0dd' : 'Plum', '#ffffff' : 'White'};
	        return coloroptions['#' + (str_pad(r, 2, 0) + str_pad(g, 2, 0) + str_pad(b, 2, 0))];
        }
        
        return {
            formatFontsize:function(csssize) {
	            switch(csssize) {
		            case '7.5pt':
		            case '10px': return 1;
		            case '10pt': return 2;
		            case '12pt': return 3;
		            case '14pt': return 4;
		            case '18pt': return 5;
		            case '24pt': return 6;
		            case '36pt': return 7;
		            default: return System.component.EditorResources.lang['fontsize'];
	            }
            },
            rgbToColor:function(forecolor,defaultcolor) {
	            if(!System.UserAgent.gecko && !System.UserAgent.pera) {
		            return rgbhexToColor((forecolor & 0xFF).toString(16), ((forecolor >> 8) & 0xFF).toString(16), ((forecolor >> 16) & 0xFF).toString(16));
	            }
	            if(forecolor == "" || forecolor == null) {
		            forecolor = defaultcolor;
	            }
	            if(forecolor && forecolor.toLowerCase().indexOf('rgb') == 0) {
		            var matches = forecolor.match(/^rgb\s*\(([0-9]+),\s*([0-9]+),\s*([0-9]+)\)$/);
		            if(matches) {
			            return rgbhexToColor((matches[1] & 0xFF).toString(16), (matches[2] & 0xFF).toString(16), (matches[3] & 0xFF).toString(16));
		            } else {
			            return System.component.EditorHelper.rgbToColor(null);
		            }
	            } else {
		            return forecolor;
	            }
            },
            stripSimple:function(tag, str, iterations) {
	            var opentag = '[' + tag + ']';
	            var closetag = '[/' + tag + ']';

	            if(typeof(iterations)=="undefined") {
		            iterations = -1;
	            }
	            while((startindex = stripos(str, opentag)) !== false && iterations != 0) {
		            iterations --;
		            if((stopindex = stripos(str, closetag)) !== false) {
			            var text = str.substr(startindex + opentag.length, stopindex - startindex - opentag.length);
			            str = str.substr(0, startindex) + text + str.substr(stopindex + closetag.length);
		            } else {
			            break;
		            }
	            }
	            return str;
            },
            stripComplex:function(tag, str, iterations) {
	            var opentag = '[' + tag + '=';
	            var closetag = '[/' + tag + ']';

	            if(typeof(iterations)=="undefined") {
		            iterations = -1;
	            }
	            while((startindex = stripos(str, opentag)) !== false && iterations != 0) {
		            iterations --;
		            if((stopindex = stripos(str, closetag)) !== false) {
			            var openend = stripos(str, ']', startindex);
			            if(openend !== false && openend > startindex && openend < stopindex) {
				            var text = str.substr(openend + 1, stopindex - openend - 1);
				            str = str.substr(0, startindex) + text + str.substr(stopindex + closetag.length);
			            } else {
				            break;
			            }
		            } else {
			            break;
		            }
	            }
	            return str;
            }            
        };
    })();
}

if(typeof(System.component.EditorMenu)=="undefined"){
var menuslidetimer = null;
function doane(eventobj) {
	if(!eventobj || System.UserAgent.ie)	{
		window.event.returnValue = false;
		window.event.cancelBubble = true;
		return window.event;
	} else {
		eventobj.stopPropagation();
		eventobj.preventDefault();
		return eventobj;
	}
}

function ebygum(eventobj) {
	if(!eventobj || System.UserAgent.ie) {
		window.event.cancelBubble = true;
		return window.event;
	} else {
		if(eventobj.target.type == 'submit')  eventobj.target.form.submit();
		eventobj.stopPropagation();
		return eventobj;
	}
}

System.component.EditorMenu=(function(){
    var open_steps=2;
    var open_fade = false;
    var active = false;
    var menus = [];
    var activemenu = null;
    var hidden_selects =[];
    return {
        Popup_Handler:{
            activate:function(act) {
	            active = act;
            },
            checkActive:function(){
                return active;
            },
            register:function(menuEl,clickActive,editorDomID) {
                if(!menuEl || !menuEl.nodeType) return;
                var cmd=menuEl.getAttribute("cmd");
                if(typeof(cmd)!="string" || cmd=="") return;
                cmd=cmd.split(",")[0];
                var controlKey=editorDomID + "_" + cmd;
	            menus[controlKey] = new System.component.EditorMenu.Popup_Menu(menuEl,clickActive, editorDomID,cmd);
	            return menus[controlKey];
            },
            hide:function(){
	            if(activemenu != null){ menus[activemenu].hide();}
            },
            menuhide:function() {
		        if(activemenu != null){ menus[activemenu].slidehide();}
            },
            getActiveMenu:function(){
                if(activemenu != null){ return menus[activemenu];}
                return null;
            },
            setActiveMenu:function(controlKey){
                activemenu=controlKey;
            }            
        },
        Popup_Events:{
            menuHandler_Show:function(e) {
	            doane(e);
	            clearTimeout(this.slidetimer);
	            if(activemenu == null || menus[activemenu].controlKey != this.controlKey)	{menus[this.controlKey].show(this, false, menus[this.controlKey].clickActive);}
            },
            menuHandler_OnClick:function(e) {
	            doane(e);
	            if(activemenu == null || menus[activemenu].controlKey != this.controlKey)	{menus[this.controlKey].show(this, false, menus[this.controlKey].clickActive);}
	            else {menus[this.controlKey].hide();}
            },
            menuHandler_OnMouseover:function(e) {
	            doane(e);
	            menus[this.controlKey].hover(this);
            },
            menuOption_Onclick_Function:function(e) {
	            if(typeof(this.ofunc)=="function"){this.ofunc(e);}
	            menus[this.controlKey].hide();
            },
            menuOption_OnClick_Link:function(e) {
	            menus[this.controlKey].choose(e, this);
            },
            menuOption_OnMouseover:function(e) {
	            this.className = "dizEditorPopupmenu_highlight";
            },
            menuOption_OnMouseout:function(e) {
	            this.className = "dizEditorPopupmenu_option";
            }            
        },
        Popup_Menu:function(menuHandlerEl,clickActive,editorDomID, cmd) {
            this.menuHandler=menuHandlerEl;
            
            this.menuHandler.controlKey=this.controlKey=editorDomID + "_" + cmd;
            this.editorDomID=editorDomID;
            this.cmd=cmd;
            this.clickActive =clickActive;
              
            this.slide_open = (System.UserAgent.opera ? false : true);
            this.open_steps = open_steps;
                         
            this.menuName = "popup_" + cmd + "_Menu";
            this.menuEl=dh.DomQuery.select("#" + editorDomID + " ul[name=" + this.menuName + "]");
            if(this.menuEl && this.menuEl.length){
                this.menuEl=this.menuEl[0];
                this.init_Menu(clickActive);
            }
            this.init_Control();
        }
    };
})();

function popupMenu_Hide(e) {
	if(e && e.button && e.button != 1 && e.type == "click"){  return true;}
	else {System.component.EditorMenu.Popup_Handler.hide();}
}    
System.EventManager.on(document,"click",popupMenu_Hide);
System.EventManager.onWindowResize(popupMenu_Hide);
System.component.EditorMenu.Popup_Handler.activate(true);
   
System.extend(System.component.EditorMenu.Popup_Menu, System.Event.Observable, {
	init_Menu:function() {
		if(this.menuEl && !this.menuEl.initialized) {
			this.menuEl.initialized= true;
			this.menuEl.onclick = ebygum;
			//this.menuEl.style.position = "absolute";
			if(!this.clickActive) {
				this.menuEl.onmouseover = function() {
					clearTimeout(menuslidetimer);
				}
				this.menuEl.onmouseout = function() {
					menuslidetimer = setTimeout("System.component.EditorMenu.Popup_Handler.menuhide()",500);
				}
			}
			this.menuEl.style.zIndex = 50;
			if(System.UserAgent.ie && !System.UserAgent.mac) {
				this.menuEl.style.filter += "progid:DXImageTransform.Microsoft.shadow(direction=135,color=#CCCCCC,strength=2)";
			}
			
			this.init_Menu_Contents();
		}
	}, 
	init_Control:function() {
		this.menuHandler.state = false;
		if(this.menuHandler.firstChild && (this.menuHandler.firstChild.tagName == "TEXTAREA" || this.menuHandler.firstChild.tagName == "INPUT")) {
		} else {
			/*if(!this.clickActive && !noimage && !(is_mac && is_ie)) {
				var img = document.createElement('img');
				img.src = 'images/common/jsmenu.gif';
				img.border = 0;
				img.title = '';
				img.alt = '';
				this.controlobj.appendChild(img);
			}
			if(!noimage) {
				this.controlobj.style.cursor = is_ie ? 'hand' : 'pointer';
			}*/
			this.menuHandler.unselectable = true;
			if(this.clickActive) {
				this.menuHandler.onclick = System.component.EditorMenu.Popup_Events.menuHandler_OnClick;
				this.menuHandler.onmouseover =  System.component.EditorMenu.Popup_Events.menuHandler_OnMouseover;
			} else {
				this.menuHandler.onmouseover =  System.component.EditorMenu.Popup_Events.menuHandler_Show;
			}
		}
	},
	init_Menu_Contents:function() {
		var lis =  dh.DomQuery.select("#" + dh.id(this.menuEl) +" a[name=popupItem]");
		for(var i = 0; i < lis.length; i++) {
				/*if(System.UserAgent.ie && !System.UserAgent.mac) {
					tds[i].style.filter += "progid:DXImageTransform.Microsoft.Alpha(opacity=85,finishOpacity=100,style=0)";
				}
				tds[i].style.opacity = 0.85;*/
				if(lis[i].title && lis[i].title == "nohighlight") {
					lis[i].title = '';
				} else {
					lis[i].controlKey = this.controlKey;
					/*if(tds[i].className != "dizEditorEditorColornormal") {
						tds[i].onmouseover =  System.component.EditorMenu.Popup_Events.menuOption_OnMouseover;
						tds[i].onmouseout =  System.component.EditorMenu.Popup_Events.menuOption_OnMouseout;
					}*/
					if(typeof lis[i].onclick == "function") {
						lis[i].ofunc = lis[i].onclick;
						lis[i].onclick =  System.component.EditorMenu.Popup_Events.menuOption_Onclick_Function;
					} else {
						lis[i].onclick = System.component.EditorMenu.Popup_Events.menuOption_Onclick_Function;// System.component.EditorMenu.Popup_Events.menuOption_OnClick_Link;
					}
					//lis[i].onfocus=function(){this.blur();}
					/*if(!is_saf && !is_kon)	{
						try {
							links = findtags(tds[i], 'a');
							for(var j = 0; j < links.length; j++) {
								if(typeof links[j].onclick  == 'undefined') links[j].onclick = ebygum;
							}
						}
						catch(e) {}
					}*/
			}
		}
	},
	show:function(obj, instant) {
		if(!System.component.EditorMenu.Popup_Handler.checkActive()){return false;}
		else if(!this.menuEl)	{this.init_Menu();}
		if(!this.menuEl) {return false;}
		if(System.component.EditorMenu.Popup_Handler.getActiveMenu() != null) {System.component.EditorMenu.Popup_Handler.getActiveMenu().hide();}
		System.component.EditorMenu.Popup_Handler.setActiveMenu(this.controlKey);
		this.menuEl.style.display ="";
		if(this.slide_open) {this.menuEl.style.clip = "rect(auto, auto, auto, auto)";}
		/*var xy=System.fly(obj.parentNode).getXY();
		
		this.pos = this.fetch_offset(obj);
		this.leftpx = this.pos['left'];
		/*this.toppx = this.pos['top'] + obj.offsetHeight;
		if((this.leftpx + this.menuEl.offsetWidth) >= document.body.clientWidth && (this.leftpx + obj.offsetWidth - this.menuEl.offsetWidth) > 0) {
			this.leftpx = this.leftpx + obj.offsetWidth - this.menuobj.offsetWidth;
			this.direction = 'right';
		} else {this.direction = 'left';}
		this.menuEl.style.left = this.leftpx + 'px';
		this.menuEl.style.top  = xy[1]+ 'px';*/
		if(!instant && this.slide_open) {
			this.intervalX = Math.ceil(this.menuEl.offsetWidth / this.open_steps);
			this.intervalY = Math.ceil(this.menuEl.offsetHeight / this.open_steps);
			this.slide((this.direction == 'left' ? 0 : this.menuEl.offsetWidth), 0, 0);
		}else if(this.menuEl.style.clip && this.slide_open) {
			this.menuEl.style.clip = "rect(auto, auto, auto, auto)";
		}
		this.handle_overlaps(true);
		if(this.menuEl.scrollHeight > 400) {
			this.menuEl.style.height = "400px";
			if(System.UserAgent.ie || System.UserAgent.opera) {
				this.menuEl.style.width = this.menuEl.scrollWidth + 18;
			}
			if(System.UserAgent.opera){
				this.menuEl.style.overflow = "scroll";
			} else {
				this.menuEl.style.overflowY = "scroll";
			}
		}
	},
	hide:function(e) {
		if(e && e.button && e.button != 0) {return true;}
		this.stop_slide();
		this.menuEl.style.display = "none";
		this.handle_overlaps(false);
		System.component.EditorMenu.Popup_Handler.setActiveMenu(null);
	},
	slidehide:function() {
		System.component.EditorMenu.Popup_Handler.getActiveMenu().hide()
	},
    hover:function(obj, clickActive) {
		if(System.component.EditorMenu.Popup_Handler.getActiveMenu()!= null) {
			if(System.component.EditorMenu.Popup_Handler.getActiveMenu().controlKey != this.controlKey) {this.show(obj, true, clickActive);}
		}
	},
	choose:function(e, obj) {
		var links = System.fly(obj).select("a");
		if(links[0]){
			if(System.UserAgent.ie) {
				links[0].click();
				window.event.cancelBubble = true;
			} else {
				if(e.shiftKey) {
					window.open(links[0].href);
					e.stopPropagation();
					e.preventDefault();
				} else {
					window.location = links[0].href;
					e.stopPropagation();
					e.preventDefault();
				}
			}
			this.hide();
		}links=null;
	},
    slide:function(clipX, clipY, opacity) {
		if(this.direction == "left" && (clipX < this.menuEl.offsetWidth || clipY < this.menuEl.offsetHeight)) {
			if(this.open_fade && System.UserAgent.ie) {
				opacity += 10;
				this.menuEl.filters.item("DXImageTransform.Microsoft.alpha").opacity = opacity;
			}
			clipX += this.intervalX;
			clipY += this.intervalY;
			this.menuEl.style.clip = "rect(auto, " + clipX + "px, " + clipY + "px, auto)";
			this.slidetimer = setTimeout("System.component.EditorMenu.Popup_Handler.getActiveMenu().slide(" + clipX + ", " + clipY + ", " + opacity + ");", 0);
		} else if(this.direction == "right" && (clipX > 0 || clipY < this.menuEl.offsetHeight)) {
			if(this.open_fade && System.UserAgent.ie) {
				opacity += 10;
				this.menuEl.filters.item("DXImageTransform.Microsoft.alpha").opacity = opacity;
			}
			clipX -= this.intervalX;
			clipY += this.intervalY;
			this.menuEl.style.clip = "rect(auto, " + this.menuEl.offsetWidth + "px, " + clipY + "px, " + clipX + "px)";
			this.slidetimer = setTimeout("System.component.EditorMenu.Popup_Handler.getActiveMenu().slide(" + clipX + ", " + clipY + ", " + opacity + ");", 0);
		} else {this.stop_slide();}
	},
	stop_slide:function() {
		clearTimeout(this.slidetimer);
		this.menuEl.style.clip = "rect(auto, auto, auto, auto)";
		if(this.open_fade && System.UserAgent.ie) {this.menuEl.filters.item("DXImageTransform.Microsoft.alpha").opacity = 100;}
	},
	fetch_offset:function(obj) {
		var left_offset = obj.offsetLeft;
		var top_offset = obj.offsetTop;
		while ((obj = obj.offsetParent) != null) {
			left_offset += obj.offsetLeft;
			top_offset += obj.offsetTop;
		}
		return { "left" : left_offset, "top" : top_offset };
	},
	overlaps:function(obj, m) {
		var s = new Array();
		var pos = this.fetch_offset(obj);
		s['L'] = pos['left'];
		s['T'] = pos['top'];
		s['R'] = s['L'] + obj.offsetWidth;
		s['B'] = s['T'] + obj.offsetHeight;
		if(s['L'] > m['R'] || s['R'] < m['L'] || s['T'] > m['B'] || s['B'] < m['T']) {return false;}
		return true;
	},
	handle_overlaps:function(dohide) {
		if(System.UserAgent.ie) {return;
			var selects = System.fly(document.body).select("select");
			if(dohide){
				var menuarea = new Array(); menuarea = {
					'L' : this.leftpx,
					'R' : this.leftpx + this.menuEl.offsetWidth,
					'T' : this.toppx,
					'B' : this.toppx + this.menuEl.offsetHeight
				};
				for(var i = 0; i < selects.length; i++) {
					if(this.overlaps(selects[i], menuarea)) {
						var hide = true;
						var s = selects[i];
						while (s = s.parentNode) {
							if(s.className == 'popupmenu_popup') {
								hide = false;
								break;
							}
						}
						if(hide) {
							selects[i].style.visibility = "hidden";
							arraypush(popupmenu.hidden_selects, i);
						}
					}
				}
			} else {
				while (true) {
					var i = arraypop(popupmenu.hidden_selects);
					if(typeof i == 'undefined' || i == null) break;
					else selects[i].style.visibility = 'visible';
				}
			}
		}
	}   
});
    
}

if(typeof(System.component.EditorEvents)=="undefined"){
    System.component.EditorEvents=(function(){
        var eh=System.component.EditorHelper;
        return {
            ctlent:function(e) {},
            storeCaret:function(textEl){
                if(textEl &&textEl.createTextRange){
	                textEl.caretPos = document.selection.createRange().duplicate();
                }
            },
            buttonContext:function(e, state) {
	            if(state == "mouseover") {
		            var mode = this.state ? "down" : "hover";
		            var cls= this.state ?"c_editorFctOn":"";
		            if(this.mode != mode) {
			            this.mode = mode;
			            this.parentNode.className = cls;
		            }
	            } else {
		            var mode = this.state ? "selected" : "normal";
		            if(this.mode != mode) {
			            this.mode = mode;
			            this.parentNode.className = (mode == 'selected') ? "c_editorFctOn" : "";
		            }
	            }
            },
            menuContext:function(e, state) {return;
	            this.style.cursor = System.UserAgent.ie ? "hand" : "pointer";
	            var mode = (state == "mouseover") ? 'hover' : 'normal';
	            var cls=(state == 'mouseover')?'dizEditorEditorButtonhover':'dizEditorButtonNormal';
	            this.className = cls;
	            var td=System.fly(this).select("td[name=menu]");
	            if(td){td.className="dizEditorEditorMenu" + mode;}
	            td=System.fly(this).select("td[name=colorMenu]");
	            if(td){td.className="dizEditorEditorColorMenu" + mode;}
            },
            colorContext:function(e, state) {
	            this.style.cursor = System.UserAgent.ie ? "hand" : "pointer";
	            var mode = (state == "mouseover") ? "hover" : "normal";
	            var cls=(state=="mouseover")?"dizEditorEditorColorhover":"dizEditorEditorColornormal";
	            this.className = cls;
            }        
        };
    })();
}

if(typeof(System.component.Editor)=="undefined"){
    System.component.Editor=function(objID,domID){
        this.objID=objID;
        this.domID=domID;

        this.el=System.get(domID);
        if(this.el==null){alert("\u4E0D\u5B58\u5728 Dom ID \u4E3A" + domID + "\u7684\u5143\u7D20\uFF01")} 
        
        //is ubb mode
        this.bbInsert = parseInt('1');
        this.hiddenValue=this.el.child("input[name=" + domID.replace(/_container/g,"") + "]");
        this.contentArea=this.el.child("textarea[name=editorContentArea]"); 
        this.contentArea.enableDisplayMode(""); 
        this.contentIframe=dh.insertBefore(this.contentArea,{tag:"iframe",id:this.domID +"_Iframe",name: this.domID +"_Iframe"},true); 
        this.contentIframe.enableDisplayMode(""); 

        var editorSwitcher=this.el.child("div[name=editorSwitcher]");
        if(editorSwitcher && !allowswitcheditor){editorSwitcher.dom.style.display="none";}editorSwitcher=null;
        
        this._constructor();
        System.component.Editor.superclass.constructor.call(this);
    }
    System.extend(System.component.Editor, System.Event.Observable, {
        _constructor:function(){
            this.cursor=-0;
            this.stack=[];
            this.textObj=this.contentArea.dom;
            
            this.boundEditorEvents();
            this.switchEditor(1,true);           
        },
        setEditorObjectID:function(){
            //currentEditorObjectID=this.objID;
            System.component.EditorManager.setActive(this.objID);
        },
        moveCursor:function(increment) {
	        var test = this.cursor + increment;
	        if(test >= 0 && this.stack[test] != null && !typeof(this.stack[test])=="undefined") {
		        this.cursor += increment;
	        }
        },
        addSnapshot:function(str) {
	        if(this.stack[this.cursor] == str) {
		        return;
	        } else {
		        this.cursor++;
		        this.stack[this.cursor] = str;

		        if(!typeof(this.stack[this.cursor + 1])=="undefined") {
			        this.stack[this.cursor + 1] = null;
		        }
	        }
        },
        getSnapshot:function() {
	        if(!typeof(this.stack[this.cursor])=="undefined" && this.stack[this.cursor] != null) {
		        return this.stack[this.cursor];
	        } else {
		        return false;
	        }
        },
        boundEditorEvents:function(){
            this.buttons=this.el.select("a[name=editorButton]");
            var me=this;
            if(this.buttons && this.buttons.length){
                for(var i=0;i<this.buttons.length;i++){
                    /*evm.on(this.buttons[i],"mouseover",function(e){
                       me.setEditorObjectID();
                       System.component.EditorEvents.buttonContext.apply(this,[e,"mouseover"]);
                    });
                    evm.on(this.buttons[i],"mouseout",function(e){
                        me.setEditorObjectID();
                        System.component.EditorEvents.buttonContext.apply(this,[e,"mouseout"]);
                    });*/
                    evm.on(this.buttons[i],"click",function(e){
                        me.setEditorObjectID();
                        var cmd=this.getAttribute("cmd");
                        if(typeof(cmd)=="string" && cmd!=""){
                            cmd=cmd.split(",");
                            me.excuteCommand.apply(me,cmd);
                        }  
                    });
                    //evm.on(this.buttons[i],"focus",function(e){ this.blur(); });
                }
            }
            this.menus=this.el.select("a[name=editorPopup]");
            if(this.menus && this.menus.length){
                for(var i=0;i<this.menus.length;i++){
                    /*evm.on(this.menus[i],"mouseover",function(e){
                        me.setEditorObjectID();
                        System.component.EditorEvents.menuContext.apply(this,[e,"mouseover"]);
                    });
                    evm.on(this.menus[i],"mouseout",function(e){
                        me.setEditorObjectID();
                        System.component.EditorEvents.menuContext.apply(this,[e,"mouseout"]);
                    });*/
                    //evm.on(this.menus[i],"focus",function(e){this.blur();});
                    System.component.EditorMenu.Popup_Handler.register(this.menus[i],true,this.domID);
                }
            }
            this.popupItems=this.el.select("a[name=popupItem]");
            if(this.popupItems && this.popupItems.length){
               var icmd="";
               for(var i=0;i<this.popupItems.length;i++){
                   //colorContext(this, 'mouseover') || colorContext(this, 'mouseout')
                    icmd=this.popupItems[i].getAttribute("cmd");
                     if(typeof(icmd)=="string" && icmd!=""){
                        icmd=icmd.split(",")[0];
                        if(icmd=="forecolor"){
                            evm.on(this.popupItems[i],"mouseover",function(e){
                               me.setEditorObjectID();
                               System.component.EditorEvents.colorContext.apply(this,[e,"mouseover"]);
                            });
                            evm.on(this.popupItems[i],"mouseout",function(e){
                                me.setEditorObjectID();
                                System.component.EditorEvents.colorContext.apply(this,[e,"mouseout"]);
                            });
                        }
                    }  
                    evm.on(this.popupItems[i],"click",function(e){
                         me.setEditorObjectID();
                        var cmd=this.getAttribute("cmd");
                        if(typeof(cmd)=="string" && cmd!=""){
                            cmd=cmd.split(",");
                            me.excuteCommand.apply(me,cmd);
                        }  
                    });
               }
            }popupItems=null;
            
            /*this.bbCodeMode=this.el.child("a[name=bbCodeMode]");
            if(this.bbCodeMode){
                this.bbCodeMode.on("click",function(){
                    me.switchEditor.apply(me,[0]);
                });
                this.bbCodeMode.on("focus",function(){this.blur();});
                this.bbCodeMode.enableDisplayMode("block");
            }
            this.bbDesignMode=this.el.child("a[name=bbDesignMode]");
            if(this.bbDesignMode){
               this.bbDesignMode.on("click",function(){
                    me.switchEditor.apply(me,[1]);
               });
               this.bbDesignMode.on("focus",function(){this.blur();}); 
            } */      
            
            /*var bbContract=this.el.child("img[name=bbContract]");
            if(bbContract){
                bbContract.on("click",function(){
                    me.resizeEditor.apply(me,[-100]);
               });
            }btnContract=null;        
            var bbExpand=this.el.child("img[name=bbExpand]");
            if(bbExpand){
                bbExpand.on("click",function(){
                    me.resizeEditor.apply(me,[100]);
                });
            }bbExpand=null;*/
            
            /*var btnCheckLength=this.el.child("input[name=btnCheckLength]");
            if(btnCheckLength){
                btnCheckLength.on("click",function(){me.checkLength.apply(me,[]);});
            }btnCheckLength=null;
            
            var btnClearContent=this.el.child("input[name=btnClearContent]");
            if(btnClearContent){
                btnClearContent.on("click",function(){me.clearContent.apply(me,[]);});
            }btnClearContent=null;*/
            
            this.fontOut=this.el.child("span[name=lblFontOut]");
            this.sizeOut=this.el.child("span[name=lblSizeOut]");
            this.colorBar=this.el.child("span[name=plColorBar]");
            
            this.contentArea.on("select",function(e){me.setEditorObjectID();System.component.EditorEvents.storeCaret(this.dom);});
            this.contentArea.on("click",function(e){me.setEditorObjectID();System.component.EditorEvents.storeCaret(this.dom);});
            this.contentArea.on("keyup",function(e){me.setEditorObjectID();System.component.EditorEvents.storeCaret(this.dom);});
        
        },
        initEditorContent:function(initialtext){
        	if(this.mode){
        	    this.editWin=this.contentIframe.dom.contentWindow;
        	    this.editDoc=this.contentIframe.dom.contentWindow.document;
		        this.writeEditorContents(typeof(initialtext)=="undefined" ? this.textObj.value : initialtext);
	        } else {
	            this.editWin=this.textObj;
	            this.editDoc=this.textObj;
		        if(typeof(initialtext)!="undefined") {
			        this.writeEditorContents(initialtext);
		        }
		        this.addSnapshot(this.contentArea.dom.value);
	        }
	        this.setEditorEvents();
        },
        setEditorEvents:function(){
            var me=this;
            if(this.mode){
                evm.on(this.editDoc,"mouseup",function(e) {me.setEditorObjectID();me.setContext.apply(me,[null,e]);System.component.EditorMenu.Popup_Handler.hide();});
                evm.on(this.editDoc,"keyup", function(e) {me.setEditorObjectID();me.setContext.apply(me,[null,e]);});
                evm.on(this.editDoc,"keydown", function(e) {System.component.EditorEvents.ctlent.apply(this,[e]);});
            }
            evm.on(this.editWin,"blur",function(e) {me.hiddenValue.dom.value=me.getEditorContents();this.hasfocus = false;});
            evm.on(this.editWin,"focus",function(e) {me.setEditorObjectID();this.hasfocus = true;});
        },           
        resizeEditor:function(change) {
		    var newheight = (this.mode ? parseInt(this.contentIframe.getHeight(), 10):parseInt(this.contentArea.getHeight(), 10)) + change;
		    if(newheight >= 100) {
			    if(this.moe){
			        this.contentIframe.setHeight( newheight + "px");
			    }else{
			        this.contentArea.setHeight( newheight + "px");
			    }
		    }
	    },
	    switchEditor:function(mode,isInit) {
	        this.setEditorObjectID();

	        if(!allowswitcheditor)return;
    	    
	        mode = parseInt(mode);
	        if(mode==this.mode){
	            this.checkFocus();
	            return;
	        } 	        
	        if(!mode){
	            if(this.buttons && this.buttons.length){
	                for(var i=0;i<this.buttons.length;i++){
	                    this.buttons[i].state=false;
	                    this.buttons[i].mode="normal";
	                    //this.buttons[i].className="dizEditorButtonNormal";
	                }
	            }
	            if(this.menus && this.menus.length){
	                for(var i=0;i<this.menus.length;i++){
	                    this.menus[i].state=false;
	                }
	            }
	            this.contentArea.setHeight(this.contentIframe.getHeight());
	            //this.contentArea.setWidth(this.contentIframe.getWidth());
	            this.contentIframe.setVisible(false);
	            this.contentArea.setVisible(true);	            
		    }else{
	            this.contentIframe.setHeight(this.contentArea.getHeight());
	            //this.contentIframe.setWidth(this.contentArea.getWidth());
		        this.contentArea.setVisible(false);
		        this.contentIframe.setVisible(true);		    
		    }   
		    
		    var tableButton=this.el.child("div[cmd=table]");
		    if(tableButton){tableButton.enableDisplayMode("");tableButton.setVisible(mode?true:false);}tableButton=null;
		    
	        this.cursor = -1;
	        this.stack = [];
	        
	        if(this.fontOut){
	            this.fontOut.update(System.component.EditorResources.lang["fontname"]);
	            this.fontOut.dom.fontstate=null;
	        }
	        
	        if(this.sizeOut){
	            this.sizeOut.update(System.component.EditorResources.lang["fontsize"]);
	            this.sizeOut.dom.sizestate=null;
	        }
	        if(this.colorBar){this.colorBar.setStyle("background-color","#000000");}
	        
           //this.bbCodeMode.dom.className = mode ? "dizEditorSwitcher" : "dizEditorSwitcherHighLight";
	       //this.bbDesignMode.dom.className = mode ? "dizEditorSwitcherHighLight" : "dizEditorSwitcher";
	       //this.bbDesignMode.addClass("dizEditorSwitcherHighLight");
	     	
	     	var parsedtext = this.getEditorContents(this.mode);
	     	if(isInit==true){this.hiddenValue.dom.value=parsedtext;}else{
	     		parsedtext = mode ? System.component.EditorBBCode.bbcode2html(parsedtext) : System.component.EditorBBCode.html2bbcode(parsedtext);
	     	}
            
	     	this.mode = mode;
	        this.initEditorContent(parsedtext);
            
	        //this.checkFocus(); //onpageload focus editor
        },
        writeEditorContents:function(text) {
	        if(this.mode) {
		        if(text == "" && System.UserAgent.gecko) {
			        text = "<br />";
		        }
		        var editdoc=this.contentIframe.dom.contentWindow.document;
		        if(this.editDoc && this.editDoc.initialized) {
			        this.editDoc.body.innerHTML = text;
		        } else {
			        this.editDoc.designMode = 'on';
			        this.editDoc = this.editWin.document;
			        this.editDoc.open('text/html', 'replace');
			        this.editDoc.write(text);
			        this.editDoc.close();
			        this.editDoc.body.contentEditable = true;
			        this.editDoc.initialized = true;
		        }
	        } else {
		        this.textObj.value = text;
	        }

	        this.setEditorStyle();
        },
        setEditorStyle:function() {
	        if(this.mode) {
		        if(System.UserAgent.gecko || System.UserAgent.opera) {
			        for(var ss = 0; ss < document.styleSheets.length; ss++) {
				        if(document.styleSheets[ss].cssRules.length <= 0) {
					        continue;
				        }
				        for(var i = 0; i < document.styleSheets[ss].cssRules.length; i++) {
					        if(document.styleSheets[ss].cssRules[i].selectorText == ".dizEditor_contentBody") {
						        var newss = this.editDoc.createElement("style");
						        newss.type = "text/css";
						        newss.innerHTML = document.styleSheets[ss].cssRules[i].cssText + " p { margin: 0px; }";
						        this.editDoc.documentElement.childNodes[0].appendChild(newss);
						        this.editDoc.body.style.fontSize = document.styleSheets[ss].cssRules[i].style.fontSize;
						        this.editDoc.body.style.fontFamily = document.styleSheets[ss].cssRules[i].style.fontFamily;
					        }
				        }
			        }
			        this.contentIframe.setStyle("border","0px");
		        }else if(System.UserAgent.ie) {
			        if(document.styleSheets["css"]) {
				        try{this.editDoc.createStyleSheet().cssText = document.styleSheets["css"].cssText + " p { margin: 0px; }";}catch(ex){}
				        //this.editDoc.body.className = "wysiwyg";
			        }
			        this.editDoc.body.style.border = "0px";
		        }
		        this.editDoc.body.style.background = "";
		        this.editDoc.body.style.backgroundColor = "#FFFFFF";
	        }
        },          
        setContext:function(cmd,e) {
            var contextcontrols = new Array("bold", "italic", "underline", "justifyleft", "justifycenter", "justifyright", "insertorderedlist","insertunorderedlist");
            var myCmd="";
            for(var i=0;i<contextcontrols.length;i++) {
                var obj = this.el.child("a[cmd=" + contextcontrols[i] + "]");
                if(obj != null) {
                    myCmd=obj.dom.getAttribute("cmd");
                    if(typeof(myCmd)=="string" && myCmd!="" ){ myCmd=myCmd.split(",")[0];}
	                try {
		                var state = this.editDoc.queryCommandState(contextcontrols[i]);
	                } catch(e) {
		                var state = false;
	                }
	                if(typeof(obj.dom.state)=="undefined") {
		                obj.dom.state = false;
	                }
	                if(obj.dom.state != state) {
		                obj.dom.state = state;
		                System.component.EditorEvents.buttonContext.apply(obj.dom,[e,myCmd==cmd?"mouseover":"mouseout"]);
	                }
                }
            }

            var fs = this.editDoc.queryCommandValue("fontname");
            if(fs == "" && !System.UserAgent.ie && window.getComputedStyle) {
                fs = this.editDoc.body.style.fontFamily;
            } else if(fs == null) {
                fs = "";
            }
            
            if(this.fontOut && fs != this.fontOut.dom.fontstate) {
                thingy = fs.indexOf(",") > 0 ? fs.substr(0, fs.indexOf(",")) : fs;
                this.fontOut.update(thingy);
                this.fontOut.dom.fontstate = fs;
            }

            var ss = this.editDoc.queryCommandValue('fontsize');
            if(ss == null || ss == "") {
                ss = System.component.EditorHelper.formatFontsize(this.editDoc.body.style.fontSize);
            }
            if(this.sizeOut && ss != this.sizeOut.dom.sizestate) {
                if(this.sizeOut.dom.sizestate == null) {
	                this.sizeOut.dom.sizestate = "";
                }
                this.sizeOut.update(ss);
                this.sizeOut.dom.sizestate = ss;
            }

            //var cs = this.editDoc.queryCommandValue("forecolor");
            //this.colorBar.setStyle("background-color",System.component.EditorHelper.rgbToColor(cs));
        },                     
        checkFocus:function(){
            var obj = (typeof(this.mode) == "undefined" || !this.mode) ? this.textObj : this.editWin;
	        if(!obj.hasfocus) {
		       obj.focus();
	        }
        },
        getEditorContents:function() {
	        return this.mode ? this.editDoc.body.innerHTML : this.textObj.value;
        },
        checkLength:function(){
            var message = this.bbInsert && this.mode ? System.component.EditorBBCode.html2bbcode(this.getEditorContents()) : this.textObj.value;
	        var showmessage = postmaxchars != 0 ? '\u7CFB\u7EDF\u9650\u5236\u003A ' + postminchars + ' \u5230 ' + postmaxchars + ' \u5B57\u8282' : '';
	        alert('\n\u5F53\u524D\u957F\u5EA6: ' + message.length + ' \u5B57\u8282\n\n' + showmessage);
        },
        clearContent:function() {
			if(this.mode && this.bbInsert) {
				this.editDoc.body.innerHTML = System.UserAgent.gecko ? "<br />" :"";
				this.hiddenValue.dom.value="";
			}else {
				this.textObj.value = "";
			}
		},
        showPrompt:function(dialogtxt, defaultval) {
	        return trim(prompt(dialogtxt, defaultval) + '');
        },
        verifyPrompt:function(str) {
	        if(in_array(str, ['http://', 'null', 'undefined', 'false', '']) || str == null || str == false) {
		        return false;
	        } else {
		        return str;
	        }
        },
        promptLink:function(tagname, phrase, iprompt) {
	        var value = this.showPrompt(phrase, iprompt);
	        if((value = this.verifyPrompt(value)) !== false) {
		        if(this.getSel()) {
			        this.applyFormat('unlink');
			        this.wrapTags(tagname, value);
		        } else {
			        this.wrapTags(tagname, value, value);
		        }
	        }
	        return true;
        },		
        getSel:function(){         
	        if(this.mode) {
		        if(System.UserAgent.gecko || System.UserAgent.opera) {
			        selection = this.editWin.getSelection();
			        this.checkFocus();
			        range = selection ? selection.getRangeAt(0) : this.editDoc.createRange();
			        return readNodes(range.cloneContents(), false);
		        } else {
			        var range = this.editDoc.selection.createRange();
			        if(range.htmlText && range.text) {
				        return range.htmlText;
			        } else {
				        var htmltext = "";
				        for(var i = 0; i < range.length; i++) {
					        htmltext += range.item(i).outerHTML;
				        }
				        return htmltext;
			        }
		        }
	        } else {
		        if(typeof(this.editDoc.selectionStart)!="undefined") {
			        return this.editDoc.value.substr(this.editDoc.selectionStart, this.editDoc.selectionEnd - this.editDoc.selectionStart);
		        } else if(document.selection && document.selection.createRange) {
			        return document.selection.createRange().text;
		        } else if(window.getSelection) {
			        return window.getSelection() + "";
		        } else {
			        return false;
		        }
	        }
        },
        add_Range:function(node) {
	        this.checkFocus();
	        var sel = this.editWin.getSelection();
	        var range = this.editDoc.createRange();
	        range.selectNodeContents(node);
	        sel.removeAllRanges();
	        sel.addRange(range);
        },
        insertNodeAtSelection:function(text) {
	        this.checkFocus();

	        var sel = this.editWin.getSelection();
	        var range = sel ? sel.getRangeAt(0) : this.editDoc.createRange();
	        sel.removeAllRanges();
	        range.deleteContents();

	        var node = range.startContainer;
	        var pos = range.startOffset;

	        switch(node.nodeType) {
		        case Node.ELEMENT_NODE:
			        if(text.nodeType == Node.DOCUMENT_FRAGMENT_NODE) {
				        selNode = text.firstChild;
			        } else {
				        selNode = text;
			        }
			        node.insertBefore(text, node.childNodes[pos]);
			        this.add_Range(selNode);
			        break;

		        case Node.TEXT_NODE:
			        if(text.nodeType == Node.TEXT_NODE) {
				        var text_length = pos + text.length;
				        node.insertData(pos, text.data);
				        range = this.editDoc.createRange();
				        range.setEnd(node, text_length);
				        range.setStart(node, text_length);
				        sel.addRange(range);
			        } else {
				        node = node.splitText(pos);
				        var selNode;
				        if(text.nodeType == Node.DOCUMENT_FRAGMENT_NODE) {
					        selNode = text.firstChild;
				        } else {
					        selNode = text;
				        }
				        node.parentNode.insertBefore(text, node);
				        this.add_Range(selNode);
			        }
			        break;
	        }
        },        
        insertText:function(text, movestart, moveend) {
	        if(this.mode) {
		        if(System.UserAgent.gecko || System.UserAgent.opera) {
			        var fragment = this.editDoc.createDocumentFragment();
			        var holder = this.editDoc.createElement("span");
			        holder.innerHTML = text;

			        while(holder.firstChild) {
				        fragment.appendChild(holder.firstChild);
			        }
			        this.insertNodeAtSelection(fragment);
		        } else {
			        this.checkFocus();
			        if(typeof(this.editDoc.selection)!="undefined" && this.editDoc.selection.type != "Text" && this.editDoc.selection.type != "None") {
				        movestart = false;
				        this.editDoc.selection.clear();
			        }

			        var sel = this.editDoc.selection.createRange();
			        sel.pasteHTML(text);

			        if(text.indexOf('\n') == -1) {
				        if(typeof(movestart)!="undefined") {
					        sel.moveStart("character", -mb_strlen(text) +movestart);
					        sel.moveEnd("character", -moveend);
				        } else if(movestart != false) {
					        sel.moveStart("character", -mb_strlen(text));
				        }
			        }
		        }
	        } else {

		        this.checkFocus();
		        if(typeof(this.editDoc.selectionStart)!="undefined") {

			        var opn = this.editDoc.selectionStart + 0;

			        this.editDoc.value = this.editDoc.value.substr(0, this.editDoc.selectionStart) + text + this.editDoc.value.substr(this.editDoc.selectionEnd);

			        if(typeof(movestart)!="undefined") {
				        this.editDoc.selectionStart = opn + movestart;
				        this.editDoc.selectionEnd = opn + mb_strlen(text) - moveend;
			        } else if(movestart !== false) {
				        this.editDoc.selectionStart = opn;
				        this.editDoc.selectionEnd = opn + mb_strlen(text);
			        }
		        } else if(document.selection && document.selection.createRange) {

			        var sel = document.selection.createRange();
			        sel.text = text.replace(/\r?\n/g, '\r\n');

			        if(typeof(movestart)!="undefined") {
				        sel.moveStart("character", -mb_strlen(text) +movestart);
				        sel.moveEnd("character", -moveend);
			        } else if(movestart !== false) {
				        sel.moveStart("character", -mb_strlen(text));
			        }
			        sel.select();
		        } else {
			        this.editDoc.value += text;
		        }
	        }
        },		
		wrapTags:function(tagname, useoption, selection) {
	        if(tagname=="code") {
		        this.applyFormat("removeformat");
	        }

	        if(typeof(selection)=="undefined") {
		        var selection = this.getSel();
		        if(selection === false) {
			        selection = "";
		        } else {
			        selection += "";
		        }
	        }

	        if(useoption === true) {
		        var option = this.showPrompt(construct_phrase(System.component.EditorResources.lang["enter_tag_option"], ("[" + tagname + "]")), "");
		        if(option = this.verifyPrompt(option)) {
			        var opentag = '[' + tagname + '=' + option + ']';
		        } else {
			        return false;
		        }
	        } else if(useoption !== false) {
		        var opentag = '[' + tagname + '=' + useoption + ']';
	        } else {
		        var opentag = '[' + tagname + ']';
	        }

	        var closetag = '[/' + tagname + ']';
	        var text = opentag + selection + closetag;
	        this.insertText(text, mb_strlen(opentag), mb_strlen(closetag));

	        return false;
        },
        applyFormat:function(cmd, dialog, argument) {
            if(typeof(argument)=="string"){argument=argument.trim();}
	        if(this.mode) {
		        var t=this.editDoc.execCommand(cmd, (typeof(dialog)=="undefined" ? false : dialog), (typeof(argument)=="undefined" ? true : argument));
		        return false;
	        }
	        switch(cmd) {
		        case 'bold':
		        case 'italic':
		        case 'underline':
			        this.wrapTags(cmd.substr(0, 1), false);
			        break;
		        case 'justifyleft':
		        case 'justifycenter':
		        case 'justifyright':
			        this.wrapTags('align', cmd.substr(7));
			        break;
		        case 'indent':
			        this.wrapTags(cmd, false);
			        break;
		        case 'fontname':
			        this.wrapTags('font', argument);
			        break;
		        case 'fontsize':
			        this.wrapTags('size', argument);
			        break;
		        case 'forecolor':
			        this.wrapTags('color', argument);
			        break;
		        case 'createlink':
			        var sel = this.getSel();
			        if(sel) {
				        this.wrapTags('url', argument);
			        } else {
				        this.wrapTags('url', argument, argument);
			        }
			        break;
		        case 'insertimage':
			        this.wrapTags('img', false, argument);
			        break;
	        }
        },
        customTags:function(tagname, params) {
	        this.applyFormat("removeformat");

	        if(System.component.EditorResources.custombbcodes[tagname].indexOf(']') == -1) {
		        System.component.EditorResources.custombbcodes[tagname] = '[' + tagname + '][/' + tagname + ']';
	        }

	        if(params == 1) {
		        var selection = this.getSel();
		        if(selection === false) {
			        selection = '';
		        } else {
			        selection += '';
		        }

		        var opentag = '[' + tagname + ']';
		        var closetag = '[/' + tagname + ']';
		        var text = opentag + selection + closetag;
		        selection == "" ? this.insertText(System.component.EditorResources.custombbcodes[tagname], mb_strlen('[' + tagname + ']'), mb_strlen('[/' + tagname + ']')) : this.insertText(text, mb_strlen(opentag), mb_strlen(closetag));
	        } else {
		        this.insertText(System.component.EditorResources.custombbcodes[tagname], System.component.EditorResources.custombbcodes[tagname].indexOf(']') + 1, mb_strlen('[/' + tagname + ']'));
	        }

	        return false;
        },
        excuteCommand:function (cmd, arg) {
            if(cmd!= "redo") {
	            this.addSnapshot(this.getEditorContents());
            }

            this.checkFocus();
	
            if(in_array(cmd, ["quote", "code"])) {
	            var ret = this.wrapTags(cmd, false);
            } else if(cmd.substr(0, 6) == "custom") {
	            var ret = this.customTags(cmd.substr(8), cmd.substr(6, 1));
            } else if(!this.mode && cmd == "removeformat") {
	            var simplestrip = new Array("b", "i", "u");
	            var complexstrip = new Array("font", "color","size");

	            var str = this.getSel();
	            if(str === false) {
		            return;
	            }
	            for(var tag=0;tag<simplestrip.length;tag++) {
		            str = System.component.EditorHelper.stripSimple(simplestrip[tag], str);
	            }
	            for(var tag=0;tag<complexstrip.length;tag++) {
		            str = System.component.EditorHelper.stripComplex(complexstrip[tag], str);
	            }
	            this.insertText(str);
            } else if(!this.mode && cmd == "undo") {
	            this.addSnapshot(this.getEditorContents());
	            this.moveCursor(-1);
	            if((str = this.getSnapshot()) !== false) {
		            this.editDoc.value = str;
	            }
            } else if(!this.mode && cmd == "redo") {
	            this.moveCursor(1);
	            if((str = this.getSnapshot()) !== false) {
		            this.editDoc.value = str;
	            }
            } else if(!this.mode && in_array(cmd, ["insertorderedlist", "insertunorderedlist"])) {
	            var listtype = cmd == "insertorderedlist" ? "1" : "";
	            var opentag = '[list' + (listtype ? ('=' + listtype) : '') + ']\n';
	            var closetag = '[/list]';

	            if(txt = this.getSel()) {
		            var regex = new RegExp('([\r\n]+|^[\r\n]*)(?!\\[\\*\\]|\\[\\/?list)(?=[^\r\n])', 'gi');
		            txt = opentag + trim(txt).replace(regex, '$1[*]') + '\n' + closetag;
		            this.insertText(txt, mb_strlen(txt), 0);
	            } else {
		            this.insertText(opentag + closetag, opentag.length, closetag.length);

		            while(listvalue = prompt(System.component.EditorResources.lang['enter_list_item'], '')) {
			            if(System.UserAgent.opera) {
				            listvalue = '\n' + '[*]' + listvalue;
				            this.insertText(listvalue, mb_strlen(listvalue) + 1, 0);
			            } else {
				            listvalue = '[*]' + listvalue + '\n';
				            this.insertText(listvalue, mb_strlen(listvalue), 0);
			            }
		            }
	            }
            } else if(!this.mode && cmd == "outdent") {
	            var sel = this.getSel();
	            sel = System.component.EditorHelper.stripSimple('indent', sel, 1);
	            this.insertText(sel);
            } else if(cmd == 'createlink') {
	            if(this.mode) {
		            if(System.UserAgent.gecko || System.UserAgent.opera) {
			            var url = this.showPrompt(System.component.EditorResources.lang["enter_link_url"], 'http://');
			            if((url = this.verifyPrompt(url)) !== false) {
				            if(this.getSel()) {
					            this.applyFormat("unlink");
					            this.applyFormat("createlink", System.UserAgent.ie, (typeof(url)=="undefined" ? true : url));
				            } else {
					            this.insertText('<a href="' + url + '">' + url + '</a>');
				            }
			            }
		            } else {
			            this.applyFormat("createlink", System.UserAgent.ie, (typeof(url)=="undefined" ? true : url));
		            }
	            } else {
		            this.promptLink('url', System.component.EditorResources.lang['enter_link_url'], 'http://');
	            }
            } else if(!this.mode && cmd == "unlink") {
	            var sel = this.getSel();
	            sel = System.component.EditorHelper.stripSimple("url", sel);
	            sel = System.component.EditorHelper.stripComplex("url", sel);
	            this.insertText(sel);
            } else if(cmd == "email") {
	            if(this.mode) {
		            var email = this.showPrompt(System.component.EditorResources.lang["enter_email_link"],"");
		            email = this.verifyPrompt(email);

		            if(email === false) {
			            this.applyFormat("unlink");
		            } else {
			            var selection = this.getSel();
			            this.insertText('<a href="mailto:' + email + '">' + (selection ? selection : email) + '</a>', (selection ? true : false));
		            }
	            } else {
		            this.promptLink("email", System.component.EditorResources.lang["enter_email_link"],"");
	            }
            }else if(cmd == 'insertimage') {
  				System.component.EditorDialogManager.openInsertImageDialog();
		        /*var img = this.showPrompt(System.component.EditorResources.lang["enter_image_url"], "http://");
	            if(img = this.verifyPrompt(img)) {
		            return this.applyFormat("insertimage", false, img);
	            } else {
		            return false;
	            }*/
            }else if(cmd=='uploadfile'){
            	System.component.EditorDialogManager.openUploadFileDialog();
            }else if(cmd == "table"){
	            if(this.mode) {
		            if(typeof(rows)=="undefined") {
			            var rows = this.showPrompt(System.component.EditorResources.lang["enter_table_rows"], "2");
		            }
		            if(rows != "null" && typeof(columns)=="undefined") {
			            var columns = this.showPrompt(System.component.EditorResources.lang["enter_table_columns"], "2");
		            }
		            if(typeof(columns)!="undefined" && columns != "null") {
			            rows = /^[-\+]?\d+$/.test(rows) && rows > 0 && rows <= 30 ? rows : 2;
			            columns = /^[-\+]?\d+$/.test(columns) && columns > 0 && columns <= 30 ? columns : 2;
			            var html = '<table cellspacing="1" cellpadding="4" width="50%" align="center" style="background: ' + System.component.EditorResources.BORDERCOLOR + '">';
			            for (var row = 0; row < rows; row++) {
				            html += '<tr bgcolor="' + System.component.EditorResources.ALTBG2 + '">\n';
				            for (col = 0; col < columns; col++){
					            html += '<td>&nbsp;</td>\n';
				            }
				            html+= '</tr>\n';
			            }
			            html += '</table>\n';
			            this.insertText(html);
		            }
	            }
	            return false;
            } else {
	            var ret = this.applyFormat(cmd, false, (typeof(arg)=="undefined" ? true : arg));return;
            }
			
            if(cmd != "undo") {
	            this.addSnapshot(this.getEditorContents());
            }
            if(this.mode) {
	            this.setContext(cmd);
	            if(cmd == "forecolor") {
	                this.colorBar.setStyle("background-color",arg);
	            }
            }
            this.checkFocus();
            return ret;
        }              
       
    });
}

})();
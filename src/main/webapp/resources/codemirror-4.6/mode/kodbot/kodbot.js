// CodeMirror, copyright (c) by Marijn Haverbeke and others
// Distributed under an MIT license: http://codemirror.net/LICENSE

(function(mod) {
    if (typeof exports == "object" && typeof module == "object") // CommonJS
	mod(require("../../lib/codemirror"));
    else if (typeof define == "function" && define.amd) // AMD
	define([ "../../lib/codemirror" ], mod);
    else
	// Plain browser env
	mod(CodeMirror);
})(function(CodeMirror) {
    "use strict";

    CodeMirror.defineMode("kodbot", function() {
	function words(str) {
	    var obj = {}, words = str.split(" ");
	    for (var i = 0; i < words.length; ++i)
		obj[words[i]] = true;
	    return obj;
	}
	var keywords = words("move jump left right repeat");
	var functions = {};

	var isOperatorChar = /[+-=]/;
	
	function refreshEditor(){
	    var oldPos = myCodeMirror.getCursor();
	    myCodeMirror.setValue(myCodeMirror.getValue());
	    myCodeMirror.setCursor(oldPos);
	}

	function tokenBase(stream, state) {
	    var ch = stream.next();
	    if (ch == "#" && state.startOfLine) {
		stream.skipToEnd();
		return "meta";
	    }
	    if (/[{}\(\);_]/.test(ch)) {
		return null;
	    }
	    if (/\d/.test(ch)) {
		stream.eatWhile(/[\w\.]/);
		return "number";
	    }
	    if (isOperatorChar.test(ch)) {
		return "operator";
	    }

	    if (ch == 'm' && stream.match('ain')) {
		return 'main';
	    }

	    stream.eatWhile(/[\w\$_]/);
	    var cur = stream.current();
	    if (keywords.propertyIsEnumerable(cur))
		return "keyword";
	    
	    if (cur.match('[a-zA-Z][a-zA-Z0-9_]*')) {
		var wholeText = myCodeMirror.getValue();
		if(stream.peek()=='{'){
		    if(typeof functions[cur] == 'undefined' || functions[cur]==null){
			functions[cur]=cur;
			refreshEditor();
		    }
		    return 'function';
		}
		if(typeof functions[cur] != 'undefined' && functions[cur] !=null){
		    if (wholeText.search(cur + "{") >= 0) {
			return 'function';
		    } else {
			functions[cur] = null;
			refreshEditor();
			return 'variable';
		    }
		}
		else{
		    return 'variable';
		}

	    }

	}

	// Interface

	return {
	    startState : function() {
		return {
		    tokenize : null,
		    functions:""
		};
	    },

	    token : function(stream, state) {
		if (stream.eatSpace())
		    return null;
		var style = (state.tokenize || tokenBase)(stream, state);
		return style;
	    },

	    electricChars : "{}"
	};
    });

    CodeMirror.defineMIME("text/x-kodbot", "kodbot");

});

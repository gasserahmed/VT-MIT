(function(t){function e(e){for(var n,c,s=e[0],i=e[1],l=e[2],p=0,f=[];p<s.length;p++)c=s[p],Object.prototype.hasOwnProperty.call(a,c)&&a[c]&&f.push(a[c][0]),a[c]=0;for(n in i)Object.prototype.hasOwnProperty.call(i,n)&&(t[n]=i[n]);u&&u(e);while(f.length)f.shift()();return r.push.apply(r,l||[]),o()}function o(){for(var t,e=0;e<r.length;e++){for(var o=r[e],n=!0,s=1;s<o.length;s++){var i=o[s];0!==a[i]&&(n=!1)}n&&(r.splice(e--,1),t=c(c.s=o[0]))}return t}var n={},a={app:0},r=[];function c(e){if(n[e])return n[e].exports;var o=n[e]={i:e,l:!1,exports:{}};return t[e].call(o.exports,o,o.exports,c),o.l=!0,o.exports}c.m=t,c.c=n,c.d=function(t,e,o){c.o(t,e)||Object.defineProperty(t,e,{enumerable:!0,get:o})},c.r=function(t){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(t,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(t,"__esModule",{value:!0})},c.t=function(t,e){if(1&e&&(t=c(t)),8&e)return t;if(4&e&&"object"===typeof t&&t&&t.__esModule)return t;var o=Object.create(null);if(c.r(o),Object.defineProperty(o,"default",{enumerable:!0,value:t}),2&e&&"string"!=typeof t)for(var n in t)c.d(o,n,function(e){return t[e]}.bind(null,n));return o},c.n=function(t){var e=t&&t.__esModule?function(){return t["default"]}:function(){return t};return c.d(e,"a",e),e},c.o=function(t,e){return Object.prototype.hasOwnProperty.call(t,e)},c.p="/GasserBookstoreFetch/";var s=window["webpackJsonp"]=window["webpackJsonp"]||[],i=s.push.bind(s);s.push=e,s=s.slice();for(var l=0;l<s.length;l++)e(s[l]);var u=i;r.push([0,"chunk-vendors"]),o()})({0:function(t,e,o){t.exports=o("56d7")},"0051":function(t,e,o){t.exports=o.p+"img/1007.df5f7270.jpg"},"0200":function(t,e,o){t.exports=o.p+"img/1003.f329eb54.jpg"},"0b95":function(t,e,o){t.exports=o.p+"img/1012.9948e2f3.jpg"},"1c4f":function(t,e,o){t.exports=o.p+"img/kitab-lab-logo.16d96d9d.png"},"1e44":function(t,e,o){t.exports=o.p+"img/1030.855d0f91.jpg"},"365c":function(t,e,o){t.exports=o.p+"img/1024.e2f1bcbe.jpg"},"3fca":function(t,e,o){"use strict";o("63c0")},4443:function(t,e,o){t.exports=o.p+"img/1028.e2f1062a.jpg"},"4ae7":function(t,e,o){t.exports=o.p+"img/1009.4520843c.jpg"},"4b96":function(t,e,o){},5068:function(t,e,o){t.exports=o.p+"img/1032.b3d5653a.jpg"},5208:function(t,e,o){t.exports=o.p+"img/1025.657fb6b7.jpg"},"52d7":function(t,e,o){},"532d":function(t,e,o){t.exports=o.p+"img/1022.f8dfc9c0.jpg"},"55e2":function(t,e,o){t.exports=o.p+"img/1014.234cd01d.jpg"},"56d7":function(t,e,o){"use strict";o.r(e);var n=o("2b0e"),a=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("div",{attrs:{id:"app"}},[o("app-header"),o("router-view",{key:t.$route.fullPath}),o("app-footer")],1)},r=[],c=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("header",{staticClass:"container header-with-shadow"},[n("section",{staticClass:"bookstore-logo-title flex-centered-container"},[n("router-link",{attrs:{to:"/"}},[n("img",{attrs:{src:o("1c4f"),alt:"Kitab Lab Logo",width:"57px",height:"auto"}})]),n("span",{staticClass:"logo-title-divider"}),n("router-link",{staticClass:"logo-text",attrs:{to:"/",tag:"h1"}},[t._v("KITAB LAB ")])],1),n("section",{staticClass:"search-bar flex-centered-container"},[n("form",{staticClass:"flex-centered-container"},[n("input",{staticClass:"search-bar-text",attrs:{type:"text",placeholder:"Search books"}}),n("br"),n("router-link",{staticClass:"button icon-inside-button search-bar-button",attrs:{to:"/category/"+t.defaultCategory.name,tag:"li"}},[n("i",{staticClass:"fas fa-search"})])],1)]),n("section",{staticClass:"header-dropdown-and-cart flex-centered-container"},[n("header-dropdown-menu"),t._m(0),n("button",{staticClass:"button",staticStyle:{padding:"7px"}},[t._v("GA")])],1)])},s=[function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("button",{staticClass:"button icon-only-button"},[o("i",{staticClass:"fas fa-shopping-cart icon-only-button-icon"},[o("span",{staticClass:"icon-only-button-text"},[t._v("0")])])])}],i=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("div",{staticClass:"header-dropdown"},[o("button",{staticClass:"button categories-button"},[t._v("Categories")]),o("ul",t._l(t.categories,(function(e){return o("router-link",{key:e.categoryId,attrs:{to:"/category/"+e.name,tag:"li"}},[t._v(" "+t._s(e.name)+" ")])})),1)])},l=[];const u={"http:":":8080","https:":":8443"},p=location.protocol+"//"+location.hostname+u[location.protocol]+"/GasserBookstoreFetch/api";var f={fetchCategories(){const t=p+"/categories";return console.log("GET from "+t),fetch(t).then(t=>{if(t.ok)return t.json();throw new Error("Network response was not ok.")}).catch(t=>{console.log("Error fetching category data",t)})},fetchDefaultCategory(){const t=p+"/categories/1001";return console.log("GET from "+t),fetch(t).then(t=>{if(t.ok)return t.json();throw new Error("Network response was not ok.")}).catch(t=>{console.log("Error fetching default category data",t)})},fetchSelectedCategoryBooks(t){const e=p+"/categories/name/"+t+"/books";return console.log("GET from "+e),fetch(e).then(t=>{if(t.ok)return t.json();throw new Error("Network response was not ok.")}).catch(t=>{console.log("Error fetching selected category books data",t)})}},g={name:"HeaderDropdownMenu",data:function(){return{categories:[]}},created:function(){console.log("Start fetchCategories"),this.fetchCategories(),console.log("Finish fetchCategories")},methods:{fetchCategories(){const t=this;f.fetchCategories().then(e=>{console.log("Data: "+e),t.categories=e}).catch(t=>{console.log("Error: "+t)})}}},d=g,b=(o("bbea"),o("2877")),h=Object(b["a"])(d,i,l,!1,null,"5c2494db",null),m=h.exports,j={name:"AppHeader",components:{HeaderDropdownMenu:m},component:{HeaderDropdownMenu:m},data:function(){return{defaultCategory:Object}},created:function(){console.log("Start fetchDefaultCategory"),this.fetchDefaultCategory(),console.log("Finish fetchDefaultCategory")},methods:{fetchDefaultCategory(){const t=this;f.fetchDefaultCategory().then(e=>{console.log("Data: "+e),t.defaultCategory=e}).catch(t=>{console.log("Error: "+t)})}}},y=j,C=(o("5f7d"),Object(b["a"])(y,c,s,!1,null,"3ee94986",null)),k=C.exports,v=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("footer",{staticClass:"container"},[t._m(0),o("section",{staticClass:"links"},[o("router-link",{attrs:{to:"/"}},[t._v("About")]),o("router-link",{attrs:{to:"/"}},[t._v("Directions")]),o("router-link",{attrs:{to:"/"}},[t._v("Contact")])],1),o("section",{staticClass:"social-media-icons"},[o("router-link",{staticClass:"icon-only-button-icon",attrs:{to:"/"}},[o("i",{staticClass:"fab fa-facebook"})]),o("router-link",{staticClass:"icon-only-button-icon",attrs:{to:"/"}},[o("i",{staticClass:"fab fa-twitter"})]),o("router-link",{staticClass:"icon-only-button-icon",attrs:{to:"/"}},[o("i",{staticClass:"fab fa-instagram"})])],1)])},x=[function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("section",{staticClass:"copyright"},[n("i",{staticClass:"far fa-copyright"}),n("span",[t._v("2021")]),n("img",{attrs:{src:o("d334"),alt:"Kitab Lab Logo",width:"57px",height:"auto"}})])}],_={name:"AppFooter"},w=_,E=(o("657b"),Object(b["a"])(w,v,x,!1,null,"1465fa7c",null)),O=E.exports,D={name:"App",components:{AppHeader:k,AppFooter:O}},B=D,S=Object(b["a"])(B,a,r,!1,null,null,null),$=S.exports,A=o("8c4f"),F=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("div",{staticClass:"home-page"},[o("section",{staticClass:"welcome-text flow-content container"},[o("h2",[t._v("KITAB LAB")]),o("p",[t._v("Where every book is a new experiment.")]),o("router-link",{staticClass:"button shop-button",attrs:{to:"/category/"+t.defaultCategory.name,tag:"li"}},[t._v(" Shop Your Experiment ")])],1)])},I=[],L={name:"Home",data:function(){return{defaultCategory:Object}},created:function(){console.log("Start fetchDefaultCategory"),this.fetchDefaultCategory(),console.log("Finish fetchDefaultCategory")},methods:{fetchDefaultCategory(){const t=this;f.fetchDefaultCategory().then(e=>{console.log("Data: "+e),t.defaultCategory=e}).catch(t=>{console.log("Error: "+t)})}}},P=L,T=(o("7482"),Object(b["a"])(P,F,I,!1,null,"b1343b82",null)),M=T.exports,G=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("div",[o("category-nav"),o("category-book-list",{attrs:{books:t.books}})],1)},N=[],H=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("nav",{staticClass:"category-nav"},[o("ul",{staticClass:"category-buttons"},[t._l(t.categories,(function(e){return[e.name===t.$route.params.name?o("router-link",{key:e.categoryId,staticClass:"button selected-category-button",attrs:{to:"/category/"+e.name,tag:"li"}},[t._v(" "+t._s(e.name)+" ")]):o("router-link",{key:e.categoryId,staticClass:"button unselected-category-button",attrs:{to:"/category/"+e.name,tag:"li"}},[t._v(" "+t._s(e.name)+" ")])]}))],2)])},K=[],q={name:"CategoryNav",data:function(){return{categories:[]}},created:function(){console.log("Start fetchCategories"),this.fetchCategories(),console.log("Finish fetchCategories")},methods:{fetchCategories(){const t=this;f.fetchCategories().then(e=>{console.log("Data: "+e),t.categories=e}).catch(t=>{console.log("Error: "+t)})}}},J=q,U=(o("9a01"),Object(b["a"])(J,H,K,!1,null,"261c238e",null)),W=U.exports,Y=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("ul",t._l(t.books,(function(t){return o("category-book-list-item",{key:t.bookId,attrs:{book:t}})})),1)},z=[],Q=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("li",{staticClass:"book-box"},[n("div",{staticClass:"book-image"},[n("router-link",{attrs:{to:"#"}},[n("img",{attrs:{src:o("e9b6")("./"+t.book.bookId+".jpg"),alt:t.book.title}})])],1),n("div",{staticClass:"book-title"},[t._v(t._s(t.book.title))]),n("div",{staticClass:"book-author"},[t._v("by "+t._s(t.book.author))]),n("div",{staticClass:"book-price"},[t._v("$"+t._s((t.book.price/100).toFixed(2)))]),n("button",{staticClass:"button add-to-cart-button"},[t._v("Add to Cart")]),t.book.isPublic?n("button",{staticClass:"button read-now-button"},[n("i",{staticClass:"fab fa-readme"})]):t._e()])},R=[],V={name:"categoryBookListItem",props:{book:{type:Object,required:!0}}},X=V,Z=(o("af42"),Object(b["a"])(X,Q,R,!1,null,"7032be4d",null)),tt=Z.exports,et={name:"CategoryBookList",props:{books:{type:Array,required:!0}},components:{CategoryBookListItem:tt}},ot=et,nt=(o("3fca"),Object(b["a"])(ot,Y,z,!1,null,"2433e80a",null)),at=nt.exports,rt={name:"category",components:{CategoryNav:W,CategoryBookList:at},data:function(){return{books:[]}},created:function(){console.log("Start fetchBooksByCategoryId"),this.fetchSelectedCategoryBooks(this.$route.params.name),console.log("Finish fetchBooksByCategoryId")},methods:{fetchSelectedCategoryBooks(t){const e=this;f.fetchSelectedCategoryBooks(t).then(t=>{console.log("Data: "+t),e.books=t}).catch(t=>{console.log("Error: "+t)})}}},ct=rt,st=Object(b["a"])(ct,G,N,!1,null,"4cc8806c",null),it=st.exports;n["a"].use(A["a"]);var lt=new A["a"]({mode:"history",base:"/GasserBookstoreFetch/",routes:[{path:"/",name:"home",component:M},{path:"/category/:name",name:"category",component:it,props:!0}]});o("aede");n["a"].config.productionTip=!1,new n["a"]({router:lt,render:function(t){return t($)}}).$mount("#app")},"5a3c":function(t,e,o){t.exports=o.p+"img/1020.9ad05bef.jpg"},"5e05":function(t,e,o){t.exports=o.p+"img/1017.59c20859.jpg"},"5f7d":function(t,e,o){"use strict";o("f7e8")},"63c0":function(t,e,o){},"657b":function(t,e,o){"use strict";o("a439")},"677d":function(t,e,o){t.exports=o.p+"img/1019.c66dfdc9.jpg"},"6fee":function(t,e,o){t.exports=o.p+"img/1001.3ad59916.jpg"},7482:function(t,e,o){"use strict";o("4b96")},"76c6":function(t,e,o){t.exports=o.p+"img/1013.f38d17a0.jpg"},"82dd":function(t,e,o){t.exports=o.p+"img/1010.5f143c2d.jpg"},"8bbf":function(t,e,o){t.exports=o.p+"img/1002.e097c192.jpg"},"8f2b":function(t,e,o){t.exports=o.p+"img/1023.001f14eb.jpg"},"93a6":function(t,e,o){t.exports=o.p+"img/1018.fbc24ee8.jpg"},"9a01":function(t,e,o){"use strict";o("e734")},"9c5c":function(t,e,o){t.exports=o.p+"img/1004.8619a783.jpg"},"9e39":function(t,e,o){t.exports=o.p+"img/1029.d65237d2.jpg"},a439:function(t,e,o){},aede:function(t,e,o){},af42:function(t,e,o){"use strict";o("52d7")},bac8:function(t,e,o){},bbea:function(t,e,o){"use strict";o("bac8")},bdcc:function(t,e,o){t.exports=o.p+"img/1008.20f23edb.jpg"},be70:function(t,e,o){t.exports=o.p+"img/1011.9ce68ea7.jpg"},c0b7:function(t,e,o){t.exports=o.p+"img/1034.21465eca.jpg"},c21c:function(t,e,o){t.exports=o.p+"img/1006.a2ee099a.jpg"},c263:function(t,e,o){t.exports=o.p+"img/1027.11b1bd4d.jpg"},cce1:function(t,e,o){t.exports=o.p+"img/1005.0eb3f915.jpg"},cda6:function(t,e,o){t.exports=o.p+"img/1015.cbae8a18.jpg"},ce4a:function(t,e,o){t.exports=o.p+"img/1031.1cd3caa2.jpg"},d084:function(t,e,o){t.exports=o.p+"img/1033.2ef96e67.jpg"},d334:function(t,e,o){t.exports=o.p+"img/kitab-lab-logo-white.73e1a38c.png"},d4ad:function(t,e,o){t.exports=o.p+"img/1026.e1608b5d.jpg"},d920:function(t,e,o){t.exports=o.p+"img/1021.d420bc6f.jpg"},e734:function(t,e,o){},e9b6:function(t,e,o){var n={"./1001.jpg":"6fee","./1002.jpg":"8bbf","./1003.jpg":"0200","./1004.jpg":"9c5c","./1005.jpg":"cce1","./1006.jpg":"c21c","./1007.jpg":"0051","./1008.jpg":"bdcc","./1009.jpg":"4ae7","./1010.jpg":"82dd","./1011.jpg":"be70","./1012.jpg":"0b95","./1013.jpg":"76c6","./1014.jpg":"55e2","./1015.jpg":"cda6","./1016.jpg":"f5ac","./1017.jpg":"5e05","./1018.jpg":"93a6","./1019.jpg":"677d","./1020.jpg":"5a3c","./1021.jpg":"d920","./1022.jpg":"532d","./1023.jpg":"8f2b","./1024.jpg":"365c","./1025.jpg":"5208","./1026.jpg":"d4ad","./1027.jpg":"c263","./1028.jpg":"4443","./1029.jpg":"9e39","./1030.jpg":"1e44","./1031.jpg":"ce4a","./1032.jpg":"5068","./1033.jpg":"d084","./1034.jpg":"c0b7"};function a(t){var e=r(t);return o(e)}function r(t){if(!o.o(n,t)){var e=new Error("Cannot find module '"+t+"'");throw e.code="MODULE_NOT_FOUND",e}return n[t]}a.keys=function(){return Object.keys(n)},a.resolve=r,t.exports=a,a.id="e9b6"},f5ac:function(t,e,o){t.exports=o.p+"img/1016.828d1779.jpg"},f7e8:function(t,e,o){}});
//# sourceMappingURL=app.933949b5.js.map
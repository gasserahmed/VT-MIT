(function(t){function e(e){for(var r,s,i=e[0],c=e[1],l=e[2],d=0,p=[];d<i.length;d++)s=i[d],Object.prototype.hasOwnProperty.call(o,s)&&o[s]&&p.push(o[s][0]),o[s]=0;for(r in c)Object.prototype.hasOwnProperty.call(c,r)&&(t[r]=c[r]);u&&u(e);while(p.length)p.shift()();return n.push.apply(n,l||[]),a()}function a(){for(var t,e=0;e<n.length;e++){for(var a=n[e],r=!0,i=1;i<a.length;i++){var c=a[i];0!==o[c]&&(r=!1)}r&&(n.splice(e--,1),t=s(s.s=a[0]))}return t}var r={},o={app:0},n=[];function s(e){if(r[e])return r[e].exports;var a=r[e]={i:e,l:!1,exports:{}};return t[e].call(a.exports,a,a.exports,s),a.l=!0,a.exports}s.m=t,s.c=r,s.d=function(t,e,a){s.o(t,e)||Object.defineProperty(t,e,{enumerable:!0,get:a})},s.r=function(t){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(t,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(t,"__esModule",{value:!0})},s.t=function(t,e){if(1&e&&(t=s(t)),8&e)return t;if(4&e&&"object"===typeof t&&t&&t.__esModule)return t;var a=Object.create(null);if(s.r(a),Object.defineProperty(a,"default",{enumerable:!0,value:t}),2&e&&"string"!=typeof t)for(var r in t)s.d(a,r,function(e){return t[e]}.bind(null,r));return a},s.n=function(t){var e=t&&t.__esModule?function(){return t["default"]}:function(){return t};return s.d(e,"a",e),e},s.o=function(t,e){return Object.prototype.hasOwnProperty.call(t,e)},s.p="/GasserBookstoreTransact/";var i=window["webpackJsonp"]=window["webpackJsonp"]||[],c=i.push.bind(i);i.push=e,i=i.slice();for(var l=0;l<i.length;l++)e(i[l]);var u=c;n.push([0,"chunk-vendors"]),a()})({0:function(t,e,a){t.exports=a("56d7")},"0051":function(t,e,a){t.exports=a.p+"img/1007.df5f7270.jpg"},"0200":function(t,e,a){t.exports=a.p+"img/1003.f329eb54.jpg"},"02a2":function(t,e,a){},"034f":function(t,e,a){"use strict";a("85ec")},"0b95":function(t,e,a){t.exports=a.p+"img/1012.9948e2f3.jpg"},"11bb":function(t,e,a){},"1c4f":function(t,e,a){t.exports=a.p+"img/kitab-lab-logo.16d96d9d.png"},"1e44":function(t,e,a){t.exports=a.p+"img/1030.855d0f91.jpg"},"1e76":function(t,e,a){},2117:function(t,e,a){},"365c":function(t,e,a){t.exports=a.p+"img/1024.e2f1bcbe.jpg"},"36bb":function(t,e,a){"use strict";a("02a2")},4443:function(t,e,a){t.exports=a.p+"img/1028.e2f1062a.jpg"},"44a1":function(t,e,a){},"44ff":function(t,e,a){"use strict";a("8961")},"452b":function(t,e,a){"use strict";a("8278")},"4ae7":function(t,e,a){t.exports=a.p+"img/1009.4520843c.jpg"},5068:function(t,e,a){t.exports=a.p+"img/1032.b3d5653a.jpg"},5208:function(t,e,a){t.exports=a.p+"img/1025.657fb6b7.jpg"},"532d":function(t,e,a){t.exports=a.p+"img/1022.f8dfc9c0.jpg"},"54d4":function(t,e,a){},"55e2":function(t,e,a){t.exports=a.p+"img/1014.234cd01d.jpg"},"56d7":function(t,e,a){"use strict";a.r(e);var r=a("2b0e"),o=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{attrs:{id:"app"}},[a("app-header"),a("router-view",{key:t.$route.fullPath}),a("app-footer")],1)},n=[],s=function(){var t=this,e=t.$createElement,r=t._self._c||e;return r("header",{staticClass:"container header-with-shadow"},[r("section",{staticClass:"bookstore-logo-title flex-centered-container"},[r("router-link",{attrs:{to:"/"}},[r("img",{staticClass:"logo-image",attrs:{src:a("1c4f"),alt:"Kitab Lab Logo",width:"57px",height:"auto"}})]),r("span",{staticClass:"logo-title-divider"}),r("router-link",{staticClass:"logo-text",attrs:{to:"/",tag:"h1"}},[t._v("KITAB LAB ")])],1),r("section",{staticClass:"search-bar flex-centered-container"},[r("form",{staticClass:"flex-centered-container"},[r("input",{staticClass:"search-bar-text",attrs:{type:"text",placeholder:"Search books"}}),r("br"),r("router-link",{staticClass:"button icon-inside-button search-bar-button",attrs:{to:"/category/"+t.$store.state.selectedCategoryName,tag:"li"}},[r("i",{staticClass:"fas fa-search"})])],1)]),r("section",{staticClass:"header-dropdown-and-cart flex-centered-container"},[r("header-dropdown-menu"),r("router-link",{staticClass:"button icon-only-button",attrs:{to:"/cart",tag:"button"}},[r("i",{staticClass:"fas fa-shopping-cart icon-only-button-icon"},[r("span",{staticClass:"icon-only-button-text"},[t._v(t._s(t.$store.state.cart.numberOfItems))])])]),r("button",{staticClass:"button",staticStyle:{padding:"7px"}},[t._v("GA")])],1)])},i=[],c=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"header-dropdown"},[t._m(0),a("ul",[t.$store.state.loadingStatus&&0===t.$store.state.categories.length?a("div",{staticClass:"loading-spinner-container"},[a("vue-spinner",{attrs:{"line-bg-color":"#D4D5D8","line-fg-color":"#2B5658",size:"26"}})],1):t._l(t.$store.state.categories,(function(e){return a("router-link",{key:e.categoryId,attrs:{to:"/category/"+e.name,tag:"li"}},[t._v(" "+t._s(e.name)+" ")])}))],2)])},l=[function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("button",{staticClass:"button categories-button"},[t._v(" Categories "),a("i",{staticClass:"fas fa-caret-right"})])}],u=a("a120"),d=a.n(u),p={name:"HeaderDropdownMenu",components:{vueSpinner:d.a}},m=p,g=(a("d580"),a("2877")),f=Object(g["a"])(m,c,l,!1,null,"03537059",null),b=f.exports,h={name:"AppHeader",components:{HeaderDropdownMenu:b},component:{HeaderDropdownMenu:b}},v=h,_=(a("44ff"),Object(g["a"])(v,s,i,!1,null,"4bad0cf1",null)),C=_.exports,y=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("footer",{staticClass:"container"},[t._m(0),a("section",{staticClass:"links"},[a("router-link",{attrs:{to:"/"}},[t._v("About")]),a("router-link",{attrs:{to:"/"}},[t._v("Directions")]),a("router-link",{attrs:{to:"/"}},[t._v("Contact")])],1),a("section",{staticClass:"social-media-icons"},[a("router-link",{staticClass:"icon-only-button-icon",attrs:{to:"/"}},[a("i",{staticClass:"fab fa-facebook"})]),a("router-link",{staticClass:"icon-only-button-icon",attrs:{to:"/"}},[a("i",{staticClass:"fab fa-twitter"})]),a("router-link",{staticClass:"icon-only-button-icon",attrs:{to:"/"}},[a("i",{staticClass:"fab fa-instagram"})])],1)])},k=[function(){var t=this,e=t.$createElement,r=t._self._c||e;return r("section",{staticClass:"copyright"},[r("i",{staticClass:"far fa-copyright"}),r("span",[t._v("2021")]),r("img",{attrs:{src:a("d334"),alt:"Kitab Lab Logo",width:"57px",height:"auto"}})])}],x={name:"AppFooter"},$=x,S=(a("707d"),Object(g["a"])($,y,k,!1,null,"5d62bf80",null)),w=S.exports,E={name:"App",components:{AppHeader:C,AppFooter:w},created:function(){this.$store.dispatch("fetchCategories")}},j=E,D=(a("034f"),Object(g["a"])(j,o,n,!1,null,null,null)),O=D.exports,T=a("8c4f"),N=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"home-page page-container"},[a("section",{staticClass:"welcome-text flow-content container"},[a("h2",[t._v("KITAB LAB")]),a("p",[t._v("Where every book is a new experiment.")]),a("router-link",{staticClass:"button shop-button",attrs:{to:"/category/"+t.$store.state.selectedCategoryName,tag:"li"}},[t._v(" Shop Your Experiment ")])],1)])},A=[],I={name:"Home"},q=I,L=(a("a48a"),Object(g["a"])(q,N,A,!1,null,"4d17c027",null)),R=L.exports,P=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"page-container"},[a("category-nav"),t.$store.state.loadingStatus?a("div",{staticClass:"loading-spinner-container"},[a("vue-spinner",{attrs:{"line-bg-color":"#D4D5D8","line-fg-color":"#2B5658"}})],1):a("category-book-list")],1)},B=[],G=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("nav",{staticClass:"category-nav"},[a("ul",{staticClass:"category-buttons"},[t._l(t.$store.state.categories,(function(e){return[e.name===t.$store.state.selectedCategoryName?a("router-link",{key:e.categoryId,staticClass:"button selected-category-button",attrs:{to:"/category/"+e.name,tag:"li"}},[t._v(" "+t._s(e.name)+" ")]):a("router-link",{key:e.categoryId,staticClass:"button unselected-category-button",attrs:{to:"/category/"+e.name,tag:"li"}},[t._v(" "+t._s(e.name)+" ")])]}))],2)])},M=[],z={name:"CategoryNav"},Y=z,F=(a("65ee"),Object(g["a"])(Y,G,M,!1,null,"37cab5d0",null)),U=F.exports,J=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("ul",t._l(t.$store.state.selectedCategoryBooks,(function(t){return a("category-book-list-item",{key:t.bookId,attrs:{book:t}})})),1)},H=[],K=function(){var t=this,e=t.$createElement,r=t._self._c||e;return r("li",{staticClass:"book-box"},[r("div",{staticClass:"book-image"},[r("router-link",{attrs:{to:"#"}},[r("img",{attrs:{src:a("e9b6")("./"+t.book.bookId+".jpg"),alt:t.book.title}})])],1),r("div",{staticClass:"book-title"},[t._v(t._s(t.book.title))]),r("div",{staticClass:"book-author"},[t._v("by "+t._s(t.book.author))]),r("div",{staticClass:"book-price"},[t._v(t._s(t._f("asDollarsAndCents")(t.book.price)))]),r("button",{staticClass:"button add-to-cart-button",on:{click:function(e){return t.addToCart(t.book)}}},[t._v(" Add to Cart ")]),t.book.isPublic?r("button",{staticClass:"button read-now-button"},[r("i",{staticClass:"fab fa-readme"})]):t._e()])},W=[],V={name:"categoryBookListItem",props:{book:{type:Object,required:!0}},methods:{addToCart(t){this.$store.dispatch("addToCart",t)}}},Q=V,Z=(a("e1af"),Object(g["a"])(Q,K,W,!1,null,"5b1aa158",null)),X=Z.exports,tt={name:"CategoryBookList",components:{CategoryBookListItem:X}},et=tt,at=(a("eb13"),Object(g["a"])(et,J,H,!1,null,"1d5976d8",null)),rt=at.exports,ot={name:"category",components:{CategoryNav:U,CategoryBookList:rt,vueSpinner:d.a},created:function(){const t=this;this.$store.dispatch("selectCategory",this.$route.params.name),this.$store.dispatch("fetchSelectedCategoryBooks").catch((function(){t.$router.push("/404")}))}},nt=ot,st=Object(g["a"])(nt,P,B,!1,null,"df6aa9b2",null),it=st.exports,ct=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"page-container"},[a("div",{staticClass:"page-body"},[t.cart.empty?a("warning-container",{attrs:{"warning-image":{src:"empty-shopping-cart.png"},"warning-title":"Your cart is currently empty!","warning-description":"Looks like you haven't added anything to your cart yet.","warning-button-route":"/category/"+t.$store.state.selectedCategoryName,"warning-button-text":"Shop Your Experiment"}}):a("section-container",{attrs:{title:"Here's what you're getting!",description:"You have "+t.$store.state.cart.numberOfItems+" "+(1===t.$store.state.cart.numberOfItems?"item":"items")+" in your cart."}},[a("cart-table"),a("div",{staticClass:"cart-footer"},[a("button",{staticClass:"button link-like-button clear-button",on:{click:t.clearCart}},[t._v(" Clear the Cart ")]),a("div",{staticClass:"cart-total"},[a("span",{staticClass:"subtotal"},[t._v("Subtotal: "+t._s(t._f("asDollarsAndCents")(t.$store.state.cart.subtotal)))])])]),a("div",{staticClass:"cart-buttons"},[a("router-link",{staticClass:"button secondary-button",attrs:{to:"/category/"+t.$store.state.selectedCategoryName,tag:"button"}},[t._v(" Continue Shopping ")]),a("router-link",{staticClass:"button",attrs:{to:"/checkout",tag:"button"}},[t._v(" Proceed to Checkout ")])],1)],1)],1)])},lt=[],ut=function(){var t=this,e=t.$createElement,r=t._self._c||e;return r("div",{staticClass:"cart-table"},[r("ul",[t._m(0),r("row-separator",{staticStyle:{"margin-top":"-1em"}}),t._l(t.$store.state.cart.items,(function(e){return r("li",{key:e.book.bookId},[r("div",{staticClass:"cart-book-image"},[r("img",{attrs:{src:a("e9b6")("./"+e.book.bookId+".jpg"),alt:e.book.title,width:"100px",height:"auto"}})]),r("div",{staticClass:"cart-book-title"},[t._v(t._s(e.book.title))]),r("div",{staticClass:"cart-book-price"},[r("span",{staticClass:"column-label"},[t._v("Price:")]),t._v(" "+t._s(t._f("asDollarsAndCents")(e.book.price))+" ")]),r("div",{staticClass:"cart-book-quantity"},[r("span",{staticClass:"column-label"},[t._v("Quantity:")]),r("button",{staticClass:"button icon-only-button dec-button",on:{click:function(a){return t.updateCart(e.book,e.quantity-1)}}},[r("i",{staticClass:"fas fa-minus-circle"})]),r("span",{staticClass:"row-book-quantity"},[t._v(t._s(e.quantity))]),r("button",{staticClass:"button icon-only-button inc-button",on:{click:function(a){return t.updateCart(e.book,e.quantity+1)}}},[r("i",{staticClass:"fas fa-plus-circle"})])]),r("div",{staticClass:"subtotal"},[r("span",{staticClass:"column-label"},[t._v("Total:")]),t._v(" "+t._s(t._f("asDollarsAndCents")(e.quantity*e.book.price))+" ")]),r("row-separator")],1)}))],2)])},dt=[function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("li",{staticClass:"cart-heading"},[a("div",{staticClass:"cart-heading-book"},[t._v("Book")]),a("div",{staticClass:"cart-heading-price"},[t._v("Price")]),a("div",{staticClass:"cart-heading-quantity"},[t._v("Quantity")]),a("div",{staticClass:"cart-heading-subtotal"},[t._v("Total")])])}],pt=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"row-separator"})},mt=[],gt={name:"RowSeparator"},ft=gt,bt=(a("452b"),Object(g["a"])(ft,pt,mt,!1,null,"7126ffe7",null)),ht=bt.exports,vt={name:"CartTable",components:{RowSeparator:ht},methods:{updateCart(t,e){this.$store.dispatch("updateCart",{book:t,quantity:e})}}},_t=vt,Ct=(a("9c40"),Object(g["a"])(_t,ut,dt,!1,null,"5b5e75eb",null)),yt=Ct.exports,kt=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"section-container"},[a("section",[t.title?a("div",{staticClass:"section-header"},[a("div",[a("h2",{staticClass:"section-title"},[t._v(t._s(t.title))]),t.button?a("router-link",{staticClass:"button link-like-button",attrs:{to:t.button.routeTo,tag:"button"}},[t._v(" "+t._s(t.button.text)+" ")]):t._e()],1),a("row-separator"),t.description?a("div",{staticClass:"section-description",domProps:{innerHTML:t._s(t.description)}}):t._e()],1):t._e(),a("div",{staticClass:"section-body"},[t._t("default")],2)])])},xt=[],$t={name:"SectionContainer",components:{RowSeparator:ht},props:{title:String,description:String,button:Object}},St=$t,wt=(a("36bb"),Object(g["a"])(St,kt,xt,!1,null,"d627486c",null)),Et=wt.exports,jt=function(){var t=this,e=t.$createElement,r=t._self._c||e;return r("section-container",[r("section",[t.warningImage?r("img",{style:"height:"+t.warningImage.height+";width:"+t.warningImage.width,attrs:{src:a("ad2b")("./"+t.warningImage.src),alt:"Warning image"}}):t._e(),t.warningTitle?r("span",{staticClass:"warning-title"},[t._v(t._s(t.warningTitle))]):t._e(),t.warningDescription?r("span",[t._v(t._s(t.warningDescription))]):t._e(),t.warningButtonRoute?r("router-link",{staticClass:"button",attrs:{to:t.warningButtonRoute,tag:"button"}},[t._v(" "+t._s(t.warningButtonText)+" ")]):t._e()],1)])},Dt=[],Ot={name:"WarningContainer",components:{SectionContainer:Et},props:{warningImage:{src:String,width:String,height:String},warningTitle:String,warningDescription:String,warningButtonRoute:String,warningButtonText:String}},Tt=Ot,Nt=(a("9b48"),Object(g["a"])(Tt,jt,Dt,!1,null,"61f4ece2",null)),At=Nt.exports,It={name:"Cart",components:{WarningContainer:At,SectionContainer:Et,CartTable:yt},computed:{cart(){return this.$store.state.cart}},methods:{clearCart(){this.$store.dispatch("clearCart")}}},qt=It,Lt=(a("604a"),Object(g["a"])(qt,ct,lt,!1,null,"084ded60",null)),Rt=Lt.exports,Pt=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"page-container"},[a("div",{staticClass:"page-body"},[t.cart.empty?a("warning-container",{attrs:{"warning-image":{src:"empty-shopping-cart.png"},"warning-title":"Your cart is currently empty!","warning-description":"Once you have added items to your cart, you can check out from here.","warning-button-route":"/category/"+t.$store.state.selectedCategoryName,"warning-button-text":"Shop Your Experiment"}}):a("div",[a("section-container",{attrs:{title:"Checkout"}},[a("form",{attrs:{id:"checkout-form"},on:{submit:function(e){return e.preventDefault(),t.submitOrder.apply(null,arguments)}}},[a("div",[a("label",{attrs:{for:"name"}},[t._v("Name")]),a("div",[a("input",{directives:[{name:"model",rawName:"v-model.lazy",value:t.$v.name.$model,expression:"$v.name.$model",modifiers:{lazy:!0}}],attrs:{type:"text",size:"20",id:"name",name:"name",placeholder:"Full Name"},domProps:{value:t.$v.name.$model},on:{change:function(e){return t.$set(t.$v.name,"$model",e.target.value)}}}),t.$v.name.$error?[t.$v.name.required?t.$v.name.minLength?t.$v.name.maxLength?a("span",{staticClass:"error"},[t._v(" An unexpected error occurred. ")]):a("span",{staticClass:"error"},[t._v(" Name can have at most "+t._s(t.$v.name.$params.maxLength.max)+" letters. ")]):a("span",{staticClass:"error"},[t._v(" Name must have at least "+t._s(t.$v.name.$params.minLength.min)+" letters. ")]):a("span",{staticClass:"error"},[t._v("Name is required")])]:t._e()],2)]),a("div",[a("label",{attrs:{for:"address"}},[t._v("Address")]),a("div",[a("input",{directives:[{name:"model",rawName:"v-model.lazy",value:t.$v.address.$model,expression:"$v.address.$model",modifiers:{lazy:!0}}],attrs:{type:"text",size:"20",id:"address",name:"address",placeholder:"Street, City, State, Zip Code"},domProps:{value:t.$v.address.$model},on:{change:function(e){return t.$set(t.$v.address,"$model",e.target.value)}}}),t.$v.address.$error?[t.$v.address.required?t.$v.address.minLength?t.$v.address.maxLength?a("span",{staticClass:"error"},[t._v(" An unexpected error occurred. ")]):a("span",{staticClass:"error"},[t._v(" Address can have at most "+t._s(t.$v.address.$params.maxLength.max)+" letters. ")]):a("span",{staticClass:"error"},[t._v(" Address must have at least "+t._s(t.$v.address.$params.minLength.min)+" letters. ")]):a("span",{staticClass:"error"},[t._v("Address is required")])]:t._e()],2)]),a("div",[a("label",{attrs:{for:"phone"}},[t._v("Phone")]),a("div",[a("input",{directives:[{name:"model",rawName:"v-model.lazy",value:t.$v.phone.$model,expression:"$v.phone.$model",modifiers:{lazy:!0}},{name:"model",rawName:"v-model",value:t.phone,expression:"phone"}],staticClass:"textField",attrs:{type:"text",size:"20",id:"phone",name:"phone",placeholder:"Phone Number"},domProps:{value:t.$v.phone.$model,value:t.phone},on:{change:function(e){return t.$set(t.$v.phone,"$model",e.target.value)},input:function(e){e.target.composing||(t.phone=e.target.value)}}}),t.$v.phone.$error?[t.$v.phone.required?t.$v.phone.isValidPhone?a("span",{staticClass:"error"},[t._v(" An unexpected error occurred. ")]):a("span",{staticClass:"error"},[t._v("Please enter a valid phone number.")]):a("span",{staticClass:"error"},[t._v("Phone is required")])]:t._e()],2)]),a("div",[a("label",{attrs:{for:"email"}},[t._v("Email")]),a("div",[a("input",{directives:[{name:"model",rawName:"v-model.lazy",value:t.$v.email.$model,expression:"$v.email.$model",modifiers:{lazy:!0}}],attrs:{type:"text",size:"20",id:"email",name:"email",placeholder:"Email"},domProps:{value:t.$v.email.$model},on:{change:function(e){return t.$set(t.$v.email,"$model",e.target.value)}}}),t.$v.email.$error?[t.$v.email.required?t.$v.email.email?a("span",{staticClass:"error"},[t._v(" An unexpected error occurred. ")]):a("span",{staticClass:"error"},[t._v("Please enter a valid email.")]):a("span",{staticClass:"error"},[t._v("Email is required")])]:t._e()],2)]),a("div",[a("label",{attrs:{for:"ccNumber"}},[t._v("Credit Card")]),a("div",[a("input",{directives:[{name:"model",rawName:"v-model.lazy",value:t.$v.ccNumber.$model,expression:"$v.ccNumber.$model",modifiers:{lazy:!0}},{name:"model",rawName:"v-model",value:t.ccNumber,expression:"ccNumber"}],attrs:{type:"text",size:"20",id:"ccNumber",name:"ccNumber",placeholder:"xxxx-xxxx-xxxx-xxxx",inputmode:"numeric"},domProps:{value:t.$v.ccNumber.$model,value:t.ccNumber},on:{change:function(e){return t.$set(t.$v.ccNumber,"$model",e.target.value)},input:function(e){e.target.composing||(t.ccNumber=e.target.value)}}}),t.$v.ccNumber.$error?[t.$v.ccNumber.required?t.$v.ccNumber.isValidCreditCard?a("span",{staticClass:"error"},[t._v(" An unexpected error occurred. ")]):a("span",{staticClass:"error"},[t._v("Please enter a valid credit card.")]):a("span",{staticClass:"error"},[t._v("Credit card is required")])]:t._e()],2)]),a("div",[a("label",[t._v("Exp. Date")]),a("div",{staticClass:"expiration-date-input-container"},[a("select",{directives:[{name:"model",rawName:"v-model",value:t.ccExpiryMonth,expression:"ccExpiryMonth"}],on:{change:function(e){var a=Array.prototype.filter.call(e.target.options,(function(t){return t.selected})).map((function(t){var e="_value"in t?t._value:t.value;return e}));t.ccExpiryMonth=e.target.multiple?a:a[0]}}},t._l(t.months,(function(e,r){return a("option",{key:r,domProps:{value:r+1}},[t._v(" "+t._s(e)+" ("+t._s(r+1)+") ")])})),0),a("select",t._l(15,(function(e,r){return a("option",{key:r},[t._v(" "+t._s(t.yearFrom(r))+" ")])})),0)])])])]),a("section-container",{attrs:{title:t.$store.state.cart.numberOfItems+" "+(1===t.$store.state.cart.numberOfItems?"item":"items"),button:{text:"Edit cart",routeTo:"/cart"}}},[t.cart.empty?t._e():a("div",{staticClass:"checkout-summary"},[a("div",[a("div",{staticClass:"bold-text"},[a("span",[t._v("Subtotal")]),a("span",[t._v(t._s(t._f("asDollarsAndCents")(t.cart.subtotal)))])]),a("div",[a("span",[t._v("Shipping")]),a("span",[t._v(t._s(t._f("asDollarsAndCents")(t.cart.surcharge)))])]),a("row-separator"),a("div",{staticClass:"bold-text"},[a("span",[t._v("Total")]),a("span",[t._v(t._s(t._f("asDollarsAndCents")(t.cart.subtotal+t.cart.surcharge)))])])],1),a("div",{staticClass:"button-wrapper",class:{disabled:"PENDING"==t.checkoutStatus}},[a("button",{staticClass:"button",attrs:{type:"submit",name:"submit",disabled:"PENDING"==t.checkoutStatus,form:"checkout-form"}},[t._v(" Complete Purchase ")])]),a("section",{directives:[{name:"show",rawName:"v-show",value:""!=t.checkoutStatus,expression:"checkoutStatus != ''"}],staticClass:"checkoutStatusBox"},["ERROR"==t.checkoutStatus?a("div",[t._v(" Error: Please fix the problems and try again. ")]):"PENDING"==t.checkoutStatus?a("div",{staticClass:"loading-spinner-container"},[a("vue-spinner",{attrs:{"line-bg-color":"#D4D5D8","line-fg-color":"#2B5658",message:"Processing..."}})],1):"OK"==t.checkoutStatus?a("div",[t._v("Order placed...")]):a("div",[t._v(" An unexpected error occurred, please try again. ")])])])])],1)],1)])},Bt=[],Gt=a("9341"),Mt=a("a606"),zt=a.n(Mt),Yt=a("3079"),Ft=a.n(Yt),Ut=a("30f8"),Jt=a.n(Ut);const Ht=t=>zt()(t,"en-US"),Kt=t=>Jt()(t);var Wt={components:{WarningContainer:At,SectionContainer:Et,vueSpinner:d.a,RowSeparator:ht},data(){return{name:"",address:"",phone:"",email:"",ccNumber:"",ccExpiryMonth:(new Date).getMonth()+1,ccExpiryYear:(new Date).getFullYear(),checkoutStatus:""}},validations:{name:{required:Gt["required"],minLength:Object(Gt["minLength"])(4),maxLength:Object(Gt["maxLength"])(45)},address:{required:Gt["required"],minLength:Object(Gt["minLength"])(4),maxLength:Object(Gt["maxLength"])(45)},phone:{required:Gt["required"],isValidPhone:Ht},email:{required:Gt["required"],email:Ft.a},ccNumber:{required:Gt["required"],isValidCreditCard:Kt}},computed:{cart(){return this.$store.state.cart},months(){return["January","February","March","April","May","June","July","August","September","October","November","December"]}},methods:{submitOrder(){this.$v.$touch(),this.$v.$invalid?this.checkoutStatus="ERROR":(this.checkoutStatus="PENDING",setTimeout(()=>{this.$store.dispatch("placeOrder",{name:this.name,address:this.address,phone:this.phone.replace(/\D/g,""),email:this.email,ccNumber:this.ccNumber.replace(/\D/g,""),ccExpiryMonth:this.ccExpiryMonth,ccExpiryYear:this.ccExpiryYear}).then(()=>{this.checkoutStatus="OK",this.$router.push({name:"confirmation"})}).catch(t=>{this.checkoutStatus="SERVER_ERROR",console.log("Error placing order",t)})},2e3))},yearFrom(t){return(new Date).getFullYear()+t}},watch:{ccNumber(){let t=this.ccNumber.replace(/-/gi,""),e=t.match(/.{1,4}/g);this.ccNumber=e.join("-")},phone(){this.phone=this.$options.filters.formatPhoneNumber(this.phone)}}},Vt=Wt,Qt=(a("f915"),Object(g["a"])(Vt,Pt,Bt,!1,null,"3ed2c480",null)),Zt=Qt.exports,Xt=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"page-container"},[a("div",{staticClass:"page-body",class:{"attached-sections":null!==t.$store.state.orderDetails}},[t.$store.state.orderDetails?[a("section-container",{attrs:{title:"Thank you for your order!"}},[a("section",{staticClass:"confirmation-section"},[a("span",[t._v("Your confirmation number: "),a("strong",[t._v(" "+t._s(t.$store.state.orderDetails.order.confirmationNumber)+" ")])]),a("span",[t._v("Date: "),a("strong",[t._v(" "+t._s(t._f("formatDisplayDate")(t.$store.state.orderDetails.order.dateCreated))+" ")])]),a("span",[t._v(" Once your order is processed, we'll send a receipt and any related instructions to "),a("strong",[t._v(t._s(t.$store.state.orderDetails.customer.email))]),t._v(". ")])])]),a("section-container",{attrs:{title:"Invoice"}},[a("section",{staticClass:"invoice-section"},[a("section",{staticClass:"shipping-section"},[a("span",[a("strong",[t._v("Ship To")])]),a("span",[t._v(" "+t._s(t.$store.state.orderDetails.customer.customerName)+" ")]),a("span",[t._v(" "+t._s(t.$store.state.orderDetails.customer.address))]),a("span",[t._v(" "+t._s(t._f("formatPhoneNumber")(t.$store.state.orderDetails.customer.phone)))]),a("span",[t._v(" "+t._s(t.$store.state.orderDetails.customer.email))])]),a("section",{staticClass:"payment-method-section"},[a("span",[a("strong",[t._v("Payment Method")])]),a("span",[t._v(t._s(t.$store.state.orderDetails.customer.customerName))]),a("span",[a("i",{staticClass:"far fa-credit-card"}),t._v(" "+t._s(t._f("formatCcLastFour")(t.$store.state.orderDetails.customer.ccNumber))+" ")]),a("span",[t._v(" "+t._s(t._f("formatCcExpDate")(t.$store.state.orderDetails.customer.ccExpDate))+" ")])]),a("section",{staticClass:"order-summary-section"},[a("span",[a("strong",[t._v("Order Summary")])]),a("div",[a("span",[t._v("Subtotal:")]),a("span",[t._v(" "+t._s(t._f("asDollarsAndCents")(t.$store.state.orderDetails.order.amount-t.$store.state.cart.surcharge))+" ")])]),a("div",[a("span",[t._v("Shipping:")]),a("span",[t._v(" "+t._s(t._f("asDollarsAndCents")(t.$store.state.cart.surcharge))+" ")])]),a("row-separator"),a("div",[a("span",[t._v("Total:")]),a("span",[t._v(" "+t._s(t._f("asDollarsAndCents")(t.$store.state.orderDetails.order.amount))+" ")])])],1)])]),a("section-container",{attrs:{title:"Order details"}},[a("confirmation-table")],1),a("section-container",[a("section",{staticClass:"footer-section"},[a("router-link",{staticClass:"button",attrs:{to:"/category/"+t.$store.state.selectedCategoryName,tag:"button"}},[t._v(" Shop for More Experiments ")])],1)])]:a("warning-container",{attrs:{"warning-image":{src:"empty-order-details.png"},"warning-title":"You haven't placed any order yet!","warning-description":"Once you have placed your order, you can find your order confirmation here.","warning-button-route":"/category/"+t.$store.state.selectedCategoryName,"warning-button-text":"Shop Your Experiment"}})],2)])},te=[],ee=function(){var t=this,e=t.$createElement,r=t._self._c||e;return r("div",{staticClass:"confirmation-table"},[r("ul",[t._m(0),r("row-separator",{staticStyle:{"margin-top":"-1em"}}),t._l(this.$store.state.orderDetails.lineItems,(function(e,o){return r("li",{key:e.productId},[r("div",{staticClass:"confirmation-book-image"},[r("img",{attrs:{src:a("e9b6")("./"+e.bookId+".jpg"),alt:t.$store.state.orderDetails.books[o].title,width:"100px",height:"auto"}})]),r("div",{staticClass:"confirmation-book-title"},[t._v(" "+t._s(t.$store.state.orderDetails.books[o].title)+" ")]),r("div",{staticClass:"confirmation-book-price"},[r("span",{staticClass:"column-label"},[t._v("Price:")]),t._v(" "+t._s(t._f("asDollarsAndCents")(t.$store.state.orderDetails.books[o].price))+" ")]),r("div",{staticClass:"confirmation-book-quantity"},[r("span",{staticClass:"column-label"},[t._v("Quantity:")]),r("span",{staticClass:"row-book-quantity"},[t._v(t._s(e.quantity))])]),r("div",{staticClass:"subtotal"},[r("span",{staticClass:"column-label"},[t._v("Total:")]),t._v(" "+t._s(t._f("asDollarsAndCents")(e.quantity*t.$store.state.orderDetails.books[o].price))+" ")]),r("row-separator")],1)}))],2)])},ae=[function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("li",{staticClass:"confirmation-heading"},[a("div",{staticClass:"confirmation-heading-book"},[t._v("Book")]),a("div",{staticClass:"confirmation-heading-price"},[t._v("Price")]),a("div",{staticClass:"confirmation-heading-quantity"},[t._v("Quantity")]),a("div",{staticClass:"confirmation-heading-subtotal"},[t._v("Total")])])}],re={name:"ConfirmationTable",components:{RowSeparator:ht}},oe=re,ne=(a("b969"),Object(g["a"])(oe,ee,ae,!1,null,"050c321c",null)),se=ne.exports,ie={name:"Confirmation",components:{WarningContainer:At,RowSeparator:ht,SectionContainer:Et,ConfirmationTable:se}},ce=ie,le=(a("c272"),Object(g["a"])(ce,Xt,te,!1,null,"803e46f0",null)),ue=le.exports,de=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"page-container"},[a("div",{staticClass:"page-body"},[a("warning-container",{attrs:{"warning-image":{src:"page-not-found.png",height:"50px"},"warning-title":"Page not found!","warning-description":"The page you were looking for was not found.","warning-button-route":"/home","warning-button-text":"Home Page"}})],1)])},pe=[],me={name:"NotFound",components:{WarningContainer:At}},ge=me,fe=Object(g["a"])(ge,de,pe,!1,null,"5a77c29a",null),be=fe.exports;r["a"].use(T["a"]);var he=new T["a"]({mode:"history",base:"/GasserBookstoreTransact/",routes:[{path:"/home",name:"home",component:R,alias:["/","/index.html"]},{path:"/category/:name",name:"category",component:it,props:!0},{path:"/category",redirect:"/category/Arts"},{path:"/cart",name:"cart",component:Rt},{path:"/checkout",name:"checkout",component:Zt},{path:"/confirmation",name:"confirmation",component:ue},{path:"*",component:be}]}),ve=(a("aede"),a("2f62"));const _e={"http:":":8080","https:":":8443"},Ce=location.protocol+"//"+location.hostname+_e[location.protocol]+"/GasserBookstoreTransact/api";var ye={fetchCategories(){const t=Ce+"/categories";return console.log("GET from "+t),fetch(t).then(t=>{if(t.ok)return t.json();throw new Error("Network response was not ok.")}).catch(t=>{console.log("Error fetching category data",t)})},fetchSelectedCategoryBooks(t){const e=Ce+"/categories/name/"+t+"/books";return console.log("GET from "+e),fetch(e).then(t=>{if(t.ok)return t.json();throw new Error("Network response was not ok.")}).catch(t=>{throw console.log("Error fetching selected category books data",t),t})},placeOrder(t){return console.log("POSTing to "+Ce+"/orders"),fetch(Ce+"/orders",{method:"POST",body:JSON.stringify(t),headers:{"Content-Type":"application/json"}}).then(t=>{if(t.ok)return t.json();throw new Error("Network response was not ok.")})}};class ke{constructor(t){this._book=t,this._quantity=1}get book(){return this._book}get quantity(){return this._quantity}set quantity(t){this._quantity=t}get bookId(){return this._book.bookId}get price(){return this._book.price}get total(){return this._quantity*this.price}increment(){this._quantity++}decrement(){this._quantity>0&&this._quantity--}toJSON(){return{book:this._book,quantity:this._quantity}}}class xe{constructor(){this._items=[]}get numberOfItems(){return this._items.reduce((t,e)=>t+e.quantity,0)}get empty(){return this._items.length<=0}get subtotal(){return this._items.reduce((t,e)=>t+e.book.price*e.quantity,0)}get total(){return this.subtotal+this.surcharge}get surcharge(){return 500}get items(){return xe.frozenArray(this._items)}clear(){this._items=[]}addItem(t,e=1){let a=this._items.find(e=>e.book.bookId==t.bookId);if(a)a.quantity++;else{let a=new ke(t);a.quantity=e,this._items.push(a)}}update(t,e){if(e<0||e>99)return;let a=this._items.findIndex(e=>e.book.bookId==t.bookId);-1!==a&&(0!==e?this._items[a].quantity=e:this._items.splice(a,1))}toJSON(){return{items:xe.frozenArray(this._items)}}static frozenArray(t){let e=[];return Object.keys(t).forEach(a=>{e.push(t[a])}),Object.freeze(e)}}r["a"].use(ve["a"]);const $e="cart",Se="order_details";var we=new ve["a"].Store({state:{categories:[],selectedCategoryName:"Arts",selectedCategoryBooks:[],cart:new xe,loadingStatus:!1,orderDetails:null},mutations:{SET_CATEGORIES(t,e){t.categories=e},SELECT_CATEGORY(t,e){t.selectedCategoryName=e},SET_SELECTED_CATEGORY_BOOKS(t,e){t.selectedCategoryBooks=e},ADD_TO_CART(t,e){t.cart.addItem(e,1),localStorage.setItem($e,JSON.stringify(this.state.cart))},UPDATE_CART(t,{book:e,quantity:a}){t.cart.update(e,a),localStorage.setItem($e,JSON.stringify(this.state.cart))},CLEAR_CART(t){t.cart.clear(),localStorage.setItem($e,JSON.stringify(this.state.cart))},SET_CART(t,e){localStorage.setItem($e,JSON.stringify(e));let a=new xe;e.items.forEach(t=>{a.addItem(t.book,t.quantity)}),t.cart=a},SET_LOADING_STATUS(t,e){t.loadingStatus=e},CLEAR_ORDER_DETAILS(t){t.orderDetails=null,sessionStorage.setItem(Se,JSON.stringify(this.state.orderDetails))},SET_ORDER_DETAILS(t,e){t.orderDetails=e,sessionStorage.setItem(Se,JSON.stringify(this.state.orderDetails))}},actions:{fetchCategories(t){t.commit("SET_LOADING_STATUS",!0),ye.fetchCategories().then(e=>{t.commit("SET_CATEGORIES",e),t.commit("SET_LOADING_STATUS",!1)}).catch(e=>{console.log("Error: "+e),t.commit("SET_LOADING_STATUS",!1)})},selectCategory(t,e){t.commit("SELECT_CATEGORY",e)},fetchSelectedCategoryBooks(t){return t.commit("SET_LOADING_STATUS",!0),ye.fetchSelectedCategoryBooks(this.state.selectedCategoryName).then(e=>{t.commit("SET_SELECTED_CATEGORY_BOOKS",e),t.commit("SET_LOADING_STATUS",!1)}).catch(e=>{throw console.log("Error: "+e),t.commit("SET_LOADING_STATUS",!1),e})},addToCart(t,e){t.commit("ADD_TO_CART",e)},updateCart(t,{book:e,quantity:a}){t.commit("UPDATE_CART",{book:e,quantity:a})},clearCart(t){t.commit("CLEAR_CART")},placeOrder({commit:t,state:e},a){return t("CLEAR_ORDER_DETAILS"),ye.placeOrder({cart:e.cart,customerForm:a}).then(e=>{t("CLEAR_CART"),t("SET_ORDER_DETAILS",e)})}}}),Ee=a("8454"),je=a.n(Ee);r["a"].config.productionTip=!1;const De=new Intl.NumberFormat("en-US",{style:"currency",currency:"USD",minimumFractionDigits:2});r["a"].filter("asDollarsAndCents",(function(t){return De.format(t/100)})),r["a"].filter("formatCcLastFour",(function(t){return"**"+t.substring(t.length-4)})),r["a"].filter("formatCcExpDate",(function(t){const e=new Date(t);return e.getMonth()+1+"/"+e.getFullYear()})),r["a"].filter("formatDisplayDate",(function(t){const e=new Date(t),a=e.toUTCString().split("GMT")[0],r=e.toTimeString().split("(")[1].replace(")","").split(" "),o=r[0][0]+r[1][0]+r[2][0];return a+o})),r["a"].filter("formatPhoneNumber",(function(t){const e=(""+t).replace(/\D/g,""),a=e.match(/^(1|)?(\d{3})(\d{3})(\d{4})$/);if(a){var r=a[1]?"+1 ":"";return[r,"(",a[2],") ",a[3],"-",a[4]].join("")}return t})),r["a"].use(je.a),new r["a"]({router:he,store:we,render:function(t){return t(O)},created(){const t=localStorage.getItem($e),e=localStorage.getItem(Se);if(t){const e=JSON.parse(t);this.$store.commit("SET_CART",e)}if(e){const t=JSON.parse(e);this.$store.commit("SET_ORDER_DETAILS",t)}}}).$mount("#app")},"5a3c":function(t,e,a){t.exports=a.p+"img/1020.9ad05bef.jpg"},"5e05":function(t,e,a){t.exports=a.p+"img/1017.59c20859.jpg"},"604a":function(t,e,a){"use strict";a("e5b1")},"65ee":function(t,e,a){"use strict";a("2117")},"677d":function(t,e,a){t.exports=a.p+"img/1019.c66dfdc9.jpg"},"6fee":function(t,e,a){t.exports=a.p+"img/1001.3ad59916.jpg"},"707d":function(t,e,a){"use strict";a("d878")},"76c6":function(t,e,a){t.exports=a.p+"img/1013.f38d17a0.jpg"},8278:function(t,e,a){},"82dd":function(t,e,a){t.exports=a.p+"img/1010.5f143c2d.jpg"},"85ec":function(t,e,a){},8961:function(t,e,a){},"8bbf":function(t,e,a){t.exports=a.p+"img/1002.e097c192.jpg"},"8f2b":function(t,e,a){t.exports=a.p+"img/1023.001f14eb.jpg"},"93a6":function(t,e,a){t.exports=a.p+"img/1018.fbc24ee8.jpg"},"9b48":function(t,e,a){"use strict";a("bd5a")},"9c40":function(t,e,a){"use strict";a("e9e2")},"9c5c":function(t,e,a){t.exports=a.p+"img/1004.8619a783.jpg"},"9e39":function(t,e,a){t.exports=a.p+"img/1029.d65237d2.jpg"},a48a:function(t,e,a){"use strict";a("e6a1")},ad2b:function(t,e,a){var r={"./background-book.jpeg":"d487","./empty-order-details.png":"df3b","./empty-shopping-cart.png":"f643","./kitab-lab-logo-white.png":"d334","./kitab-lab-logo.png":"1c4f","./page-not-found.png":"f39f"};function o(t){var e=n(t);return a(e)}function n(t){if(!a.o(r,t)){var e=new Error("Cannot find module '"+t+"'");throw e.code="MODULE_NOT_FOUND",e}return r[t]}o.keys=function(){return Object.keys(r)},o.resolve=n,t.exports=o,o.id="ad2b"},aede:function(t,e,a){},b969:function(t,e,a){"use strict";a("fc97")},bd5a:function(t,e,a){},bdcc:function(t,e,a){t.exports=a.p+"img/1008.20f23edb.jpg"},be70:function(t,e,a){t.exports=a.p+"img/1011.9ce68ea7.jpg"},c0b7:function(t,e,a){t.exports=a.p+"img/1034.21465eca.jpg"},c21c:function(t,e,a){t.exports=a.p+"img/1006.a2ee099a.jpg"},c263:function(t,e,a){t.exports=a.p+"img/1027.11b1bd4d.jpg"},c272:function(t,e,a){"use strict";a("54d4")},cce1:function(t,e,a){t.exports=a.p+"img/1005.0eb3f915.jpg"},cda6:function(t,e,a){t.exports=a.p+"img/1015.cbae8a18.jpg"},ce4a:function(t,e,a){t.exports=a.p+"img/1031.1cd3caa2.jpg"},d084:function(t,e,a){t.exports=a.p+"img/1033.2ef96e67.jpg"},d334:function(t,e,a){t.exports=a.p+"img/kitab-lab-logo-white.73e1a38c.png"},d487:function(t,e,a){t.exports=a.p+"img/background-book.d57a119b.jpeg"},d4ad:function(t,e,a){t.exports=a.p+"img/1026.e1608b5d.jpg"},d580:function(t,e,a){"use strict";a("11bb")},d878:function(t,e,a){},d920:function(t,e,a){t.exports=a.p+"img/1021.d420bc6f.jpg"},df2c:function(t,e,a){},df3b:function(t,e,a){t.exports=a.p+"img/empty-order-details.915f1184.png"},e1af:function(t,e,a){"use strict";a("1e76")},e5b1:function(t,e,a){},e6a1:function(t,e,a){},e9b6:function(t,e,a){var r={"./1001.jpg":"6fee","./1002.jpg":"8bbf","./1003.jpg":"0200","./1004.jpg":"9c5c","./1005.jpg":"cce1","./1006.jpg":"c21c","./1007.jpg":"0051","./1008.jpg":"bdcc","./1009.jpg":"4ae7","./1010.jpg":"82dd","./1011.jpg":"be70","./1012.jpg":"0b95","./1013.jpg":"76c6","./1014.jpg":"55e2","./1015.jpg":"cda6","./1016.jpg":"f5ac","./1017.jpg":"5e05","./1018.jpg":"93a6","./1019.jpg":"677d","./1020.jpg":"5a3c","./1021.jpg":"d920","./1022.jpg":"532d","./1023.jpg":"8f2b","./1024.jpg":"365c","./1025.jpg":"5208","./1026.jpg":"d4ad","./1027.jpg":"c263","./1028.jpg":"4443","./1029.jpg":"9e39","./1030.jpg":"1e44","./1031.jpg":"ce4a","./1032.jpg":"5068","./1033.jpg":"d084","./1034.jpg":"c0b7"};function o(t){var e=n(t);return a(e)}function n(t){if(!a.o(r,t)){var e=new Error("Cannot find module '"+t+"'");throw e.code="MODULE_NOT_FOUND",e}return r[t]}o.keys=function(){return Object.keys(r)},o.resolve=n,t.exports=o,o.id="e9b6"},e9e2:function(t,e,a){},eb13:function(t,e,a){"use strict";a("44a1")},f39f:function(t,e,a){t.exports=a.p+"img/page-not-found.8ffad29a.png"},f5ac:function(t,e,a){t.exports=a.p+"img/1016.828d1779.jpg"},f643:function(t,e,a){t.exports=a.p+"img/empty-shopping-cart.18b5d44b.png"},f915:function(t,e,a){"use strict";a("df2c")},fc97:function(t,e,a){}});
//# sourceMappingURL=app.ef3233df.js.map
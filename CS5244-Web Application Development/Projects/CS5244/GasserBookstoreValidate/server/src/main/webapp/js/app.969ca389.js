(function(t){function e(e){for(var o,n,i=e[0],c=e[1],l=e[2],d=0,p=[];d<i.length;d++)n=i[d],Object.prototype.hasOwnProperty.call(r,n)&&r[n]&&p.push(r[n][0]),r[n]=0;for(o in c)Object.prototype.hasOwnProperty.call(c,o)&&(t[o]=c[o]);u&&u(e);while(p.length)p.shift()();return s.push.apply(s,l||[]),a()}function a(){for(var t,e=0;e<s.length;e++){for(var a=s[e],o=!0,i=1;i<a.length;i++){var c=a[i];0!==r[c]&&(o=!1)}o&&(s.splice(e--,1),t=n(n.s=a[0]))}return t}var o={},r={app:0},s=[];function n(e){if(o[e])return o[e].exports;var a=o[e]={i:e,l:!1,exports:{}};return t[e].call(a.exports,a,a.exports,n),a.l=!0,a.exports}n.m=t,n.c=o,n.d=function(t,e,a){n.o(t,e)||Object.defineProperty(t,e,{enumerable:!0,get:a})},n.r=function(t){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(t,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(t,"__esModule",{value:!0})},n.t=function(t,e){if(1&e&&(t=n(t)),8&e)return t;if(4&e&&"object"===typeof t&&t&&t.__esModule)return t;var a=Object.create(null);if(n.r(a),Object.defineProperty(a,"default",{enumerable:!0,value:t}),2&e&&"string"!=typeof t)for(var o in t)n.d(a,o,function(e){return t[e]}.bind(null,o));return a},n.n=function(t){var e=t&&t.__esModule?function(){return t["default"]}:function(){return t};return n.d(e,"a",e),e},n.o=function(t,e){return Object.prototype.hasOwnProperty.call(t,e)},n.p="/GasserBookstoreValidate/";var i=window["webpackJsonp"]=window["webpackJsonp"]||[],c=i.push.bind(i);i.push=e,i=i.slice();for(var l=0;l<i.length;l++)e(i[l]);var u=c;s.push([0,"chunk-vendors"]),a()})({0:function(t,e,a){t.exports=a("56d7")},"0051":function(t,e,a){t.exports=a.p+"img/1007.df5f7270.jpg"},"0200":function(t,e,a){t.exports=a.p+"img/1003.f329eb54.jpg"},"034f":function(t,e,a){"use strict";a("85ec")},"0b95":function(t,e,a){t.exports=a.p+"img/1012.9948e2f3.jpg"},1507:function(t,e,a){"use strict";a("1aa9")},"1aa9":function(t,e,a){},"1b3d":function(t,e,a){"use strict";a("8315")},"1c4f":function(t,e,a){t.exports=a.p+"img/kitab-lab-logo.16d96d9d.png"},"1e44":function(t,e,a){t.exports=a.p+"img/1030.855d0f91.jpg"},"1e76":function(t,e,a){},2117:function(t,e,a){},"2be4":function(t,e,a){"use strict";a("fd12")},"365c":function(t,e,a){t.exports=a.p+"img/1024.e2f1bcbe.jpg"},"3c77":function(t,e,a){"use strict";a("47a1")},4443:function(t,e,a){t.exports=a.p+"img/1028.e2f1062a.jpg"},"44a1":function(t,e,a){},"44ff":function(t,e,a){"use strict";a("8961")},"47a1":function(t,e,a){},"4ae7":function(t,e,a){t.exports=a.p+"img/1009.4520843c.jpg"},"4fba":function(t,e,a){},5068:function(t,e,a){t.exports=a.p+"img/1032.b3d5653a.jpg"},5208:function(t,e,a){t.exports=a.p+"img/1025.657fb6b7.jpg"},"532d":function(t,e,a){t.exports=a.p+"img/1022.f8dfc9c0.jpg"},"55e2":function(t,e,a){t.exports=a.p+"img/1014.234cd01d.jpg"},"56d7":function(t,e,a){"use strict";a.r(e);var o=a("2b0e"),r=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{attrs:{id:"app"}},[a("app-header"),a("router-view",{key:t.$route.fullPath}),a("app-footer")],1)},s=[],n=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("header",{staticClass:"container header-with-shadow"},[o("section",{staticClass:"bookstore-logo-title flex-centered-container"},[o("router-link",{attrs:{to:"/"}},[o("img",{staticClass:"logo-image",attrs:{src:a("1c4f"),alt:"Kitab Lab Logo",width:"57px",height:"auto"}})]),o("span",{staticClass:"logo-title-divider"}),o("router-link",{staticClass:"logo-text",attrs:{to:"/",tag:"h1"}},[t._v("KITAB LAB ")])],1),o("section",{staticClass:"search-bar flex-centered-container"},[o("form",{staticClass:"flex-centered-container"},[o("input",{staticClass:"search-bar-text",attrs:{type:"text",placeholder:"Search books"}}),o("br"),o("router-link",{staticClass:"button icon-inside-button search-bar-button",attrs:{to:"/category/"+t.$store.state.selectedCategoryName,tag:"li"}},[o("i",{staticClass:"fas fa-search"})])],1)]),o("section",{staticClass:"header-dropdown-and-cart flex-centered-container"},[o("header-dropdown-menu"),o("router-link",{staticClass:"button icon-only-button",attrs:{to:"/cart",tag:"button"}},[o("i",{staticClass:"fas fa-shopping-cart icon-only-button-icon"},[o("span",{staticClass:"icon-only-button-text"},[t._v(t._s(t.$store.state.cart.numberOfItems))])])]),o("button",{staticClass:"button",staticStyle:{padding:"7px"}},[t._v("GA")])],1)])},i=[],c=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"header-dropdown"},[t._m(0),a("ul",t._l(t.$store.state.categories,(function(e){return a("router-link",{key:e.categoryId,attrs:{to:"/category/"+e.name,tag:"li"}},[t._v(" "+t._s(e.name)+" ")])})),1)])},l=[function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("button",{staticClass:"button categories-button"},[t._v(" Categories "),a("i",{staticClass:"fas fa-caret-right"})])}],u={name:"HeaderDropdownMenu"},d=u,p=(a("1507"),a("2877")),m=Object(p["a"])(d,c,l,!1,null,"f3bfa0b4",null),g=m.exports,f={name:"AppHeader",components:{HeaderDropdownMenu:g},component:{HeaderDropdownMenu:g}},b=f,v=(a("44ff"),Object(p["a"])(b,n,i,!1,null,"4bad0cf1",null)),h=v.exports,_=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("footer",{staticClass:"container"},[t._m(0),a("section",{staticClass:"links"},[a("router-link",{attrs:{to:"/"}},[t._v("About")]),a("router-link",{attrs:{to:"/"}},[t._v("Directions")]),a("router-link",{attrs:{to:"/"}},[t._v("Contact")])],1),a("section",{staticClass:"social-media-icons"},[a("router-link",{staticClass:"icon-only-button-icon",attrs:{to:"/"}},[a("i",{staticClass:"fab fa-facebook"})]),a("router-link",{staticClass:"icon-only-button-icon",attrs:{to:"/"}},[a("i",{staticClass:"fab fa-twitter"})]),a("router-link",{staticClass:"icon-only-button-icon",attrs:{to:"/"}},[a("i",{staticClass:"fab fa-instagram"})])],1)])},C=[function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("section",{staticClass:"copyright"},[o("i",{staticClass:"far fa-copyright"}),o("span",[t._v("2021")]),o("img",{attrs:{src:a("d334"),alt:"Kitab Lab Logo",width:"57px",height:"auto"}})])}],y={name:"AppFooter"},k=y,x=(a("707d"),Object(p["a"])(k,_,C,!1,null,"5d62bf80",null)),$=x.exports,j={name:"App",components:{AppHeader:h,AppFooter:$},created:function(){this.$store.dispatch("fetchCategories")}},E=j,S=(a("034f"),Object(p["a"])(E,r,s,!1,null,null,null)),O=S.exports,w=a("8c4f"),A=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"home-page content-container"},[a("section",{staticClass:"welcome-text flow-content container"},[a("h2",[t._v("KITAB LAB")]),a("p",[t._v("Where every book is a new experiment.")]),a("router-link",{staticClass:"button shop-button",attrs:{to:"/category/"+t.$store.state.selectedCategoryName,tag:"li"}},[t._v(" Shop Your Experiment ")])],1)])},N=[],T={name:"Home"},q=T,I=(a("1b3d"),Object(p["a"])(q,A,N,!1,null,"572e096e",null)),D=I.exports,L=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"content-container"},[a("category-nav"),t.$store.state.loadingStatus?a("div",{staticClass:"loading-spinner-container"},[a("vue-spinner",{attrs:{"line-bg-color":"#D4D5D8","line-fg-color":"#2B5658"}})],1):a("category-book-list")],1)},P=[],B=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("nav",{staticClass:"category-nav"},[a("ul",{staticClass:"category-buttons"},[t._l(t.$store.state.categories,(function(e){return[e.name===t.$store.state.selectedCategoryName?a("router-link",{key:e.categoryId,staticClass:"button selected-category-button",attrs:{to:"/category/"+e.name,tag:"li"}},[t._v(" "+t._s(e.name)+" ")]):a("router-link",{key:e.categoryId,staticClass:"button unselected-category-button",attrs:{to:"/category/"+e.name,tag:"li"}},[t._v(" "+t._s(e.name)+" ")])]}))],2)])},R=[],z={name:"CategoryNav"},G=z,M=(a("65ee"),Object(p["a"])(G,B,R,!1,null,"37cab5d0",null)),F=M.exports,J=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("ul",t._l(t.$store.state.selectedCategoryBooks,(function(t){return a("category-book-list-item",{key:t.bookId,attrs:{book:t}})})),1)},Y=[],U=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("li",{staticClass:"book-box"},[o("div",{staticClass:"book-image"},[o("router-link",{attrs:{to:"#"}},[o("img",{attrs:{src:a("e9b6")("./"+t.book.bookId+".jpg"),alt:t.book.title}})])],1),o("div",{staticClass:"book-title"},[t._v(t._s(t.book.title))]),o("div",{staticClass:"book-author"},[t._v("by "+t._s(t.book.author))]),o("div",{staticClass:"book-price"},[t._v(t._s(t._f("asDollarsAndCents")(t.book.price)))]),o("button",{staticClass:"button add-to-cart-button",on:{click:function(e){return t.addToCart(t.book)}}},[t._v(" Add to Cart ")]),t.book.isPublic?o("button",{staticClass:"button read-now-button"},[o("i",{staticClass:"fab fa-readme"})]):t._e()])},K=[],H={name:"categoryBookListItem",props:{book:{type:Object,required:!0}},methods:{addToCart(t){this.$store.dispatch("addToCart",t)}}},V=H,Q=(a("e1af"),Object(p["a"])(V,U,K,!1,null,"5b1aa158",null)),W=Q.exports,Z={name:"CategoryBookList",components:{CategoryBookListItem:W}},X=Z,tt=(a("eb13"),Object(p["a"])(X,J,Y,!1,null,"1d5976d8",null)),et=tt.exports,at=a("5b7e"),ot=a.n(at),rt={name:"category",components:{CategoryNav:F,CategoryBookList:et,vueSpinner:ot.a},created:function(){this.$store.dispatch("selectCategory",this.$route.params.name),this.$store.dispatch("fetchSelectedCategoryBooks")}},st=rt,nt=Object(p["a"])(st,L,P,!1,null,"41de1400",null),it=nt.exports,ct=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"cart-page content-container"},[t.$store.state.cart.empty?a("EmptyCart",{attrs:{text:"You need an item to checkout."}}):a("section",{staticClass:"non-empty-cart-page"},[a("div",{staticClass:"page-title-container"},[a("h2",{staticClass:"page-title"},[t._v("Here's What You're Getting!")]),a("div",{staticClass:"row-separator"}),a("div",{staticClass:"page-description"},[t._v(" You have "+t._s(t.$store.state.cart.numberOfItems)+" "),1===t.$store.state.cart.numberOfItems?a("span",[t._v("item")]):a("span",[t._v("items")]),t._v(" in your cart. ")])]),a("cart-table"),a("div",{staticClass:"cart-footer"},[a("button",{staticClass:"button link-like-button clear-button",on:{click:t.clearCart}},[t._v(" Clear the Cart ")]),a("div",{staticClass:"cart-total"},[a("span",{staticClass:"subtotal"},[t._v("Subtotal: "+t._s(t._f("asDollarsAndCents")(t.$store.state.cart.subtotal)))])])]),a("div",{staticClass:"cart-buttons"},[a("router-link",{staticClass:"button secondary-button",attrs:{to:"/category/"+t.$store.state.selectedCategoryName,tag:"button"}},[t._v(" Continue Shopping ")]),a("router-link",{staticClass:"button",attrs:{to:"/checkout",tag:"button"}},[t._v(" Proceed to Checkout ")])],1)],1)],1)},lt=[],ut=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("div",{staticClass:"cart-table"},[o("ul",[t._m(0),o("div",{staticClass:"row-separator header-row-separator"}),t._l(t.$store.state.cart.items,(function(e){return o("li",{key:e.book.bookId},[o("div",{staticClass:"cart-book-image"},[o("img",{attrs:{src:a("e9b6")("./"+e.book.bookId+".jpg"),alt:e.book.title,width:"100px",height:"auto"}})]),o("div",{staticClass:"cart-book-title"},[t._v(t._s(e.book.title))]),o("div",{staticClass:"cart-book-price"},[o("span",{staticClass:"column-label"},[t._v("Price:")]),t._v(" "+t._s(t._f("asDollarsAndCents")(e.book.price))+" ")]),o("div",{staticClass:"cart-book-quantity"},[o("span",{staticClass:"column-label"},[t._v("Quantity:")]),o("button",{staticClass:"button icon-only-button dec-button",on:{click:function(a){return t.updateCart(e.book,e.quantity-1)}}},[o("i",{staticClass:"fas fa-minus-circle"})]),o("span",{staticClass:"row-book-quantity"},[t._v(t._s(e.quantity))]),o("button",{staticClass:"button icon-only-button inc-button",on:{click:function(a){return t.updateCart(e.book,e.quantity+1)}}},[o("i",{staticClass:"fas fa-plus-circle"})])]),o("div",{staticClass:"subtotal"},[o("span",{staticClass:"column-label"},[t._v("Total:")]),t._v(" "+t._s(t._f("asDollarsAndCents")(e.quantity*e.book.price))+" ")]),o("div",{staticClass:"row-separator"})])}))],2)])},dt=[function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("li",{staticClass:"cart-heading"},[a("div",{staticClass:"cart-heading-book"},[t._v("Book")]),a("div",{staticClass:"cart-heading-price"},[t._v("Price")]),a("div",{staticClass:"cart-heading-quantity"},[t._v("Quantity")]),a("div",{staticClass:"cart-heading-subtotal"},[t._v("Total")])])}],pt={name:"CartTable",methods:{updateCart(t,e){this.$store.dispatch("updateCart",{book:t,quantity:e})}}},mt=pt,gt=(a("9b3a"),Object(p["a"])(mt,ut,dt,!1,null,"d2d6e628",null)),ft=gt.exports,bt=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("section",{staticClass:"empty-cart-page"},[a("span",{staticClass:"empty-cart-text"},[t._v("Your cart is currently empty!")]),a("div",{staticClass:"cart-buttons"},[a("router-link",{staticClass:"button secondary-button",attrs:{to:"/category/"+t.$store.state.selectedCategoryName,tag:"button"}},[t._v(" Continue Shopping ")])],1)])},vt=[],ht={name:"EmptyCart"},_t=ht,Ct=(a("3c77"),Object(p["a"])(_t,bt,vt,!1,null,"3977f24e",null)),yt=Ct.exports,kt={name:"Cart",components:{EmptyCart:yt,CartTable:ft},methods:{clearCart(){this.$store.dispatch("clearCart")}}},xt=kt,$t=(a("2be4"),Object(p["a"])(xt,ct,lt,!1,null,"9833cc1c",null)),jt=$t.exports,Et=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"content-container"},[t.cart.empty?a("EmptyCart"):a("div",{staticClass:"checkout-page"},[t.cart.empty?t._e():a("section",{staticClass:"checkout-page-body"},[a("section",{staticClass:"checkout-container"},[a("h2",{staticClass:"checkout-title"},[t._v("Checkout")]),a("div",{staticClass:"row-separator"}),a("form",{attrs:{id:"checkout-form"},on:{submit:function(e){return e.preventDefault(),t.submitOrder.apply(null,arguments)}}},[a("div",[a("label",{attrs:{for:"name"}},[t._v("Name")]),a("div",[a("input",{directives:[{name:"model",rawName:"v-model.lazy",value:t.$v.name.$model,expression:"$v.name.$model",modifiers:{lazy:!0}}],attrs:{type:"text",size:"20",id:"name",name:"name",placeholder:"Full Name"},domProps:{value:t.$v.name.$model},on:{change:function(e){return t.$set(t.$v.name,"$model",e.target.value)}}}),t.$v.name.$error?[t.$v.name.required?t.$v.name.minLength?t.$v.name.maxLength?a("span",{staticClass:"error"},[t._v(" An unexpected error occurred. ")]):a("span",{staticClass:"error"},[t._v(" Name can have at most "+t._s(t.$v.name.$params.maxLength.max)+" letters. ")]):a("span",{staticClass:"error"},[t._v(" Name must have at least "+t._s(t.$v.name.$params.minLength.min)+" letters. ")]):a("span",{staticClass:"error"},[t._v("Name is required")])]:t._e()],2)]),a("div",[a("label",{attrs:{for:"name"}},[t._v("Address")]),a("div",[a("input",{directives:[{name:"model",rawName:"v-model.lazy",value:t.$v.address.$model,expression:"$v.address.$model",modifiers:{lazy:!0}}],attrs:{type:"text",size:"20",id:"address",name:"address",placeholder:"Street, City, State, Zip Code"},domProps:{value:t.$v.address.$model},on:{change:function(e){return t.$set(t.$v.address,"$model",e.target.value)}}}),t.$v.address.$error?[t.$v.address.required?t.$v.address.minLength?t.$v.address.maxLength?a("span",{staticClass:"error"},[t._v(" An unexpected error occurred. ")]):a("span",{staticClass:"error"},[t._v(" Address can have at most "+t._s(t.$v.address.$params.maxLength.max)+" letters. ")]):a("span",{staticClass:"error"},[t._v(" Address must have at least "+t._s(t.$v.address.$params.minLength.min)+" letters. ")]):a("span",{staticClass:"error"},[t._v("Address is required")])]:t._e()],2)]),a("div",[a("label",{attrs:{for:"phone"}},[t._v("Phone")]),a("div",[a("input",{directives:[{name:"model",rawName:"v-model.lazy",value:t.$v.phone.$model,expression:"$v.phone.$model",modifiers:{lazy:!0}}],staticClass:"textField",attrs:{type:"text",size:"20",id:"phone",name:"phone",placeholder:"Phone Number"},domProps:{value:t.$v.phone.$model},on:{change:function(e){return t.$set(t.$v.phone,"$model",e.target.value)}}}),t.$v.phone.$error?[t.$v.phone.required?t.$v.phone.isValidPhone?a("span",{staticClass:"error"},[t._v(" An unexpected error occurred. ")]):a("span",{staticClass:"error"},[t._v("Please enter a valid phone number.")]):a("span",{staticClass:"error"},[t._v("Phone is required")])]:t._e()],2)]),a("div",[a("label",{attrs:{for:"email"}},[t._v("Email")]),a("div",[a("input",{directives:[{name:"model",rawName:"v-model.lazy",value:t.$v.email.$model,expression:"$v.email.$model",modifiers:{lazy:!0}}],attrs:{type:"text",size:"20",id:"email",name:"email",placeholder:"Email"},domProps:{value:t.$v.email.$model},on:{change:function(e){return t.$set(t.$v.email,"$model",e.target.value)}}}),t.$v.email.$error?[t.$v.email.required?t.$v.email.email?a("span",{staticClass:"error"},[t._v(" An unexpected error occurred. ")]):a("span",{staticClass:"error"},[t._v("Please enter a valid email.")]):a("span",{staticClass:"error"},[t._v("Email is required")])]:t._e()],2)]),a("div",[a("label",{attrs:{for:"ccNumber"}},[t._v("Credit Card")]),a("div",[a("input",{directives:[{name:"model",rawName:"v-model.lazy",value:t.$v.ccNumber.$model,expression:"$v.ccNumber.$model",modifiers:{lazy:!0}}],attrs:{type:"text",size:"20",id:"ccNumber",name:"ccNumber",placeholder:"xxxx-xxxx-xxxx-xxxx"},domProps:{value:t.$v.ccNumber.$model},on:{change:function(e){return t.$set(t.$v.ccNumber,"$model",e.target.value)}}}),t.$v.ccNumber.$error?[t.$v.ccNumber.required?t.$v.ccNumber.isValidCreditCard?a("span",{staticClass:"error"},[t._v(" An unexpected error occurred. ")]):a("span",{staticClass:"error"},[t._v("Please enter a valid credit card.")]):a("span",{staticClass:"error"},[t._v("Credit card is required")])]:t._e()],2)]),a("div",[a("label",[t._v("Exp. Date")]),a("div",{staticClass:"expiration-date-input-container"},[a("select",{directives:[{name:"model",rawName:"v-model",value:t.ccExpiryMonth,expression:"ccExpiryMonth"}],on:{change:function(e){var a=Array.prototype.filter.call(e.target.options,(function(t){return t.selected})).map((function(t){var e="_value"in t?t._value:t.value;return e}));t.ccExpiryMonth=e.target.multiple?a:a[0]}}},t._l(t.months,(function(e,o){return a("option",{key:o,domProps:{value:o+1}},[t._v(" "+t._s(e)+" ("+t._s(o+1)+") ")])})),0),a("select",t._l(15,(function(e,o){return a("option",{key:o},[t._v(" "+t._s(t.yearFrom(o))+" ")])})),0)])])])]),a("section",{staticClass:"checkout-summary-container"},[a("div",{staticClass:"checkout-summary-header"},[a("h2",{staticClass:"checkout-title"},[t._v(" "+t._s(t.$store.state.cart.numberOfItems)+" "),1===t.$store.state.cart.numberOfItems?a("span",[t._v("item")]):a("span",[t._v("items")])]),a("router-link",{staticClass:"button link-like-button edit-cart-button",attrs:{to:"/cart",tag:"button"}},[t._v(" Edit Cart ")])],1),a("div",{staticClass:"row-separator"}),t.cart.empty?t._e():a("div",{staticClass:"checkout-summary"},[a("div",[a("div",{staticClass:"bold-text"},[a("span",[t._v("Subtotal")]),a("span",[t._v(t._s(t._f("asDollarsAndCents")(t.cart.subtotal)))])]),a("div",[a("span",[t._v("Shipping")]),a("span",[t._v(t._s(t._f("asDollarsAndCents")(t.cart.surcharge)))])]),a("div",{staticClass:"row-separator"}),a("div",{staticClass:"bold-text"},[a("span",[t._v("Total")]),a("span",[t._v(t._s(t._f("asDollarsAndCents")(t.cart.subtotal+t.cart.surcharge)))])])]),a("button",{staticClass:"button",attrs:{type:"submit",name:"submit",disabled:"PENDING"==t.checkoutStatus,form:"checkout-form"}},[t._v(" Complete Purchase ")])])])]),a("section",{directives:[{name:"show",rawName:"v-show",value:""!=t.checkoutStatus,expression:"checkoutStatus != ''"}],staticClass:"checkoutStatusBox"},["ERROR"==t.checkoutStatus?a("div",[t._v(" Error: Please fix the problems above and try again. ")]):"PENDING"==t.checkoutStatus?a("div",[t._v("Processing...")]):"OK"==t.checkoutStatus?a("div",[t._v("Order placed...")]):a("div",[t._v(" An unexpected error occurred, please try again. ")])])])],1)},St=[],Ot=a("b5ae"),wt=a("6d96"),At=a.n(wt),Nt=a("5d75"),Tt=a.n(Nt),qt=a("7a9e"),It=a.n(qt);const Dt=t=>At()(t,"en-US"),Lt=t=>It()(t);var Pt={components:{EmptyCart:yt},data(){return{name:"",address:"",phone:"",email:"",ccNumber:"",ccExpiryMonth:(new Date).getMonth()+1,ccExpiryYear:(new Date).getFullYear(),checkoutStatus:""}},validations:{name:{required:Ot["required"],minLength:Object(Ot["minLength"])(4),maxLength:Object(Ot["maxLength"])(45)},address:{required:Ot["required"],minLength:Object(Ot["minLength"])(4),maxLength:Object(Ot["maxLength"])(45)},phone:{required:Ot["required"],isValidPhone:Dt},email:{required:Ot["required"],email:Tt.a},ccNumber:{required:Ot["required"],isValidCreditCard:Lt}},computed:{cart(){return this.$store.state.cart},months(){return["January","February","March","April","May","June","July","August","September","October","November","December"]}},methods:{submitOrder(){console.log("Submit order"),this.$v.$touch(),this.$v.$invalid?this.checkoutStatus="ERROR":(this.checkoutStatus="PENDING",setTimeout(()=>{this.checkoutStatus="OK",setTimeout(()=>{this.$router.push({name:"confirmation"})},1e3)},1e3))},yearFrom(t){return(new Date).getFullYear()+t}}},Bt=Pt,Rt=(a("819b"),Object(p["a"])(Bt,Et,St,!1,null,"a87e457a",null)),zt=Rt.exports,Gt=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"confirmation-container content-container"},[a("router-link",{staticClass:"button",attrs:{to:"/category/"+t.$store.state.selectedCategoryName,tag:"button"}},[t._v(" Shop for More Experiments ")])],1)},Mt=[],Ft={name:"Confirmation"},Jt=Ft,Yt=(a("6d00"),Object(p["a"])(Jt,Gt,Mt,!1,null,"1510f514",null)),Ut=Yt.exports;o["a"].use(w["a"]);var Kt=new w["a"]({mode:"history",base:"/GasserBookstoreValidate/",routes:[{path:"/",name:"home",component:D},{path:"/category/:name",name:"category",component:it,props:!0},{path:"/cart",name:"cart",component:jt},{path:"/checkout",name:"checkout",component:zt},{path:"/confirmation",name:"confirmation",component:Ut}]}),Ht=(a("aede"),a("2f62"));const Vt={"http:":":8080","https:":":8443"},Qt=location.protocol+"//"+location.hostname+Vt[location.protocol]+"/GasserBookstoreValidate/api";var Wt={fetchCategories(){const t=Qt+"/categories";return console.log("GET from "+t),fetch(t).then(t=>{if(t.ok)return t.json();throw new Error("Network response was not ok.")}).catch(t=>{console.log("Error fetching category data",t)})},fetchSelectedCategoryBooks(t){const e=Qt+"/categories/name/"+t+"/books";return console.log("GET from "+e),fetch(e).then(t=>{if(t.ok)return t.json();throw new Error("Network response was not ok.")}).catch(t=>{console.log("Error fetching selected category books data",t)})}};class Zt{constructor(t){this._book=t,this._quantity=1}get book(){return this._book}get quantity(){return this._quantity}set quantity(t){this._quantity=t}get bookId(){return this._book.bookId}get price(){return this._book.price}get total(){return this._quantity*this.price}increment(){this._quantity++}decrement(){this._quantity>0&&this._quantity--}toJSON(){return{book:this._book,quantity:this._quantity}}}class Xt{constructor(){this._items=[]}get numberOfItems(){return this._items.reduce((t,e)=>t+e.quantity,0)}get empty(){return this._items.length<=0}get subtotal(){return this._items.reduce((t,e)=>t+e.book.price*e.quantity,0)}get total(){return this.subtotal+this.surcharge}get surcharge(){return 500}get items(){return Xt.frozenArray(this._items)}clear(){this._items=[]}addItem(t,e=1){let a=this._items.find(e=>e.book.bookId==t.bookId);if(a)a.quantity++;else{let a=new Zt(t);a.quantity=e,this._items.push(a)}}update(t,e){if(e<0||e>99)return;let a=this._items.findIndex(e=>e.book.bookId==t.bookId);-1!==a&&(0!==e?this._items[a].quantity=e:this._items.splice(a,1))}toJSON(){return{items:Xt.frozenArray(this._items)}}static frozenArray(t){let e=[];return Object.keys(t).forEach(a=>{e.push(t[a])}),Object.freeze(e)}}o["a"].use(Ht["a"]);const te="";var ee=new Ht["a"].Store({state:{categories:[],selectedCategoryName:"Arts",selectedCategoryBooks:[],cart:new Xt,loadingStatus:!1},mutations:{SET_CATEGORIES(t,e){t.categories=e},SELECT_CATEGORY(t,e){t.selectedCategoryName=e},SET_SELECTED_CATEGORY_BOOKS(t,e){t.selectedCategoryBooks=e},ADD_TO_CART(t,e){t.cart.addItem(e,1),localStorage.setItem(te,JSON.stringify(this.state.cart))},UPDATE_CART(t,{book:e,quantity:a}){t.cart.update(e,a),localStorage.setItem(te,JSON.stringify(this.state.cart))},CLEAR_CART(t){t.cart.clear(),localStorage.setItem(te,JSON.stringify(this.state.cart))},SET_CART(t,e){localStorage.setItem(te,JSON.stringify(e));let a=new Xt;e.items.forEach(t=>{a.addItem(t.book,t.quantity)}),t.cart=a},SET_LOADING_STATUS(t,e){t.loadingStatus=e}},actions:{fetchCategories(t){Wt.fetchCategories().then(e=>{console.log("Categories: ",e),t.commit("SET_CATEGORIES",e)}).catch(t=>{console.log("Error: "+t)})},selectCategory(t,e){t.commit("SELECT_CATEGORY",e)},fetchSelectedCategoryBooks(t){t.commit("SET_LOADING_STATUS",!0),Wt.fetchSelectedCategoryBooks(this.state.selectedCategoryName).then(e=>{console.log("Books: ",e),t.commit("SET_SELECTED_CATEGORY_BOOKS",e),t.commit("SET_LOADING_STATUS",!1)}).catch(t=>{console.log("Error: "+t)})},addToCart(t,e){t.commit("ADD_TO_CART",e)},updateCart(t,{book:e,quantity:a}){t.commit("UPDATE_CART",{book:e,quantity:a})},clearCart(t){t.commit("CLEAR_CART")}}}),ae=a("1dce"),oe=a.n(ae),re=a("558c"),se=a.n(re);o["a"].config.productionTip=!1;const ne=new Intl.NumberFormat("en-US",{style:"currency",currency:"USD",minimumFractionDigits:2});o["a"].filter("asDollarsAndCents",(function(t){return ne.format(t/100)})),o["a"].use(oe.a),o["a"].use(se.a),new o["a"]({router:Kt,store:ee,render:function(t){return t(O)},created(){const t=localStorage.getItem(te);if(t){const e=JSON.parse(t);this.$store.commit("SET_CART",e)}}}).$mount("#app")},"5a3c":function(t,e,a){t.exports=a.p+"img/1020.9ad05bef.jpg"},"5e05":function(t,e,a){t.exports=a.p+"img/1017.59c20859.jpg"},"65ee":function(t,e,a){"use strict";a("2117")},"677d":function(t,e,a){t.exports=a.p+"img/1019.c66dfdc9.jpg"},"69da":function(t,e,a){},"6d00":function(t,e,a){"use strict";a("69da")},"6fee":function(t,e,a){t.exports=a.p+"img/1001.3ad59916.jpg"},"707d":function(t,e,a){"use strict";a("d878")},"76c6":function(t,e,a){t.exports=a.p+"img/1013.f38d17a0.jpg"},"7a83":function(t,e,a){},"819b":function(t,e,a){"use strict";a("7a83")},"82dd":function(t,e,a){t.exports=a.p+"img/1010.5f143c2d.jpg"},8315:function(t,e,a){},"85ec":function(t,e,a){},8961:function(t,e,a){},"8bbf":function(t,e,a){t.exports=a.p+"img/1002.e097c192.jpg"},"8f2b":function(t,e,a){t.exports=a.p+"img/1023.001f14eb.jpg"},"93a6":function(t,e,a){t.exports=a.p+"img/1018.fbc24ee8.jpg"},"9b3a":function(t,e,a){"use strict";a("4fba")},"9c5c":function(t,e,a){t.exports=a.p+"img/1004.8619a783.jpg"},"9e39":function(t,e,a){t.exports=a.p+"img/1029.d65237d2.jpg"},aede:function(t,e,a){},bdcc:function(t,e,a){t.exports=a.p+"img/1008.20f23edb.jpg"},be70:function(t,e,a){t.exports=a.p+"img/1011.9ce68ea7.jpg"},c0b7:function(t,e,a){t.exports=a.p+"img/1034.21465eca.jpg"},c21c:function(t,e,a){t.exports=a.p+"img/1006.a2ee099a.jpg"},c263:function(t,e,a){t.exports=a.p+"img/1027.11b1bd4d.jpg"},cce1:function(t,e,a){t.exports=a.p+"img/1005.0eb3f915.jpg"},cda6:function(t,e,a){t.exports=a.p+"img/1015.cbae8a18.jpg"},ce4a:function(t,e,a){t.exports=a.p+"img/1031.1cd3caa2.jpg"},d084:function(t,e,a){t.exports=a.p+"img/1033.2ef96e67.jpg"},d334:function(t,e,a){t.exports=a.p+"img/kitab-lab-logo-white.73e1a38c.png"},d4ad:function(t,e,a){t.exports=a.p+"img/1026.e1608b5d.jpg"},d878:function(t,e,a){},d920:function(t,e,a){t.exports=a.p+"img/1021.d420bc6f.jpg"},e1af:function(t,e,a){"use strict";a("1e76")},e9b6:function(t,e,a){var o={"./1001.jpg":"6fee","./1002.jpg":"8bbf","./1003.jpg":"0200","./1004.jpg":"9c5c","./1005.jpg":"cce1","./1006.jpg":"c21c","./1007.jpg":"0051","./1008.jpg":"bdcc","./1009.jpg":"4ae7","./1010.jpg":"82dd","./1011.jpg":"be70","./1012.jpg":"0b95","./1013.jpg":"76c6","./1014.jpg":"55e2","./1015.jpg":"cda6","./1016.jpg":"f5ac","./1017.jpg":"5e05","./1018.jpg":"93a6","./1019.jpg":"677d","./1020.jpg":"5a3c","./1021.jpg":"d920","./1022.jpg":"532d","./1023.jpg":"8f2b","./1024.jpg":"365c","./1025.jpg":"5208","./1026.jpg":"d4ad","./1027.jpg":"c263","./1028.jpg":"4443","./1029.jpg":"9e39","./1030.jpg":"1e44","./1031.jpg":"ce4a","./1032.jpg":"5068","./1033.jpg":"d084","./1034.jpg":"c0b7"};function r(t){var e=s(t);return a(e)}function s(t){if(!a.o(o,t)){var e=new Error("Cannot find module '"+t+"'");throw e.code="MODULE_NOT_FOUND",e}return o[t]}r.keys=function(){return Object.keys(o)},r.resolve=s,t.exports=r,r.id="e9b6"},eb13:function(t,e,a){"use strict";a("44a1")},f5ac:function(t,e,a){t.exports=a.p+"img/1016.828d1779.jpg"},fd12:function(t,e,a){}});
//# sourceMappingURL=app.969ca389.js.map
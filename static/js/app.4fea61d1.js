(function(t){function e(e){for(var n,s,i=e[0],l=e[1],c=e[2],d=0,f=[];d<i.length;d++)s=i[d],Object.prototype.hasOwnProperty.call(a,s)&&a[s]&&f.push(a[s][0]),a[s]=0;for(n in l)Object.prototype.hasOwnProperty.call(l,n)&&(t[n]=l[n]);u&&u(e);while(f.length)f.shift()();return r.push.apply(r,c||[]),o()}function o(){for(var t,e=0;e<r.length;e++){for(var o=r[e],n=!0,i=1;i<o.length;i++){var l=o[i];0!==a[l]&&(n=!1)}n&&(r.splice(e--,1),t=s(s.s=o[0]))}return t}var n={},a={app:0},r=[];function s(e){if(n[e])return n[e].exports;var o=n[e]={i:e,l:!1,exports:{}};return t[e].call(o.exports,o,o.exports,s),o.l=!0,o.exports}s.m=t,s.c=n,s.d=function(t,e,o){s.o(t,e)||Object.defineProperty(t,e,{enumerable:!0,get:o})},s.r=function(t){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(t,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(t,"__esModule",{value:!0})},s.t=function(t,e){if(1&e&&(t=s(t)),8&e)return t;if(4&e&&"object"===typeof t&&t&&t.__esModule)return t;var o=Object.create(null);if(s.r(o),Object.defineProperty(o,"default",{enumerable:!0,value:t}),2&e&&"string"!=typeof t)for(var n in t)s.d(o,n,function(e){return t[e]}.bind(null,n));return o},s.n=function(t){var e=t&&t.__esModule?function(){return t["default"]}:function(){return t};return s.d(e,"a",e),e},s.o=function(t,e){return Object.prototype.hasOwnProperty.call(t,e)},s.p="/";var i=window["webpackJsonp"]=window["webpackJsonp"]||[],l=i.push.bind(i);i.push=e,i=i.slice();for(var c=0;c<i.length;c++)e(i[c]);var u=l;r.push([0,"chunk-vendors"]),o()})({0:function(t,e,o){t.exports=o("56d7")},"034f":function(t,e,o){"use strict";o("85ec")},"0d6b":function(t,e,o){},1232:function(t,e,o){},"156c":function(t,e,o){},1875:function(t,e,o){"use strict";o("156c")},1915:function(t,e,o){"use strict";o("dd90")},1934:function(t,e,o){},"56d7":function(t,e,o){"use strict";o.r(e);o("e260"),o("e6cf"),o("cca6"),o("a79d");var n=o("2b0e"),a=(o("ac1f"),o("1276"),o("2f62"));n["default"].use(a["a"]);var r=new a["a"].Store({state:{token:localStorage.getItem("token")||null},getters:{authCheck:function(t,e){return!!(e.decodedToken&&e.decodedToken.exp>=(new Date).getTime()/1e3)&&(console.log("Токену осталось жить (сек):",e.decodedToken.exp-(new Date).getTime()/1e3),!0)},userRole:function(t,e){return e.decodedToken?e.decodedToken.role:null},decodedToken:function(t){if(t.token&&"undefined"!==t.token){var e=atob(t.token.split(".")[1]);return JSON.parse(e)}return null}},mutations:{logIn:function(t,e){t.token=e,localStorage.setItem("token",e)},logOut:function(t){t.token=null,localStorage.removeItem("token")}},actions:{}}),s=o("bc3a"),i=o.n(s),l=o("85fe"),c=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("div",[t.authCheck?o("el-container",{staticStyle:{"flex-direction":"column"},attrs:{id:"app"}},[o("el-header",{staticClass:"header",attrs:{height:"40px"}},[o("logout-button")],1),o("el-container",[o("el-aside",{attrs:{width:"200px"}},[o("Menu")],1),o("el-main",{staticStyle:{"padding-top":"0px"}},[o("router-view",{key:t.$route.path})],1)],1)],1):t._e(),t.authCheck?t._e():o("el-container",{attrs:{id:"app"}},[o("el-main",{staticStyle:{"padding-top":"250px"}},[o("router-view",{key:t.$route.path})],1)],1)],1)},u=[],d=(o("b0c0"),o("d3b7"),o("5530")),f=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("el-menu",{attrs:{"default-active":"1"}},t._l(t.menuItems,(function(e,n){return o("div",{key:n},[e.access.includes(t.$store.getters.userRole)?o("router-link",{attrs:{to:e.routName}},[o("el-menu-item",[o("i",{class:e.icon}),t._v(" "+t._s(e.title)+" ")])],1):t._e()],1)})),0)},m=[],p={name:"Menu",data:function(){return{menuItems:[{title:"Get product list",icon:"el-icon-document",routName:{name:"ProductList"},access:["ADMIN","USER"]},{title:"Create product",icon:"el-icon-document-add",routName:{name:"ProductFormView"},access:["ADMIN"]},{title:"Entities",icon:"el-icon-document-add",routName:{name:"UniversalListView"},access:["ADMIN"]}]}},methods:{}},h=p,g=(o("1875"),o("2877")),b=Object(g["a"])(h,f,m,!1,null,"df008708",null),v=b.exports,_=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("el-button",{staticClass:"logout-button",attrs:{type:"danger",icon:"el-icon-switch-button",circle:""},on:{click:t.logOut}})},y=[],k={name:"LogoutButton",data:function(){return{}},methods:{logOut:function(){this.$store.commit("logOut"),this.$router.push({name:"login-form"})}}},$=k,F=Object(g["a"])($,_,y,!1,null,"7d3f2e5e",null),S=F.exports,x={name:"app",components:{Menu:v,LogoutButton:S},data:function(){return{}},computed:Object(d["a"])({},Object(a["b"])(["authCheck","userRole"])),methods:{axiosSetup:function(){var t=this;this.$axios.interceptors.request.use((function(e){return e.headers["Authorization"]="Bearer ".concat(t.$store.state.token),e}),(function(t){return Promise.reject(t)})),this.$axios.interceptors.response.use((function(t){return t}),(function(e){return 403==e.response.status&&(t.$notify.error("Ошибка авторизации. Код ошибки ".concat(e.response.status)),"login-form"!==t.$route.name&&(t.$store.commit("logOut"),t.$router.push({name:"login-form"}))),Promise.reject(e)}))}},created:function(){this.axiosSetup()}},L=x,D=(o("034f"),Object(g["a"])(L,c,u,!1,null,null,null)),w=D.exports,E=o("5c96"),O=o.n(E),P=(o("0fae"),o("b2d6")),C=o.n(P);n["default"].use(O.a,{locale:C.a});o("caad"),o("45fc"),o("2532");var I=o("8c4f"),U=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("div",[o("el-table",{staticStyle:{width:"100%"},attrs:{data:t.products,"empty-text":"Loading data...",border:""}},[o("el-table-column",{attrs:{prop:"id",label:"ID",width:"100"}}),o("el-table-column",{attrs:{prop:"name",label:"NAME"}}),o("el-table-column",{attrs:{prop:"sku",label:"SKU"}}),o("el-table-column",{attrs:{prop:"supplier.name",label:"SUPPLIER"}}),o("el-table-column",{attrs:{prop:"categories[0].name",label:"CATEGORY"}}),o("el-table-column",{attrs:{label:"TOTAL QTY"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(" "+t._s(e.row.total_qty)+" "+t._s(e.row.unit.name)+" ")]}}])}),o("el-table-column",{attrs:{label:"Action",width:"172"},scopedSlots:t._u([{key:"default",fn:function(e){return[o("router-link",{attrs:{to:{name:"ProductFormViewEdit",params:{id:e.row.id}}}},[o("el-button",{staticClass:"action_btn",attrs:{size:"mini"}},["ADMIN"===t.$store.getters.userRole?o("span",[t._v("Edit")]):t._e(),"USER"===t.$store.getters.userRole?o("span",[t._v("View")]):t._e()])],1),"ADMIN"===t.$store.getters.userRole?o("el-button",{staticClass:"action_btn",attrs:{size:"mini",type:"danger"},on:{click:function(o){return t.handleDelete(e.row.id)}}},[t._v(" Delete ")]):t._e()]}}])})],1),o("div",{directives:[{name:"observe-visibility",rawName:"v-observe-visibility",value:t.handleScrollToBottom,expression:"handleScrollToBottom"}]},[t.page<t.totalPages?o("el-button",{staticClass:"load_indicator",attrs:{type:"primary",loading:t.loading,size:"small"},on:{click:t.getProductList}},[t._v(" Load more... ")]):t._e()],1)],1)},j=[],T=(o("99af"),o("c975"),o("a434"),o("2909")),R={name:"GetListProducts",data:function(){return{loading:!0,request_error:!1,page:0,totalPages:0,pageSize:10,products:[]}},methods:{getProductList:function(){var t=this;this.loading=!0,this.$axios.get("stock/?page=".concat(this.page,"&size=").concat(this.pageSize)).then((function(e){var o;(o=t.products).push.apply(o,Object(T["a"])(e.data.data.content)),t.totalPages=e.data.data.total_pages})).catch((function(e){console.log(e),t.request_error=!0})).finally((function(){t.loading=!1,t.page++}))},clear_data:function(){this.loading=!0,this.request_error=!1,this.products=[]},handleDelete:function(t){var e=this;this.$axios.delete("stock/product/".concat(t,"/")).then((function(o){console.log("successfully deleted. Server responded: ",o),e.products.splice(e.products.indexOf((function(e){return e.id===t})),1)})).catch((function(t){console.log(t),e.request_error=!0}))},handleScrollToBottom:function(t){t&&(this.loading||this.page<this.totalPages&&this.getProductList())}},created:function(){this.getProductList()}},N=R,q=(o("e248"),Object(g["a"])(N,U,j,!1,null,"373e6a1e",null)),A=q.exports,M=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("el-form",{directives:[{name:"loading",rawName:"v-loading.lock",value:!t.dataLoaded,expression:"!dataLoaded",modifiers:{lock:!0}}],ref:"form",staticClass:"product-form",attrs:{model:t.formFields,"label-position":"top",size:"small"}},[o("el-form-item",[o("el-input",{attrs:{disabled:""},model:{value:t.formFields.id,callback:function(e){t.$set(t.formFields,"id",e)},expression:"formFields.id"}},[o("template",{slot:"prepend"},[t._v("id")])],2)],1),o("el-form-item",[o("el-input",{model:{value:t.formFields.name,callback:function(e){t.$set(t.formFields,"name",e)},expression:"formFields.name"}},[o("template",{slot:"prepend"},[t._v("name")])],2)],1),o("el-form-item",[o("el-input",{model:{value:t.formFields.sku,callback:function(e){t.$set(t.formFields,"sku",e)},expression:"formFields.sku"}},[o("template",{slot:"prepend"},[t._v("sku")])],2)],1),t.dataLoaded?o("product-form-supplier",{attrs:{"selected-supplier":t.formFields.supplier},on:{"update:selectedSupplier":function(e){return t.$set(t.formFields,"supplier",e)},"update:selected-supplier":function(e){return t.$set(t.formFields,"supplier",e)}}}):t._e(),t.dataLoaded?o("product-form-category",{attrs:{"selected-categories":t.formFields.categories},on:{"update:selectedCategories":function(e){return t.$set(t.formFields,"categories",e)},"update:selected-categories":function(e){return t.$set(t.formFields,"categories",e)}}}):t._e(),t.dataLoaded?o("product-form-unit",{attrs:{"selected-unit":t.formFields.unit},on:{"update:selectedUnit":function(e){return t.$set(t.formFields,"unit",e)},"update:selected-unit":function(e){return t.$set(t.formFields,"unit",e)}}}):t._e(),t.dataLoaded?o("product-from-stock",{attrs:{product_id:t.formFields.id,"storehouses-balance":t.formFields.storehouses_balance},on:{"update:storehousesBalance":function(e){return t.$set(t.formFields,"storehouses_balance",e)},"update:storehouses-balance":function(e){return t.$set(t.formFields,"storehouses_balance",e)}}}):t._e(),"ADMIN"===t.$store.getters.userRole?o("el-form-item",[t.formFields.id?t._e():o("el-button",{attrs:{type:"success",loading:t.buttonLoading},on:{click:t.onSave}},[t._v("Save")]),t.formFields.id?o("el-button",{attrs:{type:"primary",loading:t.buttonLoading},on:{click:function(e){return t.onUpdate(t.formFields.id)}}},[t._v("Update")]):t._e(),t.formFields.id?o("el-button",{attrs:{type:"danger"},on:{click:function(e){return t.onDelete(t.formFields.id)}}},[t._v("Delete")]):t._e()],1):t._e()],1)},V=[],z=(o("4160"),o("25f0"),o("159b"),function(){var t=this,e=t.$createElement,o=t._self._c||e;return t.dataLoaded?o("el-form-item",{attrs:{label:"Categories"}},[o("el-radio-group",{model:{value:t.selectedCategory,callback:function(e){t.selectedCategory=e},expression:"selectedCategory"}},t._l(t.allCategories,(function(t){return o("el-radio-button",{key:t.id,attrs:{label:t.name}})})),1)],1):t._e()}),B=[],Q=(o("7db0"),{name:"ProductFormCategory",props:["selectedCategories"],data:function(){return{dataLoaded:!1,allCategories:[]}},computed:{selectedCategory:{get:function(){try{var t=this.selectedCategories[0].name;return t}catch(e){return null}},set:function(t){var e=[];e[0]=this.getObjectByName(t),this.updateSelectedCategoty(e)}}},methods:{loadData:function(){var t=this;try{this.$axios.get("stock/categories/").then((function(e){var o=e.data.data;t.allCategories=o,t.dataLoaded=!0})).catch((function(t){console.log(t)}))}catch(e){"TypeError"==e.name&&console.log("Create mode")}},updateSelectedCategoty:function(t){this.$emit("update:selectedCategories",t)},getObjectByName:function(t){var e=this.allCategories.find((function(e){return e.name===t}));return e}},created:function(){this.loadData()}}),G=Q,J=(o("65b3"),Object(g["a"])(G,z,B,!1,null,"0e7909e7",null)),Y=J.exports,H=function(){var t=this,e=t.$createElement,o=t._self._c||e;return t.dataLoaded?o("el-form-item",{attrs:{label:"Supplier"}},[o("el-select",{attrs:{"value-key":"id",placeholder:"Select supplier"},model:{value:t.selectedSup,callback:function(e){t.selectedSup=e},expression:"selectedSup"}},t._l(t.suppliersList,(function(e){return o("el-option",{key:e.id,attrs:{label:e.name,value:e}},[t._v(" "+t._s(e.name)+" ")])})),1)],1):t._e()},K=[],W={name:"ProductFormSupplier",props:["selectedSupplier"],data:function(){return{suppliersList:[],dataLoaded:!1}},computed:{selectedSup:{get:function(){var t=this;try{var e=this.suppliersList.find((function(e){return t.selectedSupplier.id===e.id}));return e}catch(o){return null}},set:function(t){this.$emit("update:selected-supplier",t)}}},methods:{loadData:function(){var t=this;try{this.$axios.get("stock/suppliers/").then((function(e){var o=e.data.data;t.suppliersList=o,t.dataLoaded=!0})).catch((function(t){console.log(t)}))}catch(e){"TypeError"==e.name||console.log(e)}}},created:function(){this.loadData()}},X=W,Z=Object(g["a"])(X,H,K,!1,null,"188c8440",null),tt=Z.exports,et=function(){var t=this,e=t.$createElement,o=t._self._c||e;return t.dataLoaded?o("el-form-item",{attrs:{label:"Stock"}},t._l(t.stocks,(function(e){return o("product-form-stock-el-input",{key:e.id,staticClass:"stock_input",attrs:{storehouse_name:e.name,id:e.id,quantity:e.quantity},on:{"update:quantity":function(o){return t.$set(e,"quantity",o)}}})})),1):t._e()},ot=[],nt=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("el-input",{attrs:{disabled:t.disabled},on:{input:t.updateParent},model:{value:t.stockData.local_quantity,callback:function(e){t.$set(t.stockData,"local_quantity",e)},expression:"stockData.local_quantity"}},[o("template",{slot:"prepend"},[t._v(t._s(t.stockData.local_storehouse_name))])],2)},at=[],rt={name:"ProductFormStockElInput",props:["storehouse_name","id","quantity","disabled"],data:function(){var t={local_storehouse_name:this.storehouse_name,local_id:this.id,local_quantity:this.quantity};return{stockData:t}},methods:{updateParent:function(){this.$emit("update:quantity",parseInt(this.stockData.local_quantity))}}},st=rt,it=Object(g["a"])(st,nt,at,!1,null,"5d263d74",null),lt=it.exports,ct={name:"ProductFormStock",components:{ProductFormStockElInput:lt},props:["product_id","storehouses-balance"],data:function(){return{dataLoaded:!1,stocks:[]}},methods:{loadStorehouses:function(){var t=this;this.$axios.get("stock/storehouses/").then((function(e){t.stocks=e.data.data,t.loadQuantity(t.storehousesBalance),t.dataLoaded=!0})).catch((function(t){console.log(t)}))},updateQuantity:function(){this.$emit("update:storehouses-balance",this.stocks)},loadQuantity:function(t){this.stocks.forEach((function(e){e.quantity=0,t.forEach((function(t){e.id===t.id&&(e.quantity=t.quantity)}))}))}},mounted:function(){this.loadStorehouses()},watch:{stocks:"updateQuantity"}},ut=ct,dt=(o("d1f4"),Object(g["a"])(ut,et,ot,!1,null,null,null)),ft=dt.exports,mt=function(){var t=this,e=t.$createElement,o=t._self._c||e;return t.dataLoaded?o("el-form-item",{attrs:{label:"Units"}},[o("el-select",{attrs:{"value-key":"id",placeholder:"Select units"},model:{value:t.selectedSup,callback:function(e){t.selectedSup=e},expression:"selectedSup"}},t._l(t.unitsList,(function(e){return o("el-option",{key:e.id,attrs:{label:e.name,value:e}},[t._v(" "+t._s(e.name)+" ")])})),1)],1):t._e()},pt=[],ht={name:"ProductFormUnit",props:["selectedUnit"],data:function(){return{unitsList:[],dataLoaded:!1}},computed:{selectedSup:{get:function(){var t=this;try{var e=this.unitsList.find((function(e){return t.selectedUnit.id===e.id}));return e}catch(o){return null}},set:function(t){this.$emit("update:selected-unit",t)}}},methods:{loadData:function(){var t=this;try{this.$axios.get("stock/units/").then((function(e){var o=e.data.data;t.unitsList=o,t.dataLoaded=!0})).catch((function(t){console.log(t)}))}catch(e){"TypeError"==e.name||console.log(e)}}},created:function(){this.loadData()}},gt=ht,bt=Object(g["a"])(gt,mt,pt,!1,null,"51db2595",null),vt=bt.exports,_t={components:{ProductFormCategory:Y,ProductFormSupplier:tt,ProductFromStock:ft,ProductFormUnit:vt},name:"ProductForm",data:function(){return{dataLoaded:!1,buttonLoading:!1,formFields:{id:null,name:null,sku:null,supplier:null,unit:null,categories:[],storehouses_balance:[]}}},computed:{selectedCategoryNames:function(){var t=[];return this.formFields.categories.forEach((function(e){return t.push(e.name)})),t}},methods:{onSave:function(){var t=this;this.buttonLoading=!0,this.$axios.post("stock/product/create/",this.formFields).then((function(e){console.log(e.status),201===e.status&&(t.$notify.success("Item created"),t.$router.push({name:"ProductFormViewEdit",params:{id:"".concat(e.data.data.id)}}))})).catch((function(e){t.$notify.error("Ошибка. ".concat(e)),console.log(e),t.buttonLoading=!1}))},onUpdate:function(t){var e=this;this.buttonLoading=!0,this.$axios.put("stock/product/".concat(t,"/"),this.formFields).then((function(){e.$notify.success("Item updated"),e.buttonLoading=!1})).catch((function(t){e.$notify.error("ERROR"),console.log(t)}))},onDelete:function(t){var e=this;this.buttonLoading=!0,this.$axios.delete("stock/product/".concat(t,"/")).then((function(){e.$notify.success("Item deleted"),e.$router.push({name:"ProductFormView"})})).catch((function(t){e.$notify.error("ERROR"),console.log(t)})),this.buttonLoading=!1},loadData:function(){var t=this;try{var e=this.$route.params.id.toString();this.$axios.get("stock/product/".concat(e,"/")).then((function(e){var o=e.data.data;t.formFields.id=o.id,t.formFields.name=o.name,t.formFields.sku=o.sku,t.formFields.supplier=o.supplier,t.formFields.categories=o.categories,t.formFields.unit=o.unit,t.formFields.storehouses_balance=o.storehouses_balance,t.dataLoaded=!0})).catch((function(t){console.log("loadData error: ",t)}))}catch(o){"TypeError"==o.name?(console.log("Create mode"),this.dataLoaded=!0):console.log(o)}}},created:function(){this.loadData()}},yt=_t,kt=(o("1915"),Object(g["a"])(yt,M,V,!1,null,"2cfc7427",null)),$t=kt.exports,Ft=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("div",[o("el-radio-group",{model:{value:t.selected,callback:function(e){t.selected=e},expression:"selected"}},t._l(t.entities,(function(e){return o("el-radio-button",{key:e.name,attrs:{label:e}},[t._v(" "+t._s(e.name)+" ")])})),1),o("universal-list-table",{staticClass:"table",attrs:{selected:t.selected}})],1)},St=[],xt=function(){var t=this,e=t.$createElement,o=t._self._c||e;return t.loadedData?o("el-table",{staticStyle:{width:"100%"},attrs:{data:t.loadedData,"empty-text":"Loading data...",border:""}},[t._l(t.headers,(function(t){return o("el-table-column",{key:t,attrs:{sortable:"id"==t,prop:t,label:t}})})),o("el-table-column",{attrs:{label:"Action",width:"172"},scopedSlots:t._u([{key:"default",fn:function(e){return[o("router-link",{attrs:{to:{name:"UniversalFormView",params:{type:t.selected.url,id:e.row.id}}}},[o("el-button",{staticClass:"action_btn",attrs:{size:"mini"}},[t._v(" Edit ")])],1),o("el-button",{staticClass:"action_btn",attrs:{size:"mini",type:"danger",disabled:""},on:{click:function(o){return t.handleDelete(t.selected.url,e.row.id)}}},[t._v(" Delete ")])]}}],null,!1,4095428349)})],2):t._e()},Lt=[],Dt=(o("b64b"),{name:"UniversalListTable",props:["selected"],data:function(){return{loadedData:null,request_error:!1}},computed:{headers:function(){return Object.keys(this.loadedData[0])}},methods:{loadData:function(t){var e=this;this.$axios.get("stock/".concat(t,"/")).then((function(t){e.loadedData=t.data.data})).catch((function(t){console.log(t),e.request_error=!0}))},handleDelete:function(t,e){var o=this;this.$axios.delete("stock/".concat(t,"/").concat(e,"/")).then((function(t){console.log("successfully deleted. Server responded: ",t),o.loadData})).catch((function(t){console.log(t),o.request_error=!0}))}},mounted:function(){this.loadData(this.selected.url)},watch:{selected:function(t){this.loadData(t.url)}}}),wt=Dt,Et=(o("a138"),Object(g["a"])(wt,xt,Lt,!1,null,"da173868",null)),Ot=Et.exports,Pt={components:{UniversalListTable:Ot},name:"UniversalListView",data:function(){return{entities:[{name:"SUPPLIERS",url:"suppliers"},{name:"CATEGORIES",url:"categories"},{name:"STOREHOUSES",url:"storehouses"},{name:"UNITS",url:"units"}],selected:{name:"SUPPLIERS",url:"suppliers"}}}},Ct=Pt,It=(o("b717"),Object(g["a"])(Ct,Ft,St,!1,null,"d279b4fa",null)),Ut=It.exports,jt=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("el-form",{ref:"form",staticClass:"form",attrs:{model:t.formFields,"label-position":"top",size:"small"}},[o("el-form-item",[o("el-select",{attrs:{disabled:""},model:{value:t.itemType,callback:function(e){t.itemType=e},expression:"itemType"}})],1),t._l(t.formFields,(function(e,n){return o("el-form-item",{key:n},[o("el-input",{attrs:{disabled:"id"==n},model:{value:t.formFields[n],callback:function(e){t.$set(t.formFields,n,e)},expression:"formFields[key]"}},[o("template",{slot:"prepend"},[t._v(t._s(n))])],2)],1)})),o("el-form-item",[t.formFields.id?t._e():o("el-button",{directives:[{name:"show",rawName:"v-show",value:!1,expression:"false"}],attrs:{type:"success",loading:t.buttonLoading},on:{click:t.onSave}},[t._v("Save")]),t.formFields.id?o("el-button",{attrs:{type:"primary",loading:t.buttonLoading},on:{click:function(e){return t.onUpdate(t.$route.params.type,t.formFields.id)}}},[t._v("Update")]):t._e(),t.formFields.id?o("el-button",{attrs:{disabled:"",type:"danger"},on:{click:function(e){return t.onDelete(t.formFields.id)}}},[t._v("Delete")]):t._e()],1)],2)},Tt=[],Rt={name:"UniversalFormView",data:function(){return{buttonLoading:!1,itemType:this.$route.params.type,formFields:{}}},methods:{loadData:function(t,e){var o=this;this.$axios.get("stock/".concat(t,"/").concat(e,"/")).then((function(t){var e=t.data.data;o.formFields=e})).catch((function(t){console.log("loadData error: ",t)}))},onSave:function(){},onUpdate:function(t,e){var o=this;this.buttonLoading=!0,this.$axios.put("stock/".concat(t,"/").concat(e,"/"),this.formFields).then((function(){o.buttonLoading=!1,o.$notify.success("Item updated")})).catch((function(t){o.$notify.error("ERROR"),console.log(t)}))},onDelete:function(){}},created:function(){this.loadData(this.$route.params.type,this.$route.params.id)}},Nt=Rt,qt=(o("d2b2"),Object(g["a"])(Nt,jt,Tt,!1,null,"58f509d0",null)),At=qt.exports,Mt=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("div",{staticClass:"auth-form-container"},[o("el-form",{ref:"form",staticClass:"auth-form",attrs:{model:t.formFields,"label-position":"left",size:"small",width:"400"}},[o("el-form-item",[o("el-input",{model:{value:t.formFields.email,callback:function(e){t.$set(t.formFields,"email",e)},expression:"formFields.email"}})],1),o("el-form-item",[o("el-input",{attrs:{type:"password"},model:{value:t.formFields.password,callback:function(e){t.$set(t.formFields,"password",e)},expression:"formFields.password"}})],1),o("el-form-item",[o("el-button",{attrs:{type:"primary",loading:t.loadingData},on:{click:t.submitForm}},[t._v("Log in")])],1)],1)],1)},Vt=[],zt={name:"AuthFormView",data:function(){return{loadingData:!1,formFields:{email:"",password:""}}},methods:{submitForm:function(){var t=this;this.loadingData=!0,this.$axios.post("stock/login",this.formFields).then((function(e){t.loadingData=!1,t.$store.commit("logIn",e.data.token),t.$router.push({name:"index"})})).catch((function(e){403!==e.response.status&&t.$notify.error("Ошибка соединения с сервером. Код ошибки ".concat(e.response.status)),t.loadingData=!1,console.log(e)}))}}},Bt=zt,Qt=(o("f748"),Object(g["a"])(Bt,Mt,Vt,!1,null,"a9ddd00c",null)),Gt=Qt.exports;n["default"].use(I["a"]);var Jt=[{path:"/",name:"index",component:A,meta:{access:["ADMIN","USER"]}},{path:"/login",name:"login-form",component:Gt,meta:{access:["ADMIN","USER"]},beforeEnter:function(t,e,o){r.getters.authCheck?o({name:"index"}):o()}},{path:"/product-list",name:"ProductList",component:A,meta:{access:["ADMIN","USER"]}},{path:"/product-form",name:"ProductFormView",component:$t,meta:{access:["ADMIN"]}},{path:"/product-form/:id",name:"ProductFormViewEdit",component:$t,meta:{access:["ADMIN","USER"]}},{path:"/universal-list/",name:"UniversalListView",component:Ut,meta:{access:["ADMIN"]}},{path:"/universal-form/:type/:id",name:"UniversalFormView",component:At,meta:{access:["ADMIN"]}}],Yt=new I["a"]({routes:Jt});Yt.beforeEach((function(t,e,o){"login-form"===t.name||r.getters.authCheck?"login-form"==t.name||t.matched.some((function(t){return t.meta.access.includes(r.getters.userRole)}))?o():o(!1):o({name:"login-form"})}));var Ht=Yt;i.a.defaults.baseURL="http://127.0.0.1:8001/",n["default"].use(l["a"]),n["default"].config.productionTip=!1,n["default"].prototype.$axios=i.a,new n["default"]({router:Ht,store:r,render:function(t){return t(w)}}).$mount("#app")},"65b3":function(t,e,o){"use strict";o("1934")},"732f":function(t,e,o){},"85ec":function(t,e,o){},"9d81":function(t,e,o){},"9fd4":function(t,e,o){},a138:function(t,e,o){"use strict";o("1232")},b717:function(t,e,o){"use strict";o("0d6b")},d1f4:function(t,e,o){"use strict";o("9d81")},d2b2:function(t,e,o){"use strict";o("732f")},dcbf:function(t,e,o){},dd90:function(t,e,o){},e248:function(t,e,o){"use strict";o("9fd4")},f748:function(t,e,o){"use strict";o("dcbf")}});
//# sourceMappingURL=app.4fea61d1.js.map
let product_cont= document.querySelector("#product-container");
let orders_cont= document.querySelector("#orders-container");
let users_cont= document.querySelector("#users-container");
let trans_cont= document.querySelector("#transactions-container");
let product_btn= document.querySelector("#nav-list>li:nth-child(1)");
let order_btn= document.querySelector("#nav-list>li:nth-child(2)");
let user_btn= document.querySelector("#nav-list>li:nth-child(3)");
let transaction_btn= document.querySelector("#nav-list>li:nth-child(4)");
window.onload=()=>{
    orders_cont.style.display="none";
    users_cont.style.display="none";
    trans_cont.style.display="none";
};


product_btn.onclick=()=>{
    console.log(1);
    product_cont.style.display="block";
    orders_cont.style.display="none";
    users_cont.style.display="none";
    trans_cont.style.display="none";

};


order_btn.onclick= ()=>{
    console.log(2);
    product_cont.style.display="none";
    orders_cont.style.display="block";
    users_cont.style.display="none";
    trans_cont.style.display="none";
};

user_btn.onclick=()=>{
    console.log(3);
    product_cont.style.display="none";
    orders_cont.style.display="none";
    users_cont.style.display="block";
    trans_cont.style.display="none";
};

transaction_btn.onclick=()=>{
    console.log(4);
    product_cont.style.display="none";
    orders_cont.style.display="none";
    users_cont.style.display="none";
    trans_cont.style.display="block";
};
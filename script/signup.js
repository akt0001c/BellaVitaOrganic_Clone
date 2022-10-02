let form = document.querySelector("form");
let first= document.querySelector("#fname");
let last= document.querySelector("#lname");
let email=document.querySelector("#email");
let password=document.querySelector("#pass");

form.addEventListener("submit",function(event){
    event.preventDefault();
    alert("Sign up successful and Welcome "+ first.value+" "+last.value);
    first.value="";
    last.value="";
    email.value="";
    password.value="";
    location.href="signin.html";
});
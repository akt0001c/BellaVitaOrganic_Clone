let form= document.querySelector("form");

let email= document.querySelector("#email");
let password= document.querySelector("#pass");
form.addEventListener("submit",function(event){
    event.preventDefault();
    alert("login successful");
    email.value="";
    password.value=""; 
    location.href="index.html"; 

});
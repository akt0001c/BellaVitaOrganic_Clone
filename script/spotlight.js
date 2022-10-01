 let spotlightData=[
    {
        url:"https://cdn.shopify.com/s/files/1/0054/6665/2718/files/Skin_Care_V1_Medium_717c8f68-9b82-4c7c-9ff2-0e886f52103d_480x.jpg?v=1659102928",
        name:"Skin Care"
    },
    {
        url:"https://cdn.shopify.com/s/files/1/0054/6665/2718/files/Body_Care_V2_Medium_860c536d-b4a1-4a37-b3f3-4d1c4e1f004d_480x.jpg?v=1659102928",
        name:"Body Care"
    },
    {
        url:"https://cdn.shopify.com/s/files/1/0054/6665/2718/files/Hair_Care_V1.1_Medium_a2d75bd0-3b18-4c34-92cf-04d4c74e2bcb_480x.jpg?v=1659102927",
        name:"Hair Care"
    },
    {
        url:"https://cdn.shopify.com/s/files/1/0054/6665/2718/files/Summer_Essentials_V1_Medium_10dbdd6d-898e-4ea4-8d21-d6bfe4a70bc6_480x.jpg?v=1659102927",
        name:"Summer Essentials"
    },
    {
        url:"https://cdn.shopify.com/s/files/1/0054/6665/2718/files/Sensitive_Skin_V1_Medium_e87ef676-5616-4d72-b498-92e1808e3552_480x.jpg?v=1659102928",
        name:"Sensitive Skin"
    },
    {
        url:"https://cdn.shopify.com/s/files/1/0054/6665/2718/files/Skin_Hydration_V1_Medium_bf8933f3-4b75-400b-a933-8adc21291912_480x.jpg?v=1659102928",
        name:"Skin Hydration"
    },
    {
        url:"https://cdn.shopify.com/s/files/1/0054/6665/2718/files/Detan___Exfoliation_V1_Medium_e5175d97-bd53-467d-8461-9454d3153379_480x.jpg?v=1659102928",
        name:"Exfoliation"
    },
    {
        url:"https://cdn.shopify.com/s/files/1/0054/6665/2718/files/Hair_Nourishment_V1_Medium_b8a624dd-42d3-422c-9ab2-cd97c1863f97_480x.jpg?v=1659102927",
        name:"Hair Nourishment"
    },
    {
        url:"https://cdn.shopify.com/s/files/1/0054/6665/2718/files/Skin_Care_V1_Medium_717c8f68-9b82-4c7c-9ff2-0e886f52103d_480x.jpg?v=1659102928",
        name:"Skin Care"
    },
    {
        url:"https://cdn.shopify.com/s/files/1/0054/6665/2718/files/Body_Care_V2_Medium_860c536d-b4a1-4a37-b3f3-4d1c4e1f004d_480x.jpg?v=1659102928",
        name:"Body Care"
    },
    {
        url:"https://cdn.shopify.com/s/files/1/0054/6665/2718/files/Hair_Care_V1.1_Medium_a2d75bd0-3b18-4c34-92cf-04d4c74e2bcb_480x.jpg?v=1659102927",
        name:"Hair Care"
    },
    {
        url:"https://cdn.shopify.com/s/files/1/0054/6665/2718/files/Summer_Essentials_V1_Medium_10dbdd6d-898e-4ea4-8d21-d6bfe4a70bc6_480x.jpg?v=1659102927",
        name:"Summer Essentials"
    }
];
let perpage=5;
let sheet= Math.ceil(spotlightData.length/perpage);
let slidebutton= document.querySelector("#categry-btn");

let parent= document.querySelector("#categry-page");

 
 function displayData(data){
    parent.innerHTML="";
    console.log("checking");
   data.forEach(function(el) {
    console.log(el);
       let box = document.createElement("div");
       box.setAttribute("class","categry-box");
       let image= document.createElement("img");
       image.src= el.url;
       image.setAttribute("class","categry-image");

       let name= document.createElement("p");
       name.innerText= el.name;

       box.append(image,name);
       parent.append(box); 
   });
}

displayData(spotlightData.slice(0,perpage));

for(let i=0;i<8;i++)
{
    let btn= document.createElement("span");
    btn.setAttribute("class","pagination-tab");
    btn.addEventListener("click",function(){
        displayData(spotlightData.slice(i,(perpage+i)));
    });
    slidebutton.append(btn);


}
    

       

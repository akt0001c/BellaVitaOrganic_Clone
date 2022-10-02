let imageData=[
    {
        url:"https://cdn.shopify.com/s/files/1/0054/6665/2718/files/ANI_1_480x.jpg?v=1660127230"
    },
    {
        url:"https://cdn.shopify.com/s/files/1/0054/6665/2718/files/Elle-V1_480x.png?v=1660127235"
    },
    {
        url:"https://cdn.shopify.com/s/files/1/0054/6665/2718/files/Hindustan-Times-V1_1_480x.jpg?v=1660127269"
    },
    {
        url:"https://cdn.shopify.com/s/files/1/0054/6665/2718/files/iDiva_480x.jpg?v=1660125648"
    },
    {
        url:"https://cdn.shopify.com/s/files/1/0054/6665/2718/files/Lifestyle-Asia_1_480x.jpg?v=1660127274"
    },
    {
        url:"https://cdn.shopify.com/s/files/1/0054/6665/2718/files/Mint_480x.jpg?v=1660125696"
    },
    {
        url:"https://cdn.shopify.com/s/files/1/0054/6665/2718/files/PinkVilla_480x.jpg?v=1660125706"
    },
    {
        url:"https://cdn.shopify.com/s/files/1/0054/6665/2718/files/POPXO_c5c98406-db49-4627-aedb-38a9c9c87511_480x.jpg?v=1660125726"
    },
    {
        url:"https://cdn.shopify.com/s/files/1/0054/6665/2718/files/Swirlster-NDTV_480x.jpg?v=1660125743"
    },
    {
        url:"https://cdn.shopify.com/s/files/1/0054/6665/2718/files/Your-Story_480x.jpg?v=1660127274"
    },
];

let main_imageBox= document.querySelector("#bottom2-slide");
let footer2btn= document.querySelector("#bottom2-btn");
function display3Data(data,parent){
    parent.innerHTML="";
    console.log("checking");
   data.forEach(function(el) {
    console.log(el);
       let box = document.createElement("div");
       box.setAttribute("class","categry-box");
       let image= document.createElement("img");
       image.src= el.url;
       image.setAttribute("class","luxury-img")
       box.append(image);
       parent.append(box); 
   });
}

display3Data(imageData.slice(0,6),main_imageBox);

for(let i=0;i<5 ;i++)
{
    let btn= document.createElement("span");
    btn.setAttribute("class","pagination-tab");
    btn.addEventListener("click",function(){
        display1Data(imageData.slice(i,(6+i)),main_imageBox);
    });
    footer2btn.append(btn);
}

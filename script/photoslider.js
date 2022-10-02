let luxuryData=[
    {
        url:"https://cdn.shopify.com/s/files/1/0561/9256/5292/files/category_tile_men_680x680.jpg?v=1652265181"
    },
    {
        url:"https://cdn.shopify.com/s/files/1/0561/9256/5292/files/category_tile_women_680x680.jpg?v=1652265252"
    },
    {
        url:"https://cdn.shopify.com/s/files/1/0561/9256/5292/files/category_tile_little_luxuries_680x680.jpg?v=1652266061"
    },
    {
        url:"https://cdn.shopify.com/s/files/1/0561/9256/5292/files/category_tile_unisex_680x680.jpg?v=1652266096"
    },
    {
        url:"https://cdn.shopify.com/s/files/1/0561/9256/5292/files/category_tile_giftsets_680x680.jpg?v=1652266120"
    }
];

let luxurymain= document.querySelector("#luxury-page");
function display2Data(data,parent){
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

display2Data(luxuryData,luxurymain);


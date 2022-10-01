productData=[
    {
        url:"https://cdn.shopify.com/s/files/1/0054/6665/2718/products/C-GlowFaceWash-01Medium_480x.jpg?v=1659101821",
        detail:"C-Glow Face Wash, 100ml",
        specility:"De-Tan & Brightening",
        price:"₹149"

    },
    {
        url:"https://cdn.shopify.com/s/files/1/0054/6665/2718/products/Exfoliate-01_1_Medium_a7654ecf-8f6c-4b46-9d27-f3c5e8a30f28_480x.jpg?v=1659101350",
        detail:"Exfoliate Face And Body Scrub Grit, 75gm",
        specility:"Gentle Exfoliation, Nourishing & Skin Brightening",
        price:"₹185"

    },
    {
        url:"https://cdn.shopify.com/s/files/1/0054/6665/2718/products/Eyelift-01Medium_480x.jpg?v=1659101492",
        detail:"EyeLift Under Eye Cream, 20gm",
        specility:"Repairs Dark Circles, Puffy Eyes & Wrinkles",
        price:"₹211"

    },
    
    {
        url:"https://cdn.shopify.com/s/files/1/0054/6665/2718/products/NicoLips-01Medium_480x.jpg?v=1659101603",
        detail:"NicoLips Lip Brightening Scrub, 20gm",
        specility:"Lip Brightening, Nourishing & Repairing",
        price:"₹293"

    },
    {
        url:"https://cdn.shopify.com/s/files/1/0054/6665/2718/products/KumkumadiFaceWash-01_1_50x.jpg?v=1652341314",
        detail:"Kumkumadi Face Wash - 100ml",
        specility:"Pore Cleansing, Radiating & Skin Rejuvenating",
        price:"₹259"

    },
    {
        url:"https://cdn.shopify.com/s/files/1/0054/6665/2718/products/Exfoliate_BevzillaCombo-01Medium_480x.jpg?v=1659101713",
        detail:"Ultimate Coffee Boost Combo",
        specility:"Exfoliate Face & Body Scrub, Coffee Cubes",
        price:"₹374"

    },
    {
        url:"https://cdn.shopify.com/s/files/1/0054/6665/2718/products/Hydra-plusNightCream-03_480x.jpg?v=1654165749",
        detail:"Hydra-Plus Night Cream ,50gm",
        specility:"Hydrating, Repairing And Nourishing",
        price:"₹324"

    },
    {
        url:"https://cdn.shopify.com/s/files/1/0054/6665/2718/products/Anti-AcneFaceGel-01Medium_480x.jpg?v=1659101934",
        detail:"Anti Acne, Pimple Scar Spots Removal Gel For Men & Women, 50gm",
        specility:"Pimple Scar Removal & Skin Clarifying",
        price:"₹259"

    }

]

let box= document.querySelector("#product-page");

let dataperpage=4;
let page= Math.ceil(productData.length/dataperpage);
let defaultpage= productData.slice(0,dataperpage);
let slide= document.querySelector("#product-slide");


function display(data)
{
    box.innerHTML="";
    data.forEach(function(el) {
         let div= document.createElement("div");
         div.setAttribute("class","product-box");
         let image= document.createElement("img");
         image.src= el.url;
         image.setAttribute("class","product-image");

         let innerdiv= document.createElement("div");
         innerdiv.setAttribute("class","product-innerBox");


         let heading1= document.createElement("h5");
         heading1.innerText= el.detail;

         let heading2= document.createElement("p");
         heading2.innerText= el.specility;

         let price= document.createElement("h3");
         price.innerText= el.price;

         innerdiv.append(heading1,heading2,price);
         
         let btn= document.createElement("button");
         btn.innerText= `ADD TO CART`;
         btn.setAttribute("class","product-btn");

         div.append(image,innerdiv,btn);
         box.append(div);
    });


}

display(defaultpage);

for(let i=0;i<page;i++)
{
    let slideButton= document.createElement("span");
     slideButton.setAttribute("class","slideButton");
     slideButton.innerText="  ";
     
     ;
    

     slideButton.addEventListener("click",function(){
        
        
        display(productData.slice(i*dataperpage,(i+1)*dataperpage));
       
       
         });

     
    slide.append(slideButton);

}

 let imageData1=[
    {
        url:"https://cdn.shopify.com/s/files/1/0054/6665/2718/files/1200X399_desktop_37e0e4f3-bae1-48f3-8d76-647fee274d95_1200x.jpg?v=1664376949"
    },
    {
        url:"https://cdn.shopify.com/s/files/1/0054/6665/2718/files/1200x399_9c065eb1-1986-4a47-a475-036b0bacc02e_1200x.jpg?v=1664274602"
    },
    {
        url:"https://cdn.shopify.com/s/files/1/0054/6665/2718/files/Hydra_Website_banner_1200x399_4f0cfee4-00b0-42bd-9ac5-55d8a235e0ad_1200x.png?v=1658296941"
    }
];
let slide1= document.querySelector("#first-banner");
let banner1= document.querySelector("#top-banner  img");
let tabs1= imageData1.length;
for(let i=0;i<tabs1;i++)
{
    let btn= document.createElement("span");
    btn.setAttribute("class","pagination-tab");
    btn.addEventListener("click",function(){
    banner1.setAttribute("src",imageData1[i].url);
    
    });
    slide1.append(btn);
}




let imageData2=[
    {
        url:"https://cdn.shopify.com/s/files/1/0054/6665/2718/files/Offer-Strip-Banner-v2_1200x.jpg?v=1663852851"
    },
    {
        url:"https://cdn.shopify.com/s/files/1/0054/6665/2718/files/mobikwik-1200X80_343c093e-be5c-4345-ad4f-50fb8a3db7ca_1200x.png?v=1662467840"
    }
];
let slide2= document.querySelector("#third-banner");
let banner2= document.querySelector("#last-banner img")
let tabs2= imageData2.length;
for(let j=0;j<tabs2;j++)
{
    let btn= document.createElement("span");
    btn.setAttribute("class","pagination-tab2");
    btn.addEventListener("click",function(){
        banner2.setAttribute("src",imageData2[j].url);
    });
    slide2.append(btn);
}


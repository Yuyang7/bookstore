function toPageNum(pages){
    let pageNumText = document.getElementById("pageNumText").value;
    if (pageNumText !=null && pageNumText !=""){
        var pageNum = parseInt(pageNumText);
    }
    if (pages >= pageNum && pageNum >0){
        window.location.href="/book/order/get/"+ pageNum;
    }else {
        document.getElementById("pageNumText").innerText="";
    }
 }
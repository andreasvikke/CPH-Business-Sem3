window.onload = function() {
    var divs = document.getElementsByTagName("div");
    for(i = 0; i < divs.length; i++)
        divs[i].style.backgroundColor = "blue";

    document.getElementById("colorBtn").onclick = function(){
        var divs = document.getElementsByTagName("div");
        divs[0].style.backgroundColor = "red";
        divs[1].style.backgroundColor = "yellow";
        divs[2].style.backgroundColor = "green";
    }
}
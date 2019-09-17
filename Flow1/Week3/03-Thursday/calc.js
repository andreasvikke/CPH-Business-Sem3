window.onload = function() {
    var display = document.getElementById("display");

    function calcClick() {
        var lastIndex = display.innerHTML.slice(-1);
        if(this.innerHTML == "*" || this.innerHTML == "/" || this.innerHTML == "+" || this.innerHTML == "-") {
            if(lastIndex != "*" && lastIndex != "/" && lastIndex != "+" && lastIndex != "-")
                display.innerHTML += this.innerHTML;
        } else {
            display.innerHTML += this.innerHTML;
        }
    }

    var divs = document.getElementById("buttons").children;
    for(i = 0; i < divs.length; i++) {
        if(divs[i].innerHTML != "=")
            divs[i].addEventListener('click', calcClick);
    }

    document.getElementById("calculate").onclick = function() {
        display.innerHTML = eval(display.innerHTML.replace(/[^-()\d/*+.]/g, ''));
    }
}
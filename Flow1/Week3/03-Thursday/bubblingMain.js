window.onload = function(){
    function logDiv() {
        document.getElementById("output").innerText = "Hi from " + this.id;
    }

    document.getElementById("div1").onclick = function() {
        console.log("Hi from " + this.id);
    };
    document.getElementById("div2").onclick = function() {
        console.log("Hi from " + this.id);
    };

    var divs = document.getElementById("outer").children;
    for(i = 0; i < divs.length; i++) {
        divs[i].addEventListener('click', logDiv);
    }


    var names = ["Lars", "Jan", "Peter", "Bo", "Frederik"];
    document.getElementById("list").innerHTML = arrayToList(names);
    function arrayToList(a) {
        return "<ul class='list-group'>" + a.map(e => "<li class='list-group-item'>" + e + "</li>").join('') + "</ul>";
    }

    document.getElementById("newNameForm").onsubmit = function(e) {
        e.preventDefault();
        var name = document.getElementById("newNameForm").children[0].value
        if(name == "Asger")
            alert("NO KLAMYDIA HERE!")
        else
            names.push(name);
        document.getElementById("list").innerHTML = arrayToList(names);  
    }

    document.getElementById("removeFirst").onclick = function() { 
        names.shift();
        document.getElementById("list").innerHTML = arrayToList(names); 
    }

    document.getElementById("removeLast").onclick = function() {
        names.pop();
        document.getElementById("list").innerHTML = arrayToList(names);  
    }
}
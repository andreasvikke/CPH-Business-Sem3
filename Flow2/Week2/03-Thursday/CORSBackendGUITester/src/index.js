import 'bootstrap/dist/css/bootstrap.css'
var backendURL = "https://andreasvikke.dk/CORSBackend-1.0/api/";

window.onload = () => {

    document.getElementById("all").onclick = () => {
        fetch(backendURL + "person/all")
        .then(res => res.json())
        .then(data => {
            document.getElementById("output").innerHTML = createTable(data).outerHTML;
        });
    }

    document.getElementById("get").onclick = () => {
        fetch(backendURL + "person/" + document.getElementById("personId").value)
        .then(res => res.json())
        .then(data => {
            document.getElementById("output").innerHTML = createTable(data).outerHTML;
        });
    }

    document.getElementById("edit").onclick = () => {
        fetch(backendURL + "person/" + document.getElementById("personId").value, makeOptions("PUT", {name: document.getElementById("personName").value}))
        .then(res => res.json())
        .then(data => {
            document.getElementById("output").innerHTML = createTable(data).outerHTML;
        });
    }

    document.getElementById("add").onclick = () => {
        fetch(backendURL + "person/", makeOptions("POST", {name: document.getElementById("personName").value}))
        .then(res => res.json())
        .then(data => {
            document.getElementById("output").innerHTML = createTable(data).outerHTML;
        });
    }
}

function createTable(array) {
    if (!Array.isArray(array))
        array = [array];

    var table = document.createElement("table");
    table.classList.add("table");

    var tHead = table.createTHead();
    tHead.classList.add("thead-dark");

    var tRow = tHead.insertRow(0);
    Object.keys(array[0]).map(function (key, index) {
        tRow.insertCell(index).outerHTML = "<th>" + key.charAt(0).toUpperCase() + key.slice(1); + "</th>";
    });

    var tBody = table.createTBody();
    array.map(function (obj, index) {
        var tBRow = tBody.insertRow(index);
        Object.keys(obj).map(function (key, index) {
            tBRow.insertCell(index).innerHTML = obj[key];
        });
    });

    return table;
}


function makeOptions(http_method, body) {
    var options =  {
        method: http_method,
        headers: {
        "Content-type": "application/json"
        }
    }
    if(body){
        options.body = JSON.stringify(body);
    }
    return options;
}
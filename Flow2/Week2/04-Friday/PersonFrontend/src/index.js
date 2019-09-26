import 'bootstrap/dist/css/bootstrap.css'
var backendURL = "http://localhost:8080/jpareststarter/api/";

window.onload = () => {
    loadAll();

    document.getElementById("reload").onclick = () => {
        loadAll();
    }

    document.getElementById("newPersonForm").onsubmit = (e) => {
        e.preventDefault();
        var fName = document.getElementById("newfName").value;
        var lName = document.getElementById("newlName").value;
        var phone = document.getElementById("newphone").value;
        var street = document.getElementById("newstreet").value;
        var zip = document.getElementById("newzip").value;
        var city = document.getElementById("newcity").value;
        var data = {fName : fName, lName : lName, phone : phone, address : {street : street, zip : zip, city : city}};

        fetch(backendURL + "person/add", makeOptions("POST", data))
        .then(res => res.json())
        .then(data => {
            console.log(data);
            if(data.id != null)
                $('#addPersonModal').modal('hide')
        });
    }
}

function loadAll() {
    fetch(backendURL + "person/all")
        .then(res => res.json())
        .then(data => {
            document.getElementById("output").innerHTML = createTable(data.all).outerHTML;
        });
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
            if(typeof obj[key] === 'object') {
                var objS = [];
                Object.keys(obj[key]).map(k => {
                    if(k != "id")
                        objS.push(obj[key][k])
                });
                tBRow.insertCell(index).innerHTML = objS.join(", ");
            }
            else
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
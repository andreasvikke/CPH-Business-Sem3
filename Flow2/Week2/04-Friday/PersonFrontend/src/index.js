import 'bootstrap/dist/css/bootstrap.css'
var backendURL = "https://andreasvikke.dk/RestJaxRS-1.0.1/api/";

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
            if(data.id != null) {
                $('#addPersonModal').modal('hide');
                loadAll();
            }
        });
    }

    document.getElementById("editPersonForm").onsubmit = (e) => {
        e.preventDefault();
        var id = document.getElementById("editId").value;
        var fName = document.getElementById("editfName").value;
        var lName = document.getElementById("editlName").value;
        var phone = document.getElementById("editphone").value;
        var street = document.getElementById("editstreet").value;
        var zip = document.getElementById("editzip").value;
        var city = document.getElementById("editcity").value;
        var data = {fName : fName, lName : lName, phone : phone, address : {street : street, zip : zip, city : city}};

        fetch(backendURL + "person/" + id, makeOptions("PUT", data))
        .then(res => res.json())
        .then(data => {
            console.log(data);
            if(data.id != null) {
                $('#editPersonModal').modal('hide');
                loadAll();
            }
        });
    }
}

function tBodyClickHandler(e) {
    if(e.target.id != null)
        if(e.target.classList.contains("btndelete")) {
            // Delete
            fetch(backendURL + "person/" + e.target.id, makeOptions("DELETE"))
                .then(res => res.json())
                .then(data => {
                    loadAll();
                });
        } else {
            // Edit
            fetch(backendURL + "person/" + e.target.id)
                .then(res => res.json())
                .then(data => {
                    document.getElementById("editId").value = data.id;
                    document.getElementById("editfName").value = data.fName;
                    document.getElementById("editlName").value = data.lName;
                    document.getElementById("editphone").value = data.phone;
                    document.getElementById("editstreet").value = data.address.street;
                    document.getElementById("editzip").value = data.address.zip;
                    document.getElementById("editcity").value = data.address.city;
                });
        }
}

function loadAll() {
    fetch(backendURL + "person/all")
        .then(res => res.json())
        .then(data => {
            document.getElementById("output").innerHTML = "";
            document.getElementById("output").appendChild(createTable(data.all));
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
    tRow.insertCell(0).outerHTML = "<th></th>";
    Object.keys(array[0]).map(function (key, index) {
        tRow.insertCell(index).outerHTML = "<th>" + key.charAt(0).toUpperCase() + key.slice(1); + "</th>";
    });

    var tBody = table.createTBody();
    tBody.addEventListener("click", (e) => { tBodyClickHandler(e) });
    array.map(function (obj, index) {
        var tBRow = tBody.insertRow(index);
        tBRow.insertCell(0).innerHTML = "<a href='#' class='btndelete' id='" + obj["id"] + "'>delete</a> / <a href='#' class='btnedit' id='" + obj["id"] + "' data-toggle='modal' data-target='#editPersonModal'>edit</a>";
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
import 'bootstrap/dist/css/bootstrap.css';

document.getElementById("all").onclick = () => {
    fetch("http://localhost:3333/api/users/")
        .then(res => res.json())
        .then(data => {
            document.getElementById("output").innerHTML = createTable(data).outerHTML;
        });
}
document.getElementById("get").onclick = () => {
    fetch("http://localhost:3333/api/users/" + document.getElementById("userId").value)
        .then(res => res.json())
        .then(data => {
            document.getElementById("output").innerHTML = createTable(data).outerHTML;
        });
}

function createTable(array) {
    if(!Array.isArray(array))
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
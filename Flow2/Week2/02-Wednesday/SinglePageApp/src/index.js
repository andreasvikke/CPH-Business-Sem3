import 'bootstrap/dist/css/bootstrap.css';

document.getElementById("all").onclick = () => {
    fetch("http://localhost:3333/api/users/")
        .then(res => fetchWithErrorCheck(res))
        .then(data => {
            document.getElementById("output").innerHTML = createTable(data).outerHTML;
        })
        .catch(err => {
            if (err.status) {
                err.fullError.then(e => console.log(e.msg));
            }
            else { console.log("Network error"); }
        });
}
document.getElementById("get").onclick = () => {
    console.log(document.getElementById("userId").value);
    fetch("http://localhost:3333/api/users/" + document.getElementById("userId").value)
        .then(res => fetchWithErrorCheck(res))
        .then(data => {
            document.getElementById("output").innerHTML = createTable(data).outerHTML;
        })
        .catch(err => {
            if (err.status) {
                err.fullError.then(e => console.log(e.msg));
            }
            else { console.log("Network error"); }
        });
}
document.getElementById("delete").onclick = () => {
    fetch("http://localhost:3333/api/users/" + document.getElementById("userId").value, makeOptions("DELETE"))
        .then(res => fetchWithErrorCheck(res))
        .then(data => {
            document.getElementById("output").innerHTML = createTable(data).outerHTML;
        })
        .catch(err => {
            if (err.status) {
                err.fullError.then(e => console.log(e.msg));
            }
            else { console.log("Network error"); }
        });
}

document.getElementById("createUser").onsubmit = (e) => {
    e.preventDefault();
    var name = document.getElementById("userName").value;
    var age = document.getElementById("userAge").value;
    var gender = document.getElementById("userGender").value;
    var email = document.getElementById("userEmail").value;
    var data = {name : name, age : age, gender : gender, email : email};
    fetch("http://localhost:3333/api/users/", makeOptions("POST", data))
        .then(res => fetchWithErrorCheck(res))
        .then(data => {
            document.getElementById("output").innerHTML = createTable(data).outerHTML;
        })
        .catch(err => {
            if (err.status) {
                err.fullError.then(e => console.log(e.msg));
            }
            else { console.log("Network error"); }
        });
}

document.getElementById("editUser").onclick = () => {
    var id = document.getElementById("userFormId").value;
    var name = document.getElementById("userName").value;
    var age = document.getElementById("userAge").value;
    var gender = document.getElementById("userGender").value;
    var email = document.getElementById("userEmail").value;
    var data = {name : name, age : age, gender : gender, email : email};
    fetch("http://localhost:3333/api/users/" + id, makeOptions("PUT", data))
        .then(res => fetchWithErrorCheck(res))
        .then(data => {
            document.getElementById("output").innerHTML = createTable(data).outerHTML;
        })
        .catch(err => {
            if (err.status) {
                err.fullError.then(e => console.log(e.msg));
            }
            else { console.log("Network error"); }
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
            tBRow.insertCell(index).innerHTML = obj[key];
        });
    });

    return table;
}

function fetchWithErrorCheck(res) {
    if (!res.ok) {
        return Promise.reject({ status: res.status, fullError: res.json() });;
    }
    return res.json();
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
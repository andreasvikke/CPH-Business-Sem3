window.onload = function() {
    var dataOut;

    const fetchUrl = async (url) => {
        await fetch(url)
        .then(res => res.json()) //in flow1, just do it
        .then(data => {
            dataOut = data;            
        });
    }

    document.getElementById("getUserBtn").onclick = async () => {
        await fetchUrl("https://jsonplaceholder.typicode.com/users/" + document.getElementById("userId").value); 
        document.getElementById("output").innerHTML = 
                    "Name: " + dataOut.name +
                    "<br>Phone: " + dataOut.phone+
                    "<br><br><b>Address</b>" +
                    "<br>Street: " + dataOut.address.street +
                    "<br>City: " + dataOut.address.city +
                    "<br>ZIP: " + dataOut.address.zipcode +
                    "<br>Geo (lat, lng): " + dataOut.address.geo.lat + ", " + dataOut.address.geo.lng;
    }

    document.getElementById("getAllBtn").onclick = async () => {
        await fetchUrl("https://jsonplaceholder.typicode.com/users", "all"); 
        createTable(dataOut);
    }

    function createTable(a) {
        const myNode = document.getElementById("table");
        while (myNode.firstChild) {
            myNode.removeChild(myNode.firstChild);
        }


        var header = document.getElementById("table").createTHead();
        var headRow = header.insertRow(0);
        for(i = 0; i < Object.keys(a[0]).length; i++) {
            var cell = document.createElement("TH");
            cell.innerHTML = Object.keys(a[0])[i];
            headRow.appendChild(cell);
        }

        var body = document.getElementById("table").createTFoot();
        for(i = 0; i < a.length; i++) {
            var row = body.insertRow(body.rows.length);
            for(y = 0; y < Object.keys(a[0]).length; y++){
                row.insertCell(body.rows[i].cells.length).innerHTML = a[i][Object.keys(a[0])[y]];;
            }
        }
    }


}
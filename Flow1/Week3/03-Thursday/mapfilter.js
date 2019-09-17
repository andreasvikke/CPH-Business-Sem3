window.onload = function() {
    var cars = [
        { id: 1, year: 1997, make: 'Ford', model: 'E350', price: 3000 },
        { id: 2, year: 1999, make: 'Chevy', model: 'Venture', price: 4900 },
        { id: 3, year: 2000, make: 'Chevy', model: 'Venture', price: 5000 },
        { id: 4, year: 1996, make: 'Jeep', model: 'Grand Cherokee', price: 4799 },
        { id: 5, year: 2005, make: 'Volvo', model: 'V70', price: 44799 }
    ];
    
    function createTable(a) {
        const myNode = document.getElementById("table");
        while (myNode.firstChild) {
            myNode.removeChild(myNode.firstChild);
        }


        var header = document.getElementById("table").createTHead();
        var headRow = header.insertRow(0);
        for(i = 0; i < Object.keys(a[0]).length; i++) {
            var cell =document.createElement("TH");
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

    createTable(cars);

    document.getElementById("priceForm").onsubmit = function(e){
        e.preventDefault();

        var price = document.getElementById("priceForm").children[0].value;
        createTable(cars.filter(e => e.price < Number(price)));
    }
}
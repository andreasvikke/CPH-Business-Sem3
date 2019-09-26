var backendURL = "http://localhost:8080/SVGMapProxy/proxy/country/";
var lastTarget;

window.onload = () => {
    document.getElementById("svg2").onclick = (e) => {
        if(e.target.id == "Large masses of water")
            return;

        fetch(backendURL + e.target.id.substring(0, 2))
        .then(res => res.json())
        .then(data => {
            var output = "<ul>" +
            "<li>Country: " + data[0].name + "</li>" +
            "<li>Population: " + data[0].population + "</li>" +
            "<li>Area: " + data[0].area + "</li>" +
            "<li>Borders: " + data[0].borders.join(", ") + "</li>"+
            "</ul>";

            document.getElementById("output").innerHTML = output;
        });

        if(lastTarget != null) 
            lastTarget.style.fill = "#c0c0c0";
            
        lastTarget = e.target;
        e.target.style.fill = "red";
    }
}
import 'bootstrap/dist/css/bootstrap.css'

document.getElementById("getJokes").addEventListener("click", function() {
    fetch("http://localhost:8080/QuoteWithCORS/api/jokes/random")
    .then(res => res.json())
    .then(data => {
        output(data.joke)
    });
});

document.getElementById("fourHearts").addEventListener("click", (e) => {
    output(document.getElementById(e.target.id).parentElement.id);
});


function output(quote) {
    document.getElementById("output").innerHTML = quote;
}

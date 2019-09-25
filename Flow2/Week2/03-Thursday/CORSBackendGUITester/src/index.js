import 'bootstrap/dist/css/bootstrap.css'

document.getElementById("get").onclick = () => {
    fetch("http://localhost:8080/CORSBackend/api/person")
    .then(res => res.json())
    .then(data => {
        document.getElementById("output").innerHTML = (data.name)
    });
}
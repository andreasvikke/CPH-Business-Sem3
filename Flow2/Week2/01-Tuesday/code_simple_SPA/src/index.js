import 'bootstrap/dist/css/bootstrap.css'
import jokes from "./jokes";

createJokeTable();

document.getElementById("getJoke").onclick = function() {
    let jokeId = document.getElementById("jokeId").value;
    const joke = jokes.getJokeById(jokeId);
    document.getElementById("jokeOutput").innerHTML = joke;
};

document.getElementById("addJoke").onclick = function() {
    let jokeText = document.getElementById("jokeText").value;
    const joke = jokes.addJoke(jokeText);
    createJokeTable();
};

function createJokeTable() {   
    const allJokes = jokes.getJokes().map(joke => "<li>"+joke+"</li>");
    document.getElementById("jokes").innerHTML = allJokes.join("");
}




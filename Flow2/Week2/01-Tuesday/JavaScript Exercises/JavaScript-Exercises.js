//#region Array declarations
// Names Array
var names = ["Lars", "Jan", "Peter", "Bo", "Andreas", "Frederik"];
// Numbers Array
var numbers = [1, 3, 5, 10, 11];
// Names 2
var names2 = [{
    name: "Lars",
    phone: "1234567"
}, {
    name: "Peter",
    phone: "675843"
}, {
    name: "Jan",
    phone: "98547"
}, {
    name: "Bo",
    phone: "79345"
}];
// All
var all = ["Lars", "Peter", "Jan", "Bo"];
// Numbers 2
var numbers2 = [2, 3, 67, 33];
// Members
var members = [
    {name : "Peter", age: 18},
    {name : "Jan", age: 35},
    {name : "Janne", age: 25},
    {name : "Martin", age: 22}];
// Votes
var votes = [ "Clinton","Trump","Clinton","Clinton","Trump","Trump","Trump","None"];
//#endregion

//#region 1) Using existing functions that takes a callback as an argument

// Filter
console.log(names.filter(n => n.includes("a")));

// Map
console.log(names.map(n => n.split("").reverse().join("")));
//#endregion

//#region 2) Implement user defined functions that take callbacks as an argument

// a) Filter
function myFilter(array, callback) {
    var newarray = [];

    array.forEach(element => {
        if (callback(element))
            newarray.push(element)
    });

    return newarray;
}

console.log(myFilter(names, n => n.includes("a")));

// b) Map
function myMap(array, callback) {
    var newarray = [];

    array.forEach(element => {
        newarray.push(callback(element));
    });

    return newarray;
}

console.log(myMap(names, n => n.split("").reverse().join("")));
//#endregion

//#region 3) Using the Prototype property to add new functionality to existing objects
Array.prototype.myFilter = function (callback) {
    var newarray = [];

    this.forEach(element => {
        if (callback(element))
            newarray.push(element)
    });

    return newarray;
}

console.log(names.myFilter(n => n.includes("a")));
//#endregion

//#region 4) Getting really comfortable with filter and map

// a)
console.log(numbers.map((n, i) => n + (i == numbers.length - 1 ? 0 : numbers[i + 1])));

// b)
function createNav(array) {
    var navNames = array.map(n => "    <a href='#'>" + n + "</a>");
    return "<nav>\n" + navNames.join("\n") + "\n</nav>";
}

// c)
function createTable(array) {
    var table = document.createElement("table");
    var tHead = table.createTHead();
    var tRow = tHead.insertRow(0);
    Object.keys(array[0]).map(function (key, index) {
        tRow.insertCell(index).innerHTML = key;
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

// d, e)
window.onload = function () {
    document.getElementById("nav").innerHTML = createNav(names);
    document.getElementById("box").innerHTML = createTable(names2).outerHTML;

    this.document.getElementById("filterA").onclick = function () {
        var filterNames = names.myFilter(n => n.includes("a"));
        var filterNames2 = names2.myFilter(n => n.name.includes("a"));

        document.getElementById("nav").innerHTML = createNav(filterNames);
        document.getElementById("box").innerHTML = createTable(filterNames2).outerHTML;
    }
}

//#endregion

//#region 5) Reduce
// a)
// Ved ikke hvad de mener med denne opgave så lavede en masse joins
console.log(all.join(",") + "\n" + all.join(" ") + "\n" + all.join("#") + "\n" + all.join(", #"));

// b)
console.log(numbers2.reduce((sum, num) => sum + num));

// c)
console.log(members.reduce(function (avg, member, index, arr) {
    avg += member.age;
    if (index === arr.length - 1)
        return avg / arr.length;
    return avg;
}, 0));

// d)
console.log(votes.reduce((acc, vote) => {
    if(acc[vote] == null)
        acc[vote] = 1;
    else
        acc[vote]++;
    return acc;
}, {}));
//#endregion

//#region 6) Hoisting
// Example 1)
x = 5
console.log(x);
var x;

// Example 2)
b = 6;
var y = 7
console.log(b+y);
var x;
//#endregion

//#region 7) this in JavaScript
// Example 1)
function printProp() {
    return this.prop;
}
console.log(printProp.bind({prop: "Test"})());

// Example 2)
function bar() {
    console.log(Object.prototype.toString.call(this));
}
bar.call(2);
bar.call("Hello World!")
//#endregion

//#region 8) Reusable Modules with Closures 
// 1)
function add() {
    var counter = 0;
    function plus() {counter += 1;}
    plus();   
    return counter;
}

console.log(add());

// 2)
function createPerson(pName, pAge) {
    var person = {
        name : pName,
        age : pAge,
        setAge : function(newAge) {
            this.age = newAge;
        },
        setName : function(newName) {
            this.name = newName;
        },
        getInfo : function() {
            return this.name + ", " + this.age;
        }
    } 
    return person;
}

var person = createPerson("Andreas", "21");
console.log(person.getInfo());
person.setAge(22);
console.log(person.getInfo());
person.setName("Frederik");
console.log(person.getInfo());
//#endregion
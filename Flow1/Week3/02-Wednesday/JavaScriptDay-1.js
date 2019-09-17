//#region JavaScript Functionss
//Observe: no return type, no type on parameters
function add(n1, n2){
    return n1 + n2;
}

var sub = function(n1,n2){
    return n1 - n2
}

var cb = function(n1,n2,callback){
    try {
        if(typeof n1 === "number" && typeof n2 === "number" && typeof callback === "function")
            return "Result from the two numbers: "+n1+"+"+n2+"="+callback(n1,n2);
        else
            throw new Error("Woops");
    } catch(ex) {
        console.log(ex);
    }
};

// 2)
console.log(add(1,2)); // Returns 3 as the 2 numbers are added together
console.log(add)          // Printer reference, it will print the function
console.log(add(1,2,3)) ; // Prints 3, JavaScript ignores the last parameter
console.log(add(1));      // NaN, JavaScript needs a parameter
console.log(cb(3,3,add)); // "Result from the two numbers: 3+3=6" The cb method calls the add function
console.log(cb(4,3,sub)); // "Result from the two numbers: 4+3=1" The cb method calls the sub function
console.log(cb(3,3,add())); // Error callback is not a function, The add callback is not a function
console.log(cb(3,"hh",add));// "Result from the two numbers: 3+hh=3hh", the add function will add the 2 together like a string

// 3)
console.log(cb(1,"1",add));

// 4)
var mul = (n1, n2) => n1 * n2;
console.log(cb(4,2,mul));

// 5)
console.log(cb(4,2,(n1, n2) => n1 / n2));
//#endregion

//#region Callbacks (with map, filter and forEach)
// 1)
var names = ["Lars", "Jan", "Peter", "Bo", "Frederik"];
var treeLN = names.filter((name) => name.length <= 3);
console.log(treeLN);

// 2)
var uppercase = names.map((name) => name.toUpperCase());
console.log(uppercase);

// 3)
var arrayToHTMLList = function(a) {
  return "<ul>" + a.map(e => "<li>" + e + "</li>").join('') + "</ul>";

  var html = "<ul>"
  a.forEach(e => {
      html += "<li>" + e + "</li>";
  });
  return html + "</ul>"
}
console.log(arrayToHTMLList(names));

// 4)
var cars = [
    { id: 1, year: 1997, make: 'Ford', model: 'E350', price: 3000 },
    { id: 2, year: 1999, make: 'Chevy', model: 'Venture', price: 4900 },
    { id: 3, year: 2000, make: 'Chevy', model: 'Venture', price: 5000 },
    { id: 4, year: 1996, make: 'Jeep', model: 'Grand Cherokee', price: 4799 },
    { id: 5, year: 2005, make: 'Volvo', model: 'V70', price: 44799 }
  ];
  
var cars1998 = cars.filter((car) => car.year >= 1998);
console.log(cars1998);

var carsVolvo = cars.filter((car) => car.make == "Volvo");
console.log(carsVolvo);

var cars5000 = cars.filter((car) => car.price < 5000);
console.log(cars5000);


var filterCars = function(filterText, type){
  switch(type){
    case "year": 
      return cars.filter((car) => car.year >= filterText);
    case "make":
      return cars.filter((car) => car.make == filterText);
    case "price":
      return cars.filter((car) => car.price < filterText);
  }
}

console.log(filterCars("1998", "year"));

// 4a)
var makeSQL = function(a) {
  return a.map((e) => "INSERT INTO cars (" + Object.keys(e).join(',') + ") VALUES (" + Object.keys(e).map(k => e[k]) + ");");
}

console.log("--- 4a ---");
makeSQL(cars).forEach(c => console.log(c));

// 4b)
console.log("--- 4b ---");
makeSQL(filterCars("1998", "year")).forEach(c => console.log(c));
//#endregion

//#region Asynchronous Callbacks
// Asynchronous Callbacks
// 1)
var msgPrinter = function(msg,delay){
    setTimeout(function(){
      console.log(msg);
    },delay);
  };
  console.log("aaaaaaaaaa");
  msgPrinter ("bbbbbbbbbb",2000);
  console.log("dddddddddd");
  msgPrinter ("eeeeeeeeee",1000);
  console.log("ffffffffff");

// The output will be:
// "aaaaaaaaaa"
// "dddddddddd"
// "ffffffffff"
// "eeeeeeeeee"
// "bbbbbbbbbb"


// this and constructor functions 
// 1)
function Person(name){
    this.name = name;
    console.log("Name: "+ this.name);
    setTimeout(function(){
        console.log("Hi  "+this.name);  //Explain this
    }.bind(this),2000);
}
//call it like this (do it, even if you know it’s silly ;-)
Person("Kurt Wonnegut");  //This calls the function
console.log("I'm global: "+ name);  //Explain this

// 2)
var p = new Person("Kurt Wonnegut");  //Create an instance using the constructor function
console.log("I'm global: "+ name);  //What’s different ?

// 4)
var greeter = function(){
    console.log(this.message);
};
var comp1 = { message: "Hello World" };
var comp2 = { message: "Hi" };

var g1 = greeter.bind(comp1 );//We can store a reference, with a specific “this” to use
var g2 = greeter.bind(comp2 );//And here another “this”
setTimeout(g1,500);
setTimeout(g2,1000);

// The bind functions adds the messages to this
//#endregion

//#region this and constructor functions 
var msgPrinter = function(msg,delay){
    setTimeout(function(){
      console.log(msg);
    },delay);
  };
  console.log("aaaaaaaaaa");
  msgPrinter ("bbbbbbbbbb",2000);
  console.log("dddddddddd");
  msgPrinter ("eeeeeeeeee",1000);
  console.log("ffffffffff");

// The output will be:
// "aaaaaaaaaa"
// "dddddddddd"
// "ffffffffff"
// "eeeeeeeeee"
// "bbbbbbbbbb"

//#endregion

//#region JavaScript Objects
function Person(name, birthday, phone, email){
    this.name = name;
    this.birthday = birthday;
    this.phone = phone;
    this.email = email;
}

var person = new Person("Andreas", "030698", "28438851", "andreasvikke@gmail.com");
for(prop in person) {
    console.log(prop, person[prop]);
}

delete person.email;
person["newEmail"] = "vikkedesign@gmail.com";
for(prop in person) {
    console.log(prop, person[prop]);
}
//#endregion

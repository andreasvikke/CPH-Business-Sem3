// a) Create Arrays
var boys = ["Peter", "lars", "Ole"];
var girls = ["Janne", "hanne", "Sanne"];

// b) Concat the 2 Arrays
var all = boys.concat(girls);

// c) Join
console.log("--- c) Join ---");
var commasep = all.join(',');
var hyphensep = all.join('-');
console.log(commasep);
console.log(hyphensep);

// d) Push
console.log("--- d) Push ---");
all.push("Lone", "Gitte");
console.log(all);

// e) Unshift
console.log("--- e) Unshift ---");
all.unshift("Hans", "Kurt");
console.log(all);

// f) Shift
console.log("--- f) Shift ---");
all.shift("Hans");
console.log(all);

// g) Pop
console.log("--- g) Pop ---");
all.pop("Gitte");
console.log(all);

// h) Splice
console.log("--- h) Splice ---");
all.splice(3, 2);
console.log(all);

// i) Reverse
console.log("--- i) Reverse ---");
all.reverse();
console.log(all);

// j) Sort
console.log("--- j) Sort ---");
all.sort();
console.log(all);

// k) Sort User Defined
console.log("--- k) Sort User Defined ---");
all.sort(function (a, b) {
    return a.toLowerCase().localeCompare(b.toLowerCase());
});
console.log(all);

// l) To Uppercase
console.log("--- l) To Uppercase ---");
var uppercase = all.map((name) => name.toUpperCase());
console.log(uppercase);

// m) Filter
console.log("--- m) Filter ---");
var filter = all.filter((name) => name.startsWith('l') || name.startsWith('L'));
console.log(filter);
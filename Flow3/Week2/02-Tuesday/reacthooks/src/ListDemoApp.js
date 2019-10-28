import React, { useState, replaceState } from "react";

function ListItem(props) {
    return <li>{props.number}</li>;
}

function ListRow(props) {
    return <tr><td>{props.number}</td></tr>;
}
 
function NumberList({ numbers }) {
  console.log("--NUMBERS -->",numbers)
  const listItems = numbers.map((n) => <ListItem key={n} number={n} />);
  return <ul>{listItems}</ul>;
}

function NumberTable({ numbers }) {
  console.log("--NUMBERS -->",numbers)
  const listItems = numbers.map((n) => <ListRow key={n} number={n} />);
  return <table><tbody>{listItems}</tbody></table>;
}

function ListDemo(props) {
  console.log(props.numbers)
  return (
    <div>
      <h2>All numbers passed in via props</h2>
      <NumberList numbers={props.numbers} />
      <NumberTable numbers={props.numbers} />
    </div>
  );
}
export default function App() {
  var [numbers] = useState([1]);
//   var count = 1;
//   setInterval(() => {
//     const nums = [];
//     for(var i = 1; i <= count; i++) {
//         nums.push(i);
//     }
//     count++;
//     replaceState([nums]);
//   }, 1000);
  return <ListDemo numbers={numbers} />;
}
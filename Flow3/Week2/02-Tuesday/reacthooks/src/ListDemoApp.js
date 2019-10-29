import React, { useState, useEffect } from "react";

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
  var [seconds, setSeconds] = useState([1]);

  useEffect(() => {
    const interval = setInterval(() => {
      setSeconds(seconds++);
      numbers.push(seconds);
    }, 1000);
    return () => clearInterval(interval);
  }, []);

  return <ListDemo numbers={numbers} />;
}
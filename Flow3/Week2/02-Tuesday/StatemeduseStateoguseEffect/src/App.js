import React, { useState, useEffect } from 'react';
import './App.css';

function CountToParagraph({ count }) {
  return (
    <p>Counter: {count}</p>
  )
}

function CounterSetup({ initCount, initIncre }) {
  initCount = initCount === undefined ? 0 : initCount;
  initIncre = initIncre === undefined ? 1 : initIncre;

  let[count, setCount] = useState(Number(localStorage.getItem("count")) || initCount);

  useEffect(() => {
    localStorage.setItem('count', count);
  }, [count]);

  return (
    <div className="App">
      <CountToParagraph count={count} />
      <button onClick={() => setCount(count + initIncre)}>+</button>
      <button onClick={() => setCount(count - initIncre)}>-</button>
    </div>
  );
}

function App() {
  return (
    <CounterSetup initCount={0} initIncre={5} />
  )
}

export default App;

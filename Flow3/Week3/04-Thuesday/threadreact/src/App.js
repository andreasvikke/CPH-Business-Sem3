import React, { useState, useEffect } from 'react';
import './App.css';

function TableList({ data }) {
  return (
    Object.keys(data).map((k, index) => (
      <tr key={index}>
        <td>{k}</td>
        <td>{data[k]}</td>
      </tr>
  )))
}

function App() {
  const [data, setData] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8080/ThreadDemos/api/binance')
      .then(res => res.json())
      .then(res => setData(res));
  }, [])

  return (
    <div className="App">
      <table>
        <thead>
          <tr>
            <td>Symbol</td>
            <td>Price</td>
          </tr>
        </thead>
        <tbody>
          <TableList data={data} />
        </tbody>
      </table>
    </div>
  );
}

export default App;

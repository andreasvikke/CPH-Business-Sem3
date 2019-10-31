import React, { useState, useEffect } from 'react';
import CountryTable from "./CountryTable";
import './App.css';

const App = ({factory}) => {
  const [labels, setLabels] = useState([]);
  const [countries, setCountries] = useState([]);
  const [links, setLinks] = useState("");
  const [currentPage, setCurrentPage] = useState("");

  useEffect(() => {
    factory.getLabels().then(res => res.json()).then(data => setLabels(data));
    factory.getCountries("")
      .then(res => {
        res.headers.forEach(function(value, name) {
          if(name === "link")
            setLinks(value);
        });
        return res;})
      .then(res => res.json())
      .then(data => setCountries(data));
      setCurrentPage("https://countries-project.herokuapp.com/countries?_page=1&_limit=4");
  }, [])

  useEffect(() => {
    const interval = setInterval(() => {
      //factory.getLabels().then(data => setLabels(data));
      //factory.getCountries(page).then(data => setCountries(data));
    }, 3000);
    return () => clearInterval(interval);
  }, [])

  const pageChange = (url) => {
    factory.getCountries(url)
      .then(res => {
        res.headers.forEach(function(value, name) {
          if(name === "link")
            setLinks(value);
        });
        return res;})
      .then(res => res.json())
      .then(data => setCountries(data));
      setCurrentPage(url);
  }

  const pageFilter = (filter) => {
    factory.getCountries(currentPage + "&region=" + filter)
      .then(res => {
        res.headers.forEach(function(value, name) {
          if(name === "link")
            setLinks(value);
        });
        return res;})
      .then(res => res.json())
      .then(data => setCountries(data));
  }

  return (
    <div>
      <div className="App-header">
        <h2>React, State, Fetch</h2>
      </div>
      <div className="App-intro">
        <p>Your initial task is to fetch data from the server (see exercise for how to start it),
          and create a table below, with these data</p>          
        <CountryTable
          labels={labels}
          countries={countries}
          links={links}
          pageChange={pageChange}
          pageFilter={pageFilter} />
      </div>
    </div>
  );
};


export default App;

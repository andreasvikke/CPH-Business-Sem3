import React, { useState } from "react";

const getLink = (name, linkArr) => {
  let returnLink = "";
  linkArr.forEach(link => {
    if(link.includes(name)) {
      var linkSplit = link.split(";");
      returnLink = linkSplit[0].trim().slice(1, linkSplit[0].trim().length -1);
    }
  });
  return returnLink;
}

function NavigationButton({ direction, pageChange, links }) {
  const link = getLink(direction, links.split(","))

  if(link !== "")
    return <a className="naviagtion" href={link}onClick={(e) => {e.preventDefault(); pageChange(link)}}>{direction}</a>
  else
    return <a className="naviagtion"></a>;
}



const CountryTable = ({ labels, countries, links, pageChange, pageFilter }) => {
  const [filter, setFilter] = useState("");

  const convertArray = (arr) => {
    return arr.length > 1 ? arr[0] + "(+" + (arr.length-1) + ")" : arr;
  }

  const onChange = (evt) => {
    setFilter(evt.target.value);
  }
  const onFilter = () => {
    pageFilter(filter);
  }

  return (
    <div>
      <p>Replace the thead section with a row generated from the Labels endpoint</p>
      <p>Replace the tbody section with rows generated from the countries endpoint</p>
      <table className="table">
        <thead>
          <tr>
            {labels.map((l, index) => <td key={index}>{l}</td>)}
          </tr>
        </thead>
        
        <tbody>
            {countries.map((c, index) => (
              <tr key={index}>
                <td>{c.name}</td>
                <td>{c.capital}</td>
                <td>{c.region}</td>
                <td>{c.population}</td>
                <td>{c.area}</td>
                <td>{convertArray(c.timezones)}</td>
                <td>{convertArray(c.borders)}</td>
                <td>{c.topLevelDomain}</td>
                <td>{convertArray(c.currencies)}</td>
                <td>{convertArray(c.languages)}</td>
              </tr>
            ))}
        </tbody>
      </table>
      <NavigationButton direction="first" pageChange={pageChange} links={links} />
      <NavigationButton direction="prev" pageChange={pageChange} links={links} />
      <NavigationButton direction="next" pageChange={pageChange} links={links} />
      <NavigationButton direction="last" pageChange={pageChange} links={links} />
      <br /><br />
      <input className="form-control" name="region" onChange={onChange} placeholder="Region Filter" />
      <button className="btn btn-primary" onClick={onFilter}>Filter</button>
    </div>
  );
};
export default CountryTable;